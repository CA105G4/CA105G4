package com.android.employee.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.employee.model.EmployeeService;
import com.android.employee.model.EmployeeVO;
import com.android.member.model.MemberService;
import com.android.member.model.MemberVO;
import com.android.tool.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class EmployeeServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	Gson gson = new Gson();                      // 日期格式設定
    	BufferedReader br = req.getReader();
    	StringBuilder jsonIn = new StringBuilder();
    	String tempStr = null;
    	while((tempStr = br.readLine()) != null)
    		jsonIn.append(tempStr);
    	System.out.println("EmployeeServlet jsonIn = " + jsonIn);      //觀察用
    	
    	EmployeeService empSvc = new EmployeeService();
    	JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);   // 轉成 jsonObject 物件
    	String action = jsonObject.get("action").getAsString();
    	
    	if("getEmpInfo".equals(action)) {                 //判斷是否為會員 => 用帳號密碼搜尋是否能找會員資料
    		String account = jsonObject.get("account").getAsString();
    		String password = jsonObject.get("password").getAsString();
    		EmployeeVO empVO = empSvc.getEmpInfo(account, password);
    		String outStr = gson.toJson(empVO);
    		writeText(res, outStr);                          //有 回傳會員編號; 無 回傳nothing => 送出的結果
    		System.out.println("------------------------------------------------------------------------------");
    	} else if("getEmpBraName".equals(action)) {
    		String empId = jsonObject.get("empId").getAsString();
    		String outStr = empSvc.getEmpBraName(empId);
    		writeText(res, outStr);
    	}
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
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


