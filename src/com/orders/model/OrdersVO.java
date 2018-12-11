package com.orders.model;

import java.io.Serializable;
import java.sql.Date;

public class OrdersVO implements Serializable{
	private String ordID;		//訂單編號
	private String memID;		//會員編號
	private String braID;		//分店編號
	private Integer numOfRoom;	//房間數量
	private Integer ordType;	//訂單種類
	private Integer numOfGuest;	//入住人數
	private Integer amount;		//總金額
	private Integer Bond;		//訂金
	private Integer Payment;	//付款方式
	private Integer ordState; 	//訂單狀態
	private Date OrdTime;		//訂單成立時間
	
	
	public OrdersVO() {
		super();
	}

	public OrdersVO(String ordID, String memID, String braID, Integer numOfRoom, Integer ordType, Integer numOfGuest,
			Integer amount, Integer bond, Integer payment, Integer ordState, Date ordTime) {
		super();
		this.ordID = ordID;
		this.memID = memID;
		this.braID = braID;
		this.numOfRoom = numOfRoom;
		this.ordType = ordType;
		this.numOfGuest = numOfGuest;
		this.amount = amount;
		Bond = bond;
		Payment = payment;
		this.ordState = ordState;
		OrdTime = ordTime;
	}
	
	public String getOrdID() {
		return ordID;
	}
	public void setOrdID(String ordID) {
		this.ordID = ordID;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getBraID() {
		return braID;
	}
	public void setBraID(String braID) {
		this.braID = braID;
	}
	public Integer getNumOfRoom() {
		return numOfRoom;
	}
	public void setNumOfRoom(Integer numOfRoom) {
		this.numOfRoom = numOfRoom;
	}
	public Integer getOrdType() {
		return ordType;
	}
	public void setOrdType(Integer ordType) {
		this.ordType = ordType;
	}
	public Integer getNumOfGuest() {
		return numOfGuest;
	}
	public void setNumOfGuest(Integer numOfGuest) {
		this.numOfGuest = numOfGuest;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getBond() {
		return Bond;
	}
	public void setBond(Integer bond) {
		Bond = bond;
	}
	public Integer getPayment() {
		return Payment;
	}
	public void setPayment(Integer payment) {
		Payment = payment;
	}
	public Integer getOrdState() {
		return ordState;
	}
	public void setOrdState(Integer ordState) {
		this.ordState = ordState;
	}
	public Date getOrdTime() {
		return OrdTime;
	}
	public void setOrdTime(Date ordTime) {
		OrdTime = ordTime;
	}
	
	

}
