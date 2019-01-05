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
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("msgid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言文章編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer msgid = null;
				try {
					msgid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MessageService mesaageService = new MessageService();
				MessageVO messageVO = mesaageService.getOneMessage(msgid);
				if (messageVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("messageVO", messageVO); 
				String url = "/back-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

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
				Integer msgid = new Integer(req.getParameter("msgid"));
				
				MessageService mesaageService = new MessageService();
				MessageVO messageVO = mesaageService.getOneMessage(msgid);
								
				req.setAttribute("messageVO", messageVO);         
				String url = "/back-end/message/update_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
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
				


				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/update_message_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				MessageService messageService = new MessageService();
				messageVO = messageService.updateMessage(msgid, artid, msgmemid, msgcontent, msgdate, msgstate);
				
				
				req.setAttribute("messageVO", messageVO); 
				String url = "/back-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/update_message_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
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

				

				MessageVO messageVO = new MessageVO();
				messageVO.setArtid(artid);
				messageVO.setMsgmemid(msgmemid);
				messageVO.setMsgcontent(msgcontent);
				messageVO.setMsgdate(msgdate);
				messageVO.setMsgstate(msgstate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageVO", messageVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/addMessage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MessageService messageService = new MessageService();
				messageVO = messageService.addMessage(artid, msgmemid, msgcontent, msgdate, msgstate);
				
				String url = "/front-end/article/viewArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/addMessage.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				Integer msgid = new Integer(req.getParameter("msgid"));
				
				MessageService messageService = new MessageService();
				messageService.deleteMessage(msgid);
				
				String url = "/back-end/message/listAllMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/message/listAllMessage.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
