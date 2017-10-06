package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.vo.CustomerManager;

public interface CustomerManagerDao {
	
	

	@Select("SELECT u.id, u.true_name as trueName from t_user u LEFT JOIN t_user_role ur on u.id=ur.user_id "
			+ "LEFT JOIN t_role r on ur.role_id=r.id WHERE r.id=3")
	public List<CustomerManager> queryCustomerManagers();
}
