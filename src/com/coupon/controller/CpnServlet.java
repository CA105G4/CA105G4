package com.coupon.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;


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
			
	
	
	
	}

}
