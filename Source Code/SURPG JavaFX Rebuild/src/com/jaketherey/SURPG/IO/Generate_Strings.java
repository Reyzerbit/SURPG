package com.jaketherey.SURPG.IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jaketherey.SURPG.SURPG_Core;
import com.jaketherey.SURPG.Game_Objects.Location_Chunk;

public class Generate_Strings {

	public static void main(String[] args){
		
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		
		List<Location_Chunk> chunkList = new ArrayList<Location_Chunk>();
		Location_Chunk[] chunkArray;
		
		String start;
		String attempt;
		String help;
		String[] answers;
		
		boolean loop = true;
		
		Scanner in = new Scanner(System.in);
		
		try {
			fos = new FileOutputStream(new File(SURPG_Core.SURPG_LOCATION));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(loop) {
			
			System.out.println("Input a valid start string.");
			start = in.nextLine();
			
			System.out.println("Input a valid attempt string.");
			attempt = in.nextLine();
			
			System.out.println("Input a valid help string.");
			help = in.nextLine();
			
			System.out.println("Input valid answer strings, seperated by \\|.");
			answers = in.nextLine().split("\\|");


			chunkList.add(new Location_Chunk(start, attempt, help, answers));
			
			while(true) {
				
				System.out.println("Would you like to enter another string? Enter y to do so. Enter n to quit.");
				String loop1 = in.nextLine();
				
				if(loop1.equalsIgnoreCase("y")) {
					
					break;
					
				}else if(loop1.equalsIgnoreCase("n")) {
					
					loop = false;
					break;
					
				}else {
					
					System.out.println("Please input a valid option.");
					
				}
				
			}

		}
		
		chunkArray = chunkList.toArray(new Location_Chunk[chunkList.size()]);
		
		try {
			oos.writeObject(chunkArray);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		in.close();
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
