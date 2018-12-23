package com.orderDetail.model;

import java.util.*;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public void delete(Integer odID);
	public OrderDetailVO findByPrimaryKey(Integer odID);
	public List<OrderDetailVO> getALL(); 
	
	public List<OrderDetailVO> findByOrders(String ordID);

}
