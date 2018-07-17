package com.kasback.abstest;

import org.testng.Assert;

import com.kasback.abstestbase.TestBase;
import com.kasback.utils.JearseyClient;
import com.kasback.utils.PostgresqlUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import net.minidev.json.JSONObject;

public class UserAPITestBase extends TestBase{
	private PostgresqlUtil pgutil1 = null;
	public ClientConfig config = null;
	public Client client = null;

	public UserAPITestBase() {
		super();
		this.pgutil1 = new PostgresqlUtil(environment.get("dbCon"), environment.get("dbuser"),
				environment.get("dbpwd"));
		if (this.pgutil1 == null) {
			log.info("DB Connection failed");
			this.pgutil1 = pgutilg;
		}
		if (this.pgutil1 == null) {
			Assert.fail("database connection not successful");
		}
		config = new DefaultClientConfig();
		client = Client.create(config);
	}

	public UserAPITestBase(PostgresqlUtil pgutil) {
		super(pgutil);
		this.pgutil1 = pgutil;
		config = new DefaultClientConfig();
		client = Client.create(config);
	}
	
	public void createUser(String username, String type) {
		JSONObject addUserReq = new JSONObject();
		addUserReq.put("email", username);
		addUserReq.put("f_name", "apiFirst");
		addUserReq.put("l_name", "apiLast");
		addUserReq.put("gender", "male");
		addUserReq.put("user_role_id", "1");
		addUserReq.put("password", "Agam@1");
		addUserReq.put("_password", "Agam@1");
		addUserReq.put("phone", "9999999999");
		addUserReq.put("address_line_1", "test");
		addUserReq.put("state", "Karnataka");
		addUserReq.put("city", "Bangalore");
		addUserReq.put("country", "India");
		addUserReq.put("zip_code", "123456");
		addUserReq.put("type", type);
		System.out.println(addUserReq.toString());
		String uri = environment.get("baseurl") + "/user";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.post(uri, addUserReq.toString());
		String id = pgutil.executeSelectQuery("select id from user where email='"+username+"'");
		if(!id.equals(0)) {
		Assert.assertEquals(response.getStatus(), 400, "Existing User created");
		}
	}
	
	public void getCategoryDetails(int category) {
		String uri = environment.get("baseurl") + "/category/"+category;
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.get(uri);
		Assert.assertEquals(response.getStatus(), 200, "Category data not displayed");
	}
	
	public void getProductDetails(int productId) {
		String uri = environment.get("baseurl") + "/non_real_estate/"+productId;
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.get(uri);
		String res = response.getEntity(String.class);
		log.info(res);
		Assert.assertEquals(response.getStatus(), 200, "Product data not displayed");
	}
	
	public void getAllNonRealEstateProduct() {
		String uri = environment.get("baseurl") + "/non_real_estate";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.get(uri);
		Assert.assertEquals(response.getStatus(), 200, "Non Real Estate Product Details no displayed");
	}
	
	public void deleteAProduct(int productId) {
		String uri = environment.get("baseurl") + "/non_real_estate/"+productId;
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.delete(uri, getAuthMap());
		Assert.assertEquals(response.getStatus(), 200, "Product not deleted successfully");
	}
	
	public void forgotPwd(String email) {
		JSONObject forgotPwdReq = new JSONObject();
		forgotPwdReq.put("email", email);
		String uri = environment.get("baseurl")+"/auth/forgotpwd";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.postWithHeaders(uri, forgotPwdReq.toString(), getAuthMap());
		String res = response.getEntity(String.class);
		log.info(res);
	}
	
	public void resetPwd() {
		JSONObject resetPwdReq = new JSONObject();
		resetPwdReq.put("new_password", "Agam@1");
		resetPwdReq.put("confirm_password", "Agam@1");
		String uri = environment.get("baseurl")+"/auth/resetPwd/?token=3QolV5Q8Jobjg1ao";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.postWithHeaders(uri, resetPwdReq.toString(), getAuthMap());
		String res = response.getEntity(String.class);
		log.info(res);
	}
	
	
	public String getExistingSellerEmail() {
		String email = pgutil.executeSelectQuery("select email from user where type = 'seller' order by created desc limit 1");
		return email;
	}
	
	public String getExistingBuyerEmail() {
		String email = pgutil.executeSelectQuery("select email from user where type = 'buyer' order by created desc limit 1");
		return email;
	}
	
	public void blockUser(String email) {
		pgutil.executeUpdateQuery("update user set is_blocked=1 where email='"+email+"'");
	}
	
	public void unblockUser(String email) {
		pgutil.executeUpdateQuery("update user set is_blocked=0 where email='"+email+"'");
	}
	
	public int getProductId(int productId) {
		pgutil.executeSelectQuery("select id from kasback.non_real_estate_product where name");
		return productId;
	}
	
	
}
