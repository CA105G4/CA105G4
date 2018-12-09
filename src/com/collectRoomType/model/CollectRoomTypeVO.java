package com.collectRoomType.model;

import java.io.Serializable;

public class CollectRoomTypeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String memID;   // 會員編號
	private String rtID;    // 房型編號
	
	public CollectRoomTypeVO() {
		super();
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getRtID() {
		return rtID;
	}

	public void setRtID(String rtID) {
		this.rtID = rtID;
	}
}
