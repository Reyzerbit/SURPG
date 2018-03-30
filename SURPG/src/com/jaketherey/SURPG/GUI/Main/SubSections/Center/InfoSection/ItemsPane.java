package com.jaketherey.SURPG.GUI.Main.SubSections.Center.InfoSection;

import java.util.logging.Level;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.GUI.Main.GUI_Utils;
import com.jaketherey.SURPG.IO.SURPGLogger;
import com.jaketherey.SURPG.Items.UseableItem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

public class ItemsPane extends Pane {
	
	//Lists
	ObservableList<UseableItem> itemsList = FXCollections.observableArrayList(Core.CURRENT_PLAYER.getItems());
	ListView<UseableItem> itemsListView = new ListView<UseableItem>(itemsList);
	
	public ItemsPane() {
		
		//General constraints
		this.setVisible(false);
		GUI_Utils.fitToRegion(this, itemsListView);
		
		//Placing objects
		this.getChildren().add(itemsListView);
		
		//Listeners
		addOnItemsSelectListener();
		
		//Cell Factory
		initItemListCellFactory();
	}

	/**
	 * Adds listeners to list, to activate when a selection is made.
	 */
	private void addOnItemsSelectListener() {
		itemsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				try {
					UseableItem tempItem = itemsListView.getSelectionModel().getSelectedItem();
					tempItem.onSelect(tempItem, Core.MAIN_GUI.getCenterPane().getHoverPane(), Core.CURRENT_PLAYER, itemsList);
				}catch(NullPointerException e) {
					SURPGLogger.logger.log(Level.INFO, "No item to select.");
				}
			}
		});
	}

	/**
	 * Make the list view display the name of the item instead of the Item Java Object.
	 */
	private void initItemListCellFactory() {
		itemsListView.setCellFactory(new Callback<ListView<UseableItem>, ListCell<UseableItem>>() {
		    @Override
		    public ListCell<UseableItem> call(ListView<UseableItem> param) {
		         ListCell<UseableItem> cell = new ListCell<UseableItem>() {
		             @Override
		            protected void updateItem(UseableItem item, boolean empty) {
		                super.updateItem(item, empty);
		                if(item != null) {
		                    setText(item.getItemName());
		                } else {
		                    setText(null);
		                }
		            }
		         };
		        return cell;
		    }
		});
	}
	
	public ListView<UseableItem> getItemsList() {
		return itemsListView;
	}

}
