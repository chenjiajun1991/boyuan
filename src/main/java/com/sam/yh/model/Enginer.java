package com.sam.yh.model;

import java.util.Date;

public class Enginer {
	private int batteryId;
	private int lockOne;
	private int lockTwo;
	private String lockBind;
	private Date lockOneDate;
	private Date lockTwoDate;
	private Date lockBindDate;
	
	public int getBatteryId() {
		return batteryId;
	}
	public void setBatteryId(int batteryId) {
		this.batteryId = batteryId;
	}
	public int getLockOne() {
		return lockOne;
	}
	public void setLockOne(int lockOne) {
		this.lockOne = lockOne;
	}
	public int getLockTwo() {
		return lockTwo;
	}
	public void setLockTwo(int lockTwo) {
		this.lockTwo = lockTwo;
	}
	public Date getLockOneDate() {
		return lockOneDate;
	}
	public void setLockOneDate(Date lockOneDate) {
		this.lockOneDate = lockOneDate;
	}
	public Date getLockTwoDate() {
		return lockTwoDate;
	}
	public void setLockTwoDate(Date lockTwoDate) {
		this.lockTwoDate = lockTwoDate;
	}
	public String getLockBind() {
		return lockBind;
	}
	public void setLockBind(String lockBind) {
		this.lockBind = lockBind;
	}
	public Date getLockBindDate() {
		return lockBindDate;
	}
	public void setLockBindDate(Date lockBindDate) {
		this.lockBindDate = lockBindDate;
	}
	
		
}
