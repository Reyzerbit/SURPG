package com.jaketherey.SURPG.GUI.Extra_GUI;

import java.util.logging.Level;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.Game_Objects.Player;

import javafx.scene.control.ProgressBar;

public class Health_Bar extends ProgressBar{

	private static final String[] colorStyles = {"green-bar", "yellow-bar", "red-bar"};
	double percentage;
	
	public Health_Bar(Player player) {
		
		percentage = ((double)player.getCurrentHP()/(double)player.getMaxHP());
		
	}
	
	public void refreshBar(Player player) {
		
		percentage = ((double)player.getCurrentHP()/(double)player.getMaxHP());
		
		if(percentage > 0.4 && percentage <= 1) {
			
			this.getStyleClass().removeAll(colorStyles);
			this.getStyleClass().add("green-bar");
			
		}else if(percentage <= 0.4 && percentage > 0.1) {
			
			this.getStyleClass().removeAll(colorStyles);
			this.getStyleClass().add("yellow-bar");
			
		}else if(percentage <= 0.1 && percentage > 0) {
			
			this.getStyleClass().removeAll(colorStyles);
			this.getStyleClass().add("red-bar");
			
		}else {
			SURPG_Core.logger.log(Level.WARNING, "Health Bar Error: Please send generated log file to Jake the Rey!\n");
		}
		
		this.setProgress(percentage);
		
	}
	
}
