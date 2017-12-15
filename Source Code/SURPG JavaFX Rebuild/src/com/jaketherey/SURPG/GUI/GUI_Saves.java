package com.jaketherey.SURPG.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.SURPG_Launcher;
import com.jaketherey.SURPG.Game_Objects.Player;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI_Saves {
	
	private static Player[] playerArray;
	
	public static void viewSaves(Player[] players) {

		//Players List
		playerArray = players;
		
		//View List
		ObservableList<String> obsList = FXCollections.observableArrayList(SURPG_Core.getPlayerNames(players));
		
		//GUI Stuffs
		Stage stage = new Stage();
		VBox layout = new VBox();
		HBox buttonLay = new HBox();
		VBox nameLay = new VBox();
		Scene scene = new Scene(new StackPane(layout, nameLay));
		Button newPlayer = new Button("New Player");
		Button select = new Button("Play");
		Button nameSelect = new Button("Enter");
		Label nameInstruct = new Label("Enter the name for your new save:");
		TextField nameArea = new TextField();
		ListView<String> playersList = new ListView<String>(obsList);
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setWidth(440);
		stage.setHeight(300);
		stage.initStyle(StageStyle.UTILITY);
		stage.setTitle("SAVES");
		
		nameLay.setAlignment(Pos.CENTER);
		nameLay.setPadding(new Insets(20, 20, 0, 20));
		nameLay.setSpacing(20);
		nameLay.setVisible(false);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(20, 20, 20, 20));
		buttonLay.setAlignment(Pos.CENTER);
		buttonLay.setPadding(new Insets(20, 20, 0, 20));
		buttonLay.setSpacing(200);
		
		playersList.setEditable(false);
		playersList.setMaxSize(350, 200);
		
		nameLay.getChildren().addAll(nameInstruct, nameArea, nameSelect);
		layout.getChildren().addAll(playersList, buttonLay);
		buttonLay.getChildren().addAll(newPlayer, select);
		
		stage.show();
		
		//Action Listeners
		newPlayer.setOnAction(e -> {

			layout.setVisible(false);
			nameLay.setVisible(true);
			stage.setHeight(180);
	
		});
		
		nameSelect.setOnAction(e -> {

			if(nameArea.getText().equals("")) {
				
				nameInstruct.setText("Name is invalid.");
				
			}else {
				
				List<String> tempNameList = new ArrayList<String>();
				
				for(int x = 0; x < playerArray.length; x++) {
					
					tempNameList.add((String) playerArray[x].getVal("saveName"));
					
				}
				
				if(tempNameList.contains(nameArea.getText())) {
					
					Alert duplicate = new Alert(AlertType.CONFIRMATION);
					duplicate.setTitle("Duplicate Save");
					duplicate.setHeaderText(null);
					duplicate.setContentText("This save name already exists. Overwrite it?");
					duplicate.initOwner(stage);
					
					Optional<ButtonType> result = duplicate.showAndWait();
					if (result.get() == ButtonType.OK){
						
					    SURPG_Core.removePlayer(playerArray, nameArea.getText());
						
						SURPG_Core.initNewPlayer(nameArea.getText());
						SURPG_Launcher.initSURPG();
						stage.close();
						
					} else {
					    
						nameArea.setText("");
						
					}
					
				}else {
					
					SURPG_Core.initNewPlayer(nameArea.getText());
					SURPG_Launcher.initSURPG();
					stage.close();
					
				}
				
			}
	
		});
		
		select.setOnAction(e -> {

			SURPG_Core.CURRENT_PLAYER = playerArray[playersList.getSelectionModel().getSelectedIndex()+1];
			SURPG_Launcher.initSURPG();
			stage.close();
	
		});
		
	}

}
