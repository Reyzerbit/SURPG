package com.jaketherey.SURPG.IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.SaveObjects.Answer_Packet;
import com.jaketherey.SURPG.SaveObjects.Answer_Runs;
import com.jaketherey.SURPG.SaveObjects.Location_Chunk;

public class Storyline {
	
	private static Location_Chunk data;
	private static boolean repeat = false;
	private static ObjectInputStream ois = null;
	private static Location_Chunk[] chunkArray;
	
	public static void initReader() {
		
		SURPGLogger.logger.log(Level.INFO, "Initiating storyline reader...");
		
		InputStream in = Core.class.getResourceAsStream("/resources/text/Storylines.ser"); 
		
		try {
			ois = new ObjectInputStream(in);
		} catch (IOException e) {
			SURPGLogger.runError(e);
			e.printStackTrace();
		}
		
		try {
			chunkArray = (Location_Chunk[]) ois.readObject();
			data = chunkArray[Core.CURRENT_PLAYER.getLocation()];
			SURPGLogger.logger.log(Level.INFO, "Storyline reader initiated.");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void runWithValue(String response) {
		
		SURPGLogger.logger.log(Level.INFO, "Running response: " + response);
		
		Core.MAIN_GUI.clearInput();
		
		if(repeat == false) {
			
			Core.MAIN_GUI.append(data.getStart());
			repeat = true;
			
		}else if((response.equalsIgnoreCase("help") || 
				response.equalsIgnoreCase("hint") || 
				response.equalsIgnoreCase("help me") && repeat == true)) {
			
			Core.MAIN_GUI.append(data.getHelp());
			
		}else {
			
			Answer_Packet[] temp = data.getAnswers();
			boolean runAttempt = true;
			
			for(int i = 0; i < temp.length; i++) {
			
				if(temp[i].getAnswer().equals(response.toUpperCase())) {
					
					Core.CURRENT_PLAYER.setLocation(temp[i].getDestination());
					runMethod(temp[i].getPossibleRuns());
					data = chunkArray[Core.CURRENT_PLAYER.getLocation()];
					Core.MAIN_GUI.append(data.getStart());
					
					runAttempt = false;
					
				}
				
			}
			
			if(runAttempt) {
				
				Core.MAIN_GUI.append(data.getAttempt());
				
			}
			
		}
		
	}
	
	public static void runMethod(Answer_Runs[] run) {
		
		//Run method
		for(int x = 0; x < run.length; x++) {
			switch(run[x].getMethodName()) {
			case "physicalStrength": Core.CURRENT_PLAYER.setPhysStrength(Core.CURRENT_PLAYER.getPhysStrength() + run[x].getVal());
				break;
			case "will": Core.CURRENT_PLAYER.setWill(Core.CURRENT_PLAYER.getWill() + run[x].getVal());
				break;
			case "endurance": Core.CURRENT_PLAYER.setEndurance(Core.CURRENT_PLAYER.getEndurance() + run[x].getVal());
				break;
			case "communication": Core.CURRENT_PLAYER.setCommunication(Core.CURRENT_PLAYER.getCommunication() + run[x].getVal());
				break;
			case "problemSolving": Core.CURRENT_PLAYER.setProbSolve(Core.CURRENT_PLAYER.getProbSolve() + run[x].getVal());
				break;
			case "insight": Core.CURRENT_PLAYER.setInsight(Core.CURRENT_PLAYER.getInsight() + run[x].getVal());
				break;
			case "precision": Core.CURRENT_PLAYER.setPrecision(Core.CURRENT_PLAYER.getPrecision() + run[x].getVal());
				break;
			case "athletics": Core.CURRENT_PLAYER.setAthletics(Core.CURRENT_PLAYER.getAthletics() + run[x].getVal());
				break;
			case "balance": Core.CURRENT_PLAYER.setBalance(Core.CURRENT_PLAYER.getBalance() + run[x].getVal());
				break;
			case "armor": Core.CURRENT_PLAYER.setArmor(Core.CURRENT_PLAYER.getArmor() + run[x].getVal());
				break;
			default: SURPGLogger.logger.log(Level.INFO, ("No method run."));
				break;
			}	
			
		}
		
		Core.MAIN_GUI.reloadLabels();
		
	}

}
