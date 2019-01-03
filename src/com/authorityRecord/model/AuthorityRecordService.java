package com.authorityRecord.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;


public class AuthorityRecordService {
	private AuthorityRecordDAO_interface dao;

public AuthorityRecordService() {
	dao = new AuthorityRecordJDBCDAO();
}
public AuthorityRecordVO addAuth(Integer authID,String empID) {

	AuthorityRecordVO authorityRecordVO = new AuthorityRecordVO();
	
	
	authorityRecordVO.setAuthID(authID);
	authorityRecordVO.setEmpID(empID);
	
	
	dao.insert(authorityRecordVO);

	return authorityRecordVO;
}

public void deleteEmp(String empID) {
	dao.delete(empID);
}


public AuthorityRecordVO getOneauth(String empID) {
	return dao.findByPK(empID);
}
public Set<AuthorityRecordVO> getAuthIDByEmpID(String empID) {
	return dao.getAuthIDByEmpID(empID);
}

public List<AuthorityRecordVO> getAll() {
	return dao.getAll();
}
}
