package com.billboard.model;
import java.sql.Date;

public class BillboardVO implements java.io.Serializable{
	private Integer bbID;
	private String url;
	private byte[] pic;
	private Date bbStart;
	private Date bbEnd;
	
	public Integer getBbID() {
		return bbID;
	}
	public void setBbID(Integer bbID) {
		this.bbID = bbID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public byte[] getPic() {
		return pic;
	}
	public void setPic(byte[] pic) {
		this.pic = pic;
	}
	public Date getBbStart() {
		return bbStart;
	}
	public void setBbStart(Date bbStart) {
		this.bbStart = bbStart;
	}
	public Date getBbEnd() {
		return bbEnd;
	}
	public void setBbEnd(Date bbEnd) {
		this.bbEnd = bbEnd;
	}
	
}
