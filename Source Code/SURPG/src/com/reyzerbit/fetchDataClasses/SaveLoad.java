package com.reyzerbit.fetchDataClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.reyzerbit.Feats;
import com.reyzerbit.guis.GUIContent;
import com.reyzerbit.guis.MenuBar;
import com.reyzerbit.guis.SaveNewWindow;

public class SaveLoad {
	
	public static void saveFile(){
			
		//Attempt save
		System.out.println("Saving...");
		
		//Put items into JSON Object.
		JSONObject obj = new JSONObject();
		
		//Character Value saves
		obj.put("Location", Feats.location);
		obj.put("Gem Type", Feats.gemType);
		obj.put("Gem Placement", Feats.gemSpot);
		
		//Number Stats save
		obj.put("Current Health", Feats.health);
		obj.put("Total Health", Feats.maxHealth);
		
		obj.put("Strength", Feats.strength);
		obj.put("Physical Strength", Feats.physicalStrength);
		obj.put("Will", Feats.will);
		obj.put("Endurance", Feats.endurance);
		
		obj.put("Intelligence", Feats.intelligence);
		obj.put("Problem Solving", Feats.problemSolving);
		obj.put("Communication", Feats.communication);
		obj.put("Insight", Feats.insight);
		
		obj.put("Agility", Feats.agility);
		obj.put("Precision", Feats.precision);
		obj.put("Athletics", Feats.athletics);
		obj.put("Balance", Feats.balance);
		
		//Get save file.
        String filePath = new String(Feats.saveFile.toString());
        
        //Save Data
        try(FileWriter saveData = new FileWriter(filePath)){
	        saveData.write(obj.toJSONString());
	        
		} catch (IOException e) {
			e.printStackTrace();
		} 
        
        System.out.println("Save complete!");
		
	}
	
	public static void loadGame(){
		
		//File Chooser
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON file", "json");
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(GUIContent.gui);
		
		//If statement, if they choose a valid file.
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
            Feats.saveFile = fc.getSelectedFile();
            System.out.println("Loading: " + Feats.saveFile.getName());
            
            //Read JSON File
            JSONParser parser = new JSONParser();
            try {
            	
            	//JSON Load and parse save file.
				Object objLoad = parser.parse(new FileReader(Feats.saveFile));
	            JSONObject jsonObject = (JSONObject) objLoad; 
	            
	            //Set JSON Objects to Current Data in Story
	            Feats.location = (long) jsonObject.get("Location");
	            Feats.gemSpot = (String) jsonObject.get("Gem Placement");
	            Feats.gemType = (String) jsonObject.get("Gem Type");
	            
	            Feats.health = (double) jsonObject.get("Current Health");
	    		Feats.maxHealth = (double) jsonObject.get("Total Health");
	    		
	    		Feats.strength = (long) jsonObject.get("Strength");
	    		Feats.physicalStrength = (long) jsonObject.get("Physical Strength");
	    		Feats.will = (long) jsonObject.get("Will");
	    		Feats.endurance = (long) jsonObject.get("Endurance");
	    		
	    		Feats.intelligence = (long) jsonObject.get("Intelligence");
	    		Feats.problemSolving = (long) jsonObject.get("Problem Solving");
	    		Feats.communication = (long) jsonObject.get("Communication");
	    		Feats.insight = (long) jsonObject.get("Insight");
	    		
	    		Feats.agility = (long) jsonObject.get("Agility");
	    		Feats.precision = (long) jsonObject.get("Precision");
	    		Feats.athletics = (long) jsonObject.get("Athletics");
	    		Feats.balance = (long) jsonObject.get("Balance");
	            
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
            
            //Enable save game.
            MenuBar.saveGame.setEnabled(true);

            //Clear output window.
    		GUIContent.outputWindow.setText("");
            
            System.out.println("Loading to Location: " + Feats.location);
            
            //Start story at Feats.location
            Feats.nameLocation(Feats.location);
            
            //Attempt to add to recents.
            RecentsSystem.addRecent(Feats.saveFile);
            
            //Reset Stats
            Feats.resetStat();
            
        }
		else{
			
        	System.out.println("Open command cancelled by user.");
        	
        }
		
	}
	
	public static void loadRecent(File file){
		
		Feats.saveFile = file;
		
		//Read JSON File
        JSONParser parser = new JSONParser();
        try {
        	
        	//JSON Load and parse save file.
			Object objLoad = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) objLoad; 
            
            //Set JSON Objects to Current Data in Story
            Feats.location = (long) jsonObject.get("Location");
            Feats.gemSpot = (String) jsonObject.get("Gem Placement");
            Feats.gemType = (String) jsonObject.get("Gem Type");
            
            Feats.health = (double) jsonObject.get("Current Health");
    		Feats.maxHealth = (double) jsonObject.get("Total Health");
    		
    		Feats.strength = (long) jsonObject.get("Strength");
    		Feats.physicalStrength = (long) jsonObject.get("Physical Strength");
    		Feats.will = (long) jsonObject.get("Will");
    		Feats.endurance = (long) jsonObject.get("Endurance");
    		
    		Feats.intelligence = (long) jsonObject.get("Intelligence");
    		Feats.problemSolving = (long) jsonObject.get("Problem Solving");
    		Feats.communication = (long) jsonObject.get("Communication");
    		Feats.insight = (long) jsonObject.get("Insight");
    		
    		Feats.agility = (long) jsonObject.get("Agility");
    		Feats.precision = (long) jsonObject.get("Precision");
    		Feats.athletics = (long) jsonObject.get("Athletics");
    		Feats.balance = (long) jsonObject.get("Balance");
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        //Enable save game.
        MenuBar.saveGame.enable();

        //Clear output window.
		GUIContent.outputWindow.setText("");
        
        System.out.println("Loading to Location: " + Feats.location);
        
        //Start story at Feats.location
        Feats.nameLocation(Feats.location);
        
        //Attempt to add to recents.
        RecentsSystem.addRecent(Feats.saveFile);
        
        //Reset Stats
        Feats.resetStat();
		
	}
	
	//Method to begin "save new game" process.
	public static void saveNewGame(){
		
		SaveNewWindow.saveWindow();
		
	}

}
