package com.sam.yh.resp.bean;

public class FetchOrderRegisterResp {
	
	private String imei;
	
	private String msg;
	
	private int status;
	
	private String receiveDate;
	
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
}
