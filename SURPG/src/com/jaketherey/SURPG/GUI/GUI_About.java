/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI_About {
	
	Stage stage;
	Scene scene;
	VBox layout;
	HBox bar;
	Text about;
	Pane top;
	Label exit;
	
	public GUI_About() {
		
		stage = new Stage();
		layout = new VBox();
		scene = new Scene(layout);
		about = new Text("Copyright \u00a9 Jacob Batista 2017 - Present\n\n"
				+ "All Steven Universe related characters, images, and sounds:\n"
				+ "Copyright \u00a9 Cartoon Network and Rebecca Sugar\n"
				+ "2013 - Present");
		top = new Pane();
		exit = new Label();
		bar = new HBox();
		
		stage.setResizable(false);
		stage.setWidth(440);
		stage.setHeight(200);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		
		scene.getStylesheets().add("resources/styling/About_CSS.css");
		scene.setFill(null);
		
		bar.setAlignment(Pos.CENTER_RIGHT);
		bar.setPrefSize(440, 50);
		bar.setSpacing(10);
		bar.setPadding(new Insets(0, 40, 0, 0));
		
		top.setPrefSize(440, 50);
		
		exit.setMinSize(25, 25);
		exit.setMaxSize(25, 25);
		exit.setTextAlignment(TextAlignment.CENTER);
		exit.setId("Exit");
		
		layout.setPadding(new Insets(10));
		layout.setSpacing(10);
		layout.setAlignment(Pos.CENTER);
		layout.setId("Background");
		
		about.setTextAlignment(TextAlignment.CENTER);

		top.getChildren().add(bar);
		bar.getChildren().add(exit);
		layout.getChildren().addAll(top, about);
		
		exit.setOnMouseClicked(e -> {
			
			stage.close();
			
		});
		
	}
	
	public void show() {
		
		stage.show();
		
	}
	
	public void close() {
		
		stage.close();
		
	}

}
