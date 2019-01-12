package com.coupon.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponService;

public class CpnImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		
		req.setCharacterEncoding("utf-8");
		String cpnID =req.getParameter("cpnID");
		
		CouponService cpnSvc =new CouponService();
		byte[] pic = cpnSvc.getOneByID(cpnID).getcpnPic();
		
		ServletOutputStream out =res.getOutputStream();
		res.setContentLength(pic.length);
		res.setContentType("image/*");
		
		out.write(pic);
		out.close();
	}

	

}
