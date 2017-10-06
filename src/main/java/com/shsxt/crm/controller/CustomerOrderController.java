package com.shsxt.crm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.service.CustomerOrderService;

@Controller
@RequestMapping("customer_order")
public class CustomerOrderController extends BaseController{

	@Autowired
	private CustomerOrderService customerOrderService;
	
	@RequestMapping("queryOrdersByCid")
	@ResponseBody
	public Map<String, Object> queryOrdersByCid(CustomerOrderQuery customerOrderQuery){
		return customerOrderService.queryById(customerOrderQuery);
	}
}
