package com.renewtech.aop.proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserServiceImpl implements UserService {
	
	Log log = LogFactory.getLog(this.getClass());

	@Override
	public void addUser() {
		log.info("enter method UserServiceImpl.addUser");
	}
	
	protected void testProtectedMethod() {
		log.info("enter method UserServiceImpl.testProtectedMethod");
	}
	
	public void testDefaultMethod() {
		log.info("enter method UserServiceImpl.testDefaultMethod");
	}

}
