package com.sam.yh.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.sam.yh.common.RandomCodeUtils;
import com.sam.yh.common.SamConstants;
import com.sam.yh.common.msg.DahantSmsService;
import com.sam.yh.crud.exception.AuthCodeSendException;
import com.sam.yh.crud.exception.AuthCodeVerifyException;
import com.sam.yh.crud.exception.CrudException;
import com.sam.yh.crud.exception.UserSignupException;
import com.sam.yh.dao.UserCodeMapper;
import com.sam.yh.dao.UserMapper;
import com.sam.yh.enums.UserCodeType;
import com.sam.yh.model.User;
import com.sam.yh.model.UserCode;
import com.sam.yh.service.UserCodeService;

@Service
public class UserCodeServiceImpl implements UserCodeService {
	
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserCodeMapper userCodeMapper;
    @Resource
    private DahantSmsService defaultUmsSmsService;

    @Override
    public boolean sendSignupAuthCode(String mobilePhone) throws CrudException {
        User user = userMapper.selectByPhone(mobilePhone);
        if (user != null && !user.getLockStatus()) {
            throw new UserSignupException("手机号码已经注册");
        }
        String authCode = sendAndSaveSmsCode(mobilePhone, UserCodeType.SIGNUP_CODE.getType());
        return defaultUmsSmsService.sendSignupAuthCode(mobilePhone, authCode);
    }

    @Override
    public boolean sendResetPwdAuthCode(String mobilePhone) throws CrudException {
        User user = userMapper.selectByPhone(mobilePhone);
        if (user == null) {
            throw new UserSignupException("未注册的手机号码");
        }
        String authCode = sendAndSaveSmsCode(mobilePhone, UserCodeType.RESETPWD_CODE.getType());
        return defaultUmsSmsService.sendResetPwdAuthCode(mobilePhone, authCode);
    }

    @Override
    public boolean sendTestAuthCode(String mobilePhone, String content) throws CrudException {
        return defaultUmsSmsService.sendTestSms(mobilePhone, content);
    }

    @Override
    public String genAndSaveUserSalt(String mobilePhone, int type) {
        UserCode userCode = fetchByUserName(mobilePhone, UserCodeType.USER_SALT.getType());
        String salt = RandomCodeUtils.genSalt();
        Date now = new Date();
        if (userCode == null) {
            userCode = new UserCode();
            userCode.setMobilePhone(mobilePhone);
            userCode.setCodeType(UserCodeType.USER_SALT.getType());
            userCode.setDynamicCode(salt);
            userCode.setSendTimes(1);
            userCode.setStatus(true);
            userCode.setSendDate(now);
            userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));

            userCodeMapper.insert(userCode);
        } else {
            userCode.setDynamicCode(salt);
            userCode.setSendTimes(1);
            userCode.setStatus(true);
            userCode.setSendDate(now);
            userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));

            userCodeMapper.updateByPrimaryKey(userCode);
        }
        return salt;
    }

    /**
     * 短信验证码发送
     */
    private String sendAndSaveSmsCode(String mobilePhone, int type) throws AuthCodeSendException {
        UserCode userCode = fetchByUserName(mobilePhone, type);

        String smsCode = RandomCodeUtils.genSmsCode();
        Date now = new Date();
        if (userCode == null) {
            userCode = new UserCode();
            userCode.setMobilePhone(mobilePhone);
            userCode.setCodeType(type);
            userCode.setDynamicCode(smsCode);
            userCode.setSendTimes(1);
            userCode.setStatus(true);
            userCode.setSendDate(now);
            userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));

            userCodeMapper.insert(userCode);
            return smsCode;
        }

        if (userCode.getSendTimes() >= SamConstants.MXA_SMS_SEND_TIME && DateUtils.isSameDay(now, userCode.getSendDate())) {
            throw new AuthCodeSendException("已经超过最大发送次数");
        }

        if (System.currentTimeMillis() - userCode.getSendDate().getTime() < 120 * 1000L) {
            throw new AuthCodeSendException("短信发送太快，请稍候");
        }

        int sendTimes = DateUtils.isSameDay(now, userCode.getSendDate()) ? (userCode.getSendTimes() + 1) : 1;
        if (!userCode.getStatus() || now.after(userCode.getExpiryDate())) {
            // 验证码无效
            userCode.setDynamicCode(smsCode);
            userCode.setSendTimes(sendTimes);
            userCode.setStatus(true);
            userCode.setSendDate(now);
            userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));

            userCodeMapper.updateByPrimaryKey(userCode);
            return smsCode;
        } else {
            // 验证码有效，重新发送相同的验证码
            userCode.setSendTimes(sendTimes);

            userCodeMapper.updateByPrimaryKey(userCode);
            return userCode.getDynamicCode();
        }

    }

    @Override
    public UserCode fetchByUserName(String mobilePhone, int type) {
        return userCodeMapper.selectByUserNameAndType(mobilePhone, type);
    }

    @Override
    public boolean verifyAuthCode(String mobilePhone, int type, String authCode) throws CrudException {
        UserCode userCode = fetchByUserName(mobilePhone, type);
        Date now = new Date();

        if (userCode != null && userCode.getStatus() && now.before(userCode.getExpiryDate()) && StringUtils.equals(userCode.getDynamicCode(), authCode)) {
            userCode.setStatus(false);
            userCodeMapper.updateByPrimaryKey(userCode);
            return true;
        } else {
            throw new AuthCodeVerifyException("验证码错误");
        }
    }

    @Override
    public boolean sendWarningMsg(String mobilePhone, String btyImei) throws CrudException {

        int type = UserCodeType.BTY_WARNING.getType();
        boolean send = needToSendMsg(mobilePhone, btyImei, type);
        if (send) {
            defaultUmsSmsService.sendWarningMsg(mobilePhone, btyImei);
        }

        return send;
    }

    @Override
    public boolean sendWarningMsg(String mobilePhone, String btyImei, String voltage,int flag) throws CrudException {

        int type = UserCodeType.BTY_VOLTAGE_WARNING.getType();
        boolean send = needToSendMsg(mobilePhone, btyImei, type);
        if (send) {
        	send=defaultUmsSmsService.sendVoltageWarningMsg(mobilePhone, btyImei, voltage,flag);
        }

        return send;
    }

    //重新写一下布防移动报警
    
    @Override
    public boolean sendMovingMsg(String mobilePhone, String btyImei) throws CrudException {
        int type = UserCodeType.BTY_MOVING.getType();
        
//        Calendar now = Calendar.getInstance();
//        if (now.get(Calendar.HOUR_OF_DAY) < 7) {
//            return false;
//        }
        boolean send = needToSendMsg(mobilePhone, btyImei, type);
        if (send) {
            send = defaultUmsSmsService.sendMovingMsg(mobilePhone, btyImei);
        }
        
        Date now=new Date();
        UserCode userCode = fetchByUserName(btyImei, type);
        if(userCode.getSendTimes()==2&&now.after(userCode.getExpiryDate())){
        	userCode.setSendTimes(userCode.getSendTimes()+1);
            userCode.setSendDate(now);
            userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));
            userCodeMapper.updateByPrimaryKey(userCode);
        
            send = defaultUmsSmsService.sendMovingRemindMsg(mobilePhone, btyImei);
            
        }
        
        return send;
    }

    private boolean needToSendMsg(String mobilePhone, String btyImei, int type) {
        boolean send = false;
        UserCode userCode = fetchByUserName(btyImei, type);

        Date now = new Date();
        
        Date delay=new Date();
		delay.setHours(delay.getHours()-7);
        
        if (userCode == null) {
            userCode = new UserCode();
            userCode.setMobilePhone(btyImei);
            userCode.setCodeType(type);
            userCode.setDynamicCode(mobilePhone);
            userCode.setSendTimes(1);
            userCode.setStatus(true);
            userCode.setSendDate(now);
            userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));

            userCodeMapper.insert(userCode);
            send = true;
        }
        
        //为了避免骚扰每一天短信提醒不从零点开始，从早晨七点开始算每一天的开始
        int sendTimes = DateUtils.isSameDay(delay, userCode.getSendDate()) ? (userCode.getSendTimes()+1) : 1;
        
//        int sendTimes = DateUtils.isSameDay(now, userCode.getSendDate()) ? (userCode.getSendTimes()+1) : 1;
//        if (now.after(userCode.getExpiryDate()) && sendTimes <= SamConstants.MXA_WARNING_SEND_TIME) {
//        	
//            userCode.setSendTimes(userCode.getSendTimes() + 1);
//            userCode.setSendDate(now);
//            userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));
//
//            userCodeMapper.updateByPrimaryKey(userCode);
//            send = true;
//        }
//        
        if(type==UserCodeType.BTY_VOLTAGE_WARNING.getType()){
        	if(sendTimes <= SamConstants.MXA_WARNING_SEND_TIME){
//        		 userCode.setSendTimes(sendTimes);
        		//每天发送一次
        		 userCode.setSendTimes(userCode.getSendTimes()+1);
                 userCode.setSendDate(now);
                 userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));

                 userCodeMapper.updateByPrimaryKey(userCode);
                 send = true;
        	}
        	
        }
        
        if(type==UserCodeType.BTY_DISTORY.getType()){
        	if(userCode.getSendTimes() < SamConstants.MXA_WARNING_SEND_TIME){
       		    userCode.setSendTimes(userCode.getSendTimes()+1);
                userCode.setSendDate(now);
                userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));
                
                userCodeMapper.updateByPrimaryKey(userCode);
                send = true;
       	    }
        }
        
        if(type==UserCodeType.BTY_DISTORY_SERVICE.getType()){
        	if(userCode.getSendTimes()<SamConstants.MXA_WARNING_SEND_SERVICE_TIME){
        		userCode.setSendTimes(userCode.getSendTimes() + 1);
                userCode.setSendDate(now);
                userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));
                
                userCodeMapper.updateByPrimaryKey(userCode);
                send = true;
        	}
        }
        
        if(type==UserCodeType.BTY_WARNING.getType()){
        	if(sendTimes <= SamConstants.MXA_WARNING_SEND_TIME){
//        		userCode.setSendTimes(sendTimes);
        		//调整每天仅发送一次
        		userCode.setSendTimes(userCode.getSendTimes()+1);
                userCode.setSendDate(now);
                userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));
                userCodeMapper.updateByPrimaryKey(userCode);
                send = true;
        	}
        }
        
        if(type==UserCodeType.BTY_MOVING.getType()){
        	if(userCode.getSendTimes() < 2){
        		userCode.setSendTimes(userCode.getSendTimes()+1);
                userCode.setSendDate(now);
                userCode.setExpiryDate(DateUtils.addMinutes(now, SamConstants.EXPIRY_TIME));
                userCodeMapper.updateByPrimaryKey(userCode);
                send = true;
        	   }
        }
        return send;
    }
    
    //增加一个剪断信号线电压突变报警
	@Override
	public boolean sendViolentDestroyClient(String mobilePhone,String btyImei)
			throws CrudException {
		int type=UserCodeType.BTY_DISTORY.getType();
		boolean send=needToSendMsg(mobilePhone, btyImei, type);
		if(send){
			defaultUmsSmsService.sendViolentDestroyClient(mobilePhone);
		}
		return send;
	}

	@Override
	public boolean sendViolentDestroyService(String mobilePhone,
			String btyImei, String userName, String userPhone)
			throws CrudException {
		boolean send=false;
		int type1=UserCodeType.BTY_DISTORY.getType();
		int type2=UserCodeType.BTY_DISTORY_SERVICE.getType();
		 UserCode userCode = fetchByUserName(btyImei, type1);
		 int times=userCode.getSendTimes();
		 if(times==1||times==3){
			  send=needToSendMsg(mobilePhone, btyImei, type2);
			 if(send){
				 defaultUmsSmsService.sendViolentDestroyService(mobilePhone, btyImei, userName, userPhone);
			 }
		 }
		
		return send;
	}

}
