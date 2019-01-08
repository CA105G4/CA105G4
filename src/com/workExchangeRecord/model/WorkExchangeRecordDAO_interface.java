package com.workExchangeRecord.model;

import java.util.List;

public interface WorkExchangeRecordDAO_interface {
	public void insert(WorkExchangeRecordVO workExchangeRecordVO);  //新增
	public void update(WorkExchangeRecordVO workExchangeRecordVO); //修改
	public void updateByWE(WorkExchangeRecordVO workExchangeRecordVO); //依照需求更新狀態
	public WorkExchangeRecordVO findByPrimaryKey(Integer weID, String memID); //依需求編號及會員編號找一個打工紀錄
	public List<WorkExchangeRecordVO> getAll();  //列出所有打工需求紀錄
	public List<WorkExchangeRecordVO> getAllByWE(Integer weID);  //依照需求列出打工申請紀錄
}
