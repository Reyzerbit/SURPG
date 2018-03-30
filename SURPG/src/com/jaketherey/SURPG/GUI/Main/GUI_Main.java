/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.GUI.Main;

import java.util.logging.Level;

import com.jaketherey.SURPG.GUI.Main.SubSections.OutputPane;
import com.jaketherey.SURPG.GUI.Main.SubSections.RightButtonPane;
import com.jaketherey.SURPG.GUI.Main.SubSections.Center.CenterPane;
import com.jaketherey.SURPG.IO.SURPGLogger;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GUI_Main extends Stage{
	
	/*.setBackground(new Background(new BackgroundFill(Paint.valueOf("blue"), new CornerRadii(0), new Insets(0))));*/
	
	//Scene
	Scene mainScene;
	
	//Layouts
	BorderPane totalLayout = new BorderPane();
	HBox mainLayout = new HBox();
	
	//Layout Segments (Panes)
	CenterPane centerPane = new CenterPane();
	OutputPane outputPane = new OutputPane();
	RightButtonPane buttonsPane = new RightButtonPane();
	
	/*
	//WIP
	Label characterPanel = new Label();
	Image characterImage = new Image(GUI_Main.class.getResourceAsStream("/resources/images/CharacterPic.png"));
	*/
	
	/**
	 * Upon calling a new GUI_Main(), is creates and displays
	 * a new Main GUI for the core of the game.
	 */
	public GUI_Main() {
		
		SURPGLogger.logger.log(Level.INFO, "Initiating main GUI...");

		/* WIP
		GUI_Utils.addHoverListener(characterPanel, LongStr.characterPanel);
		*/
		
		//General constraints
		initMainScene();
		initLayouts();
		
		//Placing objects
		mainLayout.getChildren().addAll(outputPane, centerPane, buttonsPane);

		//Dynamic resizing
		GUI_Utils.fitToRegion(mainLayout,outputPane);
		GUI_Utils.fitToRegion(mainLayout, centerPane);
		this.widthProperty().addListener(event -> {
			buttonsPane.setMinWidth(this.getWidth()/7);
        });
		
		//Run later
		Platform.runLater(() -> {
			buttonsPane.setMinWidth(this.getWidth()/7);
			this.centerOnScreen();
			GUI_Utils.reloadLabels();
		});
		
		//Upon completing main GUI construction...
		centerPane.getInPane().getInputWindow().requestFocus();
		SURPGLogger.logger.log(Level.INFO, "Main GUI initiated.");
		
	}

	/**
	 * Initiates the two layouts used for the main GUI.
	 */
	private void initLayouts() {
		totalLayout.setCenter(mainLayout);
		totalLayout.setStyle("-fx-background-color: #FCC8DF");
		mainLayout.setSpacing(20);
		mainLayout.setPadding(new Insets(20));
	}

	/**
	 * Initiates and builds the main scene which populates the stage.
	 */
	private void initMainScene() {
		mainScene = new Scene(totalLayout, 800, 550);
		mainScene.getStylesheets().add("resources/styling/SURPG_CSS.css");
		this.setMinHeight(550);
		this.setMinWidth(800);
		this.setTitle("SURPG");
		this.setScene(mainScene);
	}
	
	/**
	 * Adds a menu bar to the top of the Main GUI. Called if not on a Mac PC.
	 * @param menuBar Menu bar to add to the top of the Main GUI.
	 */
	public void addTopMenu(MenuBar menuBar) {
		totalLayout.setTop(menuBar);
	}
	
	public OutputPane getOutPane() {
		return outputPane;
	}
	
	public CenterPane getCenterPane() {
		return centerPane;
	}
	
	public RightButtonPane getButtonPane() {
		return buttonsPane;
	}

}
