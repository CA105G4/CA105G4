package com.roomType.model;

import java.sql.Date;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomTypeCompositeQuery {

	public static final int YEAR = 0;
	public static final int MONTH = 1;
	public static final int DAY = 2;
	
	public RoomTypeCompositeQuery() {
	}
	
	public static int getCal(Date date, int state) {
		
		String dateString = date.toString();
		
		if(state == YEAR) {
			return new Integer(dateString.substring(0,4));
		} else if(state == MONTH) {
			return new Integer(dateString.substring(5,7));
		} else if(state == DAY) {
			return new Integer(dateString.substring(8));
		} 
		
		return 0;
	}
	
	public static int getCal(String dateString, int state) {
		
		if(state == YEAR) {
			return new Integer(dateString.substring(0,4));
		} else if(state == MONTH) {
			return new Integer(dateString.substring(5,7));
		} else if(state == DAY) {
			return new Integer(dateString.substring(8));
		} 
		
		return 0;
	}
	
	public static String insertBalance(int total) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < 31; i++) {   //
			if(total > 9) 
				sb.append(total);
			else
				sb.append("0" + total);
//			System.out.println(sb.toString() + " => " + (i + 1));  // 31天
		}
		
		return sb.toString();
	}
	
public static int getRoomByDate(int date, String balance) {
		
		int dateIndex = date * 2 - 2;
		int rooms = 0;
		
		dateIndex = (dateIndex < 0 ) ? 0 : dateIndex;
		
		String dayStr = null;
		if(date == 31) {
			dayStr = balance.substring(dateIndex);
		}
		else {
//			System.out.println(dateIndex);
//			System.out.println(dateIndex + 2);
			dayStr = balance.substring(dateIndex, dateIndex + 2);
//			System.out.println("dayStr = " + dayStr);
		}
//		System.out.println(dayStr);
		rooms = new Integer(dayStr);
		
		return rooms;
	}


	public static List<RoomTypeVO> searchRoomTypeMinRoom(String braId, String checkinStr, String checkoutStr) {
		
		int checkinYear = getCal(checkinStr, YEAR);
		int checkinMonth = getCal(checkinStr, MONTH);
		int checkinDay = getCal(checkinStr, DAY);
		
		Date checkin = Date.valueOf(checkinStr);
		Date checkout = Date.valueOf(checkoutStr);    
		int[] roomsNum = null;
				
		Calendar checkinCal = new GregorianCalendar(checkinYear, checkinMonth - 1, checkinDay);
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
		RoomTypeService rtSvc = new RoomTypeService();
		List<RoomTypeVO> rtList = rtSvc.findRoomTypeByBraID(braId);   //用分店編號, 找出所有的分店下的房型
		System.out.println(rtList);
		
		List<Integer> maxRooms = new ArrayList<Integer>();    //找出房型的最大房間數
		List<String> balance = new ArrayList<String>();       //找出房型的 62byte
		for(RoomTypeVO rtVO : rtList) {
			maxRooms.add(rtVO.getTotal());          // 取得房型最大間數
			balance.add(rtVO.getBalance());         // 取得房型balance
		}
		System.out.println(maxRooms);
		System.out.println(balance);
		
		for(int rtIndex = 0; rtIndex < rtList.size(); rtIndex++) {    //滾迴圈找房型
			// 相同房型時的判斷
			int minRooms = maxRooms.get(rtIndex);          //房型最小間數
			if(totalDay == 1) {      //只住一天
				roomsNum[0] = getRoomByDate(checkinDay, balance.get(rtIndex));   //撈出所有房型, 某日期區間, 判斷是否有房間
				System.out.println("剩 " + roomsNum[0] + " 間");
			} else {
				for(int i = 0; i < roomsNum.length; i++) {   //記錄入區間的房間剩餘數量[]
					
					if(checkinDay + i > checkinDayofMonth) {   //入住日期 > 當月最大日期
						checkinDay = 0;
					}
					
					roomsNum[i] = getRoomByDate(checkinDay + i, balance.get(rtIndex));    //取得某日期的間數(日期, 62byte)
					System.out.println(checkinDay + i + "號" + "剩 " + roomsNum[i] + " 間");
					minRooms = Math.min(minRooms, roomsNum[i]);   //找出某房型某區間, 最少間數
				}
				System.out.println("最少間數 " + minRooms);  
			}
			rtList.get(rtIndex).setBalance(String.valueOf(minRooms));     // 最少間數
		}
		System.out.println("-----------------------------------------------------");
		
		return rtList;
	}
	
	public static void main(String[] args) {
		
		String braId = "麻翔";
		String checkinDate = "2018-01-20";
		String checkoutDate = "2018-01-22";
		
		List<RoomTypeVO> rtList = RoomTypeCompositeQuery.searchRoomTypeMinRoom(braId, checkinDate, checkoutDate);
		
		for(int i = 0; i < rtList.size(); i++) {
			System.out.println("分店編號" + rtList.get(i).getBraID());
			System.out.println("房型編號" + rtList.get(i).getRtID());
			System.out.println("房型名稱"+  rtList.get(i).getRtName());
			System.out.println("定價" + rtList.get(i).getHolidayPrice());
			System.out.println("剩餘間數" + rtList.get(i).getBalance());
			System.out.println("=====================");
		}
	}

}


