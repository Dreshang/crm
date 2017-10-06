package com.shsxt.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.dto.ModuleDto;
import com.shsxt.crm.service.ModuleService;

@Controller
@RequestMapping("module")
public class ModuleController {

	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping("queryModule")
	@ResponseBody
	public List<ModuleDto> queryModule(Integer rid){
		return moduleService.queryModule(rid);
	}
}
