package com.sam.yh.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.sam.yh.common.IllegalParamsException;
import com.sam.yh.crud.exception.BtyLockException;
import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.dao.FaultInfoMapper;
import com.sam.yh.model.FaultInfo;
import com.sam.yh.model.FetchErrInfo;
import com.sam.yh.req.bean.FetchErrReq;
import com.sam.yh.req.bean.FetchFaultReq;
import com.sam.yh.resp.bean.FetchErrResp;
import com.sam.yh.resp.bean.FetchFaultResp;
import com.sam.yh.resp.bean.ResponseUtils;
import com.sam.yh.resp.bean.SamResponse;
import com.sam.yh.service.UserService;

@RestController
@RequestMapping("/bty")
public class FetchFaultController {
	
	 @Autowired
	 UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(FetchFaultController.class);

    @RequestMapping(value = "/fault", method = RequestMethod.POST)
    public SamResponse fetchFault(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);

        FetchErrReq req = JSON.parseObject(jsonReq, FetchErrReq.class);

        try {
          
            List<FaultInfo>faultInfos = userService.fetchFault(req.getImei(), req.getFlag());
            
            FetchErrResp fetchErrResp = new FetchErrResp();
            List<FetchErrInfo> fetchErrInfos = new ArrayList<FetchErrInfo>();
            
            if(faultInfos != null){
            	
           
            	for(FaultInfo faultInfo : faultInfos){
            		FetchErrInfo fetchErrInfo = new FetchErrInfo();
            		fetchErrInfo.setIndexId(faultInfo.getCodeType());
            		fetchErrInfo.setFaultMessage(faultInfo.getFaultMessage());
            		Date date = faultInfo.getReceiveDate();
            		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateString = formatter.format(date);
            		fetchErrInfo.setDate(dateString);
            		
            		fetchErrInfos.add(fetchErrInfo);	
            		
            	}
            	fetchErrResp.setErrorInfos(fetchErrInfos);
            }

            return ResponseUtils.getNormalResp(fetchErrResp);
        }  catch (CrudException e) {
            if (e instanceof BtyLockException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {     
            return ResponseUtils.getSysErrorResp();
        }
}


}
