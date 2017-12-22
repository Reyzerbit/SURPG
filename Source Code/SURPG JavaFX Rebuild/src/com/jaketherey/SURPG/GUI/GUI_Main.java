package com.jaketherey.SURPG.GUI;

import java.util.logging.Level;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.GUI.Extra_GUI.Health_Bar;
import com.jaketherey.SURPG.IO.Storyline;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI_Main {
	
	//STAGE
	Stage stage = new Stage();
	
	//PANES
	Pane infoPane = new Pane();
	Pane centerScene = new Pane();
	Pane centTopScene = new Pane();
	Pane hoverScene = new Pane();
	Pane leftScene = new Pane();
	Pane rightScene = new Pane();
	
	//LABELS
	
	//Health Bar
	StackPane healthCont = new StackPane();
	Health_Bar healthBar = new Health_Bar(SURPG_Core.CURRENT_PLAYER);
	
	//Stat Labels
	Label healthPointLabel = new Label();
	Label strengthPoints = new Label("Str: " + SURPG_Core.CURRENT_PLAYER.getStrength());
	Label physicalPoints = new Label("PS: " + SURPG_Core.CURRENT_PLAYER.getPhysStrength());
	Label willPoints = new Label("Will: " + SURPG_Core.CURRENT_PLAYER.getWill());
	Label endurancePoints = new Label("End: " + SURPG_Core.CURRENT_PLAYER.getEndurance());
	Label intelPoints = new Label("Int: " + SURPG_Core.CURRENT_PLAYER.getIntelligence());
	Label communicationPoints = new Label("Com: " + SURPG_Core.CURRENT_PLAYER.getCommunication());
	Label problemSolvePoints = new Label("PrS: " + SURPG_Core.CURRENT_PLAYER.getProbSolve());
	Label insightPoints = new Label("Ins: " + SURPG_Core.CURRENT_PLAYER.getInsight());
	Label agilPoints = new Label("Agil: " + SURPG_Core.CURRENT_PLAYER.getAgility());
	Label precisionPoints = new Label("Prec: " + SURPG_Core.CURRENT_PLAYER.getPrecission());
	Label athleticsPoints = new Label("Ath: " + SURPG_Core.CURRENT_PLAYER.getAthletics());
	Label balancePoints = new Label("Bal: " + SURPG_Core.CURRENT_PLAYER.getBalance());
	
	//Character Panel
	Label characterPanel = new Label();
	
	//VALUES
	private Insets inset = new Insets(20);
	private Border border = new Border(new BorderStroke(Paint.valueOf("#653531"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(5)));
	
	//TEXT AREAS
	TextArea hover = new TextArea("Hover over a feature in the box above for a description about it's purpose.");
	TextArea outputWindow = new TextArea();
	
	//TEXT FIELDS
	TextField inputWindow = new TextField();
	
	//BUTTONS
	Button enter = new Button("Enter");
	Button statsButton = new Button("Stats");
	Button characterButton = new Button("Character");
	Button itemsButton = new Button("Items");
	
	//IMAGES
	Image characterImage = new Image(GUI_Main.class.getResourceAsStream("/resources/images/CharacterPic.png"));
	
	//LAYOUTS
	BorderPane totalLay = new BorderPane();
	private VBox rightLay = new VBox();
	private VBox midLay = new VBox();
	private HBox topCenter = new HBox();
	private HBox mainLay = new HBox();
	private GridPane statsLayout = new GridPane();
	
	//GridPane Column Constraints
	ColumnConstraints col1 = new ColumnConstraints();
	ColumnConstraints col2 = new ColumnConstraints();
	ColumnConstraints col3 = new ColumnConstraints();
	ColumnConstraints col4 = new ColumnConstraints();
	
	//Background Thing
	Background backing = new Background(new BackgroundFill(Paint.valueOf("red"), CornerRadii.EMPTY, new Insets(0)));
	
	//SCENES
	Scene mainScene = new Scene(totalLay, 800, 550);
	
	public GUI_Main() {
		
		SURPG_Core.logger.log(Level.INFO, "Initiating main GUI...");
		
		//Stage Constraints
		stage.setMinHeight(550);
		stage.setMinWidth(800);
		stage.setTitle("SURPG");
		stage.setScene(mainScene);
		
		//Scene Constraints
		mainScene.getStylesheets().add("resources/styling/SURPG_CSS.css");
		
		//Full Layout Constraints
		totalLay.setCenter(mainLay);
		totalLay.setId("MainBackground");
		
		//Run Laters
		Platform.runLater(() -> {
			stage.centerOnScreen();
		    healthBar.refreshBar(SURPG_Core.CURRENT_PLAYER);
		    outputWindow.setStyle("-fx-font-size: " + outputWindow.getWidth()/35);
			hover.setStyle("-fx-font-size: " + hover.getWidth()/35);
			rightScene.setMinWidth(mainScene.getWidth()/7);
		});
		
		//Font Resizing
		mainScene.widthProperty().addListener( event -> {
			outputWindow.setStyle("-fx-font-size: " + outputWindow.getWidth()/35);
			hover.setStyle("-fx-font-size: " + hover.getWidth()/35);
			rightScene.setMinWidth(mainScene.getWidth()/7);
        });
		
		//Set Pane Sizes
		setSize(infoPane, 280, 210);
		setSize(centTopScene, 320, 50);
		setSize(hoverScene, 280, 60);
		setSize(leftScene, 280, 440);
		
		//Column Constraints
		col1.setPercentWidth(25);
		col2.setPercentWidth(25);
		col3.setPercentWidth(25);
		col4.setPercentWidth(25);
		
		//Output Window Constraints
		outputWindow.setWrapText(true);
		outputWindow.setEditable(false);
		outputWindow.setBorder(border);
		
		//Input Window Constraints
		inputWindow.setMinWidth(180);
		inputWindow.prefWidthProperty().bind(topCenter.widthProperty());
		inputWindow.setBorder(border);
		
		//Enter Button Constraints
		enter.setAlignment(Pos.CENTER);
		enter.setMinWidth(80);
		
		//Hover Panel Constraints
		hover.setEditable(false);
		hover.setWrapText(true);
		hover.setBorder(border);
		
		//Right Scene Constraints
		rightScene.setMinWidth(100);
		
		//Side Buttons
		buttonCons(statsButton);
		buttonCons(characterButton);
		buttonCons(itemsButton);
		
		//Stats Pane Constraints
		infoPane.setStyle("-fx-background-color: #d3d3d3");
		infoPane.setBorder(border);
		
		//Health Container Constraints
		healthCont.setMinHeight(30);
		
		//Set Padding and Spacing
		topCenter.setSpacing(20);
		mainLay.setSpacing(20);
		mainLay.setPadding(inset);
		midLay.setSpacing(20);
		rightLay.setSpacing(10);
		rightLay.setPadding(new Insets(80, 10, 0, 10));
		statsLayout.setPadding(new Insets(10, 10, 10, 10));
		
		//Stats Work
		statsLayout.setHgap(10);
		statsLayout.setVgap(10);
		GridPane.setColumnSpan(healthCont, 2);
		GridPane.setRowIndex(healthCont, 0);
		GridPane.setColumnIndex(healthCont, 0);
		healthCont.setBorder(border);
		statsLayout.getChildren().add(healthCont);
		healthCont.getChildren().addAll(healthBar, new Text("HP: "));
		
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
		
		//Add Nodes
		mainLay.getChildren().addAll(leftScene, centerScene, rightScene);
		leftScene.getChildren().add(outputWindow);
		centerScene.getChildren().add(midLay);
		midLay.getChildren().addAll(centTopScene, infoPane, hoverScene);
		infoPane.getChildren().add(statsLayout);
		statsLayout.getColumnConstraints().addAll(col1, col2, col3, col4);
		centTopScene.getChildren().add(topCenter);
		topCenter.getChildren().addAll(inputWindow, enter);
		hoverScene.getChildren().add(hover);
		rightScene.getChildren().add(rightLay);
		rightLay.getChildren().addAll(statsButton, characterButton, itemsButton);
		
		//Fit to Pane
		fitToNode(leftScene, outputWindow);
		fitToNode(mainLay,leftScene);
		fitToNode(hoverScene, hover);
		fitToNode(centerScene, midLay);
		fitToNode(midLay, hoverScene);
		fitToNode(mainLay, centerScene);
		fitToNode(infoPane, statsLayout);
		fitToNode(healthCont, healthBar);
		fitToNode(rightScene, rightLay);
		StackPane.setAlignment(healthBar, Pos.CENTER_LEFT);
		topCenter.prefWidthProperty().bind(centTopScene.widthProperty());
			
		//Request Focus for Input Window
		inputWindow.requestFocus();
			
		//LISTENERS FOR HOVERS
					
		//Health Points Hover Listener
		addHoverListener(healthCont, "Your health starts at ten, and will slowly raise as you level up.");
			
		//Strength Points Hover Listener
		addHoverListener(strengthPoints, "Your strength starts at zero, and will grow as you complete events and level up. "
				+ "It is a total of your Physical Strength (PS), your Will, and your Endurance (End).");
		addHoverListener(physicalPoints, "Your physical strength points indicate how strong you are physically. "
				+ "These values are used to calculate damage done during combat");
		addHoverListener(willPoints, "Your will is your mental health, determining how sane you are, "
				+ "as well how easy it is to corrupt you and your mind.");
		addHoverListener(endurancePoints, "Your endurance is your ability to withstand specific taxing events, both emotionally and physically. "
				+ "It is used in game to calculate how long you can stand up against pressure, be it mental or physical");
			
		//Intelligence Points Hover Listener
		addHoverListener(intelPoints, "Your intelligence starts at zero, and will grow as you complete events and level up.");
		addHoverListener(communicationPoints, "Your communication points determine how efective you are at persuading, "
				+ "bargaining, and communicating with other entities.");
		addHoverListener(problemSolvePoints, "Your problem solving points determine how easy it is to solve puzzles, hack terminals, and pick locks.");
		addHoverListener(insightPoints, "Your insight points determine how observant you are, and how easily you notice things that "
				+ "are out of the ordinary.");
			
		//Agility Points Hover Listener
		addHoverListener(agilPoints, "Your agility starts at zero, and will grow as you complete events and level up.");
		addHoverListener(precisionPoints, "Your precision points determine how accurate you are, including with long range weapons.");
		addHoverListener(athleticsPoints, "Your athletics points determine your ability to out-run and out-maneuver enemies or other obstacles.");
		addHoverListener(balancePoints, "Your balance points determine your ability to stay balanced in combat. Higher balance points make "
				+ "it harder for you to be knocked prone.");
			
		//Character Picture Hover Listener
		addHoverListener(characterPanel, "This is your character picture. It shows your gem location, as well as armor and weapons equiped.");
		
		//Add Listener for Stats Button
		statsButton.setOnAction(e -> {
								
			statsLayout.setVisible(true);
							
		});
						
		//Add Listener for Stats Button
		characterButton.setOnAction(e -> {
			
			statsLayout.setVisible(false);
			
		});
			
		//Action Listener for Items
			
		itemsButton.setOnAction(e -> {
							
			statsLayout.setVisible(false);
								
		});
			
		//Add Listener for Enter Button
		enter.setOnAction(e -> {
				
			Storyline.initRun(inputWindow.getText());
		
		});
		
		//Add Listener for hitting enter key while typing.
		inputWindow.setOnKeyPressed(new EventHandler<KeyEvent>() {
			 
		    @Override
		    public void handle(KeyEvent event) {
		        if(event.getCode().equals(KeyCode.ENTER)) {
		        	
		        		Storyline.initRun(inputWindow.getText());
		        		
		        }
		    }
		    
		});
		
		stage.show();
		SURPG_Core.logger.log(Level.INFO, "Main GUI initiated.");
		
	}

	private void addHoverListener(StackPane pane, String text1) {
		
		pane.setOnMouseEntered(event -> hover.setText(text1));
		pane.setOnMouseExited(event -> hover.setText("Hover over a feature in the box above for a description about it's purpose."));
		
	}

	//Method to add hover event
	private void addHoverListener(Label label, String text1){
		
		label.setOnMouseEntered(event -> hover.setText(text1));
		label.setOnMouseExited(event -> hover.setText("Hover over a feature in the box above for a description about it's purpose."));
		
	}
	
	private void setSize(Region node, int width, int height) {
		
		node.setPrefSize(width, height);
		node.setMinSize(width-40, height);
		
		
	}
	
	private void fitToNode(Region node1, Region node2) {
		
		node2.prefWidthProperty().bind(node1.widthProperty());
		node2.prefHeightProperty().bind(node1.heightProperty());
		
	}
	
	private void setStatBlock(Label label, String hexColor, int columnIndex, int rowIndex) {

		label.setStyle("-fx-background-color: " + hexColor);
		
		label.setPrefSize(Double.MAX_VALUE, 30);
		label.setBorder(border);
		label.setAlignment(Pos.CENTER);
		label.setTextFill(Paint.valueOf("black"));
		GridPane.setRowIndex(label, rowIndex);
		GridPane.setColumnIndex(label, columnIndex);
		statsLayout.getChildren().add(label);
		
	}
	
	private void buttonCons(Button btn) {
		
		btn.setMinWidth(80);
		btn.setMinHeight(30);
		btn.setId("Button");
		btn.prefWidthProperty().bind(rightLay.widthProperty());
		
		Platform.runLater(() -> {
		    btn.setStyle("-fx-font-size: " + btn.getWidth()/11);
		});
		
		mainScene.widthProperty().addListener( event -> {
			btn.setStyle("-fx-font-size: " + btn.getWidth()/11);
        });
		
	}
	
	public void reloadLabels() {
		
		strengthPoints.setText("Str: " + SURPG_Core.CURRENT_PLAYER.getStrength());
		physicalPoints.setText("PS: " + SURPG_Core.CURRENT_PLAYER.getPhysStrength());
		willPoints.setText("Will: " + SURPG_Core.CURRENT_PLAYER.getWill());
		endurancePoints.setText("End: " + SURPG_Core.CURRENT_PLAYER.getEndurance());
		intelPoints.setText("Int: " + SURPG_Core.CURRENT_PLAYER.getIntelligence());
		communicationPoints.setText("Com: " + SURPG_Core.CURRENT_PLAYER.getCommunication());
		problemSolvePoints.setText("PrS: " + SURPG_Core.CURRENT_PLAYER.getProbSolve());
		insightPoints.setText("Ins: " + SURPG_Core.CURRENT_PLAYER.getInsight());
		agilPoints.setText("Agil: " + SURPG_Core.CURRENT_PLAYER.getAgility());
		precisionPoints.setText("Prec: " + SURPG_Core.CURRENT_PLAYER.getPrecission());
		athleticsPoints.setText("Ath: " + SURPG_Core.CURRENT_PLAYER.getAthletics());
		balancePoints.setText("Bal: " + SURPG_Core.CURRENT_PLAYER.getBalance());
		
	}
	
	public void addTopMenu(MenuBar menuBar) {
		
		totalLay.setTop(menuBar);
		
	}
	
	public void append(String string) {
		
		outputWindow.appendText(string);
		
	}
	
	public void clearInput() {
		
		inputWindow.setText("");
		
	}
	
	public void close() {
		
		stage.close();
		
	}
	
	public void show() {
		
		stage.show();
		
	}

}
