package com.shsxt.crm.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shsxt.crm.base.BaseTest;

public class TestCusService extends BaseTest{
	
	@Autowired
	private CustomerService customerService;

	@Test
	public void test() {
		
		customerService.updateLossCustomers();
	
	}

}
