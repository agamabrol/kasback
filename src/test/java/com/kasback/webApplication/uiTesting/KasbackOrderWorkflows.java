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
	
	@Test(dependsOnMethods="buyerSignUp")
	public void loginAsBuyer() {
		homepage =  new KasbackHomePage(driver);
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login();
	}
	
	@Test(dependsOnMethods="sellerSignUp")
	public void loginAsSeller() {
		homepage =  new KasbackHomePage(driver);
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login();
	}
	
	@Test(dependsOnMethods="loginAsBuyer")
	public void placeNewOrder() {
		workflowsPage = new KasbackOrderWorkflowsPage(driver);
		homepage.selectAProduct();
		workflowsPage.clickBuyNowButton();
		workflowsPage.manageAddress();
		workflowsPage.selectWalletPaymentMethod();
		String ordersCount = pgutil.executeSelectQuery("select count(*) from kasback.order where buyer_id in (select id from kasback.user where email="+"'"+emailId+"')");
		log.info("Orders count is "+ordersCount);
		Assert.assertEquals(ordersCount, "1");
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
}
