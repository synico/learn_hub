package com.renewtech.aop.proxy;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class MyInterceptor implements MethodInterceptor {
	
	static Log log = LogFactory.getLog(MyInterceptor.class);
	
	private Object target;
	
	public MyInterceptor(Object target) {
		this.target = target;
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		log.info("enter MyInterceptor.intercept");
		Object retVal = proxy.invoke(target, args);
		log.info("exit MyInterceptor.intercept");
		return retVal;
	}
	
	public static Object proxy(Object target) {
		log.info("enter MyInterceptor.proxy");
		Object retVal = Enhancer.create(target.getClass(), new MyInterceptor(target));
		log.info("exit MyInterceptor.proxy");
		return retVal;
	}

}
