package com.shsxt.crm.dao;

import java.util.List;

import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.vo.CustomerServe;

public interface CustomerServeDao {
   

    //创建服务
    public Integer insertNewServe(CustomerServe customerServe);

   //查询服务
    public List<CustomerServe> querycCustomerServes(CustomerServeQuery customerServeQuery);

    CustomerServe selectByPrimaryKey(Integer id);

    //更新状态
   public Integer updateServeState(CustomerServe customerServe);

    int updateByPrimaryKey(CustomerServe record);
}