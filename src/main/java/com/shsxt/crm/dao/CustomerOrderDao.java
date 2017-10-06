package com.shsxt.crm.dao;

import java.util.List;

import com.shsxt.crm.query.CustomerOrderQuery;
import com.shsxt.crm.vo.CustomerOrder;

public interface CustomerOrderDao {
    
    public List<CustomerOrder> queryById(CustomerOrderQuery customerOrderQuery);
    
    
    //查询最后一单
    public CustomerOrder queryLastById(Integer cusId);
}