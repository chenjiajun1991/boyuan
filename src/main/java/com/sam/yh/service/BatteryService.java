package com.sam.yh.service;

import java.util.concurrent.Future;

import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.model.Battery;
import com.sam.yh.model.BatteryInfoNst;
import com.sam.yh.model.OrderRegister;
import com.sam.yh.req.bean.BatteryInfoReq;
import com.sam.yh.req.bean.UploadFaultMessageReq;
import com.sam.yh.req.bean.UploadOrderRegisterMsgReq;

public interface BatteryService {

//    @Transactional
	@Async("asyncExecutor")
    public Future<Battery> uploadBatteryInfo(BatteryInfoReq batteryInfoReqVo) throws CrudException;
	
	@Async("asyncExecutor")
    public Future<Battery> uploadFaultInfo(UploadFaultMessageReq faultInfoReqVo) throws CrudException;
	
    @Transactional
    public Battery addBattery(Battery battery);

    @Transactional
    public Battery fetchBtyByIMEI(String imei);

    public Battery fetchBtyById(int id);

    @Transactional
    public Battery fetchBtyByPubSn(String pubSn);

    @Transactional
    public Battery fetchBtyBySimNo(String simNo);

    @Transactional
    public Battery fetchBtyBySN(String btySn);

    @Transactional
    public int countSoldBtys(int resellerId);

    @Transactional
    public int countCityBtys(int cityId);

    @Transactional
    public BatteryInfoNst fetchBtyInfo(String btySimNo) throws CrudException;
    
    @Transactional
    public int fetchRssi(int btyId) throws CrudException;
    
    @Transactional
    public void sendOrderRegister(String imei ,String msg)throws CrudException;
    
    @Transactional
    public OrderRegister fetchOrderRegister(String imei)throws CrudException;
    
    @Transactional
    public void uploadOrderRegister(UploadOrderRegisterMsgReq req)throws CrudException;
}
