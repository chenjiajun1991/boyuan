package com.sam.yh.req.bean;

import java.io.Serializable;

public class UploadOrderRegisterMsgReq implements Serializable{
	
	private static final long serialVersionUID = 3L;
	
	private String hyimei;
	
	private String string ;

	public String getHyimei() {
		return hyimei;
	}

	public void setHyimei(String hyimei) {
		this.hyimei = hyimei;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
