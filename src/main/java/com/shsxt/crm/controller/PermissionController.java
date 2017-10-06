package com.shsxt.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shsxt.crm.model.MessageModel;
import com.shsxt.crm.service.PermissionService;

@Controller
@RequestMapping("permission")
public class PermissionController {

	
	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("savaPermission")
	@ResponseBody
	public MessageModel savaPermission(Integer rid,Integer[] moduleIds){
		MessageModel messageModel=new MessageModel();
		permissionService.savePermission(rid, moduleIds);
		return messageModel;	
	}
}
