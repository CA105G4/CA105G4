package com.android.member.model;

public class MemberService {

	private MemberDAO_interface dao;
	
	public MemberService() {
		dao = new MemberDAO();
	}
	
	public String isMember(String account, String password) {
		return dao.isMember(account, password);
	}
	
	public MemberVO getMemberInfo(String memId) {
		return dao.findByMemId(memId);
	}
	
	public byte[] getMemberImage(String memId) {
		return dao.getImage(memId);
	}
}
