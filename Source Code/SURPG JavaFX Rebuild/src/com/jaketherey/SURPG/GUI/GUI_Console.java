package com.jaketherey.SURPG.GUI;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI_Console {
	
	public static void init() {
		
		Stage stage = new Stage();
		VBox layout = new VBox();
		Scene scene = new Scene(layout);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setWidth(400);
		stage.setHeight(600);
		stage.show();
		
		
	}

}
