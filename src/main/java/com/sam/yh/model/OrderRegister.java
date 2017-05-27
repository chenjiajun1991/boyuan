package com.sam.yh.model;

import java.util.Date;

public class OrderRegister {
	
	  private Long id;

	  private Integer batteryId;
	  
	  private int status;
	  
	  private String msgStatus;
	  
	  private String sendMsg;
	  
	  private String receiveMsg;
	  
	  private Date sendDate;
	  
	  private Date receiveDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBatteryId() {
		return batteryId;
	}

	public void setBatteryId(Integer batteryId) {
		this.batteryId = batteryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(String sendMsg) {
		this.sendMsg = sendMsg;
	}

	public String getReceiveMsg() {
		return receiveMsg;
	}

	public void setReceiveMsg(String receiveMsg) {
		this.receiveMsg = receiveMsg;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}
	  
}
