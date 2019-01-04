package com.article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.article.model.*;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 8 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ArticleServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("artid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer artid = null;
				try {
					artid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(artid);
				if (articleVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer artid = new Integer(req.getParameter("artid"));
				
				/***************************2.開始查詢資料****************************************/
			    ArticleService articleService = new ArticleService();
				ArticleVO articleVO = articleService.getOneArticle(artid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("articleVO", articleVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer artid = new Integer(req.getParameter("artid").trim());
				
				String memid = req.getParameter("memid");
//				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memid == null || memid.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				
				byte[] artpic =null;
				Part part = req.getPart("artpic");
				try {
					String filename = part.getSubmittedFileName();
//					System.out.println(filename);
					if (filename != "" && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						artpic = new byte[in.available()];
						in.read(artpic);
						in.close();
					}else {
						ArticleService articleService = new ArticleService();
						artpic = articleService.getOneArticle(artid).getArtpic();
					}
					
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}
				

//				InputStream artpic = req.getPart("artpic").getInputStream();
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			    byte buf[] = new byte[8192];
//			    int qt = 0;
//			    while ((qt = artpic.read(buf)) != -1) {
//			      baos.write(buf, 0, qt);
//			    }

//				byte[] artpic = null;
//				System.out.println(artpic.toString());
				
				
				
				String artexp = req.getParameter("artexp").trim();
				if (artexp == null || artexp.trim().length() == 0) {
					errorMsgs.add("文章請勿空白");
				}	
				
				java.sql.Date artdate = null;
				try {
					artdate = java.sql.Date.valueOf(req.getParameter("artdate").trim());
				} catch (IllegalArgumentException e) {
					artdate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer artstate = null;
				try {
					artstate = new Integer(req.getParameter("artstate").trim());
				} catch (NumberFormatException e) {
					artstate = 0;
					errorMsgs.add("請填狀態代碼.");
				}
//
//				Double comm = null;
//				try {
//					comm = new Double(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}

				
				ArticleVO articleVO = new ArticleVO();
				articleVO.setArtid(artid);
				articleVO.setMemid(memid);
				articleVO.setArtpic(artpic);
				articleVO.setArtexp(artexp);
				articleVO.setArtstate(artstate);
				articleVO.setArtdate(artdate);
				

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/update_article_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				ArticleService articleService = new ArticleService();
				articleVO = articleService.updateAtricle(artid, memid, artpic, artexp, artstate, artdate);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("articleVO", articleVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/update_article_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//				String artid = req.getParameter("artid");
//				if (artid == null || artid.trim().length() == 0) {
//					errorMsgs.add("文章編號有誤");
//				}
//				String artidReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (artid == null || ename.trim().length() == 0) {
//					errorMsgs.add("員工姓名: 請勿空白");
//				} else if(!ename.trim().matches(enameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//	            }
				
				String memid = req.getParameter("memid").trim();
				if (memid == null || memid.trim().length() == 0) {
					errorMsgs.add("會員編號有誤");
				}
				
				byte[] artpic =null;
				Part part = req.getPart("artpic");
				try {
					String filename = part.getSubmittedFileName();
					if (filename != null && part.getContentType() != null) {
						InputStream in = part.getInputStream();
						artpic = new byte[in.available()];
						in.read(artpic);
						in.close();
					}
				} catch (FileNotFoundException fe) {
					fe.printStackTrace();
				}

				
				String artexp = req.getParameter("artexp").trim();
				if (artexp == null || artexp.trim().length() == 0) {
					errorMsgs.add("文章請勿空白");
				}	
				
				java.sql.Date artdate =java.sql.Date.valueOf(req.getParameter("artdate").trim());;
//				try {
//					artdate = java.sql.Date.valueOf(req.getParameter("artdate").trim());
//				} catch (IllegalArgumentException e) {
//					artdate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

				Integer artstate = new Integer(req.getParameter("artstate").trim());
//				try {
//					artstate = new Integer(req.getParameter("artstate").trim());
//				} catch (NumberFormatException e) {
//					artstate = 0;
//					errorMsgs.add("請填狀態代碼.");
//				}
				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setMemid(memid);
				articleVO.setArtpic(artpic);
				articleVO.setArtexp(artexp);
				articleVO.setArtstate(artstate);
				articleVO.setArtdate(artdate);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				ArticleService articleService = new ArticleService();
				articleVO = articleService.addArticle(memid, artpic, artexp, artstate, artdate);
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/back-end/article/listAllArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				/***************************1.接收請求參數***************************************/
				Integer artid = new Integer(req.getParameter("artid"));
				
				/***************************2.開始刪除資料***************************************/
				ArticleService articleService = new ArticleService();
				articleService.deleteArticle(artid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/article/myExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/myExperience.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("get_Member_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//				String str = req.getParameter("memid");
//				if (str == null || (str.trim()).length() == 0) {
//					errorMsgs.add("請輸入文章編號");
//				}
//				// Send the use back to the form, if there were errors
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req
//							.getRequestDispatcher("/article/select_page.jsp");
//					failureView.forward(req, res);
//					return;//程式中斷
//				}
				
				String memid = req.getParameter("memid").trim();
				if (memid == null || memid.trim().length() == 0) {
					errorMsgs.add("會員編號有誤");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> list = articleSvc.getMemberArticle(memid);
//				System.out.println(articleSvc.getMemberArticle(memid));
				if (articleSvc == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("list", list); // 資料庫取出的empVO物件,存入req
				String url = "/front-end/article/myExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
