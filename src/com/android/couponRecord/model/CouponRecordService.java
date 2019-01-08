package com.android.couponRecord.model;

import java.util.List;

public class CouponRecordService {

	private CouponRecord_interface dao;
	
	public CouponRecordService() {
		dao = new CouponRecordDAO();
	}
	
	public void addCoupon(String memId, String cpnId) {
		
		CouponRecordVO couponRecordVO = new CouponRecordVO();
		
		couponRecordVO.setMemID(memId);
		couponRecordVO.setCpnID(cpnId);
		
		dao.insert(couponRecordVO);
	}
	
	public void updateState(String memId, String cpnId) {
		dao.update(memId, cpnId);
	}
	
	public boolean isMemberCollectCoupon(String memId, String cpnId) {
		return dao.findByMemIdCpnId(memId, cpnId);
	}
	
	public List<CouponRecordVO> getCollectCoupon(String memId){
		return dao.findByMemId(memId);
	}
}
