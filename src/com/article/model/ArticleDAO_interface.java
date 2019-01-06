package com.article.model;

import java.util.*;

public interface ArticleDAO_interface {
		public void insert(ArticleVO articleVO);
		public void update(ArticleVO articleVO);
		public void delete(Integer artid);
		public ArticleVO findByPrimaryKey(Integer artid);
		public List<ArticleVO> getAll();
		public List<ArticleVO> UniqueMember();
		public List<ArticleVO> findByMember(String memid);
		public List<ArticleVO> getLatestThree();
}
