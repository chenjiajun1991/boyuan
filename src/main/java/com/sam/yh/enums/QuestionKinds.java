package com.sam.yh.enums;

public enum QuestionKinds {
	
    TROUBLE_LAMP("0", "故障报警灯"),
    SENSOR_VOLTAGE("3", "传感器电源"), 
    BRAKE_SOLENOID("4", "刹车电磁阀"),
    INDOOR_MOTOR_AND_DOME_LIGHT("5", "室内风扇、顶灯电源"),
    POWER_OF_WINDSCREEN_WIPER("6", "雨刮电源"),
    KLAXON("7", "电喇叭"),
    AIR_CONDITION_AND_WARM_WIND_POWER("8", "空调、暖风电源"),
    OIL_FAN("9", "油扇"),
    TOSS_THE_MOTOR_POWER("10", "抛送电机电源"),
    DISPLAYER("14", "显示器"),
    MASTER_CLUTCH("15", "主离合"),
    ENGINE_OIL_LEVEL_SENSOR("16", "油位传感器"),
    BUNCHER_SENSOR("16", "无级变速传感器器"),
    GRANARY_ALARM_LAMP("20", "粮仓报警灯"),
    HAND_BRAKE_INPUT_SPEED_ALARM("22", "手刹输入时检测到速度时报警"),
    VOLTAGE_ALARM("23", "电压报警"),
    
    STOP_LAMP_SWITCH("24", "制动灯开关"),
    HAND_BREAK_SWITCH("25", "手刹开关"),
    WIPER_WASH("26", "雨刮器"),
    REVERSING_SWITCH("27", "倒车开关"),
    EMPTY_FILTER_ALARM("28", "空滤报警"),
    KEY_BAND_2("29", "钥匙2档"),
    KEY_BAND_4("30", "钥匙4档"),
    AIR_FAN_LIGHT_WITHIN("31", "风扇、内视灯"),
    PEELING_MACHINE_LORD_SWITCH("32", "剥皮机主离合开关"),
    EXHAUST_FAN("33", "排风扇"),
    BEFORE_WORKING_LAMP_SWITCH("34", "前工作灯开关"),
    KEY_BAND_1("35", "钥匙1档"),
    BREAK_LAMP("36", "制动灯"),
    ELECTROMAGNET_PUMP("38", "电磁泵"),
    ENGINE_EXCITATION("39", "发动机励磁"),
    RIGHT_TURN_LIGHT("40", "右转灯"),
    ECU_POWER_CONTROL("41", "ECU电源控制"),
    LEFT_TURN_LIGHT("42", "左转灯"),
    BACKUP_TURN_LIGHT("43", "倒车灯"),
    FRONT_WORK_LIGHT("44", "前工作灯"),
    ECU_POWER_SUPPLY_1("45","ECU供电1"),
    ENGINE_STARTER_RELAY("46","发动机启动继电器"),
    ECU_POWER_SUPPLY_2("47","ECU供电2"),
    HEADLIGHTS_ON_FULL_BEAM("48","远光灯"),
    DIPPED_HEADLIGHT("49","近光灯"),
    AFTER_WORKING_LIGHT("50","后工作灯"),
    WIDTH_LAMP("51","示宽灯"),
    TOSS_THE_MOTOR("68","抛送电机"),
    PEELING_MACHINE("69", "剥皮机"),
   
    
    WIDTH_LAMPL("51","示宽灯");

    
    private String type;
    private String desc;

    private QuestionKinds(String type, String desc) {
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
