package com.sam.yh.model;

import java.util.Date;

public class BatteryInfo {
    private Long id;

    private Integer batteryId;

    private String longitude;

    private String latitude;

    private String temperature;
    
    private String oilPressure;
    
    private String engineSpeed;
    
    private String speed;
    
    private String drumFlowSpeed;
    
    private String rethresherSpeed;
    
    private String engineStatus;
    
    private String ecuPower;
    
    private String motorPower;
    
	private String voltage;

    private String status;

    private Date receiveDate;

    private Date sampleDate;

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
		this.oilPressure = oilPressure == null ? null : oilPressure.trim();
	}

	public String getEngineSpeed() {
		return engineSpeed;
	}

	public void setEngineSpeed(String engineSpeed) {
		this.engineSpeed = engineSpeed == null ? null : engineSpeed.trim();
	}

	public String getSpeed() {
		return speed;
	}
	
	public String getDrumFlowSpeed() {
		return drumFlowSpeed;
	}

	public void setDrumFlowSpeed(String drumFlowSpeed) {
		this.drumFlowSpeed = drumFlowSpeed == null ? null : drumFlowSpeed.trim();
	}

	public String getRethresherSpeed() {
		return rethresherSpeed;
	}

	public void setRethresherSpeed(String rethresherSpeed) {
		this.rethresherSpeed = rethresherSpeed == null ? null : rethresherSpeed.trim();
	}

	public void setSpeed(String speed) {
		this.speed = speed == null ? null : speed.trim();
	}   
	
    public String getEngineStatus() {
		return engineStatus;
	}

	public void setEngineStatus(String engineStatus) {
		this.engineStatus = engineStatus == null ? null : engineStatus.trim();
	}

	public String getEcuPower() {
		return ecuPower;
	}

	public void setEcuPower(String ecuPower) {
		this.ecuPower = ecuPower == null ? null : ecuPower.trim();
	}

	public String getMotorPower() {
		return motorPower;
	}

	public void setMotorPower(String motorPower) {
		this.motorPower = motorPower == null ? null : motorPower.trim();
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

	@Override
	public String toString() {
		return "BatteryInfo [id=" + id + ", batteryId=" + batteryId
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", temperature=" + temperature + ", oilPressure="
				+ oilPressure + ", engineSpeed=" + engineSpeed + ", speed="
				+ speed + ", drumFlowSpeed=" + drumFlowSpeed
				+ ", rethresherSpeed=" + rethresherSpeed + ", engineStatus="
				+ engineStatus + ", ecuPower=" + ecuPower + ", motorPower="
				+ motorPower + ", voltage=" + voltage + ", status=" + status
				+ ", receiveDate=" + receiveDate + ", sampleDate=" + sampleDate
				+ "]";
	}
    
    
}