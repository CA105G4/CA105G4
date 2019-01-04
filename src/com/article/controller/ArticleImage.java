package com.article.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.article.model.*;

public class ArticleImage extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		ArticleService articleService = new ArticleService();
		Integer artid = new Integer(req.getParameter("artid"));		
		ArticleVO articleVO = articleService.getOneArticle(artid);
        res.setContentType("image/*");
        res.getOutputStream().write(articleVO.getArtpic());
		
	}
}
