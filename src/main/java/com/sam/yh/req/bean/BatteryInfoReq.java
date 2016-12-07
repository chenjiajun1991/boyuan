package com.sam.yh.req.bean;

import java.io.Serializable;

public class BatteryInfoReq implements Serializable {

    private static final long serialVersionUID = 1L;
    private String imei;
    private String imsi;
    private String phonenumber;
    private String longitude;
    private String latitude;
    private String temperature;
    private String voltage;
    
    private String pressure;
    private String motorspeed;
    private String speed;
    
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
    }

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}



	public String getMotorspeed() {
		return motorspeed;
	}

	public void setMotorspeed(String motorspeed) {
		this.motorspeed = motorspeed;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	@Override
	public String toString() {
		return "BatteryInfoReq [imei=" + imei + ", imsi=" + imsi
				+ ", phonenumber=" + phonenumber + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", temperature=" + temperature
				+ ", voltage=" + voltage + ", pressure=" + pressure
				+ ", motorspeed=" + motorspeed + ", speed=" + speed + "]";
	}
    


}
