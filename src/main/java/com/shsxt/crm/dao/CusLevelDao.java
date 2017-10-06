package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.vo.CusLevel;

public interface CusLevelDao {

	@Select("select id,data_dic_name as dataDicName,data_dic_value as level,is_valid as isValid,"
			+ "create_date as createDate,update_date as updateDate from t_datadic WHERE data_dic_name=#{dataDicName}")
	public List<CusLevel> queryLevel(String dataDicName);
}
