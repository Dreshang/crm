package com.shsxt.crm.proxy;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shsxt.base.AssertUtil;
import com.shsxt.base.exceptions.ParamsException;
import com.shsxt.crm.PermissionRequest;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.dao.PermissionDao;
import com.shsxt.crm.dao.UserDao;
import com.shsxt.crm.utils.LoginUserUtil;

@Component
@Aspect
public class PermissionProxy {

	@Autowired
	private HttpServletRequest request;
	@Autowired
	private PermissionDao PermissionDao;
	@Autowired
	private UserDao userDao;
	
	@Before("execution(* com.*.*.controller.*.*(..))")
	public void preMethod(JoinPoint joinPoint){
		MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
		Method method=methodSignature.getMethod();
		PermissionRequest permissionRequest=method.getAnnotation(PermissionRequest.class);
		List<String> aclValues =null;
		if (null!=permissionRequest) {
			String requestVal=permissionRequest.requestVal();
			Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
			AssertUtil.isTrue(null==userId||null==userDao.queryById(userId), CrmConstant.NOT_LOGIN_MSG);
			//List<String> aclValues = PermissionDao.queryModuleByUserId(userId);
			aclValues = (List<String>) request.getSession().getAttribute(CrmConstant.PERMISSION_VALUES);
			if (null==aclValues||aclValues.size()<1) {
				aclValues=PermissionDao.queryModuleByUserId(userId);
				request.getSession().setAttribute(CrmConstant.PERMISSION_VALUES,aclValues);
			}
			if (!aclValues.contains(requestVal)) {
				System.err.println(aclValues);
				throw new ParamsException("没有访问权限", 300);
			}
		
		}else{
			return;
		}
	}
}
