package com.sam.yh.resp.bean;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserBtyInfo implements Serializable {

    private static final long serialVersionUID = 2393329207228215900L;
    private String btyPubSn;
    private String btyImei;
    private String ownerPhone;
    private String longitude;
    private String latitude;
    private String temperature;
    private String oilPressure;
    private String engineSpeed;
    private String speed;
    private String voltage;
    private String power;
    private String engineStatus;
    private String motorPower;
    private String drumFlowSpeed;
    private String rethresherSpeed;
    private String receiveDate;

    public String getBtyPubSn() {
        return btyPubSn;
    }

    public void setBtyPubSn(String btyPubSn) {
        this.btyPubSn = btyPubSn;
    }

    public String getBtyImei() {
        return btyImei;
    }

    public void setBtyImei(String btyImei) {
        this.btyImei = btyImei;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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

	public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
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
	
	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	@Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
