package com.roomType.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;

public class AjaxResRoomType extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public AjaxResRoomType() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		
		if ("getRoomTypeByBranch".equals(action)) {
			String braID = req.getParameter("braID");
			
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
		
		if ("getRoomTypeNumbers".equals(action)) {
			String braID = req.getParameter("braID");
			String checkInDay = req.getParameter("checkInDay");
			String checkOutDay = req.getParameter("checkOutDay");
			
			
			System.out.println("braID"+braID);
			System.out.println("checkOutDay"+checkOutDay);
			System.out.println("checkInDay"+checkInDay);
			
			List<RoomTypeVO> rtList = RoomTypeCompositeQuery.searchRoomTypeMinRoom(braID, checkInDay, checkOutDay);
			
			JSONArray array = new JSONArray();
			
			for(RoomTypeVO rtVO : rtList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("rtID", rtVO.getRtID());
					obj.put("balance", rtVO.getBalance());
				} catch (JSONException e) {
					System.out.println("json壞啦!");
					e.printStackTrace();
				}
				array.put(obj);
			}
			
			System.out.println(array.toString());
			
			res.setContentType("text/plain");
			res.setCharacterEncoding("UTF-8");
			PrintWriter out = res.getWriter();
			out.write(array.toString());
			out.flush();
			out.close();
			
		}
		
		if ("checkmemIDcard".equals(action)) {
			
			String keyMemIDcard = req.getParameter("question");
			System.out.println("ajax收到送進來的值:"+keyMemIDcard);
			
			MemberService memSvc = new MemberService();
			List<MemberVO> memList = memSvc.getAll();
			
			for(MemberVO memVO : memList) {
				String memIDCardinDB = memVO.getMemIDcard();
				
				if(memIDCardinDB.equals(keyMemIDcard)) {
					JSONObject obj = new JSONObject();
					try {
						obj.put("answer", memVO.getMemID());
						obj.put("answer2", memVO.getMemName());
					} catch (JSONException e) {
						System.out.println("json壞啦!");
						e.printStackTrace();
					}
					System.out.println(obj.toString());
					
					res.setContentType("text/plain");
					res.setCharacterEncoding("UTF-8");
					PrintWriter out = res.getWriter();
					out.write(obj.toString());
					out.flush();
					out.close();
				}
			}		
		}
		
		if ("updateEvaluates".equals(action)) {
			Integer evaluates = new Integer(req.getParameter("evaluates"));
			Integer odID = new Integer(req.getParameter("odID"));
			
			System.out.println("evaluates:"+evaluates);
			System.out.println("odID:"+odID);
			
			OrderDetailService odSvc = new OrderDetailService();
			/**判斷是否給過評價**/
			OrderDetailVO odVO = odSvc.getOneOrderDetail(odID);
			System.out.println("odVO.getEvaluates():"+odVO.getEvaluates());
			if(odVO.getEvaluates() != 0.0) {
				return;
			}
			
			odSvc.updateEvaluates(evaluates, odID);
			
			JSONObject obj = new JSONObject();
			try {
				obj.put("odID", odID);
				obj.put("evaluates", evaluates);
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
		
		
	}
}
