/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.GUI;

import java.util.logging.Level;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.Entities.Player;
import com.jaketherey.SURPG.EventHandlers.NameSelectEventHandler;
import com.jaketherey.SURPG.IO.SURPGLogger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

public class GUI_Saves {
	
	/*
	 * viewSaves() launches and initiates the GUI to select
	 * a save state to read from.
	 */
	public static void viewSaves() {
		
		//View List
		ObservableList<Player> obsList;
		
		try {
			obsList = FXCollections.observableArrayList(Core.LOADED_SAVES);
		}catch(Exception e) {
			obsList = FXCollections.observableArrayList();
		}
		
		//GUI Stuffs
		Stage stage = new Stage();
		ImageView view = new ImageView(new Image(GUI_Saves.class.getResourceAsStream("/resources/images/SaveBackground.png")));
		VBox layout = new VBox();
		HBox buttonLay = new HBox();
		VBox nameLay = new VBox();
		StackPane pane = new StackPane(view, layout, nameLay);
		Scene scene = new Scene(pane);
		Button newPlayer = new Button("New Player");
		Button select = new Button("Play");
		Button nameSelect = new Button("Enter");
		Label nameInstruct = new Label("  Enter the name for your new save:  ");
		TextField nameArea = new TextField();
		ListView<Player> playersList = new ListView<Player>(obsList);
		
		playersList.setCellFactory(new Callback<ListView<Player>, ListCell<Player>>() {
		    @Override
		    public ListCell<Player> call(ListView<Player> param) {
		         ListCell<Player> cell = new ListCell<Player>() {
		             @Override
		            protected void updateItem(Player player, boolean empty) {
		                super.updateItem(player, empty);
		                if(player != null) {
		                    setText(player.getSaveName());
		                } else {
		                    setText(null);
		                }
		            }
		         };
		        return cell;
		    }
		});
		
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setWidth(440);
		stage.setHeight(400);
		stage.initStyle(StageStyle.TRANSPARENT);
		
		pane.setId("TransparentBackground");
		scene.setFill(null);
		
		scene.getStylesheets().add("resources/styling/Saves_CSS.css");
		newPlayer.setId("Button");
		select.setId("Button");
		nameSelect.setId("Button");
		nameLay.setAlignment(Pos.CENTER);
		nameLay.setPadding(new Insets(20, 20, 0, 20));
		nameLay.setSpacing(20);
		nameLay.setVisible(false);
		layout.setAlignment(Pos.TOP_CENTER);
		layout.setPadding(new Insets(120, 20, 20, 20));
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
		
		playersList.setOnMouseClicked(new EventHandler<MouseEvent>() {

		    @Override
		    public void handle(MouseEvent click) {

		        if(click.getClickCount() == 2) {
		        	
		        		try {
		        			
		        			if(!playersList.getSelectionModel().getSelectedItem().equals(null)) {
		        		
		        				SURPGLogger.logger.log(Level.INFO, "Loading save: " + playersList.getSelectionModel().getSelectedItem());
		        				Core.CURRENT_PLAYER = playersList.getSelectionModel().getSelectedItem();
		        				Core.init();
		        				stage.close();
		        			
		        			}
		        			
		        		}catch(NullPointerException e) {
		        			
		        			SURPGLogger.logger.log(Level.INFO, "Invalid save selection.");
		        			
		        		}
		        			
		        }
		    }
		});
		
		newPlayer.setOnAction(e -> {

			layout.setVisible(false);
			nameLay.setVisible(true);
	
		});
		
		nameSelect.setOnAction(new NameSelectEventHandler(nameArea, nameInstruct, stage));
		
		select.setOnAction(e -> {

			SURPGLogger.logger.log(Level.INFO, "Loading save: " + playersList.getSelectionModel().getSelectedItem().getSaveName());
			Core.CURRENT_PLAYER = playersList.getSelectionModel().getSelectedItem();
			Core.init();
			stage.close();
	
		});
		
	}

}
