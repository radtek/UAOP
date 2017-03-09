package com.hisign.sso.api.entity.sys;

import java.io.Serializable;

/**
 * @Title:
 *   用户系统关系对象
 * @description:
 * 
 * @author lnj 
 */
public class UserSystem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String userId;

    private String systemId;
    
	public UserSystem() {
	}

	public UserSystem(String userId, String systemId) {
		this.userId = userId;
		this.systemId = systemId;
	}

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

}
