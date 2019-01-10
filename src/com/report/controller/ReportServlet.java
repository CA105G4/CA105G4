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
		
		
		if ("getOne_For_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("repid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉文章編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer repid = null;
				try {
					repid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉文章編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ReportService reportSvc = new ReportService();
				ReportVO reportVO = reportSvc.getOneReport(repid);
				if (reportVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("reportVO", reportVO); 
				String url = "/back-end/report/listOneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer repid = new Integer(req.getParameter("repid"));
				
			    ReportService reportService = new ReportService();
				ReportVO reportVO = reportService.getOneReport(repid);
								
				req.setAttribute("reportVO", reportVO);         
				String url = "/back-end/report/update_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				
				Integer artid = null;
				try {
					artid = new Integer(req.getParameter("artid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("被檢舉文章編號有誤.");
				}
				
				
				String repreason = req.getParameter("repreason").trim();
				
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
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/update_report_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				ReportService reportService = new ReportService();
				reportVO = reportService.updateReport(repid, artid, repreason, repmemid, repdate, repstate);
				
				req.setAttribute("reportVO", reportVO); 
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/update_report_input.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("status_Update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				
				Integer artid = null;
				try {
					artid = new Integer(req.getParameter("artid").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("被檢舉文章編號有誤.");
				}
				
				
				String repreason = req.getParameter("repreason").trim();
				
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
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/listAllReport.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				ReportService reportService = new ReportService();
				reportVO = reportService.updateArticleStatus(repid, artid, repreason, repmemid, repdate, repstate);
				
				req.setAttribute("reportVO", reportVO); 
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
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

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("reportVO", reportVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/addReport.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ReportService reportService = new ReportService();
				reportService.addReport(artid, repreason, repmemid, repdate, repstate);
				
				String url = "/front-end/article/viewArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/addReport.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				Integer repid = new Integer(req.getParameter("repid"));
				
				ReportService reportService = new ReportService();
				reportService.deleteReport(repid);
				
				String url = "/back-end/report/listAllReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/report/listAllReport.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
