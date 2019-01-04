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
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("mrid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言檢舉編號編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer mrid = null;
				try {
					mrid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("留言檢舉編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MessageReportService messageReportService = new MessageReportService();
				MessageReportVO messageReportVO = messageReportService.getOneMessageReport(mrid);
				if (messageReportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/messageReport/listOneMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/select_page.jsp");
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
				Integer mrid = new Integer(req.getParameter("mrid"));
				
				/***************************2.開始查詢資料****************************************/
				MessageReportService messageReportService = new MessageReportService();
				MessageReportVO messageReportVO = messageReportService.getOneMessageReport(mrid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("messageReportVO", messageReportVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/messageReport/update_messageReport_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/listAllMessageRport.jsp");
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
//				Integer mrid = new Integer(req.getParameter("mrid").trim());
				
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
				
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageReportService messageReportService = new MessageReportService();
				messageReportVO = messageReportService.updateMessageReport(mrid, artid, msgid, mrreason, mrstate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/messageReport/listOneMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("status_Update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				Integer mrid = new Integer(req.getParameter("mrid").trim());
				
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
				
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MessageReportService messageReportService = new MessageReportService();
				messageReportVO = messageReportService.updateMessageStatus(mrid, artid, msgid, mrreason, mrstate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("messageReportVO", messageReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/messageReport/listAllMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/update_messageReport_input.jsp");
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("messageReportVO", messageReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/messageReport/addMessageReport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MessageReportService messageReportService = new MessageReportService();
				messageReportVO = messageReportService.addMessageReport(artid, msgid, mrreason, mrstate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/messageReport/listAllMessageReport.jsp";
				String url = "/front-end/article/viewArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/messageReport/addMessageReport.jsp");
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
				Integer mrid = new Integer(req.getParameter("mrid"));
				
				/***************************2.開始刪除資料***************************************/
				MessageReportService messageReportService = new MessageReportService();
				messageReportService.deleteMessageReport(mrid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/messageReport/listAllMessageReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
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
