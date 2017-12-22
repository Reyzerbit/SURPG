package com.jaketherey.SURPG.Game_Objects.Save_Objects;

import java.io.Serializable;

public class Answer_Packet implements Serializable{

	private static final long serialVersionUID = 1921181671141923518L;
	
	String answer;
	int destination;
	Answer_Runs[] possibleRuns;
	int methodCount;
	
	public Answer_Packet(String answer, int destination, int methodCount, Answer_Runs[] possibleRuns) {
		
		this.answer = answer;
		this.destination = destination;
		this.possibleRuns = possibleRuns;
		this.methodCount = methodCount;
		
	}
	
	public String getAnswer() {
		
		return answer;
		
	}
	
	public int getMethodCount() {
		
		return methodCount;
		
	}
	
	public int getDestination() {
		
		return destination;
		
	}
	
	public Answer_Runs[] getPossibleRuns() {
		
		return possibleRuns;
		
	}

}
