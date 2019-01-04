package com.workExchangeRecord.model;

import java.util.List;

public class WorkExchangeRecordService {

	private WorkExchangeRecordDAO_interface dao;
	
	public WorkExchangeRecordService() {
		dao = new WorkExchangeRecordDAO();
	}
	
	public WorkExchangeRecordVO addWER(Integer weID, String memID, Integer werState, String orderID, byte[] weApp){
			 WorkExchangeRecordVO workExchangeRecordVO = new WorkExchangeRecordVO();
			 
			 workExchangeRecordVO.setWeID(weID);
			 workExchangeRecordVO.setMemID(memID);
			 workExchangeRecordVO.setWerState(werState);
			 workExchangeRecordVO.setOrderID(orderID);
			 workExchangeRecordVO.setWeApp(weApp);
			 dao.insert(workExchangeRecordVO);
			 
			 return workExchangeRecordVO; 
		 }
		public WorkExchangeRecordVO updateWER(Integer weID, String memID, Integer werState, String orderID, byte[] weApp){
			WorkExchangeRecordVO workExchangeRecordVO = new WorkExchangeRecordVO();
			
			workExchangeRecordVO.setWeID(weID);
			workExchangeRecordVO.setMemID(memID);
			workExchangeRecordVO.setWerState(werState);
			workExchangeRecordVO.setOrderID(orderID);
			workExchangeRecordVO.setWeApp(weApp);
			dao.update(workExchangeRecordVO);
			
			return workExchangeRecordVO; 
		}
	
		public WorkExchangeRecordVO getOneWER(Integer weID,String memID) {
			return dao.findByPrimaryKey(weID, memID);
		} 
		 
		public List<WorkExchangeRecordVO> getAll(){
			return dao.getAll();
		} 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
