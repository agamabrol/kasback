package com.kasback.utils;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.kasback.abstestbase.MasterLogger;

public class TestDataGenerator {
	public static Logger log = MasterLogger.getInstance();


	public static String createText() {
		String uuid = UUID.randomUUID().toString();
		return uuid.toString();
	}
}
