package com.question.model;

public class QuestionRedisVO {
	private String to;
	private String from;
    private String msg;
    private Integer type;
    
    public QuestionRedisVO(String to,String from, String msg,Integer type) {
    	this.from = from;
    	this.to= to;
    	this.msg = msg;
    	this.type = type;
    }
    
    
    public String getTo() {
        return to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
    
}
