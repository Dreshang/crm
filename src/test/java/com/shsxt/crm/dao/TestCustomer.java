package com.shsxt.crm.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shsxt.crm.base.BaseTest;
import com.shsxt.crm.query.CustomerQuery;

public class TestCustomer extends BaseTest{
	
	@Autowired
	private CustomerDao customerDao;

	@Test
	public void test() {
		CustomerQuery customerQuery=new CustomerQuery();
		System.out.println(customerDao.queryAllCustomerInfo(customerQuery));
	}
	
	@Test
	public void test1() {
		CustomerQuery customerQuery=new CustomerQuery();
		System.out.println(customerDao.queryAllCustomerInfo(customerQuery));
	}

	@Test
	public void hello() {
		System.out.println("hello");
	}
	@Test
	public void hello1() {
		System.out.println("hello");
	}
}
