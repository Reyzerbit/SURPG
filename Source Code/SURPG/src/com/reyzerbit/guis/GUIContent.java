package com.reyzerbit.guis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.reyzerbit.Feats;

public class GUIContent{
	
	//Frame
	public static JFrame gui = new JFrame();
	
	//Labels
	static JLabel inputLabel = new JLabel("");
	static JLabel healthPoints = new JLabel("  HP: " + Feats.health);
	
	//Panels
	static JPanel stats = new JPanel();
	
	//Hover TextArea
	static JTextArea hover = new JTextArea("Hover over a stat for details.");
	
	//Text IO
	public static JTextArea outputWindow = new JTextArea();
	public static JTextField inputWindow = new JTextField();
	static JScrollPane scroll = new JScrollPane(outputWindow);
	
	//Input Streams
	static InputStream in = null;
	static InputStream in2 = null;
	
	//Images
	static BufferedImage background = null;
	static BufferedImage watermark = null;
	
	//Enter Button
	static JButton enter = new JButton("Enter");
	
	public static void init() throws IOException, URISyntaxException{
		
		//Initiate Game GUI

		//Background
		
		in = GUIContent.class.getClassLoader().getResourceAsStream("com/reyzerbit/assets/background.jpg");
		in2 = GUIContent.class.getClassLoader().getResourceAsStream("com/reyzerbit/assets/watermark.png");
		
		background = ImageIO.read(in);
		watermark = ImageIO.read(in2);
		
		@SuppressWarnings("serial")
		JPanel back = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(background, 0, 0, null);
	        }
	    };
	    
	    //Watermark
	    
	    @SuppressWarnings("serial")
		JPanel wmJPanel = new JPanel() {
	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(watermark, 0, 0, null);
	        }
	    };
		
		//GUI Constraints
	    gui.setContentPane(back);
		gui.getContentPane().setLayout(null);
		gui.setVisible(true);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(800, 500);
		gui.setName("Steven Universe Text Based RPG");
		gui.setTitle("Steven Universe Text Based RPG");
		gui.setResizable(false);
		gui.setLocationRelativeTo(null);
		gui.getRootPane().setDefaultButton(enter);

		//Hover Window Constraints
		hover.setWrapStyleWord(true);
		hover.setLineWrap(true);
		hover.setEditable(false);
		
		//Input Window Constraints
		inputWindow.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		
		//Output Windows Constraints
		outputWindow.setWrapStyleWord(true);
		outputWindow.setLineWrap(true);
		outputWindow.setEditable(false);
		outputWindow.setMargin(new Insets(5,5,5,5));
		
		//Scroll Constraints
		scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		
		//Enter Button Constraints
		enter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		enter.setOpaque(true);
		enter.setBackground(Color.LIGHT_GRAY);
		
		//JPanel Constraints
		stats.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		stats.setLayout(null);
		stats.setBackground(Color.WHITE);
		
		wmJPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		
		//Hover Constraints
		hover.setMargin(new Insets(5,5,5,5));
		hover.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		
		//Stats JPanel Item Constraints
		healthPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		healthPoints.setOpaque(true);
		healthPoints.setBackground(new Color(255, 74, 90));
		
		//Place JPanel Components
		addComponentFrame(stats, healthPoints, 20, 20, 65, 30);
		
		//Place Components
		addComponent(gui, scroll, 20, 20, 280, 440);
		addComponent(gui, inputWindow, 390, 35, 200, 20);
		addComponent(gui, enter, 610, 35, 100, 20);
		addComponent(gui, stats, 340, 80, 440, 250);
		addComponent(gui, hover, 340, 380, 320, 80);
		addComponent(gui, wmJPanel, 700, 380, 80, 80);
		
		
		//Request Focus for Input Window
		inputWindow.requestFocusInWindow();
		
		//Listener for Hovers
		healthPoints.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent event) {
                hover.setText("Your health starts at ten, and will slowly raise as you level up.");
            }

            @Override
            public void mouseExited(MouseEvent event) {
                hover.setText("Hover over a stat for details.");
            }
        });
		
		//Add Listener for Button
		enter.addActionListener(new ActionListener() {

			@Override
			
			public void actionPerformed(ActionEvent e) {
				
				Feats.runNextPath(Feats.location);
				
			}
			
		});
		
	}

	private static void addComponent(JFrame frame, Component component, int posx, int posy, int width, int height){
		
		component.setBounds(posx, posy, width, height);
		frame.getContentPane().add(component);
	
	}
	
	private static void addComponentFrame(JPanel panel, Component component, int posx, int posy, int width, int height){
		
		component.setSize(width, height);
		component.setLocation(posx, posy);
		panel.add(component);
	
	}
	
}
	

