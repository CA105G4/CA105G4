package com.orders.controller;

import java.io.IOException;
import java.util.*;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/OrdersEchoServer")
public class OrdersEchoServer {
	
	private static final Set<Session> allSessions = Collections.synchronizedSet(new HashSet<Session>());
	
	public OrdersEchoServer() {

	};
	
	Session session = null;
	@OnOpen
	public void onOpen(Session userSession) throws IOException {
		allSessions.add(userSession);
		
	}
	
	@OnMessage
	public void onMessage(Session userSession, String message) {
		for(Session session : allSessions) {
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(message+"Hello from WebSocket");
			}
		}
//		System.out.println(message);
		System.out.println("WebSocket push succeed.");
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace(System.err);
	}
	
	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		session = null;
		System.out.println(userSession.getId()+" Disconnected :"+ Integer.toString(reason.getCloseCode().getCode()));
	}
	
	
}
