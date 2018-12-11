package com.roomType.model;

import java.io.Serializable;

public class RoomTypeVO implements Serializable{
	private String rtID;			//房型編號
	private String braID;			//分店編號
	private String rtName;			//房型名稱
	private byte[] rtPic;			//房型圖片
	private String rtIntro;			//房型介紹
	private Integer rtMinimum;		//人數
	private Integer rtLimit;		//上限人數
	private Integer WeeklyPrice;	//平日價格
	private Integer holidayPrice;	//假日價格
	private String Balance;			//剩餘房間數
	private Integer Total;			//總房間數
	
	
	
	public RoomTypeVO() {
		super();
	}	

	public RoomTypeVO(String rtID, String braID, String rtName, byte[] rtPic, String rtIntro, Integer rtMinimum,
			Integer rtLimit, Integer weeklyPrice, Integer holidayPrice, String balance, Integer total) {
		super();
		this.rtID = rtID;
		this.braID = braID;
		this.rtName = rtName;
		this.rtPic = rtPic;
		this.rtIntro = rtIntro;
		this.rtMinimum = rtMinimum;
		this.rtLimit = rtLimit;
		WeeklyPrice = weeklyPrice;
		this.holidayPrice = holidayPrice;
		Balance = balance;
		Total = total;
	}

	public String getRtID() {
		return rtID;
	}

	public void setRtID(String rtID) {
		this.rtID = rtID;
	}

	public String getBraID() {
		return braID;
	}

	public void setBraID(String braID) {
		this.braID = braID;
	}

	public String getRtName() {
		return rtName;
	}

	public void setRtName(String rtName) {
		this.rtName = rtName;
	}

	public byte[] getRtPic() {
		return rtPic;
	}

	public void setRtPic(byte[] rtPic) {
		this.rtPic = rtPic;
	}

	public String getRtIntro() {
		return rtIntro;
	}

	public void setRtIntro(String rtIntro) {
		this.rtIntro = rtIntro;
	}

	public Integer getRtMinimum() {
		return rtMinimum;
	}

	public void setRtMinimum(Integer rtMinimum) {
		this.rtMinimum = rtMinimum;
	}

	public Integer getRtLimit() {
		return rtLimit;
	}

	public void setRtLimit(Integer rtLimit) {
		this.rtLimit = rtLimit;
	}

	public Integer getWeeklyPrice() {
		return WeeklyPrice;
	}

	public void setWeeklyPrice(Integer weeklyPrice) {
		WeeklyPrice = weeklyPrice;
	}

	public Integer getHolidayPrice() {
		return holidayPrice;
	}

	public void setHolidayPrice(Integer holidayPrice) {
		this.holidayPrice = holidayPrice;
	}

	public String getBalance() {
		return Balance;
	}

	public void setBalance(String balance) {
		Balance = balance;
	}

	public Integer getTotal() {
		return Total;
	}

	public void setTotal(Integer total) {
		Total = total;
	}

}
