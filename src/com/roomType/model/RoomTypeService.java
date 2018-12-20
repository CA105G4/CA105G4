package com.roomType.model;

import java.util.List;

public class RoomTypeService {
	
	private RoomTypeDAO_interface dao;
	
	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}
	
	public RoomTypeVO getOneRoomType(String rtID) {
		return dao.findByPrimaryKey(rtID);
	}
	
	public List<RoomTypeVO> getAll() {
		return dao.getAll();
	}

}