package com.shsxt.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shsxt.crm.dao.ModuleDao;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dto.ModuleDto;

@Service
public class ModuleService {

	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private PermissionDao permissionDao;
	
	public  List<ModuleDto> queryModule(Integer rid){
		List<Integer> moduleIds=permissionDao.queryModuleId(rid);
		List<ModuleDto> moduleDtos = moduleDao.queryModule();
		if (null!=moduleDtos&&moduleDtos.size()>0) {
			for(ModuleDto moduleDto:moduleDtos){
				if (moduleIds.contains(moduleDto.getId())) {
					moduleDto.setChecked(true);
				}
			}
		}
		return moduleDtos;
	}
}
