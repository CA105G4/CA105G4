package com.authorityRecord.model;

import java.util.List;



public interface AuthorityRecordDAO_interfacr {
	public void insert(AuthorityRecordVO authorityRecordVO); // 新增員工權限
	public void update(AuthorityRecordVO authorityRecordVO); // 修改員工權限
	
	public List<AuthorityRecordVO> getAll();        // 取得全部權限紀錄
}
