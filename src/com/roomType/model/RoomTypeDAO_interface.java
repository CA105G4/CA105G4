package com.roomType.model;

import java.sql.Date;
import java.util.*;

public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO roomTypeVO);
	public void update(RoomTypeVO roomTypeVO);
	public void delete(String rtID);
	public RoomTypeVO findByPrimaryKey(String rtID);
	public List<RoomTypeVO> getAll();
	
	//[Gina]{訂單交易}	新增訂單與明細的同時，更改房型的剩餘房間數欄位
	public void updateRoomBalance(String rtID, Date checkIn, Date checkOut, java.sql.Connection con, Map<String,Integer> rtIDandNumMap);
	
	/**[Gina]{Ajax}用分店找房型**/
	public List<RoomTypeVO> findRoomTypeByBranch(String braID);
	/**[Gina]{Ajax}用分店找房型**/
	
	/**[Gina]用房型找平日價格**/
	public int findWeekpriceByrtID(String rtID);
	/**[Gina]用房型找平日價格**/
	
	/**[Gina]用房型找假日價格**/
	public int findHollydaypriceByrtID(String rtID);	
	/**[Gina]用房型找假日價格**/
	
	/**[Gina]排程器使用，在當天11:59分，將當天房型數量變回原房型數量**/
	public void returnRoomNum(String balance, String rtID);
	/**[Gina]排程器使用，在當天11:59分，將當天房型數量變回原房型數量**/
	
	/**[Gina]取消訂單用，找到該訂單明細中的房型，並把原本有預約的房型數量+1**/
	public void cancelOrderChangeRoomBalance(String rtID, Date checkIn, Date checkOut);
	/**[Gina]取消訂單用，找到該訂單明細中的房型，並把原本有預約的房型數量+1**/
}
