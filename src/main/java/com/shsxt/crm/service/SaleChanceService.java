package com.shsxt.crm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.dao.SaleChanceDao;
import com.shsxt.crm.dto.DevResult;
import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChence;

@Service
public class SaleChanceService {

	@Autowired
	private SaleChanceDao saleChanceDao;
	
	//删
	public Integer deleteSaleChance(Integer[] ids){
		return saleChanceDao.deleteSaleChance(ids);
	};
	
	//查
	public Map<String, Object> querySaleChanceByParams(SaleChanceQuery saleChanceQuery){
		PageHelper.startPage(saleChanceQuery.getPage(),saleChanceQuery.getRows());
		List<SaleChence> saleChences = saleChanceDao.querySaleChanceByParams(saleChanceQuery);
		PageInfo<SaleChence> pageInfo=new PageInfo<SaleChence>(saleChences);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	//根据ID查
	public SaleChence queryById(Integer id){
		AssertUtil.isTrue(null==id, "该条记录不存在!");
		SaleChence saleChence=saleChanceDao.querySaleChanceById(id);
		AssertUtil.isTrue(saleChence==null, "该条记录不存在");
		return saleChence;
	}
	
	
	
	//增
	/**
	 * 1.参数合法性校验
	 *    客户名称
	 *    成功几率
	 *    联系人
	 *    联系电话
	 * 2.执行添加逻辑
	 *    添加时间
	 *    更新时间
	 *    有效值 默认有效
	 *    开发状态 默认为未开发  0-未开发  1-开发中  2-开发成功  3-开发失败
	 *    分配状态 state 如果分配 state=1  否则 state=0
	 *    assignMan 如果存在值  state=1  assignTime=now()  不存在 stater=0
	 *    createMan  系统获取创建人
	 */
	public Integer insertSaleChance(SaleChence saleChence){
		checkSaleChance(saleChence.getCustomerName(),saleChence.getCgjl(),saleChence.getLinkMan(),saleChence.getLinkPhone());
		saleChence.setIsValid(1);
		saleChence.setDevResult(DevResult.UN_DEV.getDevResult());
		saleChence.setState(1);
		return saleChanceDao.insertSaleChance(saleChence);
	}
	private void checkSaleChance(String customerName, Integer cgjl, String linkMan, String linkPhone) {
		AssertUtil.isTrue(StringUtils.isBlank(customerName), "客户名称不能为空!!!");
		AssertUtil.isTrue("".equals(cgjl), "成功几率不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(linkMan), "联系人不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(linkPhone), "联系电话不能为空!!!");
	}

	//改
	public Integer updataSaleChance(SaleChence saleChence){
		checkSaleChance(saleChence.getCustomerName(),saleChence.getCgjl(),saleChence.getLinkMan(),saleChence.getLinkPhone());
		AssertUtil.isTrue(null==saleChence.getId(), "该条记录不存在!");
		AssertUtil.isTrue(saleChanceDao.querySaleChanceById(saleChence.getId())==null, "该条记录不存在");
		return saleChanceDao.updateSaleChance(saleChence);
		
	}

	//开发
	public void updateDevResult(Integer devResult,Integer saleChanceId) {
		AssertUtil.isTrue(saleChanceId==null, "该计划不存在,请刷新重试");
		AssertUtil.isTrue(saleChanceDao.querySaleChanceById(saleChanceId)==null, "该计划不存在,请刷新重试");
		AssertUtil.isTrue(saleChanceDao.updateDevResult(devResult ,saleChanceId)<1, "操作失败");
	}
		
	
	
	
}
