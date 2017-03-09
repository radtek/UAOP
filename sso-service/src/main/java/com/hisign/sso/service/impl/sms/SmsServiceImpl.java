/**
 * 
 */
package com.hisign.sso.service.impl.sms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.entity.technical.SmsNotify;
import com.hisign.sso.api.entity.technical.SmsNotifyRecord;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sms.SmsService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.technical.SmsNotifyMapper;
import com.hisign.sso.persist.mapper.technical.SmsNotifyRecordMapper;

/**
 * 短信服务实现
 * @author chailiangzhi
 * @date 2017-3-9
 * 
 */
@Path("sms")
@Service("smsService")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
public class SmsServiceImpl implements SmsService {

	final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 短信表DAO
	 */
	@Autowired
	private SmsNotifyMapper smsNotifyMapper; //

	/**
	 * 短信历史表DAO
	 */
	@Autowired
	private SmsNotifyRecordMapper smsNotifyRecordMapper; //

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sms.SmsService#create(com.hisign.sso.api.entity.technical.SmsNotify)
	 */
	@Override
	@POST
	@Path("create")
	public SmsNotify create(SmsNotify smsNotify) {
		String id = SysIDGenerator.getInstance().genId();
		Long createTime = System.currentTimeMillis();
		smsNotify.setId(id);
		smsNotify.setCreateTime(createTime);
		smsNotify.setStatus(0);
		try {
			smsNotifyMapper.add(smsNotify);
			SmsNotifyRecord smsNotifyRecord = new SmsNotifyRecord();
			//		smsNotifyRecord.setId(id);
			//		smsNotifyRecord.setCreateTime(createTime);
			PropertyUtils.copyProperties(smsNotifyRecord, smsNotify);
			smsNotifyRecord.setSendStatus(0);
			smsNotifyRecord.setNotifyId(id);
			smsNotifyRecordMapper.add(smsNotifyRecord);
		} catch (Exception e) {
			logger.error("创建短信失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "创建短信失败");
		}
		return smsNotify;
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sms.SmsService#list(com.hisign.sso.api.entity.technical.SmsNotify)
	 */
	@Override
	@POST
	@Path("list")
	public List<SmsNotify> list(SmsNotify smsNotify) {
		logger.debug("List<SmsNotify> list(SmsNotify smsNotify)");
		return smsNotifyMapper.find(smsNotify);
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sms.SmsService#result(com.hisign.sso.api.entity.technical.SmsNotify)
	 */
	@Override
	@POST
	@Path("result")
	public Map<String, String> result(SmsNotifyRecord smsNotifyRecord) {
		try {
			Long sendTime = System.currentTimeMillis();
			smsNotifyRecord.setSendTime(sendTime);
			smsNotifyRecordMapper.update(smsNotifyRecord);
			String id = smsNotifyRecord.getId();
			smsNotifyMapper.delete(id);
		} catch (Exception e) {
			logger.error("记录短信发送结果失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "记录短信发送结果失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

}
