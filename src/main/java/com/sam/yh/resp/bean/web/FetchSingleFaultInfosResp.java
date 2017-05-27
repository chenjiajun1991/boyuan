package com.sam.yh.resp.bean.web;

import java.util.List;

public class FetchSingleFaultInfosResp {
	
	private List<FetchSingleFaultInfos> fetchSingleFaultInfos;

	public List<FetchSingleFaultInfos> getFetchSingleFaultInfos() {
		return fetchSingleFaultInfos;
	}

	public void setFetchSingleFaultInfos(
			List<FetchSingleFaultInfos> fetchSingleFaultInfos) {
		this.fetchSingleFaultInfos = fetchSingleFaultInfos;
	}
	
}
