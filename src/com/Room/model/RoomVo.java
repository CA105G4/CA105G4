package com.Room.model;

import java.io.Serializable;

public class RoomVo implements Serializable{
	private String roomID;
	private String roomTypeID;
	private String braID;
	private Integer roomNo;
	private Integer roomState;
	private String memName;
	
	public RoomVo() {}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getRoomTypeID() {
		return roomTypeID;
	}

	public void setRoomTypeID(String roomTypeID) {
		this.roomTypeID = roomTypeID;
	}

	public String getBraID() {
		return braID;
	}

	public void setBraID(String braID) {
		this.braID = braID;
	}

	public Integer getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(Integer roomNo) {
		this.roomNo = roomNo;
	}

	public Integer getRoomState() {
		return roomState;
	}

	public void setRoomState(Integer roomState) {
		this.roomState = roomState;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}
}
