package com.shsxt.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.CustomerManagerDao;
import com.shsxt.crm.vo.CustomerManager;

@Service
public class CustomerManagerService {

	@Autowired
	private CustomerManagerDao customerManagerDao;
	
	public List<CustomerManager> queryCustomerManagers(){
		return customerManagerDao.queryCustomerManagers();
	}
}
