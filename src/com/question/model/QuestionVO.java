package com.question.model;

public class QuestionVO implements java.io.Serializable{
	private Integer quesid;
	private String memid;
	private String empid;
	private String quescontent;
	public Integer getQuesid() {
		return quesid;
	}
	public void setQuesid(Integer quesid) {
		this.quesid = quesid;
	}
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getQuescontent() {
		return quescontent;
	}
	public void setQuescontent(String quescontent) {
		this.quescontent = quescontent;
	}
	
	
	
}
