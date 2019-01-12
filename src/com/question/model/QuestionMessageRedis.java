package com.question.model;

import java.util.Date;
import java.util.List;

public class QuestionMessageRedis{
	private String  alert;   
    private List<String>  names;
    private String  sendMsg;
    private String  from;
    private String  date;
    
    public QuestionMessageRedis() {
        super();
    }
    
    public QuestionMessageRedis(String alert, List<String> names, String sendMsg, String from, String date) {
		super();
		this.alert = alert;
		this.names = names;
		this.sendMsg = sendMsg;
		this.from = from;
		this.date = date;
	}
    
	public String getDate() { 
    	return date; 
    	} 
    public void setDate(String date) { 
    	this.date = date; 
    	} 
    public String getSendMsg() { 
    	return sendMsg; 
    	}
    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }
    public String getFrom() {
        return from;
    }
    public void setFrom(String from) {
        this.from = from;
    }

    public String getAlert() {
        return alert;
    }
    public void setAlert(String alert) {
        this.alert = alert;
    }
    public List<String> getNames() {
        return names;
    }
    public void setNames(List<String> names) {
        this.names = names;
    }
 
}
