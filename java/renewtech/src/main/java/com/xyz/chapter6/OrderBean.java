package com.xyz.chapter6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrderBean {
	
	private Log log = LogFactory.getLog(OrderBean.class);
	
	private OrderBean() {
		log.info("constructor of OrderBean");
		System.out.println("constructor of OrderBean");
	}
	
	public String toString() {
		return "OrderBean";
	}

}
