/**
 * 服务层启动主类
 */
package com.hisign.sso.service.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sdk.common.util.LockUtils;
import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sso.api.constant.UAOPConstant;

/**
 * 服务层启动主类
 * @author chailiangzhi
 * @date 2015-10-13
 * 
 */
public class ServiceProvider {

	/**
	 * 
	 */
	private static final Logger logger = LoggerFactory.getLogger(ServiceProvider.class);

	/**
	 * 
	 */
	private static void initPara() {
		SysConfigLoader.getInstance().loadSysConfig(UAOPConstant.SYSTEMID);
		// System.setProperty("zkconnect", "127.0.0.1:2181");
		// System.setProperty("dubbo.registry.address", "zookeeper://172.16.0.114:52181");
		// System.setProperty("rest_port", "8811");
	}

	/**
	 * 获取zookeeper共享锁
	 */
	private static void acquireLock() {
		String zkAddress = System.getProperty("zkconnect");
		String path = "/sso_started";
		try {
			logger.info("开始获取zookeeper共享锁");
			LockUtils.zkLock(zkAddress, path).acquire();
			logger.info("成功获得锁");
		} catch (Exception e) {
			logger.error("获取zookeeper共享锁异常", e);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		initPara();
		acquireLock();
		com.alibaba.dubbo.container.Main.main(null);

	}
}
