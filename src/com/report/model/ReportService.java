package com.report.model;

import java.util.List;

public class ReportService {
	
	private ReportDAO_interface dao;
	
	public ReportService() {
		dao = new ReportJDBCDAO();
	}
	
	public ReportVO addReport(Integer artid,String repreason,String repmemid,
			java.sql.Date repdate,Integer repstate) {
		
		ReportVO reportVO = new ReportVO();
		
		reportVO.setArtid(artid);
		reportVO.setRepreason(repreason);
		reportVO.setRepmemid(repmemid);
		reportVO.setRepdate(repdate);
		reportVO.setRepstate(repstate);
		
		dao.insert(reportVO);
		return reportVO;
	}
	
	public ReportVO updateReport(Integer repid,Integer artid,String repreason,String repmemid,
			java.sql.Date repdate,Integer repstate) {
		
		ReportVO reportVO = new ReportVO();
		
		reportVO.setRepid(repid);
		reportVO.setArtid(artid);
		reportVO.setRepreason(repreason);
		reportVO.setRepmemid(repmemid);
		reportVO.setRepdate(repdate);
		reportVO.setRepstate(repstate);
		
		dao.update(reportVO);
		return reportVO;
	}
	
	public void deleteReport(Integer repid) {
		dao.delete(repid);
	}
	
	public ReportVO getOneReport(Integer repid) {
		return dao.findByPrimaryKey(repid);
	}
	
	public List<ReportVO> getAll(){
		return dao.getAll();
	}
	
	public ReportVO updateArticleStatus(Integer repid,Integer artid,String repreason,String repmemid,
			java.sql.Date repdate,Integer repstate) {
		
		ReportVO reportVO = new ReportVO();
		
		reportVO.setRepid(repid);
		reportVO.setArtid(artid);
		reportVO.setRepreason(repreason);
		reportVO.setRepmemid(repmemid);
		reportVO.setRepdate(repdate);
		reportVO.setRepstate(repstate);
		
		dao.updateArticleStatus(reportVO);
		return reportVO;
	}
}
