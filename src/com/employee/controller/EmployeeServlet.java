package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.employee.model.*;
import com.member.model.MemberService;
import com.member.model.MemberVO;
@MultipartConfig
public class EmployeeServlet extends HttpServlet {

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
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = req.getParameter("empID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
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
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empID);
				if (employeeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String empID = new String(req.getParameter("empID"));
				
				/***************************2.�}�l�d�߸��****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO employeeVO = empSvc.getOneEmp(empID);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/employee/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				
				throw new ServletException(e);
			}
		}
		
		
		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1接收請求參數 - 輸入格式的錯誤處理**********************/
				String empID = new String(req.getParameter("empID").trim());
		
				String empName = req.getParameter("empName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String braID = req.getParameter("braID").trim();
				if (braID == null || braID.trim().length() == 0) {
					errorMsgs.add("分店編號請勿空白");
				}		
					
				String empJob = req.getParameter("empJob").trim();
				if (empJob == null || empJob.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}		
				String empTel = req.getParameter("empTel").trim();
				if (empTel == null || empTel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}		
				String empAcc = req.getParameter("empAcc").trim();
				if (empAcc == null || empAcc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}		
				String empPsw = req.getParameter("empPsw").trim();
				if (empPsw == null || empPsw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}		
//				Integer empState = req.getParameter("empState").trim();
//				if (empState == null || empState.trim().length() == 0) {
//					errorMsgs.add("員工狀態請勿空白");
				
				Integer empState = new Integer(req.getParameter("empState").trim());
				
				byte[] empPic = null;
				Part part = req.getPart("empPic");
				if(part == null || part.getSubmittedFileName().length() == 0) {
					EmployeeService rtSvc = new EmployeeService();
					EmployeeVO rtvo = rtSvc.getOneEmp(empID);
					empPic = rtvo.getEmpPic();	
					
				}else {
					InputStream in =  part.getInputStream();
					empPic = new byte[in.available()];
					in.read(empPic);
					in.close();
				
				}
				

				

				EmployeeVO employeeVO = new EmployeeVO();
				employeeVO.setEmpID(empID);
				employeeVO.setBraID(braID);
				employeeVO.setEmpName(empName);
				employeeVO.setEmpState(empState);
				employeeVO.setEmpJob(empJob);
				employeeVO.setEmpTel(empTel);
				employeeVO.setEmpAcc(empAcc);
				employeeVO.setEmpPsw(empPsw);
				employeeVO.setEmpPic(empPic);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/update_emp_input.jsp");
					failureView.forward(req, res);
					return; //�{�����_
				}
				
				/***************************2.�}�l�ק���*****************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.updateEmp(braID, empName, empJob, empTel, empState, empAcc, empPsw, empID);
				
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("employeeVO", employeeVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/employee/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/employee/update_emp_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

//			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String empName = req.getParameter("empName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if(!empName.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String braID = req.getParameter("braID").trim();
				if (braID == null || braID.trim().length() == 0) {
					errorMsgs.add("分店請勿空白");
				}		
				
				String empJob = req.getParameter("empJob").trim();
				if (empJob == null || empJob.trim().length() == 0) {
					errorMsgs.add("職位請勿空白");
				}		
				String empTel = req.getParameter("empTel").trim();
				if (empTel == null || empTel.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}		
				String empAcc = req.getParameter("empAcc").trim();
				if (empAcc == null || empAcc.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}		
				String empPsw = req.getParameter("empPsw").trim();
				if (empPsw == null || empPsw.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}		
				
				String empID =null;
				
				byte[] empPic = null;
				Part part = req.getPart("empPic");
				if(part == null || part.getSubmittedFileName().length() == 0) {
					EmployeeService rtSvc = new EmployeeService();
					EmployeeVO rtvo = rtSvc.getOneEmp(empID);
					empPic = rtvo.getEmpPic();	
					
				}else {
					InputStream in =  part.getInputStream();
					empPic = new byte[in.available()];
					in.read(empPic);
					in.close();
				
				}

				EmployeeVO employeeVO = new EmployeeVO();
				
				employeeVO.setEmpName(empName);
				employeeVO.setBraID(braID);
				employeeVO.setEmpJob(empJob);
				employeeVO.setEmpTel(empTel);
				employeeVO.setEmpAcc(empAcc);
				employeeVO.setEmpPsw(empPsw);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("employeeVO", employeeVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/employee/addEmployee.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���***************************************/
				EmployeeService empSvc = new EmployeeService();
				employeeVO = empSvc.addEmp(braID, empName, empJob, empTel, empPic, empAcc, empPsw);
				
				/***************************3.�s�W����,�ǳ����(Send the Success view)***********/
				
				String url = "/back-end/employee/listAllEmp_1.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************��L�i�઺���~�B�z**********************************/
			
		}
		
       
		
			
		
	}
}
