package com.android.tool;

import java.sql.Date;

public class ProcDate {
	
	public static final int YEAR = 0;
	public static final int MONTH = 1;
	public static final int DAY = 2;

	public ProcDate() {
		
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
}
