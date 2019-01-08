package com.android.coupon.model;

import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

public class CouponService {
	
	private CouponDAO_interface dao;
	
	public CouponService() {
		dao = new CouponDAO();
	}
	
	public void updateCoupon(String cpnId, Integer quantity) {   // 回來時應傳遞 會員編號, 優惠券編號, 數量, 紀錄收藏
		dao.update(cpnId, quantity);
	}
	
	public List<CouponVO> getAllCoupon(){   //找出所有 優惠券
		return dao.getAll();
	}
	
	public List<CouponVO> getQuantityAllCoupon(){
		return dao.getQuantityAll();		
	}
	
	public List<Coupon> getCouponByMemId(String memId){     //用會員編號查詢 inner join
		return dao.getCouponByMemId(memId);
	}
	
	public List<Coupon> getCoupon(){   //加上會員紀錄的 left join
		return dao.getCoupon();
	}
	
	public Integer getCouponDiscount(String cpnId) {
		return dao.getCouponDiscount(cpnId);
	}
}
