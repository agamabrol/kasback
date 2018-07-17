package com.kasback.pages;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import com.kasback.abstestbase.AbstractPage;
import com.kasback.abstestbase.UITestBase;

public class KasbackOrderWorkflowsPage extends AbstractPage {

	WebDriver driver;
	UITestBase uitestbase = new UITestBase();
	KasbackLoginPage login = null;

	public KasbackOrderWorkflowsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void clickBuyNowButton() {
		checkPageIsReady();
		click(getWebElement("buyNowButton"));
	}

	public void manageAddress() {
		if (isWebElementDisplayed("selectAddressRadioButton")) {
			click(getWebElement("selectAddressRadioButton"));
		} else {
			click(getWebElement("addNewAddressButton"));
			getWebElement("addressLine1").sendKeys("test address");
			// Select Address Type
			selectByIndex(getWebElement("selectType"), 2);
			// Select Country
			selectByIndex(getWebElement("selectCountry"), 1);
			// Select State
			selectByIndex(getWebElement("selectState"), 1);
			// Select City
			selectByIndex(getWebElement("selectCity"), 1);
			getWebElement("postalCode").sendKeys("123456");
			click(getWebElement("saveNewAddressButton"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			click(getWebElement("closeAddressModal"));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

	}

	public void selectWalletPaymentMethod() {
		login = new KasbackLoginPage(driver);
		String handle = driver.getWindowHandle();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(getWebElement("buyButton"));

		driver.switchTo().frame(getWebElement("paymentModalFrame"));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getWebElement("phoneNumber").sendKeys("9999999999");
		
		click(getWebElement("netbankingButton"));
		click(getWebElement("selectFirstAvailableBank"));
		click(getWebElement("payButton"));
		
		String parentWindow = driver.getWindowHandle();
		Set<String> handles =  driver.getWindowHandles();
		   for(String windowHandle  : handles)
		       {
		       if(!windowHandle.equals(parentWindow))
		          {
		    	   try {
		          driver.switchTo().window(windowHandle);
					click(getWebElement("successPaymentButton"));
					
		    	   }catch(NoSuchWindowException e) {
		    		   e.getMessage();
		    	   }
		          }
		       }
		   driver.switchTo().window(parentWindow);
		   click(getWebElement("buton2"));
		   isTextPresentInPage("1/1");
			
		
		
	}

}
