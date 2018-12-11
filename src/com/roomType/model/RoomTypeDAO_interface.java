package com.roomType.model;

import java.util.*;

public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO roomTypeVO);
	public void update(RoomTypeVO roomTypeVO);
	public void delete(String rtID);
	public RoomTypeVO findByPrimaryKey(String rtID);
	public List<RoomTypeVO> getAll();
	
}
