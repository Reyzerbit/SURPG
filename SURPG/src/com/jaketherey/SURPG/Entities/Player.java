package com.jaketherey.SURPG.Entities;

import java.io.Serializable;
import java.util.ArrayList;

import com.jaketherey.SURPG.Items.UseableItem;

public class Player extends Entity implements Serializable{
	
	private static final long serialVersionUID = 1921181671612125518L;
	
	String saveName;
	String gemType;
	String gemSpot;
	int location;

	public Player(String saveName, String gemType, String gemSpot, int[] stats){
		
		super(stats);
		
		this.saveName = saveName;
		this.gemType = gemType;
		this.gemSpot = gemSpot;
		this.location = stats[14];
		heldItems = new ArrayList<UseableItem>();
		
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public String getGemType() {
		return gemType;
	}

	public void setGemType(String gemType) {
		this.gemType = gemType;
	}

	public String getGemSpot() {
		return gemSpot;
	}

	public void setGemSpot(String gemSpot) {
		this.gemSpot = gemSpot;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
	
}
