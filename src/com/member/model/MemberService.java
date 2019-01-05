package com.member.model;

import java.sql.Date;
import java.util.List;



public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberJDBCDAO();
}
	public MemberVO addMem(String memName,String memAcc,String memPsw,Date memBirth,String memEmail,String memTel,String memAddr,String memSex,String memSkill,byte[]memPic,String memIDcard) {

		MemberVO memberVO = new MemberVO();
		
		
		memberVO.setMemName(memName);
		memberVO.setMemAcc(memAcc);
		memberVO.setMemPsw(memPsw);
		memberVO.setMemBirth(memBirth);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemTel(memTel);
		memberVO.setMemAddr(memAddr);
		memberVO.setMemSex(memSex);
		memberVO.setMemSkill(memSkill);
		memberVO.setMemPic(memPic);
		memberVO.setMemIDcard(memIDcard);
		
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMem(String memID,String memName,String memAcc,String memPsw,Date memBirth,String memEmail,String memTel,String memAddr,String memSex,String memSkill,Integer memState,byte[]memPic,Date memReg,String memIDcard) {

		MemberVO memberVO = new MemberVO();
		
		memberVO.setMemID(memID);
		memberVO.setMemName(memName);
		memberVO.setMemAcc(memAcc);
		memberVO.setMemPsw(memPsw);
		memberVO.setMemBirth(memBirth);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemTel(memTel);
		memberVO.setMemAddr(memAddr);
		memberVO.setMemSex(memSex);
		memberVO.setMemSkill(memSkill);
		memberVO.setMemState(memState);
		memberVO.setMemReg(memReg);
		memberVO.setMemPic(memPic);
		
		memberVO.setMemIDcard(memIDcard);
		
		dao.update(memberVO);

		return memberVO;
	}

	

	public MemberVO getOneMem(String memID) {
		return dao.findByPK(memID);
	}

	public List<MemberVO> getAll() {
		return dao.getAll();
	}
}
