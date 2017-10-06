package com.shsxt.base.exceptions;




import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shsxt.crm.constant.CrmConstant;
import com.shsxt.crm.model.MessageModel;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver{

	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv=getModelAndView();
		if (handler instanceof HandlerMethod) {
			if (ex instanceof ParamsException) {
				ParamsException pe=(ParamsException) ex;
				if (pe.getCode().equals(CrmConstant.NOT_LOGIN_CODE)) {
					mv.addObject("ctx", request.getContextPath());
					mv.addObject("errorMsg", pe.getErrorMsg());
					mv.addObject("code", pe.getCode());
					mv.addObject("uri", request.getRequestURI());
					mv.setViewName("error");
					return mv;
				}
			}
			//判断返回的是json还是视图
			HandlerMethod handlerMethod=(HandlerMethod) handler;
			Method method=handlerMethod.getMethod();
			ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
			if (null!=responseBody) {
				//返回json数据
				MessageModel messageModel=new MessageModel();
				messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
				messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
				if (ex instanceof ParamsException) {
					ParamsException paramsException=(ParamsException) ex;
					messageModel.setCode(paramsException.getCode());
					messageModel.setMsg(paramsException.getErrorMsg());
				}
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/json;charset=utf-8");
				PrintWriter pw=null;
				try {
					pw = response.getWriter();
				} catch (IOException e) {
					e.printStackTrace();
					messageModel.setCode(CrmConstant.OPS_FAILED_CODE);
					messageModel.setMsg(CrmConstant.OPS_FAILED_MSG);
				}finally {
					if (pw!=null) {
						pw.write(JSON.toJSONString(messageModel));
						pw.flush();
						pw.close();
					}
				}
				return null;
			}else{
				if (ex instanceof ParamsException) {
					ParamsException paramsException=(ParamsException) ex;
					mv.addObject("code", paramsException.getCode());
					mv.addObject("errorMsg", paramsException.getErrorMsg());
				}
				return mv;
			}
		}else{
			return mv;
		}
	}

	private ModelAndView getModelAndView() {
		ModelAndView mv=new ModelAndView();
		mv.addObject("code", CrmConstant.OPS_FAILED_CODE);
		mv.addObject("errorMsg", CrmConstant.OPS_FAILED_MSG);
		return mv;
	}



}
