/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hisign.sso.api.entity.sys.Sysparam;
import com.hisign.sso.api.service.sys.SysparamService;
import com.hisign.sso.persist.mapper.sys.SysparamMapper;

/**
 * 系统参数服务实现
 * @author chailiangzhi
 * @date 2017-1-6
 * 
 */
@Service("sysparamService")
public class SysparamServiceImpl implements SysparamService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SysparamMapper sysparamMapper;

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.SysparamService#find(com.hisign.sso.api.entity.sys.Sysparam)
	 */
	@Override
	public List<Sysparam> find(Sysparam sysparam) {
		logger.debug("List<Sysparam> find(Sysparam sysparam)");
		return sysparamMapper.find(sysparam);
	}

}
