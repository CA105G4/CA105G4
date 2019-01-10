package com.room.model;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/AjaxResRoomServlet")
public class AjaxResRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public  void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public  void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("UpdateRoomState".equals(action)) {
			
			Integer roomNo =new Integer(req.getParameter("roomNo"));
			Integer roomState = new Integer(req.getParameter("roomState"));
			
			Integer clean = new Integer(req.getParameter("clean"));
			Integer checkIn = new Integer(req.getParameter("checkIn"));
			Integer empty = new Integer(req.getParameter("empty"));
			Integer outOfOrder = new Integer(req.getParameter("outOfOrder"));
			Integer reserved = new Integer(req.getParameter("reserved"));

			System.out.println("房號:"+roomNo);
			System.out.println("房間狀態:"+roomState);
			System.out.println("起始----------------");
			System.out.println("空房總共:"+empty);
			System.out.println("入住總共:"+checkIn);
			System.out.println("打掃總共:"+clean);
			System.out.println("維修總共:"+outOfOrder);
			System.out.println("保留總共:"+reserved);
			
			RoomService rSvc = new RoomService();
			//更新所選的那間房況
			rSvc.updateRSByRoomNo(roomState, roomNo);
			//各個房間狀態數量
			empty = rSvc.getEachRoomState(1,"B01");
			checkIn = rSvc.getEachRoomState(2,"B01");
			clean = rSvc.getEachRoomState(3,"B01");
			outOfOrder = rSvc.getEachRoomState(4,"B01");
			reserved = rSvc.getEachRoomState(5,"B01");
			
			System.out.println("結束----------------");
			System.out.println("空房總共:"+empty);
			System.out.println("入住總共:"+checkIn);
			System.out.println("打掃總共:"+clean);
			System.out.println("維修總共:"+outOfOrder);
			System.out.println("保留總共:"+reserved);
			
			JSONObject jobj = new JSONObject();
			try {
				jobj.put("roomNo", roomNo);
				jobj.put("roomState", roomState);
				
				jobj.put("empty", empty);
				jobj.put("checkIn", checkIn);
				jobj.put("clean", clean);
				jobj.put("outOfOrder", outOfOrder);
				jobj.put("reserved", reserved);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(jobj.toString());
			out.flush();
			out.close();
		}
	}

}