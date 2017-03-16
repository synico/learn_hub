package com.xyz.chapter6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanWithFactory {
	
	private static Log log = LogFactory.getLog(BeanWithFactory.class);
	
	private static volatile BeanWithFactory beanFactory = null;
	
	private BeanWithFactory() {}
	
	public BeanWithFactory(OrderBean orderBean) {
		log.info("initialize BeanWithFactory with OrderBean");
	}
	
	public static BeanWithFactory getInstance() {
		log.info("enter method BeanWithFactory.getInstance");
		if(beanFactory == null) {
			synchronized(BeanWithFactory.class) {
				if(beanFactory == null) {
					beanFactory = new BeanWithFactory();
				}
			}
		}
		log.info("exit method BeanWithFactory.getInstance");
		return beanFactory;
	}
	
}
