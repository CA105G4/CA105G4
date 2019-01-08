package com.orders.model;

import java.sql.Date;
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
	
	//查詢單筆訂單的明細
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
	
	//[Gina]{checkout}修改訂單的狀態
	public void updateOrdState(Integer ordState, String ordID) {
		dao.updateOrdState(ordState, ordID);
	}
	
	//[addOrders_Step2]查詢
	public String findNewOrderID(String memID) {
		return dao.findNewOrderID(memID);
	};
	
	//用分店查找訂單
	public List<OrdersVO> findBybraID(String braID){
		return dao.findBybraID(braID);
	};
	
	//用分店及訂單種類ordType(0線上,1臨櫃)查找訂單
	public List<OrdersVO> findByordType01(String braID){
		return dao.findByordType01(braID);
	};
	
	//用分店及訂單種類ordType(2打工換宿)查找訂單
	public List<OrdersVO> findByordType2(String braID){
		return dao.findByordType2(braID);
	};
	
	//用分店及訂單狀態ordState(3退訂)查找訂單
	public List<OrdersVO> findByordState3(String braID){
		return dao.findByordState3(braID);
	};
	
	//用會員memID及訂單狀態ordState(0預訂)查找訂單
	public List<OrdersVO> findOrdersBymemIDordState0(String memID){
		return dao.findOrdersBymemIDordState0(memID);
	};
	
	//用會員memID及訂單狀態ordState(1入住2退房3退房)查找訂單
	public List<OrdersVO> findOrdersBymemIDordState123(String memID){
		return dao.findOrdersBymemIDordState123(memID);
	};
	
	//[Gina]{加床}加床要更改訂單總金額
	public void addBedupdateAmount(Integer amount, String ordID) {
		dao.addBedupdateAmount(amount, ordID);
	}
	
}
