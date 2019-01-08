package com.room.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.room.model.RoomService;
import com.room.model.RoomVO;

public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		List<String> errMessage = new LinkedList<String>();
		req.setAttribute("errMsseage", errMessage);
		
		//來自HomePage.jsp的請求
		if("getOne_For_Display".equals(action)) {
			String roomID = req.getParameter("roomID");
			RoomService roomSvc = new RoomService();
			RoomVO roomVO = roomSvc.getOneRoom(roomID);
			req.setAttribute("roomVO", roomVO);
			RequestDispatcher rd = req.getRequestDispatcher("/back-end/room/listOneRoom.jsp");
			rd.forward(req, res);
		}
		
		//來自HomePage.jsp依分店的請求
		if("getRoomByBranch_For_Display".equals(action)) {
//			System.out.println("in");
			String braID = req.getParameter("braID");
//			System.out.println(braID);
			RoomService roomSvc = new RoomService();
			List<RoomVO> set = roomSvc.getRoomByBranch(braID);
//			for(RoomVO roomVO : set) {
//				System.out.println(roomVO.getRoomID());
//				System.out.println(roomVO.getRoomTypeID());
//				System.out.println(roomVO.getRoomNo());
//				System.out.println(roomVO.getRoomState());
//				System.out.println(roomVO.getMemName());
//				System.out.println("----------------------------------");
//				System.out.println("Successfully Search!");
//				}
			req.setAttribute("set", set);
			RequestDispatcher rd = req.getRequestDispatcher("/back-end/room/listRoomByBranch.jsp");
			rd.forward(req, res);
		}
		
		
		
		//來自 listAllRoom的請求
		if("getOne_For_Update".equals(action)) {
			//接受請求參數
			String roomID = req.getParameter("roomID");
			//開始查詢資料
			RoomService roomSvc = new RoomService();
			RoomVO roomVO = roomSvc.getOneRoom(roomID);
			req.setAttribute("roomVO", roomVO);
			//轉交到可修改的地方
			RequestDispatcher dispatcher = req.getRequestDispatcher("/back-end/room/updateOneRoom.jsp");
			dispatcher.forward(req, res);
		}
		
		
		
		
		
		//來自updateOneRoom的請求
		if("confirm_Modify".equals(action)) {
			//接受請求參數，格式錯誤處理
			String roomID= req.getParameter("roomID").trim();	
//			String roomTypeIDReg = "^RT\\d{2}$";
//			System.out.println(roomTypeID);
//			if(roomTypeID == null || roomTypeID.trim().length() == 0) {
//				errMessage.add("房型代號，不得為空白");
//				roomTypeID = "RT01";
//				
//			}else if(!roomTypeID.trim().matches(roomTypeIDReg)) {
//				errMessage.add("房型代號前兩個字母為RT，後兩位為0-9的數字");
//				roomTypeID = "RT01";
//			}
			
			
			String roomTypeID = req.getParameter("roomTypeID").trim();
//			System.out.println(roomTypeID);
			String braID = req.getParameter("braID").trim();
//			String braIDReg = "^B0\\d$";
//			System.out.println(braID);
//			if(braID == null || braID.trim().length() == 0) {
//				errMessage.add("分店代號，不得為空白");
//				braID = "B03";
//			}else if(!braID.trim().matches(braIDReg)) {
//				errMessage.add("分店代號須為B開頭，第一位數字須為0,最後一位為2以上的數字");
//				braID = "B03";
//			}
			
			Integer roomNo = Integer.parseInt(req.getParameter("roomNo").trim()); 
//			try {
//				roomNo = new Integer(req.getParameter("roomNo").trim());
//				if(roomNo<0 || roomNo>=1000) {
//					errMessage.add("房號不得為負值，且不得大於1000");
//				}
//			} catch (NumberFormatException e) {
//				roomNo = 123;
//				errMessage.add("房號請填數字");
//			}
			
			Integer roomState = Integer.parseInt(req.getParameter("roomState").trim()); 
//			try {
//				roomState = new Integer(req.getParameter("roomState").trim());
//				if(roomState<1 || roomState>5) {
//					errMessage.add("房間狀態只有1-5");
//				}
//			} catch(NumberFormatException e) {
//				roomState = 1;
//				errMessage.add("房間狀態請填數字");
//			}
			
			
			String memName = req.getParameter("memName").trim();
			if(memName == null || memName.trim().length() == 0) {
				memName = "湯姆貓";
			}
			RoomVO roomVO = new RoomVO();
			roomVO.setRoomID(roomID);
			roomVO.setRoomTypeID(roomTypeID);
			roomVO.setBraID(braID);
			roomVO.setRoomNo(roomNo);
			roomVO.setRoomState(roomState);
			roomVO.setMemName(memName);
			//錯誤退回原畫面
			if(!errMessage.isEmpty()) {
				req.setAttribute("roomVO", roomVO);
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/room/updateOneRoom.jsp");
				failure.forward(req, res);
				return;
			}
			//開始更新
			RoomService roomSvc = new RoomService();
			roomVO = roomSvc.updateRoom(roomID, roomTypeID, braID, roomNo, roomState, memName);
			req.setAttribute("roomVO", roomVO);
			//修改成功轉交回原畫面查看
			RequestDispatcher success = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
			success.forward(req,res);
		}
		
		
		//來自addRoom.jsp的請求
		if("confirm_Add".equals(action)) {
			//接受請求參數，錯誤驗證處理
			String roomTypeID = req.getParameter("roomTypeID").trim();
//			if(roomTypeID != null) {
//				System.out.println("motherfucker");
//			}
//			String roomTypeIDReg = "^RT\\d{2}$";
//			if(roomTypeID == null || roomTypeID.trim().length() == 0) {
//				errMessage.add("房型代號，不得為空白");
//				roomTypeID = "RT01";	
//			}else if(!roomTypeID.trim().matches(roomTypeIDReg)) {
//				errMessage.add("房型代號前兩個字母為RT，後兩位為0-9的數字");
//				roomTypeID = "RT01";
//			}
			
			String braID = req.getParameter("braID").trim();
			String braIDReg = "^B0\\d$";
//			System.out.println(braID);
			if(braID == null || braID.trim().length() == 0) {
				errMessage.add("分店代號，不得為空白");
				braID = "B02";
			}else if(!braID.trim().matches(braIDReg)) {
				errMessage.add("分店代號須為B開頭，第一位數字須為0,最後一位為2以上的數字");
				braID = "B02";
			}
			
			Integer roomNo = null; 
			try {
				roomNo = new Integer(req.getParameter("roomNo").trim());
				if(roomNo<0 || roomNo>=1000) {
					errMessage.add("房號不得為負值，且不得大於1000");
				}
			} catch (NumberFormatException e) {
				roomNo = 123;
				errMessage.add("房號請填數字");
			}
			
			Integer roomState = Integer.parseInt(req.getParameter("roomState").trim()); 
//			try {
//				roomState = new Integer(req.getParameter("roomState").trim());
//				if(roomState<1 || roomState>5) {
//					errMessage.add("房間狀態只有1-5");
//				}
//			} catch(NumberFormatException e) {
//				roomState = 1;
//				errMessage.add("房間狀態請填數字");
//			}
			
			String memName = req.getParameter("memName").trim();
			if(memName == null || memName.trim().length() == 0) {
				memName = "傑利鼠";
			}
			RoomVO roomVO = new RoomVO();
			roomVO.setRoomTypeID(roomTypeID);
			roomVO.setBraID(braID);
			roomVO.setRoomNo(roomNo);
			roomVO.setRoomState(roomState);
			roomVO.setMemName(memName);
			//錯誤退回原畫面
			if(!errMessage.isEmpty()) {
				req.setAttribute("roomVO", roomVO);
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/room/addRoom.jsp");
				failure.forward(req, res);
				return;
			}
			//開始新增
			RoomService roomSvc = new RoomService();
			roomVO = roomSvc.addRoom(roomTypeID, braID, roomNo, roomState, memName);
			req.setAttribute("roomVO", roomVO);
			//轉交回查全部的頁面
			RequestDispatcher success = req.getRequestDispatcher("/back-end/room/listAllRoom.jsp");
			success.forward(req, res);
		}
		
	}
	
}
