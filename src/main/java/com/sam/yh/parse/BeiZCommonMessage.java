package com.sam.yh.parse;

import com.sam.yh.model.BeiZCommonData;

public class BeiZCommonMessage {
	
	
	public static BeiZCommonData  parseString(String str){
		
		BeiZCommonData beiZCommonData = new BeiZCommonData();
		
		if(str.length() < 4 ){
			beiZCommonData.setVoltage("0");
		}else{
			
			int temp = Integer.parseInt((str.substring(0, 2)+str.substring(2, 4)),16);
			
			double t = (3480d/30.6d + 2484d/21.5d)/2d;
			
			beiZCommonData.setVoltage((double)temp/t +"");
		}
		
		return beiZCommonData;
		
	}

	
	
	
}
