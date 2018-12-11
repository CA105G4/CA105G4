package com.report.model;

import java.sql.Date;

public class ReportVO implements java.io.Serializable{
	private Integer repid;
	private Integer artid;
	private String artreason;
	private String repmemid;
	private Date repdate;
	private Integer repstate;
	public Integer getRepid() {
		return repid;
	}
	public void setRepid(Integer repid) {
		this.repid = repid;
	}
	public Integer getArtid() {
		return artid;
	}
	public void setArtid(Integer artid) {
		this.artid = artid;
	}
	public String getArtreason() {
		return artreason;
	}
	public void setArtreason(String artreason) {
		this.artreason = artreason;
	}
	public String getRepmemid() {
		return repmemid;
	}
	public void setRepmemid(String repmemid) {
		this.repmemid = repmemid;
	}
	public Date getRepdate() {
		return repdate;
	}
	public void setRepdate(Date repdate) {
		this.repdate = repdate;
	}
	public Integer getRepstate() {
		return repstate;
	}
	public void setRepstate(Integer repstate) {
		this.repstate = repstate;
	}
	
	
}
