package com.authorityRecord.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AuthorityRecordService {
	private AuthorityRecordDAO_interface dao;

	public AuthorityRecordService() {
		dao = new AuthorityRecordJDBCDAO();
	}

	public AuthorityRecordVO addAuth(Integer authID, String empID) {

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

	public AuthorityRecordVO getOneupdate(String empID,Integer authID) {

		AuthorityRecordVO authorityRecordVO = new AuthorityRecordVO();
		
		
		authorityRecordVO.setAuthID(authID);
		authorityRecordVO.setEmpID(empID);
		
		dao.update(authorityRecordVO);
		
		
		return authorityRecordVO;
	}
	
	public AuthorityRecordVO getOneEmp(String empID) {
		return dao.findByPK(empID);
	}
	public List<AuthorityRecordVO> getAll() {
		return dao.getAll();
	}
	
	public Set<AuthorityRecordVO> getAuthIDByEmpID(String empID) {
		return dao.getAuthIDByEmpIDSet(empID);
	}
	
	public List<String> getAuthIDByEmpIDList(String empId){
		
		List<String> authList = new ArrayList<String>();
		List<AuthorityRecordVO> list = dao.getAuthIdByEmpIdList(empId);
		
		for(int i = 0; i < list.size(); i++) {
			authList.add(String.valueOf(list.get(i).getAuthID()));
			System.out.println(authList.get(i));
		}
		
		return authList;
		
//		return dao.getAuthIdByEmpIdList(empId);
	}
	
}
