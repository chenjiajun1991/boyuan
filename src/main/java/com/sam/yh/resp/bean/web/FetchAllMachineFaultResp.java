package com.sam.yh.resp.bean.web;

import java.util.List;

public class FetchAllMachineFaultResp {
	
	private List<FetchAllMachineFault> fetchAllMachineFault1;
	
	private List<FetchAllMachineFault> fetchAllMachineFault2;

	public List<FetchAllMachineFault> getFetchAllMachineFault1() {
		return fetchAllMachineFault1;
	}

	public void setFetchAllMachineFault1(
			List<FetchAllMachineFault> fetchAllMachineFault1) {
		this.fetchAllMachineFault1 = fetchAllMachineFault1;
	}

	public List<FetchAllMachineFault> getFetchAllMachineFault2() {
		return fetchAllMachineFault2;
	}

	public void setFetchAllMachineFault2(
			List<FetchAllMachineFault> fetchAllMachineFault2) {
		this.fetchAllMachineFault2 = fetchAllMachineFault2;
	}
}
