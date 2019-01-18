package scheduleServlet;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.roomType.model.RoomTypeService;
import com.roomType.model.RoomTypeVO;

public class ScheduleServlet extends HttpServlet{

	Timer timer; 
	
	public void init() throws ServletException {
		
		Calendar cal = new GregorianCalendar(2019,0,6,23,59,0);	//今天	2019,0,6,23,59,0
		
		TimerTask task = new TimerTask() {
			public void run() {
				Calendar cal = new GregorianCalendar();	
				int todayDate = cal.get(Calendar.DATE);
				System.out.println("todayDate:"+todayDate);
				
				RoomTypeService rtSvc = new RoomTypeService();
				List<RoomTypeVO> rtlist = rtSvc.getAll();
				for(RoomTypeVO rtVO : rtlist) {
					RoomTypeVO rtVO2 = rtSvc.getOneRoomType(rtVO.getRtID());
//					System.out.println("拿出的房型為"+rtVO.getRtID());
					
					String beforeBalance = rtVO2.getBalance();
//					System.out.println("當天的Balance"+ beforeBalance);
					
					Integer total = rtVO2.getTotal();
//					System.out.println("該房型的原房間數量"+total);
					
					StringBuffer afterbalance = new StringBuffer(beforeBalance);
					
					String roomnumstr = null;
					if(total<10) {
						roomnumstr = "0" + total.toString();
					}else {
						roomnumstr = total.toString();
					}	
//					System.out.println("要改變的房型數量為:"+roomnumstr);
					
					int todaybindex = (todayDate*2)-2;
					int todayeindex = todayDate*2;
				
					afterbalance.replace(todaybindex, todayeindex, roomnumstr);
//					System.out.println("rt新的剩餘房間術欄位" + afterbalance);
					
					rtSvc.returnRoomNum(afterbalance.toString(), rtVO.getRtID());
//					System.out.println("已成功執行排程器的RUN方法");
				}
			}
		};
		
		timer = new Timer();
//		timer.scheduleAtFixedRate(task, cal.getTime(), 1 *24* 60 * 60 * 1000);	   //測試用, 會追朔
		timer.schedule(task, cal.getTime(), 1 *24* 60 * 60 * 1000);	               //不會追朔
		System.out.println("已建立排程!");
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	}

	public void destroy() {
		timer.cancel();
		System.out.println("已移除排程!");
	}
	
}
