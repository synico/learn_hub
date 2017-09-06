package com.selenium.jenkins;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class RunCopyJob {
	
	private static final String JENKINS_URL = "http://jenkins-em.avnet.com:8080/login";
	private static final String MUSEUM_URL = "http://www.snhm.org.cn/";
	private static final Log log = LogFactory.getLog(RunCopyJob.class);
	
	private WebDriver webDriver = new HtmlUnitDriver();
	
	private boolean login2Jenkins() {
		boolean rst = false;
		webDriver.get(JENKINS_URL);
		log.info(webDriver.getPageSource());
		//Enter username
		WebElement usernameField = webDriver.findElement(By.id("j_username"));
		usernameField.sendKeys("nick.liu");
		//Enter password
		WebElement passwordField = webDriver.findElement(By.name("j_password"));
		passwordField.sendKeys("August2017");
		//Checked remember me checkbox
		WebElement rememberMeField = webDriver.findElement(By.id("remember_me"));
		rememberMeField.clear();
		//Submit login form
		WebElement submitField = webDriver.findElement(By.name("Submit"));
		submitField.submit();
		
		log.info(webDriver.findElement(By.xpath("//*[@id='job_RM_DX_Copy']/td[3]/a")));
		return rst;
	}
	
	private boolean getCurrentPersonsNum() {
		boolean rst = false;
		webDriver.get(MUSEUM_URL);
		// get current num of persons
//		WebElement numOfPerson = webDriver.findElement(By.id("currentPersonsNum"));
//		System.out.println(numOfPerson);
		log.info(webDriver.getPageSource());
		return rst;
	}
	
	public static void main(String[] args) {
		RunCopyJob cpJob = new RunCopyJob();
//		cpJob.login2Jenkins();
		cpJob.getCurrentPersonsNum();
	}

}
