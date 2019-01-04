package com.messageReport.model;

import java.util.List;


public interface MessageReportDAO_interface {
	
	public void insert(MessageReportVO messageReportVO);
	public void update(MessageReportVO messageReportVO);
	public void delete(Integer mrid);
	public MessageReportVO findByPrimaryKey(Integer mrid);
	public List<MessageReportVO> getAll();
	public void updateMessageStatus(MessageReportVO messageReportVO);
}
