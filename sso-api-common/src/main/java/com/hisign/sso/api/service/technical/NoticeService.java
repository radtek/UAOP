package com.hisign.sso.api.service.technical;

import java.util.List;
import java.util.Map;


import com.hisign.sso.api.entity.technical.Notice;
import com.hisign.sso.api.entity.technical.NoticeRecieve;
import com.hisign.sso.api.query.QueryFilter;


/**
 * @Title:
 *   通知通告管理服务
 * @description:
 * 
 * @author lnj 
 */
public interface NoticeService {

	/**
	 * 根据ID或者唯一条件查询
	 * @param id
	 * @return
	 */
	public Notice getById(String id);

	/**
	 * 根据id删除记录
	 * @param id
	 * @throws Exception
	 */
	public Map<String, String> delete(String id) throws Exception;

	/**
	 * 根据id列表删除多条记录
	 * @param ids
	 * @throws Exception
	 */
	public Map<String, String> deleteByIds(List<String> ids);

	/**
	 * 更新记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> update(Notice t) throws Exception;

	/**
	 * 新增记录
	 * @param t
	 * @throws Exception
	 */
	public Map<String, String> add(Notice t) throws Exception;
	
	/**
	 * 保存并发送消息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> send(Notice t)  throws Exception;
	
	/**
	 * 条件查询(非分页)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Notice> query(Map<String, Object> map) throws Exception;
	
	/**
	 * 按条件查询数量
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int count(Map<String, Object> map) throws Exception;
	
	/**
	 * 分页查询
	 * @param queryFilter  查询条件
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> queryByPagination(QueryFilter queryFilter) throws Exception;
	
	/**
	 * 根据noticeId获取签发和反馈列表
	 * @param noticeId 通告Id
	 * @return
	 * @throws Exception
	 */
	public List<NoticeRecieve> getRecieveListByNoticeId(String noticeId) throws Exception;
	
}
