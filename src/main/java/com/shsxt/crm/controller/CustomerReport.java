package com.shsxt.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shsxt.base.BaseController;

@Controller
@RequestMapping("report")
public class CustomerReport extends BaseController{

	@RequestMapping("/{page}")
	public String index(@PathVariable("page")Integer page){
		switch (page) {
		case 0:return "customer_contribution";
		case 1:return "customer_gc";
		case 2:return "customer_serve";
		case 3:return "customer_loss";
		default:return "error";
		}
		
	}
}
