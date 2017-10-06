package com.shsxt.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.service.CustomerRepriService;
import com.shsxt.crm.vo.CustomerReprieve;

@Controller
@RequestMapping("cus_repri")
public class CustomerRepriController {
	
	@Autowired
	private CustomerRepriService customerRepriService;

	@RequestMapping("queryByLossId")
	@ResponseBody
	public List<CustomerReprieve> queryByLossId(Integer lossId){
		return customerRepriService.queryReprieve(lossId);
	}
	
	@RequestMapping("save")
	@ResponseBody
	public MessageModel save(CustomerReprieve customerReprieve){
		MessageModel messageModel=new MessageModel();
		customerRepriService.insertCusRepri(customerReprieve);
		return messageModel;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(CustomerReprieve customerReprieve){
		MessageModel messageModel=new MessageModel();
		customerRepriService.updateCusRepri(customerReprieve);
		return messageModel;
	}
	
}
