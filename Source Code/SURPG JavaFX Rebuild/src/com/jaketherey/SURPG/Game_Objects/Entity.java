package com.jaketherey.SURPG.Game_Objects;

import java.io.Serializable;

import com.jaketherey.SURPG.SURPG_Core;

public class Entity implements Serializable{
	
	private static final long serialVersionUID = 1921181675142092025L;
	
	int strength;
	int physStrength;
	int will;
	int endurance;
	int intelligence;
	int communication;
	int probSolve;
	int insight;
	int agility;
	int precission;
	int athletics;
	int balance;
	int maxHP;
	int currentHP;
	int location;

	public Entity(int[] stats){
		
		try {
			
			this.strength = stats[0];
			this.physStrength = stats[1];
			this.will = stats[2];
			this.endurance = stats[3];
			this.intelligence = stats[4];
			this.communication = stats[5];
			this.probSolve = stats[6];
			this.insight = stats[7];
			this.agility = stats[8];
			this.precission = stats[9];
			this.athletics = stats[10];
			this.balance = stats[11];
			this.maxHP = stats[12];
			this.currentHP = stats[13];
			this.location = stats[14];
			
		}catch(ArrayIndexOutOfBoundsException e) {
			SURPG_Core.runError();
			e.printStackTrace();
		}
		
	}
	
	public void setVal(String value, Object setVal) {

		try {
			this.getClass().getField(value).set(value, setVal);
		}catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			SURPG_Core.runError();
			e.printStackTrace();
		}
		
	}
	
	public Object getVal(String value){
		
		Object returnVal = null;
		
		try {
			returnVal = this.getClass().getField(value);
		}catch (IllegalArgumentException | NoSuchFieldException | SecurityException e) {
			SURPG_Core.runError();
			e.printStackTrace();
		}
		
		return returnVal;
		
	}
	
}
