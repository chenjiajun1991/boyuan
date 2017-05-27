package com.sam.yh.resp.bean;

import java.util.ArrayList;
import java.util.List;

import com.sam.yh.model.FaultInfo;

public class FetchFaultResp {
	
	private List<FaultInfo> faultInfos = new ArrayList<FaultInfo>();

	public List<FaultInfo> getFaultInfos() {
		return faultInfos;
	}

	public void setFaultInfos(List<FaultInfo> faultInfos) {
		this.faultInfos = faultInfos;
	}
}
