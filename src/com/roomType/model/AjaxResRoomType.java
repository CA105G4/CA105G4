package com.roomType.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/** Gina因缺少JSON的jar檔所以會紅字，待整合時再加入 **/
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AjaxResRoomType extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public AjaxResRoomType() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		String braID = req.getParameter("braID");
		
		if ("getRoomTypeByBranch".equals(action)) {
			RoomTypeService rtSvc = new RoomTypeService();
			List<RoomTypeVO> rtList = null;
			rtList = rtSvc.findRoomTypeByBraID(braID);
			
//			for(RoomTypeVO rtVO:rtList) {	//測試是否有收到jsp中的Json
//				System.out.println(rtVO.getRtID());
//				System.out.println(rtVO.getRtName());
//			}

			JSONArray array = new JSONArray();
			
			for (RoomTypeVO rtVO : rtList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("rtID", rtVO.getRtID());
					obj.put("rtName", rtVO.getRtName());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				array.put(obj);
			}
			
//			System.out.println(array.toString());	//測試是否有順利把值放入Json Array
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
		}
	}
}
