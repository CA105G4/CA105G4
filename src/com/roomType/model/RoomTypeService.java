package com.roomType.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class RoomTypeService {
	
	private RoomTypeDAO_interface dao;
	
	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}
	
	public RoomTypeVO addRoomType(String braID, String rtName, byte[] rtPic, String rtIntro, Integer rtMinimum, Integer rtLimit, Integer weeklyPrice, Integer holidayPrice, Integer total) {
		RoomTypeVO rtVO = new RoomTypeVO();
		rtVO.setBraID(braID);
		rtVO.setRtName(rtName);
		rtVO.setRtPic(rtPic);
		rtVO.setRtIntro(rtIntro);
		rtVO.setRtMinimum(rtMinimum);
		rtVO.setRtLimit(rtLimit);
		rtVO.setWeeklyPrice(weeklyPrice);
		rtVO.setHolidayPrice(holidayPrice);
		rtVO.setTotal(total);
		
		dao.insert(rtVO);
		
		return rtVO;
	}
	
	public RoomTypeVO updateRoomType(String rtID, String braID, String rtName, byte[] rtPic, String rtIntro, Integer rtMinimum, Integer rtLimit, Integer weeklyPrice, Integer holidayPrice, Integer total) {
		
		RoomTypeVO rtVO = new RoomTypeVO();
		rtVO.setRtID(rtID);
		rtVO.setBraID(braID);
		rtVO.setRtName(rtName);
		rtVO.setRtPic(rtPic);
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
	
	public List<RoomTypeVO> getAll() {
		return dao.getAll();
	}
	
	public Set<RoomTypeVO> getAllInSet() {
		return dao.getAllInSet();
	}
	
	/**[Gina]{Ajax}用分店找房型**/
	public List<RoomTypeVO> findRoomTypeByBraID(String braID) {
		return dao.findRoomTypeByBranch(braID);
	}
	/**[Gina]{Ajax}用分店找房型**/
	
	/**[Gina]用房型找平日價格**/
	public int findWeekpriceByrtID(String rtID) {
		return dao.findWeekpriceByrtID(rtID);
	}
	/**[Gina]用房型找價格**/
	
	/**[Gina]用房型找假日價格**/
	public int findHollydaypriceByrtID(String rtID) {
		return dao.findHollydaypriceByrtID(rtID);
	}
	/**[Gina]用房型找價格**/
	
	/**[Gina]排程器使用，在當天11:59分，將當天房型數量變回原房型數量**/
	public void returnRoomNum(String balance, String rtID) {
		dao.returnRoomNum(balance, rtID);
	}
	/**[Gina]排程器使用，在當天11:59分，將當天房型數量變回原房型數量**/
	
	/**[Gina]取消訂單用，找到該訂單明細中的房型，並把原本有預約的房型數量+1**/
	public void cancelOrderChangeRoomBalance(String rtID, Date checkIn, Date checkOut) {
		dao.cancelOrderChangeRoomBalance(rtID, checkIn, checkOut);
	}
	/**[Gina]取消訂單用，找到該訂單明細中的房型，並把原本有預約的房型數量+1**/
}