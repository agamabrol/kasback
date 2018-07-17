package com.kasback.abstestbase;

import org.apache.log4j.Logger;

import com.kasback.abstestbase.MasterLogger;

public class InvalidLocator extends Exception {

	/**
	 *
	 */
	public static Logger log = MasterLogger.getInstance();
	private static final long serialVersionUID = 3990909229327303440L;

	public InvalidLocator(String locator, String message) {
		super(message);
		log.info(message + "\n" + message);
	}

	public InvalidLocator(String message) {
		super(message);
		log.info(message);
	}

}
