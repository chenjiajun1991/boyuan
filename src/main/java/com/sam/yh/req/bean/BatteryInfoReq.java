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
    
    private String speedz; 
    private String speedf;
  
    private String enginestatus;
    private String ecupower;
    private String motorpower;
    
    
    private String hexString;
    
    private String hex1;
    private String hex2;
    
    private String string;
    private String len;
    
    private String version;
    
    
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
        return temperature == null ? "25" :temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature == null ? "25" :temperature.trim();
    }

    public String getVoltage() {
        return voltage == null ? "600" : voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage == null ? "600" : voltage.trim();
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
	
	
	public String getSpeedz() {
		return speedz;
	}

	public void setSpeedz(String speedz) {
		this.speedz = speedz == null ? "0" : speedz;
	}

	public String getSpeedf() {
		return speedf;
	}

	public void setSpeedf(String speedf) {
		this.speedf = speedf == null ? "0" : speedf;;
	}
	

	public String getEnginestatus() {
		return enginestatus;
	}

	public void setEnginestatus(String enginestatus) {
		this.enginestatus = enginestatus;
	}

	public String getEcupower() {
		return ecupower;
	}

	public void setEcupower(String ecupower) {
		this.ecupower = ecupower;
	}

	public String getMotorpower() {
		return motorpower;
	}

	public void setMotorpower(String motorpower) {
		this.motorpower = motorpower;
	}
	

	public String getHexString() {
		return hexString;
	}

	public void setHexString(String hexString) {
		this.hexString = hexString;
	}
	

	public String getHex1() {
		return hex1;
	}

	public void setHex1(String hex1) {
		this.hex1 = hex1;
	}

	public String getHex2() {
		return hex2;
	}

	public void setHex2(String hex2) {
		this.hex2 = hex2;
	}
	
	

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}
	
	

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "BatteryInfoReq [imei=" + imei + ", imsi=" + imsi
				+ ", phonenumber=" + phonenumber + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", temperature=" + temperature
				+ ", voltage=" + voltage + ", pressure=" + pressure
				+ ", motorspeed=" + motorspeed + ", speed=" + speed
				+ ", speedz=" + speedz + ", speedf=" + speedf
				+ ", enginestatus=" + enginestatus + ", ecupower=" + ecupower
				+ ", motorpower=" + motorpower + ", hexString=" + hexString
				+ ", hex1=" + hex1 + ", hex2=" + hex2 + ", string=" + string
				+ ", len=" + len + ", version=" + version + "]";
	}

}
