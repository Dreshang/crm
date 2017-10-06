package com.shsxt.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.service.CustomerManagerService;
import com.shsxt.crm.service.CustomerPlanService;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.CustomerPlan;
import com.shsxt.crm.vo.SaleChence;

@Controller
@RequestMapping("cus_dev_plan")
public class CustomerDevPlanController extends BaseController{

	@Autowired
	private SaleChanceService saleChanceService;
	@Autowired
	private CustomerPlanService customerManagerService;
	
	//初始化页面
	@RequestMapping("index")
	public String index(Integer id,Model model){
		SaleChence saleChence = saleChanceService.queryById(id);
		model.addAttribute("saleChance", saleChence);
		return "cus_dev_plan_detail";
	}
	
	
	//查询计划信息
	@RequestMapping("queryCusDevPlansByParams")
	@ResponseBody
	public List<CustomerPlan> queryCusDevPlansByParams(Integer saleChanceId) {
		return customerManagerService.queryCusPlanById(saleChanceId);
	}
	
	//增
	@RequestMapping("insetCusDevPlans")
	@ResponseBody
	public MessageModel insetCusDevPlans(CustomerPlan customerPlan){
		MessageModel messageModel = new  MessageModel();
		customerManagerService.insertCusPlan(customerPlan);
		return messageModel;
	}
	//改
	@RequestMapping("updateCusDevPlans")
	@ResponseBody
	public MessageModel updateCusDevPlans(CustomerPlan customerPlan){
		MessageModel messageModel = new  MessageModel();
		customerManagerService.updeteCusPlan(customerPlan);
		return messageModel;
	}
	
	//删
	@RequestMapping("deleteCusDevPlans")
	@ResponseBody
	public MessageModel deleteCusDevPlans(CustomerPlan customerPlan){
		MessageModel messageModel = new  MessageModel();
		customerManagerService.deleteCusDevPlans(customerPlan);
		return messageModel;
	}
	
}
