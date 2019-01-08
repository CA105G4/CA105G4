package com.android.activityDetail.model;

public class ActivityDetailVO implements java.io.Serializable{
	private String actID;
	private String rtID;
	private Float Discount;
	
	public String getActID() {
		return actID;
	}
	public void setActID(String actID) {
		this.actID = actID;
	}
	public String getRtID() {
		return rtID;
	}
	public void setRtID(String rtID) {
		this.rtID = rtID;
	}
	public Float getDiscount() {
		return Discount;
	}
	public void setDiscount(Float discount) {
		Discount = discount;
	}
	
}
