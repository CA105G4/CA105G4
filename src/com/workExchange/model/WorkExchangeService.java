package com.workExchange.model;

import java.util.List;

public class WorkExchangeService {

	private WorkExchangeDAO_interface dao;
	
	public WorkExchangeService() {
		dao = new WorkExchangeDAO();
	}
	
	public WorkExchangeVO addWE(String memID, String empID,String rtID, String weName,
		 String weContent , byte[] wePic, byte[] weVideo, java.sql.Date weStart, java.sql.Date weEnd){
			 WorkExchangeVO workExchangeVO = new WorkExchangeVO();
			 
			 workExchangeVO.setMemID(memID);
			 workExchangeVO.setEmpID(empID);
			 workExchangeVO.setRtID(rtID);
			 workExchangeVO.setWeName(weName);
			 workExchangeVO.setWeContent(weContent);
			 workExchangeVO.setWePic(wePic);
			 workExchangeVO.setWeVideo(weVideo);
			 workExchangeVO.setWeStart(weStart);
			 workExchangeVO.setWeEnd(weEnd);
			 dao.insert(workExchangeVO);
			 
			 return workExchangeVO; 
		 }
		public WorkExchangeVO updateWE(Integer weID,String empID, String memID,String rtID, String weName,
				String weContent , byte[] wePic, byte[] weVideo, java.sql.Date weStart, java.sql.Date weEnd){
			WorkExchangeVO workExchangeVO = new WorkExchangeVO();
			
			workExchangeVO.setWeID(weID);
			workExchangeVO.setEmpID(empID);
			workExchangeVO.setMemID(memID);
			workExchangeVO.setRtID(rtID);
			workExchangeVO.setWeName(weName);
			workExchangeVO.setWeContent(weContent);
			workExchangeVO.setWePic(wePic);
			workExchangeVO.setWeVideo(weVideo);
			workExchangeVO.setWeStart(weStart);
			workExchangeVO.setWeEnd(weEnd);
			dao.update(workExchangeVO);
			
			return workExchangeVO; 
		}
		
		public WorkExchangeVO updateMemID(Integer weID, String memID){
			WorkExchangeVO workExchangeVO = new WorkExchangeVO();
			
			workExchangeVO.setWeID(weID);
			workExchangeVO.setMemID(memID);
			dao.updateMemID(workExchangeVO);
			
			return workExchangeVO; 
		}
	
		public void deleteWe(Integer weID) {
			dao.delete(weID);
		} 
		 
		public WorkExchangeVO getOneWE(Integer weID) {
			return dao.findByPrimaryKey(weID);
		} 
		 
		public List<WorkExchangeVO> getAll(){
			return dao.getAll();
		} 
		public List<WorkExchangeVO> getAllEmpty(){
			return dao.getAllEmpty();
		} 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
