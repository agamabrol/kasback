package com.kasback.abstest;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.IExecutionListener;

import com.kasback.abstestbase.AbstractTest;
import com.kasback.abstestbase.FileIOUtility;
import com.kasback.abstestbase.MasterLogger;
import com.kasback.constants.UtilConstants;
import com.kasback.utils.CSVUtils;
import com.kasback.utils.ProcessBuilderUtility;
import com.kasback.utils.PropertyFileUtils;

public class SuiteListener extends AbstractTest implements IExecutionListener {
	Logger log = MasterLogger.getInstance();
	static boolean firsttimeLog = false;
	public String saveServerLogs = "false";
	public String processLogs = "false";
	static ServerLogsProcessor logProcessor = new ServerLogsProcessor();
	String startTime = null;

	private void printBanner(String s) {
		log.info("========================================= " + s + " ================================");

	}

	/*@Override
	public void onExecutionStart() {
		Date date = new Date();
		startTime = TestDataGenerator.getformatedDateinTimeZone(date, "yyyy-MM-dd HH:mm", "GMT");
		log.info("start time for logs ->" + startTime);
		String env = System.getProperty("env");
		envoir = env;
		log.info("before env inside " + env);
		if (saveServerLogs.equalsIgnoreCase("true")) {
			verifyMachinesAreUp();
			log.info("all machines are up for testing");
		}

		if (firsttimeLog == false) {
			String cmd = "sudo rm -rf " + System.getProperty("user.dir") + "/src/test/resources/snapshots/*";
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() == 0) {
				log.info("snapshot contents deleted");
			} else {
				log.info("snapshot folder not deleted");
			}
			log.info("setting up for first time ");
			FileIOUtility.deleteFileIfExists("extentReport/ExtentReportTestNG.html");
			setExtentReport();
			log.info("extent report set");
			printBanner("NETRADYNE AUTOMATION FRAMEWORK LOG STARTS BELOW");

			csv = new CSVUtils("./testcaseList.csv");
			csvtracers = new CSVUtils("./extentReport/tracerIdsList.csv");
			csvknownIssues = new CSVUtils("./extentReport/knownIssues.csv");
			csv.createNewCSV("./testcaseList.csv");
			csvtracers.createNewCSV("./extentReport/tracerIdsList.csv");
			csvtracers.append2CSV("testClass,testCase,tracerIdsList,,,");
			csvknownIssues.append2CSV("ids,url,priority,,");
			csv.append2CSV(
					"TestName,TestGroups,TestCaseName,Description,Parameters,URI,INPUT,OUTPUT,Status,DefectId, DefectUrl,DefectPrty");
			firsttimeLog = true;
			log.info("set first time logging true");
			FileIOUtility.deleteAllFilesNSubfolders("./target/test-classes/api/");
		}
		saveServerLogs = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "saveserverlogs");
		processLogs = PropertyFileUtils.getProperty(UtilConstants.CONFIG_FILE, "processServerLogsAfterRun");
		FileIOUtility.deleteAllFilesNSubfolders("./target/surefire-reports/html");
		FileIOUtility.deleteAllFilesNSubfolders("./target/surefire-reports/xml");
		FileIOUtility.createIfFolderNotExists("./target/surefire-reports/html");
		FileIOUtility.createIfFolderNotExists("./target/surefire-reports/xml");
	}
*/
	@Override
	public void onExecutionFinish() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onExecutionStart() {
		// TODO Auto-generated method stub
		
	}

	/*@Override
	public void onExecutionFinish() {
		extent.flush();
		log.info(saveServerLogs);
		if (saveServerLogs.equalsIgnoreCase("true")) {
			log.info("saving logs at server ...");
			logProcessor.saveServerLogsforTest(startTime);
		}

		if (saveServerLogs.equalsIgnoreCase("true")) {
			log.info("downloading server logs");
			logProcessor.downloadServerLogs();
			log.info("deleting temp server logs");
			logProcessor.deleteTempServerLogs();
		}
		if (processLogs.equalsIgnoreCase("true")) {
			log.info("processing log files");
			logProcessor.buildReport();
			log.info(
					"Report generated for server Exceptions during test run:  ServerLogsExceptionReport.log verify serverlogs folder");
		}
		if (FileIOUtility.isFileExists("./testcaseList.csv") && (saveAtS3.equals("true"))) {
			try {
				FileIOUtility.deleteFileIfExists("./testExecutionSummary.html");
				ProcessBuilderUtility.executeComand("python testCaesReportGenerator.py");
			} catch (Exception e) {
				log.info(e.getMessage());
			}
		}
		FileIOUtility.createIfFolderNotExists("./target/surefire-reports/html");
		FileIOUtility.createIfFolderNotExists("./target/surefire-reports/xml");
		pgutil.closeDBConnection();
		FileIOUtility.deleteAllFilesNSubfolders("./target/test-classes/api");
		log.info("current dir---"+System.getProperty("user.dir"));
		FileIOUtility.deleteAllFilesNSubfolders("./target/test-classes/api/");
		FileIOUtility.deleteAllFilesMatchingPattern(ApiConstants.VIDEOS_PATH,"_y.mp4");
		for (String s : knownIssues) {
			csvknownIssues.append2CSV(s);
		}
		printBanner("TEST SUIT(s) FINISHED");
	}*/

	/*public void verifyMachinesAreUp() {
		Assert.assertEquals(NetworkUtils.verifyMachineReachable(ServerConstants.APP_SERVER1), true,
				"App Server 1 is not reachable");
		log.info("is app server 2 reachable: " + NetworkUtils.verifyMachineReachable(ServerConstants.APP_SERVER2));
		Assert.assertEquals(NetworkUtils.verifyMachineReachable(ServerConstants.TASK_PROCESSOR_SERVER), true,
				"task processor is not reachable");
		Assert.assertEquals(NetworkUtils.verifyMachineReachable(ServerConstants.SPA_SERVER), true,
				"SPA is not reachable");
		Assert.assertEquals(NetworkUtils.verifyMachineReachable(ServerConstants.ANNOTATION_SERVER), true,
				"annotation is not reachable");
		Assert.assertEquals(NetworkUtils.verifyMachineReachable(ServerConstants.INWARD_ANNOTATION_SERVER), true,
				"Inward annotation is not reachable");
		Assert.assertEquals(NetworkUtils.verifyMachineReachable(ServerConstants.IMG_SERVER), true,
				"cropping machine is not reachable");
	}*/

}
