package com.kasback.pages;

import org.openqa.selenium.WebDriver;

import com.kasback.abstestbase.AbstractPage;
import com.kasback.abstestbase.UITestBase;


public class KasbackHomePage extends AbstractPage{
	WebDriver driver;
	UITestBase uitestbase = new UITestBase();
	String host = "imap.googlemail.com";// change accordingly
	String mailStoreType = "imap";
	String identificationcode;
	String finalCode;
	String productName="washing machine";


	public KasbackHomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void searchProductName() {
		checkPageIsReady();
		click(getWebElement("searchBox"));
		getWebElement("searchBox").sendKeys(productName);
	}
	
	public void clickProductName() {
		click(getWebElement("firstServiceInSearchAutoFill"));
	}
	
	

	
	public KasbackHomePage selectAProduct() {
		searchProductName();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickProductName();
		checkPageIsReady();
		return new KasbackHomePage(driver);
	}
	
	
	
	
	
}
