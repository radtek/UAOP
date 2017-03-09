package com.hisign.sso.service.impl.sys;

import java.util.ArrayList;
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
import com.hisign.sso.api.entity.sys.UserOrganization;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.UserOrganizationService;
import com.hisign.sso.persist.mapper.sys.UserOrganizationMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;
import com.hisign.sso.service.impl.msg.MessageProcessor;

/**
 * @Title:
 *  用户组织结构关系服务实现
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午10:07:35
 */
@Path("userorganizations")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("userOrganizationService")
public class UserOrganizationServiceImpl implements UserOrganizationService {

	private static final Logger log = LoggerFactory.getLogger(UserOrganizationServiceImpl.class);
	
	@Autowired
	private UserOrganizationMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper
	
	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(final UserOrganization t) throws Exception {
		mapper.add(t);
		MessageProcessor.getInstance().sendUserOrganiseRelUpdateMsg(new ArrayList<UserOrganization>(){{add(t);}}); //发送变更消息
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<UserOrganization> list) throws Exception {
		batchCommitHelper.addBatch(UserOrganizationMapper.class, list);
		MessageProcessor.getInstance().sendUserOrganiseRelUpdateMsg(list); //发送变更消息
	}

	@Override
	@POST
	@Path("delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(final UserOrganization userOrg) {
        mapper.delete(userOrg);
        MessageProcessor.getInstance().sendUserOrganiseRelUpdateMsg(new ArrayList<UserOrganization>(){{add(userOrg);}}); //发送变更消息
	}

	@Override
	@GET
	@Path("{orgId}/userids")
	public List<String> getUserIdsInOrganization(@PathParam("orgId") String orgId) {
		return mapper.getUserIdsInOrganization(orgId);
	}

	/**
	 * 根据userId列表真实删除
	 * @param userIds
	 */
	@Override
	@POST
	@Path("deletebyuserids")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String,Object> deleteByUserIds(List<String> userIds) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			mapper.deleteByUserIds(userIds);
		}catch(Exception ex){
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		
		MessageProcessor.getInstance().sendUserOrganiseRelUpdateMsg(this.buildUserOrganizationList(userIds)); //发送变更消息
		
		resultMap.put("message", "删除成功");
		return resultMap;
	}
	
	private List<UserOrganization> buildUserOrganizationList(List<String> userIds){
		List<UserOrganization> list = new ArrayList<UserOrganization>(userIds.size());
		for(String userId : userIds){
			UserOrganization uo = new UserOrganization();
			uo.setUserId(userId);
			list.add(uo);
		}
		
		return list;
	}
}
