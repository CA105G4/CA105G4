package com.message.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.message.model.*;

public class MessageServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("msgid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer msgid = null;
				try {
					msgid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MessageService mesaageService = new MessageService();
				MessageVO messageVO = mesaageService.getOneMessage(msgid);
				if (messageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageVO", messageVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer msgid = new Integer(req.getParameter("msgid"));
				
				/***************************2.開始查詢資料****************************************/
				MessageService mesaageService = new MessageService();
				MessageVO messageVO = mesaageService.getOneMessage(msgid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("messageVO", messageVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/message/update_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer msgid = new Integer(req.getParameter("msgid").trim());
				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				Integer artid = null;
				try {
					artid = new Integer(req.getParameter("artid"));
				} catch (Exception e) {
					errorMsgs.add("文章編號不正確");
				}
				
				
				String msgmemid = req.getParameter("msgmemid").trim();
				if (msgmemid == null || msgmemid.trim().length() == 0) {
					errorMsgs.add("留言會員編號不正確");
				}	
				
				String msgcontent = req.getParameter("msgcontent").trim();
				if (msgcontent == null || msgcontent.trim().length() == 0) {
					errorMsgs.add("留言內容不可空白");
				}	
				
				java.sql.Date msgdate = null;
				try {
					msgdate = java.sql.Date.valueOf(req.getParameter("msgdate").trim());
				} catch (IllegalArgumentException e) {
					msgdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer msgstate = null;
				try {
					msgstate = new Integer(req.getParameter("msgstate"));
				} catch (Exception e) {
					errorMsgs.add("文章編號不正確");
				}
				

				Integer msgid = new Integer(req.getParameter("msgid").trim());
				
				MessageVO messageVO = new MessageVO();
				messageVO.setMsgid(msgid);
				messageVO.setArtid(artid);
				messageVO.setMsgmemid(msgmemid);
				messageVO.setMsgcontent(msgcontent);
				messageVO.setMsgdate(msgdate);
				messageVO.setMsgstate(msgstate);
				


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/update_message_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageService messageService = new MessageService();
				messageVO = messageService.updateMessage(msgid, artid, msgmemid, msgcontent, msgdate, msgstate);
				
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageVO", messageVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/update_message_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				Integer artid = null;
				try {
					artid = new Integer(req.getParameter("artid"));
				} catch (Exception e) {
					errorMsgs.add("文章編號不正確");
				}
				
				String msgmemid = req.getParameter("msgmemid").trim();
				if (msgmemid == null || msgmemid.trim().length() == 0) {
					errorMsgs.add("留言會員編號不正確");
				}	
				
				String msgcontent = req.getParameter("msgcontent").trim();
				if (msgcontent == null || msgcontent.trim().length() == 0) {
					errorMsgs.add("留言內容不可空白");
				}
				
				java.sql.Date msgdate = null;
				try {
					msgdate = java.sql.Date.valueOf(req.getParameter("msgdate").trim());
				} catch (IllegalArgumentException e) {
					msgdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer msgstate = null;
				try {
					msgstate = new Integer(req.getParameter("msgstate"));
				} catch (Exception e) {
					errorMsgs.add("留言狀態不正確");
				}

				
//				Integer msgid = new Integer(req.getParameter("msgid").trim());

				MessageVO messageVO = new MessageVO();
//				messageVO.setMsgid(msgid);
				messageVO.setArtid(artid);
				messageVO.setMsgmemid(msgmemid);
				messageVO.setMsgcontent(msgcontent);
				messageVO.setMsgdate(msgdate);
				messageVO.setMsgstate(msgstate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/addMessage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MessageService messageService = new MessageService();
				messageVO = messageService.addMessage(artid, msgmemid, msgcontent, msgdate, msgstate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/article/viewArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/addMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer msgid = new Integer(req.getParameter("msgid"));
				
				/***************************2.開始刪除資料***************************************/
				MessageService messageService = new MessageService();
				messageService.deleteMessage(msgid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
