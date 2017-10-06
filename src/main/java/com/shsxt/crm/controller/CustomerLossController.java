package com.shsxt.crm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.service.CustomerLossService;
import com.shsxt.crm.vo.CustomerLoss;

@Controller
@RequestMapping("customer_loss")
public class CustomerLossController extends BaseController{
	
	@Autowired
	private CustomerLossService customerLossService;

	//跳转页面
	@RequestMapping("index/{page}")
	public String index(@PathVariable("page")Integer page){
		return "cus_loss";
	}
	
	//查询流失客户
	@RequestMapping("queryCustomerLossesByParams")
	@ResponseBody
	public Map<String,Object> queryCustomerLossesByParams(CustomerLossQuery customerLossQuery){
		Map<String,Object> map=customerLossService.qureyCustomerLoss(customerLossQuery);
		return map;
		
	}
	
	@RequestMapping("queryCustomerRepid")
	public String queryCustomerRepid(Integer id,Model model){
		Map<String, Object> map=customerLossService.queryById(id);
		model.addAttribute("customerLoss", map);
		return "customer_repri";
	}
	
	//更新流失状态
	@RequestMapping("updateLossState")
	@ResponseBody
	public MessageModel updateLossState(CustomerLoss customerLoss){
		MessageModel messageModel=new MessageModel();
		customerLossService.updateCusLossState(customerLoss);
		return messageModel;
		
	}
	
}
