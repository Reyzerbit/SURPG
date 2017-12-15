package com.jaketherey.SURPG.IO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.GUI.GUI_Main;
import com.jaketherey.SURPG.Game_Objects.Location_Chunk;

public class Storyline {
	
	private static Location_Chunk data;
	private static boolean repeat = false;
	private static ObjectInputStream ois = null;
	private static Location_Chunk[] chunkArray;
	
	public static void initReader() {
		
		ois = SURPG_Core.readFile("/resources/text/Storylines.ser");
		
		try {
			chunkArray = (Location_Chunk[]) ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		data = chunkArray[(int) SURPG_Core.CURRENT_PLAYER.getVal("location")];
		
	}
	
	public static void initRun(String response) {
		
		GUI_Main.inputWindow.setText("");
		
		if(repeat == false) {
			
			SURPG_Core.append(data.getStart());
			repeat = true;
			
		}else if(Arrays.asList(data.getAnswers()).contains(response.toUpperCase()) && repeat == true) {
			
			SURPG_Core.CURRENT_PLAYER.setVal("location", (int) SURPG_Core.CURRENT_PLAYER.getVal("location") + 1);
			data = chunkArray[(int) SURPG_Core.CURRENT_PLAYER.getVal("location")];
			SURPG_Core.append(data.getStart());
			
		}else if((response.equalsIgnoreCase("help") || 
				response.equalsIgnoreCase("hint") || 
				response.equalsIgnoreCase("help me") && repeat == true)) {
			
			SURPG_Core.append(data.getHelp());
			
		}else {
			
			SURPG_Core.append(data.getAttempt());
			
		}
		
	}

}
