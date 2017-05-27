package com.sam.yh.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestionTypeUtils {

	  
    private static HashMap<String,String> questionMap;
    
    static {
    	questionMap = initMap();	
    }
    
    private static HashMap<String,String> initMap(){
    	
    	HashMap<String,String> tempMap = new HashMap<String,String>();
    	
 
    	tempMap.put("01", "故障报警灯（短路）");
    	tempMap.put("02", "故障报警灯（断路）");
    	
    	tempMap.put("10", "传感器电源（短路）");
    	tempMap.put("11", "刹车电磁阀（短路）");
    	tempMap.put("12", "室内风扇、顶灯电源（短路）");
    	tempMap.put("13", "雨刮电源（短路）");
    	tempMap.put("14", "电喇叭（短路）");
    	tempMap.put("15", "空调、暧风电源（短路）");
    	tempMap.put("16", "油扇（短路）");
    	tempMap.put("17", "抛送电机电源（短路）");
    	
    	tempMap.put("20", "传感器电源（断路）");
    	tempMap.put("21", "刹车电磁阀（断路）");
    	tempMap.put("22", "电喇叭（断路）");
    	tempMap.put("23", "显示器（断路）");
    	tempMap.put("24", "主离合（断路）");
    	tempMap.put("25", "油位传感器（断路）");
    	tempMap.put("26", "无级变速传感器（断路）");
    	tempMap.put("27", "油扇（断路）");

    	tempMap.put("30", "抛送电机电源（断路）");
    	tempMap.put("31", "粮仓报警灯（断路）");
    	tempMap.put("32", "故障报警灯（断路）");
    	tempMap.put("36", "手刹输入时检测到速度时报警");
    	tempMap.put("37", "电压报警");
    	
    	tempMap.put("40", "起动机开关受限（空档）");
    	tempMap.put("41", "起动机开关受限（主离合）");
    	tempMap.put("42", "起动机开关受限（发电机状态）");
    	tempMap.put("43", "起动机开关受限（ECU地输入）");
    	
    	tempMap.put("50", "雨刮断路");
    	tempMap.put("51", "空滤报警");
    	tempMap.put("52", "风扇/内视灯断路");
    	tempMap.put("53", "排风扇堵转");
    	tempMap.put("54", "暖风保险F24断路");
    	tempMap.put("55", "抛送电机堵转");
    	tempMap.put("56", "剥皮机堵转");
    	tempMap.put("57", "二次排茎堵转");
    	
    	tempMap.put("60", "制动灯短路");
    	tempMap.put("61", "显示器短路");
    	tempMap.put("62", "电磁泵短路");
    	tempMap.put("63", "发电机励磁短路");
    	tempMap.put("64", "右转灯短路");
    	tempMap.put("65", "ECU电源控制短路");
    	tempMap.put("66", "左转灯短路");
    	tempMap.put("67", "倒车灯短路");
    	
    	tempMap.put("70", "前工作灯短路");
    	tempMap.put("71", "ECU供电1短路");
    	tempMap.put("72", "发动机启动继电器短路");
    	tempMap.put("73", "ECU供电2短路");
    	tempMap.put("74", "远光灯短路");
    	tempMap.put("75", "近光灯短路");
    	tempMap.put("76", "后工作灯短路");
    	
    	tempMap.put("80", "示宽灯短路");
    	tempMap.put("81", "抛送电机F1断路");
    	tempMap.put("82", "抛送电机继电器4断路");
    	tempMap.put("83", "传感器电源保险F16断路");
    	tempMap.put("84", "显示屏保险F14断路");
    	tempMap.put("85", "粮满报警输出保险F22断路");
    	tempMap.put("86", "粮满报警输出保险F20断路");
    	tempMap.put("87", "危险灯输出保险F17断路");
    	
    	tempMap.put("90", "制动灯断路");
    	tempMap.put("91", "显示器断路");
    	tempMap.put("92", "电磁泵断路");
    	tempMap.put("93", "发电机励磁断路");
    	tempMap.put("94", "右转灯断路");
    	tempMap.put("95", "ECU电源控制断路");
    	tempMap.put("96", "左转灯断路");
    	tempMap.put("97", "倒车灯断路");
    	
    	tempMap.put("A0", "前工作灯断路");
    	tempMap.put("A1", "ECU供电1断路");
    	tempMap.put("A2", "发电机启动继电器断路");
    	tempMap.put("A3", "ECU供电2断路");
    	tempMap.put("A4", "远光灯断路");
    	tempMap.put("A5", "近光灯断路");
    	tempMap.put("A6", "后工作灯断路");
    	tempMap.put("A7", "雨刮保险F23断路");
    	
    	tempMap.put("B0", "风扇/内视灯保险F15断路");
    	tempMap.put("B1", "ECU供电保险F1断路");
    	tempMap.put("B2", "危险灯输出保险F19断路");
    	tempMap.put("B3", "空调电源保险F28断路");
    	tempMap.put("B4", "空调电源继电器3断路");
    	tempMap.put("B7", "示宽灯断路");
    	
    	tempMap.put("C0", "冷却水温高");
    	tempMap.put("C1", "机油压力低");
    	tempMap.put("C2", "燃油进水");
    	tempMap.put("C3", "空滤堵塞报警");
    	tempMap.put("C4", "发动机系统一般故障");
    	tempMap.put("C5", "发动机系统严重故障");
    	
    	return tempMap;
    }
    
    public static String getQuestionMessage(String key){
    	return questionMap.get(key);
    }
    
    
    public static String hexString2binaryString(String hexString) {
//        if (hexString == null || hexString.length() % 2 != 0)
//          return null;
        if (hexString == null)
            return null;
        
        String bString = "", tmp;
        for (int i = 0; i < hexString.length(); i++) {
          tmp = "0000" + Integer.toBinaryString(Integer.parseInt(hexString.substring(i, i + 1), 16));
          bString += tmp.substring(tmp.length() - 4);
        }
        return bString;
      }

      public static String binaryString2hexString(String bString) {
        if (bString == null || bString.equals("") || bString.length() % 8 != 0)
          return null;
        StringBuffer tmp=new StringBuffer();
        int iTmp = 0;
        for (int i = 0; i < bString.length(); i += 4) {
          iTmp = 0;
          for (int j = 0; j < 4; j++) {
            iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);
          }
          tmp.append(Integer.toHexString(iTmp));
        }
        return tmp.toString();
      }
      
      public static List<String> convertQuestion(String indexId , String hexString){
    	  
    	  String tempString = hexString2binaryString(hexString);
    	  
    	  if(tempString == null){
    		  return null;
    	  }
    	  List<String> faultList = new ArrayList<>();

    		  for(int i = tempString.length()-1;i>=0;i--){
    			  if(String.valueOf(tempString.charAt(i)).equals("1")){
    				  String temp = String.valueOf(tempString.length()-i-1);
    				  String groupString = indexId.toUpperCase()+temp;
    				  faultList.add(groupString);
    				  
    			  }
    			  
    			  
    		  }

    	  return faultList;
    	  
      }
      
    public static List<String> convertQuestion1(String hexString,String hexString2){
    	
    	List<String> faultList = new ArrayList<>();
    	
    	String[] arr = hexStringToArr(hexString);
    	String[] arr2 = hexStringToArr(hexString2);
    	
    	if(arr != null){
    		if(arr.length == 8){
    			for(int i = 0; i < arr.length ; i++){
    				String tempString = arr[i];
    				
    				 for(int j = tempString.length()-1;j>=0;j--){
    	    			  if(String.valueOf(tempString.charAt(j)).equals("1")){
    	    				  String temp = String.valueOf(tempString.length()-j-1);
    	    				  String groupString = String.valueOf(i)+temp;
    	    				  faultList.add(groupString);	  
    	    			  }
    	    		  }
    			}
    		}else if(arr.length == 4){
    			
    			for(int i = 0; i < arr.length ; i++){
    				String tempString = arr[i];
    				
    				 for(int j = tempString.length()-1;j>=0;j--){
    	    			  if(String.valueOf(tempString.charAt(j)).equals("1")){
    	    				  String temp = String.valueOf(tempString.length()-j-1);
    	    				  String groupString = intToHex(i+8)+temp;
    	    				  faultList.add(groupString);	  
    	    			  }
    	    		  }
    			}

    		}
    	}
    	
    	if(arr2 != null){
    		if(arr2.length == 8){
    			for(int i = 0; i < arr2.length ; i++){
    				String tempString = arr2[i];
    				
    				 for(int j = tempString.length()-1;j>=0;j--){
    	    			  if(String.valueOf(tempString.charAt(j)).equals("1")){
    	    				  String temp = String.valueOf(tempString.length()-j-1);
    	    				  String groupString = String.valueOf(i)+temp;
    	    				  faultList.add(groupString);	  
    	    			  }
    	    		  }
    			}
    		}else if(arr2.length == 4){
    			
    			for(int i = 0; i < arr2.length ; i++){
    				String tempString = arr2[i];
    				
    				 for(int j = tempString.length()-1;j>=0;j--){
    	    			  if(String.valueOf(tempString.charAt(j)).equals("1")){
    	    				  String temp = String.valueOf(tempString.length()-j-1);
    	    				  String groupString = intToHex(i+8)+temp;
    	    				  faultList.add(groupString);	  
    	    			  }
    	    		  }
    			}

    		}
    	}
    	
    	
    	  
    	  return faultList;
    	  
      }
    
    public static String[] hexStringToArr(String hexString){
		if(hexString != null){
			if(hexString.length() == 16){
				String arr1[] = new String[8];
				arr1[0] = hexString2binaryString(hexString.substring(0, 2));
				arr1[1] = hexString2binaryString(hexString.substring(2, 4));
				arr1[2] = hexString2binaryString(hexString.substring(4, 6));
				arr1[3] = hexString2binaryString(hexString.substring(6, 8));
				arr1[4] = hexString2binaryString(hexString.substring(8, 10));
				arr1[5] = hexString2binaryString(hexString.substring(10, 12));
				arr1[6] = hexString2binaryString(hexString.substring(12, 14));
				arr1[7] = hexString2binaryString(hexString.substring(14, 16));
				return arr1;
				
			}else if(hexString.length() == 8){
				String arr2[] = new String[4];
				arr2[0] = hexString2binaryString(hexString.substring(0, 2));
				arr2[1] = hexString2binaryString(hexString.substring(2, 4));
				arr2[2] = hexString2binaryString(hexString.substring(4, 6));
				arr2[3] = hexString2binaryString(hexString.substring(6, 8));
				return arr2;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
    
    public static String intToHex(int i){
    	if(i==10){
    		return "A";
    	}else if(i == 11){
    		return "B";
    	}else{
    		return String.valueOf(i);
    	}
    }

}
