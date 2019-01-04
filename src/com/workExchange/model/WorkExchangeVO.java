package com.workExchange.model;

import java.io.Serializable;
import java.sql.Date;

public class WorkExchangeVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer weID;
	private String empID;
	private String memID;
	private String rtID;
	private String weName;
	private String weContent;
	private byte[] wePic;
	private byte[] weVideo;
	private Date weStart;
	private Date weEnd;
	
	public WorkExchangeVO() {}

	public Integer getWeID() {
		return weID;
	}

	public void setWeID(Integer weID) {
		this.weID = weID;
	}

	public String getEmpID() {
		return empID;
	}
	
	public void setEmpID(String empID) {
		this.empID = empID;
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
	public byte[] getWeVideo() {
		return weVideo;
	}

	public void setWeVideo(byte[] weVideo) {
		this.weVideo = weVideo;
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
