package com.renewtech.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyInvocationHandler implements InvocationHandler {
	
	Log log = LogFactory.getLog(this.getClass());
	
	private Object target;
	
	public MyInvocationHandler(Object target) {
		super();
		log.info("enter constructor of MyInvocationHandler");
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.info("before invoke target method");
		Object result = method.invoke(target, args);
		log.info("after invoke target method");
		return result;
	}
	
	public Object getProxy() {
		log.info("enter method MyInvocationHandler.getProxy");
		Object obj = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), target.getClass().getInterfaces(), this);
		log.info("exit method MyInvocationHandler.getProxy");
		return obj;
	}

}
