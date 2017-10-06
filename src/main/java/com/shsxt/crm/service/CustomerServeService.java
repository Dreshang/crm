package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.CustomerServeDao;
import com.shsxt.crm.dto.ServeResult;
import com.shsxt.crm.query.CustomerServeQuery;
import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerServe;

@Service
public class CustomerServeService {

	@Autowired
	private CustomerServeDao customerServeDao;
	
	//创建服务
	public void insertNewServe(CustomerServe customerServe){
		//校验非空
		checkServeParams(customerServe.getServeType(),customerServe.getCustomer(),
				customerServe.getOverview(),customerServe.getServiceRequest());
		AssertUtil.isTrue(StringUtils.isBlank(customerServe.getCreatePeople()), "创建人不存在");
		customerServe.setIsValid(1);
		customerServe.setState(ServeResult.SERVE_NEW_SERVE.getState());
		customerServe.setCreateDate(new Date());
		customerServe.setUpdateDate(new Date());
		AssertUtil.isTrue(customerServeDao.insertNewServe(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
	}
	private void checkServeParams(String serveType, String customer, String overview, String serviceRequest) {
		AssertUtil.isTrue(StringUtils.isBlank(serveType), "服务类型非空");
		AssertUtil.isTrue(StringUtils.isBlank(customer), "客户名称非空");
		AssertUtil.isTrue(StringUtils.isBlank(overview), "概要");
		AssertUtil.isTrue(StringUtils.isBlank(serviceRequest), "服务请求非空");
		
	}
	//查询服务列表
	public Map<String, Object> querycCustomerServes(CustomerServeQuery customerServeQuery){
		PageHelper.startPage(customerServeQuery.getPage(),customerServeQuery.getRows());
		List<CustomerServe> list=customerServeDao.querycCustomerServes(customerServeQuery);
		PageInfo<CustomerServe> pageInfo=new PageInfo<CustomerServe>(list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	//更新服务状态
	public void updateServeState(CustomerServe customerServe){
		checkServeParams(customerServe.getServeType(),customerServe.getCustomer(),
				customerServe.getOverview(),customerServe.getServiceRequest());
		if (customerServe.getState().equals(ServeResult.SERVE_ASSIGNED.getState())) {
		//分配
			AssertUtil.isTrue(StringUtils.isBlank(customerServe.getAssigner()), "分配人非空!!!");
			customerServe.setAssignTime(new Date());
			AssertUtil.isTrue(customerServeDao.updateServeState(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
		}else if (customerServe.getState().equals(ServeResult.SERVE_PROCEED.getState())) {
		//处理
			AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProce()), "处理人非空!!!");
			customerServe.setServiceProceTime(new Date());
			AssertUtil.isTrue(customerServeDao.updateServeState(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
		}else if (customerServe.getState().equals(ServeResult.SERVE_FEED_BACK.getState())) {
		//反馈
			AssertUtil.isTrue(StringUtils.isBlank(customerServe.getServiceProceResult()), "反馈结果非空!!!");
			customerServe.setServiceProceTime(new Date());
			AssertUtil.isTrue(customerServeDao.updateServeState(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
		}else if (customerServe.getState().equals(ServeResult.SERVE_ARCHIVE.getState())) {
		//归档
			AssertUtil.isTrue(customerServeDao.updateServeState(customerServe)<1, CrmConstant.OPS_FAILED_MSG);
		}
	}

}
