package com.android.room.model;

import java.util.List;

public interface RoomDAO_interface {
	public void insert(RoomVO roomVO); //新增房間
	public void update(RoomVO roomVO); //修改房間狀態、旅客姓名
	public RoomVO findByPrimaryKey(String roomID); //用PK找到對應的房間
	public List<RoomVO> getAll(); //取得所有房間之列表
	public List<RoomVO> findRoomByBranch(String braID);//依照分店查找房間
	public List<RoomVO> findRoomForAssign(String braID, String roomTypeID, Integer roomState); //可依照分店、房型找尋不同狀態的房間
	
	/**[CHECKIN]Gina更改房間狀態)**/
	public void updateRoomState(Integer roomState, String memname, String roomID);
	/**[CHECKIN]Gina更改房間狀態)**/
	
	//依房號去更新房間狀態(分店房號都要不同)
	public void updateRSByRoomNo(Integer roomState, Integer roomNo);
	
	//依分店及狀態查詢各個房況總數
	public int getEachRoomState(Integer roomState, String braID);
	
	//依PK去更新房間狀態(1/11更新)
	public void updateRSByRoomID(Integer roomState, String roomID);
	
	//找出某分店且待打掃的
	public List<Room> getRoomByBranchClean(String braId, Integer roomState);   //依分店找出待打掃的房間 =>打掃中  3

}
