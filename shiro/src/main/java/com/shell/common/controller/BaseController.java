/*
 * 
 */
package com.shell.common.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.shell.common.enums.BackCodeEnum;
import com.shell.common.exception.DataAccessException;
import com.shell.common.model.ResultObject;
import com.shell.common.util.RequestUtil;

/**
 * @author shell
 *
 */
public class BaseController {
	
	protected Logger logger = Logger.getLogger(getClass().getName());
	
	protected HttpSession session;
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	@ModelAttribute
	public void init(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	
	@ExceptionHandler
	public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Exception exception) {
		String uri = request.getRequestURI();   // 记录请求uri
		Map<String, String[]> params = request.getParameterMap();  // 记录请求参数
		
		logger.error("出现错误的请求uri: " + uri);
		logger.error("请求参数为: " + params.toString());
		logger.error(exception);
		
		ResultObject resultObject = null;  // 存储返回给客户端的异常信息
		ModelAndView modelAndView = null;
		
		/** 判读异常是属于哪类异常，已返回客户端准确的信息 */
		if (exception instanceof DataAccessException) {
			resultObject = new ResultObject(BackCodeEnum.DATABASE_ERROR);
		} else {
			resultObject = new ResultObject(BackCodeEnum.SYSTEME_RROR);
		}
		
		/** 判断是否为异步请求  */
		if (RequestUtil.isAjax(request)) {
			try {
				PrintWriter writer = response.getWriter();
				writer.write(resultObject.toString());
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			modelAndView = new ModelAndView("error/error");
			modelAndView.addObject("errMsg",resultObject);
		}
		
		return modelAndView;
	}

}
