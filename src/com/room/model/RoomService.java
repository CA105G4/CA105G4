package com.room.model;

import java.util.List;

public class RoomService {
	
	private RoomDAO_interface dao;
	
	public RoomService() {
		dao = new RoomDAO();
	} 
	
	public RoomVO addRoom(String roomTypeID, String braID, Integer roomNo,
			Integer roomState, String memName) {
		RoomVO roomVO = new RoomVO();
		
//		roomVO.setRoomID(roomID);
		roomVO.setRoomTypeID(roomTypeID);
		roomVO.setBraID(braID);
		roomVO.setRoomNo(roomNo);
		roomVO.setRoomState(roomState);
		roomVO.setMemName(memName);
		dao.insert(roomVO);
		
		return roomVO;
	}
	
	public RoomVO updateRoom(String roomID, String roomTypeID, String braID, Integer roomNo, Integer roomState, String memName) {
		RoomVO roomVO = new RoomVO();
		
		roomVO.setRoomID(roomID);
		roomVO.setRoomTypeID(roomTypeID);
		roomVO.setBraID(braID);
		roomVO.setRoomNo(roomNo);
		roomVO.setRoomState(roomState);
		roomVO.setMemName(memName);
		dao.update(roomVO);
		
		return roomVO;
	}
	
	public RoomVO getOneRoom(String roomID) {
		return dao.findByPrimaryKey(roomID);
	}
	
	public List<RoomVO> getAll(){
		return dao.getAll();
	}
	
	public List<RoomVO> getRoomByBranch(String braID){
		return dao.findRoomByBranch(braID);
	}
	public List<RoomVO> getRoomForAssign(String braID, String roomTypeID, Integer roomState){
		return dao.findRoomForAssign(braID, roomTypeID, roomState);
	}
	
	/**[Gina]CHECKIN更改房間狀態)**/
	public void updateRoomState(Integer roomState, String memname, String roomID) {
		dao.updateRoomState(roomState, memname, roomID);
	}
	/**[Gina]CHECKIN更改房間狀態)**/
	
	//依房號去更新房間狀態(分店房號都要不同)
	public void updateRSByRoomNo(Integer roomState, Integer roomNo) {
		dao.updateRSByRoomNo(roomState, roomNo);
	}

	//依分店房間狀態取得各房間狀態間數
	public int getEachRoomState(Integer roomState, String braID) {
		return dao.getEachRoomState(roomState, braID);
	}
	
	//依房間ID去更新房間狀態
	public void updateRSByRoomID(Integer roomState, String roomID) {
		dao.updateRSByRoomID(roomState, roomID);
	}
	
	
}
