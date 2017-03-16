package com.renewtech.aop.proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyInvocationTest {
	
	static Log log = LogFactory.getLog(MyInvocationTest.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("enter method ProxyTest.main");
		UserService userService = new UserServiceImpl();
		
		MyInvocationHandler invokeHandler = new MyInvocationHandler(userService);
		
		UserService proxy = (UserService)invokeHandler.getProxy();
		
		proxy.addUser();
		
		log.info("exit method ProxyTest.main");
	}

}
