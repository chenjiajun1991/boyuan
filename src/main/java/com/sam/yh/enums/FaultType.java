package com.sam.yh.enums;

public enum FaultType {
	    NORMAL("0", "正常"),
	    SHORT_CIRCUIT("1", "短路"), 
	    OPEN_CIRCUIT("2", "断路"),
	    LOCKED_ROTOR("2", "堵转");

	    private String type;
	    private String desc;

	    private FaultType(String type, String desc) {
	        this.type = type;
	        this.desc = desc;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getDesc() {
	        return desc;
	    }

	    public void setDesc(String desc) {
	        this.desc = desc;
	    }

}
