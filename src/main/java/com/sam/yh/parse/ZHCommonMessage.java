package com.sam.yh.parse;

import com.sam.yh.model.ZHCommonData;

public class ZHCommonMessage {
	
	public static ZHCommonData parseString(String str){
		
		ZHCommonData zhCommonData = new ZHCommonData();
		
		if(str.length()<6){
			zhCommonData.setOilPressure("0.0");
		}else{
			zhCommonData.setOilPressure(Integer.parseInt((str.substring(4, 6)+str.substring(2, 4)),16)+"");
		}
		
		if(str.length()<8){
			zhCommonData.setCwt("0.0");
		}else{
			zhCommonData.setCwt(Integer.parseInt(str.substring(6, 8),16)+"");
		}
		
		if(str.length()<12){
			zhCommonData.setMotorSpeed("0.0");
		}else{
			zhCommonData.setMotorSpeed(Integer.parseInt((str.substring(10, 12)+str.substring(8, 10)),16)+"");
		}
		
		if(str.length()<16){
			zhCommonData.setSpeedz("0.0");
		}else{
			zhCommonData.setSpeedz(Integer.parseInt((str.substring(14, 16)+str.substring(12, 14)),16)+"");
		}
		
		if(str.length()<20){
			zhCommonData.setSpeedf("0.0");
		}else{
			zhCommonData.setSpeedf(Integer.parseInt((str.substring(18, 20)+str.substring(16, 18)),16)+"");
		}
		  
	    return zhCommonData;
	  	
	}
	

	

}
