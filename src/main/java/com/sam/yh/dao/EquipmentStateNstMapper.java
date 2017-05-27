package com.sam.yh.dao;

import com.sam.yh.model.EquipmentState;

public interface EquipmentStateNstMapper {
	
	EquipmentState selectByPrimaryKey(Long id);
	
	EquipmentState selectByBtyId(int batteryId);
	
	int deleteByPrimaryKey(Long id);
	
	int insert(EquipmentState infos);
	
	int insertSelective(EquipmentState infos);
	
	int updateByPrimaryKeySelective(EquipmentState infos);

}
