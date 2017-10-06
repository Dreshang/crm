package com.shsxt.crm.dao;

import java.util.List;

import com.shsxt.crm.query.OrderDetailQuery;
import com.shsxt.crm.vo.CustomerOrder;
import com.shsxt.crm.vo.OrderDetail;

public interface OrderDetailDao {

	//查询订单详情
	public List<OrderDetail> queryOrderDetail(OrderDetailQuery orderDetailQuery);
	
	//查询总金额
	public double queryTotalPrice(Integer orderId);
	
	//查询单条订单
	public CustomerOrder queryByOrderId(Integer orderId);
	
}
