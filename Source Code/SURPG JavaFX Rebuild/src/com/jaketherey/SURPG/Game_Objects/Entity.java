package com.jaketherey.SURPG.Game_Objects;

import java.io.Serializable;

import com.jaketherey.SURPG.SURPG_Core;

public class Entity implements Serializable{
	
	private static final long serialVersionUID = 1921181675142092025L;
	
	public int strength;
	public int physStrength;
	public int will;
	public int endurance;
	public int intelligence;
	public int communication;
	public int probSolve;
	public int insight;
	public int agility;
	public int precission;
	public int athletics;
	public int balance;
	public int maxHP;
	public int currentHP;

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
			
		}catch(ArrayIndexOutOfBoundsException e) {
			SURPG_Core.runError(e);
			e.printStackTrace();
		}
		
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getPhysStrength() {
		return physStrength;
	}

	public void setPhysStrength(int physStrength) {
		this.physStrength = physStrength;
	}

	public int getWill() {
		return will;
	}

	public void setWill(int will) {
		this.will = will;
	}

	public int getEndurance() {
		return endurance;
	}

	public void setEndurance(int endurance) {
		this.endurance = endurance;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getCommunication() {
		return communication;
	}

	public void setCommunication(int communication) {
		this.communication = communication;
	}

	public int getProbSolve() {
		return probSolve;
	}

	public void setProbSolve(int probSolve) {
		this.probSolve = probSolve;
	}

	public int getInsight() {
		return insight;
	}

	public void setInsight(int insight) {
		this.insight = insight;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getPrecission() {
		return precission;
	}

	public void setPrecission(int precission) {
		this.precission = precission;
	}

	public int getAthletics() {
		return athletics;
	}

	public void setAthletics(int athletics) {
		this.athletics = athletics;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}

	public int getCurrentHP() {
		return currentHP;
	}

	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	
}
