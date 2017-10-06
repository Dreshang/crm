package com.shsxt.crm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.dao.CustomerServeDao;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.service.CustomerServeService;
import com.shsxt.crm.vo.CustomerServe;

@Controller
@RequestMapping("customer_serve")
public class CustomerServeController extends BaseController{
	
	@Autowired
	private CustomerServeService customerServeService;

	//页面跳转
	@RequestMapping("index/{page}")
	public String index(@PathVariable("page")Integer page){
		switch (page) {
		case (1):return "customer_serve_create";
		case (2):return "customer_serve_assign";
		case (3):return "customer_serve_proceed";
		case (4):return "customer_serve_feed_back";
		case (5):return "customer_serve_archive";
		default: return "error";
		}
	}
	
	
	//创建服务
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(CustomerServe customerServe){
		MessageModel messageModel=new MessageModel();
		customerServeService.insertNewServe(customerServe);
		return messageModel;
	}
	
	//查询服务
	@RequestMapping("queryCustomerServesByParams")
	@ResponseBody
	public Map<String, Object> queryCustomerServesByParams(CustomerServeQuery customerServeQuery){
	Map<String, Object> map =customerServeService.querycCustomerServes(customerServeQuery);
	return map;
	}
	//更新服务处理状态
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(CustomerServe customerServe){
		MessageModel messageModel =new MessageModel();
		customerServeService.updateServeState(customerServe);
		return messageModel;
	}
	
}
