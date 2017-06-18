package com.reyzerbit.guis;

import javax.swing.JFrame;

public class CombatGUI{
	
	//Frame
	public static JFrame combatGUI = new JFrame();
	
	public static void initCombat(){
		
		combatGUI.getContentPane().setLayout(null);
		combatGUI.setVisible(true);
		combatGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		combatGUI.setSize(300, 400);
		combatGUI.setName("Combat");
		combatGUI.setTitle("Combat");
		combatGUI.setResizable(false);
		combatGUI.setLocationRelativeTo(null);
		
	}
	
}