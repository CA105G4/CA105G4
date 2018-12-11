package com.message.model;

import java.sql.Date;

public class MessageVO implements java.io.Serializable{
	
	private Integer msgid;
	private Integer artid;
	private String msgmemid;
	private String msgcontent;
	private Date msgdate;
	private Integer msgstate;
	
	public Integer getMsgid() {
		return msgid;
	}
	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}
	public Integer getArtid() {
		return artid;
	}
	public void setArtid(Integer artid) {
		this.artid = artid;
	}
	public String getMsgmemid() {
		return msgmemid;
	}
	public void setMsgmemid(String msgmemid) {
		this.msgmemid = msgmemid;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	public Date getMsgdate() {
		return msgdate;
	}
	public void setMsgdate(Date msgdate) {
		this.msgdate = msgdate;
	}
	public Integer getMsgstate() {
		return msgstate;
	}
	public void setMsgstate(Integer msgstate) {
		this.msgstate = msgstate;
	}
	
	
}
