/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Items;

import java.io.Serializable;

import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Item implements Serializable{
	
	private static final long serialVersionUID = 192118160709200513L;
	
	public String itemName;
	public String description;
	
	public Item(String itemName, String description) {
		
		this.itemName = itemName;
		this.description = description;
		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static void clearSelect(Pane pane, TextArea text) {
		pane.getChildren().remove(0);
		pane.getChildren().add(text);
	}

}
