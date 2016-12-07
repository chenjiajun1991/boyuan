package com.sam.yh.dao;

import com.sam.yh.model.Enginer;

public interface EngineMapper {
	
	int insert(Enginer record);
	
	int deleteBtyById(int btyId);
	
	int updateByBtyId(Enginer record);
	
	Enginer selectByBtyId(int btyId);
	
}
