package com.sam.yh.dao;

import com.sam.yh.model.LockInfo;

public interface LockInfoMapper {
	
	LockInfo selectByBtyId(int batteryId);
	
	int deleteBtyById(int batteryId);
	
	int insert(LockInfo lockInfo);
	
	int updateByBtyId(LockInfo lockInfo);

}
