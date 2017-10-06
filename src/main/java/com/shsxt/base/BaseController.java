package com.shsxt.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
	
	/**
	 * 获取上下文路径
	 * @param request
	 */
	@ModelAttribute
	public void preMethod(HttpServletRequest request){
		request.setAttribute("ctx", request.getContextPath());
	}

}
