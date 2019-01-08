package com.android.roomType.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.coupon.model.CouponService;
import com.android.couponRecord.model.CouponRecordService;
import com.android.jdbc.compositeQuery.RoomTypeCompositeQuery;
import com.android.roomType.model.RoomTypeBra;
import com.android.roomType.model.RoomTypeService;
import com.android.tool.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

public class RoomTypeServlet extends HttpServlet {
	
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
    	System.out.println("RoomTypeServlet jsonIn = " + jsonIn);      //觀察用
    	
    	RoomTypeService roomTypeSvc = new RoomTypeService();
    	JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);   // 轉成 jsonObject 物件
    	String action = jsonObject.get("action").getAsString();
    	
    	if("getAllRoomType".equals(action)) {
    		String braName = jsonObject.get("braName").getAsString();    	 // 全部, 分店名(多個)
    		String outStr = null;
    		if("全部".equals(braName)) {      
    			outStr = gson.toJson(roomTypeSvc.getAllRoomType());    			
    		} 
    		else {       // 分店找 (複合查詢)
    			String checkinDate = jsonObject.get("checkinDate").getAsString();
				String checkoutDate = jsonObject.get("checkoutDate").getAsString();
    			List<RoomTypeBra> roomTypeList = roomTypeSvc.getRoomTypeByBraName(braName);    // 連線池
    			
    			         
    			List<Integer> maxRooms = new ArrayList<Integer>();    //找出房型的最大房間數
    			List<String> balance = new ArrayList<String>();       //找出房型的 62byte
    			for(RoomTypeBra rtVO : roomTypeList) {
    				maxRooms.add(rtVO.getTotal());
    				balance.add(rtVO.getBalance());    				
    			}
    			System.out.println(maxRooms);
    			System.out.println(balance);
    			
    			roomTypeList = RoomTypeCompositeQuery.searchRoomTypeRange(maxRooms, balance, braName, checkinDate, checkoutDate);

    			outStr = gson.toJson(roomTypeList);     //加上剩餘房間數
    			System.out.println(roomTypeList.get(0).getRooms());
    		}
    		writeText(res, outStr);
    	} else if("getRoomTypeImage".equals(action)){
    		OutputStream os = res.getOutputStream();
    		String rtId = jsonObject.get("rtId").getAsString();
    		int imageSize = jsonObject.get("imageSize").getAsInt();
    		byte[] image = ImageUtil.getImage("rtPic", "roomType", "rtId", rtId);
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
