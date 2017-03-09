package com.hisign.sso.persist.mapper.sys;

import java.util.List;

import com.hisign.sso.api.entity.sys.UserSystem;
import com.hisign.sso.api.persist.BaseMapper;

public interface UserSystemMapper extends BaseMapper<UserSystem>{
	
	public void deleteByUserId(String userId);
	
	public void deleteByUserIdList(List<String> userIdList);
	
	public void deleteBySystemId(String systemId);
	
	public List<String> getSystemIdsByUserId(String userId);
	
	public List<UserSystem>  getByUserIdList(List<String> userIds);
}