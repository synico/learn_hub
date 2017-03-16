package com.xyz.chapter6;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.renewtech.TestAspect;

public class AutowiredTest {
	
	private static Log log = LogFactory.getLog(TestAspect.class);
	
	@Autowired
	SimpleMovieLister sl;
	
	public void testAutowired() {
		if(sl == null) {
			log.info("autowired fail: " + this.getClass().getClassLoader());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
