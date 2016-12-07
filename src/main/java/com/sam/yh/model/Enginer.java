package com.sam.yh.model;

import java.util.Date;

public class Enginer {
	private int batteryId;
	private int lockOne;
	private int lockTwo;
	private Date lockOneDate;
	private Date lockTwoDate;
	
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
		
}
