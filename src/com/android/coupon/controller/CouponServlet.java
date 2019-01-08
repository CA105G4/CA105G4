package com.android.coupon.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.android.coupon.model.CouponService;
import com.android.couponRecord.model.CouponRecordService;
import com.android.member.model.MemberService;
import com.android.member.model.MemberVO;
import com.android.orderDetail.model.OrderDetailService;
import com.android.orders.model.OrdersCheckInOutVO;
import com.android.orders.model.OrdersService;
import com.android.tool.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class CouponServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	Gson gson = new Gson(); 
    	BufferedReader br = req.getReader();
    	StringBuilder jsonIn = new StringBuilder();
    	String tempStr = null;
    	while((tempStr = br.readLine()) != null)
    		jsonIn.append(tempStr);
    	System.out.println("CouponServlet jsonIn = " + jsonIn);      //觀察用
    	
    	CouponService couponSvc = new CouponService();
    	CouponRecordService couponRecordSvc = new CouponRecordService();
    	JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);   //轉成 jsonObject 物件
    	String action = jsonObject.get("action").getAsString();

    	if("getAllCoupon".equals(action)) {        //顯示所有優惠券
    		String outStr = gson.toJson(couponSvc.getAllCoupon());   
    		writeText(res, outStr);
    		
    	} else if("memberGetCoupon".equals(action)){                 //會員領取優惠券 => 數量-1 + 新增會員優惠券收藏
    		String memId = jsonObject.get("memId").getAsString();
    		String cpnId = jsonObject.get("cpnId").getAsString();   
    		int quantity = jsonObject.get("quantity").getAsInt();    //取回來的已經被扣掉1了
    		couponSvc.updateCoupon(cpnId, quantity);                 //更新優惠券數量
    		couponRecordSvc.addCoupon(memId, cpnId);                 //新增會員優惠券收藏
    		boolean isCollect = couponRecordSvc.isMemberCollectCoupon(memId, cpnId);
    		String outStr = gson.toJson(isCollect);
    		writeText(res, outStr);   // 成功取得回傳true
    		
    	} else if("getCollectCoupon".equals(action)) {         //會員已收藏的全部優惠券 left join
    		String outStr = gson.toJson(couponSvc.getCoupon());        //加上會員紀錄的 left join
    		writeText(res, outStr);
    		
    	} else if("getMemberCollect".equals(action)){           //inner join
    		String memId = jsonObject.get("memId").getAsString(); 
    		String outStr = gson.toJson(couponSvc.getCouponByMemId(memId));   //用會員編號查詢 inner join
    		writeText(res, outStr);
    		
    	} else if("useCoupon".equals(action)) {       //會員使用優惠券
    		String memId = jsonObject.get("memId").getAsString();
    		String cpnId = jsonObject.get("cpnId").getAsString();
    		Date uDate = new Date();
    		java.sql.Date today = new java.sql.Date(uDate.getTime());
    		System.out.println(today);
    		
    		OrdersService ordersSvc = new OrdersService();
    		OrdersCheckInOutVO ordCheckinVO = ordersSvc.getCheckinMember(memId, today);
			String ordId = ordCheckinVO.getOrdID();      //訂單Id
    		int amount = ordCheckinVO.getAmount();       //總金額
    		int discount = couponSvc.getCouponDiscount(cpnId);       //取得優惠券折價金額
    		couponRecordSvc.updateState(memId, cpnId);               //改變會員收藏狀態
    		int newAmount = amount - discount;
    		System.out.println(ordId);
    		System.out.println(amount);
    		System.out.println(discount);
    		System.out.println(newAmount);
    		String outStr = String.valueOf(ordersSvc.updateOrdersAmount(newAmount, ordId));   //折價後的金額, 存回去
    		writeText(res, outStr);
    		
    	} else if("getCouponImage".equals(action)) {      //優惠券圖片
    		OutputStream os = res.getOutputStream();
    		String cpnId = jsonObject.get("cpnId").getAsString();
    		int imageSize = jsonObject.get("imageSize").getAsInt();
    		byte[] image = ImageUtil.getImage("cpnPic", "coupon", "cpnId", cpnId);
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
