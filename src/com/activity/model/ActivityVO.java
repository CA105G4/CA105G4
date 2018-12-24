package com.activity.model;

import java.sql.Date;

public class ActivityVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private String actID;
	private String actName;
	private Date actStart;
	private Date actEnd;
	public String getActID() {
		return actID;
	}
	public void setActID(String actID) {
		this.actID = actID;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public Date getActStart() {
		return actStart;
	}
	public void setActStart(Date actStart) {
		this.actStart = actStart;
	}
	public Date getActEnd() {
		return actEnd;
	}
	public void setActEnd(Date actEnd) {
		this.actEnd = actEnd;
	}
}