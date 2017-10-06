package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.shsxt.crm.vo.CustomerPlan;

public interface CustomerPlanDao {

	//查
	public List<CustomerPlan> queryCusPlanById(Integer saleChanceId);
	
	//增
	public Integer insertCusPlan(CustomerPlan customerPlan);
	
	//改
	public Integer updateupdeteCusPlan(CustomerPlan customerPlan);

	//删
	public Integer deleteCusDevPlans(CustomerPlan customerPlan);
}
