/*
 * Copyright (c) Jacob Batista 2017 - Present
 * All Steven Universe related characters, images, and sounds are copyright (c) Cartoon Network and Rebecca Sugar
 */

package com.jaketherey.SURPG.IO;

import java.util.logging.Level;

import com.jaketherey.SURPG.Core;

public class Commands {
	
	public static void interpret(String[] args) {
		
		if(args.length > 0) {
			
			for(int x = 0; x < args.length; x++) {
			
				switch(args[x]) {
			
				case "--developerMode": Core.DEVELOPER_MODE = true;
					SURPGLogger.logger.log(Level.INFO, "Developer mode activated.");
					break;
				
				default: SURPGLogger.logger.log(Level.INFO, "Unknown command requested.");
			
				}
				
			}
			
		}else {
			
		}
		
		Core.CURRENT_SAVES_FILE = Core.MAIN_SAVES_FILE;
		
	}

}
