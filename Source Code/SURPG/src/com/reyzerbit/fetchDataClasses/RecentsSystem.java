package com.reyzerbit.fetchDataClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.reyzerbit.Feats;
import com.reyzerbit.guis.MenuBar;

public class RecentsSystem {
	
	static String recentsFilePath = new String(System.getProperty("user.home") + Feats.separate + "Documents" 
    		+ Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "Recents.json");
	
	static File recentsFile = new File(recentsFilePath);
	
	public static void addRecent(File filePath){
		
		//If file exists
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		
		boolean exists = false;
		
        try {
        	
        	//Read file
			Object objLoad = parser.parse(new FileReader(recentsFile));
            jsonObject = (JSONObject) objLoad;
            //Get size of recents file.
	        int recNum = jsonObject.size();
	        
	        //Loop to make all recents.
	        for(int i = 0; i < recNum-1; i++){
	        	
	        	//Get recent by taking current loop number (i) and adding 1 to it.
	        	String loadNumber = Integer.toString(i+1);
	        	
	        	//Make the a string that is the name of the recent save file, received from the JSON file.
	        	String nameMenu = (String) jsonObject.get(loadNumber);
	        	
	        	File namePath = new File(nameMenu);
	        	
	        	if(namePath.equals(filePath)){
	        		
	        		System.out.println("This recent already exists.");
	        		exists = true;
	        		
	        	}else{
	        		
	        		System.out.println("Checking next file.");
	        		
	        	}
	        	
	        }
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
        if(exists){
        	
        	System.out.println("Not adding recent file.");
        	
        }else{
        	
        	//Get recents file.
	        String recentsPath = new String(System.getProperty("user.home") + Feats.separate + "Documents" 
	        		+ Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "Recents.json");
	        
	        File recentFilePath = new File(recentsPath);

	        //Attempt to read recents file.
            JSONParser parser1 = new JSONParser();
			Object objLoad1 = null;
			try {
				objLoad1 = parser1.parse(new FileReader(recentsPath));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			//Look at this section later
			
            JSONObject obj1 = (JSONObject) objLoad1;
            
            int numberFile = obj1.size();
            
            String numFileLoc = Integer.toString(numberFile);
            
            String fileLoc = filePath.getPath();
			
			obj1.put(numFileLoc, fileLoc);
			
			//^^^^^^^^^^^^^^^^^^^^^^
	        
	        //Save Data
	        try(FileWriter saveData = new FileWriter(recentFilePath)){
	        	
		        saveData.write(obj1.toJSONString());
		        
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        }
        
        loadRecents();
        
	}
	
	public static void loadRecents(){
		
		MenuBar.loadGame.removeAll();
		MenuBar.loadGame.add(MenuBar.pickFile);
		MenuBar.loadGame.addSeparator();
		MenuBar.loadGame.add(MenuBar.recents);
		
		if(recentsFile.exists()){
			
			//If file exists
			JSONParser parser = new JSONParser();
			JSONObject jsonObject;
			
	        try {
	        	
	        	//Read file
				Object objLoad = parser.parse(new FileReader(recentsFile));
	            jsonObject = (JSONObject) objLoad;
	            //Get size of recents file.
		        int recNum = jsonObject.size();
		        
		        //Loop to make all recents.
		        for(int i = 0; i < recNum-1; i++){
		        	
		        	//Get recent by taking current loop number (i) and adding 1 to it.
		        	String loadNumber = Integer.toString(i+1);
		        	
		        	//Make the a string that is the name of the recent save file, received from the JSON file.
		        	String nameMenu = (String) jsonObject.get(loadNumber);
		        	
		        	//Print the name of the file.
		        	System.out.println(nameMenu);
		        	
		        	//Make a file from the received save file.
		        	File fileMenu = new File(nameMenu);
		        	
		        	if(fileMenu.exists()){
		        		
		        		//Add said file (with name) to menu bar.
		        		MenuBar.makeMenuItem(nameMenu, fileMenu);
		        		
		        	}
		        	
		        }
				
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}else if(!recentsFile.exists()){
			
			//Make the recents file.
			System.out.println("Making recents file.");
			
			//Set download URL for later use.
			URL download = null;
			try {
				download = new URL("https://raw.githubusercontent.com/jacobaccio/SURPG/Version-1.0/Resources/Recents.json");
			} catch (MalformedURLException e2) {
				e2.printStackTrace();
				System.out.println("Failed to set URL for recents.json");
			}
			
			//Try to download said file
			try {
				FileUtils.copyURLToFile(download, recentsFile, 30000, 30000);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Failed to download recents.json");
			}
			
		}
		
	}

}
