/*
 * 
 */
package com.shell.common.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shell.common.exception.DataAccessException;
import com.shell.common.log.Log;
import com.shell.common.log.LogService;

/**
 * @author shell
 *
 */
@Aspect
@Component
public class ServiceInterceptor {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private LogService service;
	
	/**
	 * @param service the service to set
	 */
	public void setService(LogService service) {
		this.service = service;
	}
	
	
	@Pointcut("execution(* com.shell..*ServiceImpl.*(..))")
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
	
	@AfterThrowing(pointcut="add()", throwing="exception")
	public void afterThrowing(JoinPoint joinPoint, RuntimeException exception) {
		// 抛出异常时，记录日志,打印日志
		Log log = new Log();
		service.addLog(log);
		
		Object[] args = joinPoint.getArgs();
		StringBuilder argsSb = new StringBuilder();
		logger.error("异常信息：" + exception.getMessage());
		logger.error("对象信息：" + joinPoint.getTarget().getClass().getName());
		logger.error("发生异常的方法信息：" + joinPoint.getSignature().getName());
		for (Object arg : args) {
			argsSb.append(arg.toString());
		}
		logger.error("出入参数信息：" + argsSb.toString());
		throw new DataAccessException(exception);
	}
}
