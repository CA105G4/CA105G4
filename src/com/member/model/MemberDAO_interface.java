package com.member.model;

import java.util.List;

public interface MemberDAO_interface {

	public void insert(MemberVO  memberVO); //新增會員
	public void update(MemberVO  memberVO); //修改會員
	public MemberVO findByPK(String memID); //用PK找對應的會員
	public List<MemberVO> getAll(); //取得全部會員之列表

}
