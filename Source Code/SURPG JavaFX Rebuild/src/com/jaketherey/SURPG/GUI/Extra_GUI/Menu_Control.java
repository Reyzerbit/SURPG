package com.jaketherey.SURPG.GUI.Extra_GUI;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.GUI.GUI_Console;
import com.jaketherey.SURPG.GUI.GUI_Main;
import com.jaketherey.SURPG.IO.Saves_Handler;

import de.codecentric.centerdevice.MenuToolkit;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class Menu_Control {
	
	public static MenuBar menuBar;
	public static Menu menuFile;
	public static Menu menuGame;
	public static Menu menuTools;
	public static MenuItem save;
	
	public static void initMenus() {
		
		if(SURPG_Core.isMac()) {
			macMenus(genMenuBar());
		}else {
			mainMenus(genMenuBar());
		}
		
	}
	
	private static MenuBar genMenuBar() {
		
		MenuBar menuBar = new MenuBar();
		
		//Application Menu
		Menu appMenu = new Menu("SURPG");
		MenuItem about = new MenuItem("About SURPG");
		MenuItem prefs = new MenuItem("Preferences");
		MenuItem hide = new MenuItem("Hide SURPG");
		MenuItem hideAll = new MenuItem("Hide Others");
		MenuItem show = new MenuItem("Show All");
		MenuItem quit = new MenuItem("Quit SURPG");
		
		//File Menu
		Menu fileMenu = new Menu("File");
		MenuItem save = new MenuItem("Save");
		
		//Developer Menu
		Menu developerMenu = new Menu("Developer");
		MenuItem console = new MenuItem("Console");
		
		fileMenu.getItems().add(save);
		developerMenu.getItems().add(console);
		appMenu.getItems().addAll(about, new SeparatorMenuItem(), prefs, new SeparatorMenuItem(), hide, hideAll, show, 
				new SeparatorMenuItem(), quit);
		
		save.setOnAction(e -> {
			Saves_Handler.saveData();
		});
		
		console.setOnAction(e -> {
			GUI_Console.init();
		});
		
		menuBar.getMenus().addAll(appMenu, fileMenu, developerMenu);
		
		return menuBar;
		
	}
	
	private static void macMenus(MenuBar menuBar) {
		
		MenuToolkit tk = MenuToolkit.toolkit();
		
		tk.setGlobalMenuBar(menuBar);
		
	}
	
	private static void mainMenus(MenuBar menuBar) {
		
		GUI_Main.totalLay.setTop(menuBar);
		
	}

}
