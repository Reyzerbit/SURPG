package com.jaketherey.SURPG.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.Entities.Player;
import com.jaketherey.SURPG.GUI.GUI_Saves;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class Saves_Handler {
	
	private static ObjectInputStream ois = null;
	private static FileInputStream fis = null;
	
	public static void loadSaveData() {
		
		SURPGLogger.logger.log(Level.INFO, "Loading and reading saves...");
		
		if(!(Core.CURRENT_SAVES_FILE.exists())) {
			
			SURPGLogger.logger.log(Level.INFO, "No saves file exists, creating new one.");
			
			try {
				Core.genBlankSaves();
				SURPGLogger.logger.log(Level.INFO, "New saves file generated.");
			} catch (IOException e) {
				SURPGLogger.runError(e);
			}
			
		}else {
		
			try {
				FileInputStream tempFIS = new FileInputStream(Core.CURRENT_SAVES_FILE);
				Scanner s = new Scanner(tempFIS);
				if(s.hasNext()) {

					fis = new FileInputStream(Core.CURRENT_SAVES_FILE);
					ois = new ObjectInputStream(fis);
					Core.LOADED_SAVES = (Player[]) ois.readObject();
					SURPGLogger.logger.log(Level.INFO, "Saves read.");
					fis.close();
					ois.close();
					
				}
				s.close();
				tempFIS.close();
			} catch (ClassNotFoundException | IOException e) {
				SURPGLogger.runError(e);
			}
		
		}
		
		GUI_Saves.viewSaves();
		
	}
	
	public static void saveData() {

		SURPGLogger.logger.log(Level.INFO, "Saving data...");
		List<Player> tempList;
		try {
			tempList = new ArrayList<Player>(Arrays.asList(Core.LOADED_SAVES));
		}catch(Exception e) {
			
			tempList = new ArrayList<Player>();
			
		}
		
		if(tempList.contains(Core.CURRENT_PLAYER)) {
			
			tempList.remove(tempList.indexOf(Core.CURRENT_PLAYER));
			tempList.add(Core.CURRENT_PLAYER);
			
		}else {
			
			tempList.add(Core.CURRENT_PLAYER);
			
		}
		
		Core.LOADED_SAVES = tempList.toArray(new Player[tempList.size()]);
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		
		if(!Core.CURRENT_SAVES_FILE.exists()) {
			
			Core.CURRENT_SAVES_FILE.getParentFile().mkdirs();
			try {
				Core.CURRENT_SAVES_FILE.createNewFile();
			} catch (IOException e) {
				SURPGLogger.runError(e);
			}
			
		}
		
		try {
			fos = new FileOutputStream(Core.CURRENT_SAVES_FILE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Core.LOADED_SAVES);
			oos.close();
			fos.close();
			SURPGLogger.logger.log(Level.INFO, "Data saved.");
		} catch (IOException e) {
			SURPGLogger.runError(e);
			e.printStackTrace();
		}
		
	}
	
	public static void saveAsNew() {
		
		FileChooser selectFile = new FileChooser();
		selectFile.setTitle("Choose New Save");
		selectFile.getExtensionFilters().add(new ExtensionFilter("SURPG Save Files (.surpg)", "*.surpg"));
		Core.CURRENT_SAVES_FILE = selectFile.showSaveDialog(Core.MAIN_GUI.getStage());
		saveData();
		
	}
	
	public static void loadFrom() {
		
		FileChooser selectFile = new FileChooser();
		selectFile.setTitle("Choose Save to Load");
		selectFile.getExtensionFilters().add(new ExtensionFilter("SURPG Save Files (.surpg)", "*.surpg"));
		Core.CURRENT_SAVES_FILE = selectFile.showOpenDialog(Core.MAIN_GUI.getStage());
		loadSaveData();
		
	}

}
