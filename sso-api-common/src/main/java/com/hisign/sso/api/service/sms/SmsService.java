/**
 * 
 */
package com.hisign.sso.api.service.sms;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.technical.SmsNotify;
import com.hisign.sso.api.entity.technical.SmsNotifyRecord;

/**
 * 短信服务
 * @author chailiangzhi
 * @date 2017-3-9
 * 
 */
public interface SmsService {

	/**
	 * 创建短信
	 * @param smsNotify
	 * @return
	 */
	public SmsNotify create(SmsNotify smsNotify);

	/**
	 * 查询短信待发列表
	 * @param smsNotify
	 * @return
	 */
	public List<SmsNotify> list(SmsNotify smsNotify);

	/**
	 * 报告短信发送结果
	 * @param smsNotify
	 * @return
	 */
	public Map<String, String> result(SmsNotifyRecord smsNotifyRecord);
}
