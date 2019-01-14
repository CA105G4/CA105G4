package com.collectRoomType.model;

import java.util.List;

public class CollectRoomTypeService {
	private CollectRoomType_interface dao;
	
	public CollectRoomTypeService () {
		dao = new CollectRoomTypeDAO();
	}
	
	public CollectRoomTypeVO addCRT(String memID,String rtID) {
		CollectRoomTypeVO  crtVO =new CollectRoomTypeVO(); 
		crtVO.setMemID(memID);
		crtVO.setRtID(rtID);
		dao.insert(crtVO);
		return crtVO;
	}
	
	public void deleteCRT(String memID, String rtID) {
		dao.delete(memID, rtID);
		
	}
	
	public List<CollectRoomTypeVO> findByMemID(String memID){
		return dao.findByMemID(memID);
	}

}
