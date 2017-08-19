package com.reyzerbit.storyline;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.reyzerbit.Feats;
import com.reyzerbit.fetchDataClasses.StringsClass;
import com.reyzerbit.guis.AlertUpgrade;

public class PickingCharacter {
	
	//String Arrays as Lists for Possible Choices
	static List<String> helpHint = Arrays.asList("HELP", "HINT", "HELP ME");
	static List<String> pChoices = Arrays.asList("FORM", "GENERATE PHYSICAL FORM", "GENERATE FORM", "GENERATE A FORM", "GENERATE A PHYSICAL FORM", "CREATE A PHYSICAL FORM", "PHYSICAL FORM");
	static List<String> pChoices0_1 = Arrays.asList("BREAK FREE", "FREE MYSELF");
	static List<String> pChoices1 = Arrays.asList("CHEST", "HAND", "HEAD", "FOREHEAD", "STOMACH", "EYE", "NOSE", "BACK");
	static List<String> pChoices2 = Arrays.asList("RUBY", "SAPPHIRE", "AMETHYST", "ROSE QUARTZ", "PERIDOT", "LAPIS LAZULI", "JASPER", "PEARL");
	static List<String> pChoices2_0 = Arrays.asList("DIAMOND", "YELLOW DIAMOND", "PINK DIAMOND", "BLUE DIAMOND", "WHITE DIAMOND");
	static List<String> pChoices3_0 = Arrays.asList("RUN", "ESCAPE", "RUN AWAY", "LEAVE");
	static List<String> pChoices3_1 = Arrays.asList("ATTACK", "FIGHT", "ATTACK HER", "FIGHT HER");
	static List<String> pChoices3_2 = Arrays.asList("TALK", "REPLY", "TALK TO HER", "SPEAK", "TALK BACK");
	
	//Int for Return
	static int returnVal = 0;

	//Each Run (Run0, Run1, etc.) runs part of the story line.
	
	//Begin game.
	public static void run0(){
		Feats.addText(StringsClass.readString("s1"));
		Feats.location = 1;
		
	}
		
	//Decide to form body.
	public static void run1(String formBody){
		
		if(pChoices.contains(formBody.toUpperCase())){
			
			Feats.addText(StringsClass.readString("s2"));
			Feats.location = 2;
				
		}
		else if(pChoices0_1.contains(formBody.toUpperCase())){
				
			Feats.addText("You try to " + formBody + ", but it doesn't work, because you have no physical form. What do you do instead?\n\n");
				
		}
		else if(helpHint.contains(formBody.toUpperCase())){
				
			Feats.addText("You are currently an unformed gem. Maybe try generating a physical form?\n\n");
				
		}
		else if(formBody.isEmpty()){
			
			Feats.addText("Please enter a valid input.\n\n");
			
		}
		else{
				
			Feats.addText("You try to " + formBody + ", but it doesn't work. What do you do instead?\n\n");
				
		}
		
	}
	
	//Pick gem location.
	public static void run2(String gemSpot){
		
		if(pChoices1.contains(gemSpot.toUpperCase())){
			
			Feats.gemSpot = gemSpot;
			Feats.addText(StringsClass.readString("s3"));
			Feats.addText(gemSpot);
			Feats.addText(StringsClass.readString("s3.1"));
			Feats.location = 3;
				
		}
		else if(helpHint.contains(gemSpot.toUpperCase())){
				
			Feats.addText("Pick a spot on your body for your gem to be located.\n\n");
				
		}
		else if(gemSpot.isEmpty()){
			
			Feats.addText("Please enter a valid input.\n\n");
			
		}
		else{
				
			Feats.addText("I don't think you can have a gem on your " + gemSpot + ". Where is it really?\n\n");
				
		}
		
	}
	
	//Gem type selection.
	public static void run3(String gemType){
		
		if(pChoices2.contains(gemType.toUpperCase())){
			
			Feats.gemType = gemType;
			Feats.addText(StringsClass.readString("s4"));
			Feats.addText(gemType);
			Feats.addText(StringsClass.readString("s4.1"));
			Feats.location = 4;
		}
		else if(helpHint.contains(gemType.toUpperCase())){
			
			Feats.addText("Pick a type of valuable stone to play as, for instance a ruby or a sapphire.\n\n");
			
		}
		else if(pChoices2_0.contains(gemType.toUpperCase())){
			
			Feats.addText("You cannot play as a diamond, because there are only four, and they are already alive.\n\n");
			
		}
		else if(gemType.isEmpty()){
			
			Feats.addText("Please enter a valid input.\n\n");
			
		}
		else{
			Feats.addText("I don't think you can be a " + gemType +". What gem are you really?\n\n");
		}
		
	}
		
	//Confrontation with Peridot.
	public static void run4(String confrontation) throws IOException{
		//Attempt to run.
		if(pChoices3_0.contains(confrontation.toUpperCase())){
			
			Feats.addText(StringsClass.readString("s5"));
			Feats.addText(Feats.gemType);
			Feats.addText(StringsClass.readString("s5.1"));
			Feats.location = 5;
			++Feats.agility;
			AlertUpgrade.upgrade("agility");
			Feats.resetStat();
			
		}
		//Attempt to fight.
		else if(pChoices3_1.contains(confrontation.toUpperCase())){
				
			Feats.addText(StringsClass.readString("s6"));
			Feats.location = 6;
			Feats.physicalStrength++;
			Feats.will++;
			AlertUpgrade.upgrade("will and physical strength");
			Feats.resetStat();
				
		}
		//Attempt to reason.
		else if(pChoices3_2.contains(confrontation.toUpperCase())){
				
			Feats.addText(StringsClass.readString("s7"));
			Feats.location = 7;
			++Feats.intelligence;
			AlertUpgrade.upgrade("intelligence");
			Feats.resetStat();
				
		}
		else if(helpHint.contains(confrontation.toUpperCase())){
				
			Feats.addText("Do you want to run away from the peridot? Or perhaps reply to her?\n\n");
				
		}
		else if(confrontation.isEmpty()){
			
			Feats.addText("Please enter a valid input.\n\n");
			
		}
		else{
				
			Feats.addText("I don't think that would work. What do you do instead?\n\n");
				
		}
		
	}
	
}
