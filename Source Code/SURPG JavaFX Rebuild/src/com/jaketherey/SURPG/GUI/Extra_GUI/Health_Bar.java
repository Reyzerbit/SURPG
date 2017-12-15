package com.jaketherey.SURPG.GUI.Extra_GUI;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.Game_Objects.Player;

import javafx.scene.control.ProgressBar;

public class Health_Bar extends ProgressBar{

	private static final String[] colorStyles = {"green-bar", "yellow-bar", "red-bar"};
	double percentage;
	
	public Health_Bar(Player player) {
		
		percentage = ((double)player.getVal("currentHP")/(double)player.getVal("maxHP"));
		
	}
	
	public void refreshBar(Player player) {
		
		percentage = ((double)player.getVal("currentHP")/(double)player.getVal("maxHP"));
		
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
			SURPG_Core.runError();
		}
		
		this.setProgress(percentage);
		
	}
	
}
