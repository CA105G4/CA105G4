package com.roomType.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.activity.model.*;
import com.collectRoomType.model.*;
import com.member.model.MemberVO;
import com.roomType.model.*;

@MultipartConfig
public class RoomTypeServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("GetOneUpdate".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/******接收請求參數取得主鍵******/
				String rtID = req.getParameter("rtID");	
				
				/******開始查詢******/
				RoomTypeService rtsvc = new RoomTypeService();
				RoomTypeVO rtVO = rtsvc.getOneRoomType(rtID);
				
				/******查詢完成，set之後，執行轉交******/
				req.setAttribute("rtVO", rtVO);
				String url = "/back-end/roomType/update_roomType_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomType/select_roomType_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			
			/******接收請求參數，開始錯誤處理******/
			String rtID = req.getParameter("rtID");
			
			
			String braID = req.getParameter("braID");
			if(braID == null || braID.trim().length() == 0) {
				braID="RT10";
				errorMsgs.add("分店編號，請勿空白!");
			}
			
			String rtName = req.getParameter("rtName");
			if(rtName == null || rtName.trim().length() == 0) {
				rtName = "新增的房型";
				errorMsgs.add("分店名稱，請勿空白");
			}
			
			byte[] rtPic = null;
			Part part = req.getPart("rtPic");
			if(part == null || part.getSubmittedFileName().length() == 0) {
				RoomTypeService rtSvc = new RoomTypeService();
				RoomTypeVO rtvo = rtSvc.getOneRoomType(rtID);
				rtPic = rtvo.getRtPic();
			}else {
				InputStream in =  part.getInputStream();
				rtPic = new byte[in.available()];
				in.read(rtPic);
				in.close();
			}
			
			String rtIntro =req.getParameter("rtIntro");
			if(rtIntro == null || rtIntro.trim().length() == 0) {
				rtIntro = "自己新增的資訊唷唷唷唷!";
				errorMsgs.add("房型介紹，請勿空白!");
			}
			
			Integer rtMinimum =null;
			try {
				rtMinimum = new Integer(req.getParameter("rtMinimum").trim());
			}catch(NumberFormatException e) {
				rtMinimum = 2;
				errorMsgs.add("一般住房人數:請填數字!");
			}
			 
			Integer rtLimit;
			try {
				rtLimit = new Integer(req.getParameter("rtLimit").trim());
			}catch(NumberFormatException e) {
				rtLimit = 3;
				errorMsgs.add("住房上限人數:請填數字!");
			}
				
			Integer weeklyPrice;
			try {
				weeklyPrice = new Integer(req.getParameter("weeklyPrice"));
			}catch(NumberFormatException e) {
				weeklyPrice = 3000;
				errorMsgs.add("住房上限人數:請填數字!");
			}
			
			Integer holidayPrice;
			try {
				holidayPrice = new Integer(req.getParameter("holidayPrice"));
			}catch(NumberFormatException e) {
				holidayPrice = 3000;
				errorMsgs.add("平日價格:請填數字!");
			}
	
			Integer total;
			try {
				total = new Integer(req.getParameter("total"));
			}catch(NumberFormatException e) {
				total = 3;
				errorMsgs.add("房型數量:請填數字");
			}
			
			RoomTypeVO rtVO = new RoomTypeVO();
			rtVO.setBraID(braID);
			rtVO.setRtName(rtName);
			rtVO.setRtPic(rtPic);
			rtVO.setRtIntro(rtIntro);
			rtVO.setRtMinimum(rtMinimum);
			rtVO.setRtLimit(rtLimit);
			rtVO.setWeeklyPrice(weeklyPrice);
			rtVO.setHolidayPrice(holidayPrice);
			rtVO.setTotal(total);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("rtVO", rtVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomType/update_roomType_input.jsp");
				failureView.forward(req, res);
				return;
			}			
			
			/******開始修改資料******/
			RoomTypeService rtSvc = new RoomTypeService();
			rtVO = rtSvc.updateRoomType(rtID, braID, rtName, rtPic, rtIntro, rtMinimum, rtLimit, weeklyPrice, holidayPrice, total);
			
			/******修改完資料，要set，然後再轉交******/
			req.setAttribute("rtVO", rtVO);
			String url = "/back-end/roomType/listAllRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/******接收請求參數，開始錯誤處理******/
			
			String braID = req.getParameter("braID");
			if(braID == null || braID.trim().length() == 0) {
				braID = "B01";
				errorMsgs.add("分店編號，請勿空白!");
			}
			
			String rtName = req.getParameter("rtName");
			if(rtName == null || rtName.trim().length() == 0) {
				rtName = "自訂多人房";
				errorMsgs.add("房型名稱，請勿空白!");
			}
			
			byte[] rtPic = null;
			Part pic = req.getPart("rtPic");
			if(pic == null || pic.getSubmittedFileName().length() == 0) {
				errorMsgs.add("房型照片:請上傳照片!");
			}else {
				InputStream in = pic.getInputStream();
				rtPic = new byte[in.available()];
				in.read(rtPic);
				in.close();
			}
			
			String rtIntro = req.getParameter("rtIntro");
			if(rtIntro == null || rtIntro.trim().length() == 0) {
				rtIntro = "自己打的介紹rrrrr";
				errorMsgs.add("房型介紹，請勿空白!");
			}
			
			Integer rtMinimum = null;
			try {
				rtMinimum = new Integer(req.getParameter("rtMinimum").trim());
			}catch(NumberFormatException e) {
				rtMinimum = 2;
				errorMsgs.add("一般住宿人數:請輸入數字!");
			}
			
			Integer rtLimit = null;
			try {
			rtLimit = new Integer(req.getParameter("rtLimit").trim());
			}catch(NumberFormatException e) {
				rtLimit = 3;
				errorMsgs.add("上限人數:請輸入數字!");
			}
			
			Integer weeklyPrice = null;
			try {
				weeklyPrice = new Integer(req.getParameter("weeklyPrice").trim());
			}catch(NumberFormatException e) {
				weeklyPrice = 3333;
				errorMsgs.add("平日價格:請輸入數字!");
			}
			
			Integer holidayPrice = null;
			try {
				holidayPrice = new Integer(req.getParameter("holidayPrice").trim());
			}catch(NumberFormatException e) {
				holidayPrice = 6666;
				errorMsgs.add("假日價格:請輸入數字!");
			}
			
			Integer total = null;
			try {
				total = new Integer(req.getParameter("total").trim());
			}catch(NumberFormatException e) {
				total = 10;
				errorMsgs.add("房間總數量:請填入數字!");
			}
			
			RoomTypeVO rtVO = new RoomTypeVO();
			rtVO.setBraID(braID);
			rtVO.setRtName(rtName);
			rtVO.setRtPic(rtPic);
			System.out.println(rtVO.getRtPic());
			rtVO.setRtIntro(rtIntro);
			rtVO.setRtMinimum(rtMinimum);
			rtVO.setRtLimit(rtLimit);
			rtVO.setWeeklyPrice(weeklyPrice);
			rtVO.setHolidayPrice(holidayPrice);
			rtVO.setTotal(total);
			
			if(!errorMsgs.isEmpty()) {	//如果驗證有錯誤，轉交回同一頁面，但會把原本打的東西記起來
				for(String s:errorMsgs) {
					System.out.println(s);
				}
				
				req.setAttribute("rtVO", rtVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/roomType/addroomType.jsp");
				failureView.forward(req, res);
				return;	//跳離這個方法
			}
			
			/******使用service新增資料******/
			RoomTypeService rtSvc = new RoomTypeService();
			rtSvc.addRoomType(braID, rtName, rtPic, rtIntro, rtMinimum, rtLimit, weeklyPrice, holidayPrice, total);
			
			/******新增完成，set完後，準備轉交******/
			String url = "/back-end/roomType/listAllRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		
		if("search".equals(action)) {
			
	System.out.println(action);		
			//1.接收查詢參數
			try {
				String checkinStr= req.getParameter("startdate");
				String checkoutStr =req.getParameter("enddate");
				/******判斷活動時間******/
				Date checkin = Date.valueOf(checkinStr);
				Date checkout = Date.valueOf(checkoutStr);
System.out.println(checkin  + "  " + checkin.getTime());
System.out.println(checkout + "  " + checkout.getTime());	
				ActivityService actSvc =new ActivityService();
				List<ActivityVO> actList =actSvc.getAll();
				
				for(ActivityVO actVO:actList) {
					long in = checkin.getTime();    //入住時間
//					long out =checkout.getTime();
					long actStart=actVO.getActStart().getTime();
					long actEnd=actVO.getActEnd().getTime();
System.out.println(actStart + "  " + actVO.getActStart().getTime());		
System.out.println(actEnd + "  " + actVO.getActEnd().getTime());		
					if(in>actStart && in<actEnd) {
						req.setAttribute("actVOInTime", actVO);
					}
					
				}
				
				
				/******判斷活動時間******/
				String braID =req.getParameter("braID"); 
System.out.println(braID);
				//2.找尋房間
//				RoomTypeCompositeQuery rtCQ =new RoomTypeCompositeQuery();
				
				List<RoomTypeVO>searchList =RoomTypeCompositeQuery.searchRoomTypeMinRoom(braID, checkinStr, checkoutStr);
				
				RoomTypeService rtSvc =new RoomTypeService();
				Set<RoomTypeVO>searchSet =rtSvc.getAllInSet();
				//3.轉交房型頁面
				
				req.setAttribute("checkinDate", checkinStr);
				req.setAttribute("checkoutDate", checkoutStr);
				
				req.setAttribute("searchList",searchList);
				req.setAttribute("searchSet",searchSet);
				String url ="/front-end/roomType/searchRoomType.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		
		if("get_member_displaycrt".equals(action)) {
			List<String>errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			CollectRoomTypeService crtSvc =new CollectRoomTypeService();
			
			try {
			String memID =req.getParameter("memID").trim();
			
			if(memID==null||memID.trim().length()==0) {
				errorMsgs.add("會員編號有誤");
			}
			
			List<CollectRoomTypeVO>crtList = crtSvc.findByMemID(memID);
			
			req.setAttribute("crtList", crtList);
			
			String url = "/front-end/roomType/myRoomType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/myAccountMyPage.jsp");
				failureView.forward(req, res);
			}
			
		}
		
		if("collect_room".equals(action)) {
			List<String>errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			CollectRoomTypeService crtSvc =new CollectRoomTypeService();
			CollectRoomTypeVO  crtVO =new CollectRoomTypeVO();
			try {
	//			從會員session存取會員ID
				HttpSession session =  req.getSession();
				MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
				String memID = memVO.getMemID();
				System.out.println(memID);
	//			String memID = "M0010";
				String rtID =req.getParameter("rtID");
				System.out.println(rtID);
				crtVO=crtSvc.addCRT(memID, rtID);
				
				JSONObject obj = new JSONObject();
				try {
					obj.put("rtID", rtID);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		//取消收藏房型 ，來自myRoomType.jsp的請求
		if("delete_collectRT".equals(action)) {
			List<String>errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			CollectRoomTypeService crtSvc =new CollectRoomTypeService();
			
			//1.取參數
			try{
			String rtID =req.getParameter("rtID");
			
			if(rtID.trim().length()==0||rtID==null) {
				errorMsgs.add("房型編號輸入錯誤");
			}
//			從會員session存取會員ID
				HttpSession session =  req.getSession();
				MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
				String memID = memVO.getMemID();
				System.out.println(memID);
			
			//2.轉交參數給service
				
				crtSvc.deleteCRT(memID, rtID);
				
			//3.回傳myRoomType.jsp 的ajax success回覆
			
				JSONObject obj = new JSONObject();
				try {
					obj.put("rtID", rtID);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		
		
		
	}
	
}
