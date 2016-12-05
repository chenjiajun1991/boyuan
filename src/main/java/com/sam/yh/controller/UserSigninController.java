package com.sam.yh.controller;

import java.util.Set;

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
import com.google.common.collect.Sets;
import com.sam.yh.common.IllegalParamsException;
import com.sam.yh.common.MobilePhoneUtils;
import com.sam.yh.common.PwdUtils;
import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.crud.exception.UserSignupException;
import com.sam.yh.model.User;
import com.sam.yh.req.bean.UserSigninReq;
import com.sam.yh.resp.bean.ResponseUtils;
import com.sam.yh.resp.bean.SamResponse;
import com.sam.yh.resp.bean.UserInfoResp;
import com.sam.yh.service.UserService;

@RestController
@RequestMapping("/user")
public class UserSigninController {

    @Autowired
    UserService userService;

    @Resource
    private String adminPhones;

    private static final Logger logger = LoggerFactory.getLogger(UserSigninController.class);

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public SamResponse signin(HttpServletRequest httpServletRequest,  @RequestParam("jsonReq")String jsonReq) {
    	
        logger.info("Request json String:" + jsonReq);

        UserSigninReq req = JSON.parseObject(jsonReq, UserSigninReq.class);

        try {
            validateSigninArgs(req);
            logger.info("req.getPassword():" +  req.getPassword());
            User user = userService.signin(req.getUserPhone(), req.getPassword(), req.getDeviceInfo());
            

            UserInfoResp respData = new UserInfoResp();
            respData.setUserUid(user.getUuid());
            respData.setUserType(user.getUserType());

            return ResponseUtils.getNormalResp(respData);
        } catch (IllegalParamsException e) {
            return ResponseUtils.getParamsErrorResp(e.getMessage());
        } catch (CrudException e) {
            logger.error("signin exception, " + req.getUserPhone(), e);
            if (e instanceof UserSignupException) {
                return ResponseUtils.getServiceErrorResp(e.getMessage());
            } else {
                return ResponseUtils.getSysErrorResp();
            }
        } catch (Exception e) {
            logger.error("signin exception, " + req.getUserPhone(), e);
            return ResponseUtils.getSysErrorResp();
        }
    }

    private void validateSigninArgs(UserSigninReq userSigninReq) throws IllegalParamsException {
        if (!MobilePhoneUtils.isValidPhone(userSigninReq.getUserPhone()) && !isAdminPhone(userSigninReq.getUserPhone())) {
            throw new IllegalParamsException("请输入正确的手机号码");
        }

        if (!PwdUtils.isValidPwd(userSigninReq.getPassword())) {
            throw new IllegalParamsException("密码长度为8-20位字符");
        }

    }

    private boolean isAdminPhone(String userPhone) {
        String[] phones = StringUtils.split(adminPhones, ",");
        if (phones == null || phones.length == 0) {
            logger.error("获取admin phone 失败");
        }
        Set<String> admins = Sets.newHashSet(phones);
        return admins.contains(userPhone);
    }
}
