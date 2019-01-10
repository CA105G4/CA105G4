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
			System.out.println(roomNo);
			System.out.println(roomState);
			
			RoomService rSvc = new RoomService();
			rSvc.updateRSByRoomNo(roomState, roomNo);
			JSONObject jobj = new JSONObject();
			try {
				jobj.put("roomNo", roomNo);
				jobj.put("roomState", roomState);
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
