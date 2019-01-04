package com.billboard.model;
import java.sql.Date;

public class BillboardVO implements java.io.Serializable{
	private Integer bbID;
	private String url;
	private byte[] pic;
	private Date bbStart;
	private Date bbEnd;
	
	public Integer getbbID() {
		return bbID;
	}
	public void setbbID(Integer bbID) {
		this.bbID = bbID;
	}
	public String geturl() {
		return url;
	}
	public void seturl(String url) {
		this.url = url;
	}
	public byte[] getpic() {
		return pic;
	}
	public void setpic(byte[] pic) {
		this.pic = pic;
	}
	public Date getbbStart() {
		return bbStart;
	}
	public void setbbStart(Date bbStart) {
		this.bbStart = bbStart;
	}
	public Date getbbEnd() {
		return bbEnd;
	}
	public void setbbEnd(Date bbEnd) {
		this.bbEnd = bbEnd;
	}
	
}
