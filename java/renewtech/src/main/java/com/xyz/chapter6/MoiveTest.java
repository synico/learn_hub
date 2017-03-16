package com.xyz.chapter6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.renewtech.TestAspect;

import com.xyz.util.AppContextUtils;

@SuppressWarnings("all")
public class MoiveTest {
	
	private static Log log = LogFactory.getLog(TestAspect.class);

	public static void main(String[] args) {
		ApplicationContext ctx = AppContextUtils.loadXMLAppContext("classpath:6.9.2_AppContext.xml");
		AutowiredTest at = new AutowiredTest();
		at.testAutowired();
		log.info("classloader of MovieTest: " + Thread.currentThread().getContextClassLoader());
	}

}
