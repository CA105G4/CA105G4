package com.android.room.model;

import java.io.Serializable;

public class Room implements Serializable{

	private static final long serialVersionUID = 1L;

	private String roomID;
	private String roomTypeName;
	private Integer roomNo;
	
	public String getRoomID() {
		return roomID;
	}
	
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	
	public String getRoomTypeName() {
		return roomTypeName;
	}
	
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	
	public Integer getRoomNo() {
		return roomNo;
	}
	
	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}
}
