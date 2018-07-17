package com.kasback.abstest;

import java.util.Random;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import org.testng.Assert;

import com.kasback.abstestbase.TestBase;
import com.kasback.abstestbase.UITestBase;
import com.kasback.utils.JearseyClient;
import com.kasback.utils.JsonUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class BuyerApiTestbase extends TestBase {
	static Random rad = new Random();
	UITestBase uiTestBase = new UITestBase();
	UserAPITestBase userTestBase = new UserAPITestBase();
	public static String username;

	public BuyerApiTestbase() {
		super();		
		config = new DefaultClientConfig();
		client = Client.create(config);
	}

	public static String randomEmail() {
		String email = null;
		for (int j = 1; j <= 1; j++) {
			email = "usename" + rad.nextInt(10000) + "@gmail.com";
			System.out.print(email);
		}
		return email;
	}

	public String createNewBuyer() {
		JSONObject addUserReq = new JSONObject();
		username = randomEmail();
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
		addUserReq.put("type", "buyer");
		System.out.println(addUserReq.toString());
		String uri = environment.get("baseurl") + "/user";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.post(uri, addUserReq.toString());
		if (response.getStatus() != 200) {
			log.info("create user is not successful " + username);
		}
		return username;
	}

	public void getAllFavourites() {
		String uri = environment.get("baseurl") + "/favourite";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.getWithHeaders(uri, getAuthMap());
		Assert.assertEquals(response.getStatus(), 200, "User not authorized");
		/*
		 * if(response.getStatus() == 200) { log.info("Favourites Dsiplayed"); }
		 */
	}

	public void getSingleFavourite() {
		String uri = environment.get("baseurl") + "/favourite/1";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.getWithHeaders(uri, getAuthMap());
		Assert.assertEquals(response.getStatus(), 200, "User not authorized");
		/*
		 * if(response.getStatus() == 200) { log.info("Favourite Dsiplayed"); }
		 */
	}

	public void addProductToCart(int productId) {
		JSONObject addProdToCartReq = new JSONObject();
		addProdToCartReq.put("product_id", productId);
		String uri = environment.get("baseurl") + "/cart";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.postWithHeaders(uri, addProdToCartReq.toString(), getAuthMap());
		String res = response.getEntity(String.class);
		log.info(res);
		Assert.assertEquals(response.getStatus(), 200);
	}

	/*
	 * public void removeProductFromCart(int productId) { String uri =
	 * environment.get("baseurl") + "/cart/"+productId; reportLog("URI", uri);
	 * ClientResponse response = JearseyClient.delete(uri, authMap); String res =
	 * response.getEntity(String.class); log.info(res);
	 * Assert.assertEquals(response.getStatus(), 200); }
	 */

	public void filterProductBasedOnPrice() {
		String uri = environment.get("baseurl") + "/non_real_estate/priceFilter/5/1/";
		MultivaluedMap<String, String> m = new MultivaluedHashMap<>();
		m.add("greater", "100");
		m.add("lesser", "9000000");
		ClientResponse response = JearseyClient.getUsingQueryParm(uri, m);
		log.info(response);
		String res = response.getEntity(String.class);
		log.info(res);
		Assert.assertEquals(response.getStatus(), 200);
	}

	public void getCart() {
		String uri = environment.get("baseurl") + "/cart";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.getWithHeaders(uri, getAuthMap());
		String res = response.getEntity(String.class);
		String cartData = "$['cart']";
		JSONArray cart = JsonUtils.getJsonArrayfromJsonPath(res, cartData);
		log.info(cart);
		Assert.assertEquals(response.getStatus(), 200);
	}

	public void checkOut(int productId) {
		JSONObject checkoutReq = new JSONObject();
		int[] s = new int[0];
		checkoutReq.put("selection", s);
		checkoutReq.put("totalPrice", getPriceOfProduct(productId));
		checkoutReq.put("totalPoints", null);
		log.info(checkoutReq);
		String uri = environment.get("baseurl") + "/order/checkout/";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.postWithHeaders(uri, checkoutReq.toString(), getAuthMap());
		String res = response.getEntity(String.class);
		log.info(res);
		Assert.assertEquals(response.getStatus(), 200, "Checkout failed");
	}
	
	
	public void makePayment(int productId) {
		JSONObject paymentReq = new JSONObject();
		int[] s = new int[0];
		paymentReq.put("Address_id", s);
		paymentReq.put("amound", getPriceOfProduct(productId));
		paymentReq.put("pay_id", null);
		paymentReq.put("type", "buyProducts");
		log.info(paymentReq);
		String uri = environment.get("baseurl") + "/razorPay";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.postWithHeaders(uri, paymentReq.toString(), getAuthMap());
		String res = response.getEntity(String.class);
		log.info(res);
		Assert.assertEquals(response.getStatus(), 200, "Checkout failed");
	}

	public String getPriceOfProduct(int productId) {
		String price = pgutil
				.executeSelectQuery("select price from kasback.non_real_estate_product where id=" + productId);
		return price;
	}

	
	public void buyProductWorkflow() {
		int productId = getProductId();
		String newBuyerEmail = createNewBuyer();
		uiTestBase.updateEmailVerificationStatus(newBuyerEmail);
		login(newBuyerEmail, "Agam@1");
		//addNewProduct("phone");
		userTestBase.getProductDetails(productId);
		addProductToCart(productId);
		getCart();
		checkOut(productId);
		//makePayment(productId);
	}

}
