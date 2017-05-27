package com.sam.yh.dao;

import java.util.List;

import com.sam.yh.model.EquipmentState;

public interface EquipmentStateMapper {
	
	EquipmentState selectByPrimaryKey(Long id);
	
	List<EquipmentState> selectByBtyId(int batteryId);
	
	int deleteByPrimaryKey(Long id);
	
	int insert(EquipmentState infos);
	
	int insertSelective(EquipmentState infos);

}
