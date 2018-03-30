package com.jaketherey.SURPG.GUI;

import com.jaketherey.SURPG.IO.SURPGLogger;
import com.jaketherey.SURPG.IO.Saves_Handler;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Opener_Icon{
	
	private static Stage stage;
	private static Task<Void> sleeper;
	private static Scene mainScene;

	/**
	 * Initializes the opening icon.
	 */
	private static void initIconGUI() {
        buildImage();
		buildStage();
        stage.show();
	}

	/**
	 * Prepares the stage for the image.
	 */
	private static void buildStage() {
		stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setWidth(369);
		stage.setHeight(300);
		stage.setScene(mainScene);
		stage.centerOnScreen();
		stage.requestFocus();
	}

	/**
	 * Prepares the image to be displayed as the starting icon.
	 */
	private static void buildImage() {
		Image image = new Image(Opener_Icon.class.getResourceAsStream("/resources/images/CookieCatLogo.png"));
        ImageView view = new ImageView();
        view.setFitHeight(300);
        view.setFitWidth(369);
        view.setImage(image);
        Group main = new Group(view);
		mainScene = new Scene(main);
		mainScene.setFill(Color.TRANSPARENT);
	}

	/**
	 * Initiates the launch of the starting icon.
	 */
	public static void runIcon() {
		initIconGUI();
		initIconThread();
        setThreadFinish();
        new Thread(sleeper).start();
	}
	
	/**
	 * Creates the sub-thread for the icon.
	 */
	private static void initIconThread() {
		sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                		SURPGLogger.runError(e);
                }
                return null;
            }
        };
	}

	/**
	 * Tells the thread what to do once it is finished.
	 */
	private static void setThreadFinish() {
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				Saves_Handler.loadSaveData();
				stage.close();
            }
        });
	}
	
}
