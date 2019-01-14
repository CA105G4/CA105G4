package com.authorityRecord.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;


import com.authorityRecord.model.*;
import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;


public class AuthorityRecordServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("empID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				String empID = null;
				try {
					empID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				AuthorityRecordService authSvc = new AuthorityRecordService();
				AuthorityRecordVO authorityRecordVO = authSvc.getOneauth(empID);
				if (authorityRecordVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("authorityRecordVO", authorityRecordVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/authorityRecord/listOneAuthorityRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String empID = new String(req.getParameter("empID"));
				System.out.println(empID);
				if (empID == null || empID.trim().length() == 0) {
					errorMsgs.add("員工編號: 請勿空白");
					}
				
				
				
				if (!errorMsgs.isEmpty()) {
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
					failureView.forward(req, res);
					return; 
				}
				/***************************2.�}�l�d�߸��****************************************/
				AuthorityRecordService authSvc = new AuthorityRecordService();
				System.out.println("test");
				AuthorityRecordVO authorityRecordVO = authSvc.getOneEmp(empID);
				System.out.println(authorityRecordVO == null);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("authorityRecordVO", authorityRecordVO); 
				String url = "/back-end/authorityRecord/update_auth_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
				String empID = req.getParameter("empID");
				
				String[] authID = req.getParameterValues("authID");
				Integer [] authID1 = new Integer [authID.length];
				AuthorityRecordVO authorityRecordVO = new AuthorityRecordVO();
				AuthorityRecordService  authSvc = new AuthorityRecordService();
				for(int i = 0; i < authID.length; i++) {
					authID1[i] = Integer.parseInt(authID[i]);
					authorityRecordVO.setAuthID(authID1[i]);
					authorityRecordVO.setEmpID(empID);
					authorityRecordVO = authSvc.addAuth(authID1[i], empID);
				}
				
				
				
				

				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("authorityRecordVO", authorityRecordVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/addAuthorityRecord.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
//				AuthorityRecordService  authSvc = new AuthorityRecordService();
//				authorityRecordVO = authSvc.addAuth(authID, empID);
				
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				String url = "/back-end/authorityRecord/listAllAuthorityRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/authorityRecord/addAuthorityRecord.jsp");
				failureView.forward(req, res);
			}
		}
        if ("update".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
        	
        	List<String> errorMsgs = new LinkedList<String>();
        	// Store this set in the request scope, in case we need to
        	// send the ErrorPage view.
        	req.setAttribute("errorMsgs", errorMsgs);
        	
        	try {
        		/***********************1.�����ШD�Ѽ� - ��J�榡�����~�B�z*************************/
        		String empID = req.getParameter("empID");
        		if(empID== null ||empID.trim().length() == 0) {
        			errorMsgs.add("員工ID請勿空白");
        		}
        		
        		String[] authID = req.getParameterValues("authID");
        		
        	
				Integer [] authID1 = new Integer [authID.length];
				AuthorityRecordVO authorityRecordVO = new AuthorityRecordVO();
				AuthorityRecordService  authSvc = new AuthorityRecordService();
				authSvc.deleteEmp(empID);

				for(int i = 0; i < authID.length; i++) {
					authID1[i] = Integer.parseInt(authID[i]);
					authorityRecordVO.setAuthID(authID1[i]);
					authorityRecordVO.setEmpID(empID);
					authorityRecordVO = authSvc.addAuth(authID1[i], empID);
				}
        		
        	
        		
        		
        		
        		
        		// Send the use back to the form, if there were errors
        		if (!errorMsgs.isEmpty()) {
        			req.setAttribute("authorityRecordVO", authorityRecordVO); 
        			RequestDispatcher failureView = req
        					.getRequestDispatcher("/back-end/authorityRecord/update_auth_input.jsp");
        			failureView.forward(req, res);
        			return;
        		}
//        		
//        		/***************************2.�}�l�s�W���***************************************/
        	
				
				//        		AuthorityRecordService  authSvc = new AuthorityRecordService();
        		
        		
        		
        		/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
        		String url = "/back-end/authorityRecord/listAllAuthorityRecord.jsp";
        		RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
        		successView.forward(req, res);				
        		
        		/***************************��L�i�઺���~�B�z**********************************/
        	} catch (Exception e) {
        		errorMsgs.add(e.getMessage());
        		RequestDispatcher failureView = req
        				.getRequestDispatcher("/back-end/authorityRecord/update.jsp");
        		failureView.forward(req, res);
        	}
        }
        if ("listAuths_ByEmpID_A".equals(action) ) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				String str = req.getParameter("empID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				String empID = null;
				try {
					empID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AuthorityRecordService empIDSvc = new AuthorityRecordService();
				Set<AuthorityRecordVO> set = empIDSvc.getAuthIDByEmpID(empID);
				AuthorityRecordService authSvc = new AuthorityRecordService();
				AuthorityRecordVO authorityRecordVO = authSvc.getOneauth(empID);
				if (authorityRecordVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/authorityRecord/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listEmps_ByAuthID", set);    // ��Ʈw���X��set����,�s�Jrequest

				String url = null;
				if ("listAuths_ByEmpID_A".equals(action))
					url = "/back-end/authorityRecord/listOneAuthorityRecord.jsp";        // ���\��� dept/listEmps_ByDeptno.jsp
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
       
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				String empID = new String(req.getParameter("empID"));
				
				/***************************2.�}�l�R�����***************************************/
				AuthorityRecordService authSvc = new AuthorityRecordService();
				
				authSvc.deleteEmp(empID);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/
				String url = "/back-end/authorityRecord/listAllAuthorityRecord.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/authorityRecord/listAllAuthorityRecord.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
