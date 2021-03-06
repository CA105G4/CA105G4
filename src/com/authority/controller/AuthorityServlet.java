package com.authority.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.authority.model.*;
import com.authorityRecord.model.*;

public class AuthorityServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String action = req.getParameter("action");

		                        
		if ("listEmps_ByAuthID_A".equals(action) ) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer authID = new Integer(req.getParameter("authID"));

				/*************************** 2.�}�l�d�߸�� ****************************************/
				AuthorityService authIDSvc = new AuthorityService();
				Set<AuthorityRecordVO> set = authIDSvc.getEmpsByAuthID(authID);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("listEmps_ByAuthID", set);    // ��Ʈw���X��set����,�s�Jrequest
				req.setAttribute("authID", authID);

				String url = null;
				if ("listEmps_ByAuthID_A".equals(action))
					url = "/back-end/authority/listEmp_ByAuthID.jsp";        // ���\��� dept/listEmps_ByDeptno.jsp
				
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z ***********************************/
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		
	

	}
}
