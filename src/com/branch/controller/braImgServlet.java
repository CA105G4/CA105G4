package com.branch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.branch.model.BranchService;


public class braImgServlet extends HttpServlet {
  
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String braID =req.getParameter("braID");
			
			BranchService bchSvc = new BranchService();
			byte[]pic = bchSvc .getOneByID(braID).getBraPic();
			
			
			
			
			ServletOutputStream out =res.getOutputStream();
			res.setContentLength(pic.length);
			res.setContentType("image/*");
			
			out.write(pic);
			out.close();
	}

	

}
