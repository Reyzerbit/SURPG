package com.jaketherey.SURPG.Game_Objects;

import java.io.Serializable;

public class Player extends Entity implements Serializable{
	
	private static final long serialVersionUID = 1921181671612125518L;
	
	public String saveName;
	public String gemType;
	public String gemSpot;
	public int location;

	public Player(String saveName, String gemType, String gemSpot, int[] stats){
		
		super(stats);
		
		this.saveName = saveName;
		this.gemType = gemType;
		this.gemSpot = gemSpot;
		this.location = stats[14];
		
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
