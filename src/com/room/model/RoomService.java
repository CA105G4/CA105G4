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
}
