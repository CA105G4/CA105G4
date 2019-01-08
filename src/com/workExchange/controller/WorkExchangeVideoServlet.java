package com.workExchange.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.workExchange.model.WorkExchangeService;

public class WorkExchangeVideoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req, res);
	}
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer weID  = new Integer(req.getParameter("weID"));
		WorkExchangeService weSvc = new WorkExchangeService();
		byte[] video = weSvc.getOneWE(weID).getWeVideo();
		
		
		ServletOutputStream out= res.getOutputStream();
		res.setContentLength(video.length);
		res.setContentType("video/*");
		out.write(video);
		out.close();
    }
}
