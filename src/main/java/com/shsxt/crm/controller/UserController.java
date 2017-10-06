package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.base.exceptions.ParamsException;
import com.shsxt.crm.PermissionRequest;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.model.UserModel;
import com.shsxt.crm.query.UserQuery;
import com.shsxt.crm.service.CustomerManagerService;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.CustomerManager;
import com.shsxt.crm.vo.User;
import com.shsxt.crm.vo.UserInfo;


@Controller
@RequestMapping("user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	@Autowired
	private CustomerManagerService CustomerManagerService;
	
	@RequestMapping("index/{type}")
	public String Index(@PathVariable("type")Integer type){
		switch (type) {
		case 1:return "user";
		case 2:return "role";	
		default: return "error";
		}
	}
	
	@RequestMapping("queryUserById")
	@ResponseBody
	private User queryUserById(Integer id){
		return userService.queryById(id);
	}
	
	//删除用户+UserRole
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer [] ids){
		MessageModel messageModel =new MessageModel();
		userService.deleteUser(ids);
		return messageModel;
	}
	
	
	//修改用户信息
	@RequestMapping("update")
	@ResponseBody
	public MessageModel Update(UserInfo userInfo){
		MessageModel messageModel =new MessageModel();
		userService.updateUser(userInfo);
		return messageModel;	
		
	}
	
	
	
	//添加用户信息+role
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel Insert(UserInfo userInfo){
		MessageModel messageModel =new MessageModel();
		userService.insertUserAndRole(userInfo);
		return messageModel;
	}
	
	
	
	
	//查询用户信息+role
	@RequestMapping("queryUsersByParams")
	@ResponseBody
	public Map<String, Object> queryUsersByParams(UserQuery userQuery){
		return userService.queryUserInfo(userQuery);
	}
	
	
	
	//查找顾客经理
	@RequestMapping("queryAllCustomerManager")
	@ResponseBody
	public List<CustomerManager> queryAllCustomerManager(){
		return CustomerManagerService.queryCustomerManagers();
	}
	
	
	//修改密码
	@RequestMapping("userPwdModify")
	@ResponseBody
	public MessageModel userPwdModify(HttpServletRequest request ,String oldPassword,String newPassword,String confirmPassword){
		Integer id=LoginUserUtil.releaseUserIdFromCookie(request);
		MessageModel messageModel=new MessageModel();
		
		try {
			userService.updateUserPwd(oldPassword, newPassword, confirmPassword, id);
		} 
		catch (ParamsException e) {
			e.printStackTrace();
			messageModel.setCode(e.getCode());
			messageModel.setMsg(e.getErrorMsg());
		}catch (Exception e) {
			e.printStackTrace();
			messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
			messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
		}
		return messageModel;
	}
	
	
	
	
	

	//登录
	@PermissionRequest(requestVal=("10"))
	@RequestMapping("userLogin")
	@ResponseBody
	private MessageModel userLogin(String userName,String userPwd){
		MessageModel messageModel=new MessageModel();
		try {
			UserModel userModel = userService.userLoginCheck(userName, userPwd);
			messageModel.setResult(userModel);
		} catch (ParamsException e) {
			e.printStackTrace();
			messageModel.setCode(e.getCode());
			messageModel.setMsg(e.getErrorMsg());
		}catch (Exception e) {
			messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
			messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
		}
		return messageModel;
	}
}
