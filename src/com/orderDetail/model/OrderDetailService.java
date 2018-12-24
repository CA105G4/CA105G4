package com.orderDetail.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.orders.model.OrdersVO;

public class OrderDetailService {
	
	public OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO addOrderDetail(String ordID, String rtID, Date checkIn, Date checkOut, Integer special) {
		OrderDetailVO odVO = new OrderDetailVO();

		odVO.setOrdID(ordID);
		odVO.setRtID(rtID);
		odVO.setCheckIn(checkIn);
		odVO.setCheckOut(checkOut);
		odVO.setSpecial(special);
		dao.insert(odVO);
		
		return odVO;
	}
	
	public OrderDetailVO updateOrderDetail(Integer odID, String roomID, String ordID, String rtID, Date checkIn, Date checkOut, Double evaluates, Integer special) {
		OrderDetailVO odVO = new OrderDetailVO();
		odVO.setOdID(odID);
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
	
	public Set<OrderDetailVO> getOrderDetailByOrders(String ordID){
		return dao.findByOrders(ordID);
	}
	
	public List<OrderDetailVO> getAll(){
		return dao.getALL();
	}
	
	public void deleteOrderDetail(Integer odID) {
		dao.delete(odID);;
	}	
	
	
}
