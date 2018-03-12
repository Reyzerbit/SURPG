/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Misc;

import java.util.logging.Level;

import com.jaketherey.SURPG.IO.SURPGLogger;

import javafx.scene.control.Slider;

public class Combat_Runnable implements Runnable {
	
	Slider combatSlider;
	int speedSlider;
	
	//Direction Enum
	enum Direction{ LEFT, RIGHT }
	
	public Combat_Runnable(Slider combatSlider, int speedSlider) {
		
		this.combatSlider = combatSlider;
		this.speedSlider = speedSlider;
		
	}

	@Override
	public void run() {
		
		Direction direction = Direction.RIGHT;
		
		while(true) {
			
			if(combatSlider.getValue() == 100) {
				
				direction = Direction.LEFT;
				combatSlider.setValue(combatSlider.getValue()-1);
				
			}else if(combatSlider.getValue() == 0) {
				
				direction = Direction.RIGHT;
				combatSlider.setValue(combatSlider.getValue()+1);
				
			}else if(direction == Direction.LEFT) {

				combatSlider.setValue(combatSlider.getValue()-1);
				
			}else if(direction == Direction.RIGHT) {
				
				combatSlider.setValue(combatSlider.getValue()+1);
				
			}else {
				
				SURPGLogger.logger.log(Level.INFO, "Something went wrong...");
				
			}
			
			try {
				Thread.sleep(speedSlider);
			} catch (InterruptedException e) {
				SURPGLogger.runError(e);
			}
			
		}
		
	}

}
