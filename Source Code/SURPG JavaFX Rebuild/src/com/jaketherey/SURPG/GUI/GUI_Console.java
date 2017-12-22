package com.jaketherey.SURPG.GUI;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jaketherey.SURPG.SURPG_Core;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class GUI_Console {

	Stage stage;
	VBox layout;
	Scene scene;
	Button enter;
	public static TextArea window;
	private static Border border = new Border(new BorderStroke(Paint.valueOf("#653531"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));
	
	String[] tempArray;
	String tempString;
	String[] returnArray;
	Method method;
	
	GUI_Combat combat;
	
	public GUI_Console() {
		
		//Objects
		stage = new Stage();
		layout = new VBox();
		scene = new Scene(layout);
		window = new TextArea();
		enter = new Button("Enter");
		
		//Scene Constraints
		scene.getStylesheets().add("resources/styling/SURPG_CSS.css");
		
		layout.setId("MainBackground");
		enter.setId("Button");
		window.setBorder(border);
		window.setPrefSize(360, 500);
		window.setWrapText(true);
		enter.setPrefSize(100, 30);
		layout.setPadding(new Insets(20));
		layout.setSpacing(20);
		layout.setAlignment(Pos.CENTER);
		
		layout.getChildren().addAll(window, enter);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setWidth(400);
		stage.setHeight(600);
		stage.show();
		
		//Add Listener for hitting enter key while typing.
		window.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				if(event.getCode().equals(KeyCode.ENTER)) {
					
					runMethod(getLastLine());

				}

			}

		});
		
		enter.setOnAction(e -> {
			
			runMethod(getLastLine());
		
		});
		
	}
	
	public String[] getLastLine() {
		
		tempArray = window.getText().split("\\n");
		tempString = tempArray[tempArray.length-1];
		returnArray = tempString.split(" ");
		
		return returnArray;
		
	}
	
	public void runMethod(String[] args) {
		
		method = null;
		
		if(args[0].equals("")) {
			
			GUI_Console.window.appendText("Please enter a valid command.\n");
			
		}else if(args[0].substring(0, 3).equals("get")) {
			
			try {
				method = SURPG_Core.CURRENT_PLAYER.getClass().getMethod(args[0]);
			} catch (NoSuchMethodException | SecurityException e) {
				SURPG_Core.runError(e);
			}
			
			try {
				GUI_Console.window.appendText("\n" + method.invoke(SURPG_Core.CURRENT_PLAYER) + "\n");
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				SURPG_Core.runError(e);
			}
			
		}else if(args[0].substring(0, 3).equals("set") && !args[0].equals("setSaveName")) {
			
			if(args.length == 2) {
				
				try {
					method = SURPG_Core.CURRENT_PLAYER.getClass().getMethod(args[0], int.class);
					method.invoke(SURPG_Core.CURRENT_PLAYER, Integer.parseInt(args[1]));
				} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					GUI_Console.window.appendText("\nUnknown set command.\n");
				}
				
			}else {
				
				GUI_Console.window.appendText("\nCommand must be used as follows:\nsetValue (int valueToSet)\n");
				
			}
			
		}else if(args[0].equals("initCombat")) {
			
			try {
				combat = new GUI_Combat(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			} catch (NumberFormatException | IOException e) {
				SURPG_Core.runError(e);
			}
			
			stage.close();
			
		}else {
			
			GUI_Console.window.appendText("\nUnknown command, please try again.\n");
			
		}
		
		SURPG_Core.MAIN_GUI.reloadLabels();
		
	}
	
	public void close() {
		
		stage.close();
		
	}
	

}
