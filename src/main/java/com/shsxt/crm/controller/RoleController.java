package com.shsxt.crm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.base.BaseController;
import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.service.RoleService;
import com.shsxt.crm.vo.RoleType;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{

	@Autowired
	private RoleService roleService;
	
	//查询角色
	@RequestMapping("queryAllRoles")
	@ResponseBody
	public List<RoleType> queryAllRoles(RoleQuery roleQuery){
		return roleService.queryAllRoles(roleQuery);
	}
	
	//查询角色列表
	@RequestMapping("queryRolesByParams")
	@ResponseBody
	public Map<String, Object> queryRolesByParams(RoleQuery roleQuery){
		return roleService.queryRole(roleQuery);
	}
	
	//添加角色
	@RequestMapping("insert")
	@ResponseBody
	public MessageModel insert(RoleType roleType){
		MessageModel messageModel=new MessageModel();
		roleService.insertRoles(roleType);
		return messageModel;
	}
	
	//更新
	@RequestMapping("update")
	@ResponseBody
	public MessageModel update(RoleType roleType){
		MessageModel messageModel=new MessageModel();
		roleService.updateRole(roleType);
		return messageModel;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public MessageModel delete(Integer[] ids){
		MessageModel messageModel=new MessageModel();
		roleService.deleteRoles(ids);
		return messageModel;
	}
	
}
