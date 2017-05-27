package com.sam.yh.service.impl;

import io.netty.channel.Channel;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.sam.yh.common.DistanceUtils;
import com.sam.yh.common.QuestionTypeUtils;
import com.sam.yh.common.RandomCodeUtils;
import com.sam.yh.common.TempUtils;
import com.sam.yh.common.baidugps.SendGpsToBaiduServer;
import com.sam.yh.common.msg.UmsSmsServiceImpl;
import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.crud.exception.FetchBtyInfoException;
import com.sam.yh.dao.BatteryInfoMapper;
import com.sam.yh.dao.BatteryInfoNstMapper;
import com.sam.yh.dao.BatteryMapper;
import com.sam.yh.dao.EquipmentStateMapper;
import com.sam.yh.dao.EquipmentStateNstMapper;
import com.sam.yh.dao.FaultInfoMapper;
import com.sam.yh.dao.FaultInfoNstMapper;
import com.sam.yh.dao.OrderRegisterMapper;
import com.sam.yh.dao.UserMapper;
import com.sam.yh.enums.BatteryStatus;
import com.sam.yh.model.Battery;
import com.sam.yh.model.BatteryInfo;
import com.sam.yh.model.BatteryInfoNst;
import com.sam.yh.model.BeiZCommonData;
import com.sam.yh.model.EquipmentState;
import com.sam.yh.model.FaultInfo;
import com.sam.yh.model.FaultInfoNst;
import com.sam.yh.model.OrderRegister;
import com.sam.yh.model.User;
import com.sam.yh.model.UserBattery;
import com.sam.yh.model.ZHCommonData;
import com.sam.yh.parse.BeiZCommonMessage;
import com.sam.yh.parse.ZHCommonMessage;
import com.sam.yh.req.bean.BatteryInfoReq;
import com.sam.yh.req.bean.UploadFaultMessageReq;
import com.sam.yh.req.bean.UploadOrderRegisterMsgReq;
import com.sam.yh.service.BatteryService;
import com.sam.yh.service.UserBatteryService;
import com.sam.yh.service.UserCodeService;
import com.sam.yh.service.socket.SamBtyDataHandler;

@Service
public class BatteryServiceImpl implements BatteryService {

    private static final Logger logger = LoggerFactory.getLogger(BatteryServiceImpl.class);

    @Resource
    private BatteryMapper batteryMapper;

    @Resource
    private BatteryInfoNstMapper batteryInfoNstMapper;

    @Resource
    private BatteryInfoMapper batteryInfoMapper;

    @Resource
    private UserBatteryService userBatteryService;

    @Resource
    private UserMapper userMapper;
    
    @Resource
    private FaultInfoMapper faultInfoMapper;
    
    @Resource
    private FaultInfoNstMapper faultInfoNstMapper;
    
    @Resource
    private EquipmentStateMapper equipmentStateMapper;
    
    @Resource 
    private EquipmentStateNstMapper equipmentStateNstMapper;
    
    @Resource
    private OrderRegisterMapper orderRegisterMapper;

    @Resource
    private UserCodeService userCodeService;
    
    @Resource
    private Long MoveDis;
    
    @Resource
    private String servicePhone;
    
    @Resource
    private String testPhone;
    
    @Resource
    private SendGpsToBaiduServer sendGpsToBaiduServer;
    
    @Resource
    private UmsSmsServiceImpl umsSmsServiceImpl;
 
 
    @Override
    public Future<Battery> uploadBatteryInfo(BatteryInfoReq batteryInfoReqVo) throws CrudException {
        if (batteryInfoReqVo == null) {
            return null;
        }
        logger.info("Upload battery info request:" + batteryInfoReqVo);
        Battery battery = fetchBtyByIMEI(batteryInfoReqVo.getImei());
        if (battery == null) {
        	
        	logger.info("还未绑定的电池:" + batteryInfoReqVo);
        	
        	//test
//        	 Date now = new Date();
//             battery = new Battery();
//             battery.setSn(batteryInfoReqVo.getImei());
//             battery.setPubSn(RandomCodeUtils.genBtyPubSn());
//             battery.setImei(batteryInfoReqVo.getImei());
//             battery.setIccid("123456");
//             battery.setSimNo(batteryInfoReqVo.getImei());
//             battery.setBtyType(true);
//             battery.setStatus(BatteryStatus.NORMAL.getStatus());
//             battery.setResellerId(33);
//             battery.setCityId(100);
//             battery.setSaleStatus(true);
//             battery.setCreateDate(now);
//             battery.setSaleDate(now);
//             battery.setBtyQuantity(4);
//
//            addBattery(battery);
        	 	
            return new AsyncResult<Battery>(null);
        }
       
        
        if (battery.getImsi() == null || battery.getGsmSimNo() == null) {
            if (batteryInfoReqVo.getImsi() != null) {
                battery.setImsi(batteryInfoReqVo.getImsi());
            }
            if (batteryInfoReqVo.getPhonenumber() != null) {
                battery.setGsmSimNo(batteryInfoReqVo.getPhonenumber());
            }
            batteryMapper.updateByPrimaryKeySelective(battery);
        }
        
    
        BatteryInfo info = new BatteryInfo();
        info.setBatteryId(battery.getId());
        info.setLongitude(batteryInfoReqVo.getLongitude());
        info.setLatitude(batteryInfoReqVo.getLatitude());
//        info.setTemperature(convertAdcToTemp(batteryInfoReqVo.getTemperature()));
  
        String str = batteryInfoReqVo.getString();
        
        if(str != null){
      
        	
        	if(str.length() < 12){
        
        		
        		BeiZCommonData beiZCommonData = BeiZCommonMessage.parseString(str);
        		info.setVoltage(beiZCommonData.getVoltage());
        		info.setTemperature("20");
        		
//        		if(str.length() > 4){
//        			info.setStatus(str.substring(4));
//        		}
        		
        	}else{
        		logger.info("test:" + "5");
        	
        		ZHCommonData zhCommonData = ZHCommonMessage.parseString(str);
            	
            	info.setOilPressure(zhCommonData.getOilPressure());
            	info.setTemperature(zhCommonData.getCwt());
                info.setEngineSpeed(zhCommonData.getMotorSpeed());
                info.setDrumFlowSpeed(zhCommonData.getSpeedz());
                info.setRethresherSpeed(zhCommonData.getSpeedf());
        		
        	}
        	
        	
        }

    
//        info.setSpeed(batteryInfoReqVo.getSpeed());
        
//        info.setEngineStatus(batteryInfoReqVo.getHexString());
//        if(batteryInfoReqVo.getHex1()!=null){
//        	info.setEngineStatus(batteryInfoReqVo.getHex1()+batteryInfoReqVo.getHex2());
//        }else{
//        	info.setEngineStatus(batteryInfoReqVo.getHexString());
//        }
        
        
//        info.setEcuPower(batteryInfoReqVo.getEcupower());
//        info.setMotorPower(batteryInfoReqVo.getMotorpower());
        
        
        String voltage = convertAdcToVo(batteryInfoReqVo.getVoltage());
//        info.setVoltage(voltage);
    
        
        // 临时代码，调整电池的节数
        int BtyQuantity = battery.getBtyQuantity() == null ? 4 : battery.getBtyQuantity();
        Date BtySaleDate = battery.getSaleDate();
        Date now  = new Date();
        
        //调整4节组的临界电压值
        if (BtyQuantity == 4 && Double.parseDouble(voltage) > 62d && now.before(DateUtils.addHours(BtySaleDate, 2))) {
            Battery temBattery = new Battery();
            temBattery.setId(battery.getId());
            BtyQuantity = 5;
            temBattery.setBtyQuantity(BtyQuantity);
            batteryMapper.updateByPrimaryKeySelective(temBattery);
        }
        
        
        //当电压过高或过低时发短信提示用户
        if(BtyQuantity==4){
        	if(Double.parseDouble(voltage)>62d){
        		sendVolageWarningMsg(battery, voltage, 1);
        	}else if(Double.parseDouble(voltage)<41.5d){
        		sendVolageWarningMsg(battery, voltage, 0);
        	}
        }else if(BtyQuantity==5){
            if(Double.parseDouble(voltage)>77.5d){
            	sendVolageWarningMsg(battery, voltage, 1);
            	 Date date=new Date();
            	 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 String dateString = formatter.format(date);
                 logger.info("sendHigeVoltageTime1:" + dateString);
            	
        	}else if(Double.parseDouble(voltage)<52d){
        		sendVolageWarningMsg(battery, voltage, 0);
        	}
        }
        
        
        //增加一个剪断信号线电压突变报警
        if(Double.parseDouble(voltage)<15.5d){
        	sendViolentDestroyClient(battery);
        	sendViolentDestroyService(servicePhone, battery);
        }
        
      
        // TODO
        // info.setSampleDate(batteryInfoReqVo.getSampleDate());
        info.setSampleDate(new Date());
        BatteryStatus status = getBatteryStatus(batteryInfoReqVo);
        info.setStatus(status.getStatus());
        info.setReceiveDate(new Date());
 
        
        

        

        batteryInfoMapper.insert(info);


        updateBatteryInfoNst(info);
    
        
//        if(batteryInfoReqVo.getHex1() != null && batteryInfoReqVo.getHex2() != null){
//        	
//        	
//        	String hexString = batteryInfoReqVo.getHex1()+batteryInfoReqVo.getHex2();
//        	
//        	logger.info("hexString:" + hexString);
//        	
//        	if(hexString.length()%2 == 0 && hexString.length() >= 12){	
//        		addEquipmentState(battery, hexString);		
//        	} 	
//        }
        
        
        //若经纬度信息不为零，发送电池信息到百度鹰眼服务器
        if(Double.parseDouble(batteryInfoReqVo.getLatitude())!=0&&Double.parseDouble(batteryInfoReqVo.getLongitude())!=0){
        	
        	String result=sendGpsToBaiduServer.sendGPStoBaiDu(batteryInfoReqVo.getImei(), 
            		Double.parseDouble(batteryInfoReqVo.getLatitude()),Double.parseDouble(batteryInfoReqVo.getLongitude()) ,
            		System.currentTimeMillis()/1000);
        	try {
				String s=new String(result.getBytes(),"utf-8");
				logger.info("sendGpsToBaiduServer:" +s+batteryInfoReqVo.getLatitude()+","+batteryInfoReqVo.getLongitude());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        		
        }else{
        	Battery bty=batteryMapper.selectByIMEI(batteryInfoReqVo.getImei());
        	BatteryInfoNst nstInfo = batteryInfoNstMapper.selectByBtyId(bty.getId());
        	
        	String result=sendGpsToBaiduServer.sendGPStoBaiDu(batteryInfoReqVo.getImei(), 
            		Double.parseDouble(nstInfo.getLatitude()),Double.parseDouble(nstInfo.getLongitude()),
            		System.currentTimeMillis()/1000);
        	try {
				String s=new String(result.getBytes(),"utf-8");
				logger.info("sendGpsToBaiduServerNst:" +s+nstInfo.getLatitude()+","+nstInfo.getLongitude());
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        

//        if (TempUtils.isWarning(batteryInfoReqVo.getTemperature())) {
//            sendWarningMsg(battery);
//        }

//        if (Double.parseDouble(voltage) < (BtyQuantity * 10.5d + 1)) {
////            sendVolageWarningMsg(battery, voltage);
//        }

        if (BatteryStatus.LOCKED.getStatus().equals(battery.getStatus())&&Double.parseDouble(batteryInfoReqVo.getLongitude())!=0) {
            long moveDis = (long) DistanceUtils.GetDistance(batteryInfoReqVo.getLongitude(), batteryInfoReqVo.getLatitude(), battery.getLockLongitude(),
                    battery.getLockLatitude());
            if (moveDis > MoveDis) {
                sendMovingMsg(battery);
            }
        }

        return new AsyncResult<Battery>(battery);
    }

    private BatteryInfoNst updateBatteryInfoNst(BatteryInfo btyInfo) {
    
    	logger.info("Test:" +btyInfo.toString());
    	
        Integer batteryId = btyInfo.getBatteryId();
        BatteryInfoNst batteryInfoNst = batteryInfoNstMapper.selectByBtyId(batteryId);
        if (batteryInfoNst == null) {
            batteryInfoNst = new BatteryInfoNst();
            batteryInfoNst.setBatteryId(batteryId);
            copyProps(btyInfo, batteryInfoNst);
            batteryInfoNstMapper.insertSelective(batteryInfoNst);
//            batteryInfoNstMapper.insert(batteryInfoNst);
        } else {
            copyProps(btyInfo, batteryInfoNst);
//            batteryInfoNstMapper.updateByPrimaryKeySelective(batteryInfoNst);
            logger.info("Test:" +"1");
            
            batteryInfoNstMapper.updateByPrimaryKey(batteryInfoNst);
            logger.info("Test:" +"2");
        }

        return batteryInfoNst;

    }

    private void copyProps(BatteryInfo btyInfo, BatteryInfoNst batteryInfoNst) {
        BigDecimal longitude = new BigDecimal(btyInfo.getLongitude());
        BigDecimal latitude = new BigDecimal(btyInfo.getLatitude());
        if (BigDecimal.ZERO.compareTo(longitude) != 0) {
            batteryInfoNst.setLongitude(btyInfo.getLongitude());
        }
        if (BigDecimal.ZERO.compareTo(latitude) != 0) {
            batteryInfoNst.setLatitude(btyInfo.getLatitude());
        }
        batteryInfoNst.setTemperature(btyInfo.getTemperature());
        batteryInfoNst.setOilPressure(btyInfo.getOilPressure());
        batteryInfoNst.setEngineSpeed(btyInfo.getEngineSpeed());
        batteryInfoNst.setSpeed(btyInfo.getSpeed());
        
        batteryInfoNst.setDrumFlowSpeed(btyInfo.getDrumFlowSpeed());
        batteryInfoNst.setRethresherSpeed(btyInfo.getRethresherSpeed());
        
        batteryInfoNst.setEngineStatus(btyInfo.getEngineStatus());
        batteryInfoNst.setEcuPower(btyInfo.getEcuPower());
        batteryInfoNst.setMotorPower(btyInfo.getMotorPower());
        batteryInfoNst.setVoltage(btyInfo.getVoltage());
        batteryInfoNst.setStatus(btyInfo.getStatus());
        batteryInfoNst.setReceiveDate(new Date());
        batteryInfoNst.setSampleDate(new Date());
    }

    
    private String convertAdcToTemp(String adc) {
    	//增加温度超过60度时转换的部分
//        return TempUtils.isWarning(adc) ? adc : TempUtils.getTemp(adc);
    	return TempUtils.getTemp(adc);
    }

    private String convertAdcToVo(String adc) {
        float tem = Float.valueOf(adc);
        float vol = (float) ((int) ((tem / 10.73) * 10)) / 10;
        return String.valueOf(vol);
    }

    private BatteryStatus getBatteryStatus(BatteryInfoReq batteryInfoReqVo) {
        BatteryStatus status = BatteryStatus.NORMAL;
        String adcTmp = batteryInfoReqVo.getTemperature();
        if (TempUtils.isWarning(adcTmp)) {
            status = BatteryStatus.T_ABNORMAL;
        }

//        float adcVol = Float.valueOf(convertAdcToVo(batteryInfoReqVo.getVoltage()));
//        if (adcVol < 42 || adcVol > 77.5) {
//            status = BatteryStatus.V_ABNORMAL;
//        }

        return status;
    }

    private void sendWarningMsg(Battery battery) throws CrudException {

        UserBattery userBattery = userBatteryService.fetchUserByBtyId(battery.getId());
        User user = userMapper.selectByPrimaryKey(userBattery.getUserId());
        userCodeService.sendWarningMsg(user.getMobilePhone(), battery.getImei());
    }

    private void sendVolageWarningMsg(Battery battery, String voltage,int flag) throws CrudException {

        UserBattery userBattery = userBatteryService.fetchUserByBtyId(battery.getId());
        User user = userMapper.selectByPrimaryKey(userBattery.getUserId());
        userCodeService.sendWarningMsg(user.getMobilePhone(), battery.getImei(), voltage,flag);
    }

    private void sendMovingMsg(Battery battery) throws CrudException {

        UserBattery userBattery = userBatteryService.fetchUserByBtyId(battery.getId());
        User user = userMapper.selectByPrimaryKey(userBattery.getUserId());
        userCodeService.sendMovingMsg(user.getMobilePhone(), battery.getImei());
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        logger.info("sendMovingMsgTime1:" + dateString);
    }
    
  //增加一个剪断信号线电压突变报警
    private void  sendViolentDestroyClient(Battery battery) throws CrudException{
    	UserBattery userBattery = userBatteryService.fetchUserByBtyId(battery.getId());
        User user = userMapper.selectByPrimaryKey(userBattery.getUserId());
        userCodeService.sendViolentDestroyClient(user.getMobilePhone(), battery.getImei());
    }
    
    private void sendViolentDestroyService(String servciePhone,Battery battery) throws CrudException{
    	UserBattery userBattery = userBatteryService.fetchUserByBtyId(battery.getId());
        User user = userMapper.selectByPrimaryKey(userBattery.getUserId());
        userCodeService.sendViolentDestroyService(servciePhone, battery.getImei(), user.getUserName(), user.getMobilePhone());
    }
    
    

    @Override
    public Battery addBattery(Battery battery) {
        batteryMapper.insertSelective(battery);
        return battery;
    }

    @Override
    public Battery fetchBtyByIMEI(String imei) {
        return batteryMapper.selectByIMEI(imei);
    }

    @Override
    public Battery fetchBtyByPubSn(String pubSn) {
        return batteryMapper.selectByPubSn(pubSn);
    }

    @Override
    public Battery fetchBtyBySimNo(String simNo) {
        return batteryMapper.selectBySimNo(simNo);
    }

    @Override
    public Battery fetchBtyBySN(String btySn) {
        return batteryMapper.selectBySn(btySn);
    }

    @Override
    public int countSoldBtys(int resellerId) {
        return batteryMapper.countByReseller(resellerId);
    }

    @Override
    public int countCityBtys(int cityId) {
        return batteryMapper.countByCity(cityId);
    }

    @Override
    public BatteryInfoNst fetchBtyInfo(String btySimNo) throws CrudException {
        Battery battery = batteryMapper.selectBySimNo(btySimNo);
        if (battery == null) {
            throw new FetchBtyInfoException("电池不存在");
        }

        BatteryInfoNst nstInfo = batteryInfoNstMapper.selectByBtyId(battery.getId());

        if (nstInfo != null) {
            return nstInfo;
        }
        BatteryInfo batteryInfo = batteryInfoMapper.selectByBtyId(battery.getId());
        if (batteryInfo == null) {
            throw new FetchBtyInfoException("未接收到此电池发送的信息");
        }
        return updateBatteryInfoNst(batteryInfo);

    }

    @Override
    public Battery fetchBtyById(int id) {
        return batteryMapper.selectByPrimaryKey(id);
    }

	@Override
	public int fetchRssi(int btyId) throws CrudException {
		 int rssi = 1;
		 BatteryInfoNst btyInfoNst = batteryInfoNstMapper.selectByBtyId(btyId);
		 if(btyInfoNst == null){
			 throw new FetchBtyInfoException("未接收到此电池发送的信息");
		 }
		 
		 BatteryInfo btyInfo = batteryInfoMapper.selectByBtyId(btyId);
		 if(btyInfo == null){
			 throw new FetchBtyInfoException("未接收到此电池发送的信息");
		 }
		 
		 Date now = new Date();
		 //最后一次上报数据的时间与现在间隔3分钟以内
		if(DateUtils.addMinutes(btyInfoNst.getReceiveDate(), 3).after(now)){
			if(btyInfoNst.getLongitude().equals(btyInfo.getLongitude())){
				rssi = 0;
			}else {
				rssi = 1;
			}
		}
		
		if(DateUtils.addMinutes(btyInfoNst.getReceiveDate(), 3).before(now) && DateUtils.addMinutes(btyInfoNst.getReceiveDate(), 30).after(now)){
			rssi = 1;
		}
		
		if(DateUtils.addMinutes(btyInfoNst.getReceiveDate(), 30).before(now)){
			rssi = 2;
		}
		
		return rssi;
	}
	
	private boolean sendLockResp(String btyImei,String message) {
		Battery battery = batteryMapper.selectByIMEI(btyImei);
		if (battery == null) {
			logger.error("电池不存在, " + btyImei);
			return false;
		}
		boolean hasConn = false;

		ConcurrentHashMap<String, Channel> channelMap = SamBtyDataHandler.channelMap;
		Channel channel = channelMap.get(battery.getImei());
		if (channel != null) {
			channel.writeAndFlush(message+"\n");
			hasConn = true;
		}

		if (!hasConn) {
			logger.error("未获取到长连接, " + btyImei);
		}
		return hasConn;
	}
	

	@Override
	public Future<Battery> uploadFaultInfo(UploadFaultMessageReq faultInfoReqVo)
			throws CrudException {
		
		if(faultInfoReqVo == null){
			return null;
		}
		
        logger.info("Upload faultInfo request:" + faultInfoReqVo);
   
        
        Battery battery = fetchBtyByIMEI(faultInfoReqVo.getFaultimei());
        
        if(battery == null){
        	logger.info("还未绑定设备:" + faultInfoReqVo);
            return new AsyncResult<Battery>(null);	
        }
     
          
        String hexString= faultInfoReqVo.getByte1();
           
        String hexString2 = faultInfoReqVo.getByte2();
        
        String hexString3 = faultInfoReqVo.getString();
        
        String markType = RandomCodeUtils.genSalt();
        
        String tempString = faultInfoReqVo.getString();
        

        
        if(tempString.length()>=6){
 
        	
        	if(tempString.substring(5, 6).equals("1")){
        		
      
        		
        		battery.setStatus("4");
     
    			
    			batteryMapper.updateByPrimaryKeySelective(battery);
    	
            	
            }else if(tempString.substring(5, 6).equals("2")){
          
            	battery.setStatus("5");
     
    			
    			batteryMapper.updateByPrimaryKeySelective(battery);
    	
            }
        	
        	
        }
        
        
        
        
        
        if( hexString != null && hexString2 != null){
        
        	addFaultMessage(battery, hexString,hexString2, markType);
        }
        
        if(hexString3 != null && hexString3.length() >= 4){
        	addFaultMessage(battery, hexString,hexString2,hexString3.substring(2, 4), markType);	
        }
        
        if(hexString3 != null && hexString3.length()>= 6){
        	String temp = hexString3.substring(4, 6);
        	
        	String respString = QuestionTypeUtils.hexString2binaryString(temp);
        	
        	if(respString != null && respString.length() >3){
        		if(respString.substring(respString.length()-1, respString.length()).equals("1")){
        			
//        			sendLockResp(faultInfoReqVo.getFaultimei(), "wrdata,001,0");
        			
        			battery.setStatus("4");

        			
        			batteryMapper.updateByPrimaryKeySelective(battery);
        			
        			
        			
        			logger.info("successlock resp:" + battery.getImei());
        		}
        		if(respString.substring(respString.length()-2, respString.length()-1).equals("1")){
        			
//        			sendLockResp(faultInfoReqVo.getFaultimei(), "wrdata,001,0");
        			
        			battery.setStatus("5");
 
        			
        			batteryMapper.updateByPrimaryKeySelective(battery);
        			
        			logger.info("successunlock resp:" + battery.getImei());
        		}
        		if(respString.substring(respString.length()-3, respString.length()-2).equals("1")){
        			
//        			sendLockResp(faultInfoReqVo.getFaultimei(), "wrdata,001,0");
        			
//        			battery.setStatus("6");
//        			battery.setExpiryDate(new Date());
//        			
//        			batteryMapper.updateByPrimaryKeySelective(battery);
//        			
//        			logger.info("bindsuccess resp:" + battery.getImei());
        		}
        	}
        	
        }
        
        	
		// TODO Auto-generated method stub
        return new AsyncResult<Battery>(battery);
	}
	
	public void addFaultMessage(Battery battery,String hexString1,String hexString2,String hexString3,String markType) throws  CrudException{
		logger.info("addFaultMessage hexString3 info request:" + hexString3);
		List<String> indexList = QuestionTypeUtils.convertQuestion("C",hexString3);
		
		FaultInfoNst faultInfoNst = faultInfoNstMapper.selectByBtyId(battery.getId());
		
		if(faultInfoNst == null){
			FaultInfoNst infoNst = new FaultInfoNst();
			infoNst.setBatteryId(battery.getId());
			infoNst.setByte1(hexString1);
			infoNst.setByte2(hexString2);
			infoNst.setByte3(hexString3);
			infoNst.setIsFault(1);
			infoNst.setMarkType(markType);
			infoNst.setReceiveDate(new Date());
			faultInfoNstMapper.insertSelective(infoNst);
			
		}else{

			faultInfoNst.setByte3(hexString3);
			
			if(hexString3.equals("0")|| hexString3.equals("00")){
				faultInfoNst.setIsFault(faultInfoNst.getIsFault());
			}else{
				faultInfoNst.setIsFault(1);
			}
			faultInfoNst.setMarkType(markType);
			faultInfoNst.setReceiveDate(new Date());
					
			faultInfoNstMapper.updateByBtyIdSelective(faultInfoNst);
			
		}
		
		
		
		
		
		List<FaultInfo>faultInfos = faultInfoMapper.selectByBtyId(battery.getId());
				
		if(faultInfos != null && faultInfos.size() > 0){
			for(FaultInfo faultInfo : faultInfos){
				
					faultInfo.setIsNewest(faultInfo.getIsNewest()+1);
					faultInfoMapper.updateByPrimaryKeySelective(faultInfo);
				
			}
		}
		
		if(indexList != null && indexList.size()>0){
		 	logger.info("Upload battery info request:" + "00");
			for(String index : indexList){
			
				if(QuestionTypeUtils.getQuestionMessage(index) != null){
					
					FaultInfo info = new FaultInfo();
		         	info.setBatteryId(battery.getId());
		         	info.setCodeType(index);
		         	info.setFaultType(1);
		         	info.setFaultMessage(QuestionTypeUtils.getQuestionMessage(index));
		         	info.setIsNewest(2);
		         	info.setMarkType(markType);
		         	info.setByte1(hexString1);
		         	info.setByte2(hexString2);    	
		         	info.setByte3(hexString3);
		         	info.setReceiveDate(new Date());
		         	
		         	faultInfoMapper.insert(info);
		         	
//		          	userCodeService.sendErrorMsg(testPhone, QuestionTypeUtils.getQuestionMessage(index));
					
				}		
				
			}		
		}
			
		
	}
		
	
	public void addFaultMessage(Battery battery, String hexString, String hexString2,String markType) throws CrudException{
		
	
		FaultInfoNst faultInfoNst = faultInfoNstMapper.selectByBtyId(battery.getId());

		
		if(faultInfoNst == null){
			
			FaultInfoNst nstInfo = new FaultInfoNst();
			 
			nstInfo.setBatteryId(battery.getId());
			nstInfo.setMarkType(markType);
			if(hexString.equals("0000000000000000") && hexString2.equals("00000000")){
				nstInfo.setIsFault(0);
			}else{
				nstInfo.setIsFault(1);
			}
			nstInfo.setByte1(hexString);
			nstInfo.setByte2(hexString2);
			nstInfo.setReceiveDate(new Date());
			
		
			
			faultInfoNstMapper.insertSelective(nstInfo);		
				
		}else{
			
			faultInfoNst.setMarkType(markType);
			if(hexString.equals("0000000000000000") && hexString2.equals("00000000")){
				faultInfoNst.setIsFault(0);
			}else{
				faultInfoNst.setIsFault(1);
			}
			faultInfoNst.setByte1(hexString);
			faultInfoNst.setByte2(hexString2);
			faultInfoNst.setReceiveDate(new Date());
			
			faultInfoNstMapper.updateByBtyIdSelective(faultInfoNst);
	
		}
		


		List<FaultInfo>faultInfos = faultInfoMapper.selectByBtyId(battery.getId());
		

		List<String> indexList = QuestionTypeUtils.convertQuestion1(hexString,hexString2);
				
		
		if(faultInfos == null || faultInfos.size() == 0){

			
			if(indexList != null){

				for(String index :indexList){
					
					if(QuestionTypeUtils.getQuestionMessage(index) != null){
						
						FaultInfo info = new FaultInfo();
		             	info.setBatteryId(battery.getId());
		             	info.setCodeType(index);
		             	info.setFaultType(1);
		             	info.setFaultMessage(QuestionTypeUtils.getQuestionMessage(index));
		             	info.setIsNewest(1);
		             	info.setMarkType(markType);
		             	info.setByte1(hexString);
		             	info.setByte2(hexString2);
		             	info.setReceiveDate(new Date());
		             	
		             	faultInfoMapper.insert(info);
		             	
//		             	umsSmsServiceImpl.sendErrorMessage("13661645501", QuestionTypeUtils.getQuestionMessage(index));
		             	
		             	userCodeService.sendErrorMsg(testPhone, QuestionTypeUtils.getQuestionMessage(index));
		             	
						
					}
					
					
	             	
				}
			}
			
		}else{
			for(FaultInfo faultInfo :faultInfos){
				if(faultInfo.getFaultType()==0 &&faultInfo.getIsNewest() == 1){
					faultInfo.setIsNewest(0);
					faultInfoMapper.updateByPrimaryKeySelective(faultInfo);
					
				}
								
				
			}
			
			if(indexList != null){
				for(String index :indexList){
					if(QuestionTypeUtils.getQuestionMessage(index) != null){
						FaultInfo info = new FaultInfo();
		             	info.setBatteryId(battery.getId());
		             	info.setCodeType(index);
		             	info.setFaultType(1);
		             	info.setFaultMessage(QuestionTypeUtils.getQuestionMessage(index));
		             	info.setIsNewest(1);
		             	info.setMarkType(markType);
		             	info.setByte1(hexString);
		             	info.setByte2(hexString2);
		             	info.setReceiveDate(new Date());
		             	
		             	faultInfoMapper.insert(info);
		             	
//		             	umsSmsServiceImpl.sendErrorMessage("13661645501", QuestionTypeUtils.getQuestionMessage(index));
		             	
		             	userCodeService.sendErrorMsg(testPhone, QuestionTypeUtils.getQuestionMessage(index));
					}
	             	
				}
			}
			
		}
		
	}
	
	
	public void addEquipmentState(Battery battery,String hexString){
		
		   EquipmentState equipmentState = new EquipmentState();
	
			String arr1[] = new String[7];
			arr1[0] = QuestionTypeUtils.hexString2binaryString(hexString.substring(0, 2));
			arr1[1] = QuestionTypeUtils.hexString2binaryString(hexString.substring(2, 4));
			arr1[2] = QuestionTypeUtils.hexString2binaryString(hexString.substring(4, 6));
			arr1[3] = QuestionTypeUtils.hexString2binaryString(hexString.substring(6, 8));
			arr1[4] = QuestionTypeUtils.hexString2binaryString(hexString.substring(8, 10));
			arr1[5] = QuestionTypeUtils.hexString2binaryString(hexString.substring(10, 12));
			arr1[6] = QuestionTypeUtils.hexString2binaryString(hexString.substring(12, 14));
			
			
			equipmentState.setBatteryId(battery.getId());
			
			if(arr1[0]!=null && arr1[0].length()==8){
				String s0 = arr1[0];
				equipmentState.setElectricHornSwitchState(Integer.parseInt(s0.substring(7, 8)));
				equipmentState.setOilFanSwitchState(Integer.parseInt(s0.substring(4, 5)));
				equipmentState.setGapSwitchState(Integer.parseInt(s0.substring(2, 3)));
				equipmentState.setGeneratorState(Integer.parseInt(s0.substring(0, 1)));
				

			}
			
			if(arr1[1]!=null && arr1[1].length()==8){
				String s1 = arr1[1];
				equipmentState.setBarnFullSwitchState(Integer.parseInt(s1.substring(7, 8)));
			}
			
			if(arr1[2]!=null && arr1[2].length()==8){
				String s2 = arr1[2];
				
				equipmentState.setStopLampSwitchState(Integer.parseInt(s2.substring(7, 8)));
				equipmentState.setHandBrakeSwitchState(Integer.parseInt(s2.substring(6, 7)));
				equipmentState.setBeforeWorkingLampSwitchState(Integer.parseInt(s2.substring(5, 6)));
				equipmentState.setReversingSwitchState(Integer.parseInt(s2.substring(4, 5)));
				equipmentState.setPeelingMachineLordSwitchState(Integer.parseInt(s2.substring(3, 4)));
				equipmentState.setKey1State(Integer.parseInt(s2.substring(2, 3)));
				equipmentState.setKey2State(Integer.parseInt(s2.substring(1, 2)));
				equipmentState.setKey3State(Integer.parseInt(s2.substring(0, 1)));
			}
			
			
			if(arr1[3]!=null && arr1[3].length()==8){
				String s3 = arr1[3];
				
				equipmentState.setWidthLampState(Integer.parseInt(s3.substring(7, 8)));
				equipmentState.setDippedHeadlightState(Integer.parseInt(s3.substring(6, 7)));
				equipmentState.setHighBeamState(Integer.parseInt(s3.substring(5, 6)));
				equipmentState.setLeftTurnSignalState(Integer.parseInt(s3.substring(4, 5)));
				equipmentState.setRightTurnSignalState(Integer.parseInt(s3.substring(3, 4)));
				equipmentState.setFrontWorkLightState(Integer.parseInt(s3.substring(2, 3)));
				equipmentState.setAfterWorkingLampState(Integer.parseInt(s3.substring(1, 2)));
				equipmentState.setStopLampState(Integer.parseInt(s3.substring(0, 1)));

			}
			
			if(arr1[4]!=null && arr1[4].length()==8){
				String s4 = arr1[4];
				
				equipmentState.setBrakeSolenoidState(Integer.parseInt(s4.substring(7, 8)));
				equipmentState.setBackupLampState(Integer.parseInt(s4.substring(6, 7)));
				equipmentState.setSensorVoltageState(Integer.parseInt(s4.substring(5, 6)));
				equipmentState.setEcuPowerState(Integer.parseInt(s4.substring(4, 5)));
				equipmentState.setEcuControlPowerState(Integer.parseInt(s4.substring(3, 4)));
				equipmentState.setElectromagneticPumpState(Integer.parseInt(s4.substring(2, 3)));
				equipmentState.setAirConditionerDimWindPowerState(Integer.parseInt(s4.substring(1, 2)));
				equipmentState.setFanDomeLightPowerState(Integer.parseInt(s4.substring(0, 1)));

				
			}
			
			if(arr1[5]!=null && arr1[5].length()==8){
				String s5 = arr1[5];
				equipmentState.setWiperPowerState(Integer.parseInt(s5.substring(7, 8)));
				equipmentState.setOilFanPowerState(Integer.parseInt(s5.substring(6, 7)));
				equipmentState.setTossMotorPowerState(Integer.parseInt(s5.substring(5, 6)));
				equipmentState.setCleanFan1State(Integer.parseInt(s5.substring(4, 5)));
				equipmentState.setCleanFan2State(Integer.parseInt(s5.substring(3, 4)));
				equipmentState.setGeneratorExcitationState(Integer.parseInt(s5.substring(2, 3)));
				equipmentState.setConstantSpeedLiftState(Integer.parseInt(s5.substring(1, 2)));
				equipmentState.setGranaryAlarmLampState(Integer.parseInt(s5.substring(0, 1)));
				
			}
			
			if(arr1[6]!=null && arr1[6].length()==8){
				String s6 = arr1[6];
				 equipmentState.setFaultAlarmLampState(Integer.parseInt(s6.substring(6, 7)));
			}
			
			equipmentState.setReceiveDate(new Date());
			
			equipmentStateMapper.insertSelective(equipmentState);
			
			
			EquipmentState es = equipmentStateNstMapper.selectByBtyId(battery.getId());
			
			if(es == null){
				
				equipmentStateNstMapper.insertSelective(equipmentState);
				
			}else{
				
				
				equipmentStateNstMapper.updateByPrimaryKeySelective(equipmentState);

			}
	}

	@Override
	public void sendOrderRegister(String imei, String msg) throws CrudException {
		
		Battery battery = batteryMapper.selectByIMEI(imei);
		
		if(battery == null){
		    throw new CrudException("设备不存在!");
		}
		
		boolean hasConn = false;

		ConcurrentHashMap<String, Channel> channelMap = SamBtyDataHandler.channelMap;
		Channel channel = channelMap.get(battery.getImei());
		if (channel != null) {
			channel.writeAndFlush(msg+"\n");
			hasConn = true;
			
			OrderRegister orderRegister = orderRegisterMapper.selectByBtyId(battery.getId());
			
			if(orderRegister == null){
				orderRegister = new OrderRegister();
				
				orderRegister.setBatteryId(battery.getId());
				orderRegister.setStatus(0);
				orderRegister.setSendMsg(msg);
				orderRegister.setSendDate(new Date());
				
				orderRegisterMapper.insertSelective(orderRegister);
			}else{
				
				orderRegister.setStatus(0);
				orderRegister.setSendMsg(msg);
				orderRegister.setSendDate(new Date());
				
				orderRegisterMapper.updateByPrimaryKeySelective(orderRegister);
				
			}
		}

		if (!hasConn) {
			 throw new CrudException("设备未连接!");
		}	
	}

	@Override
	public OrderRegister fetchOrderRegister(String imei) throws CrudException {
		
		Battery battery = batteryMapper.selectByIMEI(imei);
		
		if(battery == null){
		    throw new CrudException("设备不存在!");
		}
		
		OrderRegister orderRegister = orderRegisterMapper.selectByBtyId(battery.getId());
	
		return orderRegister;
	}

	@Override
	public void uploadOrderRegister(UploadOrderRegisterMsgReq req)
			throws CrudException {
		// TODO Auto-generated method stub
		
		if(req == null){
			return;
		}
		
		Battery battery = batteryMapper.selectByIMEI(req.getHyimei());
		
		if(battery == null){
			logger.info("还未绑定的设备:" + req.getHyimei());
		}else{
			OrderRegister orderRegister = orderRegisterMapper.selectByBtyId(battery.getId());
			
			if(orderRegister == null){
				orderRegister = new OrderRegister();
				
				orderRegister.setBatteryId(battery.getId());
				orderRegister.setStatus(1);
				orderRegister.setReceiveMsg(req.getString());
				orderRegister.setReceiveDate(new Date());
				
				orderRegisterMapper.insertSelective(orderRegister);
			}else{
				orderRegister.setStatus(1);
				orderRegister.setReceiveMsg(req.getString());
				orderRegister.setReceiveDate(new Date());
				
				orderRegisterMapper.updateByPrimaryKeySelective(orderRegister);
			}
			
		}
		
	}
		
}
