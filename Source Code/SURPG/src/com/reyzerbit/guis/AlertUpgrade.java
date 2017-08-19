package com.reyzerbit.guis;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.reyzerbit.Feats;
import com.reyzerbit.fetchDataClasses.PlaySoundBite;

public class AlertUpgrade {

	public static void upgrade(String upgrade){
		
		System.out.println("Displaying upgrade panel.");
		
		PlaySoundBite.play(System.getProperty("user.home") + Feats.separate + "Documents" + Feats.separate + "SURPG" + 
		Feats.separate + "RequiredFiles"+ Feats.separate + "audio"+ Feats.separate + "Upgrade.wav", false);
		ImageIcon iconAlert = new ImageIcon("src/com/reyzerbit/assets/Steven.png");
		JOptionPane.showMessageDialog(null,  "Your " + upgrade + " increased by one point.", "Upgrade", JOptionPane.PLAIN_MESSAGE, iconAlert);
		System.out.println("Closing sound bite...");
		PlaySoundBite.close();
		System.out.println("Sound bite closed.");
		
	}
	
}
