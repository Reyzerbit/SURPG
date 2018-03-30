package com.jaketherey.SURPG.GUI.Main.SubSections.Center;

import com.jaketherey.SURPG.GUI.Main.GUI_Utils;
import com.jaketherey.SURPG.Items.Item;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class HoverPane extends Pane {
	
	TextArea hover = new TextArea("Hover over a feature in the box above for a description about it's purpose.");

	public HoverPane() {
		
		//General constraints
		hover.setStyle("-fx-font-size: " + hover.getWidth()/35);
		hover.setEditable(false);
		hover.setWrapText(true);
		hover.widthProperty().addListener(event -> hover.setStyle("-fx-font-size: " + hover.getWidth()/35));
		
		//Placing objects
		this.getChildren().add(hover);
		
		//Dynamic resizing
		GUI_Utils.fitToRegion(this, hover);
		GUI_Utils.setSize(this, 280, 60);
		
		//Run later
		Platform.runLater(() -> hover.setStyle("-fx-font-size: " + hover.getWidth()/35));
		
		//IDK why this is here.
		Item.clearSelect(this, hover);
		
	}
	
	public TextArea getHoverText() {
		return hover;
	}
	
}
