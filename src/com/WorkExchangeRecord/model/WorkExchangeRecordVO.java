package com.WorkExchangeRecord.model;

import java.io.Serializable;

public class WorkExchangeRecordVO implements Serializable{
	private Integer werID;
	private String memID;
	private Integer werState;
	private String orderID;
	private byte[] weApp;
	
	public WorkExchangeRecordVO(){}

	public Integer getWerID() {
		return werID;
	}

	public void setWerID(Integer werID) {
		this.werID = werID;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public Integer getWerState() {
		return werState;
	}

	public void setWerState(Integer werState) {
		this.werState = werState;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public byte[] getWeApp() {
		return weApp;
	}

	public void setWeApp(byte[] weApp) {
		this.weApp = weApp;
	}

}
