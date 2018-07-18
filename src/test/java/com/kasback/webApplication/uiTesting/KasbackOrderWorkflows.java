package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackOrderWorkflowsPage;
import com.kasback.pages.KasbackHomePage;
import com.kasback.pages.KasbackLoginPage;

public class KasbackOrderWorkflows extends AbstractTest{

	WebDriver driver;
	KasbackLoginPage loginPage = null;
	KasbackLogin login = null;
	KasbackHomePage homepage = null;
	KasbackOrderWorkflowsPage workflowsPage = null;
	String emailId;
	
	
	@Test
	public void sellerSignUp() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("signupurl"));
		loginPage = new KasbackLoginPage(driver);
		emailId = loginPage.signUpAsSeller();
	}
	
	
	
	@Test(dependsOnMethods="sellerSignUp")
	public void loginAsSeller() {
		homepage =  new KasbackHomePage(driver);
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login();
	}
	
	
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
