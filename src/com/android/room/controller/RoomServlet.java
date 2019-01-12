package com.android.room.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.employee.model.EmployeeService;
import com.android.employee.model.EmployeeVO;
import com.android.member.model.MemberService;
import com.android.member.model.MemberVO;
import com.android.room.model.Room;
import com.android.room.model.RoomService;
import com.android.tool.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class RoomServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	Gson gson = new Gson();                      // 日期格式設定
    	BufferedReader br = req.getReader();
    	StringBuilder jsonIn = new StringBuilder();
    	String tempStr = null;
    	while((tempStr = br.readLine()) != null)
    		jsonIn.append(tempStr);
    	System.out.println("RoomServlet jsonIn = " + jsonIn);      //觀察用
    	
    	RoomService roomSvc = new RoomService();
    	JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);   // 轉成 jsonObject 物件
    	String action = jsonObject.get("action").getAsString();
    	
    	if("getRoomState".equals(action)) {      
    		String braId = jsonObject.get("braId").getAsString();
    		String outStr = gson.toJson(roomSvc.getRoomByBranchClean(braId, 3));      	//依分店找出待打掃的房間 =>打掃中  3
    		writeText(res, outStr);
    	} else if("updateRoomState".equals(action)) {
    		String roomId = jsonObject.get("roomId").getAsString();
    		roomSvc.updateRSByRoomID(1, roomId);    //用房間ID修改房間狀態 => 轉為空房 1
    		System.out.println("----------------------------------------------");
    	}
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
    
    private void writeText(HttpServletResponse res, String outText) throws IOException {
    	res.setContentType(CONTENT_TYPE);
    	PrintWriter out = res.getWriter();             
    	out.print(outText);
    	out.close();
		System.out.println("output = " + outText);        //觀察用
    	System.out.println("----------------------------------------------------------------------------------");      
	}
}


