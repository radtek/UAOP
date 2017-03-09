package com.hisign.sso.service.impl.sys;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hisign.sso.api.entity.sys.DeviceRegist;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.DeviceRegistService;
import com.hisign.sso.persist.mapper.sys.DeviceRegistMapper;

/**
 * 设备注册服务(操作本地库)
 * @author chailiangzhi
 * @date 2016-12-7
 * 
 */
@Transactional
@Service("deviceRegistService")
public class DeviceRegistServiceImpl implements DeviceRegistService {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DeviceRegistMapper deviceRegistMapper;

	/* (non-Javadoc)
	 * @see com.hisign.mpp.api.service.sys.DeviceRegistService#qryRegistInfo(com.hisign.mpp.api.entity.sys.DeviceRegist)
	 */
	@Override
	public DeviceRegist qryRegistInfo(DeviceRegist deviceRegist) {
		logger.debug("qryRegistInfo");
		List<DeviceRegist> deviceRegistList = deviceRegistMapper.find(deviceRegist);
		if (deviceRegistList == null || deviceRegistList.isEmpty()) {
			return null;
		} else if (deviceRegistList.size() == 1) {
			return deviceRegistList.get(0);
		} else {
			logger.warn("查到多于一条注册信息");
			throw new RestBusinessException(Response.Status.NOT_ACCEPTABLE, "查到多于一条注册信息");
		}

	}

	/* (non-Javadoc)
	 * @see com.hisign.mpp.api.service.sys.DeviceRegistService#addRegistInfo(com.hisign.mpp.api.entity.sys.DeviceRegist)
	 */
	@Override
	public void addRegistInfo(DeviceRegist deviceRegist) {
		logger.debug("addRegistInfo");
		try {
			deviceRegistMapper.add(deviceRegist);
		} catch (Exception e) {
			logger.error("添加注册信息失败", e);
			throw new RestBusinessException(Response.Status.NOT_ACCEPTABLE, "添加注册信息失败");
		}

	}

}
