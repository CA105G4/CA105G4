package com.question.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.Date; 
import java.util.HashMap; 
import java.util.HashSet; 
import java.util.List; 
import java.util.Map; 
import java.util.Set; 
import javax.websocket.OnClose;
import javax.websocket.OnMessage; 
import javax.websocket.OnOpen; 
import javax.websocket.Session; 
import javax.websocket.server.ServerEndpoint;
import com.question.model.*;

import com.google.gson.Gson;


@ServerEndpoint("/question")
public class QuestionEchoServer {
	private static Set<QuestionEchoServer> sockets=new HashSet<QuestionEchoServer>(); 
	private Session session; 
	private static Map<String, Session> map = new HashMap<String, Session>(); 
	private static List<String> names = new ArrayList<String>(); 
	private String username; 
	private Gson gson=new Gson();



	@OnOpen
	 public void open(Session session){ 
		this.session = session; 
		sockets.add(this);
//		String queryString = session.getQueryString();
		String queryString="";
		
		try {
			queryString = URLDecoder.decode(session.getQueryString(), "UTF-8");
			 System.out.println("queryString:" +queryString);
			 this.username = queryString.substring(queryString.indexOf("=")+1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	    System.out.println("queryString:" +queryString);
//	    this.username = queryString.substring(queryString.indexOf("=")+1);
	    names.add(this.username);
	    this.map.put(this.username, this.session); 
	    System.out.println("會員"+this.username+"加入聊天室"); 
	    QuestionMessageRedis message = new QuestionMessageRedis(); 
	    message.setNames(names);
//員工
	    if( !(this.username.equals("元元") || this.username.equals("商商")) ) {
	    	System.out.println(this.username);
	    	message.setAlert("會員"+this.username+"加入！！！");
	    }
	    broadcast(sockets, gson.toJson(message) );
	}
	
	@OnClose
    public void close(Session session){
		sockets.remove(this);
		names.remove(this.username);
        System.out.println("會員"+this.username+"退出聊天室");
        QuestionMessageRedis message = new QuestionMessageRedis();
        message.setNames(names);
        message.setAlert(this.username+"退出！！！");
        broadcast(sockets, gson.toJson(message));
    }
	
	
	@OnMessage
    public void receive(Session session,String msg) throws IOException{
		
		SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm:ss MM/dd");
		Date current = new Date();
		QuestionRedisVO vo = gson.fromJson(msg, QuestionRedisVO.class);
		
		if(vo.getType()==1){
			QuestionMessageRedis message = new QuestionMessageRedis();
            message.setDate(sdFormat.format(current).toString());
            message.setFrom(this.username);
            message.setSendMsg("<font color=red> 系統管理員公告：</font>     " + vo.getMsg());
            broadcast(sockets, gson.toJson(message));
		}
		else if(vo.getType()==3) {
			String from = this.username;
//			System.out.println("getType()==3 from:" + from);
			String to  = vo.getTo();
//			System.out.println("getType()==3 to:" + to);
			String fromVO = vo.getFrom();

			List<String> history = JedisMessage.getMsg(from, to);
//			System.out.println("history:" + history);
			String historyMsg = gson.toJson(history);
//			System.out.println("historyMsg:" + historyMsg);
			QuestionRedisVO voHisotry = new QuestionRedisVO(to,fromVO,historyMsg,3);
//			System.out.println("voHisotry:" + voHisotry);
			if(session != null && session.isOpen()) {
				session.getAsyncRemote().sendText(gson.toJson(voHisotry));
				return;
			}
		}
		else{		
			QuestionMessageRedis message = new QuestionMessageRedis();
            message.setDate(sdFormat.format(current).toString());
            message.setFrom(this.username); 
//          message.setAlert(vo.getMsg()); 
            message.setSendMsg(vo.getMsg());
            String to  = vo.getTo();
//            System.out.println("to:" + to);
//            System.out.println("msg:" + msg);
            String from = message.getFrom();
//            System.out.println("from:" + from);
            Session to_session = this.map.get(to);
            to_session.getAsyncRemote().sendText(gson.toJson(message));
            JedisMessage.saveMessage(from, to, msg);    
		}
		
		
		
	}
	
	public void broadcast(Set<QuestionEchoServer> sockets ,String msg){
		for(QuestionEchoServer socket : sockets){
            socket.session.getAsyncRemote().sendText(msg);
		}
	}
}
