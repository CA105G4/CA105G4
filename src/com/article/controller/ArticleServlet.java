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
		
		
		if ("getOne_For_Display".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("artid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入文章編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				Integer artid = null;
				try {
					artid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("文章編號不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(artid);
				if (articleVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/article/listOneArticle.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer artid = new Integer(req.getParameter("artid"));
				
			    ArticleService articleService = new ArticleService();
				ArticleVO articleVO = articleService.getOneArticle(artid);
								
				req.setAttribute("articleVO", articleVO);        
				String url = "/front-end/article/update_article_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/listAllArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
			try {
				Integer artid = new Integer(req.getParameter("artid").trim());
				
				String memid = req.getParameter("memid");
				if (memid == null || memid.trim().length() == 0) {
					errorMsgs.add("會員編號: 請勿空白");
				}
				
				byte[] artpic =null;
				Part part = req.getPart("artpic");
				try {
					String filename = part.getSubmittedFileName();
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

				ArticleVO articleVO = new ArticleVO();
				articleVO.setArtid(artid);
				articleVO.setMemid(memid);
				articleVO.setArtpic(artpic);
				articleVO.setArtexp(artexp);
				articleVO.setArtstate(artstate);
				articleVO.setArtdate(artdate);
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/update_article_input.jsp");
					failureView.forward(req, res);
					return; 
				}
				
				ArticleService articleService = new ArticleService();
				articleVO = articleService.updateAtricle(artid, memid, artpic, artexp, artstate, artdate);
				
				req.setAttribute("articleVO", articleVO); 
				String url = "/article/article.do?memid=" + memid + "&action=get_Member_Display";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/update_article_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
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

				Integer artstate = new Integer(req.getParameter("artstate").trim());
				

				ArticleVO articleVO = new ArticleVO();
				articleVO.setMemid(memid);
				articleVO.setArtpic(artpic);
				articleVO.setArtexp(artexp);
				articleVO.setArtstate(artstate);
				articleVO.setArtdate(artdate);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("articleVO", articleVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ArticleService articleService = new ArticleService();
				articleVO = articleService.addArticle(memid, artpic, artexp, artstate, artdate);
				String url = "/article/article.do?memid=" + memid + "&action=get_Member_Display";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
				
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/addNewArticle.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("delete".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
			try {
				Integer artid = new Integer(req.getParameter("artid"));
				
				ArticleService articleService = new ArticleService();
				articleService.deleteArticle(artid);
				
				String url = "/front-end/article/myExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/article/myExperience.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("get_Member_Display".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				String memid = req.getParameter("memid").trim();
				if (memid == null || memid.trim().length() == 0) {
					errorMsgs.add("會員編號有誤");
				}
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				ArticleService articleSvc = new ArticleService();
				List<ArticleVO> list = articleSvc.getMemberArticle(memid);
				if (articleSvc == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/article/select_page.jsp");
					failureView.forward(req, res);
					return;
				}
				
				req.setAttribute("list", list); 
				String url = "/front-end/article/myExperience.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/article/select_page.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
