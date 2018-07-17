package com.kasback.constants;

import com.kasback.abstestbase.AbstractTest;

public class ServerConstants {

	public static final String ENV = AbstractTest.envoir;
	public static final String APP_SERVER1;
	public static final String APP_SERVER2;
	public static final String TASK_PROCESSOR_SERVER;
	public static final String ANNOTATION_SERVER;
	public static final String IMG_SERVER;
	public static final String SPA_SERVER;
	public static final String INWARD_ANNOTATION_SERVER;
	public static final String PRIVATE_KEY = "~/.ssh/id_rsa";
	public static final String USER = "ubuntu";

	static {
		switch (ENV) {
		case "stage":
			APP_SERVER1 = "10.200.0.203";
			APP_SERVER2 = "10.200.0.198";
			TASK_PROCESSOR_SERVER = "10.200.0.49";
			ANNOTATION_SERVER = "10.200.0.58";
			IMG_SERVER="10.200.0.97";
			SPA_SERVER="10.200.0.188";
			INWARD_ANNOTATION_SERVER="10.200.0.135";
			break;
		case "test":
			APP_SERVER1 = "10.210.0.93";
			APP_SERVER2 = null;
			TASK_PROCESSOR_SERVER = "10.210.0.104";
			ANNOTATION_SERVER = "10.210.0.56";
			IMG_SERVER="10.210.0.176";
			SPA_SERVER="10.210.0.108";
			INWARD_ANNOTATION_SERVER="10.210.0.49";
			break;
		case "qa":
			APP_SERVER1 = "10.230.0.151";
			APP_SERVER2 = null;
			TASK_PROCESSOR_SERVER = "10.230.0.122";
			ANNOTATION_SERVER = "10.230.0.21";
			IMG_SERVER="10.230.0.37";
			SPA_SERVER="10.230.0.134";
			INWARD_ANNOTATION_SERVER="10.230.0.58";
			break;
		default:
			APP_SERVER1 = "10.210.0.32";
			APP_SERVER2 = null;
			TASK_PROCESSOR_SERVER = "10.210.0.186";
			ANNOTATION_SERVER = "10.210.0.124";
			IMG_SERVER="10.210.0.71";
			SPA_SERVER="10.210.0.108";
			INWARD_ANNOTATION_SERVER="10.210.0.27";
			break;
		}
	}

}
