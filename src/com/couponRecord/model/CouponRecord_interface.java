package com.couponRecord.model;

import java.util.List;

public interface CouponRecord_interface {
	public void insert(CouponRecordVO couponRecordVO);    // 新增會員收藏的優惠券
	public void update(CouponRecordVO couponRecordVO);    // 修改優惠券使用狀態
	public List<CouponRecordVO> findByPK(String cpnID);   // 用會員編號找出所有的優惠券編號
}
