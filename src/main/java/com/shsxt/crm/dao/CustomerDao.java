package com.shsxt.crm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.query.CustomerContributeQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.vo.Customer;

public interface CustomerDao {
	
	@Select("SELECT id , name FROM t_customer where is_valid=1 ")
	public List<Customer> queryAllCustomers();
	
	//初始化查询
	public List<Customer> queryAllCustomerInfo(CustomerQuery customerQuery);

	//删除
	public Integer deleteByCustomer(Integer[] ids);

	//添加
	public Integer insertCustomerInfo(Customer customer);


	//查询单条记录是否存在
	@Select("select * from t_customer where id=#{id}")
	public Customer selectByPrimaryKey(Integer id);

	//更新
	public Integer updateCustomerInfo(Customer customer);


	//查询顾客贡献值
	public List<CustomerContributeQuery> queryContribute(CustomerContributeQuery contributeQuery);
	
	//查询顾客等级组成
	public List<CustomerContributeQuery> queryLevelCount();
	
	//查询服务统计
	public List<CustomerContributeQuery> queryServeCount();
	
	
	//查询需要应流失的客户
	public List<Customer> queryLossCustomer();
	
	//批量流失顾客
	public Integer updateCusState(Integer[] ids);
}
