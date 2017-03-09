package com.hisign.sso.api.entity.sys;

import java.io.Serializable;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * @Title:
 *   用户角色
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月6日  上午10:36:15
 */
public class Role extends SysBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色Id
	 */
	private String roleId;
	
	/**
	 * 角色名称
	 */
	private String roleName;
	
	/**
	 * 上级角色ID
	 */
	private String superId;
	
	/**
	 * 角色类型: 0:普通 1：默认内置
	 */
	private Integer type;
	
	/**
	 * 角色说明
	 */
	private String note;
	
	/**
	 * 系统唯一标识
	 */
	private String systemId;
	
	/**
	 * 角色启用状态  1:启用 0:禁止
	 */
	private Integer activeStatus;
	
	/**
	 * 角儿英文名称
	 */
	private String roleEnName;

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

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
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

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getRoleEnName() {
		return roleEnName;
	}

	public void setRoleEnName(String roleEnName) {
		this.roleEnName = roleEnName;
	}

}
