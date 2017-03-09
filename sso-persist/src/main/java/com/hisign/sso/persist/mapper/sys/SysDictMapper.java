/**
 * 
 */
package com.hisign.sso.persist.mapper.sys;

import java.util.List;
import java.util.Map;

import com.hisign.sso.api.entity.sys.SysDict;
import com.hisign.sso.api.persist.BaseMapper;

/**
 * 系统字典表DAO
 * @author chailiangzhi
 * @date 2016-6-29
 * 
 */
public interface SysDictMapper extends BaseMapper<SysDict> {

	/**
	 * 根据RootKey查询数据字典集合且ParentKey不为空
	 * @参数： 
	 * @参数：    
	 * @return  
	 * @throws
	 */
    public List<SysDict> findSysDictsByRootKey(String rookey);
    
    
    /**
     * 根据rootKey和dictKey唯一获取字典对象
     * @param map
     * @return
     */
    public SysDict getSysDictByRootAndDictKey(Map<String,Object> map);
    
    /**
     * 根据rootKey和dictKey列表获取字典对象列表
     * @param map
     * @return
     */
    public List<SysDict> getSysDictListByRootAndDictKeyList(Map<String,Object> map);
    
    /**
     * 批量获取数据
     * @param rootkeyList
     * @return
     */
    public List<SysDict> findSysDictsByRootKeys(List<String> rootkeyList);
    
	/**
	 * 按条件进行删除
	 * @param map
	 */
	public void deleteByCondition(Map<String,Object> map);
	
	/**
	 * 获取所有字典项
	 * @return
	 */
	public List<SysDict> getAll();
	
	/**
	 * 递归获取字典项以及子字典项
	 * @param id
	 * @return
	 */
	public List<SysDict> getNestChildrenById(Map<String,Object> map);
}
