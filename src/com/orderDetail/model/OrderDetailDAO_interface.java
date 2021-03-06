package com.orderDetail.model;

import java.util.*;


public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public void delete(Integer odID);
	public OrderDetailVO findByPrimaryKey(Integer odID);
	public List<OrderDetailVO> getALL();
	//查詢某訂單的明細(是否放在這?)
	public Set<OrderDetailVO> findByOrders(String ordID);
	
	//[Gina]{訂單交易}	同時新增訂單與明細
	public void insertwithOrders(OrderDetailVO orderDetailVO, java.sql.Connection con, Map<String,Integer> rtIDandNumMap);
	
	//[Gina]{CHECKIN} 訂單明細加入房間編號
	public void updateRoomID(String roomID, Integer odID);
	
	//[Gina]{加床}再訂單明細中改加床
	public void updateSpecial(Integer special, Integer odID);
	
	//[Gina]{給評價}再訂單明細中給評價
	public void updateEvaluates(Integer evaluates, Integer odID);
}
