package com.article.model;

import java.sql.Date;

public class ArticleVO implements java.io.Serializable{
	
	
	private Integer artid;
	private String memid;
	private byte[] artpic;
	private String artexp;
	private Integer artstate;
	private Date artdate;
	
	
	public Integer getArtid() {
		return artid;
	}
	public void setArtid(Integer artid) {
		this.artid = artid;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public byte[] getArtpic() {
		return artpic;
	}
	public void setArtpic(byte[] artpic) {
		this.artpic = artpic;
	}
	public String getArtexp() {
		return artexp;
	}
	public void setArtexp(String artexp) {
		this.artexp = artexp;
	}
	public Integer getArtstate() {
		return artstate;
	}
	public void setArtstate(Integer artstate) {
		this.artstate = artstate;
	}
	public Date getArtdate() {
		return artdate;
	}
	public void setArtdate(Date artdate) {
		this.artdate = artdate;
	}
	
	
	
}
