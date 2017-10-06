package com.shsxt.crm.dao;

import java.util.List;

import com.shsxt.crm.vo.UserRole;

public interface UserRoleDao {

	//添加用户角色关联
	public Integer insertUserRole(List<UserRole> userRoles);
	
	//根据userid查询角色数量
	public Integer queryCount(Integer userId);
	
	//批量删除
	public Integer deletebatch(Integer userId);
	
	
}
