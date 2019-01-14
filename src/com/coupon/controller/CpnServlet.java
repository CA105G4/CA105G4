package com.coupon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;
import com.couponRecord.model.CouponRecordService;
import com.couponRecord.model.CouponRecordVO;


@MultipartConfig
public class CpnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String action =req.getParameter("action");
			
			
			if("insert".equals(action)) {
			List<String>errorMsgs =new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			//1.接收參數
			try {
			Integer discount =null;
			
			try {
			discount=new Integer(req.getParameter("discount"));
			}catch(NumberFormatException e) {
				discount =0;
				errorMsgs.add("優惠金額填入數字");
			}
			
			Integer quantity =null;
			try {
				quantity =new Integer(req.getParameter("quantity"));
			}catch(NumberFormatException e){
				quantity =0;
				errorMsgs.add("發行數量請填入數字");
			}
			
			Integer appQuantity =null;
			try {
			appQuantity =new Integer(req.getParameter("appQuantity"));
//				if(appQuantity!=quantity) {
//					errorMsgs.add("發行數量與申請數量請一致");
//				}
			
			}catch(NumberFormatException e) {
				appQuantity =0;
				errorMsgs.add("申請數量請填數字");
			}
				
			System.out.println(quantity);System.out.println(appQuantity);
			
			byte []pic =null;
			
			Part cpnPic =req.getPart("cpnPic");
			
			if(cpnPic.getSubmittedFileName().trim().length()==0||cpnPic.getSubmittedFileName()==null) {
				errorMsgs.add("請上傳一張優惠卷圖片");
			}
			
			InputStream inP =cpnPic.getInputStream();
			pic = new byte[inP.available()];
			inP.read(pic);
			inP.close();
			
			CouponVO cpnVO =new CouponVO();
			cpnVO.setdiscount(discount);
			cpnVO.setquantity(quantity);
			cpnVO.setappQuantity(appQuantity);
			cpnVO.setcpnPic(pic);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("cpnVO", cpnVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/addCoupon.jsp");
				failureView.forward(req, res);
				return;
			}
			//2.新增資料
			CouponService cpnSvc =new CouponService();
			cpnVO =cpnSvc.addCpn(pic, discount, quantity, appQuantity);
			
			//3.新增完成，準備轉交
			String urlBack = "/back-end/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(urlBack);
			successView.forward(req, res);
			}catch(Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/addCoupon.jsp");
				failureView.forward(req, res);
			}
			
			}
			
		if("getOne_For_Update".equals(action)) {
				List<String>errorMsgs =new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL =req.getParameter("requestURL");
				
				try {
				String cpnID =req.getParameter("cpnID");
				
				CouponService cpnSvc =new CouponService();
				CouponVO cpnVO =cpnSvc.getOneByID(cpnID);
				
				req.setAttribute("cpnVO", cpnVO);
				String url = "/back-end/coupon/updateCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				}catch(Exception e) {
					errorMsgs.add("修改資料取出無效" + e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
				
				}
			}

		
		
			if("update".equals(action)) {
				List<String>errorMsgs =new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				String requestURL =req.getParameter("requestURL");
				
				try {
				String cpnID =req.getParameter("cpnID");
				
				Integer discount =null;
				
				try {
				discount =new Integer(req.getParameter("discount"));
				}catch(NumberFormatException e) {
					discount =0;
					errorMsgs.add("請輸入正確的數字");
				}
				
				Integer quantity =null;
				
				try {
				quantity =new Integer(req.getParameter("quantity"));
				}catch(NumberFormatException e) {
					quantity =0;
					errorMsgs.add("請輸入正確數字");
				}
				
				Integer appQuantity =null;
				try {
				appQuantity =new Integer(req.getParameter("appQuantity"));
				}catch(NumberFormatException e) {
					appQuantity =0;
					errorMsgs.add("請輸入正確的數字");
				}
			
				byte[]pic =null;
				
				Part cpnPic =req.getPart("cpnPic");
				InputStream inP =cpnPic.getInputStream();
				
				pic =new byte[inP.available()];
				inP.read(pic);
				inP.close();
				
				if(cpnPic.getSubmittedFileName().trim().length()==0||cpnPic.getSubmittedFileName()==null) {
					errorMsgs.add("請上傳優惠卷圖片");
				}
				
				CouponVO cpnVO =new CouponVO();
			
				cpnVO.setcpnID(cpnID);
				cpnVO.setdiscount(discount);
				cpnVO.setquantity(quantity);
				cpnVO.setappQuantity(appQuantity);
				cpnVO.setcpnPic(pic);
				
				if(!errorMsgs.isEmpty()) {
					req.setAttribute("cpnVO", cpnVO);
					RequestDispatcher failureView =req.getRequestDispatcher("/back-end/coupon/updateCoupon.jsp");
					failureView.forward(req, res);
					
					for(String s: errorMsgs)
						System.out.println(s);
					
					return;
				}
								
				
				CouponService cpnSvc =new CouponService();
				cpnVO =cpnSvc.updateCpn(pic, discount, quantity, appQuantity, cpnID);
				
				req.setAttribute("cpnVO", cpnVO);
				String successURL =requestURL;
				RequestDispatcher successView =req.getRequestDispatcher(successURL);
				successView.forward(req, res);
			}catch(Exception e) {
					errorMsgs.add("修改資料失敗"+e.getMessage());
				RequestDispatcher failureView =req.getRequestDispatcher("/back-end/coupon/updateCoupon.jsp");
				failureView.forward(req, res);
				}
				
				
				
			}
			
			if("delete".equals(action)) {
				List<String>errorMsgs =new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				try {
				String cpnID =req.getParameter("cpnID");
				
				CouponService cpnSvc =new CouponService();
				cpnSvc.delete(cpnID);
				
				String url ="/back-end/coupon/listAllCoupon.jsp";
				RequestDispatcher successView =req.getRequestDispatcher(url);
				successView.forward(req, res);
				}catch(Exception e) {
					errorMsgs.add("刪除資料失敗"+e.getMessage());
					RequestDispatcher failureView =req.getRequestDispatcher("/back-end/coupon/listAllCoupon.jsp");
					failureView.forward(req, res);
					
				}
				
				
			}
			
			//前端頁面選取優惠券
			if("get_coupon".equals(action)) {
				
				
				System.out.println(action);
				
				List<String> errorMsgs =new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				CouponVO cpnVO =new CouponVO();
				@SuppressWarnings("unused")
				CouponRecordVO crVO =new CouponRecordVO();
				Integer totalNow= null;
				CouponService cpnSvc =new CouponService(); 
				CouponRecordService crSvc =new CouponRecordService();
				//1.傳送參數
				String cpnID =req.getParameter("cpnID");
//				String memID =req.getParameter("memID");
				
//				HttpSession session =  req.getSession();
//				MemberVO memVO = (MemberVO)session.getAttribute("memberVO");
//				String memID = memVO.getMemID();
				String memID = "M0008";
				
				System.out.println(cpnID);
				System.out.println(memID);
				if(cpnID.trim().length()==0||cpnID==null) {
					errorMsgs.add("傳輸產生錯誤");
				}
				
				
				//1-1傳送參數，同時新增優惠券明細
				
				try {
					crVO=crSvc.addCR(cpnID, memID,0);
				
					boolean isCollect = crSvc.isMemberCollectCoupon(cpnID, memID);  //檢查是否領取過優惠卷
					
					//2.取出該優惠卷VO，修改資料
					
					cpnVO =cpnSvc.getOneByID(cpnID);
					totalNow =cpnVO.getquantity()-1;     //領取減少的動作
					
					cpnVO= cpnSvc.updateCpn(totalNow, cpnID);  //更改優惠卷數量
					
					//2-1優惠卷取完後，回傳0
					Integer balance=0;
					
					if(cpnVO.getquantity()<=0) {
						cpnVO.setquantity(balance);
						cpnVO=cpnSvc.updateCpn(balance, cpnID);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				

				//3.使用JSONObject 回傳資料
				JSONObject obj = new JSONObject();
				try {
					obj.put("cpnVO", cpnVO.getquantity());
					obj.put("cpnID", cpnID);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				res.setContentType("text/plain");
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.write(obj.toString());
				out.flush();
				out.close();
				
			}
			
			if("get_member_displayCpn".equals(action)) {
				List<String>errorMsgs =new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				CouponRecordService crSvc =new CouponRecordService();
				CouponRecordVO crVO =new CouponRecordVO();
				
				try {
				String memID =req.getParameter("memID");
				List<CouponRecordVO >crList =crSvc.findByMemID(memID);
				
				req.setAttribute("crList", crList);
				
				String url = "/front-end/coupon/myCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
				}catch(Exception e) {
					errorMsgs.add("無法取得資料:" + e.getMessage());
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/myAccountMyPage.jsp");
					failureView.forward(req, res);
				}
				
				
				
				
			}
			
			
	
	
	}

}
