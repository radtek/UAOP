package com.hisign.sso.api.service.sys;

import com.hisign.sso.api.entity.sys.DeviceRegist;

/**
 * 设备注册服务(操作本地库)
 * @author chailiangzhi
 * @date 2016-12-7
 * 
 */
public interface DeviceRegistService {

	/**
	 * 查询本地保存的设备注册信息
	 * @param deviceRegist
	 * @return
	 */
	public DeviceRegist qryRegistInfo(DeviceRegist deviceRegist);

	/**
	 * 添加注册信息到本地库
	 * @param deviceRegist
	 */
	public void addRegistInfo(DeviceRegist deviceRegist);
}
