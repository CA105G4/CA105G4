package com.authorityRecord.model;

import java.util.List;
import java.util.Set;



public interface AuthorityRecordDAO_interface {
	public void insert(AuthorityRecordVO authorityRecordVO); // 新增員工權限
	public void update(AuthorityRecordVO authorityRecordVO); // 修改員工權限
	public void delete(String empID);
	public List<AuthorityRecordVO> getAll();        // 取得全部權限紀錄
	      // 取得全部權限紀錄
	public AuthorityRecordVO findByPK(String empID);
	public Set<AuthorityRecordVO> getAuthIDByEmpID(String empID);
	
}
