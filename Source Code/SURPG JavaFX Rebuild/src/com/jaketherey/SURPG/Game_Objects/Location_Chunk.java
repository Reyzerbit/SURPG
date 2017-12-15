package com.jaketherey.SURPG.Game_Objects;

import java.io.Serializable;

public class Location_Chunk implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String start;
	String attempt;
	String help;
	String[] answers;
	
	public Location_Chunk(String start, String attempt, String help, String[] answers) {
		
		this.start = start;
		this.attempt = attempt;
		this.help = help;
		this.answers = answers;
		
	}
	
	public String getStart() {
		
		return start.replaceAll("\\\\n", System.lineSeparator());
		
	}
	
	public String getAttempt() {
		
		return attempt.replaceAll("\\\\n", System.lineSeparator());
		
	}
	
	public String getHelp() {
		
		return help.replaceAll("\\\\n", System.lineSeparator());
		
	}
	
	public String[] getAnswers() {
		
		return answers;
		
	}

}
