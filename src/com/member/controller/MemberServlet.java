package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;


import com.member.model.*;
@MultipartConfig
public class MemberServlet extends HttpServlet {

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
				String str = req.getParameter("memID");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				String memID = null;
				try {
					memID = new String(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMem(memID);
				if (memberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/member/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/member/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	
		
		
		if ("getOne_For_Update_forMem".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String memID = new String(req.getParameter("memID"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMem(memID);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/front-end/member/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				
				throw new ServletException(e);
			}
		}
		if ("getOne_For_Update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				String memID = new String(req.getParameter("memID"));
				
				/***************************2.�}�l�d�߸��****************************************/
				MemberService memSvc = new MemberService();
				MemberVO memberVO = memSvc.getOneMem(memID);
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				req.setAttribute("memberVO", memberVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/back-end/member/update_mem_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				
				throw new ServletException(e);
			}
		}
		
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***************************1接收請求參數 - 輸入格式的錯誤處理**********************/
				String memID = new String(req.getParameter("memID").trim());
				String memName = req.getParameter("memName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memAcc  = req.getParameter("memAcc").trim();
				
				String memPsw  = req.getParameter("memPsw").trim();
				if (memPsw  == null || memPsw .trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}		
				java.sql.Date memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				
				String memEmail  = req.getParameter("memEmail").trim();
				

				String memTel = null;
				String memTel_reg = "^09[0-9]{8}$";
				memTel = req.getParameter("memTel").trim();
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if (!memTel.trim().matches(memTel_reg)) {
					errorMsgs.add("手機號碼請填10位數字 ( 09xxxxxxxx ) ");
				}
				

				
				String memAddr  = req.getParameter("memAddr").trim();
				if (memAddr  == null || memAddr .trim().length() == 0) {
					errorMsgs.add("地址請勿空白ˋ口ˊ");
				}		
				String memSex   = req.getParameter("memSex").trim();
				java.sql.Date memReg  = java.sql.Date.valueOf(req.getParameter("memReg").trim());
				String memSkill  = req.getParameter("memSkill").trim();
				Integer memState = new Integer(req.getParameter("memState").trim());
				
				
				byte[] memPic = null;
				Part part = req.getPart("memPic");
				if(part == null || part.getSubmittedFileName().length() == 0) {
					MemberService picSvc = new MemberService();
					MemberVO rtvo = picSvc.getOneMem(memID);
					memPic = rtvo.getMemPic();				
				}else {
					InputStream in =  part.getInputStream();
					memPic = new byte[in.available()];
					in.read(memPic);
					in.close();
				}
				String memIDcard   = req.getParameter("memIDcard").trim();
				 
				

				
				
				MemberVO memberVO = new MemberVO();
			
				memberVO.setMemState(memState);

				memberVO.setMemName(memName);
				memberVO.setMemAcc(memAcc);
				memberVO.setMemPsw(memPsw);
				memberVO.setMemBirth(memBirth);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemTel(memTel);
				memberVO.setMemAddr(memAddr);
				memberVO.setMemSex(memSex);
				memberVO.setMemSkill(memSkill);
				memberVO.setMemIDcard(memIDcard);
				memberVO.setMemPic(memPic);
				memberVO.setMemReg(memReg);
				memberVO.setMemID(memID);
				
			
				
//				
				
				
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/update_mem_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.updateMem(memID, memName, memAcc, memPsw, memBirth, memEmail, memTel, memAddr, memSex, memSkill, memState, memPic,memReg, memIDcard);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); 
				String url = "/back-end/member/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/member/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update_from_mem".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/***************************1接收請求參數 - 輸入格式的錯誤處理**********************/
				String memID = new String(req.getParameter("memID").trim());
				String memName = req.getParameter("memName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName.trim().matches(enameReg)) { //�H�U�m�ߥ��h(�W)��ܦ�(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String memAcc  = req.getParameter("memAcc").trim();
				
				String memPsw  = req.getParameter("memPsw").trim();
				if (memPsw  == null || memPsw .trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}		
				java.sql.Date memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				
				String memEmail  = req.getParameter("memEmail").trim();
				
				
				String memTel = null;
				String memTel_reg = "^09[0-9]{8}$";
				memTel = req.getParameter("memTel").trim();
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if (!memTel.trim().matches(memTel_reg)) {
					errorMsgs.add("手機號碼請填10位數字 ( 09xxxxxxxx ) ");
				}
				
				
				
				String memAddr  = req.getParameter("memAddr").trim();
				if (memAddr  == null || memAddr .trim().length() == 0) {
					errorMsgs.add("地址請勿空白ˋ口ˊ");
				}		
				String memSex   = req.getParameter("memSex").trim();
				java.sql.Date memReg  = java.sql.Date.valueOf(req.getParameter("memReg").trim());
				String memSkill  = req.getParameter("memSkill").trim();
				Integer memState = new Integer(req.getParameter("memState").trim());
				
				
				byte[] memPic = null;
				Part part = req.getPart("memPic");
				if(part == null || part.getSubmittedFileName().length() == 0) {
					MemberService picSvc = new MemberService();
					MemberVO rtvo = picSvc.getOneMem(memID);
					memPic = rtvo.getMemPic();				
				}else {
					InputStream in =  part.getInputStream();
					memPic = new byte[in.available()];
					in.read(memPic);
					in.close();
				}
				String memIDcard   = req.getParameter("memIDcard").trim();
				
				
				
				
				
				MemberVO memberVO = new MemberVO();
				
				memberVO.setMemState(memState);
				memberVO.setMemName(memName);
				memberVO.setMemAcc(memAcc);
				memberVO.setMemPsw(memPsw);
				memberVO.setMemBirth(memBirth);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemTel(memTel);
				memberVO.setMemAddr(memAddr);
				memberVO.setMemSex(memSex);
				memberVO.setMemSkill(memSkill);
				memberVO.setMemIDcard(memIDcard);
				memberVO.setMemPic(memPic);
				memberVO.setMemReg(memReg);
				memberVO.setMemID(memID);
				
				
				
//				
				
				
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					System.out.println("memberVO:" + memberVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_mem_input.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�ק���*****************************************/
				MemberService memSvc = new MemberService();
				memberVO = memSvc.updateMem(memID, memName, memAcc, memPsw, memBirth, memEmail, memTel, memAddr, memSex, memSkill, memState, memPic,memReg, memIDcard);
				/***************************3.�ק粒��,�ǳ����(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); 
				System.out.println("memberVO:" + memberVO);
				String url = "/front-end/TemplateInner.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/update_mem_input.jsp");
				failureView.forward(req, res);
			}
		}
		


		if ("register".equals(action)) {  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memName  = req.getParameter("memName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName  == null || memName .trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName .trim().matches(enameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}
				
				String memAcc  = req.getParameter("memAcc").trim();
				if (memAcc  == null || memAcc .trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}		
				
				String memPsw  = req.getParameter("memPsw").trim();
				if (memPsw  == null || memPsw .trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}		
				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					memBirth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}
				
				String memEmail  = req.getParameter("memEmail").trim();
				if (memEmail  == null || memEmail .trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}		
				String memTel = null;
				String memTel_reg = "^09[0-9]{8}$";
				memTel = req.getParameter("memTel").trim();
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if (!memTel.trim().matches(memTel_reg)) {
					errorMsgs.add("手機號碼請填10位數字 ( 09xxxxxxxx ) ");
				}
				String memAddr  = req.getParameter("memAddr").trim();
				if (memAddr  == null || memAddr .trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}		
				String memSex   = req.getParameter("memSex").trim();
				if (memSex   == null || memSex  .trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}
				if (memSex   =="" ) {
					errorMsgs.add("性別請勿空白");
				}
				
				String memSkill = req.getParameter("memSkill").trim();
				
				String memIDcard   = req.getParameter("memIDcard").trim();
				if (memIDcard   == null || memIDcard  .trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}		
				String memID =null;
				
				
				byte[] memPic = null;
				Part part = req.getPart("memPic");
//				if(part == null || part.getSubmittedFileName().length() == 0) {
//					MemberService rtSvc = new MemberService();
//					MemberVO rtvo = rtSvc.getOneMem(memID);
//					memPic = rtvo.getMemPic();	
//					
//				}else {
//					InputStream in =  part.getInputStream();
//					memPic = new byte[in.available()];
//					in.read(memPic);
//					in.close();
//				
//				}
				
				
				
				MemberVO memberVO = new MemberVO();
				
				memberVO.setMemName(memName);
				memberVO.setMemAcc(memAcc);
				memberVO.setMemPsw(memPsw);
				memberVO.setMemBirth(memBirth);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemTel(memTel);
				memberVO.setMemAddr(memAddr);
				memberVO.setMemSex(memSex);
				memberVO.setMemSkill(memSkill);
				memberVO.setMemIDcard(memIDcard);
				memberVO.setMemPic(memPic);
				
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); 
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/register.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2..新增完成,準備轉交(***************************************/
				System.out.println(errorMsgs);
				MemberService memSvc = new MemberService();
				memberVO = memSvc.addMem(memName, memAcc, memPsw, memBirth, memEmail, memTel, memAddr, memSex, memSkill, memPic, memIDcard);
				
				/***************************3.�新增完成,準備轉交Send the Success view)***********/
				
				String url = "/front-end/Login.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
				
			}catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/register.jsp");
				failureView.forward(req, res);
				
			}
			
		}
        if ("insert".equals(action)) {  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memName  = req.getParameter("memName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName  == null || memName .trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName .trim().matches(enameReg)) { 
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memAcc  = req.getParameter("memAcc").trim();
				if (memAcc  == null || memAcc .trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}		
				
				String memPsw  = req.getParameter("memPsw").trim();
				if (memPsw  == null || memPsw .trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}		
				java.sql.Date memBirth = null;
				try {
					memBirth = java.sql.Date.valueOf(req.getParameter("memBirth").trim());
				} catch (IllegalArgumentException e) {
					memBirth=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}
				
				String memEmail  = req.getParameter("memEmail").trim();
				if (memEmail  == null || memEmail .trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}		
				String memTel = null;
				String memTel_reg = "^09[0-9]{8}$";
				memTel = req.getParameter("memTel").trim();
				if (memTel == null || memTel.trim().length() == 0) {
					errorMsgs.add("手機號碼請勿空白");
				} else if (!memTel.trim().matches(memTel_reg)) {
					errorMsgs.add("手機號碼請填10位數字 ( 09xxxxxxxx ) ");
				}
				String memAddr  = req.getParameter("memAddr").trim();
				if (memAddr  == null || memAddr .trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}		
				String memSex   = req.getParameter("memSex").trim();
				if (memSex   == null || memSex  .trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}
//				if (memSex !="M"||memSex !="F") {
//					errorMsgs.add("請選擇性別");
//				}
				String memSkill = req.getParameter("memSkill").trim();
					
				String memIDcard   = req.getParameter("memIDcard").trim();
				if (memIDcard   == null || memIDcard  .trim().length() == 0) {
					errorMsgs.add("身分證字號請勿空白");
				}		
				String memID =null;
				
				
				byte[] memPic = null;
				Part part = req.getPart("memPic");
//				if(part == null || part.getSubmittedFileName().length() == 0) {
//					MemberService rtSvc = new MemberService();
//					MemberVO rtvo = rtSvc.getOneMem(memID);
//					memPic = rtvo.getMemPic();	
//					
//				}else {
//					InputStream in =  part.getInputStream();
//					memPic = new byte[in.available()];
//					in.read(memPic);
//					in.close();
//				
//				}
				
				

				MemberVO memberVO = new MemberVO();
				
				memberVO.setMemName(memName);
				memberVO.setMemAcc(memAcc);
				memberVO.setMemPsw(memPsw);
				memberVO.setMemBirth(memBirth);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemTel(memTel);
				memberVO.setMemAddr(memAddr);
				memberVO.setMemSex(memSex);
				memberVO.setMemSkill(memSkill);
				memberVO.setMemIDcard(memIDcard);
				memberVO.setMemPic(memPic);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); 
					
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2..新增完成,準備轉交(***************************************/
				System.out.println(errorMsgs);
				MemberService memSvc = new MemberService();
				memberVO = memSvc.addMem(memName, memAcc, memPsw, memBirth, memEmail, memTel, memAddr, memSex, memSkill, memPic, memIDcard);
				
				/***************************3.�新增完成,準備轉交Send the Success view)***********/
				
				String url = "/back-end/member/listAllMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
			
		}catch (Exception e) {
			errorMsgs.add(e.getMessage());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/member/addMember.jsp");
			failureView.forward(req, res);
			
		}
		
        }
		
			
		
	}
}