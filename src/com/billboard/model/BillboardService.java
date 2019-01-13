package com.billboard.model;

import java.sql.Timestamp;
import java.util.List;

public class BillboardService {
	
	private BillboardDAO_interface dao;
	
	public BillboardService() {
		dao =new BillboardDAO();
	}
	
	public BillboardVO addBB(String url, byte[] pic, Timestamp bbStart, Timestamp bbEnd,Integer bbStatus ) {
		BillboardVO bbVO=new BillboardVO();
		
		bbVO.seturl(url);
		bbVO.setpic(pic);
		bbVO.setbbStart(bbStart);		
		bbVO.setbbEnd(bbEnd);
		bbVO.setbbStatus(bbStatus);
		dao.insert(bbVO);
		
		return bbVO;
	}
	
	public BillboardVO updateBB(String url, byte[] pic,Timestamp bbStart,Timestamp bbEnd,Integer bbID,Integer bbStatus) {
		BillboardVO bbVO = new BillboardVO(); 
	
		bbVO.seturl(url);
		bbVO.setpic(pic);
		bbVO.setbbStart(bbStart);
		bbVO.setbbEnd(bbEnd);
		bbVO.setbbID(bbID);
		bbVO.setbbStatus(bbStatus);
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
