package com.orders.model;

import java.util.*;

public interface OrdersDAO_interface {
	public void insert(OrdersVO ordersVO);
	public void update(OrdersVO ordersVO);
	public OrdersVO findByPrimaryKey(String ordID);
	public List<OrdersVO> getAll();

}
