package com.android.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.sql.Date;
import java.util.List;

import org.json.JSONArray;

import com.android.roomType.model.RoomTypeBra;
import com.android.roomType.model.RoomTypeJDBCDAO;
import com.android.roomType.model.RoomTypeService;
import com.android.roomType.model.RoomTypeVO;

public class ToolTest {

	public static void main(String[] args) {

		System.out.println(ProcDate.getCal("2018-05-12", ProcDate.YEAR));
		System.out.println(ProcDate.getCal("2018-05-12", ProcDate.MONTH));
		System.out.println(ProcDate.getCal("2018-05-12", ProcDate.DAY));
	
		int room = 10;    //原本間數
		System.out.println(ProcDate.insertBalance(room));
		String balance[] = {"10081010101005080310100310101010101010081010101010100107100608", 
							"10081010101007060801040310101010101010081010101010100107100608"};
		System.out.println(balance);
		
		System.out.println(ProcDate.getRoomByDate(7, balance[0]));  //取得對應日期的間數
		System.out.println(ProcDate.getRoomByDate(8, balance[1]));  //取得對應日期的間數
		
		System.out.println("----------------------------------------------------------");
		for(int day = 1; day <= 31; day++) {
			System.out.println(day + "號 " + ProcDate.getRoomByDate(day, balance[0]));
			System.out.println(day + "號 " + ProcDate.getRoomByDate(day, balance[1]));
			System.out.println("========");
		}
		
		System.out.println("----------------------------------------------------------");
		
		String checkinStr =  "2018-10-07";
		String checkoutStr = "2018-10-10";
		
		int checkinYear = ProcDate.getCal(checkinStr, ProcDate.YEAR);
		int checkinMonth = ProcDate.getCal(checkinStr, ProcDate.MONTH);
		int checkinDay = ProcDate.getCal(checkinStr, ProcDate.DAY);
		
		Date checkin = Date.valueOf(checkinStr);
		Date checkout = Date.valueOf(checkoutStr);    
		int[] roomsNum = null;
				
		Calendar checkinCal = new GregorianCalendar(checkinYear, checkinMonth-1, checkinDay);
		int checkinDayofMonth = checkinCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println("GregorianCalendar:" + checkinCal.get(Calendar.YEAR) + "年" + (checkinCal.get(Calendar.MONTH)+1) + "月" + checkinCal.get(Calendar.DATE));
		System.out.println("入住當月天數：" + checkinDayofMonth);
		System.out.println("=======================");
		
		
		System.out.println(checkin  + "  " + checkin.getTime());
		System.out.println(checkout + "  " + checkout.getTime());
		
		int totalDay = (int)((checkout.getTime() - checkin.getTime()) / 1000 / 60 / 60 / 24);   //入住幾天
		
		System.out.println("checkin  日期 " + checkin);     //轉成 string 型態 
		System.out.println("checkout 日期 " + checkout);
		System.out.println("住 " + totalDay + " 天");
		
		roomsNum = new int[totalDay];
		int minRooms = room;
		
//		if(totalDay == 1) {      //只住一天
//			roomsNum[0] = ProcDate.getRoomByDate(checkinDay, balance);   //撈出所有房型, 某日期區間, 判斷是否有房間
//			System.out.println("剩 " + roomsNum[0] + " 間");
//		} else {
//			for(int i = 0; i < roomsNum.length; i++) {   //記錄入區間的房間剩餘數量[]
//				
//				if(checkinDay + i > checkinDayofMonth) {   //入住日期 > 當月最大日期
//					checkinDay = 0;
//				}
////				
//				roomsNum[i] = ProcDate.getRoomByDate(checkinDay + i, balance);    //取得某日期的間數(日期, 62byte)
//				System.out.println(checkinDay + i + "號" + "剩 " + roomsNum[i] + " 間");
//				minRooms = Math.min(minRooms, roomsNum[i]);   //找出某房型某區間, 最少間數
//			}
//			System.out.println("最少間數 " + minRooms);   
//		}
	
		
		List<RoomTypeBra> list = new ArrayList<RoomTypeBra>();
		
		for(int rtIndex = 0; rtIndex < list.size(); rtIndex++) {    //滾迴圈找房型
			// 相同房型時的判斷
			if(totalDay == 1) {      //只住一天
				roomsNum[0] = ProcDate.getRoomByDate(checkinDay, balance[rtIndex]);   //撈出所有房型, 某日期區間, 判斷是否有房間
				System.out.println("剩 " + roomsNum[0] + " 間");
			} else {
				for(int i = 0; i < roomsNum.length; i++) {   //記錄入區間的房間剩餘數量[]
					
					if(checkinDay + i > checkinDayofMonth) {   //入住日期 > 當月最大日期
						checkinDay = 0;
					}
	//				
					roomsNum[i] = ProcDate.getRoomByDate(checkinDay + i, balance[rtIndex]);    //取得某日期的間數(日期, 62byte)
					System.out.println(checkinDay + i + "號" + "剩 " + roomsNum[i] + " 間");
					minRooms = Math.min(minRooms, roomsNum[i]);   //找出某房型某區間, 最少間數
				}
				System.out.println("最少間數 " + minRooms);   
			}
		}

		
		System.out.println("---------------------------------------------------");
		
//		rooms = ProcDate.getRoomByDate(checkinDay, balance); //撈出所有房型, 某日期區間, 判斷是否有空房
//		System.out.println(checkinDay + " 號, " + rooms + " 間");

//		RoomTypeService rtSvc = new RoomTypeService();		
//		list = rtSvc.getRoomTypeByBraName("麻翔");
		RoomTypeJDBCDAO dao = new RoomTypeJDBCDAO();
		list = dao.findByBraName("麻翔");
		for(RoomTypeBra rtVO : list) {
			rtVO.setRooms(minRooms);         //填補 每間最少間數
		}
	
		for(int i = 0; i < list.size(); i++) {
			System.out.println("分店編號" + list.get(i).getBraID());
			System.out.println("房型編號" + list.get(i).getRtID());
			System.out.println("房型名稱"+  list.get(i).getRtName());
			System.out.println("定價" + list.get(i).getHolidayPrice());
			System.out.println("剩餘間數" + list.get(i).getRooms());
			System.out.println("=====================");
		}
	}
	
}
