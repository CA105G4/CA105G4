package com.report.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.report.model.*;

public class ReportServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
				String str = req.getParameter("repid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer repid = null;
				try {
					repid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉文章編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ReportService reportSvc = new ReportService();
				ReportVO reportVO = reportSvc.getOneReport(repid);
				if (reportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportVO", reportVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/report/listOneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/select_page.jsp");
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
				Integer repid = new Integer(req.getParameter("repid"));
				
				/***************************2.開始查詢資料****************************************/
			    ReportService reportService = new ReportService();
				ReportVO reportVO = reportService.getOneReport(repid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("reportVO", reportVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/report/update_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
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
//				Integer repid = new Integer(req.getParameter("repid").trim());
				
				Integer artid = null;
				try {
					artid = new Integer(req.getParameter("artid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("被檢舉文章編號有誤.");
				}
				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				String repreason = req.getParameter("repreason").trim();
//				if (artreason == null || artreason.trim().length() == 0) {
//					errorMsgs.add("檢舉理由空白");
//				}	
				
				String repmemid = req.getParameter("repmemid").trim();
				if (repmemid == null || repmemid.trim().length() == 0) {
					errorMsgs.add("檢舉會員編號空白");
				}	
				
				java.sql.Date repdate = null;
				try {
					repdate = java.sql.Date.valueOf(req.getParameter("repdate").trim());
				} catch (IllegalArgumentException e) {
					repdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer repstate = null;
				try {
					repstate = new Integer(req.getParameter("repstate").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("檢舉文章狀態不為空白.");
				}

				Integer repid = new Integer(req.getParameter("repid").trim());

				ReportVO reportVO = new ReportVO();
				reportVO.setRepid(repid);
				reportVO.setArtid(artid);
				reportVO.setRepreason(repreason);
				reportVO.setRepmemid(repmemid);
				reportVO.setRepstate(repstate);
				reportVO.setRepdate(repdate);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/update_report_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReportService reportService = new ReportService();
				reportVO = reportService.updateReport(repid, artid, repreason, repmemid, repdate, repstate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportVO", reportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/report/listOneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/update_report_input.jsp");
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
//				Integer repid = new Integer(req.getParameter("repid").trim());
				
				Integer artid = null;
				try {
					artid = new Integer(req.getParameter("artid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("被檢舉文章編號有誤.");
				}
				
//				String ename = req.getParameter("ename");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (ename == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				String repreason = req.getParameter("repreason").trim();
//				if (artreason == null || artreason.trim().length() == 0) {
//					errorMsgs.add("檢舉理由空白");
//				}	
				
				String repmemid = req.getParameter("repmemid").trim();
				if (repmemid == null || repmemid.trim().length() == 0) {
					errorMsgs.add("檢舉會員編號空白");
				}	
				
				java.sql.Date repdate = null;
				try {
					repdate = java.sql.Date.valueOf(req.getParameter("repdate").trim());
				} catch (IllegalArgumentException e) {
					repdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				
				Integer repstate = null;
				try {
					repstate = new Integer(req.getParameter("repstate").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("檢舉文章狀態不為空白.");
				}

				Integer repid = new Integer(req.getParameter("repid").trim());

				ReportVO reportVO = new ReportVO();
				reportVO.setRepid(repid);
				reportVO.setArtid(artid);
				reportVO.setRepreason(repreason);
				reportVO.setRepmemid(repmemid);
				reportVO.setRepstate(repstate);
				reportVO.setRepdate(repdate);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/listAllReport.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ReportService reportService = new ReportService();
				reportVO = reportService.updateArticleStatus(repid, artid, repreason, repmemid, repdate, repstate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("reportVO", reportVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
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
					artid = new Integer(req.getParameter("artid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("文章編號有誤.");
				}
				
				String repreason = req.getParameter("repreason").trim();
				
				String repmemid = req.getParameter("repmemid").trim();
				if (repmemid == null || repmemid.trim().length() == 0) {
					errorMsgs.add("檢舉會員編號空白");
				}	
				
				
				java.sql.Date repdate = java.sql.Date.valueOf(req.getParameter("repdate").trim());
//				try {
//					repdate = java.sql.Date.valueOf(req.getParameter("repdate").trim());
//				} catch (IllegalArgumentException e) {
//					repdate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}
				
				Integer repstate = null;
				try {
					repstate = new Integer(req.getParameter("repstate").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("檢舉文章狀態不為空白.");
				}
				

				ReportVO reportVO = new ReportVO();
				reportVO.setArtid(artid);
				reportVO.setRepreason(repreason);
				reportVO.setRepmemid(repmemid);
				reportVO.setRepstate(repstate);
				reportVO.setRepdate(repdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/addReport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ReportService reportService = new ReportService();
				reportService.addReport(artid, repreason, repmemid, repdate, repstate);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
//				String url = "/back-end/report/listAllReport.jsp";
				String url = "/front-end/article/viewArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/addReport.jsp");
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
				Integer repid = new Integer(req.getParameter("repid"));
				
				/***************************2.開始刪除資料***************************************/
				ReportService reportService = new ReportService();
				reportService.deleteReport(repid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
