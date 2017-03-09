package com.hisign.sso.service.impl.helper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sso.api.constant.UAOPConstant;

/**
 * @Title:
 *   登录token超时帮助类
 * @description:
 * 
 * @author lnj 
 */
public class TokenHelper {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public final static int DEFAULT_TOKEN_TIMEOUT = 7200;
	public final static int DEFAULT_SYSTEM_TOKEN_TIMEOUT = 5400;
	private int tokenTimeout = -1; //token超时时间，默认2小时
	private int systemTokenTimeout = -1; //各个业务系统token超时时间,默认1.5小时(必须比tokenTimeout小)
	
	private static TokenHelper instance = null;
	
	public static synchronized TokenHelper getInstance(){
		if(instance == null){
			instance = new TokenHelper();
		}
		return instance;
	}
	
	private TokenHelper(){
	}
	
	
	public  int getTokenTimeout(){
		if(tokenTimeout > 0){
			return tokenTimeout;
		}
		
		String logtokenTimeout = System.getProperty(UAOPConstant.SysParamKey.logtoken_timeout);
		if(!StringUtils.isEmpty(logtokenTimeout)){
			try{
				tokenTimeout = Integer.parseInt(logtokenTimeout);
			}catch(Exception ex){
			}
		}
		
		if(tokenTimeout <= 0){
			tokenTimeout = DEFAULT_TOKEN_TIMEOUT;
		}
		
		logger.info("######logtoken_timeout="+tokenTimeout);
        return tokenTimeout;	
	}
	
	public int getSystemTokenTimeout(){
		if(systemTokenTimeout > 0){
			return systemTokenTimeout;
		}
		
		String system_logtoken_timeout = System.getProperty(UAOPConstant.SysParamKey.system_logtoken_timeout);
		if(!StringUtils.isEmpty(system_logtoken_timeout)){
			try{
				systemTokenTimeout = Integer.parseInt(system_logtoken_timeout);
			}catch(Exception ex){
			}
		}
		
		if(systemTokenTimeout <= 0){
			systemTokenTimeout = DEFAULT_SYSTEM_TOKEN_TIMEOUT;
		}
		
		int logtoken_timeout = getTokenTimeout();
		if(systemTokenTimeout > logtoken_timeout){ //system_logtoken_timeout必须比logtoken_timeout小
			systemTokenTimeout =  (int)(logtoken_timeout * 0.75);
		}
		
		logger.info("######logtoken_timeout="+systemTokenTimeout);
        return systemTokenTimeout;	
	}
}
