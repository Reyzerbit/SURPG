package com.jaketherey.SURPG.GUI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.GUI.Extra_GUI.Opener_Icon;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI_Combat{
	
	//0 is forward, 1 is backward
	int sliderDirection = 0;
	
	//Audio
	Media sound;
	MediaPlayer mediaPlayer;
	
	//Direction Enum
	enum Direction{ LEFT, RIGHT }
	
	//Input stream
	Image barImage = new Image(GUI_Combat.class.getResourceAsStream("/resources/images/CombatBar.png"));
	
	//Images
	ImageView barView = new ImageView(barImage);
	Image back = new Image(Opener_Icon.class.getResourceAsStream("/resources/images/CombatBackground.png"));
	
	//Layouts
	VBox layout = new VBox();
	GridPane fightLayout = new GridPane();
	GridPane grid = new GridPane();
	
	//Stage and Scene
	Stage stage = new Stage();
	Scene scene = new Scene(layout);
	
	//Buttons
	Button fight = new Button("Fight");
	Button items = new Button("Items");
	Button flee = new Button("Flee");
	Button abilities = new Button("Abilities");
	Button hit = new Button("Hit");
	Button cancel = new Button("Cancel");
	
	//Panes
	Pane top = new Pane();
	StackPane bottom = new StackPane();
	
	//Slider
	Slider combatSlider = new Slider(0, 100, 0);
	
	//Loop booleans
	boolean loop = true;
	
	//Integers
	
	//This integer represents the speed of the slider in milliseconds. 7 is for strong but slow enemies, 5 is for default enemies, 4 is for elite monsters.
	int speedSlider = 20;
	int stopSlider = 1;
	int damageInt = 0;
	long enemyHealthVar = 0;
	
	//JLabel
	Label enemyHealthLabel = new Label("Enemy HP: " + enemyHealthVar);
	
	//Moving Slider Thread
	Thread threadMove = new Thread(new Runnable(){

		@Override
		public void run() {
			
			Direction direction = Direction.RIGHT;
			
			while(loop) {
				
				if(combatSlider.getValue() == 100) {
					
					direction = Direction.LEFT;
					combatSlider.setValue(combatSlider.getValue()-1);
					
				}else if(combatSlider.getValue() == 0) {
					
					direction = Direction.RIGHT;
					combatSlider.setValue(combatSlider.getValue()+1);
					
				}else if(direction == Direction.LEFT) {

					combatSlider.setValue(combatSlider.getValue()-1);
					
				}else if(direction == Direction.RIGHT) {
					
					combatSlider.setValue(combatSlider.getValue()+1);
					
				}else {
					
					SURPG_Core.logger.log(Level.INFO, "Something went wrong...");
					
				}
				
				try {
					Thread.sleep(speedSlider);
				} catch (InterruptedException e) {
					SURPG_Core.runError(e);
				}
				
			}
			
		}
	    
	});
	
	public GUI_Combat(int speedNumber, int enemyHealth, int enemyStrength) throws IOException{
		
		sound = null;
		try {
			sound = new Media(GUI_Combat.class.getResource("/resources/audio/Battle.wav").toURI().toString());
		} catch (URISyntaxException e1) {
			SURPG_Core.runError(e1);
		}
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
        
        barView.setFitHeight(30);
        barView.setFitWidth(230);
        barView.setImage(barImage);
	    
	    enemyHealthVar = enemyHealth;
	    
	    SURPG_Core.MAIN_GUI.reloadLabels();
		
		speedSlider = speedNumber;
		
		stage.setScene(scene);
		stage.setWidth(300);
		stage.setHeight(460);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setResizable(false);
		
		scene.getStylesheets().add("resources/styling/SURPG_CSS.css");
		scene.setFill(null);
		
		//Slider Visuals
		setVisuals(fight);
		setVisuals(flee);
		setVisuals(items);
		setVisuals(abilities);
		setVisuals(hit);
		setVisuals(cancel);
		//setVisuals(enemyWindow);
		setVisuals(enemyHealthLabel);
		
		//Size Constraints
		top.setPrefSize(230, 150);
		top.setMaxSize(230, 150);
		
		layout.setPadding(new Insets(80, 10, 10, 10));
		layout.setBackground(new Background(new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		//Alignment
		layout.setAlignment(Pos.CENTER);
		grid.setAlignment(Pos.CENTER);
		fightLayout.setAlignment(Pos.CENTER);
		bottom.setAlignment(Pos.CENTER);
		
		//Fight Layout
		fightLayout.setHgap(10);
		fightLayout.setVgap(20);
		fightLayout.setVisible(false);
		GridPane.setColumnSpan(barView, 2);
		GridPane.setColumnSpan(combatSlider, 2);
		
		//Main Combat Layout
		grid.setHgap(10);
		grid.setVgap(20);
		
		//Top Pane
		top.setId("CombatBackground");
		
		layout.getChildren().addAll(top, bottom);
		bottom.getChildren().addAll(grid, fightLayout);
		
		fightLayout.add(enemyHealthLabel, 0, 0);
		fightLayout.add(barView, 0, 1);
		fightLayout.add(combatSlider, 0, 2);
		fightLayout.add(hit, 0, 3);
		fightLayout.add(cancel, 1, 3);
		
		grid.add(fight, 0, 0);
		grid.add(items, 1, 0);
		grid.add(abilities, 0, 1);
		grid.add(flee, 1, 1);
		
		/*addComponent(combatGUI, fight, 30, 260, 100, 30);
		addComponent(combatGUI, items, 160, 260, 100, 30);
		addComponent(combatGUI, abilities, 30, 320, 100, 30);
		addComponent(combatGUI, flee, 160, 320, 100, 30);
		addComponent(combatGUI, enemyWindow, 30, 30, 230, 150);
		addComponent(combatGUI, combatSlider, 30, 240, 230, 30);
		addComponent(combatGUI, combatPanel, 30, 270, 230, 30);
		addComponent(combatGUI, hit, 30, 320, 100, 30);
		addComponent(combatGUI, cancel, 160, 320, 100, 30);
		addComponent(combatGUI, enemyHealthLabel, 30, 200, 100, 30);*/
		
		stage.show();
		
		fight.setOnAction(e -> {

			combatSlider.setValue(0);
				
			grid.setVisible(false);
							
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
		
		flee.setOnAction(e -> {

			mediaPlayer.stop();
			
			SURPG_Core.MAIN_GUI.show();
			stage.close();
	
		});
		
	}
	
	private void setVisuals(Control comp){
		
		comp.setId("Button");
		comp.setPrefSize(100, 30);
		
	}
	
	private long calculateDamage(int percentage){
		
		long totalDamage = 0;
		
		if(percentage <= 25 || percentage >= 75){
			
			totalDamage = ((int) Math.floor(SURPG_Core.CURRENT_PLAYER.getPhysStrength() * 0));
			
		}else if(percentage > 25 && percentage <= 40 || percentage < 75 && percentage >= 60){
			
			totalDamage = ((int) Math.floor(SURPG_Core.CURRENT_PLAYER.getPhysStrength() * .25));
			
		}else if(percentage > 40 && percentage <= 48 || percentage < 60 && percentage >= 52){
			
			totalDamage = ((int) Math.floor(SURPG_Core.CURRENT_PLAYER.getPhysStrength() * .75));
			
		}else if(percentage > 48 && percentage < 52){
			
			totalDamage = SURPG_Core.CURRENT_PLAYER.getPhysStrength() * 1;
			
		}else{
			
			System.out.println("Something went wrong");
			
		}
		
		System.out.println(totalDamage);
		
		return totalDamage;
		
	}
	
	public void close() {
		
		stage.close();
		
	}
	
}