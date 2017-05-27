package com.sam.yh.dao;

import com.sam.yh.model.OrderRegister;

public interface OrderRegisterMapper {
	
	OrderRegister selectByPrimaryKey(Long id);
	
	OrderRegister selectByBtyId(int batteryId);
	
	int deleteByPrimaryKey(Long id);
	
	int insertSelective(OrderRegister record);
	
	int updateByPrimaryKeySelective(OrderRegister record);
}
