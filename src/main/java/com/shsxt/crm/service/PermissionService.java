package com.shsxt.crm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.ModuleDao;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.vo.Module;
import com.shsxt.crm.vo.Permission;

@Service
public class PermissionService {

	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private ModuleDao moduleDao;
	
	//添加permission
	public void savePermission(Integer rid,Integer[] moduleIds){
		checkPermissionParam(rid,moduleIds);
		int count =permissionDao.queryCount(rid);
		if (count>0) {
			AssertUtil.isTrue(permissionDao.deletePermission(rid)<count, CrmConstant.OPS_FAILED_MSG);
		}
		List<Permission> permissions=new ArrayList<Permission>();
		Permission permission=null;
		for(int i=0;i<moduleIds.length;i++){
			Integer moduleId = moduleIds[i];
			permission=new Permission();
			Module module = moduleDao.queryById(moduleId);
			AssertUtil.isTrue(null==module, "授权权限不存在");
			permission.setRoleId(rid);
			permission.setModuleId(moduleId);
			permission.setAclValue(module.getOptValue());
			permission.setCreateDate(new Date());
			permission.setUpdateDate(new Date());
			permissions.add(i, permission);
		}
	AssertUtil.isTrue(permissionDao.insertBatch(permissions)<moduleIds.length, CrmConstant.OPS_FAILED_MSG);	;
		
	}

	private void checkPermissionParam(Integer rid, Integer[] moduleIds) {
		AssertUtil.isTrue(null==rid, "授权角色不存在");
		AssertUtil.isTrue(null==moduleIds||moduleIds.length<1, "角色为指定授权");
	}
}
