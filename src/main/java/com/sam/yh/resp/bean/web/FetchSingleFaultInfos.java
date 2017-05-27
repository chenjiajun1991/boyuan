package com.sam.yh.resp.bean.web;

public class FetchSingleFaultInfos {
	
	private int batteryId;
	
	private String codeType;
	
	private String faultMessage;
	
	private String receiveDate;

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

	public String getFaultMessage() {
		return faultMessage;
	}

	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	
}
