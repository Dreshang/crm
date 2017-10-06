package com.shsxt.crm.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.CustomerLossDao;
import com.shsxt.crm.dao.CustomerReprieveDao;
import com.shsxt.crm.vo.CustomerReprieve;

@Service
public class CustomerRepriService {
	
	@Autowired
	private CustomerReprieveDao customerReprieveDao;
	@Autowired
	private CustomerLossDao customerLossDao;
	
	//查询暂缓流失措施
		public List<CustomerReprieve> queryReprieve(Integer lossId){
			AssertUtil.isTrue(lossId==null, "该客户流失记录不存在");
			return customerReprieveDao.queryReprieve(lossId);
		}
		
		//增
		/**
		 * 1.检查措施,lossId非空
		 * 2.添加isValid=1 时间
		 * 
		 * @param customerReprieve
		 * @return
		 */
		public void insertCusRepri(CustomerReprieve customerReprieve){
			checkCusRepriParams(customerReprieve.getLossId(),customerReprieve.getMeasure());
			customerReprieve.setIsValid(1);
			customerReprieve.setCreateDate(new Date());
			customerReprieve.setUpdateDate(new Date());
			AssertUtil.isTrue(customerReprieveDao.insertCusRepri(customerReprieve)<1, CrmConstant.OPS_FAILED_MSG);
			
		}
		
		//改
		public void updateCusRepri(CustomerReprieve customerReprieve){
			checkCusRepriParams(customerReprieve.getLossId(),customerReprieve.getMeasure());
			AssertUtil.isTrue(customerReprieve.getId()==null, "该条措施不存在!!!");
			customerReprieve.setUpdateDate(new Date());
			AssertUtil.isTrue(customerReprieveDao.updateCusRepri(customerReprieve)<1, CrmConstant.OPS_FAILED_MSG);
			
		}
		

		private void checkCusRepriParams(Integer lossId, String measure) {
			AssertUtil.isTrue(StringUtils.isBlank(measure), "暂缓措施不能为空!!!");
			AssertUtil.isTrue(lossId==null||customerLossDao.qureyCustomerLossById(lossId)==null, "该条流失记录不存在!!!");
		}
}
