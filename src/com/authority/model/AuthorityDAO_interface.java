package com.authority.model;

import java.util.List;
import java.util.Set;

import com.authorityRecord.model.AuthorityRecordVO;

public interface AuthorityDAO_interface  {
	
	
	public void insert(AuthorityVO authorityVO); // 新增權限
	public void update(AuthorityVO authorityVO); // 修改權限
	public AuthorityVO findByPK(Integer authID);   // 用PK找對應權限
	public List<AuthorityVO> getAll();        // 取得全部權限
	public Set<AuthorityRecordVO> getEmpsByAuthID(Integer authID);
}
