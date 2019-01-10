package com.employee.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.*;



public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpLogin() {
        super();
}
   
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	
    	}
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
	
//		System.out.println("empAcc"+empAcc);
//		System.out.println("empPsw"+empPsw);
		HttpSession session = req.getSession();
		EmployeeService service = new EmployeeService();
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String empID =null;
		String empAcc = req.getParameter("empAcc");
		if (empAcc == null || (empAcc.trim()).length() == 0) {
			errorMsgs.add("請輸入帳號");
			
			
		}
	
		String empPsw  = req.getParameter("empPsw");
		if(empPsw==null||(empPsw.trim()).length()==0) {
			errorMsgs.add("密碼不得空白");
			
		}
//		System.out.println(errorMsgs);
		String button = req.getParameter("button");
//		註冊
//		if("register".equals(button)){
//			req.setAttribute(empAcc, empAcc);
//			req.setAttribute(empPsw, empPsw);
//			String url ="Registeremp_page.jsp";
//			res.sendRedirect(url);
//			return;
//			
//			}
	
		
		
		

		if(errorMsgs.isEmpty()) {
			
		try {
				empID=service.getOneEmpByAcc(empAcc);
			
		}catch(RuntimeException re) {
			System.out.println(re.getMessage());
			log(re.getMessage());
		}
		if(empID==null) {
			errorMsgs.add("此員工不存在");
		}
		else {
			
			if(empPsw==null||(empPsw.trim().length()==0)) {
				errorMsgs.add("密碼不得空白");
			}else {
				EmployeeVO employeeVO = service.getOneEmp(empID);
				if(empPsw.equals(employeeVO.getEmpPsw())){
					session.setAttribute("employeeVO", employeeVO);
			}else {
				errorMsgs.add("輸入密碼有誤");
				}
			}
		}
		
		}
		if(!errorMsgs.isEmpty()) {
			
			RequestDispatcher error = req.getRequestDispatcher("/back-end/login.jsp");
			error.forward(req, res);
			
		}else {
			session.setAttribute("employeeVO", service.getOneEmp(empID));
			try{
				String location = (String) session.getAttribute("location");
				if(location!=null) {
					session.removeAttribute("location");
					res.sendRedirect(location);
					return;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			res.sendRedirect(req.getContextPath()+"/back-end/room/roomState.jsp");//要跳轉到的地方
		}
		
		return;
		
		
		
		
    }
	
  }

    