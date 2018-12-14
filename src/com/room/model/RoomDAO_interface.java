package com.room.model;

import java.util.List;

public interface RoomDAO_interface {
	public void insert(RoomVO roomVO); //新增房間
	public void update(RoomVO roomVO); //修改房間
	public RoomVO findByPrimaryKey(String roomID); //用PK找到對應的房間
	public List<RoomVO> getAll(); //取得所有房間之列表
}
