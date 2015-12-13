/*
 * 
 */
package com.shell.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shell.common.log.Log;
import com.shell.common.log.LogService;

/**
 * @author shell
 *
 */
@Aspect
@Component
public class ServiceInterceptor {
	@Autowired
	private LogService service;
	
	/**
	 * @param service the service to set
	 */
	public void setService(LogService service) {
		this.service = service;
	}
	
	
	@Pointcut("execution(* com.shell..*ServiceImpl.*(..))")
//	@Pointcut("execution(* com.shell.common.service.BaseService.*(..))")
//	@Pointcut("execution(* com.shell..*Service.*(..))")
	private void add() {
		
	}
	
	@Before("add()")
	public void beforeAdd() {
		// 插入前记录日志
		Log log = new Log();
		service.addLog(log);
	}
	
	@After("add()")
	public void afterAdd() {
		// 插入后记录日志
		Log log = new Log();
		service.addLog(log);
	}
	
	@AfterThrowing("add()")
	public void afterThrowing() {
		// 抛出异常时，记录日志
		Log log = new Log();
		service.addLog(log);
	}
}
