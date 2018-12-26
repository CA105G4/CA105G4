package com.room.model;

import java.util.List;

public interface RoomDAO_interface {
	public void insert(RoomVO roomVO); //新增房間
	public void update(RoomVO roomVO); //修改房間狀態、旅客姓名
	public RoomVO findByPrimaryKey(String roomID); //用PK找到對應的房間
	public List<RoomVO> getAll(); //取得所有房間之列表
	public List<RoomVO> findRoomByBranch(String braID);//依照分店查找房間
	public List<RoomVO> findRoomForAssign(String braID, String roomTypeID, Integer roomState); //可依照分店、房型找尋不同狀態的房間
}
