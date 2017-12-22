package com.jaketherey.SURPG;

import java.util.logging.Level;

import com.jaketherey.SURPG.GUI.GUI_Main;
import com.jaketherey.SURPG.GUI.Extra_GUI.Menu_Control;
import com.jaketherey.SURPG.GUI.Extra_GUI.Opener_Icon;
import com.jaketherey.SURPG.IO.Saves_Handler;
import com.jaketherey.SURPG.IO.Storyline;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SURPG_Launcher extends Application{

	//Launch Application
	public static void main(String[] args) {
		
		//Init Logger
		SURPG_Core.initLogger();
		
		//Init Font
		SURPG_Core.logger.log(Level.INFO, "Loading font...");
		Font.loadFont(GUI_Main.class.getResourceAsStream("/resources/styling/crewniverse_font.ttf"), 14);
		SURPG_Core.logger.log(Level.INFO, "Font loaded.");
		
		SURPG_Core.logger.log(Level.INFO, "Launching game.");
		launch(args);
		
	}

	//Start Method
	@Override
	public void start(Stage stage){
		
		//SURPG Icon
		Opener_Icon.start();
		
		//Wait
		Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        //After wait, select load file.
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                Saves_Handler.loadMainSaves();
                Opener_Icon.closeIcon();
            }
        });
        new Thread(sleeper).start();
		
	}
	
	@Override
	public void stop() {
		
		SURPG_Core.logger.log(Level.INFO, "Closing game...");
		
	}
	
	//Launch Main Game
	public static void initSURPG() {
		
		//Init GUI
		SURPG_Core.MAIN_GUI = new GUI_Main();
			
		//Begin Story Reader
		Storyline.initReader();
		Storyline.initRun("");

		//Menu Control
		Platform.runLater(() -> {
			Menu_Control.initMenus();
		});
		
	}

}
