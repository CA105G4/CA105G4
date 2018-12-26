package com.roomType.model;

import java.util.*;
import com.room.model.RoomVO;


public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO roomTypeVO);
	public void update(RoomTypeVO roomTypeVO);
	public void delete(String rtID);
	public RoomTypeVO findByPrimaryKey(String rtID);
	public List<RoomTypeVO> getAll();
	
	//更改房型的剩餘房間數欄位
	public void updateRoomBalance(String balance, String rtID);
}
