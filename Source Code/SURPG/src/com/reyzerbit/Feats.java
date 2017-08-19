package com.reyzerbit;

import java.io.File;
import java.io.IOException;

import com.reyzerbit.fetchDataClasses.StringsClass;
import com.reyzerbit.guis.CombatGUI;
import com.reyzerbit.guis.GUIContent;
import com.reyzerbit.guis.HealthRectangle;
import com.reyzerbit.storyline.PickingCharacter;
import com.reyzerbit.storyline.Test;

public class Feats {
	
	//Player Features
	//Gem spot and type.
	public static String gemSpot = new String("undefined");
	public static String gemType = new String("undefined");
	
	//Stats
	public static long physicalStrength = 0;
	public static long will = 0;
	public static long endurance = 0;
	public static long strength = 0;
	
	
	public static long agility = 0;
	public static long precision = 0;
	public static long athletics = 0;
	public static long balance = 0;
	
	public static long intelligence = 0;
	public static long insight = 0;
	public static long communication = 0;
	public static long problemSolving = 0;
	
	public static double health = 10;
	public static double maxHealth = 10;
	
	//Separator for Different OS's
	public static String separate = new String(System.getProperty("file.separator"));
	public static String requiredFiles = new String(System.getProperty("user.home") + separate + "Documents" + separate + "SURPG" + 
				separate + "RequiredFiles" + separate);
	
	//Location in Story
	
	public static long location = 0;
	
	//Save File Location
	
	public static File saveFile = null;
	
	//Add Text Feature
	
	public static void addText(String text){
		
		GUIContent.outputWindow.append(text);
		System.out.println("Text added.");
		
	}
	
	//Path Run Method
	
	public static void runNextPath(long locator) throws IOException{

		
		if(locator == 0){
			PickingCharacter.run0();
			clearInput();
		}
		else if(locator == 1){
			PickingCharacter.run1(getInput());
			clearInput();
		}
		else if(locator == 2){
			PickingCharacter.run2(getInput());
			clearInput();
		}
		else if(locator == 3){
			PickingCharacter.run3(getInput());
			clearInput();
		}
		else if(locator == 4){
			PickingCharacter.run4(getInput());
			clearInput();
		}
		else if(locator == 5){
			Test.run0();
			clearInput();
		}
		
		System.out.println("Next path run.");
		
	}
	
	//Clear input window.
	
	public static void clearInput(){
		
		GUIContent.inputWindow.setText("");
		
	}
	
	//Get input window text
	public static String getInput(){
		
		return GUIContent.inputWindow.getText();
		
	}
	
	//For Load Files
	
	public static void nameLocation(long loc){
		
		String locNum = "s" + loc;
		String locatedName = StringsClass.readString(locNum);
		
		if(loc == 3){
			
			Feats.addText(locatedName);
			Feats.addText(gemSpot);
			Feats.addText(StringsClass.readString(locNum + ".1"));
			
		}
		else if(loc == 4){
			
			Feats.addText(locatedName);
			Feats.addText(gemType);
			Feats.addText(StringsClass.readString(locNum + ".1"));
			
		}
		else if(loc == 5){
			
			Feats.addText(locatedName);
			Feats.addText(gemType);
			Feats.addText(StringsClass.readString(locNum + ".1"));
			
		}else{
			
			Feats.addText(locatedName);
			
		}

	}
	
	//Reset Stats GUI
	
	public static void resetStat(){

		strength = physicalStrength + will + endurance;
		intelligence = insight + communication + problemSolving;
		agility = precision + balance + athletics;
		
		GUIContent.agilPoints.setText("Agl: " + agility);
		GUIContent.strengthPoints.setText("Str: " + strength);
		GUIContent.intelPoints.setText("Int: " + intelligence);
		GUIContent.physicalPoints.setText("PS: " + physicalStrength);
		GUIContent.willPoints.setText("Will: "  + will);
		GUIContent.endurancePoints.setText("End: " + endurance);
		GUIContent.communicationPoints.setText("Com: " + communication);
		GUIContent.problemSolvePoints.setText("Prob: " + problemSolving);
		GUIContent.insightPoints.setText("Ins: " + insight);
		GUIContent.precisionPoints.setText("Prec: " + precision);
		GUIContent.athleticsPoints.setText("Ath: " + athletics);
		GUIContent.balancePoints.setText("Bal: " + balance);
		
		CombatGUI.enemyHealthLabel.setText("Enemy HP: " + CombatGUI.enemyHealthVar);
		
		//Calculate health bar amount.
		HealthRectangle.healthPercent = (Math.ceil((health/maxHealth)*140));
		GUIContent.healthBar.repaint();
		GUIContent.healthPointLabel.setText(Math.round(health) + "/" + Math.round(maxHealth));
		
		System.out.println("Stats reloaded.");
		
	}
	
}
