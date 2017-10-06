package com.shsxt.crm.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.RoleDao;
import com.shsxt.crm.query.RoleQuery;
import com.shsxt.crm.vo.RoleType;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 查询下拉列表
	 * @param roleQuery
	 * @return
	 */
	public List<RoleType> queryAllRoles(RoleQuery roleQuery){
		return roleDao.queryAllRoles(roleQuery);
	}
	
	
	/**
	 * 
	 * 查询列表
	 * @param roleQuery
	 * @return
	 */
	public Map<String, Object> queryRole(RoleQuery roleQuery){
		PageHelper.startPage(roleQuery.getPage(),roleQuery.getRows());
		List<RoleType> roleTypes = roleDao.queryAllRoles(roleQuery);
		PageInfo<RoleType> pageInfo=new PageInfo<RoleType>(roleTypes);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", pageInfo.getList());
		return map;
	}
	
	/**
	 * 添加角色
	 * @param roleType
	 */
	public void insertRoles(RoleType roleType){
		checkRoleParam(roleType.getRoleName(),roleType.getRoleRemark());
		AssertUtil.isTrue(null!=roleDao.queryByRoleName(roleType.getRoleName()), "角色名已存在");
		roleType.setIsValid(1);
		roleType.setCreateDate(new Date());
		roleType.setUpdateDate(new Date());
		AssertUtil.isTrue(roleDao.insertRole(roleType)<1, CrmConstant.OPS_FAILED_MSG);
	}
	//校验
	private void checkRoleParam(String roleName, String roleRemark) {
		AssertUtil.isTrue(null==roleName || roleName=="", "角色名非空");
		AssertUtil.isTrue(null==roleRemark || roleRemark=="", "角色备注非空");
	}
	/**
	 * 更新角色
	 * @param roleType
	 */
	public void updateRole(RoleType roleType){
		checkRoleParam(roleType.getRoleName(),roleType.getRoleRemark());
		RoleType role = roleDao.queryByRoleName(roleType.getRoleName());
		AssertUtil.isTrue(null!=role&&role.getId()!=roleType.getId(), "角色名已存在");
		roleType.setUpdateDate(new Date());
		roleType.setIsValid(1);
		AssertUtil.isTrue(roleDao.updateRole(roleType)<1, CrmConstant.OPS_FAILED_MSG);
	}
	/**
	 * 删除
	 * @param ids
	 */
	public void deleteRoles(Integer[] ids){
		AssertUtil.isTrue(null==ids||ids.length<=0, "请选择要删除的记录");
		for (Integer id:ids) {
			AssertUtil.isTrue(null==roleDao.queryById(id), "编号为"+id+"的记录不存在");
		}
		AssertUtil.isTrue(roleDao.deleteRole(ids)<ids.length, CrmConstant.OPS_FAILED_MSG);
	}
}
