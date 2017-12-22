package com.jaketherey.SURPG.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.GUI.GUI_Saves;
import com.jaketherey.SURPG.Game_Objects.Player;

public class Saves_Handler {
	
	private static ObjectInputStream ois = null;
	private static FileInputStream fis = null;
	
	public static void loadMainSaves() {
		
		SURPG_Core.logger.log(Level.INFO, "Loading and reading saves...");
		
		if(!SURPG_Core.MAIN_SAVES.exists()) {
			
			SURPG_Core.logger.log(Level.INFO, "No saves file exists, creating new one.");
			
			SURPG_Core.MAIN_SAVES.getParentFile().mkdirs();
			try {
				SURPG_Core.genBlankSaves();
				SURPG_Core.logger.log(Level.INFO, "New saves file generated.");
			} catch (IOException e) {
				SURPG_Core.runError(e);
				e.printStackTrace();
			}
			
		}
		
		try {
			fis = new FileInputStream(SURPG_Core.MAIN_SAVES);
			ois = new ObjectInputStream(fis);
			SURPG_Core.LOADED_SAVES = (Player[]) ois.readObject();
			SURPG_Core.logger.log(Level.INFO, "Saves read.");
		} catch (ClassNotFoundException | IOException e) {
			SURPG_Core.runError(e);
			e.printStackTrace();
		}
		
		GUI_Saves.viewSaves(SURPG_Core.LOADED_SAVES);
		
	}
	
	public static void saveData() {

		SURPG_Core.logger.log(Level.INFO, "Saving data...");
		
		SURPG_Core.removePlayer(SURPG_Core.LOADED_SAVES, SURPG_Core.CURRENT_PLAYER.getSaveName());
		
		List<Player> tempList = new ArrayList<Player>(Arrays.asList(SURPG_Core.LOADED_SAVES));
		tempList.add(SURPG_Core.CURRENT_PLAYER);
		
		SURPG_Core.LOADED_SAVES = tempList.toArray(new Player[tempList.size()]);
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(SURPG_Core.MAIN_SAVES);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(SURPG_Core.LOADED_SAVES);
			oos.close();
			fos.close();
			SURPG_Core.logger.log(Level.INFO, "Data saved.");
		} catch (IOException e) {
			SURPG_Core.runError(e);
			e.printStackTrace();
		}
		
	}

}
