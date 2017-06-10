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
import com.reyzerbit.MenuBar;
import com.reyzerbit.guis.GUIContent;
import com.reyzerbit.guis.SaveNewWindow;

public class SaveLoad {
	
	public static void saveFile(){
			
		//Attempt save
		System.out.println("Saving...");
		
		//Put items into JSON Object.
		JSONObject obj = new JSONObject();
		
		obj.put("Location", Feats.location);
		obj.put("Gem Type", Feats.gemType);
		obj.put("Gem Placement", Feats.gemSpot);
		obj.put("Recent", Feats.recent);
		
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
	
	public static void loadRecent(File file){
		
		//Get save file, "file" variable is from method, predeclared upon selecting option in menubar.
		Feats.saveFile = file;
		
        System.out.println("Loading: " + file.getName());
        
        //Read JSON File
        JSONParser parser = new JSONParser();
        try {
        	
			Object objLoad = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) objLoad; 
            
            //Set JSON Objects to Current Data in Story
            Feats.location = (long) jsonObject.get("Location");
            Feats.gemSpot = (String) jsonObject.get("Gem Placement");
            Feats.gemType = (String) jsonObject.get("Gem Type");
            
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        //Enable save game menu item.
        MenuBar.saveGame.enable();

        //Reset output window.
		GUIContent.outputWindow.setText("");
        
        System.out.println("Loading to Location: " + Feats.location);
        
        //Begin story at Feats.location
        Feats.nameLocation(Feats.location);
		
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
            long recentYN = 0;
            try {
            	
            	//JSON Load and parse save file.
				Object objLoad = parser.parse(new FileReader(Feats.saveFile));
	            JSONObject jsonObject = (JSONObject) objLoad; 
	            
	            //Set JSON Objects to Current Data in Story
	            Feats.location = (long) jsonObject.get("Location");
	            Feats.gemSpot = (String) jsonObject.get("Gem Placement");
	            Feats.gemType = (String) jsonObject.get("Gem Type");
	    		
	    		recentYN = (long) jsonObject.get("Recent");
	    		
	    		//Check if it's on the recents file.
	    		if(recentYN == 0){
	    			String filePath = new String(Feats.saveFile.toString());
	    			jsonObject.put("Recent", 1);
	    			try(FileWriter saveData = new FileWriter(filePath)){
	    		        saveData.write(jsonObject.toJSONString());
	    		        
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			} 
	    		}
	            
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
            RecentsLoad.addRecent(Feats.saveFile, recentYN);
            
        }
		else{
			
        	System.out.println("Open command cancelled by user.");
        	
        }
		
	}
	
	//Method to begin "save new game" process.
	public static void saveNewGame(){
		
		SaveNewWindow.saveWindow();
		
	}

}
