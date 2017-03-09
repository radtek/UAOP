/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.List;

import com.hisign.sso.api.entity.sys.Sysparam;

/**
 * 系统参数服务
 * @author chailiangzhi
 * @date 2017-1-6
 * 
 */
public interface SysparamService {

	/**
	 * 查询系统参数
	 * @param sysparam
	 * @return
	 */
	public List<Sysparam> find(Sysparam sysparam);

}
