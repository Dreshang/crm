package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.dao.UserRoleDao;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.utils.Md5Util;
import com.shsxt.crm.utils.UserIDBase64;
import com.shsxt.crm.vo.CustomerLoss;
import com.shsxt.crm.vo.User;
import com.shsxt.crm.vo.UserInfo;
import com.shsxt.crm.vo.UserRole;

@Service
public class UserService {
	
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserRoleDao userRoleDao;
	
	public User queryById(Integer id){
		return userDao.queryById(id);
	}
	
	/**
	 * 批量删除用户信息
	 * @param ids
	 */
	public void deleteUser(Integer[] ids){
		AssertUtil.isTrue(null==ids||ids.length<=0, "请选择要删除的记录");
		for (Integer id:ids) {
			AssertUtil.isTrue(null==userDao.queryById(id), "id为"+id+"的记录不存在");
		}
		AssertUtil.isTrue(userDao.deleteUser(ids)<ids.length, CrmConstant.OPS_FAILED_MSG);
		for (Integer id:ids) {
		int count=userRoleDao.queryCount(id);
		if (count>0) {
			AssertUtil.isTrue(userRoleDao.deletebatch(id)<count, CrmConstant.OPS_FAILED_MSG);
		}
		}
	}
	
	
	
	
	
	
	
	/**
	 * 
	 * 修改用户信息
	 * @param userInfo
	 */
	public void updateUser(UserInfo userInfo){
		checkUserInfoParams(userInfo.getUserName(),userInfo.getUserPwd(),userInfo.getTrueName());
		AssertUtil.isTrue(null==userInfo.getId()||userDao.queryById(userInfo.getId())==null, "待更新记录不存在");
		User user = userDao.queryUserByUserName(userInfo.getUserName());
		AssertUtil.isTrue(null!=user && user.getId()!=userInfo.getId(), "用户名已存在!!!");
		userInfo.setUpdateDate(new Date());
		AssertUtil.isTrue(userDao.updateUser(userInfo)<1, CrmConstant.OPS_FAILED_MSG);
		int count = userRoleDao.queryCount(userInfo.getId());
		if (count>0) {
			AssertUtil.isTrue(userRoleDao.deletebatch(userInfo.getId())<count, CrmConstant.OPS_FAILED_MSG);
		}
		if (null!=userInfo.getRoleIds()&&userInfo.getRoleIds().size()>0) {
			insertUserRole(userInfo.getId(),userInfo.getRoleIds());
		}
	}
	
	
	
	/**
	 * 
	 * 添加用户信息+用户角色
	 * @param userInfo
	 */
			//添加用户信息
			public void insertUserAndRole(UserInfo userInfo){
				checkUserInfoParams(userInfo.getUserName(),userInfo.getUserPwd(),userInfo.getTrueName());
				User user = userDao.queryUserByUserName(userInfo.getUserName());
				AssertUtil.isTrue(user!=null, "用户名已存在!!!");
				userInfo.setIsValid(1);
				userInfo.setCreateDate(new Date());
				userInfo.setUpdateDate(new Date());
				AssertUtil.isTrue(userDao.insertUser(userInfo)<1, "用户添加失败!!!");
				//获取返回的主键
				int id =userInfo.getId();
				if (userInfo.getRoleIds()!=null) {
					insertUserRole(id,userInfo.getRoleIds());
				}
			 }
			//添加用户角色
			private void insertUserRole(int id, List<Integer> roleIds) {
				Integer count = userRoleDao.queryCount(id);
				if(count>0) {
					AssertUtil.isTrue(userRoleDao.deletebatch(id)<count, CrmConstant.OPS_FAILED_MSG);
				}
				List<UserRole> userRoles=new ArrayList<UserRole>();
				for(Integer roleId: roleIds){
					UserRole userRole=new UserRole();
				userRole.setUserId(id);
					userRole.setRoleId(roleId);
					userRole.setCreateDate(new Date());
					userRole.setUpdateDate(new Date());
					userRoles.add(userRole);
				}
				AssertUtil.isTrue(userRoleDao.insertUserRole(userRoles)<roleIds.size(), CrmConstant.OPS_FAILED_MSG);
			}
		
			//校验
			private void checkUserInfoParams(String userName, String userPwd, String trueName) {
				AssertUtil.isTrue(StringUtils.isBlank(userName), "用户名不能为空!!!");
				AssertUtil.isTrue(StringUtils.isBlank(userPwd), "用户密码不能为空!!!");
				AssertUtil.isTrue(StringUtils.isBlank(trueName), "真实姓名不能为空!!!");
			}

			
			
			
			
	//查询用户信息
	public Map<String, Object> queryUserInfo(UserQuery userQuery){
		PageHelper.startPage(userQuery.getPage(),userQuery.getRows());
		List<UserInfo> userInfos = userDao.queryUserInfo(userQuery);
		if (null!=userInfos&&userInfos.size()>0) {
			for (int i = 0; i < userInfos.size(); i++) {
				UserInfo userInfo = userInfos.get(i);
				String roleIdStr = userInfo.getRoleIdStr();
				if (null!=roleIdStr) {
					String[] roleIdStrs = roleIdStr.split(",");
				for (String temp : roleIdStrs) {
					userInfo.getRoleIds().add(Integer.parseInt(temp));
				}
				}
			}
		}
		PageInfo<UserInfo> pageInfo=new PageInfo<UserInfo>(userInfos);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	
	//修改密码
	public void updateUserPwd(String oldPassword,String newPassword,String confirmPassword,Integer id){

		
		User user =userDao.queryById(id);
		AssertUtil.isTrue(null==user||null==id, "用户未登录!!!");
		AssertUtil.isTrue(StringUtils.isBlank(oldPassword), "原始密码不能为空!!!");
		AssertUtil.isTrue(!user.getUserPwd().equals(Md5Util.encode(oldPassword)), "原始密码输入错误!!!");
		AssertUtil.isTrue(StringUtils.isBlank(newPassword ), "新密码不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(confirmPassword), "确认密码不能为空!!!");
		AssertUtil.isTrue(!newPassword.equals(confirmPassword), "两次密码输入不一致!!!");
		user.setUserPwd(Md5Util.encode(newPassword));
		AssertUtil.isTrue(userDao.updateUserPwd(user)<1, "更新失败!!!");
	}
	
	
	
	//验证登录
	public UserModel userLoginCheck(String userName,String userPwd){
		//先判断非空
		AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空!!!");
		AssertUtil.isTrue(StringUtils.isBlank(userPwd), "密码不能为空!!!");
		//调用dao查询
		User user = userDao.queryUserByUserName(userName);
		AssertUtil.isTrue(null==user, "用户不存在!!!");
		AssertUtil.isTrue(user.getIsValid()==0, "该用户已注销!!!");
		AssertUtil.isTrue(!Md5Util.encode(userPwd).equals(user.getUserPwd()), "密码不正确!!!");
		return buildUserModel(user);
	}
	//创建UserModel
	private UserModel buildUserModel(User user) {
		UserModel userModel=new UserModel();
		userModel.setUserName(user.getUserName());
		userModel.setTrueName(user.getTrueName());
		userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
		return userModel;
	} 

}
