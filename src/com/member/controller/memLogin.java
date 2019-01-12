package com.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.*;


public class memLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public memLogin() {
        super();
}
   
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req,res);
	
    	}
    protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
	
//		System.out.println("memAcc"+memAcc);
//		System.out.println("memPsw"+memPsw);
		HttpSession session = req.getSession();
		MemberService service = new MemberService();
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		String memID =null;
		String memAcc = req.getParameter("memAcc");
		if (memAcc == null || (memAcc.trim()).length() == 0) {
			errorMsgs.add("請輸入帳號");
			
			
		}
		String memPsw  = req.getParameter("memPsw");
		if(memPsw==null||(memPsw.trim()).length()==0) {
			errorMsgs.add("密碼不得空白");
			
		}
//		System.out.println(errorMsgs);
		String button = req.getParameter("button");
//		註冊
		if("insert".equals(button)){
			req.setAttribute(memAcc, memAcc);
			req.setAttribute(memPsw, memPsw);
			String url =req.getContextPath()+"/front-end/member/register.jsp";//註冊葉面
			res.sendRedirect(url);
			return;
			
			}
		
		
		
		

		if(errorMsgs.isEmpty()) {
			
		try {
				memID=service.getOneMemByAcc(memAcc);
			
		}catch(RuntimeException re) {
			System.out.println(re.getMessage());
			log(re.getMessage());
		}
		if(memID==null) {
			errorMsgs.add("此帳號不存在");
		}
		else {
			
			if(memPsw==null||(memPsw.trim().length()==0)) {
				errorMsgs.add("密碼不得空白");
			}else {
				MemberVO memberVO = service.getOneMem(memID);
				if(memPsw.equals(memberVO.getMemPsw())){
					session.setAttribute("memberVO", memberVO);
			}else {
				errorMsgs.add("輸入密碼有誤");
				}
			}
		}
		
		}
		if(!errorMsgs.isEmpty()) {
			
			RequestDispatcher error = req.getRequestDispatcher("/front-end/Login.jsp");
			error.forward(req, res);
			
		}else {
			session.setAttribute("memberVO", service.getOneMem(memID));
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
			res.sendRedirect(req.getContextPath()+"/front-end/member/myAccountMyPage.jsp");//要跳轉的頁面
		}
		
		return;
		
		
		
		
    }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    }

    