package listener;

import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Initialize_Map_Listener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext context = event.getServletContext();
		
		Map<Integer, String> ordStateMap = new HashMap<Integer, String>();
		ordStateMap.put(0, "預訂");
		ordStateMap.put(1, "入住");
		ordStateMap.put(2, "退房");
		ordStateMap.put(3, "退訂");
		context.setAttribute("ordStateMap", ordStateMap);
		
		Map<Integer, String> paymentMap = new HashMap<Integer, String>();
		paymentMap.put(0, "現金");
		paymentMap.put(1, "信用卡");
		context.setAttribute("paymentMap", paymentMap);
		
		Map<Integer, String> ordTypeMap = new HashMap<Integer, String>();
		ordTypeMap.put(0, "線上");
		ordTypeMap.put(1, "臨櫃");
		ordTypeMap.put(2, "打工換宿");
		context.setAttribute("ordTypeMap", ordTypeMap);
		
		Map<Integer, String> specialMap = new HashMap<Integer, String>();
		specialMap.put(0, "不加床");
		specialMap.put(1, "加床");
		context.setAttribute("specialMap", specialMap);
		
		//房間
		Map<Integer, String> roomStateMap = new HashMap<Integer, String>();
		roomStateMap.put(1, "空房");
		roomStateMap.put(2, "入住");
		roomStateMap.put(3, "打掃");
		roomStateMap.put(4, "維修");
		roomStateMap.put(5, "保留");
		context.setAttribute("roomStateMap", roomStateMap);
		
		//分店
		Map<Integer, String> braStateMap = new HashMap<Integer, String>();
		braStateMap.put(0, "關店");
		braStateMap.put(1, "正常營業");
		context.setAttribute("braStateMap", braStateMap);
		
		//優惠劵
		Map<Integer, String> cpnStateMap = new HashMap<Integer, String>();
		cpnStateMap.put(0, "未使用");
		cpnStateMap.put(1, "已使用");
		context.setAttribute("cpnStateMap", cpnStateMap);
		
		//打工申請需求紀錄
		Map<Integer, String> werStateMap = new HashMap<Integer, String>();
		werStateMap.put(0, "待審核");
		werStateMap.put(1, "通過");
		werStateMap.put(2, "不通過");
		context.setAttribute("werStateMap", werStateMap);
		
		//員工
		Map<Integer, String> empStateMap = new HashMap<Integer, String>();
		empStateMap.put(0, "離職");
		empStateMap.put(1, "在職");
		context.setAttribute("empStateMap", empStateMap);
		
		//留言檢舉
		Map<Integer, String> mrStateMap = new HashMap<Integer, String>();
		mrStateMap.put(0, "待審核");
		mrStateMap.put(1, "通過");
		mrStateMap.put(2, "駁回");
		context.setAttribute("mrStateMap", mrStateMap);
		
		//留言
		Map<Integer, String> msgStateMap = new HashMap<Integer, String>();
		msgStateMap.put(0, "顯示");
		msgStateMap.put(1, "隱藏");
		context.setAttribute("msgStateMap", msgStateMap);
		
		//文章檢舉
		Map<Integer, String> repStateMap = new HashMap<Integer, String>();
		repStateMap.put(0, "待審核");
		repStateMap.put(1, "通過");
		repStateMap.put(2, "駁回");
		context.setAttribute("repStateMap", repStateMap);
		
		//分享文章
		Map<Integer, String> artStateMap = new HashMap<Integer, String>();
		artStateMap.put(0, "隱藏");
		artStateMap.put(1, "顯示");
		context.setAttribute("artStateMap", artStateMap);
		
		//會員(狀態)
		Map<Integer, String> memStateMap = new HashMap<Integer, String>();
		memStateMap.put(0, "未認證");
		memStateMap.put(1, "認證");
		context.setAttribute("memStateMap", memStateMap);
		
		//會員(男女)
		Map<String, String> memSexMap = new HashMap<String, String>();
		memSexMap.put("M", "男");
		memSexMap.put("F", "女");
		context.setAttribute("memSexMap", memSexMap);
		
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		//do nothing;
	}

}
