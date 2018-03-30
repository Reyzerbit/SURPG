package com.jaketherey.SURPG.GUI.Main.SubSections.Center;

import com.jaketherey.SURPG.GUI.Main.GUI_Utils;
import com.jaketherey.SURPG.IO.Storyline;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class InputPane extends Pane {
	
	private HBox inputLayout = new HBox();
	TextField inputWindow = new TextField();
	Button enterButton = new Button("Enter");
	
	public InputPane() {
		
		//General constraints
		inputWindow.setMinWidth(180);
		enterButton.setAlignment(Pos.CENTER);
		enterButton.setMinWidth(80);
		inputLayout.setSpacing(20);
		GUI_Utils.setSize(this, 320, 50);
		
		//Add objects
		this.getChildren().add(inputLayout);
		inputLayout.getChildren().addAll(inputWindow, enterButton);
		
		//Dynamic resizing
		inputWindow.prefWidthProperty().bind(inputLayout.widthProperty());
		inputLayout.prefWidthProperty().bind(this.widthProperty());
		
		//Listeners
		initListeners();
	}

	/**
	 * Initiates the listeners for the input pane.
	 */
	private void initListeners() {
		inputWindow.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ENTER)) {
				Storyline.runWithValue(inputWindow.getText());
			}
		});
		enterButton.setOnAction(e -> {
			Storyline.runWithValue(inputWindow.getText());
		});
	}
	
	public TextField getInputWindow() {
		return inputWindow;
	}

}
