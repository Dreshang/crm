package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.vo.Module;

public interface ModuleDao {

	@Select("select id,parent_id as pId,module_name as name from t_module where is_valid=1")
	public List<ModuleDto> queryModule();
	
	//查询
	@Select("SELECT module_name as moduleName,module_style as moduleStyle,url,"
			+ "parent_id as parentId,grade,opt_value as optValue,orders,is_valid as isValid,"
			+ "create_date as createDate,update_date as updateDate "
			+ "FROM t_module "
			+ "WHERE is_valid=1 and id=#{id}")
	public Module queryById(Integer id);
}
