package com.orders.model;

import java.sql.Date;
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
	public void insertwithOrderDetail(OrdersVO ordersVO, List<OrderDetailVO> odlist, Map<String,Integer> rtIDandNumMap);
	
	//[Gina]{checkin}查找當日checkIn的訂單與明細
	public List<OrdersCheckInOutVO> findCheckInByOrdJoinOD(Date checkIn, String braID);
	
	//[Gina]{checkout}查找當日checkOut的訂單與明細
	public List<OrdersCheckInOutVO> findCheckOut_ByOrdJoinOD(Date checkOut, String braID);
	
	//[Gina][CHECKIN]更改訂單狀態
	public void updateOrdState(Integer ordState, String ordID);
	
	//[addOrders_Step2]查詢
	public String findNewOrderID(String memID);
	
	//用分店查找訂單
	public List<OrdersVO> findBybraID(String braID);
	
	//用分店及訂單種類ordType(0線上,1臨櫃)查找訂單
	public List<OrdersVO> findByordType01(String braID);
	
	//用分店及訂單種類ordType(2打工換宿)查找訂單
	public List<OrdersVO> findByordType2(String braID);
	
	//用分店及訂單狀態ordState(3退訂)查找訂單
	public List<OrdersVO> findByordState3(String braID);
	
	//用會員memID及訂單狀態ordState(0預訂)查找訂單
	public List<OrdersVO> findOrdersBymemIDordState0(String memID);
	
	//用會員memID及訂單狀態ordState(1入住2退房3退房)查找訂單
	public List<OrdersVO> findOrdersBymemIDordState123(String memID);
	
	//[Gina]{加床}加床要更改訂單總金額
	public void addBedupdateAmount(Integer amount, String ordID);
	
}
