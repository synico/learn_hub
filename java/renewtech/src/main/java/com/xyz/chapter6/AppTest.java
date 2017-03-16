package com.xyz.chapter6;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@SuppressWarnings("all")
public class AppTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext appContext = new FileSystemXmlApplicationContext("/src/main/resources/applicationContext.xml");
		try {
			Thread.sleep(1000*60*3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
