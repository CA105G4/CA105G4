package com.android.branch.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.branch.model.BranchService;
import com.android.coupon.model.CouponService;
import com.android.couponRecord.model.CouponRecordService;
import com.android.roomType.model.RoomTypeService;
import com.android.tool.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

public class BranchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	Gson gson = new Gson(); 
    	BufferedReader br = req.getReader();
    	StringBuilder jsonIn = new StringBuilder();
    	String tempStr = null;
    	while((tempStr = br.readLine()) != null)
    		jsonIn.append(tempStr);
    	System.out.println("BranchServlet jsonIn = " + jsonIn);      //觀察用
    	
    	System.out.println("test");
			BranchService branchSvc = new BranchService();
			System.out.println(branchSvc);			
			JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);   // 轉成 jsonObject 物件
			System.out.println(jsonObject);
			String action = jsonObject.get("action").getAsString();
			System.out.println(action);
			
    	if("getAllBraName".equals(action)) {
    		String outStr = branchSvc.getAllBraName().toString();
    		System.out.println(outStr);
    		writeText(res, outStr);
    	} else if("getAllBranchInfo".equals(action)) {
    		String outStr = "";
    		writeText(res, outStr);
    	} 
    	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	private void writeText(HttpServletResponse res, String outText) throws IOException {
    	res.setContentType(CONTENT_TYPE);
    	PrintWriter out = res.getWriter();             
    	out.print(outText);
    	out.close();
		System.out.println("output = " + outText);        //觀察用
    	System.out.println("----------------------------------------------------------------------------------");   
	}
}
