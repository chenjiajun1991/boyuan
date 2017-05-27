package com.sam.yh.model;

import java.util.Date;

public class FaultInfo {
	private Long id;
	
	private int batteryId;
	
	private String codeType;
	
	private int faultType;
	
	private String faultMessage;
	
	private int isNewest;
	
	private String markType;
	
	private String byte1;
	
	private String byte2;
	
	private String byte3;
	
	private Date receiveDate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBatteryId() {
		return batteryId;
	}
	
	public void setBatteryId(int batteryId) {
		this.batteryId = batteryId;
	}
	

	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public int getFaultType() {
		return faultType;
	}
	
	public void setFaultType(int faultType) {
		this.faultType = faultType;
	}
	
	public String getFaultMessage() {
		return faultMessage;
	}
	
	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}
	
	public int getIsNewest() {
		return isNewest;
	}

	public void setIsNewest(int isNewest) {
		this.isNewest = isNewest;
	}

	public String getMarkType() {
		return markType;
	}
	
	public void setMarkType(String markType) {
		this.markType = markType;
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

	public Date getReceiveDate() {
		return receiveDate;
	}
	
	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	@Override
	public String toString() {
		return "FaultInfo [id=" + id + ", batteryId=" + batteryId
				+ ", codeType=" + codeType + ", faultType=" + faultType
				+ ", faultMessage=" + faultMessage + ", isNewest=" + isNewest
				+ ", markType=" + markType + ", byte1=" + byte1 + ", byte2="
				+ byte2 + ", byte3=" + byte3 + ", receiveDate=" + receiveDate
				+ "]";
	}



}
