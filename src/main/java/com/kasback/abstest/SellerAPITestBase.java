package com.kasback.abstest;

import java.util.Random;

import com.kasback.abstestbase.TestBase;
import com.kasback.utils.JearseyClient;
import com.sun.jersey.api.client.ClientResponse;

import net.minidev.json.JSONObject;

public class SellerAPITestBase extends TestBase{
	static Random rad = new Random();
	UserAPITestBase uiTestBase = null;
	public static String username;

	
	 public static String randomEmail() {
		String email = null;
		for (int j=1; j<=1; j++ )
       {
			email="usename"+rad.nextInt(10000)+"@gmail.com";
       System.out.print(email);  
       }
       return email;
   }
	
	public String createNewSeller() {		
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
		addUserReq.put("type", "seller");
		System.out.println(addUserReq.toString());
		String uri = environment.get("baseurl") + "/user";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.post(uri, addUserReq.toString());
		if(response.getStatus() != 200) {
			log.info("create user is not successful " + username);
		}
		return username;
		
	}
	
	public void createNewCompany() {
		JSONObject createNewCompany = new JSONObject();
		createNewCompany.put("name", "test company");
		createNewCompany.put("tin_no", "qwerty");
		createNewCompany.put("cin_no", "qwertyu");
		System.out.println(createNewCompany.toString());
		String uri = environment.get("baseurl") + "/company";
		reportLog("URI", uri);
		ClientResponse response = JearseyClient.postWithHeaders(uri, createNewCompany.toString(), getAuthMap());
		String res = response.getEntity(String.class);
		log.info(res);
		if(response.getStatus() != 200) {
			log.info("create company is not successful");
		}
	}
	

	
	

}
