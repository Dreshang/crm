package com.shsxt.crm.vo;

import java.util.ArrayList;
import java.util.List;

public class UserInfo extends User{

	private String roleName;
	private String roleIdStr;
	private List<Integer> roleIds=new ArrayList<Integer>();
	
	public List<Integer> getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleIdStr() {
		return roleIdStr;
	}
	public void setRoleIdStr(String roleIdStr) {
		this.roleIdStr = roleIdStr;
	}
	
	
	
}
