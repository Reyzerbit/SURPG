package com.jaketherey.SURPG.GUI.Extra_GUI;

import java.util.logging.Level;

import com.jaketherey.SURPG.Core;
import com.jaketherey.SURPG.GUI.GUI_About;
import com.jaketherey.SURPG.GUI.GUI_Console;
import com.jaketherey.SURPG.IO.SURPGLogger;
import com.jaketherey.SURPG.IO.Saves_Handler;

import de.codecentric.centerdevice.MenuToolkit;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class Menu_Control {
	
	public static MenuBar menuBar;
	public static Menu menuFile;
	public static Menu menuGame;
	public static Menu menuTools;
	public static MenuItem save;
	
	private static GUI_Console console;
	private static GUI_About about;
	
	public static void initMenus() {
		
		try {
		
			SURPGLogger.logger.log(Level.INFO, "Initiating menues...");
		
			if(Core.isMac()) {
				macMenus(genMenuBar());
			}else {
				mainMenus(genMenuBar());
			}
		
			SURPGLogger.logger.log(Level.INFO, "Menues initiated.");
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	private static MenuBar genMenuBar() {
		
		MenuBar menuBar = new MenuBar();
		
		//Application Menu
		Menu appMenu = new Menu("SURPG");
		MenuItem aboutMenu = new MenuItem("About SURPG");
		MenuItem prefs = new MenuItem("Preferences");
		MenuItem hide = new MenuItem("Hide SURPG");
		MenuItem hideAll = new MenuItem("Hide Others");
		MenuItem show = new MenuItem("Show All");
		MenuItem quit = new MenuItem("Quit SURPG");
		
		//File Menu
		Menu fileMenu = new Menu("File");
		MenuItem save = new MenuItem("Save");
		MenuItem saveNew = new MenuItem("Save As New...");
		MenuItem load = new MenuItem("Load");
		MenuItem loadFrom = new MenuItem("Load From...");
		
		//Developer Menu
		Menu developerMenu = new Menu("Developer");
		MenuItem consoleMenu = new MenuItem("Console");
		
		//Adding Menu Items
		fileMenu.getItems().addAll(save, saveNew, load, loadFrom);
		developerMenu.getItems().add(consoleMenu);
		appMenu.getItems().addAll(aboutMenu, new SeparatorMenuItem(), prefs, new SeparatorMenuItem(), hide, hideAll, show, 
				new SeparatorMenuItem(), quit);
		
		//Key Bindings
		save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
		saveNew.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));
		load.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.SHORTCUT_DOWN));
		loadFrom.setAccelerator(new KeyCodeCombination(KeyCode.L, KeyCombination.SHIFT_DOWN, KeyCombination.SHORTCUT_DOWN));
		quit.setAccelerator(new KeyCodeCombination(KeyCode.Q, KeyCombination.SHORTCUT_DOWN));
		
		save.setOnAction(e -> {
			Saves_Handler.saveData();
		});
		
		saveNew.setOnAction(e -> {
			Saves_Handler.saveAsNew();
		});
		
		load.setOnAction(e -> {
			Saves_Handler.loadSaveData();
			Core.MAIN_GUI.close();
		});
		loadFrom.setOnAction(e -> {
			Saves_Handler.loadFrom();
			Core.MAIN_GUI.close();
		});
		
		consoleMenu.setOnAction(e -> {
			console = new GUI_Console();
			console.show();
			Core.MAIN_GUI.close();
		});
		
		aboutMenu.setOnAction(e -> {
			about = new GUI_About();
			about.show();
		});
		
		quit.setOnAction(e -> {
			Platform.exit();
		});
		
		menuBar.getMenus().addAll(appMenu, fileMenu);
		if(Core.DEVELOPER_MODE) {
			menuBar.getMenus().add(developerMenu);
		}
		
		return menuBar;
		
	}
	
	private static void macMenus(MenuBar menuBar) {
		
		MenuToolkit tk = MenuToolkit.toolkit();
		
		tk.setGlobalMenuBar(menuBar);
		
	}
	
	private static void mainMenus(MenuBar menuBar) {
		
		Core.MAIN_GUI.addTopMenu(menuBar);
		
	}

}
