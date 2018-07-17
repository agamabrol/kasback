package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackOrderWorkflowsPage;
import com.kasback.pages.KasbackHomePage;
import com.kasback.pages.KasbackLoginPage;

public class KasbackHome extends AbstractTest{
	WebDriver driver;
	KasbackLoginPage loginPage = null;
	KasbackLogin login = null;
	KasbackHomePage homepage = null;
	KasbackOrderWorkflowsPage orderpage = null;
	
	
	/*@BeforeClass
	public void loadDriver() {
	}*/
	
	@Test(enabled = true)
	public void login() {
		driver = getDriver();
		homepage =  new KasbackHomePage(driver);
		loginPage = new KasbackLoginPage(driver);
		orderpage = new KasbackOrderWorkflowsPage(driver);				
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login();
	}
	
	
	
	@AfterClass()
	public void quitDriver() {
		driver.quit();
	}
	
}
