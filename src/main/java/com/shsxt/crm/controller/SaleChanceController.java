package com.shsxt.crm.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.PermissionRequest;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.service.SaleChanceService;
import com.shsxt.crm.vo.SaleChence;


@RequestMapping("sale_chance")
@Controller
public class SaleChanceController extends BaseController{

	@Autowired
	private SaleChanceService saleChanceService;
	
	@RequestMapping("index/{state}")
	public String index(@PathVariable("state")Integer state){
		switch(state){
		case (1): return "sale_chance";
		case (2): return "cus_dev_plan";
		default: return "index";
		}
		
		
	}
	
	//开发状态
	@PermissionRequest(requestVal="101002")
	@RequestMapping("updateDevResult")
	@ResponseBody
	public MessageModel updateDevResult(@Param("devResult")Integer devResult,Integer saleChanceId){
		System.out.println(devResult);
		System.out.println(saleChanceId);
		MessageModel messageModel =new MessageModel();
		saleChanceService.updateDevResult(devResult ,saleChanceId);
		return messageModel;
	}
	
	
	
	//删
	@PermissionRequest(requestVal="101003")
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer[] ids){
		MessageModel messageModel =new MessageModel();
		Integer rs = saleChanceService.deleteSaleChance(ids);
		if (rs<1) {
			messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
			messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
		}
		return messageModel;
	}
	
	
	//改
	@PermissionRequest(requestVal="101002")
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(SaleChence saleChence){
		MessageModel messageModel=new MessageModel();
		Integer rs=saleChanceService.updataSaleChance(saleChence);
		if (rs<1) {
			messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
			messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
		}
		return messageModel;
	}
	
	
	//增
	@PermissionRequest(requestVal="101001")
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel Insert(SaleChence saleChence){
		MessageModel messageModel=new MessageModel();
		Integer rs=saleChanceService.insertSaleChance(saleChence);
		if (rs<=0) {
			messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
			messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
		}
		return messageModel;
	}
	
	
	//查
	@PermissionRequest(requestVal="1010")
	@RequestMapping("querySaleChancesByParams")
	@ResponseBody
	public Map<String, Object> querySaleChancesByParams(SaleChanceQuery saleChanceQuery){
		
		return saleChanceService.querySaleChanceByParams(saleChanceQuery);
	}
	
	public MessageModel insert(SaleChence saleChence){
		MessageModel messageModel=new MessageModel();
		saleChanceService.insertSaleChance(saleChence);
		return messageModel;
	}
	
}
