package com.coupon.model;

import java.util.List;

public class CouponService {
	
	private CouponDAO_interface dao;
	
	public CouponService() {
		dao = new CouponDAO();
	}
	
	public CouponVO addCpn(byte[] cpnPic,Integer discount,Integer quantity,Integer appQuantity) {
		CouponVO cpnVO =new CouponVO();
		
		cpnVO.setCpnPic(cpnPic);
		cpnVO.setDiscount(discount);
		cpnVO.setQuantity(quantity);
		cpnVO.setAppQuantity(appQuantity);
		dao.insert(cpnVO);
		
		return cpnVO;
	}
	
	public CouponVO updateCpn(byte[] cpnPic,Integer discount,Integer quantity,Integer appQuantity,String cpnID) {
	CouponVO cpnVO = new CouponVO();	
		
	cpnVO.setCpnPic(cpnPic);
	cpnVO.setDiscount(discount);
	cpnVO.setQuantity(quantity);
	cpnVO.setAppQuantity(appQuantity);
	cpnVO.setCpnID(cpnID);
	
	dao.update(cpnVO);
		
		return cpnVO;
	}
	
	public CouponVO getOneByID(String cpnID) {
		return dao.findByPK(cpnID);
		
	}
	
	public List<CouponVO>getALl(){
		return dao.getAll();
	}
	
	
	
}
