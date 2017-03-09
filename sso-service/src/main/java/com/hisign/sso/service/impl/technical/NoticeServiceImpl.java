package com.hisign.sso.service.impl.technical;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sdk.common.util.StringUtils;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.technical.Notice;
import com.hisign.sso.api.entity.technical.NoticeRecieve;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.technical.NoticeService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.technical.NoticeMapper;
import com.hisign.sso.persist.mapper.technical.NoticeRecieveMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *  通知通告管理服务
 * @description:
 * 
 * @author lnj 
 */
@Path("notices")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private NoticeMapper mapper;  //通知通告dao
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper
	
	@Autowired
	private NoticeRecieveMapper noticeRecieveMapper;

	@Override
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Notice getById(@PathParam("id") String id) {
		return mapper.getById(id);
	}

	@Override
	@GET
	@Path("{id}/delete")
	public Map<String, String> delete(@PathParam("id") String id) throws Exception {
		try {
			Notice t =new Notice();
			t.setId(id);
			t.initUpdParameter();
			t.setStatus(UAOPConstant.STATUS.DELETED);
			
			mapper.updateStatusById(t);
		} catch (Exception e) {
			logger.error("删除失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return null;
	}

	@Override
	public Map<String, String> deleteByIds(List<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> update(Notice t) throws Exception {
		t.initUpdParameter();
		try {
			mapper.update(t);
		} catch (Exception e) {
			logger.error("修改失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "修改失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		
		return retMap;
	}

	@Override
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> add(Notice t) throws Exception {
		String id = t.getId();
		if(StringUtils.isEmpty(id)){
			id = SysIDGenerator.getInstance().genId();
			t.setId(id);
		}
		t.initBaseParameter();
		try {
			mapper.add(t);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("id", id);
		retMap.put("message", "成功");
		
		return retMap;
	}
	
	/**
	 * 保存并发送消息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("saveandsend")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String, String> send(Notice t)  throws Exception {
		String id = t.getId();
		if(StringUtils.isEmpty(id)){ //保存并发送
			t.setSendStatus(UAOPConstant.SendStatus.YES);
			return this.add(t);
		}else{ //发送
			Notice update = new Notice();
			update.setId(id);
			update.setSendStatus(UAOPConstant.SendStatus.YES);
			return this.update(update);
		}
	}
	
	@Override
	@POST
	@Path("query")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<Notice> query(Map<String, Object> map) throws Exception  {
		return mapper.query(map);
	}

	@Override
	@POST
	@Path("count")
	@Consumes({MediaType.APPLICATION_JSON})
	public int count(Map<String, Object> map) throws Exception  {
		return mapper.count(map);
	}
	

	/**
	 * 分页查询
	 * @param queryFilter  查询条件
	 * @return
	 */
	@Override
	@POST
	@Path("pagequery")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String,Object> queryByPagination(QueryFilter queryFilter) throws Exception {
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		if(condition != null){
			String title = condition.getTitle();
			map.put("title", title);
			Integer sendStatus = condition.getSendStatus();
			map.put("sendStatus", sendStatus);
			String sender = condition.getSender();
			map.put("sender", sender);
			String sendTimeStart = condition.getSendTimeStart();
			map.put("sendTimeStart", sendTimeStart);
			String sendTimeEnd = condition.getSendTimeEnd();
			map.put("sendTimeEnd", sendTimeEnd);
			String sendUnit = condition.getSendUnit();
			map.put("sendUnit", sendUnit);
			Integer sendType = condition.getSendType();
			map.put("sendType", sendType);
			String recieveUnit = condition.getRecieveUnit();
			map.put("recieveUnit", recieveUnit);
			String recieveUser = condition.getRecieveUser();
			map.put("recieveUser", recieveUser);
			Integer feedbackStatus = condition.getFeedbackStatus();
			map.put("feedbackStatus", feedbackStatus);
		}
		
		queryFilter.calculateOffsetAndLimit(); //计算offset和Limit
		PageHelper.offsetPage(queryFilter.getOffset(), queryFilter.getLimit(), orderBy);
		
        Page<Notice> page = (Page<Notice>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
	
	
	/**
	 * 根据noticeId获取签发和反馈列表
	 * @param noticeId
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{noticeId}/recievelist")
	@Produces({MediaType.APPLICATION_JSON})
	public List<NoticeRecieve> getRecieveListByNoticeId(@PathParam("noticeId") String noticeId) throws Exception{
		return noticeRecieveMapper.getByNoticeId(noticeId);
	}

}
