package com.kasback.webApplication.uiTesting;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.pages.KasbackLoginPage;

public class KasbackLogin extends AbstractTest{
	WebDriver driver = null;
	KasbackLoginPage loginPage = null;
	
	
	
	@Test
	public void buyerSignUp() {
		driver = getDriver();
		loginPage.browseURL(environment.get("signupurl"));
		loginPage = new KasbackLoginPage(driver);
		loginPage.signUpAsBuyer();
	}
	
	@Test(dependsOnMethods="buyerSignUp")
	public void doLogin() {
		driver = getDriver();
		loginPage = new KasbackLoginPage(driver);
		loginPage.browseURL(environment.get("baseurl"));
		loginPage.login();
	}
	
	
	
}
