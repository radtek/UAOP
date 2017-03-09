/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.druid.sql.parser.Token;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sdk.config.service.SysParamService;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.DeviceRegist;
import com.hisign.sso.api.entity.sys.LogToken;
import com.hisign.sso.api.rest.entity.sys.User;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.DeviceRegistService;
import com.hisign.sso.api.service.sys.TokenService;
import com.hisign.sso.api.service.sys.UserService;
import com.hisign.sso.common.util.JsonUtil;
import com.hisign.sso.persist.mapper.sys.LogTokenMapper;
import com.hisign.sso.service.impl.helper.TokenHelper;

/**
 * TOKEN服务的实现
 * @author chailiangzhi
 * @date 2016-6-29
 * 
 */
@Path("tokens")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
@Service("tokenService")
public class TokenServiceImpl implements TokenService {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Token记录表DAO
	 */
	@Autowired
	LogTokenMapper logTokenMapper;

	/**
	 * 
	 */
	@Autowired
	SysParamService sysParamService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeviceRegistService deviceRegistService;

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.TokenService#checkToken(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String checkToken(String token, String sysCode, String serviceCode) {
		logger.debug("1..in service checkToken token="+token+",sysCode="+sysCode+",serviceCode="+serviceCode);
		Map<String, String> resMap = new HashMap<String, String>();
		LogToken logToken = logTokenMapper.getById(token);
		if (logToken == null) {
			logger.debug("2..in service checkToken token="+token+",sysCode="+sysCode+",serviceCode="+serviceCode);
			resMap.put(RSP_CODE, TOKEN_NOT_EXIST);
			Map<String, String> keyValues = sysParamService.getProperties("UAOP", "SSO");
			String loginRestUrl = keyValues.get("LOGIN_REST_URL");
			logger.info("loginRestUrl={}", loginRestUrl);
			resMap.put(LOGIN_REST_URL, loginRestUrl);
		} else {
			logger.debug("3..in service checkToken token="+token+",sysCode="+sysCode+",serviceCode="+serviceCode);
			logger.info("account={}", logToken.getAccount());
			resMap.put(RSP_CODE, TOKEN_VALID);
			resMap.put(USER_ID, logToken.getUserId());
			resMap.put(ACCOUNT, logToken.getAccount());
		}
		String retJson = JsonUtil.gson.toJson(resMap);
		return retJson;
	}

	
	/**
	 * 检测token是否超时
	 * 1.超时,返回null
	 * 2.未超时，更新token的最后超时时间，并且将新的token对象返回
	 * @param token
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	public LogToken checkTimeout(String token,String systemId) throws Exception{
		LogToken logToken = logTokenMapper.getById(token);
		long invalidTime = Long.parseLong(logToken.getInvalidTime());
		long now = System.currentTimeMillis();
		if(now > invalidTime){ //当前时间已经超过失效时间,令牌失效
			logger.info("systemId="+systemId+",token="+token+" is invalid");
			return null;
		}else{ //当前还未超时,更新失效时间，并且返回新的token对象
			logger.info("systemId="+systemId+",token="+token+" is valid");
			long newInvalidTime = now + TokenHelper.getInstance().getTokenTimeout();
			LogToken upateToken = new LogToken();
			upateToken.setToken(token);
			upateToken.setEffectiveTime(String.valueOf(now));
			upateToken.setInvalidTime(String.valueOf(newInvalidTime));
			if(StringUtils.isEmpty(systemId)){
				upateToken.setSysCode(systemId);
			}
			logTokenMapper.update(upateToken);
			
			logToken.setEffectiveTime(String.valueOf(now));
			logToken.setInvalidTime(String.valueOf(newInvalidTime));
			if(StringUtils.isEmpty(systemId)){
				logToken.setSysCode(systemId);
			}
			logToken.setSystemTokenInvalidTime(now+TokenHelper.getInstance().getSystemTokenTimeout()*1000);
			logToken.setSystemTokenTimeout(TokenHelper.getInstance().getSystemTokenTimeout());
			String account =  logToken.getAccount();
			User user = userService.getUserByAccount(account);
			logToken.setUser(user);
			return logToken;
		}
	}


	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.TokenService#check(java.util.Map)
	 */
	@Override
	@POST
	@Path("check")
	public Map<String, String> check(Map<String, String> checkPara) {
		String token = checkPara.get("token");
		String deviceId = checkPara.get("deviceId");
		String sysCode = checkPara.get("sysCode");
		String serviceCode = checkPara.get("serviceCode");
		logger.debug("sysCode={}", sysCode);
		logger.debug("serviceCode={}", serviceCode);
		Assert.hasLength(token, "token必须有值");
		Assert.hasLength(deviceId, "deviceId必须有值");
		DeviceRegist deviceRegistPara = new DeviceRegist();
		deviceRegistPara.setDeviceId(deviceId);
		DeviceRegist deviceRegist = deviceRegistService.qryRegistInfo(deviceRegistPara);
		if (deviceRegist == null) {
			logger.warn("设备未注册");
			throw new RestBusinessException(Response.Status.NOT_ACCEPTABLE, "设备未注册");
		}
		LogToken logToken = logTokenMapper.getById(token);
		if (logToken == null) {
			logger.warn("TOKEN不存在");
			throw new RestBusinessException(Response.Status.NOT_ACCEPTABLE, "TOKEN不存在");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("rspCode", "0000");
		retMap.put("message", "成功");
		return retMap;
	}

}
