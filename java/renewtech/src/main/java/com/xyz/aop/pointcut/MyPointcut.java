package com.xyz.aop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("all")
@Aspect
public class MyPointcut {
	
	
	@Pointcut("execution(* transfer(..))")//the pointcut expression
	private void anyOldTransfer() {}// the pointcut signature
	
	@Pointcut("execution(public * com.xyz.dao.*.*(..))")
	private void anyPublicOperation() {}
	
	@Pointcut("within(com.xyz.dao..*)")
	private void inOrderDaoService() {}
	
	@Pointcut("this(com.xyz.dao.OrderDaoService)")
	private void thisPointcut() {}
	
	@Pointcut("target(com.xyz.dao.OrderDaoService)")
	private void targetPointcut() {}
	
	@Pointcut("bean(orderDaoService)")
	private void beanPointcut() {}
	
//	@Pointcut("anyPublicOperation() && inOrderDaoService())")
//	@Pointcut("inOrderDaoService()")
//	@Pointcut("anyPublicOperation()")
	@Pointcut("thisPointcut()")
//	@Pointcut("targetPointcut()")
//	@Pointcut("beanPointcut()")
	public void tradingOperation() {}

}
