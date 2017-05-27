package com.sam.yh.resp.bean;

import java.util.ArrayList;
import java.util.List;

import com.sam.yh.model.FetchErrInfo;

public class FetchErrResp {
	
	private List<FetchErrInfo> errorInfos = new ArrayList<FetchErrInfo>();

	public List<FetchErrInfo> getErrorInfos() {
		return errorInfos;
	}

	public void setErrorInfos(List<FetchErrInfo> errorInfos) {
		this.errorInfos = errorInfos;
	}
}
