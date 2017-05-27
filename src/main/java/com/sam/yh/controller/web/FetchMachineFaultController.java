package com.sam.yh.controller.web;

import java.util.List;

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
import com.sam.yh.req.bean.web.FetchAllMachineFaultReq;
import com.sam.yh.req.bean.web.FetchSingleFaultInfosReq;
import com.sam.yh.resp.bean.ResponseUtils;
import com.sam.yh.resp.bean.SamResponse;
import com.sam.yh.resp.bean.web.FetchAllMachineFault;
import com.sam.yh.resp.bean.web.FetchAllMachineFaultResp;
import com.sam.yh.resp.bean.web.FetchSingleFaultInfos;
import com.sam.yh.resp.bean.web.FetchSingleFaultInfosResp;
import com.sam.yh.service.WebService;

@RestController
@RequestMapping("/machine")
public class FetchMachineFaultController {
	@Autowired
    WebService webService;
	 private static final Logger logger = LoggerFactory.getLogger(FetchMachineFaultController.class);
	 @RequestMapping(value = "/fault", method = RequestMethod.POST)
	    public SamResponse fetchMachineFault(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {

	        logger.info("Request json String:" + jsonReq);
	       FetchAllMachineFaultReq req = JSON.parseObject(jsonReq, FetchAllMachineFaultReq.class);
	        try {
	        	
	        	List<FetchAllMachineFault> fetchAllMachineFaults1 = webService.fetchAllMachineFault(req.getUserPhone(),1);
	        	List<FetchAllMachineFault> fetchAllMachineFaults2 = webService.fetchAllMachineFault(req.getUserPhone(),0);
	        	
	        	FetchAllMachineFaultResp respDate = new FetchAllMachineFaultResp();
	        	respDate.setFetchAllMachineFault1(fetchAllMachineFaults1);
	        	respDate.setFetchAllMachineFault2(fetchAllMachineFaults2);
	        	
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
	 
	 @RequestMapping(value = "/faultInfos", method = RequestMethod.POST)
	    public SamResponse fetchFaultInfos(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {

	        logger.info("Request json String:" + jsonReq);
	        FetchSingleFaultInfosReq req = JSON.parseObject(jsonReq, FetchSingleFaultInfosReq.class);
	        try {
	        	
	        	List<FetchSingleFaultInfos> fetchSingleFaultInfos = webService.fetchSingleFaultInfos(req.getImei());
	        
	        	
	        	FetchSingleFaultInfosResp respDate = new FetchSingleFaultInfosResp();
	        	respDate.setFetchSingleFaultInfos(fetchSingleFaultInfos);  
	        	
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
