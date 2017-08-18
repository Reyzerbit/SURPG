package com.reyzerbit.guis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class HealthRectangle extends JPanel{

public static double healthPercent = 140;

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawRect(0,0,(int) healthPercent,30);  
	    g.setColor(Color.RED);  
	    g.fillRect(0,0,(int) healthPercent,30);  
	  }

	  public Dimension getPreferredSize() {
	    return new Dimension(65, 30);
	  }

}
