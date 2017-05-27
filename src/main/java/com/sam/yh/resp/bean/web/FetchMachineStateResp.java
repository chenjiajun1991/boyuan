package com.sam.yh.resp.bean.web;

import com.sam.yh.model.BatteryInfoNst;
import com.sam.yh.model.EquipmentState;

public class FetchMachineStateResp {
	
	private EquipmentState equipmentState;
	
	private BatteryInfoNst batteryInfoNst;

	public EquipmentState getEquipmentState() {
		return equipmentState;
	}

	public void setEquipmentState(EquipmentState equipmentState) {
		this.equipmentState = equipmentState;
	}

	public BatteryInfoNst getBatteryInfoNst() {
		return batteryInfoNst;
	}

	public void setBatteryInfoNst(BatteryInfoNst batteryInfoNst) {
		this.batteryInfoNst = batteryInfoNst;
	}
	
}
