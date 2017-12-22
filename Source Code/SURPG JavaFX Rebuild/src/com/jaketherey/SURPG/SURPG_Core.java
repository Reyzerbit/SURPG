package com.jaketherey.SURPG;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jaketherey.SURPG.GUI.GUI_Main;
import com.jaketherey.SURPG.Game_Objects.Player;
import com.jaketherey.SURPG.Misc.Log_Format;

public class SURPG_Core {
	
	public static final String SURPG_LOCATION = System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
			 + System.getProperty("file.separator") + "SURPG";
	public static final String SAVES_LOCATION = System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
			 + System.getProperty("file.separator") + "SURPG" + System.getProperty("file.separator") + "Saves";
	public static final File MAIN_SAVES = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
			 + System.getProperty("file.separator") + "SURPG" + System.getProperty("file.separator") + "Saves"
			 + System.getProperty("file.separator") + "MAIN_SAVE.surpg");
	public static final String LOG_LOCATION = (System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
			 + System.getProperty("file.separator") + "SURPG" + System.getProperty("file.separator") + "Logs");
	public static final File LATEST_LOG = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
			 + System.getProperty("file.separator") + "SURPG" + System.getProperty("file.separator") + "Logs"
			 + System.getProperty("file.separator") + "LATEST_LOG.log");
	
	public static Player CURRENT_PLAYER;
	public static Player[] LOADED_SAVES;
	public static GUI_Main MAIN_GUI;
	
	public static final Logger logger = Logger.getLogger("MainLog");
	private static FileHandler fileHandler1;
	private static FileHandler fileHandler2;
	
	public static Player genBlankPlayer() {
		
		final int[] blankArray = new int[15];
		
		blankArray[0] = 0;
		blankArray[1] = 0;
		blankArray[2] = 0;
		blankArray[3] = 0;
		blankArray[4] = 0;
		blankArray[5] = 0;
		blankArray[6] = 0;
		blankArray[7] = 0;
		blankArray[8] = 0;
		blankArray[9] = 0;
		blankArray[10] = 0;
		blankArray[11] = 0;
		blankArray[12] = 0;
		blankArray[13] = 0;
		blankArray[14] = 0;
		
		return new Player("IGNORE", "", "", blankArray);
		
	}
	
	public static void genBlankSaves() throws IOException {
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		
		Player[] ignoreArray = new Player[]{genBlankPlayer()};
		
		File blankFile = SURPG_Core.MAIN_SAVES;
		blankFile.createNewFile();
		
		fos = new FileOutputStream(blankFile);
		
		oos = new ObjectOutputStream(fos);
		
		oos.writeObject(ignoreArray);
		
		oos.close();
		fos.close();
		
	}
	
	public static Player genNewPlayer(String saveName) {
		
		final int[] newArray = new int[15];
		
		newArray[0] = 0;
		newArray[1] = 0;
		newArray[2] = 0;
		newArray[3] = 0;
		newArray[4] = 0;
		newArray[5] = 0;
		newArray[6] = 0;
		newArray[7] = 0;
		newArray[8] = 0;
		newArray[9] = 0;
		newArray[10] = 0;
		newArray[11] = 0;
		newArray[12] = 10;
		newArray[13] = 10;
		newArray[14] = 0;
		
		return new Player(saveName, "", "", newArray);
		
	}
	
	public static List<String> getPlayerNames(Player[] playerNames){
		
		List<String> playerNamesList = new ArrayList<String>();
		Player readPlayer;
		
		for(int x = 0; x < playerNames.length; x++) {
			
			readPlayer = playerNames[x];
			if(!"IGNORE".equals(readPlayer.getSaveName())) {
				
				playerNamesList.add(readPlayer.getSaveName());
				
			}
			
		}
		
		return playerNamesList;
		
	}
	
	public static void initLogger() {
		
		DateFormat dateFormat = new SimpleDateFormat("MM:dd:yyyy_hh.mm.ss");
		Date date = new Date();
		String currentLogString = dateFormat.format(date);
		
		File currentLog = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
			 + System.getProperty("file.separator") + "SURPG" + System.getProperty("file.separator") + "Logs" + 
				System.getProperty("file.separator") + currentLogString + ".log");
		
		if(!LATEST_LOG.exists()) {
			
			LATEST_LOG.getParentFile().mkdirs();
			try {
				LATEST_LOG.createNewFile();
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
			fileHandler1 = new FileHandler(LATEST_LOG.getPath(), false);
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
	
	public static void initNewPlayer(String saveName) {
		
		SURPG_Core.CURRENT_PLAYER = SURPG_Core.genNewPlayer(saveName);
		
	}
	
	public static boolean isMac() {
		
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("mac") >= 0);
		
	}
	
	public static ObjectInputStream readFile(String filePath) {
		
		ObjectInputStream ois = null;
		
		InputStream in = SURPG_Core.class.getResourceAsStream(filePath); 
		
		try {
			ois = new ObjectInputStream(in);
		} catch (IOException e) {
			runError(e);
			e.printStackTrace();
		}
		
		return ois;
		
	}
	
	public static void removePlayer(Player[] playerArray, String player) {
		
		List<Player> tempList = new ArrayList<Player>();
		
		for(int x = 0; x < playerArray.length; x++) {
			
			if(!player.equals(playerArray[x].getSaveName())) {
				
				tempList.add(playerArray[x]);
				
			}else {
				
				//Do nothing.
				
			}
			
		}
		
		LOADED_SAVES = tempList.toArray(new Player[tempList.size()]);
		
	}
	
	public static void runError(Exception e) {
		
		logger.log(Level.SEVERE, "Error! Please send the generated log file to Jake the Rey!\n");
		logger.log(Level.SEVERE, e.toString(), e);
		
	}

}
