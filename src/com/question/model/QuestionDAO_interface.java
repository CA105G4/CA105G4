package com.question.model;

import java.util.List;


public interface QuestionDAO_interface {
	public void insert(QuestionVO questionVO);
	public void update(QuestionVO questionVO);
	public void delete(Integer quesid);
	public QuestionVO findByPrimaryKey(Integer quesid);
	public List<QuestionVO> getAll();
}
