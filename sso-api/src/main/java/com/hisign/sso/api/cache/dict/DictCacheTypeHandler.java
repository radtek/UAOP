package com.hisign.sso.api.cache.dict;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.SysDict;
import com.hisign.sso.api.util.StringUtils;

/**
 * @Title:
 *   字典缓存mybatis数据处理器
 * @description: 
 *   mybatis从数据库读取字典数据时，从本地缓存中进行转义
 * @author lnj 
 */
public class DictCacheTypeHandler extends BaseTypeHandler<String> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter);
	}

	@Override
	public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String rootDictKey = rs.getString(columnName);
		String value = this.getDictValueFromCache(rootDictKey);
		logger.info("1. get dict value="+value+" from cache by rootDictKey="+rootDictKey);
		return value;
	}

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String rootDictKey = rs.getString(columnIndex);
		String value = this.getDictValueFromCache(rootDictKey);
		logger.info("2. get dict value="+value+" from cache by rootDictKey="+rootDictKey);
		return value;
	}

	@Override
	public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String rootDictKey = cs.getString(columnIndex);
		String value = this.getDictValueFromCache(rootDictKey);
		logger.info("3. get dict value="+value+" from cache by rootDictKey="+rootDictKey);
		return value;
	}
	
	/**
	 * 从缓存中读取字典值
	 * @param rootDictKey rootKey+DictKey组合
	 * @return
	 */
	private String getDictValueFromCache(String rootDictKey){
		if(StringUtils.isEmpty(rootDictKey)){
			return null;
		}
		
		String systemId = System.getProperty(UAOPConstant.KEY_SYSTEMID);
		if(StringUtils.isEmpty(systemId)){
			systemId = UAOPConstant.UNKOWN_SYSTEMID;
		}
		
		String dictValue = this.getDictValueFromCache(systemId, rootDictKey);
		if(StringUtils.isEmpty(dictValue)){ //如果获取为空，则尝试从UAOP中进行获取
			dictValue = this.getDictValueFromCache(UAOPConstant.SYSTEMID, rootDictKey); 
		}
		
		return dictValue;
	}
	
	/**
	 * 从缓存中读取字典值
	 * @param systemId 系统唯一标识
	 * @param rootDictKey rootKey+DictKey组合
	 * @return
	 */
	private String getDictValueFromCache(String systemId,String rootDictKey){
		Map<String,Map<String,SysDict>> dictMap = DictCache.getInstance().getDictMap();
		Map<String,SysDict> map = dictMap.get(systemId);
		if(map == null || map.size() <= 0){
			return null;
		}
		
		String key = rootDictKey.toUpperCase();
		SysDict dict = map.get(key);
		if(dict == null){
			return null;
		}
		
		String dictValue = dict.getDictValue1();
		
		return dictValue;
	}

}
