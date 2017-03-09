/**
 * 
 */
package com.hisign.sso.api.rest.entity.sys;

import java.io.Serializable;

import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.entity.sys.UserOrganization;

/**
 * REST接口用户对象
 * @author chailiangzhi
 * @date 2016-8-16
 * 
 */
public class User extends UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 账户ID(主要是兼容现勘账户ID)
	 */
	private String accountId;

	/**
	 * 
	 */
	private String account;

	/**
	 * 
	 */
	private String pass;
	
	
	/**
	 * 用户类型
	 */
	private Integer userType;
	
	/**
	 * 用户启用状态  1:启用 0:禁止
	 */
	private Integer activeStatus;
	
	/**
	 * 所属单位级别
	 */
	private String unitLevel;
	
	/**
	 * 所属单位级别名称
	 */
	private String unitLevelName;

	/**
	 * 
	 */
	private String orgId;
	
	/**
	 * 组织机构名称
	 */
	private String orgName;
	
	/**
	 * 组织机构编码
	 */
	private String orgCode;
	
	/**
	 * 组织机构类型
	 */
	private Integer orgType;

	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 所属公司Id
	 */
	private String companyId;
	
	/**
	 * 所属公司名称
	 */
	private String companyName;
    

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	/**
	 * @return the orgId
	 */
	public String getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
    
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getUnitLevel() {
		return unitLevel;
	}

	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}
	
	public String getUnitLevelName() {
		return unitLevelName;
	}

	public void setUnitLevelName(String unitLevelName) {
		this.unitLevelName = unitLevelName;
	}
	
	/**
	 * 获取系统用户
	 * @return
	 */
	public SysUser fetchSysUser(){
		SysUser sysUser = new SysUser();
		sysUser.setAccount(this.account);
		sysUser.setUserName(this.getUserName());
		sysUser.setUserId(this.getUserId());
		sysUser.setUserType(this.userType);
		sysUser.setActiveStatus(this.activeStatus);
		sysUser.setPass(this.pass);
		sysUser.setNote(this.getNote());
		sysUser.setAccountId(this.accountId);
		sysUser.setUnitLevel(this.unitLevel);
		return sysUser;
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public UserInfo fetchUserInfo(){
		return this;
	}

	/**
	 * 获取用户组织机构关系
	 * @return
	 */
    public UserOrganization fetchUserOrganization(){
    	UserOrganization userOrg = new UserOrganization();
    	userOrg.setUserId(this.getUserId());
    	userOrg.setOrgId(this.getOrgId());
        return userOrg;
    }
    
}
