package com.messageReport.model;

public class MessageReportVO implements java.io.Serializable{
	
		private Integer mrid;
		private Integer artid;
		private Integer msgid;
		private String mrreason;
		private Integer mrstate;
	
		public Integer getMrid() {
			return mrid;
		}
		public void setMrid(Integer mrid) {
			this.mrid = mrid;
		}
		public Integer getArtid() {
			return artid;
		}
		public void setArtid(Integer artid) {
			this.artid = artid;
		}
		public Integer getMsgid() {
			return msgid;
		}
		public void setMsgid(Integer msgid) {
			this.msgid = msgid;
		}
		public String getMrreason() {
			return mrreason;
		}
		public void setMrreason(String mrreason) {
			this.mrreason = mrreason;
		}
		public Integer getMrstate() {
			return mrstate;
		}
		public void setMrstate(Integer mrstate) {
			this.mrstate = mrstate;
		}
		
		
		
		
}
