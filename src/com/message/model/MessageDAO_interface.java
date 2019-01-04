package com.message.model;

import java.util.List;

import com.article.model.ArticleVO;

public interface MessageDAO_interface {
	public void insert(MessageVO messageVO);
	public void update(MessageVO messageVO);
	public void delete(Integer msgid);
	public MessageVO findByPrimaryKey(Integer msgid);
	public List<MessageVO> getAll();
	public List<MessageVO> findByArticle(Integer artid);
}
