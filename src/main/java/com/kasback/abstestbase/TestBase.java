package com.kasback.abstestbase;

import com.kasback.utils.PostgresqlUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public abstract class TestBase extends AbstractTest{
	public ClientConfig config = new DefaultClientConfig();
	public Client client = Client.create(config);

	PostgresqlUtil pgutil0 = null;
	
	public TestBase() {
		this.pgutil0 = pgutilg;
	}
	
	public TestBase(PostgresqlUtil putil) {
		if (putil == null) {
			log.info("DB Connection failed");
			this.pgutil0 = pgutilg;
		} else {
			this.pgutil0 = putil;
		}
	}
}
