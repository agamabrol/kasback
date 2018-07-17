package com.kasback.abstest;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.kasback.abstestbase.MasterLogger;

public class ServerLogsProcessor {
	public static Logger log = MasterLogger.getInstance();
	Set<String> uniqueTrnNo = new HashSet<String>();
	//String trnPattern = "\\[[A-Za-z0-9_@#$%^&!]{24}\\]";
	String trnPattern="\\[[A-Za-z0-9_@#$%^&!-]{24,30}\\]";

	/*public void saveServerLogsforTest(String startTimestamp) {
		Date date = new Date();
		String endtime = TestDataGenerator.getformatedDateinTimeZone(date, "yyyy-MM-dd HH:mm", "GMT");
		log.info("end time for logs -> " + endtime);
		if (AbstractTest.envoir.equals("qa") || AbstractTest.envoir.equals("test")
				|| AbstractTest.envoir.equals("stage")) {
			String cmd = "sed -n '/" + startTimestamp + "/ , /" + endtime
					+ "/p' /data/idms/logs/restserver.log > /tmp/testAppAutomation0.log ";
			if (SshUtils.runcommand(ServerConstants.APP_SERVER1, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
					cmd) != 0) {
				log.info("App server log generation for automation test duration has been failed.");
			}
			if (ServerConstants.APP_SERVER2 != null) {
				cmd = "sed -n '/" + startTimestamp + "/ , /" + endtime
						+ "/p' /data/idms/logs/restserver.log > /tmp/testAppAutomation1.log ";
				if (SshUtils.runcommand(ServerConstants.APP_SERVER2, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
						cmd) != 0) {
					log.info("App server log generation for automation test duration has been failed.");
				}
			}
			cmd = "sed -n '/" + startTimestamp + "/ , /" + endtime
					+ "/p' /data/idms/logs/task-processor.log > /tmp/testTPAutomation.log ";
			if (SshUtils.runcommand(ServerConstants.TASK_PROCESSOR_SERVER, ServerConstants.USER,
					ServerConstants.PRIVATE_KEY, cmd) != 0) {
				log.info("task processor server log generation for automation test duration has been failed.");
			}

			cmd = "sed -n '/" + startTimestamp + "/ , /" + endtime
					+ "/p' /var/log/idms-annotation.log > /tmp/testAnnotationAutomation.log ";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.ANNOTATION_SERVER, ServerConstants.USER,
					ServerConstants.PRIVATE_KEY, cmd) != 0) {
				log.info("Annotation server log generation for automation test duration has been failed.");
			}

			cmd = "sed -n '/" + startTimestamp + "/ , /" + endtime
					+ "/p' /var/log/idms-annotation.log > /tmp/testImgServerAutomation.log ";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.IMG_SERVER, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
					cmd) != 0) {
				log.info("img server log generation for automation test duration has been failed.");
			}
			cmd = "sed -n '/" + startTimestamp + "/ , /" + endtime
					+ "/p' /var/log/idms-annotation.log > /tmp/testSpaServerAutomation.log ";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.SPA_SERVER, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
					cmd) != 0) {
				log.info("spa server log generation for automation test duration has been failed.");
			}

			cmd = "sed -n '/" + startTimestamp + "/ , /" + endtime
					+ "/p' /var/log/idms-annotation.log > /tmp/testInwardServerAutomation.log ";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.INWARD_ANNOTATION_SERVER, ServerConstants.USER,
					ServerConstants.PRIVATE_KEY, cmd) != 0) {
				log.info("inward annotation server log generation for automation test duration has been failed.");
			}
		} else {
			log.info("environment is not supported " + AbstractTest.envoir);
		}
	}*/

	/*public void downloadServerLogs() {
		if (AbstractTest.envoir.equals("qa") || AbstractTest.envoir.equals("test")
				|| AbstractTest.envoir.equals("stage")) {
			String cmd = "scp -o StrictHostKeyChecking=no ubuntu@" + ServerConstants.APP_SERVER1
					+ ":/tmp/testAppAutomation0.log ./serverlogs/restserver0.log";
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("App server download logs for automation test has been failed.");
			}
			if (ServerConstants.APP_SERVER2 != null) {
				cmd = "scp -o StrictHostKeyChecking=no ubuntu@" + ServerConstants.APP_SERVER2
						+ ":/tmp/testAppAutomation1.log ./serverlogs/restserver1.log";
				log.info(cmd);
				if (SshUtils.runcommand(ServerConstants.APP_SERVER2, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
						cmd) != 0) {
					log.info("App server log generation for automation test duration has been failed.");
				}
				log.info("merging app0 and app1 logs");
				FileIOUtility.mergeFiles("./serverlogs/restserver0.log", "./serverlogs/restserver1.log",
						"./serverlogs/restserver.log");
			} else {
				FileIOUtility.deleteFileIfExists("./serverlogs/restserver.log");
				FileIOUtility.renameFile("./serverlogs/restserver0.log", "./serverlogs/restserver.log");
			}
			cmd = "scp -o StrictHostKeyChecking=no ubuntu@" + ServerConstants.TASK_PROCESSOR_SERVER
					+ ":/tmp/testTPAutomation.log ./serverlogs/task-processor.log";
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("task processor server log generation for automation test duration has been failed.");
			}

			cmd = "scp -o StrictHostKeyChecking=no ubuntu@" + ServerConstants.ANNOTATION_SERVER
					+ ":/tmp/testAnnotationAutomation.log ./serverlogs/annotation.log";
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("Annotation server log generation for automation test duration has been failed.");
			}
			cmd = "scp -o StrictHostKeyChecking=no ubuntu@" + ServerConstants.IMG_SERVER
					+ ":/tmp/testImgServerAutomation.log ./serverlogs/imgserver.log";
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("img server log generation for automation test duration has been failed.");
			}

			cmd = "scp -o StrictHostKeyChecking=no ubuntu@" + ServerConstants.SPA_SERVER
					+ ":/tmp/testSpaServerAutomation.log ./serverlogs/spaannotation.log";
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("Spa server log generation for automation test duration has been failed.");
			}

			cmd = "scp -o StrictHostKeyChecking=no ubuntu@" + ServerConstants.INWARD_ANNOTATION_SERVER
					+ ":/tmp/testInwardServerAutomation.log ./serverlogs/inwardannotation.log";
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("Inward Annotation server log generation for automation test duration has been failed.");
			}
		} else {
			log.info("environment is not supported " + AbstractTest.envoir);
		}
	}

	public void deleteTempServerLogs() {
		if (AbstractTest.envoir.equals("qa") || AbstractTest.envoir.equals("test")
				|| AbstractTest.envoir.equals("stage")) {
			String cmd = "rm /tmp/testAppAutomation0.log";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.APP_SERVER1, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
					cmd) != 0) {
				log.info("App server test automation log delete failed.");
			}
			if (ServerConstants.APP_SERVER2 != null) {
				cmd = "rm /tmp/testAppAutomation1.log";
				log.info(cmd);
				if (SshUtils.runcommand(ServerConstants.APP_SERVER2, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
						cmd) != 0) {
					log.info("App server test automation log delete failed.");
				}
			}
			cmd = "sudo rm /tmp/testTPAutomation.log";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.TASK_PROCESSOR_SERVER, ServerConstants.USER,
					ServerConstants.PRIVATE_KEY, cmd) != 0) {
				log.info("task processor test automation log delete failed.");
			}

			cmd = "sudo rm /tmp/testAnnotationAutomation.log";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.ANNOTATION_SERVER, ServerConstants.USER,
					ServerConstants.PRIVATE_KEY, cmd) != 0) {
				log.info("Annotation server test automation log delete failed.");
			}

			cmd = "sudo rm /tmp/testImgServerAutomation.log";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.IMG_SERVER, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
					cmd) != 0) {
				log.info("Img server test automation log delete failed.");
			}

			cmd = "sudo rm /tmp/testSpaServerAutomation.log";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.SPA_SERVER, ServerConstants.USER, ServerConstants.PRIVATE_KEY,
					cmd) != 0) {
				log.info("SPA server test automation log delete failed.");
			}

			cmd = "sudo rm /tmp/testInwardServerAutomation.log";
			log.info(cmd);
			if (SshUtils.runcommand(ServerConstants.INWARD_ANNOTATION_SERVER, ServerConstants.USER,
					ServerConstants.PRIVATE_KEY, cmd) != 0) {
				log.info("inward annotation server test automation log delete failed.");
			}
		} else {
			log.info("environment is not supported " + AbstractTest.envoir);
		}
	}

	// Following method should run only on Unix/Linux supported platforms
	public void processLogs() {
		// Grep Exception from restserverlogs
		String cmd = "sh grep.sh";
		log.info(cmd);
		try {
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("Grep Exception logs failed.");
			}
			if (FileIOUtility.isFileExists("./serverlogs/appExceptions.log")) {
				extractFailedTrnNoFromLogs("./serverlogs/appExceptions.log");
			}
			if (FileIOUtility.isFileExists("./serverlogs/tpExceptions.log")) {
				extractFailedTrnNoFromLogs("./serverlogs/tpExceptions.log");
			}
			if (FileIOUtility.isFileExists("./serverlogs/annotationExceptions.log")) {
				extractFailedTrnNoFromLogs("./serverlogs/annotationExceptions.log");
			}
			if (FileIOUtility.isFileExists("./serverlogs/imgserver.log")) {
				extractFailedTrnNoFromLogs("./serverlogs/imgserver.log");
			}

			if (FileIOUtility.isFileExists("./serverlogs/spaannotation.log")) {
				extractFailedTrnNoFromLogs("./serverlogs/spaannotation.log");
			}

			if (FileIOUtility.isFileExists("./serverlogs/inwardannotation.log")) {
				extractFailedTrnNoFromLogs("./serverlogs/inwardannotation.log");
			}
		} catch (Exception e) {
			log.info("Exception occurred while extracting failure logs. \n" + e.getMessage());
		}

		log.info(uniqueTrnNo);
	}

	public void extractFailedTrnNoFromLogs(String fileName) {
		log.info("processing file " + fileName);
		try {

			File f = new File(fileName);
			Matcher m = null;

			BufferedReader b = new BufferedReader(new FileReader(f));

			String readLine = "";

			log.info("Reading file using Buffered Reader");

			while ((readLine = b.readLine()) != null) {
				log.info(readLine);
				m = Pattern.compile(trnPattern).matcher(readLine);
				if (m.find()) {
					uniqueTrnNo.add(m.group(0));
				}
			}
			
			FileWriter writer = new FileWriter("./serverlogs/failedTrns.txt"); 
			for(String str: uniqueTrnNo) {
			  writer.write(str);
			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
			log.info(e.getMessage());
		}

	}*/

	/*public void buildReport() {
		processLogs();
		String trnNo = null;
		String cmd = null;
		FileIOUtility.deleteFileIfExists("./serverlogs/ServerLogsExceptionReport.log");
		try {
			FileIOUtility.createEmptyFile("./serverlogs/ServerLogsExceptionReport.log");
		} catch (IOException e) {
			log.info("issues while log creating file");
		}
		Iterator<String> i = uniqueTrnNo.iterator();
		while (i.hasNext()) {
			trnNo = i.next().replaceAll("\\[", "").replaceAll("\\]", "");
			cmd = "python " + ApiConstants.SCRIPTS_PATH
					+ "generateExceptionReport.py -i ./serverlogs/restserver.log -t " + trnNo;
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("failed for trn " + trnNo);
			}
			cmd = "python " + ApiConstants.SCRIPTS_PATH
					+ "generateExceptionReport.py -i ./serverlogs/task-processor.log -t " + trnNo;
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("failed for trn " + trnNo);
			}

			cmd = "python " + ApiConstants.SCRIPTS_PATH
					+ "generateExceptionReport.py -i ./serverlogs/annotation.log -t " + trnNo;
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("failed for trn " + trnNo);
			}

			cmd = "python " + ApiConstants.SCRIPTS_PATH + "generateExceptionReport.py -i ./serverlogs/imgserver.log -t "
					+ trnNo;
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("failed for trn " + trnNo);
			}

			cmd = "python " + ApiConstants.SCRIPTS_PATH
					+ "generateExceptionReport.py -i ./serverlogs/spaannotation.log -t " + trnNo;
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("failed for trn " + trnNo);
			}

			cmd = "python " + ApiConstants.SCRIPTS_PATH
					+ "generateExceptionReport.py -i ./serverlogs/inwardannotation.log -t " + trnNo;
			log.info(cmd);
			if (ProcessBuilderUtility.executeCommandTimeout5Minutes(cmd).exitValue() != 0) {
				log.info("failed for trn " + trnNo);
			}
		}
	}*/

}
