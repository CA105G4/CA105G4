package com.branch.model;

import java.io.Serializable;

public class BranchVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String braID;     // 分店編號
	private String braName;   // 分店名稱
	private String braIntro;  // 分店介紹
	private byte[] braPic;    // 分店圖片
	private String braTel;    // 分店電話
	private byte[] braVideo;  // 分店介紹影片
	private String braAddr;   // 分店地址
	private Double braLng;    // 分店經度
	private Double braLat;    // 分店緯度
	private Integer braState; // 分店狀態
	
	public BranchVO() {
		super();
	}

	public String getBraID() {
		return braID;
	}

	public void setBraID(String braID) {
		this.braID = braID;
	}

	public String getBraName() {
		return braName;
	}

	public void setBraName(String braName) {
		this.braName = braName;
	}

	public String getBraIntro() {
		return braIntro;
	}

	public void setBraIntro(String braIntro) {
		this.braIntro = braIntro;
	}

	public byte[] getBraPic() {
		return braPic;
	}

	public void setBraPic(byte[] braPic) {
		this.braPic = braPic;
	}

	public String getBraTel() {
		return braTel;
	}

	public void setBraTel(String braTel) {
		this.braTel = braTel;
	}

	public byte[] getBraVideo() {
		return braVideo;
	}

	public void setBraVideo(byte[] braVideo) {
		this.braVideo = braVideo;
	}

	public String getBraAddr() {
		return braAddr;
	}

	public void setBraAddr(String braAddr) {
		this.braAddr = braAddr;
	}

	public Double getBraLng() {
		return braLng;
	}

	public void setBraLng(Double braLng) {
		this.braLng = braLng;
	}

	public Double getBraLat() {
		return braLat;
	}

	public void setBraLat(Double braLat) {
		this.braLat = braLat;
	}

	public Integer getBraState() {
		return braState;
	}

	public void setBraState(Integer braState) {
		this.braState = braState;
	}
}
