package com.billboard.model;

import java.sql.Date;
import java.util.List;

public class BillboardService {
	
	private BillboardDAO_interface dao;
	
	public BillboardService() {
		dao =new BillboardDAO();
	}
	
	public BillboardVO addBB(String url, byte[] pic, Date bbStart, Date bbEnd ) {
		BillboardVO bbVO=new BillboardVO();
		
		bbVO.seturl(url);
		bbVO.setpic(pic);
		bbVO.setbbStart(bbStart);		
		bbVO.setbbEnd(bbEnd);
		dao.insert(bbVO);
		
		return bbVO;
	}
	
	public BillboardVO updateBB(String url, byte[] pic,Date bbStart,Date bbEnd,Integer bbID) {
		BillboardVO bbVO = new BillboardVO(); 
	
		bbVO.seturl(url);
		bbVO.setpic(pic);
		bbVO.setbbStart(bbStart);
		bbVO.setbbEnd(bbEnd);
		bbVO.setbbID(bbID);
		dao.update(bbVO);
		return 	bbVO;
	}
		
	public void deleteBB(Integer bbID) {
		dao.delete(bbID);
	}
	
	public BillboardVO findByPK(Integer bbID) {
		return dao.findByPK(bbID);
		
	}
	
	
	public List<BillboardVO>getAll(){
		return dao.getAll();
	}
	
	
}
