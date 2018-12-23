package com.orderDetail.model;

import java.sql.Date;
import java.util.List;

import com.orders.model.OrdersVO;

public class OrderDetailService {
	
	public OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO addOrderDetail(String roomID, String ordID, String rtID, Date checkIn, Date checkOut, Double evaluates, Integer special) {
		OrderDetailVO odVO = new OrderDetailVO();
		odVO.setRoomID(roomID);
		odVO.setOrdID(ordID);
		odVO.setRtID(rtID);
		odVO.setCheckIn(checkIn);
		odVO.setCheckOut(checkOut);
		odVO.setEvaluates(evaluates);
		odVO.setSpecial(special);
		dao.insert(odVO);
		
		return odVO;
	}
	
	public OrderDetailVO updateOrderDetail(String roomID, String ordID, String rtID, Date checkIn, Date checkOut, Double evaluates, Integer special) {
		OrderDetailVO odVO = new OrderDetailVO();
		odVO.setRoomID(roomID);
		odVO.setOrdID(ordID);
		odVO.setRtID(rtID);
		odVO.setCheckIn(checkIn);
		odVO.setCheckOut(checkOut);
		odVO.setEvaluates(evaluates);
		odVO.setSpecial(special);
		dao.update(odVO);
		
		return odVO;
	}
	
	public OrderDetailVO getOneOrderDetail(Integer odID) {
		return dao.findByPrimaryKey(odID);
	}
	
	public List<OrderDetailVO> getbyOrder(String ordID){
		return dao.findByOrders(ordID);
	}
	
	public List<OrderDetailVO> getAll(){
		return dao.getALL();
	}
	
	public void deleteOrderDetail(Integer odID) {
		dao.delete(odID);;
	}	
	
	
}
