	package com.workExchange.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.workExchange.model.WorkExchangeService;
import com.workExchange.model.WorkExchangeVO;

import sendEmail.MailService;

@WebServlet("/workExchange.do")
@MultipartConfig
public class WorkExchangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		Map<String,String> errMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errMsgs", errMsgs);
		
		//來自listAllWorkExchange的請求----detail
		if("getOne_WEDET".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
			WorkExchangeService weSvc = new WorkExchangeService();
			WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
			req.setAttribute("workExchangeVO", workExchangeVO);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchange/listWorkExchangeDET.jsp");
			success.forward(req,res);
		}

		//來自listAllWorkExchange的請求----verify
		if("choose_member".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
			WorkExchangeService weSvc = new WorkExchangeService();
			WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
			req.setAttribute("workExchangeVO", workExchangeVO);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchangeRecord/listWorkExchangeRecordByWE.jsp");
			success.forward(req, res);
		}
		
		
		
		
		//來自listWorkExchangeDET的請求
		if("getOne_For_Update".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
//			System.out.println(weID);
			WorkExchangeService weSvc = new WorkExchangeService();
			WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
			req.setAttribute("workExchangeVO", workExchangeVO);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchange/updateOneWorkExchange.jsp");
			success.forward(req,res);
		}
		
//		System.out.println("沒進來");
//		System.out.println(action);
		//來自listWorkExchangeDET的請求 ----->發送打工邀請
		if("search_Mem_By_WeName".equals(action)) {
			String weName = req.getParameter("weName");
			System.out.println(weName);
			req.setAttribute("weName", weName);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchange/listMemBySkill.jsp");
			success.forward(req,res);
		}
		
		
		
		
		//來自updateOneWorkExchange的請求
		if("confirm_Modify".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
			String empID = req.getParameter("empID");
			String memID = req.getParameter("memID");
			String rtID = req.getParameter("rtID");
			String weName = req.getParameter("weName");
			String weContent = req.getParameter("weContent");
			
			byte[] wePic = null;
			Part part = req.getPart("wePic");
			if(part == null || part.getSubmittedFileName().length() == 0) {
				WorkExchangeService weSvc = new WorkExchangeService();
				WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
				wePic = workExchangeVO.getWePic();
			}else {
				InputStream in = part.getInputStream();
				wePic = new byte[in.available()];
				in.read(wePic);
				in.close();
			}
			
			byte[] weVideo = null;
			Part part2 = req.getPart("weVideo");
			if(part2 == null || part2.getSubmittedFileName().length() == 0) {
				WorkExchangeService weSvc = new WorkExchangeService();
				WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
				weVideo = workExchangeVO.getWeVideo();
			}else {
				InputStream in = part2.getInputStream();
				weVideo = new byte[in.available()];
				in.read(weVideo);
				in.close();
			}
			
			String start = req.getParameter("weStart");
			Date weStart = Date.valueOf(start);
			
			String end = req.getParameter("weEnd");
			Date weEnd = Date.valueOf(end);
		
			WorkExchangeService weSvc = new WorkExchangeService();
			WorkExchangeVO workExchangeVO = weSvc.updateWE(weID,empID,memID,rtID,weName,weContent,wePic,weVideo,weStart,weEnd);
			req.setAttribute("workExchangeVO", workExchangeVO);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchange/listWorkExchangeDET.jsp");
			success.forward(req,res);
		} 
		
		//來自addOneWorkExchange的請求
		if("confirm_Add".equals(action)) {
//			Integer weID = new Integer(req.getParameter("weID"));
			String empID = req.getParameter("empID");
			String memID = req.getParameter("memID"); //審核通過可以查的到
			String rtID = req.getParameter("rtID");
			
			String weName = req.getParameter("weName");
			if(weName == null || weName.trim().length() == 0) {
				errMsgs.put("weName", "需求名稱不得為空");
			}
			
			String weContent = req.getParameter("weContent");
			if(weContent == null || weContent.trim().length() == 0) {
				errMsgs.put("weContent", "需求內容不得為空");
			}
			
			byte[] wePic = null;
			Part part = req.getPart("wePic");
			if(part == null || part.getSubmittedFileName().length() == 0) {
				errMsgs.put("wePic", "請選擇一張照片");
			}else {
				InputStream in = part.getInputStream();
				wePic = new byte[in.available()];
				in.read(wePic);
				in.close();
			}
			
			byte[] weVideo = null;
			Part part2 = req.getPart("weVideo");
			if(part2 == null || part2.getSubmittedFileName().length() == 0) {
				errMsgs.put("weVideo", "請選擇一支影片");
			}else {
				InputStream in = part2.getInputStream();
				weVideo = new byte[in.available()];
				in.read(weVideo);
				in.close();
			}
			
			java.sql.Date weStart = null;
			try {
				weStart = java.sql.Date.valueOf(req.getParameter("weStart").trim());
			} catch (IllegalArgumentException e) {
				weStart = new java.sql.Date(System.currentTimeMillis());
			}
			
			java.sql.Date weEnd = null;
			try {
				weEnd = java.sql.Date.valueOf(req.getParameter("weEnd").trim());
			} catch(IllegalArgumentException e){
				weEnd = new java.sql.Date(System.currentTimeMillis());
			}
			//給錯誤處理存下輸入的VO
			WorkExchangeVO workExchangeVO = new WorkExchangeVO();
			workExchangeVO.setEmpID(empID);
			workExchangeVO.setMemID(memID);
			workExchangeVO.setRtID(rtID);
			workExchangeVO.setWeName(weName);
			workExchangeVO.setWeContent(weContent);
			workExchangeVO.setWePic(wePic);
			workExchangeVO.setWeVideo(weVideo);
			workExchangeVO.setWeStart(weStart);
			workExchangeVO.setWeEnd(weEnd);
			
			//錯誤返回原頁面
			if (!errMsgs.isEmpty()) {
				req.setAttribute("workExchangeVO", workExchangeVO);
				RequestDispatcher failure = req.getRequestDispatcher("/back-end/workExchange/addOneWorkExchange.jsp");
				failure.forward(req, res);
				return;
			}
			//成功的話轉交
			WorkExchangeService weSvc = new WorkExchangeService();
			weSvc.addWE(memID, empID, rtID, weName, weContent, wePic, weVideo, weStart, weEnd);
			RequestDispatcher success = req.getRequestDispatcher("/back-end/workExchange/listAllWorkExchange.jsp");
			success.forward(req, res);
		}
		
		//來自listAllWE的請求
		if("displayDetail_FromFrontEnd".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
			WorkExchangeService weSvc = new WorkExchangeService();
			WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
			req.setAttribute("workExchangeVO", workExchangeVO);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/workExchange/listWEDetail.jsp");
			success.forward(req,res);
		}
		
		//來自listWEDetail的請求
		if("apply_WE".equals(action)) {
			Integer weID = new Integer(req.getParameter("weID"));
			WorkExchangeService weSvc = new WorkExchangeService();
			WorkExchangeVO workExchangeVO = weSvc.getOneWE(weID);
			req.setAttribute("workExchangeVO", workExchangeVO);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/workExchangeRecord/addWER.jsp");
			success.forward(req,res);
		}
		
		System.out.println(action);
		//來自listMemBySkill的請求
		if("send_Email".equals(action)) {
			System.out.println("進來了");
			String memID = req.getParameter("memID");
			System.out.println("===========\n"+memID);
			MemberService memSvc = new MemberService();
			MemberVO memberVO = memSvc.getOneMem(memID);
			String memName = memberVO.getMemName();
			String memEmail = memberVO.getMemEmail();
			
			MailService ms = new MailService();
			String to = "superivanshang@gmail.com";
			String subject = "打工換宿邀請";
			String messageText = "親愛的"+memName+"會員您好，目前有新的打工需求，如有意願，我們都在這等您的到來^_^";
			
			ms.sendMail(to, subject, messageText);
			JSONObject jobj = new JSONObject();
			try {
				jobj.put("memID", memID);
				jobj.put("memName", memName);
				jobj.put("memEmail", memEmail);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(jobj.toString());
			out.flush();
			out.close();
		}
		
	}

}
