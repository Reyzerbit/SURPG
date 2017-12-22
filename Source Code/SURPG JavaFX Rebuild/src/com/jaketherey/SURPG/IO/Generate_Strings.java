package com.jaketherey.SURPG.IO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jaketherey.SURPG.Game_Objects.Save_Objects.Answer_Packet;
import com.jaketherey.SURPG.Game_Objects.Save_Objects.Answer_Runs;
import com.jaketherey.SURPG.Game_Objects.Save_Objects.Location_Chunk;

//Only for generating the strings serialized file, no in-game purpose.
public class Generate_Strings {

	public static void main(String[] args){
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		
		List<Location_Chunk> chunkList = new ArrayList<Location_Chunk>();
		List<Answer_Packet> answerList = new ArrayList<Answer_Packet>();
		Location_Chunk[] chunkArray;
		
		String start;
		String attempt;
		String help;
		String[] answer_bits;
		String[] packetBits;
		List<Answer_Runs> runList;
		
		Scanner in = null;
		try {
			in = new Scanner(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
				 + System.getProperty("file.separator") + "SURPGWork" + System.getProperty("file.separator") + "DataStorage.txt"));
			fos = new FileOutputStream(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Documents"
					 + System.getProperty("file.separator") + "SURPGWork" + System.getProperty("file.separator") + "Storylines.ser"));
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(in.hasNextLine()) {
			
			start = null;
			attempt = null;
			attempt = null;
			help = null;
			answer_bits = null;
			packetBits = null;
			runList = null;
			answerList = null;
			answerList = new ArrayList<Answer_Packet>();
			
			start = in.nextLine();
			
			attempt = in.nextLine();
			
			help = in.nextLine();
			
			answer_bits = in.nextLine().split("\\|");
			
			//0 is answer, 1 is destination, 2 is method count, 3-4 and so on is method and value.
			
			for(int i = 0; i < answer_bits.length; i++) {
				
				packetBits = answer_bits[i].split("-");
				runList = new ArrayList<Answer_Runs>();
				
				for(int x = 0; x < Integer.parseInt(packetBits[2]); x += 2) {
					
					try {
						runList.add(new Answer_Runs(packetBits[3+x], Integer.parseInt(packetBits[4+x])));
					} catch (NumberFormatException | SecurityException | NullPointerException e) {
						e.printStackTrace();
					}
					
				}
				
				try{
					
					answerList.add(new Answer_Packet(packetBits[0], Integer.parseInt(packetBits[1]), 
							
							Integer.parseInt(packetBits[2]), runList.toArray(new Answer_Runs[runList.size()])));
					
				}catch(IndexOutOfBoundsException | NullPointerException | SecurityException e) {
					
					answerList.add(new Answer_Packet(packetBits[0], Integer.parseInt(packetBits[1]), 0, new Answer_Runs[0]));
					
				}
				
			}

			chunkList.add(new Location_Chunk(start, attempt, help, answerList.toArray(new Answer_Packet[answerList.size()])));

		}
		
		chunkArray = chunkList.toArray(new Location_Chunk[chunkList.size()]);
		
		try {
			oos.writeObject(chunkArray);
			in.close();
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
