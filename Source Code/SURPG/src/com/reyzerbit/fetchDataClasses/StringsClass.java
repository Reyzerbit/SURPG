package com.reyzerbit.fetchDataClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.reyzerbit.Feats;

public class StringsClass {

	
	//Read String from Strings.json
	public static String readString(String stringTitle){
		
		String stringComplete = null;
		
		//File location
		File stringsFile = new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + Feats.separate + "RequiredFiles" + Feats.separate + "StringsFile.json");

		//This declare the download URL for later use.
		URL download = null;
		try {
			download = new URL("https://raw.githubusercontent.com/jacobaccio/SURPG/Version-1.0/Resources/StringsFile.json");
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		}
		
		//Check to see if file exists. If it does, continue. If it doesn't attempt download.
		if(stringsFile.exists()){
			
			System.out.println("Strings JSON Found, Reading String");
			
		}
		else{
			
			//File downloading
			System.out.println("Downloading File");
			
			try {
				FileUtils.copyURLToFile(download, stringsFile, 30000, 30000);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Couldn't download Strings.json");
			}
			
		}
		
		//Create JSON Parser and object to read JSON file
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		
		//Read specific string. Uses the item "stringTitle" from method to find required string location.
        try {
        	
			Object objLoad = parser.parse(new FileReader(stringsFile));
            jsonObject = (JSONObject) objLoad;
	        stringComplete = (String) jsonObject.get(stringTitle);
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
        
        return stringComplete;
        
	}
	
}
