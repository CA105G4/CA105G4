package com.branch.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import com.branch.model.BranchService;
import com.branch.model.BranchVO;
import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;

@MultipartConfig

public class braServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		System.out.println(action);

		if ("insert".equals(action)) { // 來自addBra.jsp的請求
			System.out.println("insert");
			List<String> errorMsgs = new LinkedList<String>(); // 放置錯誤訊息的容器
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				String braName = req.getParameter("braName");

				if (braName == null || braName.trim().length() == 0) {
					errorMsgs.add("請輸入分店店名");
				}

				String phone = req.getParameter("phone");

				if (phone == null || phone.trim().length() == 0) {
					errorMsgs.add("請輸入分店電話");
				}

				Double lng = null;

				try {
					lng = new Double(req.getParameter("lng").trim());
				} catch (NumberFormatException e) {
					lng = 0.0;
					errorMsgs.add("請填入分店經度");
				}

				Double lat = null;

				try {
					lat = new Double(req.getParameter("lat").trim());
				} catch (NumberFormatException e) {
					lat = 0.0;
					errorMsgs.add("請填入分店緯度");
				}

				String addr = req.getParameter("addr");

				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("分店地址請勿空白");
				}

				String intro = req.getParameter("intro");

				if (intro == null || intro.trim().length() == 0) {
					errorMsgs.add("請輸入分店介紹");
				}

				byte[] pic = null;
				Part braPic = req.getPart("braPic");
				if (braPic.getSubmittedFileName().trim().length() == 0 || braPic.getSubmittedFileName() == null) {
					errorMsgs.add("請上傳分店照片");
				}
				InputStream inP = braPic.getInputStream();

				pic = new byte[inP.available()];
				inP.read(pic);
				inP.close();

				byte[] video = null;
				Part braVideo = req.getPart("braVideo");
				InputStream inV = braVideo.getInputStream();
				video = new byte[inV.available()];
				inV.read(video);
				inV.close();

				if (braVideo.getSubmittedFileName().trim().length() == 0 && braVideo.getContentType() == null) {
					errorMsgs.add("請上傳分店影片");
				}

				Integer bchStateChecked = null;
				String braState = req.getParameter("braState");
				if ("1".equals(braState)) {
					bchStateChecked = new Integer(braState);
				} else if ("0".equals(braState)) {
					bchStateChecked = new Integer(braState);
				}

				BranchVO braVO = new BranchVO();

				braVO.setBraName(braName);
				braVO.setBraTel(phone);
				braVO.setBraAddr(addr);
				braVO.setBraIntro(intro);
				braVO.setBraLng(lng);
				braVO.setBraLat(lat);
				braVO.setBraState(bchStateChecked);
				braVO.setBraPic(pic);
				braVO.setBraVideo(video);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("braVO", braVO); // 含有輸入格式錯誤的braVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/branch/addBra.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				BranchService braSvc = new BranchService();
				braVO = braSvc.addBra(braName, intro, pic, phone, video, addr, lng, lat, bchStateChecked);
				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/back-end/branch/listAllBranch.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {

				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/branch/addBra.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自update_bra_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>(); // 錯誤訊息
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");// 送出修改的來源請求

			try {
			

				String braID = req.getParameter("braID");
				System.out.println(braID);
				/*************************** 2.開始查詢資料 ****************************************/
				BranchService bchSvc = new BranchService();
				BranchVO braVO = bchSvc.getOneByID(braID);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("braVO", braVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/branch/update_bra_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/

			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失效:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		
		
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>(); // 儲存錯誤在list裡
			req.setAttribute("errorMsgs", errorMsgs);
System.out.println(action);			
			String requestURL = req.getParameter("requestURL"); 	 // 送出修改的來源網頁路徑: 可能為【/branch/listAllBranch.jsp】
			/*************************** * 1.接收請求參數- 輸入格式的錯誤處理 ****************************************/
try {
			String braID = req.getParameter("braID");
System.out.println(braID);			
			String braName = req.getParameter("braName");

			if (braName == null || braName.trim().length() == 0) {
				errorMsgs.add("修改資料，分店名稱請勿空白");
			}

			String phone = req.getParameter("phone");

			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("分店電話請勿空白");
			}

			Double lng = null;
			try {
				lng = new Double(req.getParameter("lng").trim());
			} catch (NumberFormatException e) {
				lng = 0.0;
				errorMsgs.add("請填入分店經度");
			}
			
			Double lat = null;
			try {
				lat = new Double(req.getParameter("lat").trim());
			} catch (NumberFormatException e) {
				lat = 0.0;
				errorMsgs.add("請填入分店緯度");
			}
			
			String addr = req.getParameter("addr");
			if (addr == null || addr.trim().length() == 0) {
				errorMsgs.add("分店地址請勿空白");
			}
			
			String intro = req.getParameter("intro");
			if (intro == null || intro.trim().length() == 0) {
				errorMsgs.add("請輸入分店介紹");
			}


			byte[] pic =null;
			Part braPic =req.getPart("braPic");
			if (braPic.getSubmittedFileName().trim().length() == 0 || braPic.getSubmittedFileName() == null) {
				BranchService braSvc = new BranchService();
				BranchVO braVO = braSvc.getOneByID(braID);
				pic = braVO.getBraPic();
			}else {
				InputStream in =  braPic.getInputStream();
				pic = new byte[in.available()];
				in.read(pic);
				in.close();
			}
			
//			InputStream inP= braPic.getInputStream();
			
//			pic = new byte[inP.available()];
//			inP.read(pic);
//			inP.close();
			
			
			
			byte[] video = null;
			Part braVideo = req.getPart("braVideo");
			int i;
			if (braVideo.getSubmittedFileName().trim().length() == 0 && braVideo.getContentType() == null) {
				BranchService braSvc = new BranchService();
				BranchVO braVO = braSvc.getOneByID(braID);
				video = braVO.getBraVideo();
			}else {
				InputStream in =  braVideo.getInputStream();
				video = new byte[in.available()];
				in.read(video);
				in.close();
			}
			
			Integer bchStateChecked = null;
			String braState = req.getParameter("braState");
			if ("1".equals(braState)) {
				bchStateChecked = new Integer(braState);
			} else if ("0".equals(braState)) {
				bchStateChecked = new Integer(braState);
			}

			BranchVO braVO = new BranchVO();
			braVO.setBraID(braID);
			braVO.setBraName(braName);
			braVO.setBraTel(phone);
			braVO.setBraAddr(addr);
			braVO.setBraIntro(intro);
			braVO.setBraLng(lng);
			braVO.setBraLat(lat);
			braVO.setBraState(bchStateChecked);
			braVO.setBraPic(pic);
			braVO.setBraVideo(video);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("braVO", braVO); // 含有輸入格式錯誤的braVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/branch/update_bra_input.jsp");
				failureView.forward(req, res);
				return;
			}
			
			
			/***************************2.開始修改資料*****************************************/
			BranchService braSvc =new BranchService();
			braVO =braSvc.updateBra(braName, intro, pic, phone, video, addr, lng, lat, bchStateChecked,braID);
			System.out.println("testA");	
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("braVO", braVO);// 資料庫update成功後,正確的的braVO物件,存入req
			String url =requestURL;
			RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
			successView.forward(req, res);
		
			System.out.println("testB");	
		}catch(Exception e) {
			errorMsgs.add("修改資料失敗"+ e.getMessage());
			System.out.println(e.getMessage());
			RequestDispatcher failureView =req.getRequestDispatcher("/back-end/branch/update_bra_input.jsp");
			failureView.forward(req, res);
		
			for(String s:errorMsgs) {
				System.out.println(s);
			}
			
		}
		
		}



	}

}// doPost()的右括弧
