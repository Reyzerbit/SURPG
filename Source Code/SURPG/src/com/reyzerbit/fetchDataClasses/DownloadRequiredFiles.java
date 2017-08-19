package com.reyzerbit.fetchDataClasses;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import com.reyzerbit.Feats;

public class DownloadRequiredFiles {
	
	public static void downloadAudioFile(String fileURL, String name) throws IOException{
		
		//Downloading Audio File
		System.out.println("Downloading audio file.");
		
		//Set download URL for later use.
		URL download = new URL(fileURL);
		File upload = new File(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + 
				Feats.separate + "RequiredFiles"+ Feats.separate + "audio"+ Feats.separate + name);
		
		if(upload.exists()){
			
			System.out.println("Audio file already exists.");
			
		}else{
		
			//Try to download said file
			FileUtils.copyURLToFile(download, upload, 30000, 30000);
		
		}
		
	}

}
