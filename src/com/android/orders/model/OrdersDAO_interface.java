package com.android.orders.model;

import java.sql.Date;
import java.util.*;

import com.android.orderDetail.model.OrderDetailVO;

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
	
	//使用優惠券 修改總價
	public boolean updateOrdersAmount(Integer amount, String ordId);
	
	//找出要 checkin 的會員
	public OrdersCheckInOutVO findCheckinMember(String memId, Date checkin);
}
