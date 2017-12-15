package com.jaketherey.SURPG.Game_Objects;

import java.io.Serializable;

public class Player extends Entity implements Serializable{
	
	private static final long serialVersionUID = 1921181671612125518L;
	
	String saveName;
	String gemType;
	String gemSpot;

	public Player(String saveName, String gemType, String gemSpot, int[] stats){
		
		super(stats);
		
		this.saveName = saveName;
		this.gemType = gemType;
		this.gemSpot = gemSpot;
		
	}
	
}
