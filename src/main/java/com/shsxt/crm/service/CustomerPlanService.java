package com.shsxt.crm.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.base.AssertUtil;
import com.shsxt.crm.dao.CustomerPlanDao;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.vo.CustomerPlan;

@Service
public class CustomerPlanService {

	@Autowired
	private CustomerPlanDao customerPlanDao;
	@Autowired
	private SaleChanceDao saleChanceDao;
	
	//查
	public List<CustomerPlan> queryCusPlanById(Integer saleChanceId){
		AssertUtil.isTrue(null==saleChanceId, "该条记录不存在!");
		AssertUtil.isTrue(saleChanceDao.querySaleChanceById(saleChanceId)==null, "该条记录不存在");
		AssertUtil.isTrue(saleChanceDao.querySaleChanceById(saleChanceId)==null, "该条记录不存在");
		return customerPlanDao.queryCusPlanById(saleChanceId);
	}
	/**
	 * saleChanceId
	 * planItem;
	 * planDate;
	 * exeAffect
	 * @param customerPlan
	 * @return
	 */
	//增
	public void insertCusPlan(CustomerPlan customerPlan){
		checkCustomerPlan(customerPlan.getSaleChanceId(),customerPlan.getPlanItem(),customerPlan.getPlanDate(),customerPlan.getExeAffect());
		customerPlan.setCreateDate(new Date());
		customerPlan.setUpdateDate(new Date());
		customerPlan.setIsValid(1);
		AssertUtil.isTrue(customerPlanDao.insertCusPlan(customerPlan)<1, "计划事项添加失败!!!");
	}
	//更新
	public void updeteCusPlan(CustomerPlan customerPlan) {
		checkCustomerPlan(customerPlan.getSaleChanceId(),customerPlan.getPlanItem(),customerPlan.getPlanDate(),customerPlan.getExeAffect());
		AssertUtil.isTrue(null==customerPlan.getId(), "该计划不存在!!!");
		customerPlan.setUpdateDate(new Date());
		customerPlan.setIsValid(1);
		AssertUtil.isTrue(customerPlanDao.updateupdeteCusPlan(customerPlan)<1, "更新失败!!!");
	}
	private void checkCustomerPlan(Integer saleChanceId, String planItem, Date planDate, String exeAffect) {
		AssertUtil.isTrue(null==saleChanceId||null==saleChanceDao.querySaleChanceById(saleChanceId), "该条开发记录不存在,请刷新重试!");
		AssertUtil.isTrue(StringUtils.isBlank(planItem), "计划内容不能为空!");
		AssertUtil.isTrue(null==planDate, "日期不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(exeAffect), "执行结果不能为空!!!");
	}
	//删
	public void deleteCusDevPlans(CustomerPlan customerPlan) {
		AssertUtil.isTrue(null==customerPlan.getSaleChanceId()||null==saleChanceDao.querySaleChanceById(customerPlan.getSaleChanceId()), "该条开发记录不存在,请刷新重试!");
		AssertUtil.isTrue(null==customerPlan.getId(), "该计划不存在!!!");
		AssertUtil.isTrue(customerPlanDao.deleteCusDevPlans(customerPlan)<1, "删除失败!!!");
	}
	
	
	
}
















