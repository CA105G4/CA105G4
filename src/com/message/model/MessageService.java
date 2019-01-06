package com.message.model;

import java.util.List;

public class MessageService {
	private MessageDAO_interface dao;
	public MessageService() {
		dao = new MessageJDBCDAO();
	}
	
	
	public MessageVO addMessage(Integer artid,String msgmemid,
			String msgcontent,java.sql.Date msgdate,Integer msgstate) {
		
			MessageVO messageVO = new MessageVO();
			
			messageVO.setArtid(artid);
			messageVO.setMsgmemid(msgmemid);
			messageVO.setMsgcontent(msgcontent);
			messageVO.setMsgdate(msgdate);
			messageVO.setMsgstate(msgstate);
			
			dao.insert(messageVO);
			return messageVO;
	}
	
	public MessageVO updateMessage(Integer msgid,Integer artid,String msgmemid,
			String msgcontent,java.sql.Date msgdate,Integer msgstate) {
		
			MessageVO messageVO = new MessageVO();
			
			messageVO.setMsgid(msgid);
			messageVO.setArtid(artid);
			messageVO.setMsgmemid(msgmemid);
			messageVO.setMsgcontent(msgcontent);
			messageVO.setMsgdate(msgdate);
			messageVO.setMsgstate(msgstate);
			
			dao.update(messageVO);
			return messageVO;
	}
	
	public void deleteMessage(Integer msgid) {
			dao.delete(msgid);
	}
	
	public MessageVO getOneMessage(Integer msgid) {
		return dao.findByPrimaryKey(msgid);
	}
	
	public List<MessageVO> getAll() {
		return dao.getAll();
	}
	
	public List<MessageVO> getArticleMessage(Integer artid) {
		return dao.findByArticle(artid);
	}
	
}
