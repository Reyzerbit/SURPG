package com.reyzerbit.fetchDataClasses;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.UnsupportedAudioFileException;

import com.jaketherey.openal_java_bundle.ALException;
import com.jaketherey.openal_java_bundle.OpenAL;
import com.jaketherey.openal_java_bundle.Source;

public class PlaySoundBite {
	
	public static OpenAL sound = null;
	public static Source audioSource = null;
	
	public static void play(String filePath, boolean looping){
		
		File file = new File(filePath);
		
		try {
			sound = new OpenAL();
		} catch (ALException e1) {
			e1.printStackTrace();
		}
		
		try {
			audioSource = sound.createSource(file);
		} catch (ALException | IOException | UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		}
		
		try {
			audioSource.setLooping(looping);
		} catch (ALException e) {
			e.printStackTrace();
		}
		
		try {
			audioSource.play();
		} catch (ALException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public static void close(){
		
		System.out.println("Closing sound bite...");
		
		sound.close();

		System.out.println("Sound bite closed.");
		
	}
	
}
