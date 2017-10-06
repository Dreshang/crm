package com.shsxt.crm.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shsxt.crm.service.CustomerService;
@Component
public class CustomerLossTask {

	@Autowired
	private CustomerService customerService;
	
	
	public void task(){
		customerService.updateLossCustomers();
	}
	
}
