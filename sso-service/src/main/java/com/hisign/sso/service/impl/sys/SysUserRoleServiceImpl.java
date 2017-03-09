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
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.entity.sys.SysUserRole;
import com.hisign.sso.api.entity.sys.UserOrganization;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.SysUserRoleService;
import com.hisign.sso.persist.mapper.sys.SysUserMapper;
import com.hisign.sso.persist.mapper.sys.SysUserRoleMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;
import com.hisign.sso.service.impl.msg.MessageProcessor;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午9:15:52
 */
@Path("sysuserroles")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl implements SysUserRoleService {

	private static final Logger log = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);
	
	@Autowired
	private SysUserRoleMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(final SysUserRole t) throws Exception {
		mapper.add(t);
		
		MessageProcessor.getInstance().sendUserRoleRelUpdateMsg(new ArrayList<String>(){{add(t.getAccount());}}); //发送变更消息
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<SysUserRole> list) throws Exception {
		batchCommitHelper.addBatch(SysUserRoleMapper.class, list);
		List<String> accountList = new ArrayList<String>();
		for(SysUserRole t : list){
			accountList.add(t.getAccount());
		}

		MessageProcessor.getInstance().sendUserRoleRelUpdateMsg(accountList); //发送变更消息
	}

	@Override
	@POST
	@Path("delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(final SysUserRole userRole) throws Exception {
		mapper.delete(userRole);
		
		MessageProcessor.getInstance().sendUserRoleRelUpdateMsg(new ArrayList<String>(){{add(userRole.getAccount());}}); //发送变更消息
	}

	@Override
	@GET
	@Path("{account}/roleids")
	public List<String> getRoleIdsByAccount(@PathParam("account") String account) throws Exception {
		return mapper.getRoleIdsByAccount(account);
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
		
		List<String> accountList = this.buildAccountList(userIds);
		if(accountList != null && accountList.size() > 0){   
			MessageProcessor.getInstance().sendUserRoleRelUpdateMsg(accountList); //发送变更消息
		}
		
		resultMap.put("message", "删除成功");
		return resultMap;
	}
	
	private List<String> buildAccountList(List<String> userIds){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userIds", userIds);
		List<SysUser> sysUserList = sysUserMapper.query(map);
		if(sysUserList == null || sysUserList.size() <= 0){
			return null;
		}
		
		List<String> list = new ArrayList<String>(userIds.size());
		for(SysUser sysUser : sysUserList){
			list.add(sysUser.getAccount());
		}
		
		return list;
	}
	
	/**
	 * 根据账号删除用户角色关系
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{account}/delete")
	public Map<String,Object> deleteByAccount(@PathParam("account") String account)  throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			mapper.deleteByAccount(account);
		}catch(Exception ex){
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		
		List<String> accountList = new ArrayList<String>(1);
		accountList.add(account);
		
		MessageProcessor.getInstance().sendUserRoleRelUpdateMsg(accountList); //发送变更消息
		
		resultMap.put("message", "删除成功");
		return resultMap;
	}
	
	/**
	 * 更新用户角色关系
	 * @param map 
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("updatebyaccount")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String,Object> updateSysUserRoles(Map<String,Object> map) throws Exception{
		String account = (String)map.get("account");
		List<String> newRoleIds = (List<String>)map.get("newRoleIds");
		return this.updateSysUserRoles(account, newRoleIds);
	}
	
	/**
	 * 更新用户角色关系
	 * @param account 账号
	 * @param newRoleIds 新角色id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public Map<String,Object> updateSysUserRoles(String account,List<String> newRoleIds) throws Exception{
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
			mapper.deleteByAccount(account);
			List<SysUserRole> newUserRoleList = new ArrayList<SysUserRole>();
			for(String roleId : newRoleIds){
				SysUserRole userRole = new SysUserRole();
				userRole.setAccount(account);
				userRole.setRoleId(roleId);
				newUserRoleList.add(userRole);
			}
			
			batchCommitHelper.addBatch(SysUserRoleMapper.class, newUserRoleList);
		}catch(Exception ex){
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "更新失败");
		}
		
		
		resultMap.put("message", "更新成功");
		return resultMap;
	}
}
