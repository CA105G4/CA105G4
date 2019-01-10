package com.activityDetail.model;

import java.util.List;


public class ActivityDetailService {
	private ActivityDetailDAO_interface dao;
	
	public ActivityDetailService() {
		dao =new ActivityDetailDAO();
	}
	
	public ActivityDetailVO addAD(Float dis) {
		ActivityDetailVO adVO =new ActivityDetailVO();
		
		adVO.setDiscount(dis);
		dao.insert(adVO);
		
		return adVO;
	}
	
	public ActivityDetailVO updateAD(Float dis) {
		ActivityDetailVO adVO =new ActivityDetailVO();
			
		adVO.setDiscount(dis);
		dao.update(adVO);
		
		return adVO;
	}
	
	public void deleteAD(String actID) {
		dao.delete(actID);
	}
	
	public ActivityDetailVO getOneByID(String actID) {
		return dao.findByPK(actID);
	}
	
	public List<ActivityDetailVO>getAll(){
		return dao.getAll();
	}
}
