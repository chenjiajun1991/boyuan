package com.sam.yh.model;

import java.util.Date;

public class PubBatteryInfo {
    private Long id;

    private Integer batteryId;

    private String btyPubSn;

    private String bytImei;

    private String ownerPhone;

    private String longitude;

    private String latitude;

    private String temperature;
    
    private String oilPressure;
    
    private String engineSpeed;
    
    private String speed;
    
    private String drumFlowSpeed;
    
    private String rethresherSpeed;
    
    private String engineStatus;
    
    private String motorPower;

    private String voltage;

    private String status;

    private Date receiveDate;

    private Date sampleDate;

    public PubBatteryInfo() {
        super();
    }

    public PubBatteryInfo(BatteryInfoNst batteryInfo) {
        this.id = batteryInfo.getId();
        this.batteryId = batteryInfo.getBatteryId();
        this.longitude = batteryInfo.getLongitude();
        this.latitude = batteryInfo.getLatitude();
        this.temperature = batteryInfo.getTemperature();
        this.oilPressure = batteryInfo.getOilPressure();
        this.engineSpeed = batteryInfo.getEngineSpeed();
        this.speed = batteryInfo.getSpeed();
        this.drumFlowSpeed = batteryInfo.getDrumFlowSpeed();
        this.rethresherSpeed = batteryInfo.getRethresherSpeed();
        this.voltage = batteryInfo.getVoltage();
        this.status = batteryInfo.getStatus();
        this.engineStatus = batteryInfo.getEngineStatus();
        this.motorPower = batteryInfo.getMotorPower();
        this.receiveDate = batteryInfo.getReceiveDate();
        this.sampleDate = batteryInfo.getSampleDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(Integer batteryId) {
        this.batteryId = batteryId;
    }

    public String getBtyPubSn() {
        return btyPubSn;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public String getBytImei() {
        return bytImei;
    }

    public void setBytImei(String bytImei) {
        this.bytImei = bytImei;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public void setBtyPubSn(String btyPubSn) {
        this.btyPubSn = btyPubSn == null ? null : btyPubSn.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? null : temperature.trim();
    }
    

    public String getOilPressure() {
		return oilPressure;
	}

	public void setOilPressure(String oilPressure) {
		this.oilPressure = oilPressure;
	}

	public String getEngineSpeed() {
		return engineSpeed;
	}

	public void setEngineSpeed(String engineSpeed) {
		this.engineSpeed = engineSpeed;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDrumFlowSpeed() {
		return drumFlowSpeed;
	}

	public void setDrumFlowSpeed(String drumFlowSpeed) {
		this.drumFlowSpeed = drumFlowSpeed;
	}

	public String getRethresherSpeed() {
		return rethresherSpeed;
	}

	public void setRethresherSpeed(String rethresherSpeed) {
		this.rethresherSpeed = rethresherSpeed;
	}

	public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage == null ? null : voltage.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEngineStatus() {
		return engineStatus;
	}

	public void setEngineStatus(String engineStatus) {
		this.engineStatus = engineStatus;
	}

	public String getMotorPower() {
		return motorPower;
	}

	public void setMotorPower(String motorPower) {
		this.motorPower = motorPower;
	}

	public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public Date getSampleDate() {
        return sampleDate;
    }

    public void setSampleDate(Date sampleDate) {
        this.sampleDate = sampleDate;
    }
}