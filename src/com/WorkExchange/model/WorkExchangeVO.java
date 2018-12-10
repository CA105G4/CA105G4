package com.WorkExchange.model;

import java.io.Serializable;
import java.sql.Date;

public class WorkExchangeVO implements Serializable{
	private Integer weID;
	private String memID;
	private String empID;
	private String weRoomID;
	private String weName;
	private String weContent;
	private byte[] wePic;
	private byte[] weRoomPic;
	private Date weStart;
	private Date weEnd;
	
	public WorkExchangeVO() {}

	public Integer getWeID() {
		return weID;
	}

	public void setWeID(Integer weID) {
		this.weID = weID;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getWeRoomID() {
		return weRoomID;
	}

	public void setWeRoomID(String weRoomID) {
		this.weRoomID = weRoomID;
	}

	public String getWeName() {
		return weName;
	}

	public void setWeName(String weName) {
		this.weName = weName;
	}

	public String getWeContent() {
		return weContent;
	}

	public void setWeContent(String weContent) {
		this.weContent = weContent;
	}

	public byte[] getWePic() {
		return wePic;
	}

	public void setWePic(byte[] wePic) {
		this.wePic = wePic;
	}

	public byte[] getWeRoomPic() {
		return weRoomPic;
	}

	public void setWeRoomPic(byte[] weRoomPic) {
		this.weRoomPic = weRoomPic;
	}

	public Date getWeStart() {
		return weStart;
	}

	public void setWeStart(Date weStart) {
		this.weStart = weStart;
	}

	public Date getWeEnd() {
		return weEnd;
	}

	public void setWeEnd(Date weEnd) {
		this.weEnd = weEnd;
	}

}
