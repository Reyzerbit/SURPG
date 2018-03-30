/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import com.jaketherey.SURPG.GUI.Main.GUI_Utils;
import com.jaketherey.SURPG.IO.SURPGLogger;
import com.jaketherey.SURPG.Items.BoostValue;
import com.jaketherey.SURPG.Items.EquipableItem;
import com.jaketherey.SURPG.Items.Item;
import com.jaketherey.SURPG.Items.UseableItem;

/**
 * The entity object, a basic collection of data values that are
 * referenced to determine the Entity's ability to do things in the game world.
 * @author Jacob Batista
 * @since 1.0
 * @see com.jaketherey.SURPG.Entities.Player
 */
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
	int precision;
	int athletics;
	int balance;
	int maxHP;
	int currentHP;
	int armor;

	List<UseableItem> heldItems;
	List<EquipableItem> equipedItems;
	
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
			this.precision = stats[9];
			this.athletics = stats[10];
			this.balance = stats[11];
			this.maxHP = stats[12];
			this.currentHP = stats[13];
			this.armor = stats[14];
			
			equipedItems = new ArrayList<EquipableItem>();
			
		}catch(ArrayIndexOutOfBoundsException e) {
			SURPGLogger.runError(e);
			e.printStackTrace();
		}
		
	}

	public int getStrength() {
		refreshStats();
		return strength;
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
		refreshStats();
		return intelligence;
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
		refreshStats();
		return agility;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
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
	
	/**
	 * Refreshes all the primary stat values (strength, intelligence, and agility)
	 * based on the sum of their parts.
	 * @since 1.0
	 */
	public void refreshStats() {
		strength = physStrength + will + endurance;
		intelligence = communication + probSolve + insight;
		agility = precision + athletics + balance;
	}
	
	/**
	 * Equips an {@link com.jaketherey.SURPG.Items.EquipableItem} to the entity.
	 * @param item {@link com.jaketherey.SURPG.Items.EquipableItem} item to be equipped
	 * @since 1.0
	 * @see #unequipItem(EquipableItem)
	 */
	public void equipItem(EquipableItem item) {
		equipedItems.add(item);
		BoostValue[] tempBoostVal = item.getBoostValues();
		for(int x = 0; x < tempBoostVal.length; x++) {
			switch(tempBoostVal[x].getStatToBoost()) {
			case "physicalStrength": physStrength += tempBoostVal[x].getBoostValue();
				break;
			case "will": will += tempBoostVal[x].getBoostValue();
				break;
			case "endurance": endurance += tempBoostVal[x].getBoostValue();
				break;
			case "communication": communication += tempBoostVal[x].getBoostValue();
				break;
			case "problemSolving": probSolve += tempBoostVal[x].getBoostValue();
				break;
			case "insight": insight += tempBoostVal[x].getBoostValue();
				break;
			case "precision": precision += tempBoostVal[x].getBoostValue();
				break;
			case "athletics": athletics += tempBoostVal[x].getBoostValue();
				break;
			case "balance": balance += tempBoostVal[x].getBoostValue();
				break;
			case "armor": armor += tempBoostVal[x].getBoostValue();
				break;
			default: SURPGLogger.logger.log(Level.INFO, ("Nothing boosted"));
				break;
			}	
		}
		GUI_Utils.reloadLabels();
	}
	
	/**
	 * Unequips an item from the entity.
	 * @param item Item to unequip.
	 * @since 1.0
	 * @see #equipItem(EquipableItem)
	 */
	public void unequipItem(EquipableItem item) {
		equipedItems.remove(item);
		BoostValue[] tempBoostVal = item.getBoostValues();
		for(int x = 0; x < tempBoostVal.length; x++) {
			switch(tempBoostVal[x].getStatToBoost()) {
			case "physicalStrength": physStrength -= tempBoostVal[x].getBoostValue();
				break;
			case "will": will -= tempBoostVal[x].getBoostValue();
				break;
			case "endurance": endurance -= tempBoostVal[x].getBoostValue();
				break;
			case "communication": communication -= tempBoostVal[x].getBoostValue();
				break;
			case "problemSolving": probSolve -= tempBoostVal[x].getBoostValue();
				break;
			case "insight": insight -= tempBoostVal[x].getBoostValue();
				break;
			case "precision": precision -= tempBoostVal[x].getBoostValue();
				break;
			case "athletics": athletics -= tempBoostVal[x].getBoostValue();
				break;
			case "balance": balance -= tempBoostVal[x].getBoostValue();
				break;
			case "armor": armor += tempBoostVal[x].getBoostValue();
				break;
			default: SURPGLogger.logger.log(Level.INFO, ("Nothing unboosted"));
				break;
			}	
		}
		GUI_Utils.reloadLabels();
	}
	
	public List<EquipableItem> getEquipedItems(){
		return equipedItems;
	}
	
	public int getArmor() {
		return armor;
	}
	
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	public void addItem(UseableItem item) {
		
		heldItems.add(item);
		
	}
	
	public List<UseableItem> getItems(){
		
		return heldItems;
		
	}
	
	public void removeItem(Item item) {
		heldItems.remove(item);
	}
	
}
