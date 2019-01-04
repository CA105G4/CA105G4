package com.article.model;

import java.util.List;

public class ArticleService {
	
	private ArticleDAO_interface dao;
	public ArticleService() {
		dao = new ArticleJDBCDAO();
	}

	public ArticleVO addArticle(String memid, byte[] artpic,String artexp,
			Integer artstate, java.sql.Date artdate) {

		ArticleVO articleVO = new ArticleVO();

		articleVO.setMemid(memid);
		articleVO.setArtpic(artpic);;
		articleVO.setArtexp(artexp);;
		articleVO.setArtstate(artstate);
		articleVO.setArtdate(artdate);
		dao.insert(articleVO);

		return articleVO;
	}

	public ArticleVO updateAtricle(Integer artid, String memid, 
			byte[] artpic,String artexp,Integer artstate, java.sql.Date artdate) {

		ArticleVO articleVO = new ArticleVO();
		
		articleVO.setArtid(artid);
		articleVO.setMemid(memid);
		articleVO.setArtpic(artpic);;
		articleVO.setArtexp(artexp);;
		articleVO.setArtstate(artstate);
		articleVO.setArtdate(artdate);
		dao.update(articleVO);

		return articleVO;
	}

	public void deleteArticle(Integer artid) {
		dao.delete(artid);
	}

	public ArticleVO getOneArticle(Integer artid) {
		return dao.findByPrimaryKey(artid);
	}

	public List<ArticleVO> getAll() {
		return dao.getAll();
	}
	
	public List<ArticleVO> getUniqueMember() {
		return dao.UniqueMember();
	}
	
	public List<ArticleVO> getMemberArticle(String memid) {
		return dao.findByMember(memid);
	}
	
	public List<ArticleVO> getLastestThree() {
		return dao.getLatestThree();
	}
}
