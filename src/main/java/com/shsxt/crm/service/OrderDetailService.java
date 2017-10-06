package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.dao.OrderDetailDao;
import com.shsxt.crm.query.OrderDetailQuery;
import com.shsxt.crm.vo.CustomerOrder;
import com.shsxt.crm.vo.OrderDetail;

@Service
public class OrderDetailService {
	
	@Autowired
	private OrderDetailDao orderDetailDao;

	public Map<String, Object>queryOrderDetail(OrderDetailQuery orderDetailQuery){
		PageHelper.startPage(orderDetailQuery.getPage(),orderDetailQuery.getRows());
		List<OrderDetail> list=orderDetailDao.queryOrderDetail(orderDetailQuery);
		PageInfo<OrderDetail> pageInfo=new PageInfo<OrderDetail>(list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	
	//查询订单信息
	public CustomerOrder queryByOrderId(Integer orderId){
		AssertUtil.isTrue(orderId==null, "订单不存在");
		//查询总价
		double total = orderDetailDao.queryTotalPrice(orderId);
		//查询订单
		CustomerOrder customerOrder=orderDetailDao.queryByOrderId(orderId);
		customerOrder.setTotal(total);
		return customerOrder;
	}
}
