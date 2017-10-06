package com.shsxt.crm.service;

import java.util.ArrayList;
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
import com.shsxt.crm.dao.CustomerDao;
import com.shsxt.crm.dao.CustomerLossDao;
import com.shsxt.crm.dao.CustomerOrderDao;
import com.shsxt.crm.dto.CusServeDto;
import com.shsxt.crm.query.CustomerContributeQuery;
import com.shsxt.crm.query.CustomerQuery;
import com.shsxt.crm.utils.MathUtil;
import com.shsxt.crm.vo.Customer;
import com.shsxt.crm.vo.CustomerLoss;
import com.shsxt.crm.vo.CustomerOrder;


@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerOrderDao customerOrderDao;
	@Autowired
	private CustomerLossDao customerLossDao;
	
	public List<Customer> queryAllCustomers(){
		return customerDao.queryAllCustomers();
	}
	
	//查询服务统计
		public Map<String, Object> queryServeCount(){
			List<CustomerContributeQuery> list=customerDao.queryServeCount();
			String[] Types=null;
			CusServeDto[] cusServeDtos =null;
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("code", 300);
			if (list!=null&&list.size()>0) {
				Types=new String[list.size()];
				cusServeDtos= new CusServeDto[list.size()];
				for (int i = 0; i < list.size(); i++) {
					CustomerContributeQuery customerContributeQuery=list.get(i);
					Types[i]=customerContributeQuery.getServeType();
					CusServeDto cusServeDto=new CusServeDto();
					cusServeDto.setName(customerContributeQuery.getServeType());
					cusServeDto.setValue(customerContributeQuery.getCount2());
					cusServeDtos[i]=cusServeDto;
				}
				map.put("code", 200);
			}
			map.put("type", Types);
			map.put("cusServeDto", cusServeDtos);
			return map;
		}
	
	
	//顾客等级组成
	public Map<String, Object> queryLevelCount(){
		List<CustomerContributeQuery> contributeQueries = customerDao.queryLevelCount();
		Map<String, Object> map = new HashMap<String, Object>();
		String[] levels=null;
		Integer[] count=null;
		map.put("code", 300);
		if (contributeQueries!=null&&contributeQueries.size()>0) {
			levels=new String[contributeQueries.size()];
			count=new Integer[contributeQueries.size()];
			for (int i = 0 ;i<contributeQueries.size();i++) {
				CustomerContributeQuery contributeQuery =contributeQueries.get(i);
				levels[i]=contributeQuery.getLevel();
				count[i]=contributeQuery.getCount();
			}
			map.put("code", 200);
		}
		map.put("level", levels);
		map.put("count", count);
		return map ;
	}
	
	
	//查询顾客贡献值
	public Map<String,Object> queryContribute(CustomerContributeQuery customerContributeQuery){
		PageHelper.startPage(customerContributeQuery.getPage(),customerContributeQuery.getRows());
		List<CustomerContributeQuery> customerContributeQueries=customerDao.queryContribute(customerContributeQuery);
		PageInfo<CustomerContributeQuery> pageInfo=new PageInfo<CustomerContributeQuery>(customerContributeQueries);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	//查询
	public Map<String, Object> queryAllCustomerInfo(CustomerQuery customerQuery){
		PageHelper.startPage(customerQuery.getPage(),customerQuery.getRows());
		List<Customer> list=customerDao.queryAllCustomerInfo(customerQuery);
		PageInfo<Customer> pageInfo=new PageInfo<Customer>(list);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	//添加
	public void insertCustomerInfo(Customer customer){
		/**
		 *  1.参数合法性校验  客户名称不可重复
		 *    客户姓名
		 *    客户电话
		 *    客户法人代表
		 * 2.额外参数补充
		 *    添加时间
		 *    更新时间
		 *    有效字段
		 *    是否流失  state=0代表未流失
		 * 3.执行添加
		 */
		checkCustomerInfo(customer.getName(),customer.getPhone(),customer.getFr());
		customer.setIsValid(1);
		customer.setState(0);
		customer.setCreateDate(new Date());
		customer.setUpdateDate(new Date());
		String khno=MathUtil.genereateKhCode();
		customer.setKhno(khno);
		AssertUtil.isTrue(customerDao.insertCustomerInfo(customer)<1, "添加执行失败");
		
	}
	private void checkCustomerInfo(String name, String phone, String fr) {
		AssertUtil.isTrue(StringUtils.isBlank(name), "客户姓名不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(phone), "客户联系电话不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(fr), "客户法人代表不能为空!!!");
	}
	
	//更新
	public void updeteCustomer(Customer customer){
		checkCustomerInfo(customer.getName(),customer.getPhone(),customer.getFr());
		customer.setIsValid(1);
		customer.setState(0);
		customer.setUpdateDate(new Date());
		AssertUtil.isTrue(customerDao.updateCustomerInfo(customer)<1, "更新失败!!!");
	}
	
	//删除
	public void deleteByPrimaryKey(Integer[] ids){
		AssertUtil.isTrue(ids.length<1, "请选择至少一条进行删除");
		AssertUtil.isTrue(customerDao.deleteByCustomer(ids)<1, "删除失败!");
	}

	public Customer queryById(Integer id) {
		AssertUtil.isTrue(id==null, "查询的信息不存在");
		return customerDao.selectByPrimaryKey(id);
	}

	
	
	//客户流失管理
	public void updateLossCustomers(){
		List<Customer> customers=customerDao.queryLossCustomer();
		List<CustomerLoss> customerLosses=null;
		Integer[] ids=null;
		if (null!=customers && customers.size()>0) {
			customerLosses=new ArrayList<CustomerLoss>();
			ids=new Integer[customers.size()];
			for(int i = 0;i<customers.size();i++){
				CustomerLoss customerLoss =new CustomerLoss();
				Customer customer=customers.get(i);
				customerLoss.setId(customer.getId());
				customerLoss.setCusNo(customer.getKhno());
				customerLoss.setCusName(customer.getName());
				customerLoss.setCusManager(customer.getCusManager());
				CustomerOrder customerOrder = customerOrderDao.queryLastById(customer.getId());
				if (null!=customerOrder) {
					customerLoss.setLastOrderTime(customerOrder.getOrderDate());
				}
				customerLoss.setState(0);
				customerLoss.setIsValid(1);
				customerLoss.setCreateDate(new Date());
				customerLoss.setUpdateDate(new Date());
				customerLosses.add(customerLoss);
				ids[i]=customer.getId();
			}
			AssertUtil.isTrue(customerDao.updateCusState(ids)<1||customerLossDao.insertCusLoss(customerLosses)<1, CrmConstant.OPS_FAILED_MSG);
		}
		
		
	}
	
	
	
}
