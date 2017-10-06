package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.vo.Permission;

public interface PermissionDao {

	//添加
	public Integer  insertBatch(List<Permission> permissions);
	
	//查询是否存在
	@Select("SELECT COUNT(1) as total from t_permission where role_id=#{roleId}")
	public Integer queryCount(Integer roleId);
	
	
	//删除存在的记录
	@Delete("DELETE from t_permission where role_id=#{roleId}")
	public Integer deletePermission(Integer roleId);
	
	
	//根据角色查询模块
	@Select("SELECT module_id FROM t_permission WHERE role_id= #{roleId}")
	public List<Integer> queryModuleId(Integer roleId);
	
	//根据userId查询模块
	@Select("SELECT acl_value FROM t_permission p "
			+ "LEFT JOIN t_user_role ur "
			+ "ON p.role_id=ur.role_id "
			+ "WHERE ur.user_id=#{userId}")
	public List<String> queryModuleByUserId(Integer userId);
}
