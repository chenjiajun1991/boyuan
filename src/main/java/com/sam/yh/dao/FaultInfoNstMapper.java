package com.sam.yh.dao;

import java.util.List;

import com.sam.yh.model.FaultInfoNst;

public interface FaultInfoNstMapper {
	
	FaultInfoNst selectByPrimaryKey(Long id);
	
	FaultInfoNst selectByBtyId(int batteryId);
	
	FaultInfoNst selectByMarkType(String markType);
	
	int deleteByPrimaryKey(Long id);
	
	int updateByBtyIdSelective(FaultInfoNst faultInfoNst);
	
	int insertSelective(FaultInfoNst faultInfoNst);

	List<FaultInfoNst> selectAllFaultInfo();
}
