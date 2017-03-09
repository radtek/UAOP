package com.hisign.sso.api.query;

import java.io.Serializable;

/**
 * @Title:
 *   查询条件
 * @description:
 * 
 * @author lnj 
 */
public class QueryCondition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 系统唯一标识
	 */
	private String systemId;
	
	/**
	 * 用户Id
	 */
	private String userId; 
	
	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 用户账号
	 */
	private String account;
	
	/**
	 * 用户类型
	 */
	private Integer userType=-1;
	
    /**
     * 身份证号
     */
	private String cid;
	
    /**
     * 警员ID号
     */
	private String policeId;
	
	/**
	 * 是否为辅警
	 */
	private Integer isPolice = -1;
	
	/**
	 * 状态
	 */
	private Integer status = -1;
	
	/**
	 * 角色Id
	 */
	private String roleId;
	
	/**
	 * 不包含角色的角色Id
	 */
	private String noRoleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 组织机构ID
	 */
	private String orgId;
	
	/**
	 * 机构代码 
	 */
	private String orgCode;
	
	/**
	 * 组织机构名称
	 */
	private String orgName;
	
	/**
	 * 权限资源ID
	 */
	private String resourceId;

	/**
	 * 权限资源名称
	 */
	private String resourceName;
	
	/**
	 * 说明
	 */
	private String note;
	
	/**
	 * 组织机构类型
	 */
	private Integer orgType = -1;
	
	/**
	 * 类型
	 */
	private Integer type = -1;
	
	/**
	 * 上级Id
	 */
	private String superId;
	
	/**
	 * 服务名
	 */
	private String serviceName;
	
	/**
	 * 接口名
	 */
	private String interfaceName;
	
	/**
	 * 联系方式
	 */
	private String contact; 
	
	/**
	 * 公司名
	 */
	private String companyName;
	
	/**
	 * 拼音
	 */
	private String pinyin;
	
	/**
	 * 首字母
	 */
	private String firstLetter;
	
	/**
	 * 标题
	 */
    private String title;

    /**
	 * 发送状态:0:未发送 1:已发送
	 */
    private Integer sendStatus;

    /**
	 * 发送人
	 */
    private String sender;
    
	/**
	 * 发送时间开始
	 */
	private String sendTimeStart;
	
	/**
	 * 发送时间结束
	 */
	private String sendTimeEnd;
    
    /**
	 * 发送单位
	 */
    private String sendUnit;
    
    /**
	 * 发送类型:0:发送给单位 1:发送给个人
	 */
    private Integer sendType;
    
    /**
	 * 接受单位Id,多个单位之间以逗号分隔
	 */
    private String recieveUnit;
    
    /**
	 * 接受人userId,多个之间以逗号分隔
	 */
    private String recieveUser;
    
    /**
	 * 反馈状态:0:未反馈 1:已反馈
	 */
    private Integer feedbackStatus;
	

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getNoRoleId() {
		return noRoleId;
	}

	public void setNoRoleId(String noRoleId) {
		this.noRoleId = noRoleId;
	}

	public Integer getIsPolice() {
		return isPolice;
	}

	public void setIsPolice(Integer isPolice) {
		this.isPolice = isPolice;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendUnit() {
		return sendUnit;
	}

	public void setSendUnit(String sendUnit) {
		this.sendUnit = sendUnit;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getRecieveUnit() {
		return recieveUnit;
	}

	public void setRecieveUnit(String recieveUnit) {
		this.recieveUnit = recieveUnit;
	}

	public String getRecieveUser() {
		return recieveUser;
	}

	public void setRecieveUser(String recieveUser) {
		this.recieveUser = recieveUser;
	}

	public Integer getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(Integer feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public String getSendTimeStart() {
		return sendTimeStart;
	}

	public void setSendTimeStart(String sendTimeStart) {
		this.sendTimeStart = sendTimeStart;
	}

	public String getSendTimeEnd() {
		return sendTimeEnd;
	}

	public void setSendTimeEnd(String sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}
}
