package com.jaketherey.SURPG.GUI.Main;

import com.jaketherey.SURPG.Core;

import javafx.scene.Parent;
import javafx.scene.layout.Region;

public class GUI_Utils {
	
	/**
	 * Sets minimum and recommended size for a JavaFX region.
	 * @param region R to get set.
	 * @param width Width for region to be set to.
	 * @param height Height for region to be set to.
	 */
	public static void setSize(Region region, int width, int height) {
		region.setPrefSize(width, height);
		region.setMinSize(width-40, height);
	}

	/**
	 * Binds one region's size to that of another, for dynamic resizing.
	 * @param regionA Parent region.
	 * @param regionB Region being bound to parent region.
	 */
	public static void fitToRegion(Region regionA, Region regionB) {
		regionB.prefWidthProperty().bind(regionA.widthProperty());
		regionB.prefHeightProperty().bind(regionA.heightProperty());
	}
	
	/**
	 * Appends text to the output window of Main GUI.
	 * @param string String to append to output window.
	 */
	public static void append(String string) {
		Core.MAIN_GUI.getOutPane().getOutWindow().appendText(string);
	}
	
	/**
	 * Clears input window.
	 */
	public static void clearInput() {
		Core.MAIN_GUI.getCenterPane().getInPane().getInputWindow().setText("");
	}
	
	public static void selectButton(boolean stats, boolean items) {
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().setVisible(stats);
		Core.MAIN_GUI.getCenterPane().getInfoPane().getItemsPane().setVisible(items);
		clearItemsSelect();
	}
	
	/**
	 * Adds hover listeners to different JavaFX parents.
	 * @param item JavaFX Parent to get a hover listener.
	 * @param text The text that will appear when the item is hovered over.
	 */
	public static void addHoverListener(Parent item, String text){
		item.setOnMouseEntered(event -> Core.MAIN_GUI.getCenterPane().getHoverPane().getHoverText().setText(text));
		item.setOnMouseExited(event -> Core.MAIN_GUI.getCenterPane().getHoverPane()
				.getHoverText().setText("Hover over a feature in the box above for a description about it's purpose."));
	}
	
	/**
	 * Reloads stat labels in the Main GUI. Called every time a stat is updated.
	 */
	public static void reloadLabels() {
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getStrengthPoints().setText("Str: " + Core.CURRENT_PLAYER.getStrength());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getPhysicalPoints().setText("PS: " + Core.CURRENT_PLAYER.getPhysStrength());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getWillPoints().setText("Will: " + Core.CURRENT_PLAYER.getWill());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getEndurancePoints().setText("End: " + Core.CURRENT_PLAYER.getEndurance());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getIntelPoints().setText("Int: " + Core.CURRENT_PLAYER.getIntelligence());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getCommunicationPoints().setText("Com: " + Core.CURRENT_PLAYER.getCommunication());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getProblemSolvePoints().setText("PrS: " + Core.CURRENT_PLAYER.getProbSolve());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getInsightPoints().setText("Ins: " + Core.CURRENT_PLAYER.getInsight());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getAgilPoints().setText("Agil: " + Core.CURRENT_PLAYER.getAgility());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getPrecisionPoints().setText("Prec: " + Core.CURRENT_PLAYER.getPrecision());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getAthleticsPoints().setText("Ath: " + Core.CURRENT_PLAYER.getAthletics());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getBalancePoints().setText("Bal: " + Core.CURRENT_PLAYER.getBalance());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getHealthText().setText("HP: " + Core.CURRENT_PLAYER.getCurrentHP());
		Core.MAIN_GUI.getCenterPane().getInfoPane().getStatsPane().getHealthBar().refreshBar(Core.CURRENT_PLAYER);
		Core.MAIN_GUI.getCenterPane().getInfoPane().getItemsPane().getItemsList().refresh();
	}
	
	/**
	 * Clears the selection values of the items list.
	 */
	public static void clearItemsSelect() {
		Core.MAIN_GUI.getCenterPane().getInfoPane().getItemsPane().getItemsList().getSelectionModel().clearSelection();
		Core.MAIN_GUI.getCenterPane().getHoverPane().getChildren().remove(0);
		Core.MAIN_GUI.getCenterPane().getHoverPane().getChildren().add(Core.MAIN_GUI.getCenterPane().getHoverPane().getHoverText());
	}
	
}
