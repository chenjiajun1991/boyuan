package com.sam.yh.req.bean;

import java.io.Serializable;

public class UploadFaultMessageReq implements Serializable{
	
	 private static final long serialVersionUID = 2L;
	 
	 private String imsi;
	
	 private String faultimei;
	  
	 private String byte1;
	 
	 private String byte2;
	 
	 private String byte3;
	 
	 private String string;
	 
	 private String len;
	 

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getFaultimei() {
		return faultimei;
	}

	public void setFaultimei(String faultimei) {
		this.faultimei = faultimei;
	}



	public String getByte1() {
		return byte1;
	}

	public void setByte1(String byte1) {
		this.byte1 = byte1;
	}



	public String getByte2() {
		return byte2;
	}

	public void setByte2(String byte2) {
		this.byte2 = byte2;
	}
	

	public String getByte3() {
		return byte3;
	}

	public void setByte3(String byte3) {
		this.byte3 = byte3;
	}
	
	

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
	
	

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	@Override
	public String toString() {
		return "UploadFaultMessageReq [imsi=" + imsi + ", faultimei="
				+ faultimei + ", byte1=" + byte1 + ", byte2=" + byte2
				+ ", byte3=" + byte3 + ", string=" + string + ", len=" + len
				+ "]";
	}

}
