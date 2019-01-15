package com.branch.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.branch.model.BranchService;

@WebServlet("/braVideoServlet")
public class braVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String braID =req.getParameter("braID");
		
		BranchService bchSvc =new BranchService();
		byte[] video =bchSvc.getOneByID(braID).getBraVideo();
		
		ServletOutputStream out =res.getOutputStream();
		res.setContentLength(video.length);
		res.setContentType("video/mp4");
		
		out.write(video);
		out.close();
	}


}
