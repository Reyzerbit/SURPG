package com.jaketherey.SURPG.SaveObjects;

import java.io.Serializable;

public class Answer_Runs implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String method;
	int val;
	
	public Answer_Runs(String stat, int val) {
		
		this.method = stat;
		this.val = val;
		
	}
	
	public String getMethodName() {
		
		return method;
		
	}
	
	public int getVal() {
		
		return val;
		
	}

}
