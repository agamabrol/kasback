package com.kasback.api.testing;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.kasback.abstest.SellerAPITestBase;
import com.kasback.abstest.UserAPITestBase;
import com.kasback.abstestbase.AbstractTest;
import com.kasback.abstestbase.UITestBase;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class SellerScenarios extends AbstractTest{

	Map<String, String> authMap1 = null;
	public ClientConfig config = new DefaultClientConfig();
	public Client client = Client.create(config);
	SellerAPITestBase sellerTestBase = null;
	UserAPITestBase userTestBase = null;
	UITestBase uiTestbase = null;
	String sellerEmail;

	@BeforeClass(enabled = true, description = "login API to get api key", groups = { "SANITY_GROUP" })
	public void beforeclass() {
		sellerTestBase = new SellerAPITestBase();
		userTestBase = new UserAPITestBase();
		uiTestbase = new UITestBase();
	}
	
	@Test( description="Verify a new Seller is created successfully")
	public void createNewSeller() {
		sellerEmail = sellerTestBase.createNewSeller();
	}
	
	@Test(dependsOnMethods="createNewSeller", description="Verify that Verified Seller is LoggedIn")
	public void loginVerifiedSeller() {
		uiTestbase.updateEmailVerificationStatus(sellerEmail);
		login(sellerEmail, "Agam@1");
	}
	
	@Test(dependsOnMethods="createNewSeller", description="Verify user is not able to login if Seller is not verified by Admin")
	public void loginUnVerifiedSeller() {
		login(sellerEmail, "Agam@1");
	}
	
	@Test(dependsOnMethods="loginVerifiedSeller", description="Verify user is not able to login with case insenstiive credentials")
	public void loginWithInvalidCredentials() {
		login(sellerEmail, "agam@1");
	}
	
	@Test
	public void tryToCreateExistingSeller() {
		String existingSellerEmailID = userTestBase.getExistingSellerEmail();
		userTestBase.createUser(existingSellerEmailID, "seller");
	}
	
	/*@Test
	public void addProductForVerifiedSeller() {
		sellerEmail = sellerTestBase.createNewSeller();
		uiTestbase.updateEmailVerificationStatus(sellerEmail);
		login(sellerEmail, "Agam@1");
		addNewProduct("phone");
	}
	
	@Test
	public void addProductForUnVerifiedSeller() {
		sellerEmail = sellerTestBase.createNewSeller();
		login(sellerEmail, "Agam@1");
		sellerTestBase.addNewProduct("phone");
	}*/
	
	@Test
	public void blockUserAndLogin() {
		String existingSellerEmailID = userTestBase.getExistingSellerEmail();
		userTestBase.blockUser(existingSellerEmailID);
		userTestBase.login(existingSellerEmailID, "Agam@1");
		
	}
	
	@Test(dependsOnMethods="blockUserAndLogin")
	public void unblockUserAndLogin() {
		String existingSellerEmailID = userTestBase.getExistingSellerEmail();
		userTestBase.unblockUser(existingSellerEmailID);
		userTestBase.login(existingSellerEmailID, "Agam@1");		
	}
	
	@Test(dependsOnMethods="loginVerifiedSeller")
	public void getCategoryDetails() {
		userTestBase.getCategoryDetails(2);
	}
	
	@Test(dependsOnMethods="loginVerifiedSeller")
	public void getProductDetails() {
		userTestBase.getProductDetails(1);
	}
	
	@Test(dependsOnMethods="loginVerifiedSeller")
	public void getNonRealEstateProductDetails() {
		userTestBase.getAllNonRealEstateProduct();
	}
	
	@Test(dependsOnMethods="loginVerifiedSeller")
	public void deleteAProduct() {
		int productId = sellerTestBase.addNewProduct("Phone");
		log.info("product id is: "+productId);
		userTestBase.deleteAProduct(productId);
	}
	
	/*@Test(dependsOnMethods="loginVerifiedSeller")
	public void createNewCompany() {
		sellerTestBase.createNewCompany();
	}*/
	
}
