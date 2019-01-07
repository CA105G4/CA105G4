package com.coupon.model;

import java.io.Serializable;

public class CouponVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cpnID;         // 優惠券編號
	private byte[] cpnPic;        // 優惠券圖片
	private Integer discount;     // 優惠金額 
	private Integer quantity;     // 數量
	private Integer appQuantity;  // 原本數量
	
	public CouponVO() {
		super();
	}

	public String getcpnID() {
		return cpnID;
	}
	
	public void setcpnID(String cpnID) {
		this.cpnID = cpnID;
	}
	
	public byte[] getcpnPic() {
		return cpnPic;
	}
	
	public void setcpnPic(byte[] cpnPic) {
		this.cpnPic = cpnPic;
	}
	
	public Integer getdiscount() {
		return discount;
	}
	
	public void setdiscount(Integer discount) {
		this.discount = discount;
	}
	
	public Integer getquantity() {
		return quantity;
	}

	public void setquantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getappQuantity() {
		return appQuantity;
	}
	
	public void setappQuantity(Integer appQuantity) {
		this.appQuantity = appQuantity;
	}
}
