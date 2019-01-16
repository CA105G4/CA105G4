package com.orders.controller;

import java.io.IOException;
import java.util.*;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.activity.model.ActivityService;
import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;
import com.orders.model.OrdersCheckInOutVO;
import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomType.model.RoomTypeService;

public class OrdersServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("最外層的action:"+action);
		
		if("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*------錯誤處理------*/
				String memID = req.getParameter("memID");
System.out.println("memID："+ memID);
				if(memID == null || memID.trim().length() == 0) {
					errorMsgs.add("會員編號：請勿空白");
				}
				
				String braID = req.getParameter("braID");
System.out.println("braID："+ braID);
				if("-1".equals(braID)) {
					errorMsgs.add("分店：請選擇分店");
				}
				
				//要使用name="roomnum"的值計算總房間數
					//首先要先拿到rtID的數量
				String[] rtIDlist = null;
				rtIDlist = req.getParameterValues("rtID");	
				System.out.println("rtIDlist.length:"+rtIDlist.length);
					//取到所有的總房間數
				String[] numOfRoomlistbefore = null;
				Integer numOfRoom = 0;
				numOfRoomlistbefore = req.getParameterValues("numOfRoom");
				
//				for(int i=0; i<numOfRoomlistbefore.length; i++) {
//					System.out.println("OLDnumOfRoomlist["+i+"]:"+ numOfRoomlistbefore[i]);
//				}
				
					//篩選掉請選擇(-1)，在放入新的房間數陣列
				String[] numOfRoomlist = new String[rtIDlist.length];
				int numOfRoomlistindex = 0;
				for(int i=0; i<numOfRoomlistbefore.length; i++) {
					String numOfRoomvalue = numOfRoomlistbefore[i];
					if(!("-1".equals(numOfRoomvalue))) {
						numOfRoomlist[numOfRoomlistindex]=numOfRoomlistbefore[i];
						numOfRoomlistindex++;
					}
				}			
					//算出房間總數
				for(int i=0; i<numOfRoomlist.length; i++) {
					numOfRoom += new Integer(numOfRoomlist[i]);
					System.out.println("NEWnumOfRoomlist["+i+"]:"+ numOfRoomlist[i]);
				}
System.out.println("numOfRoom："+ numOfRoom);					
			
				Integer ordType = null;
				ordType = new Integer(req.getParameter("ordType").trim());
System.out.println("ordType："+ ordType);
					
				Integer numOfGuest = null;
				try {
					numOfGuest = new Integer(req.getParameter("numOfGuest").trim());
System.out.println("numOfGuest："+ numOfGuest);				
				}catch(IllegalArgumentException e) {
					numOfGuest = 1;
					errorMsgs.add("人數：請填數字");
				}
				
				//訂單總金額的運算
				int amount = 0;
				
					//先取到明細所有的房型id	拿取前面的 rtIDlist
				
					//取到明細所有的特殊需求，但因為陣列會是全部送進來
//				String[] speciallistbefore = null;
//				speciallistbefore = req.getParameterValues("special");
					//做篩選，篩選掉請選擇(-1)，再存到新的陣列中
				String[] speciallist = new String[rtIDlist.length];
//				int specialindex = 0;
//				for(int i=0; i<speciallistbefore.length; i++) {
//					String specialvalue = speciallistbefore[i];
//					if(!("-1".equals(specialvalue))) {
//						speciallist[specialindex] = speciallistbefore[i];
//						specialindex++;
//					}
//				}
					/**因最後決定不讓客人決定要不要加床，這邊全部直接存為不加床**/
				for(int i=0; i<speciallist.length; i++) {
					speciallist[i] = "0";
				}
				
				for(int i=0; i<speciallist.length; i++) {
					System.out.println("speciallist["+i+"]"+speciallist[i]);
				}
				
				System.out.println("speciallist.length:"+speciallist.length);
					//取到明細所有的房型房間數量 拿取上面拿過的numOfRoomlist

					//取到checkIn的長整數
				String checkIn = req.getParameter("checkIn");
				java.util.Date ckinDate = Date.valueOf(checkIn);
				long ckinLong = ckinDate.getTime();
					//取到checkOut的長整數
				String checkOut = req.getParameter("checkOut");
				java.util.Date ckoutDate = Date.valueOf(checkOut);
				long ckoutLong = ckoutDate.getTime();
					//客人總共入住多少天
				int totalday = (int)((ckoutLong-ckinLong)/1000/60/60/24);
				System.out.println("住房時間有幾天:" + totalday);
					
				for(int i=1; i<=totalday; i++) {
					//算出當天是星期幾(星期五六為假日價格int=56)
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTimeInMillis(ckinLong);
					int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK)-1;
					System.out.println("今天為星期幾:" + dayOfWeek);
					
					if(5 == dayOfWeek || 6 == dayOfWeek) {
						for(int j=0; j<rtIDlist.length; j++) {
							RoomTypeService rtSvc = new RoomTypeService();
							//房型價格
							int rtprice = rtSvc.findHollydaypriceByrtID(rtIDlist[j]);
							System.out.println(rtIDlist[j]+":"+rtprice);
							//房型數量
							int rtnum = new Integer(numOfRoomlist[j]);
							System.out.println("房型"+rtIDlist[j]+"數量"+rtnum);
							
							//當日的總額
							if("1".equals(speciallist[j])) {//有加床
								int countprice =(rtprice*rtnum)+500;
								
								/**要判斷此房型的入住及退房日期是否有碰到促銷活動日期
								入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate
								房型**/
								ActivityService actSvc = new ActivityService();
								//判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDlist[j], splDate);
								countprice = (int)(countprice*todayCount);
								System.out.println("有加床假日當天總額:" + countprice);
								
								//把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:"+amount);
								
							}else {
								int countprice =(rtprice*rtnum);
								
								/**要判斷此房型的入住及退房日期是否有碰到促銷活動日期
								入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate
								房型**/
								ActivityService actSvc = new ActivityService();
								//判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDlist[j], splDate);
								countprice = (int)(countprice*todayCount);
								System.out.println("無加床假日當天總額:" + countprice);
								
								//把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:"+amount);
							}
							
						}
					}else {
						for(int j=0; j<rtIDlist.length; j++) {
							RoomTypeService rtSvc = new RoomTypeService();
							//房型價格
							int rtprice = rtSvc.findWeekpriceByrtID(rtIDlist[j]);
							//房型數量
							int rtnum = new Integer(numOfRoomlist[j]);
							
							//當日的總額
							if("1".equals(speciallist[j])) {//有加床
								int countprice =(rtprice*rtnum)+500;
								
								/**要判斷此房型的入住及退房日期是否有碰到促銷活動日期
								入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate
								房型**/
								ActivityService actSvc = new ActivityService();
								//判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDlist[j], splDate);
								countprice = (int)(countprice*todayCount);
								System.out.println("有加床平日當天總額:" + countprice);
								
								//把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:"+amount);
							}else {
								int countprice =(rtprice*rtnum);
								
								/**要判斷此房型的入住及退房日期是否有碰到促銷活動日期
								入住時間,退房時間拿取上面的java.util.Date ckinDate ckoutDate
								房型**/
								ActivityService actSvc = new ActivityService();
								//判斷當日日期及其房型是否有在促銷活動時間內
								String splDate = new Date(ckinLong).toString();
								System.out.println("splDate:" + splDate);
								float todayCount = actSvc.getActivityDiscount(rtIDlist[j], splDate);
								countprice = (int)(countprice*todayCount);
								System.out.println("無加床平日當天總額:" + countprice);
								
								//把前幾天累積的金額加上今日的金額
								amount += countprice;
								System.out.println("總額Total:"+amount);
							}
						}
					}
					ckinLong += (24*60*60*1000);
				}							
System.out.println("amount："+ amount);			
				
				//用總金額去算出訂金
				Integer bond =null;
				if(ordType == 0) {
					bond = (int)(amount*0.3);
				}else {
					bond = 0;
				}
				
System.out.println("bond："+ bond);
				
				Integer payment =null;
				payment = new Integer(req.getParameter("payment").trim());
System.out.println("payment："+payment);
				
				OrdersVO ordVO = new OrdersVO();
				ordVO.setMemID(memID);
				ordVO.setBraID(braID);
				ordVO.setNumOfRoom(numOfRoom);
				ordVO.setOrdType(ordType);
				ordVO.setNumOfGuest(numOfGuest);
				ordVO.setAmount(amount);
				ordVO.setBond(bond);
				ordVO.setPayment(payment);
				
				/**以下為訂單明細**/
				//訂單明細中的房型list 拿取上面拿過的rtIDlist

				//訂單明細中的是否加床lists 拿取上面拿過的peciallist
				
								
				List<OrderDetailVO> odlist = new ArrayList<OrderDetailVO>();
				
				for(int i=0; i<numOfRoomlist.length; i++) {
					
					int rtRoomNum = new Integer(numOfRoomlist[i]);
					//先判斷房間數量是不是1間
					if( 1 == rtRoomNum) {	
						OrderDetailVO odVO = new OrderDetailVO();
						odVO.setRtID(rtIDlist[i]);
System.out.println("1間rtID:" +rtIDlist[i]);
						odVO.setCheckIn(Date.valueOf(req.getParameter("checkIn")));
System.out.println("1間checkIn:" + Date.valueOf(req.getParameter("checkIn")));
						odVO.setCheckOut(Date.valueOf(req.getParameter("checkOut")));
System.out.println("1間checkOut:" + Date.valueOf(req.getParameter("checkOut")));					
						odVO.setSpecial(new Integer(speciallist[i]));
System.out.println("1間special:" +speciallist[i]);
						odlist.add(odVO);
System.out.println("=======================");
					//房間數量為>1間，一間要一條明細
					}else {
						for(int j=0; j<rtRoomNum; j++) {
							OrderDetailVO odVO = new OrderDetailVO();
							odVO.setRtID(rtIDlist[i]);
System.out.println("多間變1間rtID:"+rtIDlist[i]);
							odVO.setCheckIn(Date.valueOf(req.getParameter("checkIn")));
System.out.println("多間變1間checkIn:" + Date.valueOf(req.getParameter("checkIn")));
							odVO.setCheckOut(Date.valueOf(req.getParameter("checkOut")));
System.out.println("多間變1間checkOut:" + Date.valueOf(req.getParameter("checkOut")));					
							odVO.setSpecial(new Integer(speciallist[i]));
System.out.println("多間變1間special:"+speciallist[i]);
							odlist.add(odVO);
System.out.println("=======================");
						}
						
					}
					
				}
				
				/**以下為要傳入dao的Map(存著使用者輸入的房型(key)及間數(value))**/
				Map<String,Integer> rtIDandNumMap = new HashMap<String,Integer>();
				for(int i=0; i<rtIDlist.length; i++) {
					//key為拿取上面拿過的rtIDlist，value為拿取上面拿過的numOfRoomlist
					rtIDandNumMap.put(rtIDlist[i], new Integer(numOfRoomlist[i]));
					System.out.println("MAP-key:" + rtIDlist[i] + "-value:" + numOfRoomlist[i]);
				}
				
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("ordVO", ordVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/addorders.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*------開始新增資料------*/
				OrdersService ordSvc = new OrdersService();
				ordSvc.insertwithOrderDetail(ordVO, odlist, rtIDandNumMap);
				
				/*------新增完成,SET，然後轉交(Send the Success view)------*/
				req.setAttribute("memID", memID);
				req.setAttribute("rtIDandNumMap", rtIDandNumMap);
				
				String comeURI = req.getParameter("requestURL");
				System.out.println("comeURI"+comeURI);
				
				if("/back-end/orders/addorders.jsp".equals(comeURI)) {
					String url = "/back-end/orders/addorders_step2.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);	
				}else {
					String url = "/front-end/orders/addorders_step2.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);	
				}

				
				/*------其他可能的錯誤處理------*/
			}catch(Exception e) {
				errorMsgs.add(e.getMessage()+"其他莫名其妙的錯誤處理");
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/addorders.jsp");
				failureView.forward(req, res);				
			}
		}
		
		if("GetOneUpdate".equals(action)) {
			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*------接收請求參數------*/
				String ordID = req.getParameter("ordID");
				System.out.println(req.getParameter("ordID"));
				
				/*------開始查詢資料------*/
				OrdersService ordsvc = new OrdersService();
				OrdersVO ordVO = ordsvc.getOneOrders(ordID);
				
				/*------完成查詢，Set，然後轉交------*/
				req.setAttribute("ordVO", ordVO);
				String url = "/back-end/orders/update_orders_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/listAllOrders.jsp");
				failureView.forward(req, res);
			}
			
			
		}
		
		if("update".equals(action)) {
			System.out.println(action);
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String ordID = req.getParameter("ordID");
			
			/*------錯誤處理------*/
			String memID = req.getParameter("memID");
			System.out.println("MemID" + memID);
			
			
			String braID = req.getParameter("braID");
			if(braID == null || braID.trim().length() == 0) {
				errorMsgs.add("分店編號：請勿空白");
			}
			
			Integer numOfRoom = null;
			try {
				numOfRoom = new Integer(req.getParameter("numOfRoom"));
			}catch(IllegalArgumentException e) {
				errorMsgs.add("房間數：請填數字");
			}
	
			Integer ordType = null;
			try {
				ordType = new Integer(req.getParameter("ordType").trim());
			}catch(IllegalArgumentException e) {
				errorMsgs.add("訂單種類：請填數字");
			}
				
			Integer numOfGuest = null;
			try {
				numOfGuest = new Integer(req.getParameter("numOfGuest").trim());
			}catch(IllegalArgumentException e) {
				errorMsgs.add("人數：請填數字");
			}
			
			Integer amount = null;
			try {
				amount = new Integer(req.getParameter("amount").trim());
			}catch(IllegalArgumentException e) {
				errorMsgs.add("總金額：請填數字");
			}
			
			Integer bond =null;
			try {
				bond = new Integer(req.getParameter("bond").trim());
//				System.out.println(bond);
			}catch(IllegalArgumentException e) {
				errorMsgs.add("訂金：請填數字");
			}
						
			
			Integer payment =null;
			try {
				payment = new Integer(req.getParameter("payment").trim());
			}catch(IllegalArgumentException e) {
				errorMsgs.add("付款方式：請填數字");
			}
			
			
			Integer ordState =null;
			try {
				ordState = new Integer(req.getParameter("ordState").trim());
				System.out.println(req.getParameter("ordState"));
			}catch(IllegalArgumentException e) {
				errorMsgs.add("訂單狀態：請填數字");
			}
			

			OrdersVO ordVO = new OrdersVO();
			ordVO.setOrdID(ordID);
			ordVO.setMemID(memID);
			ordVO.setBraID(braID);
			ordVO.setNumOfRoom(numOfRoom);
			ordVO.setOrdType(ordType);
			ordVO.setNumOfGuest(numOfGuest);
			ordVO.setAmount(amount);
			ordVO.setBond(bond);
			ordVO.setPayment(payment);
			ordVO.setOrdType(ordType);
			ordVO.setOrdState(ordState);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("ordVO", ordVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/update_orders_input.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/*------開始修改資料------*/
			OrdersService ordsvc = new OrdersService();
			ordVO = ordsvc.updateOrders(ordID, memID, braID, numOfRoom, ordType, numOfGuest, amount, bond, payment, ordState);
			
			/*------修改完成，Set後，轉交------*/
			req.setAttribute("ordVO", ordVO);
			String url = "/back-end/orders/listAllOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("GiveRoom".equals(action)) {
			
			/*------取到該行的訂單資料，並且轉交------*/
			String ordID = req.getParameter("ordID");
System.out.println("傳進來的ordID"+ordID);
			OrdersService ordSvc = new OrdersService();
			OrdersVO ordVO = ordSvc.getOneOrders(ordID);
			req.setAttribute("ordVO", ordVO);
			
			/*------取到該行的訂單資料的訂單明細，並且轉交------*/
			Integer odID = new Integer(req.getParameter("odID"));
System.out.println("傳進來的odID"+odID);
			OrderDetailService odSvc = new OrderDetailService();
			OrderDetailVO odVO = odSvc.getOneOrderDetail(odID);
			req.setAttribute("odVO", odVO);
			
			/*------取到該分店的該房型的所有狀態為空房的房間，並且轉交------*/
			String braID = req.getParameter("braID");
System.out.println("傳進來的braID"+braID);
			String rtID = req.getParameter("rtID");
System.out.println("傳進來的rtID"+rtID);
			Integer roomState = new Integer(req.getParameter("roomState"));
System.out.println("傳進來的roomState"+roomState);
			
			RoomService roomSvc = new RoomService();
			List<RoomVO> roomVOlist = roomSvc.getRoomForAssign(braID, rtID, roomState);
			req.setAttribute("roomVOlist", roomVOlist);
			
			/*------如果原checkIn被選的明細有沒有roomID的值------*/
			String orginroomID = req.getParameter("roomID");
			System.out.println("orginroomID:"+orginroomID);
			if(orginroomID != null) {	//如果有值
				req.setAttribute("orginroomID", orginroomID);
			}
			
		
			String url = "/back-end/orders/checkIn_giveRoom.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
		if("InsertRoomintoOd".equals(action)) {
			
			String ordID = req.getParameter("ordID");
			System.out.println("ordID:"+ordID);
					
			/*------修改被分配的該間 房間的狀態 及 入住會員姓名------*/
			String roomID = req.getParameter("roomID");
			RoomService roomSvc = new RoomService();
			
			OrdersService ordSvc = new OrdersService();
			OrdersVO ordVO =ordSvc.getOneOrders(ordID);
			String memname = ordVO.getMemID();	/*------改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改改ordVO.getMemName()------*/
			System.out.println(ordID+"訂單的會員為:"+memname);
			roomSvc.updateRoomState(2, memname, roomID); //房間狀態2，為"入住"的意思
System.out.println("修改房間狀態及入住會員姓名成功");

			/*------如果orginroomID有值，要將此房間狀態 及 入住會員姓名改為1------*/
			String orginroomID = req.getParameter("orginroomID");
System.out.println(orginroomID);
			if(orginroomID != null) {
				String orgmemname = "";
				roomSvc.updateRoomState(1, orgmemname, orginroomID);//房間狀態1，為"空房"的意思
			}
			
			/*------訂單明細要加入房間編號------*/
			Integer odID = new Integer(req.getParameter("odID"));
			OrderDetailService odSvc = new OrderDetailService();
			odSvc.updateRoomID(roomID, odID);
			req.setAttribute("odID", odID);
			
System.out.println("修改訂單明細，加入房間編號成功");

			/*------以上方法皆結束，轉交回checkIn網頁------*/
			String url = "/back-end/orders/checkIn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("CheckInUpdate".equals(action)) {
			String ordID = req.getParameter("ordID");
			
			System.out.println("ordID:"+ordID);
			
			OrdersService ordSvc = new OrdersService();
			
			/*------先確認是否此訂單皆有分房完成------*/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Set<OrderDetailVO> odlist = ordSvc.getOrderDetailByOrders(ordID);
			for(OrderDetailVO odVO : odlist) {
				if(odVO.getRoomID() == null) {
					errorMsgs.add("此訂單還有明細尚未分房!請先分房完再checkIn!");
				}
			}
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/checkIn.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/*------如以上錯誤驗證皆無問題，修改訂單的狀態------*/
			ordSvc.updateOrdState(1, ordID); //訂單狀態1，為"入住"的意思
System.out.println("修改訂單狀態成功");
			
			/*------成功修改訂單狀態後，通知checkIn.jsp去告知Endpoint推播房況，使其更新------*/	/*------新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增webSocket------*/
			req.setAttribute("refreshRoomState", "1");
			
			/*------以上方法皆結束，轉交回checkIn網頁------*/
			String url = "/back-end/orders/checkIn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if("CheckOutUpdate".equals(action)) {
			
			String ordID = req.getParameter("ordID");
			OrdersService ordSvc = new OrdersService();
			
			/*------先找到被選擇的該張訂單所有的明細------*/
			Set<OrderDetailVO> odSet = ordSvc.getOrderDetailByOrders(ordID);
			
			/*------每張明細中的房間，改其 房間的狀態3 及 入住會員姓名"清空"------*/
			for(OrderDetailVO odVO : odSet) {
				
				/*------修改被分配的該間 房間的狀態 及 入住會員姓名------*/
				String roomID = odVO.getRoomID();
				RoomService roomSvc = new RoomService();
				
				String memname = "";
				System.out.println(ordID+"訂單的會員為:"+memname);
				roomSvc.updateRoomState(3, memname, roomID); //房間狀態3，為"打掃"的意思
			}
			
			/*------修改訂單的狀態------*/
			ordSvc.updateOrdState(2, ordID); 
			
			/*------成功修改訂單狀態後，通知checkOut.jsp去告知Endpoint推播房況，使其更新------*/	/*------新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增新增webSocket------*/
			req.setAttribute("refreshRoomState", "1");
			
			/*------以上方法皆結束，轉交回checkOut網頁------*/
			String url = "/back-end/orders/checkOut.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("getAll_OrderDetail".equals(action)) {
			
			String ordID = (String)req.getParameter("ordID");
			req.setAttribute("ordID", ordID);
			
			/*------查到單筆訂單的所有明細(set)------*/
			OrdersService ordSvc = new OrdersService();
			Set<OrderDetailVO> odSet = ordSvc.getOrderDetailByOrders(ordID);
			req.setAttribute("odSet", odSet);
			
			/*------設置旗標------*/
			boolean openModal=true;
			req.setAttribute("openModal",openModal);	//此為旗標
			
			/*------準備轉交------*/
			String comeURI = req.getParameter("requestURL");
			System.out.println("comeURI：" + comeURI);
			
			RequestDispatcher successView = req.getRequestDispatcher(comeURI);
			successView.forward(req, res);
	
		}
		
		
		if ("CancelOrders".equals(action)) {
			String ordID = req.getParameter("ordID");
			System.out.println("service接收到ordID:"+ordID);
			
			/**要先把該張訂單中，所有明細裡面的房型，天數加回去**/
			OrdersService ordSvc = new OrdersService();	
			Set<OrderDetailVO> odSet = ordSvc.getOrderDetailByOrders(ordID);
			for(OrderDetailVO odVO : odSet) {
				RoomTypeService rtSvc = new RoomTypeService();
				rtSvc.cancelOrderChangeRoomBalance(odVO.getRtID(), odVO.getCheckIn(), odVO.getCheckOut());
			}
			
			/**前面完成後，更改訂單狀態**/
			ordSvc.updateOrdState(3, ordID);	//訂單狀態3，為"退訂"的意思
			
			String comeURI = req.getParameter("requestURL");
			System.out.println("comeURI:" + comeURI);

			RequestDispatcher successView = req.getRequestDispatcher(comeURI);
			successView.forward(req, res);
			
		}
		
		
		if ("AddBed".equals(action)) {
			String ordID = req.getParameter("ordID");
			System.out.println("service接收到ordID:"+ordID);
			
			/*------取到該行的訂單資料，並且轉交------*/
			OrdersService ordSvc = new OrdersService();
			OrdersVO ordVO = ordSvc.getOneOrders(ordID);
			
			req.setAttribute("ordVO", ordVO);
			
			/*------取到該行的訂單資料的訂單明細，並且轉交------*/
			Integer odID = new Integer(req.getParameter("odID"));
System.out.println("傳進來的odID"+odID);
			OrderDetailService odSvc = new OrderDetailService();
			OrderDetailVO odVO = odSvc.getOneOrderDetail(odID);
			
			req.setAttribute("odVO", odVO);
			
			/*------取到來源，把來源網址傳到加床頁面------*/
			String comeURI = req.getParameter("requestURL");
			System.out.println("comeURI:" + comeURI);
			
			req.setAttribute("comeURI", comeURI);
			
			/*------導回來源------*/
			String url = "/back-end/orders/addBed.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}
		
		if ("ChangeAmount".equals(action)) {
			String ordID = req.getParameter("ordID");
System.out.println("service接收到ordID:"+ordID);
			Integer odID = new Integer(req.getParameter("odID"));
System.out.println("傳進來的odID"+odID);
			
			/*------取到加床的值------*/
			Integer special = new Integer(req.getParameter("special"));
			
			/*------找到原本加床的欄位值------*/
			OrderDetailService odSvc = new OrderDetailService();
			int oldSpecial = odSvc.getOneOrderDetail(odID).getSpecial();
			
			/*------判斷原本欄位與新的欄位是否一致------*/
			if(oldSpecial != special) {	
				/*------對明細進行 加床欄位 更改的動作------*/
				odSvc.updateSpecial(special, odID);
System.out.println("對明細的加床欄位進行修改");
				
				if(special==0){
					/*------原本加床變不加床(-500)------*/
					OrdersService ordSvc = new OrdersService();
					Integer oldAmount = ordSvc.getOneOrders(ordID).getAmount();
System.out.println("oldAmount:"+oldAmount);
					Integer newAmount = oldAmount-500;
System.out.println("newAmount:"+newAmount);
					ordSvc.addBedupdateAmount(newAmount, ordID);
				}else {
					/*------原本不加床變加床(+500)------*/
					OrdersService ordSvc = new OrdersService();
					Integer oldAmount = ordSvc.getOneOrders(ordID).getAmount();
System.out.println("oldAmount:"+oldAmount);
					Integer newAmount = oldAmount+500;
System.out.println("newAmount:"+newAmount);
					ordSvc.addBedupdateAmount(newAmount, ordID);
				}
				
				String comeURI = req.getParameter("comeURI");
System.out.println("comeURI:" + comeURI);
				
				RequestDispatcher successView = req.getRequestDispatcher(comeURI);
				successView.forward(req, res);
			}else {
				String comeURI = req.getParameter("comeURI");
System.out.println("comeURI:" + comeURI);
				
				RequestDispatcher successView = req.getRequestDispatcher(comeURI);
				successView.forward(req, res);
			}
						
		}
		
	}
	
	
}
