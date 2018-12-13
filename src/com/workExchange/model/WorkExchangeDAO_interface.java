package com.workExchange.model;

import java.util.List;

public interface WorkExchangeDAO_interface {
	public void insert(WorkExchangeVO workExchangeVO);
	public void update(WorkExchangeVO workExchangeVO);
	public void delete(WorkExchangeVO workExchangeVO);
	public WorkExchangeVO findByPrimaryKey(Integer weID);
	public List<WorkExchangeVO> getAll();
}
