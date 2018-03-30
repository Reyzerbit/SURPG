package com.jaketherey.SURPG.GUI.Main.SubSections.Center;

import com.jaketherey.SURPG.GUI.Main.GUI_Utils;
import com.jaketherey.SURPG.GUI.Main.SubSections.Center.InfoSection.CenterDataPane;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CenterPane extends Pane {
	
	VBox centerLayout = new VBox();
	HoverPane hoverPane = new HoverPane();
	CenterDataPane infoPane = new CenterDataPane();
	InputPane inputPane = new InputPane();
	
	public CenterPane() {
		
		//General constraints
		centerLayout.setSpacing(20);
		centerLayout.getChildren().addAll(inputPane, infoPane, hoverPane);
		
		//Placing objects
		this.getChildren().add(centerLayout);
		
		//Dynamic resizing
		GUI_Utils.fitToRegion(this, centerLayout);
		GUI_Utils.fitToRegion(centerLayout, hoverPane);
		
	}
	
	public InputPane getInPane() {
		return inputPane;
	}
	
	public HoverPane getHoverPane() {
		return hoverPane;
	}
	
	public CenterDataPane getInfoPane() {
		return infoPane;
	}

}
