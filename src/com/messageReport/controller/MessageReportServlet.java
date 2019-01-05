package com.messageReport.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import com.messageReport.model.*;


public class MessageReportServlet extends HttpServlet {
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
				String str = req.getParameter("mrid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言檢舉編號編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer mrid = null;
				try {
					mrid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("留言檢舉編號不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MessageReportService messageReportService = new MessageReportService();
				MessageReportVO messageReportVO = messageReportService.getOneMessageReport(mrid);
				if (messageReportVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("messageReportVO", messageReportVO); 
				String url = "/back-end/messageReport/listOneMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer mrid = new Integer(req.getParameter("mrid"));
				
				MessageReportService messageReportService = new MessageReportService();
				MessageReportVO messageReportVO = messageReportService.getOneMessageReport(mrid);
								
				req.setAttribute("messageReportVO", messageReportVO);         
				String url = "/back-end/messageReport/update_messageReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/listAllMessageRport.jsp");
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
				
				Integer msgid = null;
				try {
					msgid = new Integer(req.getParameter("msgid"));
				} catch (Exception e) {
					errorMsgs.add("留言編號不正確");
				}
				
				String mrreason = req.getParameter("mrreason").trim();
				if (mrreason == null || mrreason.trim().length() == 0) {
					errorMsgs.add("檢舉原因空白");
				}	
				
				Integer mrstate = null;
				try {
					mrstate = new Integer(req.getParameter("mrstate"));
				} catch (Exception e) {
					errorMsgs.add("留言檢舉狀態不正確");
				}

			

				Integer mrid = new Integer(req.getParameter("mrid").trim());
				
				MessageReportVO messageReportVO = new MessageReportVO();
				messageReportVO.setMrid(mrid);
				messageReportVO.setArtid(artid);
				messageReportVO.setMsgid(msgid);
				messageReportVO.setMrreason(mrreason);
				messageReportVO.setMrstate(mrstate);
				
			

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				MessageReportService messageReportService = new MessageReportService();
				messageReportVO = messageReportService.updateMessageReport(mrid, artid, msgid, mrreason, mrstate);
				
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/messageReport/listOneMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("status_Update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				Integer artid = null;
				try {
					artid = new Integer(req.getParameter("artid"));
				} catch (Exception e) {
					errorMsgs.add("文章編號不正確");
				}
				
				Integer msgid = null;
				try {
					msgid = new Integer(req.getParameter("msgid"));
				} catch (Exception e) {
					errorMsgs.add("留言編號不正確");
				}
				
				String mrreason = req.getParameter("mrreason").trim();
				if (mrreason == null || mrreason.trim().length() == 0) {
					errorMsgs.add("檢舉原因空白");
				}	
				
				Integer mrstate = null;
				try {
					mrstate = new Integer(req.getParameter("mrstate"));
				} catch (Exception e) {
					errorMsgs.add("留言檢舉狀態不正確");
				}

			

				Integer mrid = new Integer(req.getParameter("mrid").trim());
				
				MessageReportVO messageReportVO = new MessageReportVO();
				messageReportVO.setMrid(mrid);
				messageReportVO.setArtid(artid);
				messageReportVO.setMsgid(msgid);
				messageReportVO.setMrreason(mrreason);
				messageReportVO.setMrstate(mrstate);
				
			

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				MessageReportService messageReportService = new MessageReportService();
				messageReportVO = messageReportService.updateMessageStatus(mrid, artid, msgid, mrreason, mrstate);
				
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/messageReport/listAllMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
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
				
				Integer msgid = null;
				try {
					msgid = new Integer(req.getParameter("msgid"));
				} catch (Exception e) {
					errorMsgs.add("留言編號不正確");
				}
				
				String mrreason = req.getParameter("mrreason").trim();
				if (mrreason == null || mrreason.trim().length() == 0) {
					errorMsgs.add("檢舉原因空白");
				}	
				
				Integer mrstate = null;
				try {
					mrstate = new Integer(req.getParameter("mrstate"));
				} catch (Exception e) {
					errorMsgs.add("留言檢舉狀態不正確");
				}

				

				MessageReportVO messageReportVO = new MessageReportVO();
				messageReportVO.setArtid(artid);
				messageReportVO.setMsgid(msgid);
				messageReportVO.setMrreason(mrreason);
				messageReportVO.setMrstate(mrstate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/addMessageReport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				MessageReportService messageReportService = new MessageReportService();
				messageReportVO = messageReportService.addMessageReport(artid, msgid, mrreason, mrstate);
				
				String url = "/front-end/article/viewArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/addMessageReport.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				Integer mrid = new Integer(req.getParameter("mrid"));
				
				MessageReportService messageReportService = new MessageReportService();
				messageReportService.deleteMessageReport(mrid);
				
				String url = "/back-end/messageReport/listAllMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/listAllMessageReport.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
