package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.vo.RoleType;

public interface RoleDao {

	
	//查询所有的角色
	public List<RoleType> queryAllRoles(RoleQuery roleQuery);
	
	//查询
	@Select("select count(1) from t_role where id=#{id}")
	public Integer queryById(Integer id);
	
	//添加角色
	public Integer insertRole(RoleType roleType);
	
	//更新角色
	public Integer updateRole(RoleType roleType);
	
	//删除角色
	public Integer deleteRole(Integer[] ids);
	
	//校验角色名不重复
	public RoleType queryByRoleName(String roleName);
	
	
	
}
