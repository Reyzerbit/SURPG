package com.jaketherey.SURPG.GUI.Extra_GUI;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Opener_Icon{
	
	private static Stage stage;

	public static void start() {
		
		stage = new Stage();
		
        Image image = new Image(Opener_Icon.class.getResourceAsStream("/resources/images/CookieCatLogo.png"));
        
        ImageView view = new ImageView();
        view.setFitHeight(300);
        view.setFitWidth(369);
        view.setImage(image);
        
        Group main = new Group(view);
		
		Scene mainScene = new Scene(main);
		mainScene.setFill(Color.TRANSPARENT);
		
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setWidth(369);
		stage.setHeight(300);
		stage.setScene(mainScene);
		stage.centerOnScreen();
		stage.show();
		
	}
	
	public static void closeIcon() {
		
		stage.close();
		
	}
	
}
