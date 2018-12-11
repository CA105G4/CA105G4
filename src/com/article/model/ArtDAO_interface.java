package com.article.model;

import java.util.*;

public interface ArtDAO_interface {
		public void insert(ArtVO artVO);
		public void update(ArtVO artVO);
		public void delete(Integer artid);
		public ArtVO findByPrimaryKey(Integer artid);
		public List<ArtVO> getAll();
}
