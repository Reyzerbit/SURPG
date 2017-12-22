package com.jaketherey.SURPG.IO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.Game_Objects.Player;
import com.jaketherey.SURPG.Game_Objects.Save_Objects.Answer_Packet;
import com.jaketherey.SURPG.Game_Objects.Save_Objects.Answer_Runs;
import com.jaketherey.SURPG.Game_Objects.Save_Objects.Location_Chunk;

public class Storyline {
	
	private static Location_Chunk data;
	private static boolean repeat = false;
	private static ObjectInputStream ois = null;
	private static Location_Chunk[] chunkArray;
	
	public static void initReader() {
		
		SURPG_Core.logger.log(Level.INFO, "Initiating storyline reader...");
		
		ois = SURPG_Core.readFile("/resources/text/Storylines.ser");
		
		try {
			chunkArray = (Location_Chunk[]) ois.readObject();
			data = chunkArray[SURPG_Core.CURRENT_PLAYER.getLocation()];
			SURPG_Core.logger.log(Level.INFO, "Storyline reader initiated.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void initRun(String response) {
		
		SURPG_Core.logger.log(Level.INFO, "Running response: " + response);
		
		SURPG_Core.MAIN_GUI.clearInput();
		
		if(repeat == false) {
			
			SURPG_Core.MAIN_GUI.append(data.getStart());
			repeat = true;
			
		}else if((response.equalsIgnoreCase("help") || 
				response.equalsIgnoreCase("hint") || 
				response.equalsIgnoreCase("help me") && repeat == true)) {
			
			SURPG_Core.MAIN_GUI.append(data.getHelp());
			
		}else {
			
			Answer_Packet[] temp = data.getAnswers();
			boolean runAttempt = true;
			
			for(int i = 0; i < temp.length; i++) {
			
				if(temp[i].getAnswer().equals(response.toUpperCase())) {
					
					SURPG_Core.CURRENT_PLAYER.setLocation(temp[i].getDestination());
					runMethod(temp[i].getPossibleRuns(), temp[i].getMethodCount());
					data = chunkArray[SURPG_Core.CURRENT_PLAYER.getLocation()];
					SURPG_Core.MAIN_GUI.append(data.getStart());
					
					runAttempt = false;
					
				}
				
			}
			
			if(runAttempt) {
				
				SURPG_Core.MAIN_GUI.append(data.getAttempt());
				
			}
			
		}
		
	}
	
	public static void runMethod(Answer_Runs[] run, int methodCount) {
		
		Method method = null;
		
		//Run method
		for(int x = 0; x < methodCount; x++) {
			
			try {
				method = Player.class.getMethod(run[x].getMethodName(), int.class);
				method.invoke(SURPG_Core.CURRENT_PLAYER, run[x].getVal());
				SURPG_Core.logger.log(Level.INFO, "Method ran: " + run[x].getMethodName());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
				SURPG_Core.runError(e);
			}
			
		}
		
		SURPG_Core.MAIN_GUI.reloadLabels();
		
	}

}
