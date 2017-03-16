package com.xyz.aop.pointcut;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SystemArchitecture {
	
	Log log = LogFactory.getLog(this.getClass());
	
	@Pointcut("within(com.xyz.web..*)")
	public void inWebLayer() {
		log.info("SystemArchitecture.inWebLayer");
	}
	
	@Pointcut("within(com.xyz.service..*)")
	public void inServiceLayer() {
		log.info("SystemArchitecture.inServiceLayer");
	}
	
	@Pointcut("within(com.xyz.dao..*)")
	public void inDataAccessLayer() {
		log.info("SystemArchitecture.inDataAccessLayer");
	}
	
	@Pointcut("execution(* com.xyz..service.*.*(..))")
	public void businessService() {
		log.info("SystemArchitecture.businessService");
	}
	
	@Pointcut("execution(* com.xyz..dao.*.*(..))")
	public void dataAccessOperation() {
		log.info("SystemArchitecture.dataAccessOperation");
	}

}
