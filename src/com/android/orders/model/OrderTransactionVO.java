package com.android.orders.model;

import java.io.Serializable;
import java.util.List;

public class OrderTransactionVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
    private String action;
    private OrdersVO ordersVO;
    private List<OrderDetailData> orderDetailDataList;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public OrdersVO getOrdersVO() {
        return ordersVO;
    }

    public void setOrdersVO(OrdersVO ordersVO) {
        this.ordersVO = ordersVO;
    }

    public List<OrderDetailData> getOrderDetailList() {
        return orderDetailDataList;
    }

    public void setOrderDetailList(List<OrderDetailData> orderDetailDataList) {
        this.orderDetailDataList = orderDetailDataList;
    }
}
