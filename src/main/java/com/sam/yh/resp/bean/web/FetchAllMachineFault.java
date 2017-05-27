package com.sam.yh.resp.bean.web;

import java.util.Date;

public class FetchAllMachineFault {
	
	private int batteryId;
	
    private String imei;

	private String simNo;
	
	private String sn;
	
    private String markType;
	
	private int isFault;
	
	private String byte1;
	
	private String byte2;
	
	private String byte3;
	
	private String receiveDate;

	public int getBatteryId() {
		return batteryId;
	}

	public void setBatteryId(int batteryId) {
		this.batteryId = batteryId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSimNo() {
		return simNo;
	}

	public void setSimNo(String simNo) {
		this.simNo = simNo;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
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

	public String getByte3() {
		return byte3;
	}

	public void setByte3(String byte3) {
		this.byte3 = byte3;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

}
