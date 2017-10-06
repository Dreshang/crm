package com.shsxt.crm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;

import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.vo.User;
import com.shsxt.crm.vo.UserInfo;

public interface UserDao {
	
	public User queryById(Integer id);
	
	//根据用户名查找user
	public User queryUserByUserName(String userName);
	
	//更新密码
	public Integer updateUserPwd(User user);

	
	//查询用户信息+角色
	public List<UserInfo> queryUserInfo(UserQuery userQuery);
	
	//添加用户信息
	public Integer insertUser(UserInfo userInfo);
	
	//更新用户信息
	public Integer updateUser(UserInfo userInfo);
	
	//删除用户
	public Integer deleteUser(Integer[] ids);
}
