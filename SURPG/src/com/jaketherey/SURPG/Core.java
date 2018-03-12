/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG;

import java.io.File;
import java.io.IOException;

import com.jaketherey.SURPG.Entities.Player;
import com.jaketherey.SURPG.GUI.GUI_Main;
import com.jaketherey.SURPG.GUI.Extra_GUI.Menu_Control;
import com.jaketherey.SURPG.IO.Storyline;

public class Core {
	
	//FILE LOCATIONS
	public static final String USER_DOCUMENTS = System.getProperty("user.home") + System.getProperty("file.separator") + "Documents";
	public static final String SURPG_DIRECTORY = USER_DOCUMENTS + System.getProperty("file.separator") + "SURPG";
	public static final String SAVES_DIRECTORY = SURPG_DIRECTORY + System.getProperty("file.separator") + "Saves";
	public static final String MAIN_SAVES_FILE_PATH = SAVES_DIRECTORY + System.getProperty("file.separator") + "MAIN_SAVE.surpg";
	public static final File MAIN_SAVES_FILE = new File(MAIN_SAVES_FILE_PATH);
	public static final String LOG_FILES_DIRECTORY = (SURPG_DIRECTORY + System.getProperty("file.separator") + "Logs");
	public static final File LATEST_LOG_FILE = new File(LOG_FILES_DIRECTORY + System.getProperty("file.separator") + "LATEST_LOG.log");
	public static File CURRENT_SAVES_FILE;
	
	public static Player CURRENT_PLAYER;		//Current player (from selected save or generated new one).
	public static Player[] LOADED_SAVES;		//Array of players, retrieved from save file. Used to list saves to select.
	public static GUI_Main MAIN_GUI;			//Main GUI, used as center area for the program to find the main GUI.
	
	public static boolean DEVELOPER_MODE = false;
	
	/**
	 * genBlankSaves() creates a new blank file, which is meant to be the main save location for the game.
	 * @throws IOException
	 */
	public static void genBlankSaves() throws IOException {
		MAIN_SAVES_FILE.getParentFile().mkdirs();
		MAIN_SAVES_FILE.createNewFile();
	}
	
	/**
	 * genNewPlayer() creates a new (starting stats) player with the name given through the parameter.
	 * @param saveName The name used to identify the save state.
	 * @return Returns a new player.
	 */
	public static Player genNewPlayer(String saveName) {
		final int[] newArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0};
		return new Player(saveName, "", "", newArray);
	}
	
	/**
	 * Checks if the user's OS is Mac or not.
	 * @return True or false, depending on the OS.
	 */
	public static boolean isMac() {
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("mac") >= 0);
	}
	
	/**
	 * init() Initializes main game elements.
	 */
	public static void init() {
		initGUI();
		initStoryline();
	}

	/**
	 * Initializes the storyline reading system.
	 */
	public static void initStoryline() {
		Storyline.initReader();
		Storyline.runWithValue("");
	}

	/**
	 * Initializes the main game GUI.
	 */
	public static void initGUI() {
		MAIN_GUI = new GUI_Main();
		MAIN_GUI.show();
		Menu_Control.initMenus();
	}

}
