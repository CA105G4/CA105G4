package com.question.model;

import java.util.List;

public class QuestionService {
	private QuestionDAO_interface dao;
	public QuestionService() {
		dao = new QuestionJDBCDAO();
	}
	
	public QuestionVO addQuestion(String memid,String empid,String quescontent) {
		 
		QuestionVO questionVO = new QuestionVO();
		
		questionVO.setMemid(memid);
		questionVO.setEmpid(empid);
		questionVO.setQuescontent(quescontent);
		
		dao.insert(questionVO);
		return questionVO;
	}
	
	public QuestionVO updateQuestion(Integer quesid ,String memid,String empid,String quescontent) {
		 
		QuestionVO questionVO = new QuestionVO();
		
		questionVO.setQuesid(quesid);
		questionVO.setMemid(memid);
		questionVO.setEmpid(empid);
		questionVO.setQuescontent(quescontent);
		
		dao.insert(questionVO);
		return questionVO;
	}
	
	public void deleteQuestion(Integer quesid) {
		dao.delete(quesid);
	}
	
	public QuestionVO getOneQuestion(Integer quesid) {
		return dao.findByPrimaryKey(quesid);
	}
	
	public List<QuestionVO> getAll(){
		return dao.getAll();
	}
	

}
