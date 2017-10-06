package com.shsxt.crm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.query.OrderDetailQuery;
import com.shsxt.crm.service.OrderDetailService;
import com.shsxt.crm.vo.CustomerOrder;

@Controller
@RequestMapping("order_detail")
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping("queryOrderDetailsByOrderId")
	@ResponseBody
	public Map<String, Object> queryOrderDetailsByOrderId(OrderDetailQuery orderDetailQuery){
		return orderDetailService.queryOrderDetail(orderDetailQuery);
	}
	
	
	@RequestMapping("queryCustoemrOrderByOrderId")
	@ResponseBody
	public CustomerOrder queryCustoemrOrderByOrderId(Integer orderId){
		return orderDetailService.queryByOrderId(orderId);
		
	}
}
