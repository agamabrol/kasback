package com.kasback.utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.log4j.Logger;

import com.kasback.abstestbase.AbstractTestBase;
import com.kasback.abstestbase.MasterLogger;

/**
  Under development
 *
 */
public class ProcessBuilderUtility extends AbstractTestBase {
	protected ProcessBuilder pbuilder = new ProcessBuilder();
	public static Logger log = MasterLogger.getInstance();

	public static int executeComand(String cmd) {
		log.info("executing command "+cmd);
		CommandLine cmdLine = CommandLine.parse(cmd);
		DefaultExecutor executor = new DefaultExecutor();
		int exitValue=-1;
		try {
			exitValue = executor.execute(cmdLine);
		} catch (ExecuteException e1) {
			log.info(e1.getMessage());
		} catch (IOException e1) {
			log.info(e1.getMessage());
		}
		return exitValue;
	}
	
	public static Process executeCommandTimeout5Minutes(String cmd) {
		Process p = null;	
		if (System.getProperty("os.name").startsWith("Windows")) {
			cmd = "cmd /c " + cmd;
		}

		try {
			log.info("running cmd "+cmd);
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor(360, TimeUnit.SECONDS);
		} catch (IOException | InterruptedException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return p;
	}

	
	public static Process executeComandNoWait(String cmd) {
		Process p = null;
		if (System.getProperty("os.name").startsWith("Windows")) {
			cmd = "cmd /c " + cmd;
		}

		try {
			p = Runtime.getRuntime().exec(cmd);
		} catch (IOException e) {
			log.info(e.getMessage());
			e.printStackTrace();
		}
		return p;
	}

	public static Process executeComand(String[] cmd) {
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			log.info(e.getMessage());
		}
		return p;
	}

	public int executeProcess(String cmd) {
		pbuilder.command(cmd);
		Process process = null;
		try {
			process = pbuilder.start();
			int errcode = process.waitFor();
		} catch (InterruptedException | IOException e) {
			log.info("error while executing the process " + e.getMessage());
		}
		return process.exitValue();
	}

}
