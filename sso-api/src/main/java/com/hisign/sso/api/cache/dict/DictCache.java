package com.hisign.sso.api.cache.dict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.hisign.sso.api.entity.sys.SysDict;

/**
 * @Title:
 *   字典缓存
 * @description:
 * 
 * @author lnj 
 */
public class DictCache {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 字典缓存 key=systemId,value=Map<rootKey,dictKey,SysDict>
	 */
	private Map<String,Map<String,SysDict>> dictMap = new ConcurrentHashMap<String, Map<String,SysDict>>();
	
	private static DictCache instance = new DictCache();
	
	public static DictCache getInstance(){
		return instance;
	}
	
	private DictCache(){
	}

	public Map<String, Map<String, SysDict>> getDictMap() {
		return dictMap;
	}
	
	/**
	 * 根据rootKey和dictKey组织字典key值
	 * @param rootKey
	 * @param dictKey
	 * @return
	 */
	public String getKey(String rootKey,String dictKey){
		return rootKey+","+dictKey;
	}
	
	/**
	 * 获取业务系统所有的dict列表
	 * @param systemId 业务系统唯一标识
	 * @return
	 */
	public List<SysDict> getAllDictList(String systemId){
		if(StringUtils.isEmpty(systemId)){
			log.error("getAllDictList systemId is empty!");
			return null;
		}
		
		Map<String,SysDict> systemDictMap = this.dictMap.get(systemId.toUpperCase());
		if(systemDictMap == null || systemDictMap.size() <= 0){
			log.error("getAllDictList can't get systemDictMap from dictMap systemId="+systemId);
			return null;
		}
		
		List<SysDict> systemDictList = new ArrayList<SysDict>(systemDictMap.values());
		Collections.sort(systemDictList,new DictComparator());
		
		log.info("getAllDictList systemId="+systemId+" size="+systemDictList.size());
		
		return systemDictList;
	}
	
	/**
	 * 根据rootKey获取业务系统dict列表
	 * @param systemId 业务系统唯一标识
	 * @param rootKey  
	 * @return
	 */
	public List<SysDict> getDictListByRootKey(String systemId,String rootKey){
		List<SysDict> systemDictList = this.getAllDictList(systemId);
		if(systemDictList == null || systemDictList.size() <= 0){
			log.error("getDictListByRootKey can't get systemDictList systemId="+systemId+",rootKey="+rootKey);
			return null;
		}
		
		List<SysDict> rootKeyDictList = new ArrayList<SysDict>();
		for(SysDict dict : systemDictList){
			if(dict.getRootKey().equalsIgnoreCase(rootKey)){
				rootKeyDictList.add(dict);
			}
		}
		
		log.info("getDictListByRootKey systemId="+systemId+" rootKey="+rootKey+" size="+systemDictList.size());
		
		return rootKeyDictList;
	}
	
	/**
	 * 根据rootKe和dictKey获取字典
	 * @param systemId
	 * @param rootKey
	 * @param dictKey
	 * @return
	 */
	public SysDict getDictByDictKey(String systemId,String rootKey,String dictKey){
		Map<String,SysDict> systemDictMap = this.dictMap.get(systemId.toUpperCase());
		if(systemDictMap == null || systemDictMap.size() <= 0){
			log.error("getDictByDictKey can't get systemDictMap from dictMap systemId="+systemId);
			return null;
		}
		
		String key = this.getKey(rootKey, dictKey);
		SysDict dict = systemDictMap.get(key);
		return dict;
	}
	
	/**
	 * 根据rootKe和dictKey获取字典
	 * @param systemId
	 * @param rootKey
	 * @param dictKey
	 * @return
	 */
	public String getDictValueByDictKey(String systemId,String rootKey,String dictKey){
		SysDict dict = this.getDictByDictKey(systemId, rootKey, dictKey);
		if(dict == null){
			log.error("getDictValueByDictKey can't get dict by systemId="+systemId+",rootKey="+rootKey+",dictKey="+dictKey);
			return null;
		}
		return dict.getDictValue1();
	}
}
