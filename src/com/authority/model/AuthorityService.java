package com.authority.model;

import java.util.List;
import java.util.Set;

import com.authorityRecord.model.AuthorityRecordVO;


public class AuthorityService {
	private AuthorityDAO_interface dao;

	public AuthorityService() {
		dao = new AuthorityJDBCDAO();
	}
	public AuthorityVO addAuth(Integer authID,String authName ) {

		AuthorityVO authorityRecordVO = new AuthorityVO();
		
		
		authorityRecordVO.setAuthID(authID);
		authorityRecordVO.setAuthName(authName);
		
		
		dao.insert(authorityRecordVO);

		return authorityRecordVO;
	}
	public Set<AuthorityRecordVO> getEmpsByAuthID(Integer authID) {
		return dao.getEmpsByAuthID(authID);
	}
	


	public AuthorityVO getOneauth(Integer authID) {
		return dao.findByPK(authID);
	}

	public List<AuthorityVO> getAll() {
		return dao.getAll();
	}
	}
