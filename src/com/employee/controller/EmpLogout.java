package com.employee.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmpLogout  extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EmpLogout() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		session.removeAttribute("employeeVO");
//		session.invalidate();
		String url =req.getContextPath()+"/back-end/login.jsp";
		res.sendRedirect(url);
		System.out.println(session.getAttribute("employeeVO"));
	}

}