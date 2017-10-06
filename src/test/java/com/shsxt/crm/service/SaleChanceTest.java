package com.shsxt.crm.service;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shsxt.crm.base.BaseTest;
import com.shsxt.crm.vo.SaleChence;

public class SaleChanceTest extends BaseTest {

	@Autowired
	private SaleChanceService SaleChanceService;
	
	@Test
	public void test() {
		SaleChence saleChence=new SaleChence();
		saleChence.setCustomerName("aaa");
		saleChence.setCgjl(67);
		saleChence.setLinkMan("vbb");
		saleChence.setLinkPhone("56789");
		System.out.println(SaleChanceService.insertSaleChance(saleChence));
	}

}
