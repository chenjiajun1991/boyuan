package com.sam.yh.controller.web;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.model.BatteryInfoNst;
import com.sam.yh.model.EquipmentState;
import com.sam.yh.req.bean.web.FetchMachineStateReq;
import com.sam.yh.resp.bean.ResponseUtils;
import com.sam.yh.resp.bean.SamResponse;
import com.sam.yh.resp.bean.web.FetchMachineStateResp;
import com.sam.yh.service.WebService;

@RestController
@RequestMapping("/machine")
public class FetchMachineStateController {
	
	@Autowired
    WebService webService;
	 private static final Logger logger = LoggerFactory.getLogger(FetchMachineStateController.class);
	 
	 @RequestMapping(value = "/state", method = RequestMethod.POST)
	    public SamResponse fetchMachineState(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {

	        logger.info("Request json String:" + jsonReq);
	        FetchMachineStateReq req = JSON.parseObject(jsonReq, FetchMachineStateReq.class);
	        try {
	        	
	        	EquipmentState equipmentState = webService.fetchEquipmentStates(req.getImei());
	        	
	        	BatteryInfoNst batteryInfoNst = webService.fetchBatteryInfoNst(req.getImei());
	        	
	        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String dateString = formatter.format(batteryInfoNst.getReceiveDate());
		        
		        batteryInfoNst.setExtension(dateString);
	        	
	        	FetchMachineStateResp respDate = new FetchMachineStateResp();
	        	
	        	respDate.setEquipmentState(equipmentState);
	        	respDate.setBatteryInfoNst(batteryInfoNst);
	        	
	            return ResponseUtils.getNormalResp(respDate);
	            
	        }  catch (Exception e) {
	        	if(e instanceof CrudException){
	        		 logger.error("fetchBtyLocationInfo exception, " +  e);
	        		return ResponseUtils.getServiceErrorResp(e.getMessage());
	        	}else{
	        		return ResponseUtils.getSysErrorResp();
	        	}
	           
	        }
	    }

}
