package com.sam.yh.model;

import java.util.Date;

public class FaultInfoNst {
    private Long id;
	
	private int batteryId;
	
	private String markType;
	
	private int isFault;
	
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

	public String getMarkType() {
		return markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}

	public int getIsFault() {
		return isFault;
	}

	public void setIsFault(int isFault) {
		this.isFault = isFault;
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

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getByte3() {
		return byte3;
	}

	public void setByte3(String byte3) {
		this.byte3 = byte3;
	}
}
