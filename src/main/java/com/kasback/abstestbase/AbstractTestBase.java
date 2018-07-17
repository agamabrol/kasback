package com.kasback.abstestbase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.kasback.interfaces.ExpectedDBConditions;
import com.kasback.utils.CSVUtils;
import com.kasback.waits.WaitUntilCondition;
import com.kasback.utils.PostgresqlUtil;

public abstract class AbstractTestBase {
	public static HashMap<String, String> environment = new HashMap<String, String>();
	public static Logger log = MasterLogger.getInstance();
	public static MyAttributes attributes = new MyAttributes();
	protected static String os = System.getProperty("os.name");
	public static String snapshotfolder = "";
	public static PostgresqlUtil pgutilg = null;
	public static CSVUtils csv = null;
	public static CSVUtils csvtracers = null;
	public static CSVUtils csvknownIssues = null;
	public static HashSet<String> knownIssues =new HashSet<String>();




	
	public static void loadEnv(String env) {
		getenv("environment.xml", "environment", "id", env);
	}
	
	public static void getenv(String fileName, String tagName, String attribuateName, String attribuateValue) {
		NodeList nList = null;
		Node nNode = null;

		File inputFile = new File(fileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(inputFile);
			XPath xPath = XPathFactory.newInstance().newXPath();

			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName(tagName);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int temp = 0; temp < nList.getLength(); temp++) {
			nNode = nList.item(temp);
			String key = nNode.getAttributes().getNamedItem("id").getNodeValue();
			if (key.equals(attribuateValue)) {
				NodeList childnodes = nNode.getChildNodes();
				for (int j = 0; j < childnodes.getLength(); j++) {
					Node n = childnodes.item(j);
					environment.put(n.getNodeName().trim(), n.getTextContent().trim());
				}
			}
		}
	}

	
	public void reportLog(String label, String inputValue) {
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"green\"><b>"
				+ label + ": " + inputValue + "</b></font></td>");
		if (label.equalsIgnoreCase("URI")) {
			attributes.setAttribute("URI", inputValue);
		}
		log.info(label + ": " + inputValue);
	}

	public void reportFailure(String inputValue) {
		log.info("test failed");
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"red\"><b>"
				+ inputValue + "</b></font></td>");
	}

	public void reportLog(String str) {
		log.info(str);
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"green\"><b>"
				+ str + "</b></font></td>");
	}

	public void imbedInputFileinReport(File file) {
		String filename = file.getAbsoluteFile() + "";
		log.info("input file : " + filename);
		Reporter.log("<tr><td></td><td></td><td><font size=\"1\" face=\"Comic sans MS\" color=\"blue\"><a href="
				+ filename + "><p style='color:blue;'><strong>Input file/json (size: " + file.length()
				+ " bytes)</strong></p> </a></font></td>");
		attributes.setAttribute("INPUT", getPublicUrl(filename));
	}

	/*public void imbedResponseinReport(File file) {
		String filename = System.getProperty("user.dir") + file.getPath();
		log.info("output file : " + filename);
		attributes.setAttribute("OUTPUT", getPublicUrl(filename));
		if (saveAtS3.equals("true")) {
			filename = S3endpointUrl + snapshotfolder + "/" + file.getName();
		}
		Reporter.log("<tr><td></td><td></td><td><font size=\"1\" face=\"Comic sans MS\" color=\"blue\"><a href="
				+ filename + "><p style='color:blue;'><strong>Response file/json (size: " + file.length()
				+ " bytes)</strong></p> </a></font></td>");
	}*/

	public void imbedUrl(String legend, String url) {
		Reporter.log("<tr><td></td><td></td><td><font size=\"1\" face=\"Comic sans MS\" color=\"blue\"><a href="
				+ legend + "><p style='color:blue;'><strong> " + url + " </strong></p> </a></font></td>");
	}

	public static void reportSuccess(String str) {
		log.info("test case passed");
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"green\"><b>"
				+ str + "</b></font></td>");
	}

	public static void reportFail(String str) {
		log.info("test case failed");
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"red\"><b>"
				+ str + "</b></font></td>");
	}

	public static void reporError(String str) {
		log.info("error reported " + str);
		Reporter.log("<tr><td></td><td></td><td></td><td><font size=\"2\" face=\"Comic sans MS\" color=\"red\"><b>"
				+ str + "</b></font></td>");
	}

	public String getPublicUrl(String fileName) {
		String s = fileName;
		if (s.contains("Cloud-Automation")) {
			return "https://build.netradyne.info/job/Cloud-Automation/ws" + s.split("Cloud-Automation")[1];
		} else
			return "";

	}
	
	
	public void waitUntilDBCondition(int start, int maxTime, int polling, ExpectedDBConditions condition,
			String query) {
		/*
		 * for (int i = start; (i < maxTime &&
		 * condition.getDBConditions(query)); i = i + polling) { try {
		 * Thread.sleep(polling); log.info("polling done to get db conditions "
		 * + i); } catch (InterruptedException e) {
		 * log.info("Thread.sleep interupted"); } }
		 */

		WaitUntilCondition wait = new WaitUntilCondition();
		wait.setMaxTime(maxTime);
		wait.setPolling(polling);
		wait.setStart(start);
		wait.waitUntilCondition(condition, query);
	}
}
