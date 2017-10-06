package com.shsxt.crm.dao;

import java.util.List;
import java.util.Map;

import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.vo.CustomerLoss;
import com.shsxt.crm.vo.CustomerReprieve;

public interface CustomerLossDao{
	//初始化查询
	public List<CustomerLoss> qureyCustomerLoss(CustomerLossQuery customerLossQuery);
	
	//查询流失信息map
	public Map<String,Object> qureyCustomerLossById(Integer id);
	
	//更改流失状态
	public Integer updateCusLossState(CustomerLoss customerLoss);
	
	//批量添加流失客户
	public Integer insertCusLoss(List<CustomerLoss> customerLosses);
	
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerLoss record);

    int insertSelective(CustomerLoss record);

    CustomerLoss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerLoss record);

    int updateByPrimaryKey(CustomerLoss record);
}