package com.android.couponRecord.model;

import java.util.List;

public interface CouponRecord_interface {
	public void insert(CouponRecordVO couponRecordVO);            // 新增會員收藏的優惠券
	public void update(String memId, String cpnId);               // 修改優惠券使用狀態
	public boolean findByMemIdCpnId(String memId, String cpnId);  // 成功新增
	public List<CouponRecordVO> findByMemId(String memId);        // 用會員編號找出所有的優惠券編號
}
