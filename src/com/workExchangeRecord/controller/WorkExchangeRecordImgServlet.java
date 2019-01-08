package com.workExchangeRecord.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.workExchange.model.WorkExchangeService;
import com.workExchangeRecord.model.WorkExchangeRecordService;

public class WorkExchangeRecordImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	doPost(req, res);
	}
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Integer weID  = new Integer(req.getParameter("weID"));
		String memID = req.getParameter("memID");
//		System.out.println(weID);
//		System.out.println(memID);
		WorkExchangeRecordService werSvc = new WorkExchangeRecordService();
		byte[] pic = werSvc.getOneWER(weID,memID).getWeApp();
//		System.out.println(pic == null);
		
		ServletOutputStream out= res.getOutputStream();
		res.setContentLength(pic.length);
		res.setContentType("images/*");
		out.write(pic);
		out.close();
    }
}
