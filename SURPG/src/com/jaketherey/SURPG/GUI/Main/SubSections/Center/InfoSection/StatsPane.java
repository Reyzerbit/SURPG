package com.jaketherey.SURPG.GUI.Main.SubSections.Center.InfoSection;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.GUI.Main.GUI_Utils;
import com.jaketherey.SURPG.Misc.LongStr;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class StatsPane extends GridPane {
	
	//Column Constraints
	ColumnConstraints col1 = new ColumnConstraints();
	ColumnConstraints col2 = new ColumnConstraints();
	ColumnConstraints col3 = new ColumnConstraints();
	ColumnConstraints col4 = new ColumnConstraints();
	
	//Health Bar Stuff
	Text hp = new Text("HP: " + Core.CURRENT_PLAYER.getCurrentHP());
	StackPane healthCont = new StackPane();
	HealthBar healthBar = new HealthBar(Core.CURRENT_PLAYER);
	
	//Stats Labels
	Label strengthPoints = new Label("Str: " + Core.CURRENT_PLAYER.getStrength());
	Label physicalPoints = new Label("PS: " + Core.CURRENT_PLAYER.getPhysStrength());
	Label willPoints = new Label("Will: " + Core.CURRENT_PLAYER.getWill());
	Label endurancePoints = new Label("End: " + Core.CURRENT_PLAYER.getEndurance());
	Label intelPoints = new Label("Int: " + Core.CURRENT_PLAYER.getIntelligence());
	Label communicationPoints = new Label("Com: " + Core.CURRENT_PLAYER.getCommunication());
	Label problemSolvePoints = new Label("PrS: " + Core.CURRENT_PLAYER.getProbSolve());
	Label insightPoints = new Label("Ins: " + Core.CURRENT_PLAYER.getInsight());
	Label agilPoints = new Label("Agil: " + Core.CURRENT_PLAYER.getAgility());
	Label precisionPoints = new Label("Prec: " + Core.CURRENT_PLAYER.getPrecision());
	Label athleticsPoints = new Label("Ath: " + Core.CURRENT_PLAYER.getAthletics());
	Label balancePoints = new Label("Bal: " + Core.CURRENT_PLAYER.getBalance());
	
	public StatsPane() {
		
		//Column Constraints
		col1.setPercentWidth(25);
		col2.setPercentWidth(25);
		col3.setPercentWidth(25);
		col4.setPercentWidth(25);
		this.getColumnConstraints().addAll(col1, col2, col3, col4);
		
		initStatLabels();
		
		//General Constraints
		GUI_Utils.addHoverListener(healthCont, LongStr.healthCont);
		GUI_Utils.fitToRegion(healthCont, healthBar);
		this.getChildren().add(healthCont);
		this.setId("BorderedPane");
		this.setPadding(new Insets(10, 10, 10, 10));
		this.setHgap(10);
		this.setVgap(10);
		
		//Other Inits
		initHealthBar();
		addStatHoverListeners();
	}

	/**
	 * Initiates and sets the constraints for the health bar.
	 */
	private void initHealthBar() {
		healthCont.setMinHeight(30);
		healthCont.getChildren().addAll(healthBar, hp);
		StackPane.setAlignment(healthBar, Pos.CENTER_LEFT);
		GridPane.setColumnSpan(healthCont, 2);
		GridPane.setRowIndex(healthCont, 0);
		GridPane.setColumnIndex(healthCont, 0);
	}

	/**
	 * Sets a label as a stat label.
	 * @param label The label to be turned into a stat label.
	 * @param hexColor Hexadecimal color of background.
	 * @param columnIndex Column index.
	 * @param rowIndex Row index.
	 */
	private void setStatBlock(Label label, String hexColor, int columnIndex, int rowIndex) {
		label.setStyle("-fx-background-color: " + hexColor);
		label.setId("StatsLabel");
		label.setPrefSize(Double.MAX_VALUE, 30);
		GridPane.setRowIndex(label, rowIndex);
		GridPane.setColumnIndex(label, columnIndex);
		this.getChildren().add(label);
	}

	/**
	 * 
	 */
	private void initStatLabels() {
		setStatBlock(strengthPoints, "#af0505", 0, 2);
		setStatBlock(physicalPoints, "#d65151", 1, 2);
		setStatBlock(willPoints, "#d65151", 2, 2);
		setStatBlock(endurancePoints, "#d65151", 3, 2);
		setStatBlock(intelPoints, "#af0505", 0, 3);
		setStatBlock(communicationPoints, "#d65151", 1, 3);
		setStatBlock(problemSolvePoints, "#d65151", 2, 3);
		setStatBlock(insightPoints, "#d65151", 3, 3);
		setStatBlock(agilPoints, "#af0505", 0, 4);
		setStatBlock(precisionPoints, "#d65151", 1, 4);
		setStatBlock(athleticsPoints, "#d65151", 2, 4);
		setStatBlock(balancePoints, "#d65151", 3, 4);
	}

	/**
	 * 
	 */
	private void addStatHoverListeners() {
		GUI_Utils.addHoverListener(strengthPoints, LongStr.strengthPoints);
		GUI_Utils.addHoverListener(physicalPoints, LongStr.physicalPoints);
		GUI_Utils.addHoverListener(willPoints, LongStr.willPoints);
		GUI_Utils.addHoverListener(endurancePoints, LongStr.endurancePoints);
		GUI_Utils.addHoverListener(intelPoints, LongStr.intelPoints);
		GUI_Utils.addHoverListener(communicationPoints, LongStr.communicationPoints);
		GUI_Utils.addHoverListener(problemSolvePoints, LongStr.problemSolvePoints);
		GUI_Utils.addHoverListener(insightPoints, LongStr.insightPoints);
		GUI_Utils.addHoverListener(agilPoints, LongStr.agilPoints);
		GUI_Utils.addHoverListener(precisionPoints, LongStr.precisionPoints);
		GUI_Utils.addHoverListener(athleticsPoints, LongStr.athleticsPoints);
		GUI_Utils.addHoverListener(balancePoints, LongStr.balancePoints);
	}

	public Label getStrengthPoints() {
		return strengthPoints;
	}

	public Label getPhysicalPoints() {
		return physicalPoints;
	}

	public Label getWillPoints() {
		return willPoints;
	}

	public Label getEndurancePoints() {
		return endurancePoints;
	}

	public Label getIntelPoints() {
		return intelPoints;
	}

	public Label getCommunicationPoints() {
		return communicationPoints;
	}

	public Label getProblemSolvePoints() {
		return problemSolvePoints;
	}

	public Label getInsightPoints() {
		return insightPoints;
	}

	public Label getAgilPoints() {
		return agilPoints;
	}

	public Label getPrecisionPoints() {
		return precisionPoints;
	}

	public Label getAthleticsPoints() {
		return athleticsPoints;
	}

	public Label getBalancePoints() {
		return balancePoints;
	}
	
	public HealthBar getHealthBar() {
		return healthBar;
	}
	
	public Text getHealthText() {
		return hp;
	}

}
