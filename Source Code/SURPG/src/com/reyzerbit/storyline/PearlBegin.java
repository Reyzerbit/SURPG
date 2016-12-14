package com.reyzerbit.storyline;

import com.reyzerbit.Feats;
import com.reyzerbit.fetchDataClasses.StringsClass;

public class PearlBegin {
	
	public static void run0(){
		
		
		if(Feats.location == 51 || Feats.location == 61){
			
			Feats.addText(StringsClass.readString("s8"));
			Feats.location = (long) 5.2;
			
		}
		else{
			
			Feats.addText(StringsClass.readString("s8.1"));
			Feats.location = (long) 5.2;
			
		}
		
	}

}
