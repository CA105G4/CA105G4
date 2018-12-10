package com.activity.model;

import java.sql.Date;
import java.util.List;

public class ActivityVOJDBC {

	public static void main(String[] args) {
		ActivityJDBCDAO dao= new ActivityJDBCDAO();
		
		//新增
		ActivityVO actVO =new ActivityVO();
		Date d1 =  Date.valueOf("2018-12-25");
		Date d2 =  Date.valueOf("2018-12-31");
		actVO.setActName("聖誕老公公");
		actVO.setActStart(d1);
		actVO.setActEnd(d2);
		
		
		dao.insert(actVO);
		System.out.println("新增成功");
		
		//修改
		ActivityVO actVO2 =new ActivityVO();
		Date d3 =  Date.valueOf("2019-02-25");
		Date d4 =  Date.valueOf("2019-02-26");
		actVO2.setActID("A0003"); 
		actVO2.setActName("測試用節慶");
		actVO2.setActStart(d3);
		actVO2.setActEnd(d4);
		dao.update(actVO2);
		System.out.println("修改成功");
		
		//透過主鍵尋找全部
		ActivityVO actVO3 =dao.findByPK("A0003");
		System.out.print(actVO3.getActID()+" ");
		System.out.print(actVO3.getActName()+" ");
		System.out.print(actVO3.getActStart()+" ");
		System.out.println(actVO3.getActEnd());
		
		//找全部
		
		List<ActivityVO>list =dao.getAll();
		
		for(ActivityVO actVO4:list) {
			System.out.print(actVO4.getActID()+" ");
			System.out.print(actVO4.getActName()+" ");
			System.out.print(actVO4.getActStart()+" ");
			System.out.print(actVO4.getActEnd()+" ");
			System.out.println();
		}
		
		
	}

}
