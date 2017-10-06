package com.shsxt.crm.dao;

import java.util.List;

import com.shsxt.crm.vo.CustomerReprieve;

public interface CustomerReprieveDao {


	//查询暂缓流失措施
		public List<CustomerReprieve> queryReprieve(Integer lossId);
		
		//增
		public Integer insertCusRepri(CustomerReprieve customerReprieve);
		
		//改
		public Integer updateCusRepri(CustomerReprieve customerReprieve);
}
