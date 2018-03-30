/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Items;

import com.jaketherey.SURPG.Entities.Entity;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public interface DynamicItem {
	
	Button use = new Button();
	
	default public void onSelect(Item item, Pane pane, Entity user, ObservableList<UseableItem> itemsList) {
		pane.getChildren().remove(0);
		VBox layout = new VBox();
		TextArea description = new TextArea(item.getDescription());
		pane.getChildren().add(layout);
		layout.prefWidthProperty().bind(pane.widthProperty());
		layout.prefHeightProperty().bind(pane.heightProperty());
		layout.setId("BorderedPane");
		layout.setPadding(new Insets(10));
		layout.setSpacing(10);
		description.setEditable(false);
		description.setWrapText(true);
		description.prefWidthProperty().bind(layout.widthProperty());
		description.prefHeightProperty().bind(layout.heightProperty());
		layout.getChildren().addAll(use, description);
		use.setOnAction(e ->{
			onUse(user, itemsList);
		});
	}
	
	public void onUse(Entity target, ObservableList<UseableItem> container);
	
	default public void rename(String name, Item item) {
		use.setText(name + " " + item.getItemName());
	}

}
