package com.sam.yh.controller;

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
import com.sam.yh.req.bean.EngineLockReq;
import com.sam.yh.resp.bean.ResponseUtils;
import com.sam.yh.resp.bean.SamResponse;
import com.sam.yh.service.UserService;

@RestController
@RequestMapping("/user")
public class EngineLockController {
	
	private static final Logger logger = LoggerFactory.getLogger(EngineLockController.class);

    @Autowired
    UserService userService;
    
    @RequestMapping(value = "/engine/lockone", method = RequestMethod.POST)
    public SamResponse lockEgOne(HttpServletRequest httpServletRequest, @RequestParam("jsonReq") String jsonReq) {
        logger.info("Request json String:" + jsonReq);
        EngineLockReq req = JSON.parseObject(jsonReq, EngineLockReq.class);
        try {
            validateBtyLockArgs(req);

            userService.lockEgOne(req.getUserPhone().trim(), req.getBtyImei().trim());

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

            userService.lockEgTwo(req.getUserPhone().trim(), req.getBtyImei().trim());

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

            userService.unLockEgOne(req.getUserPhone().trim(), req.getBtyImei().trim());

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

            userService.unLockEgTwo(req.getUserPhone().trim(), req.getBtyImei().trim());

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
        
    private void validateBtyLockArgs(EngineLockReq engineLockReq) throws IllegalParamsException {
        if (!MobilePhoneUtils.isValidPhone(engineLockReq.getUserPhone())) {
            throw new IllegalParamsException("请输入正确的手机号码");
        }
        if (StringUtils.isBlank(engineLockReq.getBtyImei())) {
            throw new IllegalParamsException("不存在的电池");
        }
    } 
    
}
