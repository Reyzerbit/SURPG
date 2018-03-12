/*
 * SURPG (c) Jacob Batista 2017
 * All Steven Universe related characters, sounds, and images are copyright Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.GUI;

import java.io.IOException;
import java.net.URISyntaxException;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.GUI.Extra_GUI.Opener_Icon;
import com.jaketherey.SURPG.IO.SURPGLogger;
import com.jaketherey.SURPG.Misc.Combat_Runnable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI_Combat{
	
	//Audio
	Media sound = null;
	MediaPlayer mediaPlayer;
	
	//Input stream
	Image barImage = new Image(GUI_Combat.class.getResourceAsStream("/resources/images/CombatBar.png"));
	
	//Images
	ImageView barView = new ImageView(barImage);
	Image back = new Image(Opener_Icon.class.getResourceAsStream("/resources/images/CombatBackground.png"));
	
	//Layouts
	HBox layout = new HBox();
	VBox layoutCombat = new VBox();
	GridPane fightLayout = new GridPane();
	GridPane mainSelectGrid = new GridPane();
	GridPane statsPane = new GridPane();
	
	//Stage and Scene
	Stage stage = new Stage();
	Scene scene = new Scene(layout);
	
	//Buttons
	Button fightButton = new Button("Fight");
	Button itemsButton = new Button("Items");
	Button fleeButton = new Button("Flee");
	Button abilitiesButton = new Button("Abilities");
	Button hitButton = new Button("Hit");
	Button cancelButton = new Button("Cancel");
	
	//Panes
	Pane top = new Pane();
	TabPane right = new TabPane();
	StackPane bottom = new StackPane();
	
	//Tabs
	Tab statsTab = new Tab();
	Tab itemsTab = new Tab();
	Tab abilitiesTab = new Tab();
	
	//Slider
	Slider combatSlider = new Slider(0, 100, 0);
	
	//Loop booleans
	boolean loop = true;
	
	//Integers
	
	//This integer represents the speed of the slider in milliseconds. 7 is for strong but slow enemies, 5 is for default enemies, 4 is for elite monsters.
	int stopSlider = 1;
	int damageInt = 0;
	long enemyHealth = 0;
	
	//Label
	Label enemyHealthLabel = new Label("Enemy HP: " + enemyHealth);
	Label healthLabel = new Label("HP: " + Core.CURRENT_PLAYER.getCurrentHP());
	
	//Moving Slider Thread
	Thread threadMove;
	
	public GUI_Combat(int speedNumber, int enemyHealth, int enemyStrength) throws IOException{
		
		//Sound
		
		try {
			sound = new Media(GUI_Combat.class.getResource("/resources/audio/Battle.wav").toURI().toString());
		} catch (URISyntaxException e1) {
			SURPGLogger.runError(e1);
		}
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
        
		//Slider Bar Constraints
        barView.setFitHeight(30);
        barView.setFitWidth(230);
        barView.setImage(barImage);
	    
        //Enemy Health
	    this.enemyHealth = enemyHealth;
	    
	    //Reload Labels
	    Core.MAIN_GUI.reloadLabels();
		
	    //Combat Slider Thread
		threadMove = new Thread(new Combat_Runnable(combatSlider, speedNumber));
		
		//Stage Constraints
		stage.setScene(scene);
		stage.setWidth(800);
		stage.setHeight(560);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setResizable(false);
		
		//Scene Constraints
		scene.getStylesheets().add("resources/styling/Combat_CSS.css");
		scene.setFill(null);
		
		//Slider Visuals
		fightButton.setId("Button");
		fleeButton.setId("Button");
		itemsButton.setId("Button");
		abilitiesButton.setId("Button");
		hitButton.setId("Button");
		cancelButton.setId("Button");
		//enemyWindow
		
		//Combat Pane Constraints
		top.setId("CombatWindow");
		
		//Main Layout
		layout.setId("MainBackground");
		layout.setPadding(new Insets(150, 0, 0, 0));
		
		//Layouts
		layoutCombat.setId("Layout");
		
		//Main Grid
		mainSelectGrid.setId("GridPanes");
		
		//Fight Grid
		fightLayout.setId("GridPanes");
		fightLayout.setVisible(false);
		GridPane.setColumnSpan(barView, 2);
		GridPane.setColumnSpan(combatSlider, 2);
		
		//Stats Grid
		statsPane.setId("GridPanes");
		statsPane.add(enemyHealthLabel, 0, 0);
		statsPane.add(healthLabel, 1, 0);
		
		bottom.setAlignment(Pos.CENTER);
		
		//Tabs and Tab Pane
		itemsTab.setClosable(false);
		statsTab.setClosable(false);
		statsTab.setContent(statsPane);
		abilitiesTab.setClosable(false);
		right.getTabs().addAll(statsTab, itemsTab, abilitiesTab);
		
		//Top Pane
		top.setId("CombatPane");
		
		layout.getChildren().addAll(layoutCombat, right);
		layoutCombat.getChildren().addAll(top, bottom);
		bottom.getChildren().addAll(mainSelectGrid, fightLayout);
		
		fightLayout.add(barView, 0, 0);
		fightLayout.add(combatSlider, 0, 1);
		fightLayout.add(hitButton, 0, 2);
		fightLayout.add(cancelButton, 1, 2);
		
		mainSelectGrid.add(fightButton, 0, 0);
		mainSelectGrid.add(itemsButton, 1, 0);
		mainSelectGrid.add(abilitiesButton, 0, 1);
		mainSelectGrid.add(fleeButton, 1, 1);
		
		stage.show();
		
		fightButton.setOnAction(e -> {

			combatSlider.setValue(0);
				
			mainSelectGrid.setVisible(false);
							
			fightLayout.setVisible(true);

			if(stopSlider == 1){
					
				stopSlider = 0;
						
			}
			
			threadMove.start();
					
		});
		
		/*hit.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
				
				hit.setEnabled(false);
				
				stopSlider = 1;
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				enemyHealthVar = enemyHealthVar - calculateDamage(combatSlider.getValue());
				Feats.resetStat();
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				cancel.doClick();
				
			}
					
		});
		
		combatGUI.addWindowListener(new WindowAdapter()
		{
		    public void windowClosed(WindowEvent e)
		    {
		    	
		    	PlaySoundBite.close();
		    	GUIContent.gui.setVisible(true);
		    	
		    }
		});
		
		cancel.addActionListener(new ActionListener() {

			@Override			
			public void actionPerformed(ActionEvent e) {
				
				flee.setVisible(true);
				fight.setVisible(true);
				abilities.setVisible(true);
				items.setVisible(true);
				
				hit.setVisible(false);
				cancel.setVisible(false);
				combatSlider.setVisible(false);
				combatPanel.setVisible(false);
				
				stopSlider = 1;
				
			}
					
		});*/
		
		fleeButton.setOnAction(e -> {

			mediaPlayer.stop();
			
			Core.MAIN_GUI.show();
			stage.close();
	
		});
		
	}
	
	private long calculateDamage(int percentage){
		
		long totalDamage = 0;
		
		if(percentage <= 25 || percentage >= 75){
			
			totalDamage = ((int) Math.floor(Core.CURRENT_PLAYER.getPhysStrength() * 0));
			
		}else if(percentage > 25 && percentage <= 40 || percentage < 75 && percentage >= 60){
			
			totalDamage = ((int) Math.floor(Core.CURRENT_PLAYER.getPhysStrength() * .25));
			
		}else if(percentage > 40 && percentage <= 48 || percentage < 60 && percentage >= 52){
			
			totalDamage = ((int) Math.floor(Core.CURRENT_PLAYER.getPhysStrength() * .75));
			
		}else if(percentage > 48 && percentage < 52){
			
			totalDamage = Core.CURRENT_PLAYER.getPhysStrength() * 1;
			
		}else{
			
			SURPGLogger.runError("Something went wrong.");
			
		}
		
		System.out.println(totalDamage);
		
		return totalDamage;
		
	}
	
	public void show() {
		
		stage.show();
		
	}
	
	public void close() {
		
		stage.close();
		
	}
	
}