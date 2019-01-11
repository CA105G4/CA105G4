package com.orderDetail.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

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
	
	//[Gina]{checkout}再訂單明細中加入房間編號
	public void updateRoomID(String roomID, Integer odID) {
		dao.updateRoomID(roomID, odID);
	}
	
	//[Gina]{加床}再訂單明細中改加床
	public void updateSpecial(Integer special, Integer odID) {
		dao.updateSpecial(special, odID);
	}
	
	//[Gina]{給評價}再訂單明細中給評價
	public void updateEvaluates(Integer evaluates, Integer odID) {
		dao.updateEvaluates(evaluates, odID);
	};
}
