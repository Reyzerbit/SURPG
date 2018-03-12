/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Items;

import java.io.Serializable;

public class BoostValue implements Serializable {
	
	private static final long serialVersionUID = 2L;
	
	String statToBoost;
	int boostValue;
	
	public BoostValue(String statToBoost, int boostValue) {
		this.statToBoost = statToBoost;
		this.boostValue = boostValue;
	}

	public String getStatToBoost() {
		return statToBoost;
	}

	public void setStatToBoost(String statToBoost) {
		this.statToBoost = statToBoost;
	}

	public int getBoostValue() {
		return boostValue;
	}

	public void setBoostValue(int boostValue) {
		this.boostValue = boostValue;
	}

}
