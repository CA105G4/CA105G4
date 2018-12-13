package com.messagereport.model;

import java.util.List;


public interface MessageRepDAO_interface {
	
	public void insert(MessageRepVO messageRepVO);
	public void update(MessageRepVO messageRepVO);
	public void delete(Integer mrid);
	public MessageRepVO findByPrimaryKey(Integer mrid);
	public List<MessageRepVO> getAll();

}
