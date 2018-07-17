package com.kasback.pages;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;

import com.kasback.abstestbase.AbstractPage;
import com.kasback.abstestbase.UITestBase;

public class KasbackLoginPage extends AbstractPage{
	WebDriver driver;
	KasbackHomePage homepage = null;
	UITestBase uitestbase = new UITestBase();
	static Random rad = new Random();
	String password="Test@1";
	
	@BeforeClass
		 public static String randomEmail() {
			String email = null;
			for (int j=1; j<=1; j++ )
	        {
				email="usename"+rad.nextInt(10000)+"@gmail.com";
	        System.out.print(email);  
	        }
	        return email;
	    }
	
	
	static String buyerId = randomEmail();
	static String sellerId = randomEmail();
	
	public KasbackLoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// TODO Auto-generated constructor stub
	}

	public void browseURL(String url) {
		driver.get(url);
		setPageLoadTimeout(20000);
	}
	
	
	public void enterEmailID(String emailID) {
		checkPageIsReady();
		getWebElement("enterEmail").sendKeys(buyerId);
	}
	
	
	public void enterPassword() {
		checkPageIsReady();
		getWebElement("enterPassword").sendKeys(password);
	}
	
	public void logout() {
		checkPageIsReady();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(getWebElement("profileMenu")).perform();
		click(getWebElement("logoutButton"));
		click(getWebElement("okButtonOnLogoutPopup"));
		checkPageIsReady();
	}
	
	
	
	public void signUpCommon(String type, String id) {
		uitestbase = new UITestBase();
		String firstname = "automationFirst";
		String lastname = "automationlast";
		getWebElement("firstName").sendKeys(firstname);
		getWebElement("lastName").sendKeys(lastname);
		getWebElement("email").sendKeys(buyerId);
		getWebElement("mobileNumber").sendKeys("9999999999");
		click(getWebElement("genderMale"));
		selectByOption(getWebElement("usertype"), type);
		selectByOption(getWebElement("selectCountry"), "India");
		selectByOption(getWebElement("selectState"), "Delhi");
		selectByOption(getWebElement("selectCity"), "Delhi");
		getWebElement("postalCode").sendKeys("123456");
		getWebElement("addressLine1").sendKeys("test");
		getWebElement("password").sendKeys(password);
		getWebElement("confirmPassword").sendKeys(password);
		click(getWebElement("termsCheckbox"));
		getWebElement("digitalSign").sendKeys(firstname + " " + lastname);
		click(getWebElement("createAccountButton"));
		checkPageIsReady();
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		uitestbase.updateEmailVerificationStatus(id);
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		click(getWebElement("okButton"));
	}
	
	public String signUpAsBuyer() {
		signUpCommon("Buyer", buyerId);
		checkPageIsReady();
		return buyerId;
	}
	
	public String signUpAsSeller() {
		signUpCommon("Seller", sellerId);
		checkPageIsReady();
		return sellerId;
	}
	
	public KasbackLoginPage login() {
		click(getWebElement("login/SignUpButton"));
		enterEmailID(buyerId);
		enterPassword();
		click(getWebElement("loginButton"));
		return new KasbackLoginPage(driver);
	}

}
