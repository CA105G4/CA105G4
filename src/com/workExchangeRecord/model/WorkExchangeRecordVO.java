package com.workExchangeRecord.model;

import java.io.Serializable;

public class WorkExchangeRecordVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer weID;
	private String memID;
	private Integer werState;
	private String orderID;
	private byte[] weApp;
	
	public WorkExchangeRecordVO(){}

	public Integer getWeID() {
		return weID;
	}

	public void setWeID(Integer werID) {
		this.weID = werID;
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
