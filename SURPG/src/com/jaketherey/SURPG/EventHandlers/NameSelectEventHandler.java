/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.EventHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.IO.SURPGLogger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * A class that implements the {@link javafx.event.EventHandler},
 * called when a user creates a new save state to check if the name is valid.
 * @author Jacob Batista
 * @since 1.0
 * @see javafx.event.EventHandler
 * @see javafx.event.Event
 */
public class NameSelectEventHandler implements EventHandler<ActionEvent>{
	
	TextField nameArea;
	Label nameInstruct;
	Stage stage;
	
	public NameSelectEventHandler(TextField nameArea, Label nameInstruct, Stage stage) {
		this.nameArea = nameArea;
		this.nameInstruct = nameInstruct;
		this.stage = stage;
	}

	/**
	 * Overriden method from {@link javafx.event.EventHandler}. Checks if chosen name is blank or not.
	 */
	@Override
	public void handle(ActionEvent event) {

		if(nameArea.getText().equals("")) {
			nameInstruct.setText("  Name is invalid.  ");
		}else {
			newSaveName();
		}
	}

	/**
	 * Run the process to generate a new save name for a new save file.
	 * @since 1.0
	 */
	private void newSaveName() {
		
		List<String> tempNameList = new ArrayList<String>();
		try {
			for(int x = 0; x < Core.LOADED_SAVES.length; x++) {
				tempNameList.add((String) Core.LOADED_SAVES[x].getSaveName());
			}
		}catch(Exception e1) {
			SURPGLogger.logger.log(Level.INFO, "There are no loaded saves to check for duplicates");
		}
		
		if(tempNameList.contains(nameArea.getText())) {
			runDuplicateAlert();
		}else {
			createNewSaveName();
		}
	}

	/**
	 * Creates a new player with the requested save name and initiates the game.
	 * @since 1.0
	 */
	private void createNewSaveName() {
		SURPGLogger.logger.log(Level.INFO, "Creating new save with name: " + nameArea.getText());
		Core.CURRENT_PLAYER = Core.genNewPlayer(nameArea.getText());
		Core.init();
		stage.close();
	}

	/**
	 * Runs an alert system that tells the user that the name already exists.
	 * @since 1.0
	 */
	private void runDuplicateAlert() {
		Alert duplicate = new Alert(AlertType.CONFIRMATION);
		duplicate.setTitle("Duplicate Save");
		duplicate.setHeaderText(null);
		duplicate.setContentText("This save name already exists. Overwrite it?");
		Optional<ButtonType> result = duplicate.showAndWait();
		if (result.get() == ButtonType.OK){
			SURPGLogger.logger.log(Level.INFO, "Overwriting existing save.");
			Core.CURRENT_PLAYER = Core.genNewPlayer(nameArea.getText());
			Core.init();
			stage.close();
		} else {
			nameArea.setText("");
		}
	}

}
