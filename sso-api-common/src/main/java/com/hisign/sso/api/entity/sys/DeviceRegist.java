package com.hisign.sso.api.entity.sys;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * 设备注册表实体
 * @author chailiangzhi
 * @date 2016-12-7
 * 
 */
public class DeviceRegist extends SysBaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String deviceType;

	private String deviceId;

	private String unitCode;

	private String hisignPn;

	private String openFlag;

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType == null ? null : deviceType.trim();
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId == null ? null : deviceId.trim();
	}

	/**
	 * @return the unitCode
	 */
	public String getUnitCode() {
		return unitCode;
	}

	/**
	 * @param unitCode the unitCode to set
	 */
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getHisignPn() {
		return hisignPn;
	}

	public void setHisignPn(String hisignPn) {
		this.hisignPn = hisignPn == null ? null : hisignPn.trim();
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag == null ? null : openFlag.trim();
	}

}