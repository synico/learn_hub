package com.nliu.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class TestHtmlUnitDriver {
	
	public static void testHtmlUnit() {
		WebDriver webDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
		webDriver.get("http://whatismyipaddress.com/ip/12.9.138.11");
		WebElement ISP = null;
		WebElement country = null;
		try{
			ISP = webDriver.findElement(By.xpath("//*[@id='section_left_3rd']/form[2]/table/tbody/tr[5]/td"));//*[@id="section_left_3rd"]/form[2]/table/tbody/tr[5]/td
			country = webDriver.findElement(By.xpath("//*[@id='section_left_3rd']/table/tbody/tr[2]/td"));//*[@id="section_left_3rd"]/table/tbody/tr[2]/td
			
		}catch(Exception ex){
			System.out.println(webDriver.getPageSource());
		}
		if(ISP == null) {
			System.out.println(webDriver.getPageSource());
		} else {
			System.out.println("ISP: " + ISP.getText());
			System.out.println("Country: " + country.getText());
		}
	}
	
	public static void delayAction(String seq) {
		try {
			Thread.sleep(5000);
			System.out.println("end of delayAction: " + seq);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void callHtmlUnit() {
		BlockingQueue<String> ipList = new ArrayBlockingQueue<String>(50);
		for(int i=0;i<30;i++) {
			ipList.offer("12.9.138." + i);
		}
		
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
		
		ses.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				final String ipAddr = ipList.poll();
				if(ipAddr != null) {
					Calendar cal = new GregorianCalendar();
					cal.setTimeInMillis(System.currentTimeMillis());
					SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					System.out.println(dateformat.format(cal.getTime()) + "|" + ipAddr);
//					testHtmlUnit();
					delayAction(ipAddr);
				} else {
					ses.shutdown();
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
		
	}
	
	public static void threadFactory() {
		int numOfThread = 1000;
//		CountDownLatch startGate = new CountDownLatch(1);
		CountDownLatch endGate = new CountDownLatch(numOfThread);
		Thread threads[] = new Thread[numOfThread];
		for(int i=0;i<numOfThread;i++) {
			threads[i] = new Thread(new Runnable() {
				public void run() {
					try {
//						startGate.await();
						callURL();
						endGate.countDown();
					} catch (Exception e) {
						e.printStackTrace();
					}
//					System.out.println(System.currentTimeMillis());
				}
			});
			threads[i].start();
		}
//		startGate.countDown();
		try {
			endGate.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void callURL() {
		WebDriver webDriver = new HtmlUnitDriver(BrowserVersion.CHROME);
		webDriver.get("http://www.baidu.com");
//		System.out.println(webDriver.getPageSource().length());
	}

	public static void main(String[] args) {
//		for(int i=0; i<3; i++) {
//			Thread a = new Thread(new Runnable() {
//				public void run() {
//					testHtmlUnit();
//					System.out.println();
//				}
//			});
//			try {
//				Thread.sleep(5000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			a.start();
//		}
//		callHtmlUnit();
		threadFactory();
	}

}
