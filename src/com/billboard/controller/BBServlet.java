package com.billboard.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.billboard.model.BillboardService;
import com.billboard.model.BillboardVO;
import com.branch.model.BranchService;
import com.branch.model.BranchVO;

@MultipartConfig
public class BBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				/*************************** 1-1 URL **********************/
				String url = req.getParameter("url");

				String urlReg = "^[(?<http>(http:[/][/]|www.)([a-z]|[A-Z]|[0-9]|[/.]|[~])*)]";

				if (url.trim().length() == 0 || url == null) {
					errorMsgs.add("URL請勿空白");
				} else if (url.trim().matches(urlReg)) {
					errorMsgs.add("URL開頭必須是http且為英文字母和數字的字串");
				}
				
				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				/*************************** 1-2 開始時間 **********************/
				Timestamp bbup =null;
				
				try {
				java.util.Date sdate = sdf.parse(req.getParameter("bbStart"));		
				bbup = Timestamp.valueOf(sdf.format(sdate));
				}catch(IllegalArgumentException el) {
					errorMsgs.add("請輸入廣告開始時間");
				}
				
				/*************************** 1-3 結束時間 **********************/
				Timestamp bbdown =null;
				try {
					java.util.Date edate = sdf.parse(req.getParameter("bbEnd"));		
					bbdown = Timestamp.valueOf(sdf.format(edate));
					}catch(IllegalArgumentException el) {
						errorMsgs.add("請輸入廣告結束時間");
					}
				
				/*************************** 1-4 輪播廣告照片 **********************/
				byte[] pic = null;

				Part bbPic = req.getPart("bbPic");
				InputStream inP = bbPic.getInputStream();

				pic = new byte[inP.available()];
				inP.read(pic);
				inP.close();

				if (bbPic.getSubmittedFileName().trim().length() == 0 || bbPic.getSubmittedFileName() == null) {
					errorMsgs.add("請上傳輪播廣告圖片");
				}
				

				BillboardVO bbVO = new BillboardVO();

				bbVO.seturl(url);
				bbVO.setbbStart(bbup);
				bbVO.setbbEnd(bbdown);
				bbVO.setpic(pic);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bbVO", bbVO); // 含有輸入格式錯誤的braVO物件,也存入req

					for (String s : errorMsgs) {
						System.out.println(s);
					}
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/billboard/addBB.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				long up = bbup.getTime();
				long down = bbdown.getTime();			
				long now = System.currentTimeMillis();
				Integer bbStatus = null;
				if(  now > up && now < down   ) {
					bbStatus  = 1; //上架
				}
				else if ( now < up ){
					bbStatus  = 0; //待上架
				}else {
					bbStatus = 2; //下架
				}
				

				/*************************** 2.開始新增資料 ***************************************/
				BillboardService bbSvc = new BillboardService();
				bbVO = bbSvc.addBB(url, pic,  bbup, bbdown,bbStatus);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String urlBack = "/back-end/billboard/listAllBillBoard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(urlBack);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/billboard/addBB.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requesURL");// 送出修改的來源請求

			try {

				/*************************** 1.接收請求參數 ****************************************/

				Integer bbID = new Integer(req.getParameter("bbID"));
				/*************************** 2.開始查詢資料 ****************************************/
				BillboardService bbSvc = new BillboardService();
				BillboardVO bbVO = bbSvc.findByPK(bbID);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

				req.setAttribute("bbVO", bbVO);
				String url = "/back-end/billboard/updateBB.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料取出無效" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");
			try {
				/***************************
				 * * 1.接收請求參數- 輸入格式的錯誤處理
				 ****************************************/
				Integer bbID = new Integer(req.getParameter("bbID"));

				String url = req.getParameter("url");
				String urlReg = "^[(?<http>(http:[/][/]|www.)([a-z]|[A-Z]|[0-9]|[/.]|[~])*)]";

				if (url.trim().length() == 0 || url == null) {
					errorMsgs.add("URL請勿空白");
				} else if (url.trim().matches(urlReg)) {
					errorMsgs.add("URL開頭必須是http且為英文字母和數字的字串");
				}

				SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				Timestamp bbup =null;
				
				try {
				java.util.Date sdate = sdf.parse(req.getParameter("bbStart"));		
				bbup = Timestamp.valueOf(sdf.format(sdate));
				}catch(IllegalArgumentException el) {
					errorMsgs.add("請輸入廣告開始時間");
				}
				
				Timestamp bbdown =null;
				try {
					java.util.Date edate = sdf.parse(req.getParameter("bbEnd"));		
					bbdown = Timestamp.valueOf(sdf.format(edate));
					}catch(IllegalArgumentException el) {
						errorMsgs.add("請輸入廣告結束時間");
					}

				byte[] pic = null;
				Part bbPic = req.getPart("bbPic");
//				InputStream inP = bbPic.getInputStream();
//				pic = new byte[inP.available()];
//				inP.read(pic);
//				inP.close();

				if (bbPic.getSubmittedFileName() == null || bbPic.getSubmittedFileName().trim().length() == 0) {
					BillboardService bbSvc = new BillboardService();
					BillboardVO bbVO = bbSvc.findByPK(bbID);
					pic = bbVO.getpic();
				}else {
					InputStream inP =  bbPic.getInputStream();
					pic = new byte[inP.available()];
					inP.read(pic);
					inP.close();
				}
				
				
				BillboardVO bbVO = new BillboardVO();
				bbVO.setbbID(bbID);
				bbVO.seturl(url);
				bbVO.setbbStart(bbup);
				bbVO.setbbEnd(bbdown);
				bbVO.setpic(pic);
				
				
				long up = bbup.getTime();
				long down = bbdown.getTime();			
				long now = System.currentTimeMillis();
				Integer bbStatus = null;
				if(  now > up && now < down   ) {
					bbStatus  = 1; //上架
				}
				else if ( now < up ){
					bbStatus  = 0; //待上架
				}else {
					bbStatus = 2; //下架
				}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("bbVO", bbVO); // 含有輸入格式錯誤的bbVO物件,也存入req

					for (String s : errorMsgs) {
						System.out.println(s);
					}
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/billboard/updateBB.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始修改資料 *****************************************/
				BillboardService bbSvc = new BillboardService();
				bbVO = bbSvc.updateBB(url, pic, bbup, bbdown, bbID,bbStatus);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("bbVO", bbVO);// 資料庫update成功後,正確的的bbVO物件,存入req
				String successUrl = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(successUrl);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/billboard/updateBB.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			// 1.接收請求參數
			try {
				Integer bbID = new Integer(req.getParameter("bbID"));

				// 2.開始刪除資料

				BillboardService bbSvc = new BillboardService();
				bbSvc.deleteBB(bbID);
				// 3.刪除完成，準備轉交

				String url = "/back-end/billboard/listAllBillBoard.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				// 其他可能錯誤處理

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗");
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}

		}

	}

}
