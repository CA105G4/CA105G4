package com.billboard.model;
import java.sql.Timestamp;

public class BillboardVO implements java.io.Serializable{
	private static final long serialVersionUID = 7733480661286247360L;
	private Integer bbID;
	private String url;
	private byte[] pic;
	private Timestamp bbStart;
	private Timestamp bbEnd;
	private Integer bbStatus;
	
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
	public Timestamp getbbStart() {
		return bbStart;
	}
	public void setbbStart(Timestamp bbStart) {
		this.bbStart = bbStart;
	}
	public Timestamp getbbEnd() {
		return bbEnd;
	}
	public void setbbEnd(Timestamp bbEnd) {
		this.bbEnd = bbEnd;
	}
	
	public Integer getbbStatus() {
		return bbStatus;
	}
	
	public void setbbStatus(Integer bbStatus) {
		this.bbStatus=bbStatus;
	}
	
}
