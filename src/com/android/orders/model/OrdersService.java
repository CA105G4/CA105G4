package com.android.orders.model;

import java.sql.Date;
import java.util.*;

import com.android.orderDetail.model.OrderDetailVO;

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
	
	public OrdersVO updateOrders(String ordID, String memID, String braID, Integer numOfRoom, Integer ordType, Integer numOfGuest, Integer amount, Integer bond, Integer payment, Integer ordState) {
		
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
	public OrdersVO insertwithOrderDetail(OrdersVO ordersVO, List<OrderDetailVO> odlist, Map<String,Integer> rtIDandNumMap) {
		dao.insertwithOrderDetail(ordersVO, odlist, rtIDandNumMap);
		return ordersVO;
	}
	
	//[Gina]{checkin}查找當日checkIn的訂單與明細
	public List<OrdersCheckInOutVO> findCheckInByOrdJoinOD(Date checkIn, String braID) {
		return dao.findCheckInByOrdJoinOD(checkIn, braID);
	}
	
	//[Gina]{checkout}查找當日checkOut的訂單與明細
	public List<OrdersCheckInOutVO> findCheckOut_ByOrdJoinOD(Date checkOut, String braID) {
		return dao.findCheckOut_ByOrdJoinOD(checkOut, braID);
	}
	
	//[Gina]{checkout}查找當日checkOut的訂單與明細
	public void updateOrdState(Integer ordState, String ordID) {
		dao.updateOrdState(ordState, ordID);
	}
	
	//使用優惠券 修改總價
	public boolean updateOrdersAmount(Integer amount, String ordId) {
		return dao.updateOrdersAmount(amount, ordId);
	}
	
	//找出要 checkin 的會員
	public OrdersCheckInOutVO getCheckinMember(String memId, Date checkin) {
		return dao.findCheckinMember(memId, checkin);
	}
	
	
}
