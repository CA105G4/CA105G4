package com.message.model;

import java.util.List;

public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO);
	public void delete(Integer msgid);
	public MessageVO findByPrimaryKey(Integer msgid);
	public List<MessageVO> getAll();
}
