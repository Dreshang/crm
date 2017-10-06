package com.shsxt.crm.dao;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.shsxt.crm.base.BaseTest;

public class SaleChanceTest extends BaseTest{

	@Autowired
	private SaleChanceDao saleChanceDao;
	
	@Test
	public void test() {
		Integer[] ids={77,78,79};
		saleChanceDao.deleteSaleChance(ids);
	}

}
