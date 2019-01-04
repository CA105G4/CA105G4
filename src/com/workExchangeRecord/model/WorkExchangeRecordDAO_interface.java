package com.workExchangeRecord.model;

import java.util.List;

public interface WorkExchangeRecordDAO_interface {
	public void insert(WorkExchangeRecordVO workExchangeRecordVO);  //新增
	public void update(WorkExchangeRecordVO workExchangeRecordVO); //修改
	public WorkExchangeRecordVO findByPrimaryKey(Integer weID, String memID); //依需求編號及會員編號找一個打工紀錄
	public List<WorkExchangeRecordVO> getAll();  //列出所有打工需求紀錄
}
