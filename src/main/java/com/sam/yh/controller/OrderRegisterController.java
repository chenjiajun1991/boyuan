package com.sam.yh.controller;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sam.yh.crud.exception.BtyLockException;
import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.dao.BatteryMapper;
import com.sam.yh.model.Battery;
import com.sam.yh.model.OrderRegister;
import com.sam.yh.req.bean.FetchOrderRegisterReq;
import com.sam.yh.req.bean.SendOrderRegisterReq;
import com.sam.yh.resp.bean.FetchOrderRegisterResp;
import com.sam.yh.resp.bean.ResponseUtils;
import com.sam.yh.resp.bean.SamResponse;
import com.sam.yh.service.BatteryService;


@RestController
@RequestMapping("/bty")
public class OrderRegisterController {
	  private static final Logger logger = LoggerFactory.getLogger(OrderRegisterController.class);

	    @Autowired
	    BatteryService batteryService;
	    
	    @Resource
	    private BatteryMapper batteryMapper;

	    @RequestMapping(value = "/send/order", method = RequestMethod.POST)
	    public SamResponse sendOrder(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
	        logger.info("Request send order registe json String:" + jsonReq);

	        SendOrderRegisterReq req = JSON.parseObject(jsonReq, SendOrderRegisterReq.class);

	        try {
	            
	            batteryService.sendOrderRegister(req.getImei(), req.getMsg());

	            return ResponseUtils.getNormalResp(StringUtils.EMPTY);
	        }  catch (CrudException e) {
	            logger.error("send Order Register exception, " + req.getImei(), e);
	            
	            return ResponseUtils.getServiceErrorResp(e.getMessage());
	           
	        } catch (Exception e) {
	            logger.error("send Order Register exception, " + req.getImei(), e);
	            return ResponseUtils.getSysErrorResp();
	        }
	    }
	    
	    @RequestMapping(value = "/fetch/register", method = RequestMethod.POST)
	    public SamResponse fetchRegister(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
	        logger.info("Request fetch register registe json String:" + jsonReq);

	        FetchOrderRegisterReq req = JSON.parseObject(jsonReq, FetchOrderRegisterReq.class);

	        try {
	            
	            OrderRegister orderRegister = batteryService.fetchOrderRegister(req.getImei());
	            FetchOrderRegisterResp resp = new FetchOrderRegisterResp();
	           
	            	Battery battery = batteryMapper.selectByPrimaryKey(orderRegister.getBatteryId());
	            	resp.setImei(battery.getImei());
	            	resp.setStatus(orderRegister.getStatus());
	            	if(orderRegister.getReceiveMsg() != null){
	            		resp.setMsg(orderRegister.getReceiveMsg());
	            	}else{
	            		resp.setMsg("");
	            	}
	            	
	            	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	if(orderRegister.getReceiveDate() != null){
	            		Date date = orderRegister.getReceiveDate();
		                String dateString = formatter.format(date);
		                resp.setReceiveDate(dateString);
	            	}else{
	            		Date date = new Date();
		                String dateString = formatter.format(date);
	            		  resp.setReceiveDate(dateString);
	            	}
	            	
	            return ResponseUtils.getNormalResp(resp);
	        }  catch (CrudException e) {
	            logger.error("send Order Register exception, " + req.getImei(), e);
	            if (e instanceof BtyLockException) {
	                return ResponseUtils.getServiceErrorResp(e.getMessage());
	            } else {
	                return ResponseUtils.getSysErrorResp();
	            }
	        } catch (Exception e) {
	            logger.error("send Order Register exception, " + req.getImei(), e);
	            return ResponseUtils.getSysErrorResp();
	        }
	    }

	   
}
