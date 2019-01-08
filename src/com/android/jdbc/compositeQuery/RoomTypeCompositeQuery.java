package com.android.jdbc.compositeQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.android.roomType.model.RoomTypeBra;
import com.android.roomType.model.RoomTypeDAO;
import com.android.roomType.model.RoomTypeDAO_interface;
import com.android.roomType.model.RoomTypeJDBCDAO;
import com.android.roomType.model.RoomTypeService;
import com.android.roomType.model.RoomTypeVO;
import com.android.tool.ProcDate;

import sun.nio.ch.DatagramSocketAdaptor;

// for oracle
public class RoomTypeCompositeQuery {

	public static List<RoomTypeBra> searchRoomTypeRange(List<Integer> room, List<String> balance, String braName, String checkinStr, String checkoutStr) {
		
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
		
		//test
//		RoomTypeDAO_interface dao = new RoomTypeJDBCDAO();
//		List<RoomTypeBra> list = dao.findByBraName(braName);    // 連線池
		//test
		
		roomsNum = new int[totalDay];
		RoomTypeService rtSvc = new RoomTypeService();
		List<RoomTypeBra> list = null;
		list = rtSvc.getRoomTypeByBraName(braName);
		
		System.out.println(list);
		
		for(int rtIndex = 0; rtIndex < list.size(); rtIndex++) {    //滾迴圈找房型
			// 相同房型時的判斷
			int minRooms = room.get(rtIndex);          //房型最小間數
			if(totalDay == 1) {      //只住一天
				roomsNum[0] = ProcDate.getRoomByDate(checkinDay, balance.get(rtIndex));   //撈出所有房型, 某日期區間, 判斷是否有房間
				System.out.println("剩 " + roomsNum[0] + " 間");
			} else {
				for(int i = 0; i < roomsNum.length; i++) {   //記錄入區間的房間剩餘數量[]
					
					if(checkinDay + i > checkinDayofMonth) {   //入住日期 > 當月最大日期
						checkinDay = 0;
					}
					
					roomsNum[i] = ProcDate.getRoomByDate(checkinDay + i, balance.get(rtIndex));    //取得某日期的間數(日期, 62byte)
					System.out.println(checkinDay + i + "號" + "剩 " + roomsNum[i] + " 間");
					minRooms = Math.min(minRooms, roomsNum[i]);   //找出某房型某區間, 最少間數
				}
				System.out.println("最少間數 " + minRooms);  
			}
			list.get(rtIndex).setRooms(minRooms);     // 最少間數
		}
		System.out.println("-----------------------------------------------------");
	
		for(int i = 0; i < list.size(); i++) {
			System.out.println("分店編號" + list.get(i).getBraID());
			System.out.println("房型編號" + list.get(i).getRtID());
			System.out.println("房型名稱"+  list.get(i).getRtName());
			System.out.println("定價" + list.get(i).getHolidayPrice());
			System.out.println("剩餘間數" + list.get(i).getRooms());
			System.out.println("=====================");
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		
		String braName = "麻翔";
		String checkinDate = "2018-01-31";
		String checkoutDate = "2018-02-02";
		
//		RoomTypeService roomTypeSvc = new RoomTypeService();
//		List<RoomTypeBra> roomTypeList = roomTypeSvc.getRoomTypeByBraName(braName);    // 連線池
		
		RoomTypeDAO_interface dao = new RoomTypeJDBCDAO();
		List<RoomTypeBra> roomTypeList = dao.findByBraName(braName);    // 連線池
		
		List<Integer> maxRooms = new ArrayList<Integer>();    //找出房型的最大房間數
		List<String> balance = new ArrayList<String>();       //找出房型的 62byte
		for(RoomTypeBra rtVO : roomTypeList) {
			maxRooms.add(rtVO.getTotal());
			balance.add(rtVO.getBalance());    				
		}
		System.out.println(maxRooms);
		System.out.println(balance);
		
		roomTypeList = searchRoomTypeRange(maxRooms, balance, braName, checkinDate, checkoutDate);

		for(int i = 0; i < roomTypeList.size(); i++) {
			System.out.println("分店編號" + roomTypeList.get(i).getBraID());
			System.out.println("房型編號" + roomTypeList.get(i).getRtID());
			System.out.println("房型名稱"+  roomTypeList.get(i).getRtName());
			System.out.println("定價" + roomTypeList.get(i).getHolidayPrice());
			System.out.println("剩餘間數" + roomTypeList.get(i).getBalance());
			System.out.println("=====================");
		}
	}
}



