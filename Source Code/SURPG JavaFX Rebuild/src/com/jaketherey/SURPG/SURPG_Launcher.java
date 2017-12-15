package com.jaketherey.SURPG;

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
import javafx.stage.Stage;

public class SURPG_Launcher extends Application{

	//Launch Application
	public static void main(String[] args) {
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
	
	//Launch Main Game
	public static void initSURPG() {
			
		//Begin Story Reader
		Storyline.initReader();
		Storyline.initRun("");
			
		//Init GUI
		GUI_Main.initMainGUI();

		//Menu Control
		Platform.runLater(() -> {
			Menu_Control.initMenus();
		});
		
	}

}
