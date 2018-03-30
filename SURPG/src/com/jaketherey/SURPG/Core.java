/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG;

import java.io.File;
import java.io.IOException;

import com.jaketherey.SURPG.Entities.Player;
import com.jaketherey.SURPG.GUI.Main.GUI_Main;
import com.jaketherey.SURPG.GUI.Main.Menu_Control;
import com.jaketherey.SURPG.IO.Storyline;

/**
 * The core for the entire game. Holds globally accessed objects and static methods.
 * @author Jacob Batista
 * @since 1.0
 */
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
	
	/**
	 * Current running player from selected save or generated new one.
	 * @since 1.0
	 */
	public static Player CURRENT_PLAYER;
	/**
	 * Array of players, retrieved from save file. Used to list saves to select.
	 * @since 1.0
	 */
	public static Player[] LOADED_SAVES;
	/**
	 * Main GUI, used as center area for the program to find the main GUI.
	 * @since 1.0
	 */
	public static GUI_Main MAIN_GUI;
	/**
	 * Set upon startup, lets the game know if it is in developer mode or not.
	 * @since 1.0
	 */
	public static boolean DEVELOPER_MODE = false;
	
	/**
	 * genBlankSaves() creates a new blank file, which is meant to be the main save location for the game.
	 * @throws IOException
	 * @since 1.0
	 */
	public static void genBlankSaves() throws IOException {
		MAIN_SAVES_FILE.getParentFile().mkdirs();
		MAIN_SAVES_FILE.createNewFile();
	}
	
	/**
	 * genNewPlayer() creates a new (starting stats) player with the name given through the parameter.
	 * @param saveName The name used to identify the save state.
	 * @return Returns a new player.
	 * @since 1.0
	 */
	public static Player genNewPlayer(String saveName) {
		final int[] newArray = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 0};
		return new Player(saveName, "", "", newArray);
	}
	
	/**
	 * Checks if the user's OS is Mac or not.
	 * @return True or false, depending on the OS.
	 * @since 1.0
	 */
	public static boolean isMac() {
		String OS = System.getProperty("os.name").toLowerCase();
		return (OS.indexOf("mac") >= 0);
	}
	
	/**
	 * init() Initializes main game elements.
	 * @since 1.0
	 */
	public static void init() {
		initGUI();
		initStoryline();
	}

	/**
	 * Initializes the storyline reading system.
	 * @since 1.0
	 */
	public static void initStoryline() {
		Storyline.initReader();
		Storyline.runWithValue("");
	}

	/**
	 * Initializes the main game GUI.
	 * @since 1.0
	 */
	public static void initGUI() {
		MAIN_GUI = new GUI_Main();
		MAIN_GUI.show();
		Menu_Control.initMenus();
	}

}
