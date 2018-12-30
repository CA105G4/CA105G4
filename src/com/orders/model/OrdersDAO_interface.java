package com.orders.model;

import java.util.*;
import com.orderDetail.model.OrderDetailVO;

public interface OrdersDAO_interface {
	public void insert(OrdersVO ordersVO);
	public void update(OrdersVO ordersVO);
	public OrdersVO findByPrimaryKey(String ordID);
	public List<OrdersVO> getAll();
	//查詢某訂單的明細(一對多)(回傳 Set)
    public Set<OrderDetailVO> getOrderDetailByOrders(String ordID);
    
	//[Gina]{訂單交易}	同時新增訂單與明細
	public void insertwithOrderDetail(OrdersVO ordersVO, List<OrderDetailVO> odlist);
}
