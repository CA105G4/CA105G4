package com.messageReport.model;

import java.util.List;

import com.report.model.ReportVO;

public class MessageReportService {
	
	private MessageReportDAO_interface dao;
	
	public MessageReportService() {
		dao = new MessageReportJDBCDAO();
	}
	
	public MessageReportVO addMessageReport(Integer artid,Integer msgid
			,String mrreason,Integer mrstate) {
		
		   MessageReportVO messageReportVO = new MessageReportVO();
		   messageReportVO.setArtid(artid);
		   messageReportVO.setMsgid(msgid);
		   messageReportVO.setMrreason(mrreason);
		   messageReportVO.setMrstate(mrstate);
		   dao.insert(messageReportVO);
		   
		   return messageReportVO;
	}
	
	public MessageReportVO updateMessageReport(Integer mrid,Integer artid,Integer msgid
			,String mrreason,Integer mrstate) {
		
		   MessageReportVO messageReportVO = new MessageReportVO();
		   messageReportVO.setMrid(mrid);
		   messageReportVO.setArtid(artid);
		   messageReportVO.setMsgid(msgid);
		   messageReportVO.setMrreason(mrreason);
		   messageReportVO.setMrstate(mrstate);
		   dao.insert(messageReportVO);
		   
		   return messageReportVO;
	}
	
	public void deleteMessageReport(Integer mrid) {
			dao.delete(mrid);
	}
	
	
	public MessageReportVO getOneMessageReport(Integer mrid) {
		return dao.findByPrimaryKey(mrid);
	}
	
	public List<MessageReportVO> getAll(){
		return dao.getAll();
	}
	
	public MessageReportVO updateMessageStatus(Integer mrid,Integer artid,Integer msgid
			,String mrreason,Integer mrstate) {
		
		   MessageReportVO messageReportVO = new MessageReportVO();
		   messageReportVO.setMrid(mrid);
		   messageReportVO.setArtid(artid);
		   messageReportVO.setMsgid(msgid);
		   messageReportVO.setMrreason(mrreason);
		   messageReportVO.setMrstate(mrstate);
		   dao.updateMessageStatus(messageReportVO);
		   
		   return messageReportVO;
	}
}
