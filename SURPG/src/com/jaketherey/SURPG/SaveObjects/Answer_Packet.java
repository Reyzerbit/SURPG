package com.jaketherey.SURPG.SaveObjects;

import java.io.Serializable;

public class Answer_Packet implements Serializable{

	private static final long serialVersionUID = 1921181671141923518L;
	
	String answer;
	int destination;
	Answer_Runs[] possibleRuns;
	
	public Answer_Packet(String answer, int destination, Answer_Runs[] possibleRuns) {
		
		this.answer = answer;
		this.destination = destination;
		this.possibleRuns = possibleRuns;
		
	}
	
	public String getAnswer() {
		
		return answer;
		
	}
	
	public int getDestination() {
		
		return destination;
		
	}
	
	public Answer_Runs[] getPossibleRuns() {
		
		return possibleRuns;
		
	}

}
