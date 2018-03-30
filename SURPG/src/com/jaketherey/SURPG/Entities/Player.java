/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Entities;

import java.io.Serializable;
import java.util.ArrayList;

import com.jaketherey.SURPG.Items.UseableItem;

/**
 * The player object, which extends the {@link com.jaketherey.SURPG.Entities.Entity} class,
 * while also containing more complex data such as the {@link com.jaketherey.SURPG.Entities.Player#saveName}
 * and the {@link com.jaketherey.SURPG.Entities.Player#storylineLocation}
 * @author Jacob Batista
 * @since 1.0
 * @see com.jaketherey.SURPG.Entities.Entity
 */
public class Player extends Entity implements Serializable{
	
	private static final long serialVersionUID = 1921181671612125518L;
	
	String saveName;
	String gemType;
	String gemSpot;
	int storylineLocation;

	public Player(String saveName, String gemType, String gemSpot, int[] stats){
		
		super(stats);
		
		this.saveName = saveName;
		this.gemType = gemType;
		this.gemSpot = gemSpot;
		this.storylineLocation = stats[14];
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
		return storylineLocation;
	}

	public void setLocation(int location) {
		this.storylineLocation = location;
	}
	
}
