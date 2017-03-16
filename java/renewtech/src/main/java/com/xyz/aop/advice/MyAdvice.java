package com.xyz.aop.advice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Before("com.xyz.aop.pointcut.MyPointcut.tradingOperation()")
	public void beforeAdv(JoinPoint jp) {
		log.info("MyAdvice.beforeAdvice:" + jp.getKind());
	}
	
	@AfterReturning(pointcut="com.xyz.aop.pointcut.MyPointcut.tradingOperation()", returning="id")
	public void afterAdv(Long id) {
		log.info("MyAdvice.afterAdvice, returning: " + id);
	}
	
	@AfterThrowing(pointcut="com.xyz.aop.pointcut.MyPointcut.tradingOperation()",throwing="ex")
	public void afterThrowingAdv(Throwable ex) {
		log.info("MyAdvice.afterThrowingAdvice :" + ex.getMessage());
	}
	
	@Around("com.xyz.aop.pointcut.MyPointcut.tradingOperation() && args(id)")
	public Object aroundAdv(ProceedingJoinPoint pjp, String id) throws Throwable{
//		log.info("MyAdvice.aroundAdv");
		log.info("start of around advice, id:" + id);
		Object rst = pjp.proceed();
		log.info("end of around advice");
		return rst;
	}
	
}
