package com.jaketherey.SURPG.IO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.GUI.GUI_Saves;
import com.jaketherey.SURPG.Game_Objects.Player;

public class Saves_Handler {
	
	private static ObjectInputStream ois = null;
	private static FileInputStream fis = null;
	
	public static void loadMainSaves() {
		
		if(!SURPG_Core.MAIN_SAVES.exists()) {
			
			SURPG_Core.MAIN_SAVES.getParentFile().mkdirs();
			try {
				SURPG_Core.genBlankSaves();
			} catch (IOException e) {
				SURPG_Core.runError();
				e.printStackTrace();
			}
			
		}
		
		try {
			fis = new FileInputStream(SURPG_Core.MAIN_SAVES);
			ois = new ObjectInputStream(fis);
			SURPG_Core.LOADED_SAVES = (Player[]) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			SURPG_Core.runError();
			e.printStackTrace();
		}
		
		GUI_Saves.viewSaves(SURPG_Core.LOADED_SAVES);
		
	}
	
	public static void saveData() {

		SURPG_Core.removePlayer(SURPG_Core.LOADED_SAVES, (String) SURPG_Core.CURRENT_PLAYER.getVal("saveName"));
		
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
		} catch (IOException e) {
			SURPG_Core.runError();
			e.printStackTrace();
		}
		
	}

}
