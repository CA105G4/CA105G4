package com.collectRoomType.model;

import java.util.List;

public interface CollectRoomType_interface {
	public void insert(CollectRoomTypeVO collectRoomTypeVO);  // 新增房型收藏
	public void delete(String memID, String rtID);            // 刪除房型收藏紀錄
	public List<CollectRoomTypeVO> findByMemID(String memID);    // 搜尋會員已收藏房型
}
