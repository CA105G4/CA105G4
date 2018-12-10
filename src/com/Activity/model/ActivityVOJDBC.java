package com.Activity.model;

import java.sql.Date;

public class ActivityVOJDBC {

	public static void main(String[] args) {
		ActivityVO actVO =new ActivityVO();
		Date d1 =  Date.valueOf("2018-12-25");
		Date d2 =  Date.valueOf("2018-12-31");
		actVO.setActName("聖誕老公公");
		actVO.setActStart(d1);
		actVO.setActEnd(d2);
		
		ActivityJDBCDAO actJDAO= new ActivityJDBCDAO();
		actJDAO.insert(actVO);
		System.out.println("新增成功");
	}

}
