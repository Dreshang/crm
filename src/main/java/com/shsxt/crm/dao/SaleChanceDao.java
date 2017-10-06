package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.shsxt.crm.query.SaleChanceQuery;
import com.shsxt.crm.vo.SaleChence;

public interface SaleChanceDao {
	
	//查单一账户
	public SaleChence querySaleChanceById(Integer id);

	//查
	public List<SaleChence> querySaleChanceByParams(SaleChanceQuery saleChanceQuery);
	
	//增
	@Insert("insert into t_sale_chance(chance_source, customer_name , cgjl ,overview , link_man ,"
			+ " link_phone  ,description,create_man  ,assign_man ,assign_time ,state,dev_result ,"
			+ "is_valid ,create_date ,update_date  ) "
			+ "VALUES(#{chanceSource},#{customerName},#{cgjl},#{overview},#{linkMan},#{linkPhone},"
			+ "#{description},#{createMan},#{assignMan},NOW(),#{state},#{devResult},#{isValid},"
			+ "NOW(),NOW())")
	public Integer insertSaleChance(SaleChence saleChence);
	

	//删
	public Integer deleteSaleChance(Integer[] ids);
	
	
	//改
	@Update("UPDATE  t_sale_chance SET chance_source=#{chanceSource}, customer_name=#{customerName} ,"
			+ " cgjl=#{cgjl} ,overview=#{overview}, link_man =#{linkMan},link_phone =#{linkPhone} ,description =#{description},"
			+ "assign_man =#{assignMan},update_date =NOW() where id=#{id}")
	public Integer updateSaleChance(SaleChence saleChence);

	//开发
	@Update("update t_sale_chance SET dev_result=#{0} where id=#{1}")
	public int updateDevResult(Integer devResult,Integer saleChanceId);
}
