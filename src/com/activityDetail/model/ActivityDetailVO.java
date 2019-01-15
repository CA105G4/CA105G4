package com.activityDetail.model;

public class ActivityDetailVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String actID;
	private String rtID;
	private Float discount;
	
	public String getactID() {
		return actID;
	}
	public void setactID(String actID) {
		this.actID = actID;
	}
	public String getrtID() {
		return rtID;
	}
	public void setrtID(String rtID) {
		this.rtID = rtID;
	}
	public Float getdiscount() {
		return discount;
	}
	public void setdiscount(Float discount) {
		this.discount = discount;
	}
	
}
