package com.jaketherey.SURPG.GUI.Main.SubSections.Center.InfoSection;

import com.jaketherey.SURPG.GUI.Main.GUI_Utils;

import javafx.scene.layout.Pane;

public class CenterDataPane extends Pane {
	
	StatsPane statsPane = new StatsPane();
	ItemsPane itemsPane = new ItemsPane();

	public CenterDataPane() {
		
		//General constraints
		this.setStyle("-fx-background-color: #fffef2");
		
		//Placing objects
		this.getChildren().addAll(statsPane, itemsPane);
		
		//Dynamic resizing
		GUI_Utils.fitToRegion(this, statsPane);
		GUI_Utils.fitToRegion(this, itemsPane);
		GUI_Utils.setSize(this, 280, 210);
		
	}
	
	public StatsPane getStatsPane() {
		return statsPane;
	}
	
	public ItemsPane getItemsPane() {
		return itemsPane;
	}
	
}
