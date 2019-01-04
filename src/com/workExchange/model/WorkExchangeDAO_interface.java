package com.workExchange.model;

import java.util.List;

public interface WorkExchangeDAO_interface {
	public void insert(WorkExchangeVO workExchangeVO); //新增一個打工需求
	public void update(WorkExchangeVO workExchangeVO); //修改一個打工需求
	public void delete(Integer weID); //刪除一個打工需求
	public WorkExchangeVO findByPrimaryKey(Integer weID);  //用pk找出打工需求
	public List<WorkExchangeVO> getAll(); //列出所有打工需求
}
