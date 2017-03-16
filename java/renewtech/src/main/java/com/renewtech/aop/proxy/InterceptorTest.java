package com.renewtech.aop.proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InterceptorTest {
	
	static Log log = LogFactory.getLog(InterceptorTest.class);
	
	public static void main(String args[]) {
		UserServiceImpl userService = (UserServiceImpl)MyInterceptor.proxy(new UserServiceImpl());
		userService.addUser();
		userService.testDefaultMethod();
	}

}
