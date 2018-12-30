package com.roomType.model;

import java.sql.Date;
import java.util.*;
import com.room.model.RoomVO;


public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO roomTypeVO);
	public void update(RoomTypeVO roomTypeVO);
	public void delete(String rtID);
	public RoomTypeVO findByPrimaryKey(String rtID);
	public List<RoomTypeVO> getAll();
	
	//[Gina]{訂單交易}	新增訂單與明細的同時，更改房型的剩餘房間數欄位
	public void updateRoomBalance(String rtID, Date checkIn, Date checkOut, java.sql.Connection con);
	
	//[Gina]{Ajax}用分店找房型
	public List<RoomTypeVO> findRoomTypeByBranch(String braID);
}
