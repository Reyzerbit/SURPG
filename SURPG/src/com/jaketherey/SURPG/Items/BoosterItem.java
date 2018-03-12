/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Items;

import java.io.Serializable;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.Entities.Entity;

import javafx.collections.ObservableList;

public class BoosterItem extends UseableItem implements Serializable {
	
	private static final long serialVersionUID = 1921181607022109L;

	BoostValue[] boostValues;
	
	public BoosterItem(String itemName, String description, BoostValue[] boostValues) {
		super(itemName, description);
		this.boostValues = boostValues;
	}

	@Override
	public void onUse(Entity target, ObservableList<UseableItem> containerList) {
		for(int x = 0; x < boostValues.length; x++) {
		
			switch(boostValues[x].getStatToBoost()) {
			case "physicalStrength": target.setPhysStrength(target.getPhysStrength() + boostValues[x].getBoostValue());
				break;
			case "will": target.setWill(target.getWill() + boostValues[x].getBoostValue());
				break;
			case "endurance": target.setEndurance(target.getEndurance() + boostValues[x].getBoostValue());
				break;
			case "communication": target.setCommunication(target.getCommunication() + boostValues[x].getBoostValue());
				break;
			case "problemSolving": target.setProbSolve(target.getProbSolve() + boostValues[x].getBoostValue());
				break;
			case "insight": target.setInsight(target.getInsight() + boostValues[x].getBoostValue());
				break;
			case "precision": target.setPrecision(target.getPrecision() + boostValues[x].getBoostValue());
				break;
			case "athletics": target.setAthletics(target.getAthletics() + boostValues[x].getBoostValue());
				break;
			case "balance": target.setBalance(target.getBalance() + boostValues[x].getBoostValue());
				break;
			case "armor": target.setArmor(target.getArmor() + boostValues[x].getBoostValue());
				break;
			}	
		}
		Core.MAIN_GUI.reloadLabels();
		Core.MAIN_GUI.clearItemsSelect();
		containerList.remove(this);
	}

	public BoostValue[] getBoostValues() {
		return boostValues;
	}

	public void setBoostValues(BoostValue[] boostValues) {
		this.boostValues = boostValues;
	}

}
