package com.shsxt.crm.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shsxt.base.AssertUtil;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.service.UserService;
import com.shsxt.crm.utils.LoginUserUtil;
import com.shsxt.crm.vo.User;

public class NoAccessInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Integer id = LoginUserUtil.releaseUserIdFromCookie(request);
		AssertUtil.isTrue(id==null||id.equals(""),CrmConstant.NOT_LOGIN_MSG,CrmConstant.NOT_LOGIN_CODE);
		User user = userService.queryById(id);
		AssertUtil.isTrue(null==user, CrmConstant.NOT_LOGIN_MSG, CrmConstant.NOT_LOGIN_CODE);
		return true;
	}
}
