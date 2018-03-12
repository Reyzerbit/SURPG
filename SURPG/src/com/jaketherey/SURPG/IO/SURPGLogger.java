/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.IO;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.Misc.Log_Format;

public class SURPGLogger {
	
	//Logger (pretty self explanatory)
	public static final Logger logger = Logger.getLogger("MainLog");
	
	/*
	 * fileHandler1 deals with the log file output to LATEST_LOG.log
	 * fileHandler2 deals with the log file that is generated with
	 * the time and date, in order to keep multiple log files.
	 */
	private static FileHandler fileHandler1;
	private static FileHandler fileHandler2;
	
	public static void initLogger() {
		
		DateFormat dateFormat = new SimpleDateFormat("MM:dd:yyyy_hh.mm.ss");
		Date date = new Date();
		String currentLogString = dateFormat.format(date);
		
		File currentLog = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
			 + System.getProperty("file.separator") + "SURPG" + System.getProperty("file.separator") + "Logs" + 
				System.getProperty("file.separator") + currentLogString + ".log");
		
		if(!Core.LATEST_LOG_FILE.exists()) {
			
			Core.LATEST_LOG_FILE.getParentFile().mkdirs();
			try {
				Core.LATEST_LOG_FILE.createNewFile();
			} catch (IOException e) {
				runError(e);
				e.printStackTrace();
			}
			
		}
		
		if(!currentLog.exists()) {
			
			currentLog.getParentFile().mkdirs();
			try {
				currentLog.createNewFile();
			} catch (IOException e) {
				runError(e);
				e.printStackTrace();
			}
			
		}
		
		try {
			fileHandler1 = new FileHandler(Core.LATEST_LOG_FILE.getPath(), false);
			fileHandler2 = new FileHandler(currentLog.getPath(), false);
		} catch (SecurityException | IOException e) {
			runError(e);
			e.printStackTrace();
		}
		
		ConsoleHandler consoleHandler = new ConsoleHandler();
		
		consoleHandler.setFormatter(new Log_Format());
		fileHandler1.setFormatter(new Log_Format());
		fileHandler2.setFormatter(new Log_Format());
		
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
		logger.addHandler(fileHandler1);
		logger.addHandler(fileHandler2);
		logger.addHandler(consoleHandler);
		
	}
	
	public static void runError(Exception e) {
		
		logger.log(Level.SEVERE, "Error! Please send the generated log file to Jake the Rey!\n");
		logger.log(Level.SEVERE, e.toString(), e);
		e.printStackTrace();
		
	}
	
	public static void runError(String e) {
		
		logger.log(Level.SEVERE, e);
		
	}

}
