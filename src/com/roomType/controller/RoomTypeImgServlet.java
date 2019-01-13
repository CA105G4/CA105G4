package com.roomType.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomType.model.*;

public class RoomTypeImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String rtID =req.getParameter("rtID");
			
			
			
			
			
			RoomTypeService rtSvc = new RoomTypeService();
			byte[]pic = rtSvc.getOneRoomType(rtID).getRtPic();
			
			
			
			
			ServletOutputStream out =res.getOutputStream();
			res.setContentLength(pic.length);
			res.setContentType("image/*");
			
			out.write(pic);
			out.close();
	}

}
