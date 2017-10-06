package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.CustomerLossDao;
import com.shsxt.crm.query.CustomerLossQuery;
import com.shsxt.crm.vo.CustomerLoss;
import com.shsxt.crm.vo.CustomerReprieve;

@Service
public class CustomerLossService {

	@Autowired
	private CustomerLossDao customerLossDao;
	
	public Map<String, Object> qureyCustomerLoss(CustomerLossQuery customerLossQuery){
		PageHelper.startPage(customerLossQuery.getPage(),customerLossQuery.getRows());
		List<CustomerLoss> customerLosses = customerLossDao.qureyCustomerLoss(customerLossQuery);
		PageInfo<CustomerLoss> pageInfo=new PageInfo<CustomerLoss>(customerLosses);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	public Map<String, Object> queryById(Integer id){
		return customerLossDao.qureyCustomerLossById(id);
	}
	
	//更改流失状态
	public void updateCusLossState(CustomerLoss customerLoss) {
		AssertUtil.isTrue(customerLoss.getId()==null, "该条流失记录不存在");
		AssertUtil.isTrue(customerLossDao.qureyCustomerLossById(customerLoss.getId())==null, "该条流失记录不存在");
		AssertUtil.isTrue(customerLoss.getLossReason()==null, "流失原因非空");
		customerLoss.setConfirmLossTime(new Date());
		AssertUtil.isTrue(customerLossDao.updateCusLossState(customerLoss)<1, CrmConstant.OPS_FAILED_MSG);
	}
	
}
