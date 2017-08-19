package com.reyzerbit.guis;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.reyzerbit.Feats;
import com.reyzerbit.fetchDataClasses.PlaySoundBite;

public class ConsoleGUI {
	
	//Frame
	public static JFrame consoleGUI = new JFrame();
	
	//Text Area and Scroll
	public static JTextArea outputWindow = new JTextArea();
	static JScrollPane scroll = new JScrollPane(outputWindow);
	public static JTextField inputWindow = new JTextField();
	
	//Input
	
	//Buttons
	static JButton enter = new JButton("Enter");
		
	public static void initConsole(){
			
		outputWindow.setText("");
		
		consoleGUI.getContentPane().setLayout(null);
		consoleGUI.setVisible(true);
		consoleGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		consoleGUI.setSize(300, 400);
		consoleGUI.setName("Console");
		consoleGUI.setTitle("Console");
		consoleGUI.setResizable(false);
		consoleGUI.setLocationRelativeTo(null);
		consoleGUI.getRootPane().setDefaultButton(enter);
		
		enter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		enter.setOpaque(true);
		enter.setBackground(Color.LIGHT_GRAY);
		
		addComponent(consoleGUI, scroll, 10, 10, 280, 280);
		addComponent(consoleGUI, inputWindow, 10, 300, 280, 20);
		addComponent(consoleGUI, enter, 120, 335, 60, 30);
			
		inputWindow.requestFocus();
		
		//Add Listener for Enter Button
		enter.addActionListener(new ActionListener() {

			@Override
					
			public void actionPerformed(ActionEvent e) {
				
				String input = inputWindow.getText();
				
				String[] lineArray = input.split(" ");
				
				//Start Combat Command
				if(lineArray[0].equals("startCombat") && lineArray.length == 4){
					
					int combatSliderSpeed = Integer.parseInt(lineArray[1]);
					int combatEnemyHealth = Integer.parseInt(lineArray[2]);
					int combatEnemyStrength = Integer.parseInt(lineArray[3]);
					
					try {
						CombatGUI.initCombat(combatSliderSpeed, combatEnemyHealth, combatEnemyStrength);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					consoleGUI.dispose();
					
				}else if(lineArray[0].equals("startCombat") && lineArray.length != 4){
					
					outputWindow.append("This command is used like this:\nstartCombat [slider speed] [enemy health] [enemy strength]\n\n");
				
				//Decrease health command
					
				}else if(lineArray[0].equals("setVal") && lineArray.length == 3){
					
					//Take the inputed feat variable and set it to inputed value
					
					long temp = Long.parseLong(lineArray[2]);
					
					try {
						Feats.class.getField(lineArray[1]).set(lineArray[1], temp);;
					} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
						e1.printStackTrace();
					}
					
					outputWindow.append("Value set.\n\n");
					
					Feats.resetStat();
					
				}else if(lineArray[0].equals("setVal") && lineArray.length != 3){
					
					outputWindow.append("You need to enter the command like this:\n\nsetVal [value to reset] [value]\n\n");
					
				}else if(lineArray[0].equals("testSound") && lineArray.length == 1){
					
					//Test OpenAL Audio.
					
					PlaySoundBite.play(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + 
							Feats.separate + "RequiredFiles"+ Feats.separate + "audio"+ Feats.separate + "Error.wav", false);
					
					outputWindow.append("Playing sound.\n\n");
					
					Feats.resetStat();
					
				}else if(lineArray[0].equals("testSound") && lineArray.length != 1){
					
					outputWindow.append("You need to enter the command like this:\n\ntestSound\n\n");
					
				}else if(lineArray[0].equals("testUpgrade") && lineArray.length == 2){
					
					AlertUpgrade.upgrade(lineArray[1]);
					
					outputWindow.append("Upgrade panel testing.\n\n");
					
					Feats.resetStat();
					
				}else if(lineArray[0].equals("testUpgrade") && lineArray.length != 2){
					
					outputWindow.append("You need to enter the command like this:\n\ntestUpgrade [upgraded stat]\n\n");
					
				}else{
					
					outputWindow.append("Unknown Command\n\n");
					outputWindow.append(Integer.toString(lineArray.length));
					
				}
				
				inputWindow.setText("");
						
			}
					
		});
				
	}
	
	
	//Method to add component to GUI.
	
	private static void addComponent(JFrame frame, Component component, int posx, int posy, int width, int height){
		
		component.setBounds(posx, posy, width, height);
		frame.getContentPane().add(component);
	
	}

}
