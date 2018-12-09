package com.couponRecord.model;

import java.io.Serializable;

public class CouponRecordVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cpnID;       // 優惠券編號
	private String memID;       // 會員編號
	private Integer cpnState;   // 優惠券收藏紀錄
	
	public CouponRecordVO() {
		super();
	}
	
	public String getCpnID() {
		return cpnID;
	}
	
	public void setCpnID(String cpnID) {
		this.cpnID = cpnID;
	}
	
	public String getMemID() {
		return memID;
	}
	
	public void setMemID(String memID) {
		this.memID = memID;
	}
	
	public Integer getCpnState() {
		return cpnState;
	}
	
	public void setCpnState(Integer cpnState) {
		this.cpnState = cpnState;
	}
}
