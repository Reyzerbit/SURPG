package com.jaketherey.SURPG.GUI.Main.SubSections;

import com.jaketherey.SURPG.GUI.Main.GUI_Utils;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class OutputPane extends Pane {
	
	TextArea outputWindow = new TextArea();
	
	/**
	 * Output pane for the Main GUI.
	 */
	public OutputPane() {
		
		//General constraints
		outputWindow.setWrapText(true);
		outputWindow.setEditable(false);
		GUI_Utils.setSize(this, 280, 440);
		
		//Placing objects
		this.getChildren().add(outputWindow);
		
		//Dynamic resizing
		outputWindow.widthProperty().addListener(event -> outputWindow.setStyle("-fx-font-size: " + outputWindow.getWidth()/35));
		GUI_Utils.fitToRegion(this, outputWindow);
		
		//Run later
		Platform.runLater(() -> outputWindow.setStyle("-fx-font-size: " + outputWindow.getWidth()/35));
	}
	
	public TextArea getOutWindow() {
		return outputWindow;
	}

}
