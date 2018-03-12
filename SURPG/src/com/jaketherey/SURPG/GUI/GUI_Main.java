/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.GUI;

import java.util.logging.Level;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.GUI.Extra_GUI.Health_Bar;
import com.jaketherey.SURPG.IO.SURPGLogger;
import com.jaketherey.SURPG.IO.Storyline;
import com.jaketherey.SURPG.Items.Item;
import com.jaketherey.SURPG.Items.UseableItem;
import com.jaketherey.SURPG.Misc.LongStr;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

public class GUI_Main {
	
	//SCENES
	Scene mainScene;
	
	//STAGE
	Stage stage = new Stage();
	
	//LAYOUTS
	BorderPane totalLayout = new BorderPane();
	private VBox buttonsLayout = new VBox();
	private VBox centerLayout = new VBox();
	private HBox inputLayout = new HBox();
	private HBox mainLayout = new HBox();
	private GridPane statsLayout = new GridPane();
	
	//GRIDPANE COLUMNS
	ColumnConstraints col1 = new ColumnConstraints();
	ColumnConstraints col2 = new ColumnConstraints();
	ColumnConstraints col3 = new ColumnConstraints();
	ColumnConstraints col4 = new ColumnConstraints();
	
	//PANES
	Pane infoPane = new Pane();
	Pane centerPane = new Pane();
	Pane inputPane = new Pane();
	Pane hoverPane = new Pane();
	Pane outputPane = new Pane();
	Pane buttonsPane = new Pane();
	Pane itemsPane = new Pane();
	
	//STATS LABELS
	Label healthPointLabel = new Label();
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
	
	//BUTTONS
	Button enterButton = new Button("Enter");
	Button statsButton = new Button("Stats");
	Button characterButton = new Button("Character");
	Button itemsButton = new Button("Items");
	
	//TEXT AREAS
	TextArea hover = new TextArea("Hover over a feature in the box above for a description about it's purpose.");
	TextArea outputWindow = new TextArea();
	
	//TEXT FIELDS
	TextField inputWindow = new TextField();
	
	//TEXTS
	Text hp = new Text("HP: " + Core.CURRENT_PLAYER.getCurrentHP());
	
	//HEALTH BAR
	StackPane healthCont = new StackPane();
	Health_Bar healthBar = new Health_Bar(Core.CURRENT_PLAYER);
	
	//LISTS
	ObservableList<UseableItem> itemsList = FXCollections.observableArrayList(Core.CURRENT_PLAYER.getItems());
	ListView<UseableItem> itemsListView = new ListView<UseableItem>(itemsList);
	
	//CHARACTER PANEL
	Label characterPanel = new Label();
	
	//IMAGES
	Image characterImage = new Image(GUI_Main.class.getResourceAsStream("/resources/images/CharacterPic.png"));
	
	/**
	 * Constructor:
	 * Upon calling a new GUI_Main(), is creates and displays
	 * a new Main GUI for the core of the game.
	 */
	public GUI_Main() {
		
		SURPGLogger.logger.log(Level.INFO, "Initiating main GUI...");
		
		//To run after GUI is built.
		Platform.runLater(() -> {
		    runLaters();
		});
		
		initMainScene();
		initLayouts();
		initPanes();
		initStatLabels();
		initButtons();
		initTextAreas();
		initHealthBar();
		initItemListCellFactory();
		addListeners();
		initDynamicResizing();
		buildLayouts();
		
		inputWindow.requestFocus();
		SURPGLogger.logger.log(Level.INFO, "Main GUI initiated.");
		
	}

	/**
	 * 
	 */
	private void runLaters() {
		outputWindow.setStyle("-fx-font-size: " + outputWindow.getWidth()/35);
		hover.setStyle("-fx-font-size: " + hover.getWidth()/35);
		stage.centerOnScreen();
		buttonsPane.setMinWidth(mainScene.getWidth()/7);
		reloadLabels();
	}

	/**
	 * 
	 */
	private void addOnItemsSelectListener() {
		itemsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent click) {
				try {
					UseableItem tempItem = itemsListView.getSelectionModel().getSelectedItem();
					tempItem.onSelect(tempItem, hoverPane, Core.CURRENT_PLAYER, itemsList);
				}catch(NullPointerException e) {
					SURPGLogger.logger.log(Level.INFO, "No item to select.");
					SURPGLogger.runError(e);
				}
			}
		});
	}

	/**
	 * 
	 */
	private void addListeners() {
		addStatHoverListeners();
		addButtonListeners();
		addOnEnterListener();
		addOnItemsSelectListener();
	}

	/**
	 * 
	 */
	private void addOnEnterListener() {
		//Add Listener for hitting enter key while typing.
		inputWindow.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
	    				Storyline.runWithValue(inputWindow.getText());
				}
			}
		});
	}

	/**
	 * 
	 */
	private void addButtonListeners() {
		statsButton.setOnAction(e -> {
			statsLayout.setVisible(true);
			itemsPane.setVisible(false);
			clearItemsSelect();
		});
		characterButton.setOnAction(e -> {
			statsLayout.setVisible(false);
			itemsPane.setVisible(false);
			clearItemsSelect();
		});
		itemsButton.setOnAction(e -> {
			statsLayout.setVisible(false);
			itemsPane.setVisible(true);		
		});
		enterButton.setOnAction(e -> {
			Storyline.runWithValue(inputWindow.getText());
		});
	}

	/**
	 * 
	 */
	private void addStatHoverListeners() {
		addHoverListener(healthCont, LongStr.healthCont);
		addHoverListener(strengthPoints, LongStr.strengthPoints);
		addHoverListener(physicalPoints, LongStr.physicalPoints);
		addHoverListener(willPoints, LongStr.willPoints);
		addHoverListener(endurancePoints, LongStr.endurancePoints);
		addHoverListener(intelPoints, LongStr.intelPoints);
		addHoverListener(communicationPoints, LongStr.communicationPoints);
		addHoverListener(problemSolvePoints, LongStr.problemSolvePoints);
		addHoverListener(insightPoints, LongStr.insightPoints);
		addHoverListener(agilPoints, LongStr.agilPoints);
		addHoverListener(precisionPoints, LongStr.precisionPoints);
		addHoverListener(athleticsPoints, LongStr.athleticsPoints);
		addHoverListener(balancePoints, LongStr.balancePoints);
		addHoverListener(characterPanel, LongStr.characterPanel);
	}

	/**
	 * 
	 */
	private void buildLayouts() {
		mainLayout.getChildren().addAll(outputPane, centerPane, buttonsPane);
		outputPane.getChildren().add(outputWindow);
		centerPane.getChildren().add(centerLayout);
		centerLayout.getChildren().addAll(inputPane, infoPane, hoverPane);
		infoPane.getChildren().addAll(statsLayout, itemsPane);
		statsLayout.getColumnConstraints().addAll(col1, col2, col3, col4);
		inputPane.getChildren().add(inputLayout);
		inputLayout.getChildren().addAll(inputWindow, enterButton);
		hoverPane.getChildren().add(hover);
		buttonsPane.getChildren().add(buttonsLayout);
		buttonsLayout.getChildren().addAll(statsButton, characterButton, itemsButton);
		itemsPane.getChildren().add(itemsListView);
		statsLayout.getChildren().add(healthCont);
	}

	/**
	 * 
	 */
	private void initDynamicResizing() {
		fitToRegion(outputPane, outputWindow);
		fitToRegion(mainLayout,outputPane);
		fitToRegion(hoverPane, hover);
		fitToRegion(centerPane, centerLayout);
		fitToRegion(centerLayout, hoverPane);
		fitToRegion(mainLayout, centerPane);
		fitToRegion(infoPane, statsLayout);
		fitToRegion(healthCont, healthBar);
		fitToRegion(buttonsPane, buttonsLayout);
		fitToRegion(infoPane, itemsListView);
		inputLayout.prefWidthProperty().bind(inputPane.widthProperty());
		mainScene.widthProperty().addListener(event -> {
			outputWindow.setStyle("-fx-font-size: " + outputWindow.getWidth()/35);
			hover.setStyle("-fx-font-size: " + hover.getWidth()/35);
			buttonsPane.setMinWidth(mainScene.getWidth()/7);
        });
	}

	/**
	 * 
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

	/**
	 * 
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
	 * 
	 */
	private void initTextAreas() {
		outputWindow.setWrapText(true);
		outputWindow.setEditable(false);
		hover.setEditable(false);
		hover.setWrapText(true);
		inputWindow.setMinWidth(180);
		inputWindow.prefWidthProperty().bind(inputLayout.widthProperty());
	}

	/**
	 * 
	 */
	private void initButtons() {
		enterButton.setAlignment(Pos.CENTER);
		enterButton.setMinWidth(80);
		buttonCons(statsButton);
		buttonCons(characterButton);
		buttonCons(itemsButton);
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
	private void initPanes() {
		itemsPane.setVisible(false);
		buttonsPane.setMinWidth(100);
		infoPane.setStyle("-fx-background-color: #fffef2");
		setSize(infoPane, 280, 210);
		setSize(inputPane, 320, 50);
		setSize(hoverPane, 280, 60);
		setSize(outputPane, 280, 440);
	}

	/**
	 * 
	 */
	private void initLayouts() {
		totalLayout.setCenter(mainLayout);
		totalLayout.setStyle("-fx-background-color: #FCC8DF");
		statsLayout.setId("BorderedPane");
		statsLayout.setPadding(new Insets(10, 10, 10, 10));
		statsLayout.setHgap(10);
		statsLayout.setVgap(10);
		mainLayout.setSpacing(20);
		mainLayout.setPadding(new Insets(20));
		buttonsLayout.setSpacing(10);
		buttonsLayout.setPadding(new Insets(80, 10, 0, 10));
		inputLayout.setSpacing(20);
		centerLayout.setSpacing(20);
		
		col1.setPercentWidth(25);
		col2.setPercentWidth(25);
		col3.setPercentWidth(25);
		col4.setPercentWidth(25);
	}

	/**
	 * 
	 */
	private void initMainScene() {
		mainScene = new Scene(totalLayout, 800, 550);
		mainScene.getStylesheets().add("resources/styling/SURPG_CSS.css");
		stage.setMinHeight(550);
		stage.setMinWidth(800);
		stage.setTitle("SURPG");
		stage.setScene(mainScene);
	}
	
	/**
	 * Adds hover listeners to different JavaFX parents.
	 * @param item JavaFX Parent to get a hover listener.
	 * @param text The text that will appear when the item is hovered over.
	 */
	private void addHoverListener(Parent item, String text){
		item.setOnMouseEntered(event -> hover.setText(text));
		item.setOnMouseExited(event -> hover.setText("Hover over a feature in the box above for a description about it's purpose."));
	}
	
	/**
	 * Sets minimum and recommended size for a JavaFX region.
	 * @param region R to get set.
	 * @param width Width for region to be set to.
	 * @param height Height for region to be set to.
	 */
	private void setSize(Region region, int width, int height) {
		region.setPrefSize(width, height);
		region.setMinSize(width-40, height);
	}
	
	/**
	 * Binds one region's size to that of another, for dynamic resizing.
	 * @param regionA Parent region.
	 * @param regionB Region being bound to parent region.
	 */
	private void fitToRegion(Region regionA, Region regionB) {
		regionB.prefWidthProperty().bind(regionA.widthProperty());
		regionB.prefHeightProperty().bind(regionA.heightProperty());
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
		statsLayout.getChildren().add(label);
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
		mainScene.widthProperty().addListener( event -> {
			btn.setStyle("-fx-font-size: " + btn.getWidth()/11);
        });
	}
	
	/**
	 * Reloads stat labels in the Main GUI. Called every time a stat is updated.
	 */
	public void reloadLabels() {
		strengthPoints.setText("Str: " + Core.CURRENT_PLAYER.getStrength());
		physicalPoints.setText("PS: " + Core.CURRENT_PLAYER.getPhysStrength());
		willPoints.setText("Will: " + Core.CURRENT_PLAYER.getWill());
		endurancePoints.setText("End: " + Core.CURRENT_PLAYER.getEndurance());
		intelPoints.setText("Int: " + Core.CURRENT_PLAYER.getIntelligence());
		communicationPoints.setText("Com: " + Core.CURRENT_PLAYER.getCommunication());
		problemSolvePoints.setText("PrS: " + Core.CURRENT_PLAYER.getProbSolve());
		insightPoints.setText("Ins: " + Core.CURRENT_PLAYER.getInsight());
		agilPoints.setText("Agil: " + Core.CURRENT_PLAYER.getAgility());
		precisionPoints.setText("Prec: " + Core.CURRENT_PLAYER.getPrecision());
		athleticsPoints.setText("Ath: " + Core.CURRENT_PLAYER.getAthletics());
		balancePoints.setText("Bal: " + Core.CURRENT_PLAYER.getBalance());
		hp.setText("HP: " + Core.CURRENT_PLAYER.getCurrentHP());
		healthBar.refreshBar(Core.CURRENT_PLAYER);
		itemsList = FXCollections.observableArrayList(Core.CURRENT_PLAYER.getItems());
		itemsListView = new ListView<UseableItem>(itemsList);
		itemsListView.refresh();
	}
	
	/**
	 * Adds a menu bar to the top of the Main GUI. Called if not on a Mac PC.
	 * @param menuBar Menu bar to add to the top of the Main GUI.
	 */
	public void addTopMenu(MenuBar menuBar) {
		totalLayout.setTop(menuBar);
	}
	
	/**
	 * Appends text to the output window of Main GUI.
	 * @param string String to append to output window.
	 */
	public void append(String string) {
		outputWindow.appendText(string);
	}
	
	/**
	 * Clears input window.
	 */
	public void clearInput() {
		inputWindow.setText("");
	}
	
	/**
	 * Closes the Main GUI.
	 */
	public void close() {
		stage.close();
	}
	
	/**
	 * Shows the Main GUI.
	 */
	public void show() {
		stage.show();
	}
	
	/**
	 * Returns the Main GUI.
	 * @return
	 */
	public Stage getStage() {
		return stage;
	}
	
	/**
	 * Clears the selection values of the items list.
	 */
	public void clearItemsSelect() {
		itemsListView.getSelectionModel().clearSelection();
		Item.clearSelect(hoverPane, hover);
	}

}
