package com.orderDetail.model;

import java.util.*;

import com.orders.model.OrdersVO;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public void delete(Integer odID);
	public OrderDetailVO findByPrimaryKey(Integer odID);
	public List<OrderDetailVO> getALL();
	//查詢某訂單的明細(是否放在這?)
	public Set<OrderDetailVO> findByOrders(String ordID);
	
	//[Gina]{訂單交易}	同時新增訂單與明細
	public void insertwithOrders(OrderDetailVO orderDetailVO, java.sql.Connection con);

}
