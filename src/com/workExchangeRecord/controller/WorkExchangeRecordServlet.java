package com.workExchangeRecord.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.workExchangeRecord.model.WorkExchangeRecordService;
import com.workExchangeRecord.model.WorkExchangeRecordVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.orderDetail.model.OrderDetailVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;
import com.workExchange.model.WorkExchangeService;
import com.workExchange.model.WorkExchangeVO;

@WebServlet("/workExchangeRecord.do")
@MultipartConfig
public class WorkExchangeRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		Map<String,String> errMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errMsgs", errMsgs);
		
//		if("getOne_For_Update".equals(action)) {
//			Integer weID = new Integer(req.getParameter("weID"));
//			String memID = req.getParameter("memID");
//			WorkExchangeRecordService werSvc = new WorkExchangeRecordService();
//			WorkExchangeRecordVO workExchangeRecordVO = werSvc.getOneWER(weID, memID);
//			req.setAttribute("workExchangeRecordVO", workExchangeRecordVO);
//			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchangeRecord/updateWorkExchangeRecord.jsp");
//			success.forward(req, res);
//		}
		
		//來自listWorkExchangeRecordByWE的請求
		if("OK".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
			String memID = req.getParameter("memID");
			Integer werState = new Integer(req.getParameter("werState"));
//			System.out.println(weID);
//			System.out.println(memID);
//			System.out.println(werState);
			WorkExchangeRecordService werSvc = new WorkExchangeRecordService();
			WorkExchangeRecordVO workExchangeRecordVO = werSvc.updateByWE(weID, 2);
			workExchangeRecordVO = werSvc.updateWER(weID, memID, werState);
			req.setAttribute("workExchangeRecordVO", workExchangeRecordVO);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchangeRecord/listWER.jsp");
			success.forward(req, res);
		}
		
		//來自addWER的請求
		if("confirm_Apply".equals(action)) {
			try {
				Integer weID = new Integer(req.getParameter("weID"));
				
				String memID = req.getParameter("memID");
				if(memID == null || memID.trim().length() == 0) {
					errMsgs.put("memID", "會員姓名不得為空");
				}
				Integer werState = new Integer(req.getParameter("werState"));
				String orderID = req.getParameter("oderID");
				
				byte[] weApp = null;
				Part part = req.getPart("weApp");
				if(part == null || part.getSubmittedFileName().length() == 0) {
					System.out.println("沒進來");
					errMsgs.put("weApp", "請上傳申請圖片");
				}else {
				InputStream in = part.getInputStream();
				weApp = new byte[in.available()];
				in.read(weApp);
				in.close();
				}
				
				//錯誤返回原頁面
				if (!errMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/workExchangeRecord/addWER.jsp");
					System.out.println("123");
					failureView.forward(req, res);
					return;
				}
				
				WorkExchangeRecordService werSvc = new WorkExchangeRecordService();
				werSvc.addWER(weID, memID, werState, orderID, weApp);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/neighbourhood.jsp"); 
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errMsgs.put("Exception",e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/workExchangeRecord/addWER.jsp");
				System.out.println("test");
				failureView.forward(req, res);
			}	
		}
		
		//來自listWER的請求------------新增打工換宿訂單
		if("add_WEOrder".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
			String newMemID = req.getParameter("memID");
			WorkExchangeService  weSvc = new WorkExchangeService();
			WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
			
			//如果打工需求列表沒有會員，新增訂單
			if((weSvc.getOneWE(weID)).getMemID() == null) {
			//從需求編號取得WE房型，再從房型取得分店別
			String rtID = workExchangeVO.getRtID();
			RoomTypeService rtSvc = new RoomTypeService();
			RoomTypeVO roomTypeVO = rtSvc.getOneRoomType(rtID);
			String braID = roomTypeVO.getBraID();
			OrdersService ordSvc = new OrdersService();
			
			//新增一筆訂單
			OrdersVO ordVO = new OrdersVO();  //(memID, braID, 1, 2, 1, 0, 0, 0);
			ordVO.setMemID(newMemID);
			ordVO.setBraID(braID);
			ordVO.setNumOfRoom(1);
			ordVO.setOrdType(2);
			ordVO.setNumOfGuest(1);
			ordVO.setBond(0);
			ordVO.setPayment(0);
			ordVO.setAmount(0);
			
			
			//同時成立一筆訂單明細
			List<OrderDetailVO> odlist  = new ArrayList<OrderDetailVO>();
			OrderDetailVO odVO = new OrderDetailVO();
			odVO.setRtID(rtID);
			odVO.setCheckIn(workExchangeVO.getWeStart());
			odVO.setCheckOut(workExchangeVO.getWeEnd());
			odVO.setSpecial(0);
			odlist.add(odVO);
			
			//房型及間數
			Map<String, Integer> rtIDandNumMap = new HashMap<String,Integer>();
			rtIDandNumMap.put(rtID, 1);
			
			//新增成為新的一筆訂單轉交到訂單明細
			ordSvc.insertwithOrderDetail(ordVO, odlist, rtIDandNumMap);
			req.setAttribute("rtIDandNumMap", rtIDandNumMap);
			req.setAttribute("memID", newMemID);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/orders/addorders_step2.jsp");
			success.forward(req, res);
			
			//更新會員姓名到打工需求列表
			weSvc.updateMemID(weID, newMemID);
			}
			//更新訂單 
			else {
				String oldMemID = weSvc.getOneWE(weID).getMemID();
				MemberService memSvc = new MemberService();
				MemberVO memVO = memSvc.getOneMem(oldMemID);
//				oldMemID = memVO.getMemID();
				System.out.println(oldMemID);
				OrdersService ordSvc = new OrdersService();
				String ordID = ordSvc.findNewOrderID(oldMemID);
				OrdersVO ordVO = ordSvc.getOneOrders(ordID);
				String braID = ordVO.getBraID();
				Integer numOfRoom = ordVO.getNumOfRoom();
				Integer ordType = ordVO.getOrdType();
				Integer numOfGuest = ordVO.getNumOfGuest();
				Integer amount = ordVO.getAmount();
				Integer bond = ordVO.getBond();
				Integer payment = ordVO.getPayment();
				Integer ordState = ordVO.getOrdState();
				ordSvc.updateOrders(ordID, newMemID, braID, numOfRoom, ordType, numOfGuest, amount, bond, payment, ordState);
				System.out.println("123456");
				
				req.setAttribute("memID", newMemID);
//				req.setAttribute("nwOrdVO", nwOrdVO);
				RequestDispatcher success = req.getRequestDispatcher("/back-end/orders/addorders_step2.jsp");
				success.forward(req, res);
				System.out.println("123777776");
				
				//更新會員姓名到打工需求列表
				weSvc.updateMemID(weID, newMemID);
				System.out.println("8888888888888888888888888888888877776");
			}
		}		
		
	}

}
