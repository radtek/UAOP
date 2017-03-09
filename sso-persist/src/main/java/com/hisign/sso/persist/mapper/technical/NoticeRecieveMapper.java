package com.hisign.sso.persist.mapper.technical;

import java.util.List;

import com.hisign.sso.api.entity.technical.NoticeRecieve;
import com.hisign.sso.api.persist.BaseMapper;

public interface NoticeRecieveMapper extends BaseMapper<NoticeRecieve> {
	
	/**
	 * 根据公告Id获取反馈和签收列表
	 * @param noticeId
	 * @return
	 */
	public List<NoticeRecieve> getByNoticeId(String noticeId);
}