package com.sam.yh.dao;

import java.util.List;

import com.sam.yh.model.FaultInfo;

public interface FaultInfoMapper {
	
	FaultInfo selectByPrimaryKey(Long id);
	
	List<FaultInfo> selectByBtyId(int batteryId);
	
	List<FaultInfo> selectByMarkType(String  markType);
	
	int deleteByPrimaryKey(Long id);
	
	int insert(FaultInfo faultInfo);
	
	int insertSelective(FaultInfo faultInfo);
	
	
	int updateByPrimaryKeySelective(FaultInfo faultInfo);

}
