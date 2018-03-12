/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.Items;

import java.io.Serializable;

import com.jaketherey.SURPG.Entities.Entity;

import javafx.collections.ObservableList;

public class UseableItem extends Item implements DynamicItem, Serializable{
	
	private static final long serialVersionUID = 1921181607211905L;

	public UseableItem(String itemName, String description) {
		super(itemName, description);
	}

	@Override
	public void onUse(Entity target, ObservableList<UseableItem> container) {
	}

}
