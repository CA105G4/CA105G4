package com.workExchangeRecord.model;

import java.util.List;

public interface WorkExchangeRecordDAO_interface {
	public void insert(WorkExchangeRecordVO workExchangeRecordVO); 
	public void update(Integer werState, Integer weID, String memID); 
	public WorkExchangeRecordVO findByPrimaryKey(Integer weID); 
	public List<WorkExchangeRecordVO> getAll();  
}
