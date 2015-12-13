/*
 * 
 */
package com.shell.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.shell.common.exception.DataAccessException;

/**
 * @author shell
 *
 */
@Aspect
@Component
public class DaoInterceptor {
	
	@Pointcut("execution(* com.shell..*DaoImpl.*(..))")
	private void throwing(){
		
	}
	
	@AfterThrowing(pointcut="throwing()", throwing="exception")
	public void afterThrowing(JoinPoint joinPoint, RuntimeException exception) throws DataAccessException {
		Object[] args = joinPoint.getArgs();
		System.out.println("对象为：" + joinPoint.getTarget());
		System.out.println("执行的方法：" + joinPoint.getSignature().getName());
		System.out.println("参数为：" + args);
		throw new DataAccessException(exception);
	}
}
