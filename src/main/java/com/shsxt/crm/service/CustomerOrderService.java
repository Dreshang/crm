package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.crm.dao.CustomerOrderDao;
import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.vo.CustomerOrder;

@Service
public class CustomerOrderService {

	@Autowired
	private CustomerOrderDao customerOrderDao;
	
	public Map<String, Object> queryById(CustomerOrderQuery customerOrderQuery){
		PageHelper.startPage(customerOrderQuery.getPage(),customerOrderQuery.getRows());
		List<CustomerOrder> customerOrders = customerOrderDao.queryById(customerOrderQuery);
		PageInfo<CustomerOrder> pageInfo=new PageInfo<CustomerOrder>(customerOrders);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
}
