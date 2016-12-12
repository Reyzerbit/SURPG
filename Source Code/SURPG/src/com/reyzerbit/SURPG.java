package com.reyzerbit;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.reyzerbit.fetchDataClasses.RecentsLoad;
import com.reyzerbit.guis.GUIContent;

public class SURPG {
	
	public static void main(String[] args) throws IOException {
		
		//Menu Bar for MacOS
		
	    try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }
	    catch(ClassNotFoundException e) {
            System.out.println("ClassNotFoundException: " + e.getMessage());
	    }
	    catch(InstantiationException e) {
            System.out.println("InstantiationException: " + e.getMessage());
	    }
	    catch(IllegalAccessException e) {
            System.out.println("IllegalAccessException: " + e.getMessage());
	    }
	    catch(UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException: " + e.getMessage());
	    }
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    	public void run() {
	    	}
	    });
		
		//Initialize GUI
		
		GUIContent.init();
		MenuBar.initMenuBar();
		MenuBar.saveGame.disable();
		
		//Begin Game
		PickingCharacter.run0();
		
		RecentsLoad.loadRecents();

	}

}
