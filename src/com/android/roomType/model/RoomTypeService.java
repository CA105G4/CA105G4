package com.android.roomType.model;

import java.util.List;

public class RoomTypeService {
	
	private RoomTypeDAO_interface dao;
	
	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}
	
	public RoomTypeVO addRoomType(String braID, String rtName, String rtIntro, Integer rtMinimum, Integer rtLimit, Integer weeklyPrice, Integer holidayPrice, Integer total) {
		RoomTypeVO rtVO = new RoomTypeVO();
		rtVO.setBraID(braID);
		rtVO.setRtName(rtName);
		rtVO.setRtIntro(rtIntro);
		rtVO.setRtMinimum(rtMinimum);
		rtVO.setRtLimit(rtLimit);
		rtVO.setWeeklyPrice(weeklyPrice);
		rtVO.setHolidayPrice(holidayPrice);
		rtVO.setTotal(total);
		
		dao.insert(rtVO);
		
		return rtVO;
	}
	
	public RoomTypeVO updateRoomType(String rtID, String braID, String rtName, String rtIntro, Integer rtMinimum, Integer rtLimit, Integer weeklyPrice, Integer holidayPrice, Integer total) {
		
		RoomTypeVO rtVO = new RoomTypeVO();
		rtVO.setRtID(rtID);
		rtVO.setBraID(braID);
		rtVO.setRtName(rtName);
		rtVO.setRtIntro(rtIntro);
		rtVO.setRtMinimum(rtMinimum);
		rtVO.setRtLimit(rtLimit);
		rtVO.setWeeklyPrice(weeklyPrice);
		rtVO.setHolidayPrice(holidayPrice);
		rtVO.setTotal(total);
		
		dao.update(rtVO);
		
		return rtVO;
	}
	
	public RoomTypeVO getOneRoomType(String rtID) {
		return dao.findByPrimaryKey(rtID);
	}
	
	public List<RoomTypeVO> getAllRoomType() {
		return dao.getAll();
	}
	
	public List<RoomTypeBra> getRoomTypeByBraName(String braName){
		return dao.findByBraName(braName);
	}
	

	
	
	/**[Gina]{Ajax}用分店找房型**/
	public int findWeekpriceByrtID(String rtID) {
		return dao.findWeekpriceByrtID(rtID);
	}
	/**[Gina]用房型找價格**/
	public int findHollydaypriceByrtID(String rtID) {
		return dao.findHollydaypriceByrtID(rtID);
	}
	/**[Gina]用房型找價格**/
	
//	//更改房型的剩餘房間數欄位
//	public void updateRoomBalance(String balance, String rtID) {
//		dao.updateRoomBalance(balance, rtID);
//	}
	

}