package com.android.orders.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.activity.model.ActivityService;
import com.android.coupon.model.CouponService;
import com.android.couponRecord.model.CouponRecordService;
import com.android.orderDetail.model.OrderDetailService;
import com.android.orderDetail.model.OrderDetailVO;
import com.android.orders.model.OrderTransactionVO;
import com.android.orders.model.OrdersService;
import com.android.orders.model.OrdersVO;
import com.android.roomType.model.RoomTypeService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class OrdersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//    	Gson gson = new Gson();   // 日期格式設定
		Gson gson = new GsonBuilder().setDateFormat("yyyy-mm-dd").create(); // 日期格式設定
		BufferedReader br = req.getReader();
		StringBuilder jsonIn = new StringBuilder();
		String tempStr = null;
		while ((tempStr = br.readLine()) != null)
			jsonIn.append(tempStr);
		System.out.println("jsonIn = " + jsonIn); // 觀察用

//    	OrdersService orderSvc = new OrdersService();
//    	JsonObject jsonObject = gson.fromJson(jsonIn.toString(), JsonObject.class);   // 轉成 jsonObject 物件
//    	String action = jsonObject.get("action").getAsString();

		OrderTransactionVO orderTransactionVO = gson.fromJson(jsonIn.toString(), OrderTransactionVO.class);
		String action = orderTransactionVO.getAction();
		System.out.println(action);

		System.out.println("----------------------------------------------------");
		
		String success = "error";
		
		// 小牛寫的
		if ("sendOrder".equals(action)) { // 新增訂單
			try {
				JSONObject jObj = null; // 第一層 object => {}
				JSONObject jOrdersVO = null; // 第二層 ordersVO
				JSONArray jOrderDetail = null;
	
				// 訂單
				String memId = null;
				String braId = null;
				int ordType = 0;
				int numOfGuest = 0;
				int payment = 0;
	
				// 明細
				String checkinDate = null;
				String checkoutDate = null;
	//			String[] rtIDList = null;
	//			String[] numOfRoomList = null;   //改大寫
	//			String[] specialList = null;
	
				List<String> rtIDList = new ArrayList<String>();
				List<Integer> numOfRoomList = new ArrayList<Integer>();
				List<Integer> specialList = new ArrayList<Integer>();
	
				try {
					jObj = new JSONObject(jsonIn.toString());
					jOrdersVO = jObj.getJSONObject("ordersVO");
					jOrderDetail = jObj.getJSONArray("orderDetailList");
	
					memId = jOrdersVO.getString("memId");
					braId = jOrdersVO.getString("braId");
					ordType = jOrdersVO.getInt("orderType");
					numOfGuest = jOrdersVO.getInt("numOfGuest");
					payment = jOrdersVO.getInt("payment");
	
					checkinDate = jOrderDetail.getJSONObject(0).getString("checkinDate");
					checkoutDate = jOrderDetail.getJSONObject(0).getString("checkoutDate");
	
					for (int i = 0; i < jOrderDetail.length(); i++) {
						String rtId = jOrderDetail.getJSONObject(i).getString("rtId").toString();
						int rooms = jOrderDetail.getJSONObject(i).getInt("rooms");
//						int special = jOrderDetail.getJSONObject(i).getInt("special");
						int special = 0;
	
						if (rooms != 0) {
							rtIDList.add(rtId);
							numOfRoomList.add(rooms);
							specialList.add(special);
						}
					}
	
				} catch (JSONException e) {
					e.printStackTrace();
				}
	
				System.out.println("memId：" + memId);
				System.out.println("braId：" + braId);
				System.out.println("ordType： " + ordType);
				System.out.println("numOfGuest：" + numOfGuest);
				System.out.println("payment：" + payment);
				System.out.println("----------------------------------------");
	
				System.out.println("checkinDate：" + checkinDate);
				System.out.println("checkoutDate：" + checkoutDate);
				System.out.println(rtIDList.toString());
				System.out.println(numOfRoomList.toString());
//				System.out.println(specialList.toString());
	
				// 要使用name="roomnum"的值計算總房間數
	//			String[] numOfRoomList = null;                                       //房間數量
				Integer numOfRoom = 0;
	//			numOfRoomList = req.getParameterValues("numOfRoom");
	//			if (numOfRoomList != null) {
				for (int i = 0; i < numOfRoomList.size(); i++) {
					numOfRoom = numOfRoom + new Integer(numOfRoomList.get(i));
	
					System.out.println("numOfRoomList[" + i + "]:" + numOfRoomList.get(i));
				}
	//			}
				System.out.println("numOfRoom:" + numOfRoom);
	
	//			Integer ordType = null;
	//			ordType = new Integer(req.getParameter("ordType").trim());
	//			System.out.println("ordType："+ ordType);
	
	//			Integer numOfGuest = null;
	//			numOfGuest = new Integer(req.getParameter("numOfGuest").trim());
	//			System.out.println("numOfGuest："+ numOfGuest);				
	
				// 訂單總金額的運算
				int amount = 0;
	
				// 先取到明細所有的房型id
	//			String[] rtIDList = null;                                            //房間id
	//			rtIDList = req.getParameterValues("rtID");	
	//			System.out.println(rtIDList.length);
				// 取到明細所有的特殊需求
	//			String[] specialList = null;
	//			specialList = req.getParameterValues("special");                    //特別需求
				// 取到明細所有的房型房間數量 拿取上面拿過的numOfRoomList
	
				// 取到checkIn的長整數
	//			String checkIn = req.getParameter("checkIn");                       //入住日期
				java.util.Date ckinDate = Date.valueOf(checkinDate);
				long ckinLong = ckinDate.getTime();
				// 取到checkOut的長整數
	//			String checkOut = req.getParameter("checkOut");                    //退房日期
				java.util.Date ckoutDate = Date.valueOf(checkoutDate);
				long ckoutLong = ckoutDate.getTime();
				// 客人總共入住多少天
				int totalday = (int) ((ckoutLong - ckinLong) / 1000 / 60 / 60 / 24);
				System.out.println("住房時間有幾天:" + totalday);
	
				for (int i = 1; i <= totalday; i++) {
					// 算出當天是星期幾(星期五六為假日價格int=56)
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTimeInMillis(ckinLong);
					int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK) - 1;
					System.out.println("今天為星期幾:" + dayOfWeek);
	
					if (5 == dayOfWeek || 6 == dayOfWeek) {
						for (int j = 0; j < rtIDList.size(); j++) {
							RoomTypeService rtSvc = new RoomTypeService();
							
							// 房型價格
							int rtprice = rtSvc.findHollydaypriceByrtID(rtIDList.get(j));
							System.out.println(rtIDList.get(j) + ":" + rtprice);
							
							// 房型數量
							int rtnum = new Integer(numOfRoomList.get(j));
							System.out.println("房型" + rtIDList.get(j) + "數量" + rtnum);
	
							// 當日的總額
							if (specialList.get(j) == 1) {// 有加床
								int countprice = (rtprice * rtnum) + 500;
	
								/**
								 * 要判斷此房型的入住及退房日期是否有碰到促銷活動日期 入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate 房型
								 **/
								ActivityService actSvc = new ActivityService();
								// 判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDList.get(j), splDate);
								countprice = (int) (countprice * todayCount);
								System.out.println("有加床假日當天總額:" + countprice);
	
								// 把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:" + amount);
	
							} else {
								int countprice = (rtprice * rtnum);
	
								/**
								 * 要判斷此房型的入住及退房日期是否有碰到促銷活動日期 入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate 房型
								 **/
								ActivityService actSvc = new ActivityService();
								// 判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDList.get(i), splDate);
								countprice = (int) (countprice * todayCount);
								System.out.println("有加床假日當天總額:" + countprice);
	
								// 把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:" + amount);
							}
	
						}
					} else {
						for (int j = 0; j < rtIDList.size(); j++) {
							RoomTypeService rtSvc = new RoomTypeService();
							// 房型價格
							System.out.println(rtIDList.get(j));
							int rtprice = rtSvc.findWeekpriceByrtID(rtIDList.get(j));
							// 房型數量
							int rtnum = new Integer(numOfRoomList.get(j));
	
							// 當日的總額
							if (specialList.get(j) == 1) {// 有加床
								int countprice = (rtprice * rtnum) + 500;
	
								/**
								 * 要判斷此房型的入住及退房日期是否有碰到促銷活動日期 入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate 房型
								 **/
								ActivityService actSvc = new ActivityService();
								// 判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDList.get(j), splDate);
								countprice = (int) (countprice * todayCount);
								System.out.println("有加床假日當天總額:" + countprice);
	
								// 把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:" + amount);
							} else {
								int countprice = (rtprice * rtnum);
	
								/**
								 * 要判斷此房型的入住及退房日期是否有碰到促銷活動日期 入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate 房型
								 **/
								ActivityService actSvc = new ActivityService();
								// 判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDList.get(j), splDate);
								countprice = (int) (countprice * todayCount);
								System.out.println("有加床假日當天總額:" + countprice);
	
								// 把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:" + amount);
							}
						}
					}
					ckinLong += (24 * 60 * 60 * 1000);
				}
				System.out.println("amount：" + amount);
	
				// 用總金額去算出訂金
				Integer bond = null;
				bond = (int) (amount * 0.3);
				System.out.println("bond：" + bond);
	
	//			Integer payment =null;
	//			payment = new Integer(req.getParameter("payment").trim());
				System.out.println("payment：" + payment);
	
				OrdersVO ordVO = new OrdersVO();
				ordVO.setMemID(memId);
				ordVO.setBraID(braId);
				ordVO.setNumOfRoom(numOfRoom);
				ordVO.setOrdType(ordType);
				ordVO.setNumOfGuest(numOfGuest);
				ordVO.setAmount(amount);
				ordVO.setBond(bond);
				ordVO.setPayment(payment);
	
				/** 以下為訂單明細 **/
				// 訂單明細中的房型list 拿取上面拿過的rtIDList
	
				// 訂單明細中的是否加床lists 拿取上面拿過的peciallist
	
				List<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
	
				for (int i = 0; i < numOfRoomList.size(); i++) {
	
					int rtRoomNum = new Integer(numOfRoomList.get(i));
					// 先判斷房間數量是不是1間
					if (1 == rtRoomNum) {
						OrderDetailVO odVO = new OrderDetailVO();
						odVO.setRtID(rtIDList.get(i));
						odVO.setCheckIn(Date.valueOf(checkinDate));
						odVO.setCheckOut(Date.valueOf(checkoutDate));
						odVO.setSpecial(new Integer(specialList.get(i)));
						odlist.add(odVO);
	
						System.out.println("1間rtID:" + rtIDList.get(i));
						System.out.println("1間checkIn:" + Date.valueOf(checkinDate));
						System.out.println("1間checkOut:" + Date.valueOf(checkoutDate));
						System.out.println("1間special:" + specialList.get(i));
						System.out.println("=======================");
						// 房間數量為>1間，一間要一條明細
					} else {
						for (int j = 0; j < rtRoomNum; j++) {
							OrderDetailVO odVO = new OrderDetailVO();
							odVO.setRtID(rtIDList.get(i));
							odVO.setCheckIn(Date.valueOf(checkinDate));
							odVO.setCheckOut(Date.valueOf(checkoutDate));
							odVO.setSpecial(new Integer(specialList.get(i)));
							odlist.add(odVO);
	
							System.out.println("多間變1間rtID:" + rtIDList.get(i));
							System.out.println("多間變1間checkIn:" + Date.valueOf(checkinDate));
							System.out.println("多間變1間checkOut:" + Date.valueOf(checkoutDate));
							System.out.println("多間變1間special:" + specialList.get(i));
							System.out.println("=======================");
						}
	
					}
	
				}
	
				/** 以下為要傳入dao的Map(存著使用者輸入的房型(key)及間數(value)) **/
				Map<String, Integer> rtIDandNumMap = new HashMap<String, Integer>();
				for (int i = 0; i < rtIDList.size(); i++) {
					// key為拿取上面拿過的rtIDList，value為拿取上面拿過的numOfRoomList
					rtIDandNumMap.put(rtIDList.get(i), new Integer(numOfRoomList.get(i)));
					System.out.println("MAP-key:" + rtIDList.get(i) + "-value:" + numOfRoomList.get(i));
				}
	
				/*------開始新增資料------*/
				OrdersService ordSvc = new OrdersService();
				ordSvc.insertwithOrderDetail(ordVO, odlist, rtIDandNumMap);
				
				success = "success";
				writeText(res, success);
			} catch(Exception ex) {
				writeText(res, success);
			}
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
		System.out.println("output = " + outText); // 觀察用
		System.out.println("----------------------------------------------------------------------------------");
	}
}
