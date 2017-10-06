package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.CustomerContributeQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.service.CusLevelService;
import com.shsxt.crm.service.CustomerService;
import com.shsxt.crm.vo.CusLevel;
import com.shsxt.crm.vo.Customer;


@Controller
@RequestMapping("customer")
public class CustomerController extends BaseController{
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CusLevelService cusLevelService;
	
	@RequestMapping("index")
	public String index(){
		
		return "customer";
	}
	
	@RequestMapping("toOtherPage/{page}/{id}")
	public String toOtherPage(@PathVariable("page")Integer page,@PathVariable("id")Integer id,Model model){
		Customer customer = customerService.queryById(id);
		model.addAttribute("customer", customer);
		switch (page) {
		case (1):return "link_man";
		case (2):return "link_record";
		case (3):return "customer_order";
		default:return "error";
		}
	}

	//查询服务统计
	@RequestMapping("queryServeCount")
	@ResponseBody
		public Map<String, Object> queryServeCount(){
			return customerService.queryServeCount();
		}
		

	//查询顾客等级组成统计
	@RequestMapping("queryLevelCount")
	@ResponseBody
	public Map<String, Object> queryLevelCount(){
		return customerService.queryLevelCount();
	}
	
	
	//查询顾客贡献值
	@RequestMapping("queryCustomersContribution")
	@ResponseBody
	public Map<String, Object> queryCustomersContribution(CustomerContributeQuery customerContributeQuery){
		return customerService.queryContribute(customerContributeQuery);
	}
	
	
	//查询等级
	@RequestMapping("queryDataDicValueByDataDicName")
	@ResponseBody
	public List<CusLevel> queryDataDicValueByDataDicName(String dataDicName){
		return cusLevelService.queryLevel(dataDicName);
	}

	//查询
	@RequestMapping("queryCustomersByParams")
	@ResponseBody
	public Map<String, Object> queryCustomersByParams(CustomerQuery customerQuery){
		return customerService.queryAllCustomerInfo(customerQuery);
	}
	
	
	//添加
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(Customer customer){
		MessageModel messageModel=new MessageModel();
		customerService.insertCustomerInfo(customer);
		return messageModel;
	}
	
	//更新
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(Customer customer){
		MessageModel messageModel=new MessageModel();
		customerService.updeteCustomer(customer);
		return messageModel;
	}
	
/**
 * 
 * 查询所有客户
 * @return
 */
	@RequestMapping("queryAllCustomers")
	@ResponseBody
	public List<Customer> queryAllCustomers(){
		return customerService.queryAllCustomers();
	}
	
	//删除
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer[] ids){
		MessageModel messageModel=new MessageModel();
		customerService.deleteByPrimaryKey(ids);
		return messageModel;
	}
	
	
}
