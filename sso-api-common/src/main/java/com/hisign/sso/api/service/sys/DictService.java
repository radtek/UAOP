/**
 * 
 */
package com.hisign.sso.api.service.sys;

import java.util.List;
import java.util.Map;


import com.hisign.sso.api.entity.sys.SysDict;

/**
 * 字典服务，
 * REST字典接口
 * @author chailiangzhi
 * @date 2016-6-22
 * 
 */
public interface DictService {

	/**
	 * 方法功能说明： 查询数据字典集合 
	 * @参数： rootKey
	 * @return list
	 * @throws
	 */
	public List<SysDict> getDictByRootKey(String rootKey);
	
	/**
	 * 根据rootkey获取字典Map 
	 * @param rootKey
	 * @return map key=dictKey value=dictValue1
	 */
	public Map<String,String> getDictMapByRootKey(String rootKey);

	/**
	 * 查多个字典
	 * @param rootKeys
	 * @return
	 */
	public Map<String, List<SysDict>> findDicts(List<String> rootKeys);

	/**
	 * 查字典列表
	 * @param rootKeys
	 * @return
	 */
	public List<SysDict> findDict(SysDict dictPara);
	
    /**
     * 根据rootKey和dictKey唯一获取字典对象
     * @param map
     * @return
     */
    public SysDict getSysDictByRootAndDictKey(String rootKey,String dictKey) throws Exception;
    
    /**
     * 根据rootKey和dictKey唯一获取字典对象
     * @param map
     * @return
     */
    public SysDict findByRootAndDictKey(Map<String,Object> map);
    
    /**
     * 获取dictValue
     * @param rootKey
     * @param dictKey
     * @return
     * @throws Exception
     */
    public String getDictValue1(String rootKey,String dictKey) throws Exception;
    
    
    /**
     * 获取dictValue列表，逗号分隔
     * @param rootKey
     * @param dictKeyListStr dictKey列表逗号分隔
     * @return dictValue1ListStr dictValue1列表逗号分隔
     * @throws Exception
     */
    public String getDictValue1ListStr(String rootKey,String dictKeyListStr) throws Exception;
    
    public Map<String, String> add(SysDict t) throws Exception;
    
    public Map<String, String> update(SysDict t) throws Exception;
    
    public Map<String, String> addBatch(List<SysDict> list) throws Exception;
    
	/**
	 * 获取所有字典项
	 * @return
	 */
	public List<SysDict> getAll() throws Exception;
	
	/**
	 * 按id递归删除字典项以及子字典项
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> delete(String id) throws Exception;
	
	/**
	 * 按条件删除字典项
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> deleteByCondition(Map<String,Object> map) throws Exception;

}
