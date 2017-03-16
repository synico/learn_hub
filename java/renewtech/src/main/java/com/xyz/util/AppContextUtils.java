package com.xyz.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextUtils {
	
	private static ApplicationContext ctx;
	
	private AppContextUtils() {}
	
	public static ApplicationContext loadXMLAppContext(String location) {
		if(ctx == null) {
			synchronized(AppContextUtils.class) {
				if(ctx == null) {
					ctx = new ClassPathXmlApplicationContext(location);
				}
			}
		}
		return ctx;
	}

}
