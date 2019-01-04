package com.orders.model;

import java.sql.Date;

public class OrdersCheckInOutVO {
	/**訂單**/
	private String ordID;		//訂單編號
	private String memID;		//會員編號
	private String braID;		//分店編號
	private Integer numOfRoom;	//房間數量
	private Integer ordType;	//訂單種類
	private Integer numOfGuest;	//入住人數
	private Integer amount;		//總金額
	private Integer bond;		//訂金
	private Integer payment;	//付款方式
	private Integer ordState; 	//訂單狀態
	private Date ordTime;		//訂單成立時間
	
	/**訂單明細**/
	private Integer odID;		//訂單明細編號
	private String roomID;		//房間編號
//	private String ordID;		//訂單編號>>因重複取消
	private String rtID;		//房型編號
	private Date checkIn;		//住房日期
	private Date checkOut;		//退房日期
	private String rtName;		//房型名稱
	private Double evaluates;	//評價
	private Integer special;	//特殊需求(加床)
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
		return bond;
	}
	public void setBond(Integer bond) {
		this.bond = bond;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	public Integer getOrdState() {
		return ordState;
	}
	public void setOrdState(Integer ordState) {
		this.ordState = ordState;
	}
	public Date getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Date ordTime) {
		this.ordTime = ordTime;
	}
	public Integer getOdID() {
		return odID;
	}
	public void setOdID(Integer odID) {
		this.odID = odID;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	public String getRtID() {
		return rtID;
	}
	public void setRtID(String rtID) {
		this.rtID = rtID;
	}
	public Date getCheckIn() {
		return checkIn;
	}
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}
	public Date getCheckOut() {
		return checkOut;
	}
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}
	public String getRtName() {
		return rtName;
	}
	public void setRtName(String rtName) {
		this.rtName = rtName;
	}
	public Double getEvaluates() {
		return evaluates;
	}
	public void setEvaluates(Double evaluates) {
		this.evaluates = evaluates;
	}
	public Integer getSpecial() {
		return special;
	}
	public void setSpecial(Integer special) {
		this.special = special;
	}
	
	
}
