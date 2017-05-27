package com.sam.yh.model;

import java.util.Date;

public class LockInfo {
	
    private int id;
    private int batteryId;
    private int lockState;
    private int unlockState;
    private int bindState;
    private Date lockReqDate;
    private Date lockRespDate;
    private Date unlockReqDate;
    private Date unlockRespDate;
    private Date bindReqDate;
    private Date bindRespDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBatteryId() {
		return batteryId;
	}
	public void setBatteryId(int batteryId) {
		this.batteryId = batteryId;
	}
	public int getLockState() {
		return lockState;
	}
	public void setLockState(int lockState) {
		this.lockState = lockState;
	}
	public int getUnlockState() {
		return unlockState;
	}
	public void setUnlockState(int unlockState) {
		this.unlockState = unlockState;
	}
	public int getBindState() {
		return bindState;
	}
	public void setBindState(int bindState) {
		this.bindState = bindState;
	}
	public Date getLockReqDate() {
		return lockReqDate;
	}
	public void setLockReqDate(Date lockReqDate) {
		this.lockReqDate = lockReqDate;
	}
	public Date getLockRespDate() {
		return lockRespDate;
	}
	public void setLockRespDate(Date lockRespDate) {
		this.lockRespDate = lockRespDate;
	}
	public Date getUnlockReqDate() {
		return unlockReqDate;
	}
	public void setUnlockReqDate(Date unlockReqDate) {
		this.unlockReqDate = unlockReqDate;
	}
	public Date getUnlockRespDate() {
		return unlockRespDate;
	}
	public void setUnlockRespDate(Date unlockRespDate) {
		this.unlockRespDate = unlockRespDate;
	}
	public Date getBindReqDate() {
		return bindReqDate;
	}
	public void setBindReqDate(Date bindReqDate) {
		this.bindReqDate = bindReqDate;
	}
	public Date getBindRespDate() {
		return bindRespDate;
	}
	public void setBindRespDate(Date bindRespDate) {
		this.bindRespDate = bindRespDate;
	}
    
}
