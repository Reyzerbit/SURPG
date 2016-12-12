package com.reyzerbit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.reyzerbit.fetchDataClasses.SaveLoad;
import com.reyzerbit.guis.GUIContent;

public class MenuBar {
	
	//Menu Bar
	static JMenuBar menuBar = new JMenuBar();
	
	//Menus for Bar
	
	//Loading Saving
	static JMenu menuFile = new JMenu("File");
	static JMenu loadGame = new JMenu("Load Game");
	
	//Game and Stats
	static JMenu gameStuff = new JMenu("Game");
	static JMenu stats = new JMenu("Check Stats");
	
	//Menu Items
	
	//Loading Saving
	public static JMenuItem saveGame = new JMenuItem("Save Game");
	static JMenuItem newSave = new JMenuItem("Save New Game");
	static JMenuItem pickFile = new JMenuItem("Select File to Load");
	static JMenuItem recents = new JMenuItem("Recents");
	
	//Game and Stats
	public static JMenuItem healthPoints = new JMenuItem("HP: " + Feats.health);
	
	public static void initMenuBar(){
		
		GUIContent.gui.setJMenuBar(menuBar);
		
		//MenuBar Additions
		
		//Add File
		menuBar.add(menuFile);
		//Add Game
		menuBar.add(gameStuff);
		
		//Add Items to File
		menuFile.add(saveGame);
		menuFile.add(newSave);
		menuFile.addSeparator();
		menuFile.add(loadGame);
		
		//Add Items to Game
		gameStuff.add(stats);
		stats.add(healthPoints);
		
		//Load Game Menu
		loadGame.add(pickFile);
		loadGame.addSeparator();
		loadGame.add(recents);
		recents.disable();
		
		//Action Listeners
		saveGame.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				SaveLoad.saveFile();
				
			}
			
		});
		
		pickFile.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				SaveLoad.loadGame();
				
			}
			
		});
		
		newSave.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				SaveLoad.saveNewGame();
				
			}
			
		});
		
	}
	
	public static void makeMenuItem(String name, File file){
		
		JMenuItem menuItem = new JMenuItem(name);
		loadGame.add(menuItem);
		menuItem.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
	            SaveLoad.loadRecent(file);
				
			}
			
		});
		
	}

}
