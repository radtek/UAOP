package com.hisign.sso.persist.mapper.sys;


import java.util.List;
import java.util.Map;

import com.hisign.sso.api.persist.BaseMapper;
import com.hisign.sso.api.rest.entity.sys.User;

/**
 * @Title:
 *  用户信息(包含sysuser和userinfo)Mapper
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月12日  上午10:21:02
 */
public interface UserMapper extends BaseMapper<User> {
	
	
	/**
	 * 根据用户Id获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId);
	
	/**
	 * 判断身份证号是否存在
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	public int isCidExist(String cid);
	
	/**
	 * 判断警员编号是否存在
	 * @param policeId
	 * @return 
	 * @throws Exception
	 */
	public int isPoliceIdExist(String policeId);
	
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	public List<User> getUsersUnderOrganise(Map<String,Object> map);
	
	/**
	 * 获取某个组织组织机构下的用户数量
	 * @param map
	 * @return
	 */
	public int getUserCountUnderOrganise(Map<String,Object> map);
	
	/**
	 * 获取某个组织组织机构下的所有用户数量(递归组织机构)
	 * @param map
	 * @return
	 */
	public int getAllUserCountUnderOrganise(Map<String,Object> map);
	
	/**
	 * 根据用户账号account获取用户完整信息，包含组织机构名称等
	 * @param userId
	 * @return
	 */
	public User getUserByAccount(String account);
	
	/**
	 * 根据accountList获取用户列表
	 * @param accountList
	 * @return
	 */
	public List<User> findByAccountList(List<String> accountList);
	
	/**
	 * 查询用户基本信息
	 * @param map
	 * @return
	 */
	public List<User> queryUserBaseInfo(Map<String,Object> map);
	
	/**
	 * 根据用户id获取用户基本信息
	 * @param userId
	 * @return
	 */
	public User getUserBaseInfoByUserId(String userId);
	
	/**
	 * 根据roleId获取用户列表
	 * @param roleId
	 * @return
	 */
	public List<User> getUsersByRoleId(String roleId);
	
	/**
	 * 获取所有的账户用户(只包含账户和用户名)
	 * @return
	 */
	public List<User> getAllSimpleAccountUsers();

}
