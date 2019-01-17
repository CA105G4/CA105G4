package com.article.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.*;

public class OneArticle extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		
		ArticleService articleService = new ArticleService();
		Integer artid = new Integer(req.getParameter("artid").trim());	
		ArticleVO articleVO = articleService.getOneArticle(artid);
        res.setContentType("text/html;charset=UTF-8");
        res.getWriter().print(articleVO.getArtexp());
        
//        req.setAttribute("servletAttribute", 1);
//        RequestDispatcher view = req.getRequestDispatcher("/servlet1.jsp");      
//        view.include(req, res);
//        
	}
}
