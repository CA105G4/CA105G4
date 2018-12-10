package com.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	public void insert(CouponVO couponVO);    // 新增優惠券
	public void update(CouponVO couponVO);    // 修改優惠券
	public CouponVO findByPK(String cpnID);   // 用PK找對應的優惠券
	public List<CouponVO> getAll();           // 取得全部優惠券
}
