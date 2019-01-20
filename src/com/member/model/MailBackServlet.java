package com.member.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailBackServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException {
    String memAcc = req.getParameter("memAcc");
    if (memAcc == null) {
      req.getRequestDispatcher("/front-end/test.jsp").forward(req, res);
      return;
    }

    String memPsw = (String) req.getSession().getAttribute(memAcc);

    if (memPsw == null || memPsw.equals("")) {
      req.getRequestDispatcher("/front-end/test.jsp").forward(req, res);
      return;
    }
    MemberVO memberVO = new MemberVO();
//    String memID = req.getParameter("memID");	
    MemberService memSvc = new MemberService();
	String memID = memSvc.getOneMemByAcc(memAcc);
	
	Integer memState = 1;
    
	memberVO.setMemState(memState);
	memberVO.setMemID(memID);

//	System.out.println(memAcc);
//    System.out.println(memID);
//    System.out.println(memState);
    memberVO = memSvc.updateMemStatus(1, memID);
    req.setAttribute("memberVO", memberVO); 
   
    

    req.setAttribute("memPsw", memPsw);
    req.getRequestDispatcher("/front-end/Certification_suc_Login.jsp").forward(req, res);
  }
  	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse res)
  throws ServletException, IOException {
	  
    doGet(req, res);
  }
}
