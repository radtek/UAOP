package com.hisign.sso.api.entity.sys;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * 系统参数实体
 * @author chailiangzhi
 * @date 2017-1-6
 * 
 */
public class Sysparam extends SysBaseEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String systemId;

	private String paramType;

	private String paramName;

	private String paramValue;

	private String createUser;

	private String extStr1;

	private String extStr2;

	private String extStr3;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId == null ? null : systemId.trim();
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType == null ? null : paramType.trim();
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName == null ? null : paramName.trim();
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue == null ? null : paramValue.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getExtStr1() {
		return extStr1;
	}

	public void setExtStr1(String extStr1) {
		this.extStr1 = extStr1 == null ? null : extStr1.trim();
	}

	public String getExtStr2() {
		return extStr2;
	}

	public void setExtStr2(String extStr2) {
		this.extStr2 = extStr2 == null ? null : extStr2.trim();
	}

	public String getExtStr3() {
		return extStr3;
	}

	public void setExtStr3(String extStr3) {
		this.extStr3 = extStr3 == null ? null : extStr3.trim();
	}
}