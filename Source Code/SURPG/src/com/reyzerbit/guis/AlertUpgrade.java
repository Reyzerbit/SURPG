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
		ImageIcon iconAlert = new ImageIcon(Feats.requiredFiles + "images"+ Feats.separate + "Steven.png");
		JOptionPane.showMessageDialog(null,  "Your " + upgrade + " increased by one point.", "Upgrade", JOptionPane.PLAIN_MESSAGE, iconAlert);

		PlaySoundBite.close();
		
	}
	
}
