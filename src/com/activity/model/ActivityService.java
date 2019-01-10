package com.activity.model;

import java.util.List;
import java.util.Set;

import com.activityDetail.model.ActivityDetailVO;

public class ActivityService {
	
	private ActivityDAO_interface dao;
	
	public ActivityService() {
		dao =new ActivityDAO();
	}
	
	public ActivityVO addAct(String actName, java.sql.Date startDate,java.sql.Date  endDate) {
		ActivityVO actVO= new ActivityVO();
		
		actVO.setActName(actName);
		actVO.setActStart(startDate);
		actVO.setActEnd(endDate);
		dao.insert(actVO);
		
		return actVO;
	}
	
	public ActivityVO updateAct(String actName,java.sql.Date startDate,java.sql.Date  endDate,String actID) {
		ActivityVO actVO =new ActivityVO();
		
		actVO.setActName(actName);
		actVO.setActStart(startDate);
		actVO.setActEnd(endDate);
		actVO.setActID(actID);
		dao.update(actVO);
		
		return actVO;
	}
	
	public void deleteAct(String actID) {
		dao.delete(actID);
	}
	
	public ActivityVO getOneByID(String actID) {
		return dao.findByPK(actID);
	}
	
	public ActivityVO getOneByName(String actName) {
		return dao.findByName(actName);
	}
	
	public List<ActivityVO>getAll(){
		return dao.getAll();
	}
	
	/**[Gina]訂單計算總金額-查找促銷日期折扣**/
	public float getActivityDiscount(String rtId, String date) {
		return dao.getActivityDiscount(rtId, date);
	};
	/**[Gina]訂單計算總金額-查找促銷日期折扣**/	
	
	public ActivityVO insertWithDetail(ActivityVO actVO,List<ActivityDetailVO>adList) {
		dao.insertWithDetail(actVO, adList);
		
		return actVO;
	}
	
	public Set<ActivityDetailVO>getDetailVOByactID(String actID){
		return dao.getDetailByactID(actID);
	}
}
