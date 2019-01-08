package com.android.member.model;

public interface MemberDAO_interface {
	public String isMember(String account, String password);
	public boolean update(MemberVO memberVO);
	public MemberVO findByMemId(String memId);
	public byte[] getImage(String memId);
}
