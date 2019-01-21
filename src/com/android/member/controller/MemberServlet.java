package com.android.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.android.member.model.MemberService;
import com.android.member.model.MemberVO;
import com.android.tool.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import oracle.jdbc.proxy.annotation.SetDelegate;

public class MemberServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
//    	Gson gson = new Gson();   // 日期格式設定
    	Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create();   // 日期格式設定
    	BufferedReader br = req.getReader();
    	StringBuilder jsonIn = new StringBuilder();
    	String tempStr = null;
    	while((tempStr = br.readLine()) != null)
    		jsonIn.append(tempStr);
    	System.out.println("jsonIn = " + jsonIn);      //觀察用
    	
    	MemberService memberSvc = new MemberService();
    	JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);   // 轉成 jsonObject 物件
    	String action = jsonObject.get("action").getAsString();
//    	System.out.println(action);
    	
    	if("isMember".equals(action)) {                 //判斷是否為會員 => 用帳號密碼搜尋是否能找會員資料
    		String account = jsonObject.get("account").getAsString();
    		String password = jsonObject.get("password").getAsString();
    		String outputStr = memberSvc.isMember(account, password);
    		writeText(res, outputStr);                          //有 回傳會員編號; 無 回傳nothing => 送出的結果
    		
    	} else if("getMemberInfoByMemId".equals(action)) {       //用會員編號, 找出會員資料
    		String memId = jsonObject.get("memId").getAsString();
    		MemberVO memberVO = memberSvc.getMemberInfo(memId);
    		String outputStr = gson.toJson(memberVO);
    		writeText(res, outputStr);
    		
    	} else if("getMemberImageByMemId".equals(action)) {            // 取會員圖片
    		OutputStream os = res.getOutputStream();
    		String memId = jsonObject.get("memId").getAsString();
    		int imageSize = jsonObject.get("imageSize").getAsInt();
//    		byte[] image = memberSvc.getMemberImage(memId);
    		byte[] image = ImageUtil.getImage("memPic", "member", "memId", memId);
    		if(image != null) {
    			image = ImageUtil.shrink(image, imageSize);
    			res.setContentType("image/jpeg");
    			res.setContentLength(image.length);
    		}
    		os.write(image);
    		System.out.println("------------------------------------------------------------------------------");
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


