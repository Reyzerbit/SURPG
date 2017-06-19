package com.reyzerbit.guis;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CharacterDraw extends JPanel{
	
	static BufferedImage characterPic = null;
	
	static InputStream in2 = null;
	
	public void paintComponent(Graphics g) {
		
		//Character Shape
		in2 = GUIContent.class.getClassLoader().getResourceAsStream("com/reyzerbit/assets/CharacterPic.png");
		try {
			characterPic = ImageIO.read(in2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        super.paintComponent(g);
        g.drawImage(characterPic, 0, 0, null);
    }

}