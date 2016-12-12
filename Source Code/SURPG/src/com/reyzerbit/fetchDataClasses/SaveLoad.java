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
			
		System.out.println("Saving...");
		
		JSONObject obj = new JSONObject();
		
		obj.put("Location", Feats.location);
		obj.put("Gem Type", Feats.gemType);
		obj.put("Gem Placement", Feats.gemSpot);
		
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
        
        MenuBar.saveGame.enable();

		GUIContent.outputWindow.setText("");
        
        System.out.println("Loading to Location: " + Feats.location);
        
        Feats.nameLocation(Feats.location);
		
	}
	
	public static void loadGame(){
		
		//File Chooser
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON file", "json");
		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(GUIContent.gui);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			
            Feats.saveFile = fc.getSelectedFile();
            System.out.println("Loading: " + Feats.saveFile.getName());
            
            //Read JSON File
            JSONParser parser = new JSONParser();
            long recentYN = 0;
            try {
            	
				Object objLoad = parser.parse(new FileReader(Feats.saveFile));
	            JSONObject jsonObject = (JSONObject) objLoad; 
	            
	            //Set JSON Objects to Current Data in Story
	            Feats.location = (long) jsonObject.get("Location");
	            Feats.gemSpot = (String) jsonObject.get("Gem Placement");
	            Feats.gemType = (String) jsonObject.get("Gem Type");
	    		
	    		recentYN = (long) jsonObject.get("Recent");
	    		
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
            
            MenuBar.saveGame.enable();

    		GUIContent.outputWindow.setText("");
            
            System.out.println("Loading to Location: " + Feats.location);
            
            Feats.nameLocation(Feats.location);
            
            RecentsLoad.addRecent(Feats.saveFile, recentYN);
            
        }
		else{
			
        	System.out.println("Open command cancelled by user.");
        	
        }
		
	}
	
	public static void saveNewGame(){
		
		SaveNewWindow.saveWindow();
		
	}

}
