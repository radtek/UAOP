package com.hisign.sso.api.entity.sys;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * 单点登录系统账号表
 * @author chailiangzhi
 * @date 2016-6-28
 * 
 */
public class SysUser extends SysBaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 账户ID(主要是兼容现勘账户ID)
	 */
	private String accountId;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String pass;

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 用户类型
	 */
	private Integer userType = -1;
	
	/**
	 * 用户启用状态  1:启用 0:禁止
	 */
	private Integer activeStatus = 1;
	
	/**
	 * 备注
	 */
	private String note;
	
	/**
	 * 所属单位级别
	 */
	private String unitLevel;
	
	/**
	 * 所属单位级别名称
	 */
	private String unitLevelName;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

}