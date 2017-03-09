package com.hisign.sso.api.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.hisign.sso.api.util.StringUtils;
import com.hisign.sso.api.rest.filter.RequestContext;

/**
 * @Title:
 *   为了记录修改，基础Bean实体
 * @description:
 * 
 * @author lnj 
 */
public class SysBaseEntity implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String UNKOWN_SYSTEM = "unkown"; //未知系统
	
	public static final String UNKOWN_ACCOUNT = "unkown"; //未知账户
	
	public static final String UNKOWN_IP = "0.0.0.0"; //未知ip
	
	private String id;
	
	//状态 : 0:正常 1:不可用
	private Integer status;

	//创建账号
	private String createAccount;

	//创建时间
	@JsonIgnore
	private Long createTime;

	//最后修改时间
	@JsonIgnore
	private Long lastModifyTime;
	
	//最后修改账户
	private String lastModifyAccount;
	
	//最后修改设备Ip
	@JsonIgnore
	private String lastTerminal;
	
	//最后修改系统Id
	@JsonIgnore
	private String lastSys;
	
	/**
	 * 显示序号
	 */
	private int rowNum;
	
	/**
	 * 初始化通用参数
	 * @return  
	 * @throws
	 */
	public void initBaseParameter(){
		status = 0;
		createTime = System.currentTimeMillis();
		//未取到登录用户
		if(StringUtils.isEmpty(createAccount)){
			if(StringUtils.isEmpty(RequestContext.getLoginAccount())){
				createAccount = UNKOWN_ACCOUNT;
			}else{
				createAccount = RequestContext.getLoginAccount();
			}
		}
		
		//未取到登录ip
		if(StringUtils.isEmpty(lastTerminal)){
			if(StringUtils.isEmpty(RequestContext.getRequestIp())){
				lastTerminal = UNKOWN_IP;
			}else{
				lastTerminal = RequestContext.getRequestIp();
			}
		}
		
		//未取到登录SystemId
		if(StringUtils.isEmpty(lastSys)){
			if(StringUtils.isEmpty(RequestContext.getSystemId())){
				lastSys = UNKOWN_SYSTEM;
			}else{
				lastSys = RequestContext.getSystemId();
			}
		}
	}
	
	/**
	 * 更新数据时刷新修改字段数据
	 * @return  
	 * @throws
	 */
	public void initUpdParameter(){
		lastModifyTime = System.currentTimeMillis();
		//未取到登录用户
		if(StringUtils.isEmpty(lastModifyAccount)){
			if(StringUtils.isEmpty(RequestContext.getLoginAccount())){
				lastModifyAccount = UNKOWN_ACCOUNT;
			}else{
				lastModifyAccount = RequestContext.getLoginAccount();
			}
		}
		
		//未取到登录ip
		if(StringUtils.isEmpty(lastTerminal)){
			if(StringUtils.isEmpty(RequestContext.getRequestIp())){
				lastTerminal = UNKOWN_IP;
			}else{
				lastTerminal = RequestContext.getRequestIp();
			}
		}
		
		//未取到登录SystemId
		if(StringUtils.isEmpty(lastSys)){
			if(StringUtils.isEmpty(RequestContext.getSystemId())){
				lastSys = UNKOWN_SYSTEM;
			}else{
				lastSys = RequestContext.getSystemId();
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public String getLastModifyAccount() {
		return lastModifyAccount;
	}

	public void setLastModifyAccount(String lastModifyAccount) {
		this.lastModifyAccount = lastModifyAccount;
	}

	public String getLastTerminal() {
		return lastTerminal;
	}

	public void setLastTerminal(String lastTerminal) {
		this.lastTerminal = lastTerminal;
	}

	public String getLastSys() {
		return lastSys;
	}

	public void setLastSys(String lastSys) {
		this.lastSys = lastSys;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
}
