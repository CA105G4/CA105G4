package com.coupon.model;

import java.util.List;

public class CouponService {
	
	private CouponDAO_interface dao;
	
	public CouponService() {
		dao = new CouponDAO();
	}
	
	public CouponVO addCpn(byte[] cpnPic,Integer discount,Integer quantity,Integer appQuantity) {
		CouponVO cpnVO =new CouponVO();
		
		cpnVO.setcpnPic(cpnPic);
		cpnVO.setdiscount(discount);
		cpnVO.setquantity(quantity);
		cpnVO.setappQuantity(appQuantity);
		dao.insert(cpnVO);
		
		return cpnVO;
	}
	
	public CouponVO updateCpn(byte[] cpnPic,Integer discount,Integer quantity,Integer appQuantity,String cpnID) {
	CouponVO cpnVO = new CouponVO();	
		
	cpnVO.setcpnPic(cpnPic);
	cpnVO.setdiscount(discount);
	cpnVO.setquantity(quantity);
	cpnVO.setappQuantity(appQuantity);
	cpnVO.setcpnID(cpnID);
	
	dao.update(cpnVO);
		
		return cpnVO;
	}
		
	public CouponVO updateCpn(Integer nowQuantity,String cpnID) {
	CouponVO cpnVO = new CouponVO();	
	
	
	cpnVO.setquantity(nowQuantity);
	cpnVO.setcpnID(cpnID);
	
	dao.updateQuantity(cpnVO);
		
		return cpnVO;
	}
	
	public CouponVO getOneByID(String cpnID) {
		return dao.findByPK(cpnID);
		
	}
	
	public void delete(String cpnID) {
		dao.delete(cpnID);
	}
	
	
	public List<CouponVO>getAll(){
		return dao.getAll();
	}
	
	
	
}
