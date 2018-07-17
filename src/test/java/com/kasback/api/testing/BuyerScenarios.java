package com.kasback.api.testing;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.kasback.abstest.BuyerApiTestbase;
import com.kasback.abstest.UserAPITestBase;
import com.kasback.abstestbase.AbstractTest;
import com.kasback.abstestbase.UITestBase;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class BuyerScenarios extends AbstractTest{
	
	public ClientConfig config = new DefaultClientConfig();
	public Client client = Client.create(config);
	BuyerApiTestbase buyerTestBase = null;
	UserAPITestBase userTestBase = null;
	UITestBase uiTestbase = null;
	String buyerEmail;
	
	@BeforeClass(enabled = true, description = "login API to get api key", groups = { "SANITY_GROUP" })
	public void beforeclass() {
		buyerTestBase = new BuyerApiTestbase();
		userTestBase = new UserAPITestBase();
		uiTestbase = new UITestBase();
	}

	@Test
	public void createBuyer() {
		buyerEmail = buyerTestBase.createNewBuyer();
	}
	
	@Test
	public void tryToCreateExistingBuyer() {
		String existingBuyerEmailID = userTestBase.getExistingBuyerEmail();
		userTestBase.createUser(existingBuyerEmailID, "buyer");
	}
	
	@Test(dependsOnMethods="createBuyer", description="Verify that Unverified user is not able to login")
	public void tryToLoginUnverifiedBuyer() {
		login(buyerEmail, "Agam@1");
	}
	
	@Test(dependsOnMethods="createBuyer", description="Verify that Verified user is able to login successfully")
	public void loginVerifiedBuyer() {
		uiTestbase.updateEmailVerificationStatus(buyerEmail);
		login(buyerEmail, "Agam@1");
	}
	
	@Test(dependsOnMethods="loginVerifiedBuyer")
	public void getUserFavourites() {
		buyerTestBase.getAllFavourites();
	}
	
	@Test(dependsOnMethods="loginVerifiedBuyer")
	public void getSingleFavourite() {
		buyerTestBase.getSingleFavourite();
	}
	
	@Test(dependsOnMethods="loginVerifiedBuyer")
	public void addProductToCart() {
		buyerTestBase.addProductToCart(getProductId());
	}
	
	@Test(dependsOnMethods="addProductToCart")
	public void getCart() {
		buyerTestBase.getCart();
	}
	
	@Test(dependsOnMethods="loginVerifiedBuyer")
	public void filterProductBasedOnPrice() {
		buyerTestBase.filterProductBasedOnPrice();
	}
	
	/*@Test
	public void resetPwd() {
		buyerEmail = buyerTestBase.createNewBuyer();
		loginVerifiedBuyer();
		userTestBase.forgotPwd(buyerEmail);
		userTestBase.resetPwd();
	}*/
	
	/*@Test(dependsOnMethods="loginVerifiedBuyer")
	public void resetPwd() {
		String emailId=userTestBase.getExistingSellerEmail();
		userTestBase.resetPwd(emailId);
	}*/
	
	/*@Test(dependsOnMethods="getCart")
	public void removeProductFromCart() {
		buyerTestBase.removeProductFromCart(getProductId());
	}*/
	
	@Test
	public void fullPlaceOrderWorkflow() {
		buyerTestBase.buyProductWorkflow();
	}
	
}
