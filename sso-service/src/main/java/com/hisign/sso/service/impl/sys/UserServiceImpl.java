/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sdk.common.util.StringUtils;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.LogToken;
import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.entity.sys.UserOrganization;
import com.hisign.sso.api.entity.sys.UserSystem;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.entity.sys.User;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.rest.filter.RequestContext;
import com.hisign.sso.api.service.sys.SysAccountService;
import com.hisign.sso.api.service.sys.UserInfoService;
import com.hisign.sso.api.service.sys.UserService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.common.util.Md5Util;
import com.hisign.sso.persist.mapper.sys.LogTokenMapper;
import com.hisign.sso.persist.mapper.sys.OrganiseMapper;
import com.hisign.sso.persist.mapper.sys.RoleMapper;
import com.hisign.sso.persist.mapper.sys.SysUserMapper;
import com.hisign.sso.persist.mapper.sys.SysUserRoleMapper;
import com.hisign.sso.persist.mapper.sys.UserInfoMapper;
import com.hisign.sso.persist.mapper.sys.UserMapper;
import com.hisign.sso.persist.mapper.sys.UserOrganizationMapper;
import com.hisign.sso.persist.mapper.sys.UserSystemMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;
import com.hisign.sso.service.impl.msg.MessageProcessor;

/**
 * 4.7	用户管理
 * 实现用户信息管理。
 * 新增，删除，更新用户等操作
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
@Path("users")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("userService")
@Transactional
public class UserServiceImpl  implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * 系统用户DAO接口
	 */
	@Autowired
	private SysUserMapper mapper;
	
	/**
	 * 用户信息DAO接口
	 */
	@Autowired
	private UserInfoMapper userInfoMapper;
	
	/**
	 * 用户信息DAO接口
	 */
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 用户与组织机构
	 */
	@Autowired
	private UserOrganizationMapper userOrganizationMapper;  //
	
	/**
	 * 角色DAO接口
	 */
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 用户角色关系DAO接口
	 */
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	
	/**
	 * 用户账户基本服务
	 */
	@Autowired
	private SysAccountService sysAccountService;
	
	/**
	 * 用户基本信息服务
	 */
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * Token记录表DAO
	 */
	@Autowired
	private LogTokenMapper logTokenMapper;
	
	@Autowired
	private OrganiseMapper organiseMapper;
	
	@Autowired
	private UserSystemMapper userSystemMapper;
	
	
	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.UserService#register(com.hisign.sso.api.entity.sys.SysUser)
	 */
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON})
	public User add(User user) throws Exception {
		SysUser sysUser = new SysUser();
		String userId=SysIDGenerator.getInstance().genUserId();
		sysUser.setUserId(userId);
		sysUser.setAccount(user.getAccount());
		sysUser.setPass(Md5Util.genMd5Hex(user.getPass()).toUpperCase());
		sysUser.setUserName(user.getUserName());
		int userType = user.getUserType();
		if(userType <= 0){
			sysUser.setUserType(UAOPConstant.UserType.NORMAL);
		}else{
			sysUser.setUserType(userType);
		}
		sysUser.setActiveStatus(user.getActiveStatus());
		sysUser.setAccountId(SysIDGenerator.getInstance().genAccountId());
		sysUser.setUnitLevel(user.getUnitLevel());
		sysUser.initBaseParameter();

		UserInfo userInfo = user;
		userInfo.setUserId(userId);
		userInfo.initBaseParameter();

		UserOrganization userOrganization = new UserOrganization();
		userOrganization.setUserId(userId);
		userOrganization.setOrgId(user.getOrgId());
		userOrganization.setType(0);
		userOrganization.setAdmin(0);

		mapper.add(sysUser);
		userInfoMapper.add(userInfo);
		userOrganizationMapper.add(userOrganization);
		
		MessageProcessor.getInstance().sendAddUserMsg(user); //发送添加用户消息
		
		user.setUserId(userId);
		return user;
	}

	
	/**
	 * 判断用户账号是否存在
	 * @param account
	 * @return 
	 * @throws Exception
	 */
	@GET
	@Path("account/{account}")
	public Map<String,String> isAccountExist(@PathParam("account") String account) throws Exception{
		int count = this.mapper.isAccountExist(account);
		Map<String,String> map = new HashMap<String,String>();
		if(count > 0){
			map.put("account", account);
			return map;
		}
		
		if(RequestContext.isRestInvoke()){ //当前是rest调用
			throw new RestBusinessException(Response.Status.NOT_FOUND,"用户名不存在");
		}else{
			return map;
		}
	}
	
	
	/**
	 * 判断身份证号是否存在
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	@GET
	@Path("cid/{cid}")
	public Map<String,String> isCidExist(@PathParam("cid") String cid) throws Exception{
		int count = this.userMapper.isCidExist(cid);
		Map<String,String> map = new HashMap<String,String>();
		if(count > 0){
			map.put("cid", cid);
			return map;
		}
		
		if(RequestContext.isRestInvoke()){ //当前是rest调用
			throw new RestBusinessException(Response.Status.NOT_FOUND,"身份证号不存在");
		}else{
			return map;
		}
	}
	
	/**
	 * 判断警员编号是否存在
	 * @param policeId
	 * @return 
	 * @throws Exception
	 */
	@GET
	@Path("policeId/{policeId}")
	public Map<String,String> isPoliceIdExist(@PathParam("policeId") String policeId) throws Exception{
		int count = this.userMapper.isPoliceIdExist(policeId);
		Map<String,String> map = new HashMap<String,String>();
		if(count > 0){
			map.put("policeId", policeId);
			return map;
		}
		
		if(RequestContext.isRestInvoke()){ //当前是rest调用
			throw new RestBusinessException(Response.Status.NOT_FOUND,"警员编号不存在");
		}else{
			return map;
		}
	}
	
	
	/**
	 * 根据用户Id获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	@GET
	@Path("{userId}")
	public User getUserByUserId(@PathParam("userId") String userId) throws Exception{
		User user = this.userMapper.getUserById(userId);
		if(user == null){
			throw new RestBusinessException(Response.Status.NOT_FOUND, "不存在该用户");
		}
	
		this.setUserFirstRoleName(user);
//		user.setAvatar("");
		
		return user;
	}
	
	/**
	 * 设置用户第一个角色名
	 * @param user
	 * @throws Exception
	 */
	private void setUserFirstRoleName(User user) throws Exception{
		String roleName = "";
		List<Role> roleList = roleMapper.getRoleListByAccount(user.getAccount());
		if(roleList != null && roleList.size() > 0){
			Role defaultRole = roleList.get(0);
			roleName = defaultRole.getRoleName();
		}
		
		user.setRoleName(roleName);
	}
	
	
	/**
	 * 根据用户Account获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	@GET
	@Path("{account}/byaccount")
	public User getUserByAccount(@PathParam("account") String account) throws Exception{
		User user = this.userMapper.getUserByAccount(account);
		if(user == null){
			throw new RestBusinessException(Response.Status.NOT_FOUND, "不存在该用户");
		}
	
		this.setUserFirstRoleName(user);
//		user.setAvatar("");
		
		return user;
	}
	
	/**
	 * 根据用户Account获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	@Override
	@GET
	@Path("{accountId}/byaccountId")
	public User getUserByAccountId(@PathParam("accountId") String accountId) throws Exception{
		SysUser sysUser = this.mapper.getByAccountId(accountId);
		if(sysUser == null){
			throw new RestBusinessException(Response.Status.NOT_FOUND, "不存在该用户");
		}
		
		String account = sysUser.getAccount();
		return this.getUserByAccount(account);
	}
	
	
	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON})
	public void update(User user) throws Exception {
		SysUser sysUser = user.fetchSysUser();
		sysUser.initUpdParameter();
		this.mapper.update(sysUser);
		
		UserInfo userInfo = user.fetchUserInfo();
		userInfo.initUpdParameter();
		this.userInfoMapper.update(userInfo);
		
		UserOrganization userOrg = user.fetchUserOrganization();
		userOrg.setType(0);
		this.userOrganizationMapper.delete(userOrg);
		this.userOrganizationMapper.add(userOrg);
		
		MessageProcessor.getInstance().sendUpdateUserMsg(user); //发送修改用户消息
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.UserService#findSuperUsers(java.util.List, java.lang.String)
	 */
	@Override
	public List<String> findSuperUsers(List<String> userIds, String status) {
		logger.debug("findSuperUsers(List<String> userIds, String status)");
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < userIds.size(); i++) {
			String userId = userIds.get(i);
			if (i > 0) {
				sb.append(",");
			}
			sb.append("'").append(userId).append("'");
		}
		sb.append(")");
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userIds", sb.toString());
		paraMap.put("status", status);
		return mapper.findSuperUsers(paraMap);
	}
	
	
	/**
	 * 定义一个空方法，主要用于测试过滤器检查token
	 * @return
	 * @throws Exception
	 */
	@GET
	@Path("token/check")
	public Map<String,String> checkToken() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "true");
		return map;
	}
	
	
	/**
	 * 根据userId列表删除用户
	 * @param userIdList
	 * @return
	 * @throws Exception
	 */
	@POST
	@Path("delete/byuserids")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String,String> deleteByUserIds(List<String> userIdList) throws Exception{
		try {
			SysUser t =new SysUser();
			t.initUpdParameter();
			
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("lastModifyAccount", t.getLastModifyAccount());
			map.put("lastModifyTime", t.getLastModifyTime());
			map.put("lastTerminal", t.getLastTerminal());
			map.put("lastSys", t.getLastSys());
			map.put("status", UAOPConstant.STATUS.DELETED); //删除
			map.put("ids", userIdList);
			
			mapper.updateStatusByIdList(map);
			userInfoMapper.updateStatusByIdList(map);
			userOrganizationMapper.deleteByUserIds(userIdList); //用户与组织机构对应关系删除
			sysUserRoleMapper.deleteByUserIds(userIdList); //用户与角色关系删除
			
			MessageProcessor.getInstance().sendDeleteUserMsg(userIdList);
		} catch (Exception e) {
			logger.error("删除失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}
	
	
	/**
	 * 获取某组织机构下的所有用户
	 * @param map
	 * @return
	 */
	@Override
	@POST
	@Path("listbyorganise")
	@Consumes({MediaType.APPLICATION_JSON})
	public List<User> getUsersUnderOrganise(Map<String,Object> map) throws Exception{
		List<User> list = this.userMapper.getUsersUnderOrganise(map);
		if(list == null || list.size() <= 0){
			return new ArrayList<User>();
		}
		return list;
	}
	
	/**
	 * 获取某组织机构下的所有用户数量
	 * @param map
	 * @return
	 */
	@Override
	@POST
	@Path("countbyorganise")
	@Consumes({MediaType.APPLICATION_JSON})
	public int getUserCountUnderOrganise(Map<String,Object> map) throws Exception{
	    int count = this.userMapper.getUserCountUnderOrganise(map);
		return count;
	}
	
	/**
	 * 获取某组织机构下的所有用户数量
	 * @param map
	 * @return
	 */
	@Override
	@POST
	@Path("allcountbyorganise")
	@Consumes({MediaType.APPLICATION_JSON})
	public int getAllUserCountUnderOrganise(Map<String,Object> map) throws Exception{
	    int count = this.userMapper.getAllUserCountUnderOrganise(map);
		return count;
	}

	@POST
	@Path("query")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> query(Map<String, Object> map) throws Exception{
		List<User> list = userMapper.query(map);
		if(list == null){
			return new ArrayList<User>();
		}
		
		String systemId = (String)map.get(UAOPConstant.KEY_SYSTEMID);
		for(User user : list){
        	String roleNameListStr = this.getRoleListStrByAccount(user.getAccount(),systemId);
        	user.setRoleName(roleNameListStr);
        }
		return list;
	}
	
	/**
	 * 分页查询
	 * @param map  查询条件
	 * @param pageNum 查询页
	 * @param pageSize 每页显示条数
	 * @param orderBy  排序
	 * @return
	 */
	@POST
	@Path("pagequery")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String,Object> queryByPagination(QueryFilter queryFilter)throws Exception {
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = getSortTablePrefix(orderBy) + orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		String systemId = null;
		if(condition != null){
			systemId = condition.getSystemId();
			map.put("systemId", systemId);
			String userId = condition.getUserId();
			map.put("userId", userId);
			String userName = condition.getUserName();
			map.put("userName", userName);
			String account = condition.getAccount();
			map.put("account", account);
			String roleName = condition.getRoleName();
			map.put("roleName", roleName);
			String roleId = condition.getRoleId();
			map.put("roleId", roleId);
			String noRoleId = condition.getNoRoleId();
			map.put("noRoleId", noRoleId);
			Integer userType = condition.getUserType();
			map.put("userType", userType);
			String orgName = condition.getOrgName();
			map.put("orgName", orgName);
			Integer orgType = condition.getOrgType();
			map.put("orgType", orgType);
			String cid = condition.getCid();
			map.put("cid", cid);
			String policeId = condition.getPoliceId();
			map.put("policeId", policeId);
			Integer isPolice = condition.getIsPolice();
			map.put("isPolice", isPolice);
			String contact = condition.getContact();
			map.put("contact", contact);
		}
		
		queryFilter.calculateOffsetAndLimit(); //计算offset和Limit
		PageHelper.offsetPage(queryFilter.getOffset(), queryFilter.getLimit(), orderBy);
		
        Page<User> page = (Page<User>)userMapper.query(map);
        
        for(User user : page){
        	String roleNameListStr = this.getRoleListStrByAccount(user.getAccount(),systemId);
        	user.setRoleName(roleNameListStr);
        }
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("users", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
	

	/**
	 * 根据账号获取账号列表
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getRoleListStrByAccount(String account,String systemId) throws Exception{
		List<Role> roleList = roleMapper.getRoleListByAccount(account);
		if(roleList == null || roleList.size() <= 0){
			return null;
		}
		
		StringBuffer sb = new StringBuffer();
		for(Role role : roleList){
			String roleName = role.getRoleName();
			if(StringUtils.isEmpty(roleName)){
				continue;
			}
			
			if(!StringUtils.isEmpty(systemId)){ //查询条件中有systemId
				if(role.getSystemId() == null || !role.getSystemId().equals(systemId)){
					continue;
				}
			}
			
			sb.append(roleName).append(",");
		}
		
		if(sb.length() > 0){
			sb = sb.deleteCharAt(sb.length()-1);
		}
		
		return sb.toString();
	}
	
	/**
	 * 修改密码
	 * @param map
	 * @throws Exception
	 */
	@POST
	@Path("passmodify")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> modifyPass(Map<String, Object> map) throws Exception {
		String account = (String)map.get("account");
		Map<String,String> result = sysAccountService.modifyPass(map);
		MessageProcessor.getInstance().sendUserPassModifyMsg(account); //发送用户密码变更消息
		return result;
	}
	
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{token}/infobytoken")
	public User getUserByToken(@PathParam("token") String token) throws Exception{
		LogToken logToken = logTokenMapper.getById(token);
		if(logToken == null){
			throw new RestBusinessException(Response.Status.NOT_FOUND, "找不到对应的token记录");
		}
		
		String account = logToken.getAccount();
		return this.getUserByAccount(account);
	}
	
	
	/**
	 * 查询用户基本信息(用户基本信息+组织机构)
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("userbaseinfo/query")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> queryUserBaseInfo(Map<String, Object> map) throws Exception{
		long t1 = System.currentTimeMillis();
		List<User> list = userMapper.queryUserBaseInfo(map);
		if(list == null){
			return new ArrayList<User>();
		}
		long t2 = System.currentTimeMillis();
		
		List<String> userIds = new ArrayList<String>();
		for(User t : list){
			userIds.add(t.getUserId());
		}
		
		//防止userIds超过1000进行处理
		List<UserSystem> userSystemList = null;
		if(userIds.size() > 1000){
			List<List<String>> userIdsList = StringUtils.splitList(userIds, 1000);
			userSystemList = new ArrayList<UserSystem>();
			for(List<String> subUserIdList : userIdsList){
				List<UserSystem> subUserSystemList = this.userSystemMapper.getByUserIdList(subUserIdList);
				if(subUserSystemList != null && subUserSystemList.size() > 0){
					userSystemList.addAll(subUserSystemList);
				}
			}
		}else{
			userSystemList = this.userSystemMapper.getByUserIdList(userIds);
		}
		
		Map<String,List<String>> systemIdsMap = new ConcurrentHashMap<String,List<String>>();
		if(userSystemList != null && userSystemList.size() > 0){
			for(UserSystem us : userSystemList){
				String userId = us.getUserId();
				String systemId = us.getSystemId();
				List<String> systemIds = null;
				if(systemIdsMap.containsKey(userId)){
					systemIds = systemIdsMap.get(userId);
				}else{
					systemIds = new ArrayList<String>();
					systemIdsMap.put(userId, systemIds);
				}
				systemIds.add(systemId);
			}
		}
		
		for(User t : list){
			t.setSystemIds(systemIdsMap.get(t.getUserId()));
		}
		
		long t3 = System.currentTimeMillis();
		
		logger.info("queryUserBaseInfo total during "+(t3-t1)+" t3-t2="+(t3-t2));
		return list;
	}
	
	/**
	 * 分页查询 用户基本信息(用户基本信息+组织机构)
	 * @param map  查询条件
	 * @param pageNum 查询页
	 * @param pageSize 每页显示条数
	 * @param orderBy  排序
	 * @return
	 */
	@Override
	@POST
	@Path("userbaseinfo/pagequery")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String,Object> queryUserBaseInfoByPagination(QueryFilter queryFilter)throws Exception {
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = getSortTablePrefix(orderBy) +  orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		String systemId = null;
		if(condition != null){
			systemId = condition.getSystemId();
			map.put("systemId", systemId);
			String userId = condition.getUserId();
			map.put("userId", userId);
			String userName = condition.getUserName();
			map.put("userName", userName);
			String cid = condition.getCid();
			map.put("cid", cid);
			String policeId = condition.getPoliceId();
			map.put("policeId", policeId);
			Integer isPolice = condition.getIsPolice();
			map.put("isPolice", isPolice);
			String contact = condition.getContact();
			map.put("contact", contact);
			String orgId = condition.getOrgId();
			map.put("orgId", orgId);
			String orgName = condition.getOrgName();
			map.put("orgName", orgName);
			String orgCode = condition.getOrgCode();
			map.put("orgCode", orgCode);
	        String superId = condition.getSuperId();
	        map.put("superId", superId);
		}
		
		queryFilter.calculateOffsetAndLimit(); //计算offset和Limit
		PageHelper.offsetPage(queryFilter.getOffset(), queryFilter.getLimit(), orderBy);
		
        Page<User> page = (Page<User>)userMapper.queryUserBaseInfo(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("users", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}
	
	/**
	 * 根据orderby判断前缀分别用哪个
	 * @param orderBy
	 * @return
	 */
	private String getSortTablePrefix(String orderBy){
		String USERINFO_TABLE_PREFIX = "i.";
		String ACCOUNT_TABLE_PREFIX = "a.";
		if(StringUtils.isEmpty(orderBy)){
			return USERINFO_TABLE_PREFIX;
		}
		
		if(orderBy.equalsIgnoreCase("ACCOUNT")
				|| orderBy.equalsIgnoreCase("USER_TYPE")
				|| orderBy.equalsIgnoreCase("ACCOUNT_ID")
				|| orderBy.equalsIgnoreCase("ACTIVE_STATUS")
				|| orderBy.equalsIgnoreCase("UNIT_LEVEL")
				){
			return ACCOUNT_TABLE_PREFIX;
		}
		
		return USERINFO_TABLE_PREFIX;
	}
	
	
	/**
	 * 添加账号信息
	 * @param t
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("account/add")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> addAccount(SysUser t) throws Exception{
		return this.sysAccountService.add(t);
	}
	
	/**
	 * 更新账号信息
	 * @param t
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("account/update")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> updateAccount(SysUser t) throws Exception{
		return this.sysAccountService.update(t);
	}
	
	/**
	 * 删除帐号信息
	 * @param account
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("account/{account}/delete")
	public Map<String, String> deleteAccount(@PathParam("account") String account) throws Exception{
		return this.sysAccountService.delete(account);
	}
	
	
	/**
	 * 添加用户基本信息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("userbaseinfo/add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> addUserBaseInfo(UserInfo t) throws Exception{
		return this.userInfoService.add(t);
	}
	
	/**
	 * 更新用户基本信息
	 * @param t
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("userbaseinfo/update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> updateUserBaseInfo(UserInfo t) throws Exception{
		return this.userInfoService.update(t);
	}
	
	/**
	 * 删除用户基本信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("userbaseinfo/{userId}/delete")
	public Map<String, String> deleteUserBaseInfo(@PathParam("userId") String userId) throws Exception{
		return this.userInfoService.delete(userId);
	}


	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.UserService#findByAccountList(java.util.List)
	 */
	@Override
	public List<User> findByAccountList(List<String> accountList) {
		logger.debug("List<User> findByAccountList(List<String> accountList)");
		return userMapper.findByAccountList(accountList);
	}
	
	
	
	/**
	 * 根据用户Id获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	@GET
	@Path("userbaseinfo/{userId}/info")
	public User getUserBaseInfoByUserId(@PathParam("userId") String userId) throws Exception{
		User user = this.userMapper.getUserBaseInfoByUserId(userId);
		if(user == null){
			throw new RestBusinessException(Response.Status.NOT_FOUND, "不存在该用户");
		}
		
		return user;
	}
	
	/**
	 * 根据角色Id获取含有该角色用户以及不含所有该角色的用户
	 * @param roleId 角色Id
	 * @return Map<String,List<Map>> hasRoleUsers:含有该角色的用户列表  notHasRoleUsers:不含有该角色的用户列表
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{roleId}/byrole")
	public Map<String,Object> getUsersByRoleId(@PathParam("roleId") String roleId) throws Exception{
		Map<String,Object> retMap = new HashMap<String,Object>();
		
		List<User> hasRoleUserList = this.userMapper.getUsersByRoleId(roleId);
		List<User> notHasRoleUserList = new ArrayList<User>();
		
		List<User> allUserList = this.userMapper.getAllSimpleAccountUsers();
		if(hasRoleUserList == null || hasRoleUserList.size() <= 0){
			notHasRoleUserList = allUserList;
		}else{
			Map<String,User> hasRoleUserMap = new ConcurrentHashMap<>();
			for(User hasRoleUser:hasRoleUserList){
				hasRoleUserMap.put(hasRoleUser.getAccount(), hasRoleUser);
			}
			
			for(User user : allUserList){
				if(!hasRoleUserMap.containsKey(user.getAccount())){ //不含有角色
					notHasRoleUserList.add(user);
				}
			}
		}
		
		List<Map<String,Object>> hasRoleUsers = this.userList2MapList(hasRoleUserList);
		List<Map<String,Object>> notHasRoleUsers = this.userList2MapList(notHasRoleUserList);
		retMap.put("hasRoleUsers", hasRoleUsers);
		retMap.put("notHasRoleUsers", notHasRoleUsers);
		
		return retMap;
	}
	
	/**
	 * 用户列表转换为Map列表
	 * @param userList
	 * @return
	 */
	private List<Map<String,Object>> userList2MapList(List<User> userList){
		if(userList == null || userList.size() <= 0){
			new ArrayList<Map<String,Object>>();
		}
		
		List<Map<String,Object>> userMapList = new ArrayList<Map<String,Object>>();
		for(User user : userList){
			String account = user.getAccount();
			String userName = user.getUserName();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("account", account);
			map.put("userName", userName);
			userMapList.add(map);
		}
		
		return userMapList;
	}
}
