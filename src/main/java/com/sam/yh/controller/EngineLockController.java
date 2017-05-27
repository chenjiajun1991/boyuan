package com.sam.yh.controller;

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
import com.sam.yh.common.MobilePhoneUtils;
import com.sam.yh.crud.exception.BtyLockException;
import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.dao.BatteryMapper;
import com.sam.yh.model.Battery;
import com.sam.yh.req.bean.EngineLockReq;
import com.sam.yh.req.bean.EngineWriteReq;
import com.sam.yh.resp.bean.LockResp;
import com.sam.yh.resp.bean.ResponseUtils;
import com.sam.yh.resp.bean.SamResponse;
import com.sam.yh.service.UserService;

@RestController
@RequestMapping("/user")
public class EngineLockController {
	
	private static final Logger logger = LoggerFactory.getLogger(EngineLockController.class);

    @Autowired
    UserService userService;
    
    @Resource
    BatteryMapper batteryMapper;
    
    @RequestMapping(value = "/engine/lockone", method = RequestMethod.POST)
    public SamResponse lockEgOne(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineLockReq req = JSON.parseObject(jsonReq, EngineLockReq.class);
        try {
            validateBtyLockArgs(req);
            
            Battery battery = batteryMapper.selectByIMEI(req.getBtyImei());
            
            if(battery.getStatus().equals("4")){
    			
            }else{
            	battery.setStatus("2");
            	batteryMapper.updateByPrimaryKeySelective(battery);
            }
            
            userService.writeMessage(req.getUserPhone().trim(), req.getBtyImei().trim(), "wrdata,001,1");
            
           

//            userService.lockEgOne(req.getUserPhone().trim(), req.getBtyImei().trim());

            return ResponseUtils.getNormalResp(StringUtils.EMPTY);
        } catch (IllegalParamsException e) {
            return ResponseUtils.getParamsErrorResp(e.getMessage());
        } catch (CrudException e) {
            logger.error("lock bty exception, " + req.getUserPhone(), e);
            if (e instanceof BtyLockException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {
            logger.error("share bty exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }

    
    @RequestMapping(value = "/engine/locktwo", method = RequestMethod.POST)
    public SamResponse lockEgTwo(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineLockReq req = JSON.parseObject(jsonReq, EngineLockReq.class);
        try {
            validateBtyLockArgs(req);
            
            Battery battery = batteryMapper.selectByIMEI(req.getBtyImei());
            
            if(battery.getStatus().equals("4")){
    			
            }else{
            	battery.setStatus("2");
            	batteryMapper.updateByPrimaryKeySelective(battery);
            }

            
            userService.writeMessage(req.getUserPhone().trim(), req.getBtyImei().trim(), "wrdata,001,2");
            
           
//            userService.lockEgTwo(req.getUserPhone().trim(), req.getBtyImei().trim());

            return ResponseUtils.getNormalResp(StringUtils.EMPTY);
        } catch (IllegalParamsException e) {
            return ResponseUtils.getParamsErrorResp(e.getMessage());
        } catch (CrudException e) {
            logger.error("lock bty exception, " + req.getUserPhone(), e);
            if (e instanceof BtyLockException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {
            logger.error("share bty exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }
    
    @RequestMapping(value = "/engine/unlockone", method = RequestMethod.POST)
    public SamResponse unLockEgOne(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineLockReq req = JSON.parseObject(jsonReq, EngineLockReq.class);
        try {
            validateBtyLockArgs(req);
            
           Battery battery = batteryMapper.selectByIMEI(req.getBtyImei());
            
            if(battery.getStatus().equals("5")){
    			
            }else{
            	battery.setStatus("2");
            	batteryMapper.updateByPrimaryKeySelective(battery);
            }
            
            userService.writeMessage(req.getUserPhone().trim(), req.getBtyImei().trim(), "wrdata,001,4");
            
           

//            userService.unLockEgOne(req.getUserPhone().trim(), req.getBtyImei().trim());

            return ResponseUtils.getNormalResp(StringUtils.EMPTY);
        } catch (IllegalParamsException e) {
            return ResponseUtils.getParamsErrorResp(e.getMessage());
        } catch (CrudException e) {
            logger.error("lock bty exception, " + req.getUserPhone(), e);
            if (e instanceof BtyLockException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {
            logger.error("share bty exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }
      
    @RequestMapping(value = "/engine/unlocktwo", method = RequestMethod.POST)
    public SamResponse unLockEgTwo(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineLockReq req = JSON.parseObject(jsonReq, EngineLockReq.class);
        try {
            validateBtyLockArgs(req);
            
            Battery battery = batteryMapper.selectByIMEI(req.getBtyImei());
            if(battery.getStatus().equals("5")){
    			
            }else{
            	battery.setStatus("2");
            	batteryMapper.updateByPrimaryKeySelective(battery);
            }
            
            userService.writeMessage(req.getUserPhone().trim(), req.getBtyImei().trim(), "wrdata,001,8");
            
           

//            userService.unLockEgTwo(req.getUserPhone().trim(), req.getBtyImei().trim());

            return ResponseUtils.getNormalResp(StringUtils.EMPTY);
        } catch (IllegalParamsException e) {
            return ResponseUtils.getParamsErrorResp(e.getMessage());
        } catch (CrudException e) {
            logger.error("lock bty exception, " + req.getUserPhone(), e);
            if (e instanceof BtyLockException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {
            logger.error("share bty exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }
    
    @RequestMapping(value = "/engine/lockBind", method = RequestMethod.POST)
    public SamResponse lockBind(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineLockReq req = JSON.parseObject(jsonReq, EngineLockReq.class);
        try {
            validateBtyLockArgs(req);

            userService.lockBind(req.getUserPhone().trim(), req.getBtyImei().trim());

            return ResponseUtils.getNormalResp(StringUtils.EMPTY);
        } catch (IllegalParamsException e) {
            return ResponseUtils.getParamsErrorResp(e.getMessage());
        } catch (CrudException e) {
            logger.error("lock bty exception, " + req.getUserPhone(), e);
            if (e instanceof BtyLockException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {
            logger.error("share bty exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }
    
    
    @RequestMapping(value = "/engine/write", method = RequestMethod.POST)
    public SamResponse writeMessage(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineWriteReq req = JSON.parseObject(jsonReq, EngineWriteReq.class);
        try {
          
            userService.writeMessage(req.getUserPhone().trim(), req.getBtyImei().trim(), req.getMessage());

            return ResponseUtils.getNormalResp(StringUtils.EMPTY);
        }  catch (CrudException e) {
            logger.error("lock bty exception, " + req.getUserPhone(), e);
            if (e instanceof BtyLockException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {
            logger.error("share bty exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }
    
    
    
    
    @RequestMapping(value = "/engine/lockstatus", method = RequestMethod.POST)
    public SamResponse fetchLockStatus(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineLockReq req = JSON.parseObject(jsonReq, EngineLockReq.class);
        try {
          
           Battery battery = batteryMapper.selectByIMEI(req.getBtyImei());
           LockResp lockResp = new LockResp();
           if(battery.getStatus()!=null){
        	   lockResp.setStatus(battery.getStatus());
           }else{
        	   lockResp.setStatus("0");
           }
          

            return ResponseUtils.getNormalResp(lockResp);
        }  catch (Exception e) {
            logger.error("share bty exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }
    
    
 
    
    private void validateBtyLockArgs(EngineLockReq engineLockReq) throws IllegalParamsException {
        if (!MobilePhoneUtils.isValidPhone(engineLockReq.getUserPhone())) {
            throw new IllegalParamsException("请输入正确的手机号码");
        }
        if (StringUtils.isBlank(engineLockReq.getBtyImei())) {
            throw new IllegalParamsException("不存在的电池");
        }
    } 
}
