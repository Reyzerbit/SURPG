package com.jaketherey.SURPG.Game_Objects.Save_Objects;

import java.io.Serializable;

public class Answer_Runs implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String method;
	int val;
	
	public Answer_Runs(String method, int val) {
		
		this.method = method;
		this.val = val;
		
	}
	
	public String getMethodName() {
		
		return method;
		
	}
	
	public int getVal() {
		
		return val;
		
	}

}
