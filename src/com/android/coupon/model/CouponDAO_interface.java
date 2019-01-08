package com.android.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	public void update(String cpnId, Integer quantity);   // 優惠券 被領取-1
	public Integer getCouponDiscount(String cpnId);       // 用優惠券編號回傳折價金額
	public List<CouponVO> getAll();                       // 取得全部優惠券
	public List<CouponVO> getQuantityAll();	              // 未被領取完的優惠券
	
	//
	public List<Coupon> getCouponByMemId(String memId);
	public List<Coupon> getCoupon();
}
