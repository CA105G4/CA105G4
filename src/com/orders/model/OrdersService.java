package com.orders.model;

import java.util.*;

import com.orderDetail.model.OrderDetailVO;

public class OrdersService {
	
	private OrdersDAO_interface dao;
	
	public OrdersService() {
		dao = new  OrdersDAO();
	}
	
	public OrdersVO addOrders(String memID, String braID, Integer numOfRoom, Integer ordType, Integer numOfGuest, Integer amount, Integer bond, Integer payment) {
		
		OrdersVO ordVO = new OrdersVO();
		ordVO.setMemID(memID);
		ordVO.setBraID(braID);
		ordVO.setNumOfRoom(numOfRoom);
		ordVO.setOrdType(ordType);
		ordVO.setNumOfGuest(numOfGuest);
		ordVO.setAmount(amount);
		ordVO.setBond(bond);
		ordVO.setPayment(payment);
		
		dao.insert(ordVO);	//這行要過，才可能到下一行的return empVO
		
		return ordVO;
	}
	
	public OrdersVO updateOrders(String ordID, String memID, String braID, Integer numOfRoom, Integer ordType, Integer numOfGuest, Integer amount, Integer bond, Integer payment, Integer ordState, java.sql.Date OrdTime) {
		
		OrdersVO ordVO = new OrdersVO();
		
		ordVO.setOrdID(ordID);
		
		ordVO.setMemID(memID);
		ordVO.setBraID(braID);
		ordVO.setNumOfRoom(numOfRoom);
		ordVO.setOrdType(ordType);
		ordVO.setNumOfGuest(numOfGuest);
		ordVO.setAmount(amount);
		ordVO.setBond(bond);
		ordVO.setPayment(payment);
		ordVO.setOrdState(ordState);
		ordVO.setOrdTime(OrdTime);
		
		dao.update(ordVO);
		
		return ordVO;
	}
	
	
	public OrdersVO getOneOrders(String ordID) {
		return dao.findByPrimaryKey(ordID);
	}

	public Set<OrderDetailVO> getOrderDetailByOrders(String ordID){
		return dao.getOrderDetailByOrders(ordID);
	}
	
	public List<OrdersVO> getAll(){
		return dao.getAll();
	}
	
	//[Gina]{訂單交易}	同時新增訂單與明細
	public OrdersVO insertwithOrderDetail(OrdersVO ordersVO, List<OrderDetailVO> odlist) {
		dao.insertwithOrderDetail(ordersVO, odlist);
		return ordersVO;
	}
}
