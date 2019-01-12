package com.member.controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberVO;


public class LoginFliter  implements Filter {
	
	ServletContext context =null;

	public LoginFliter() {
    }
    
	public void init(FilterConfig config){
    	context = config.getServletContext();
    }

	public void destroy() {
	context = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO)session.getAttribute("memberVO");
		if(memberVO==null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath()+"/front-end/Login.jsp");
			return;
		}else {
			chain.doFilter(request, response);
		}
	}
}