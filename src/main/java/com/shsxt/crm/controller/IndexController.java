package com.shsxt.crm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shsxt.base.BaseController;


/**
 * 
 * @author lp
 *  登录+主页 页面转发controller
 */
@Controller
public class IndexController extends BaseController{
	
	/**
	 * 转发到登录页面
	 * @return
	 */
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	
	
	/**
	 * 转发到主页面
	 * @return
	 */
	@RequestMapping("main")
	public String main(HttpServletRequest request){
		request.getCookies();
		
		return "main";
	}
	
	

}
