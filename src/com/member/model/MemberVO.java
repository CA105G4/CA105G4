package com.member.model;

import java.sql.Date;

public class MemberVO  {
	
	
	
	private String memID;
	private String memName;
	private String memAcc;
	private String memPsw;
	private Date memBirth;
	private String memEmail;
	private String memTel;
	private String memAddr;
	private String memSex;
	private Date memReg;
	private String memSkill;
	private Integer memState;
	private byte[] memPic;
	private String memIDcard;
	
	public MemberVO() {
		super();
	}
	
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemAcc() {
		return memAcc;
	}
	public void setMemAcc(String memAcc) {
		this.memAcc = memAcc;
	}
	public String getMemPsw() {
		return memPsw;
	}
	public void setMemPsw(String memPsw) {
		this.memPsw = memPsw;
	}
	public Date getMemBirth() {
		return memBirth;
	}
	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemTel() {
		return memTel;
	}
	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}
	public String getMemAddr() {
		return memAddr;
	}
	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}
	public String getMemSex() {
		return memSex;
	}
	public void setMemSex(String memSex) {
		this.memSex = memSex;
	}
	public Date getMemReg() {
		return memReg;
	}
	public void setMemReg(Date memReg) {
		this.memReg = memReg;
	}
	public String getMemSkill() {
		return memSkill;
	}
	public void setMemSkill(String memSkill) {
		this.memSkill = memSkill;
	}
	public Integer getMemState() {
		return memState;
	}
	public void setMemState(Integer memState) {
		this.memState = memState;
	}
	public byte[] getMemPic() {
		return memPic;
	}
	public void setMemPic(byte[] memPic) {
		this.memPic = memPic;
	}
	public String getMemIDcard() {
		return memIDcard;
	}
	public void setMemIDcard(String memIDcard) {
		this.memIDcard = memIDcard;
	}
}
