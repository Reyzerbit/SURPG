/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG;

import java.util.logging.Level;

import com.jaketherey.SURPG.GUI.GUI_Main;
import com.jaketherey.SURPG.GUI.Extra_GUI.Opener_Icon;
import com.jaketherey.SURPG.IO.Commands;
import com.jaketherey.SURPG.IO.SURPGLogger;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Launcher extends Application{
	
	public static void main(String[] args) {
		SURPGLogger.initLogger();
		Commands.interpret(args);
		initFont();
		launchGame(args);
	}

	/**
	 * Launches the game application. Calls the "launch" method
	 * inherited from javafx.application.Application.
	 * @param args The arguments passed to the "launch" method
	 * from the main method.
	 */
	public static void launchGame(String[] args) {
		SURPGLogger.logger.log(Level.INFO, "Launching game.");
		launch(args);
	}

	/**
	 * Initiates the font for the game GUI.
	 */
	private static void initFont() {
		SURPGLogger.logger.log(Level.INFO, "Loading font...");
		Font.loadFont(GUI_Main.class.getResourceAsStream("/resources/styling/crewniverse_font.ttf"), 14);
		SURPGLogger.logger.log(Level.INFO, "Font loaded.");
	}
	
	/**
	 * Inherited from JavaFX, called on application startup.
	 * Runs the opener icon.
	 */
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage stage){
		Opener_Icon.runIcon();
	}
	
	/**
	 * Inherited from JavaFX, called on application startup.
	 * Called on application closure.
	 */
	/*
	 * (non-Javadoc)
	 * @see javafx.application.Application#stop()
	 */
	@Override
	public void stop() {
		SURPGLogger.logger.log(Level.INFO, "Closing game...");
	}

}
