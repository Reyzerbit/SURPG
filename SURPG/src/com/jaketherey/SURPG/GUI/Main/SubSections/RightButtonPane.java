package com.jaketherey.SURPG.GUI.Main.SubSections;

import com.jaketherey.SURPG.GUI.Main.GUI_Utils;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RightButtonPane extends Pane {
	
	private VBox buttonsLayout = new VBox();
	Button statsButton = new Button("Stats");
	Button characterButton = new Button("Character");
	Button itemsButton = new Button("Items");
	
	public RightButtonPane() {
		
		//General constraints
		this.setMinWidth(100);
		buttonsLayout.setSpacing(10);
		buttonsLayout.setPadding(new Insets(80, 10, 0, 10));
		buttonCons(statsButton);
		buttonCons(characterButton);
		buttonCons(itemsButton);
		
		//Placing objects
		this.getChildren().add(buttonsLayout);
		buttonsLayout.getChildren().addAll(statsButton, characterButton, itemsButton);
		
		//Dynamic resizing
		GUI_Utils.fitToRegion(this, buttonsLayout);
		

		//Button actions
		statsButton.setOnAction(e -> GUI_Utils.selectButton(true, false));
		itemsButton.setOnAction(e -> GUI_Utils.selectButton(false, true));
		characterButton.setOnAction(e -> GUI_Utils.selectButton(false, false));
		
	}
	
	/**
	 * Sets a button to a specific set of parameters, used for the
	 * window selection buttons to the right.
	 * @param btn Button to be constrained.
	 */
	private void buttonCons(Button btn) {
		btn.setMinWidth(80);
		btn.setMinHeight(30);
		btn.prefWidthProperty().bind(buttonsLayout.widthProperty());
		Platform.runLater(() -> {
		    btn.setStyle("-fx-font-size: " + btn.getWidth()/11);
		});
		btn.widthProperty().addListener(event -> {
			btn.setStyle("-fx-font-size: " + btn.getWidth()/11);
        });
	}

}
