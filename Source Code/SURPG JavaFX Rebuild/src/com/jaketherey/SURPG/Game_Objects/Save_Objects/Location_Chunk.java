package com.jaketherey.SURPG.Game_Objects.Save_Objects;

import java.io.Serializable;

public class Location_Chunk implements Serializable{
	
	private static final long serialVersionUID = 19211816738211411L;
	
	String start;
	String attempt;
	String help;
	Answer_Packet[] answers;
	
	public Location_Chunk(String start, String attempt, String help, Answer_Packet[] answers) {
		
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
	
	public Answer_Packet[] getAnswers() {
		
		return answers;
		
	}

}
