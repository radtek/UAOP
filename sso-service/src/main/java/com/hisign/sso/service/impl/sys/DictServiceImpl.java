/**
 * 
 */
package com.hisign.sso.service.impl.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sso.api.entity.sys.SysDict;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.DictService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.SysDictMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;
import com.hisign.sso.service.impl.msg.MessageProcessor;

/**
 * 字典服务实现
 * @author chailiangzhi
 * @date 2016-7-25
 * 
 */
@Service("dictService")
@Path("dicts")
@Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
@Produces({ ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8 })
public class DictServiceImpl implements DictService {

	/**
	 * 
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 系统字典表DAO
	 */
	@Autowired
	private SysDictMapper sysDictMapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	/**
	 * 
	 * @参数： rootKey
	 * @return list
	 * @throws
	 */
	@Override
	@GET
	@Path("rootkey/{rootKey}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SysDict> getDictByRootKey(@PathParam("rootKey") String rootKey) {
		List<SysDict> list = sysDictMapper.findSysDictsByRootKey(rootKey);
		return list;
	}
	
	/**
	 * 根据rootkey获取字典Map key=dictKey value=dictValue1
	 */
	@Override
	@GET
	@Path("{rootKey}/dictmapbyrootkey")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String,String> getDictMapByRootKey(@PathParam("rootKey") String rootKey) {
		List<SysDict> list = sysDictMapper.findSysDictsByRootKey(rootKey);
	    if(list == null || list.size() <= 0){
	    	return null;
	    }
	    
	    Map<String,String> map = new HashMap<String,String>();
	    for(SysDict dict : list){
	    	String dictKey = dict.getDictKey();
	    	String dictValue1 = dict.getDictValue1();
	    	map.put(dictKey, dictValue1);
	    }
	    
		return map;
	}

	/* (non-Javadoc)
	 * @see com.hisign.alims.api.service.sys.SysDictService#findDicts(java.util.List)
	 */
	@Override
	@POST
	@Path("rootkeybylist")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String, List<SysDict>> findDicts(List<String> rootKeys) {
		logger.debug("Map<String, List<SysDict>> findDicts(List<String> rootKeys)");
		List<SysDict> list = sysDictMapper.findSysDictsByRootKeys(rootKeys);
		Map<String, List<SysDict>> retMap = new HashMap<String, List<SysDict>>();
		
		for(SysDict sysDict: list){
			String rootKey = sysDict.getRootKey();
			List<SysDict> children = null;
			if(retMap.containsKey(rootKey)){
				children = retMap.get(rootKey);
			}else{
				children = new ArrayList<SysDict>();
				retMap.put(rootKey, children);
			}
			children.add(sysDict);
		}
		return retMap;
	}

	/* (non-Javadoc)
	 * @see com.hisign.alims.api.service.sys.SysDictService#findDict(com.hisign.alims.api.entity.sys.SysDict)
	 */
	@POST
	@Path("list")
	@Produces({MediaType.APPLICATION_JSON})
	public List<SysDict> findDict(SysDict dictPara) {
		logger.debug("List<SysDict> findDict(SysDict dictPara)");
//		dictPara.setType("0");
		return sysDictMapper.find(dictPara);
	}
	
	
    /**
     * 根据rootKey和dictKey唯一获取字典对象
     * @param map
     * @return
     */
    public SysDict getSysDictByRootAndDictKey(String rootKey,String dictKey) throws Exception{
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("rootKey", rootKey);
    	map.put("dictKey", dictKey);
    	
    	return sysDictMapper.getSysDictByRootAndDictKey(map);
    }
    
    @POST
	@Path("findByRootAndDictKey")
	@Produces({MediaType.APPLICATION_JSON})
    public SysDict findByRootAndDictKey(Map<String,Object> map) {
    	return sysDictMapper.getSysDictByRootAndDictKey(map);
    }
    
    /**
     * 获取dictValue
     * @param rootKey
     * @param dictKey
     * @return
     * @throws Exception
     */
    public String getDictValue1(String rootKey,String dictKey) throws Exception{
    	SysDict dict = this.getSysDictByRootAndDictKey(rootKey, dictKey);
    	if(dict != null){
    		return dict.getDictValue1();
    	}
    	
    	return null;
    }
    
    /**
     * 获取dictValue列表，逗号分隔
     * @param rootKey
     * @param dictKeyListStr dictKey列表逗号分隔
     * @return dictValue1ListStr dictValue1列表逗号分隔
     * @throws Exception
     */
    public String getDictValue1ListStr(String rootKey,String dictKeyListStr) throws Exception{
    	if(StringUtils.isEmpty(rootKey) || StringUtils.isEmpty(dictKeyListStr)){
    		return null;
    	}
    	
    	List<String> dictKeys = new ArrayList<String>();
    	String []arr = dictKeyListStr.split(",");
    	for(String dictKey : arr){
    		if(!StringUtils.isEmpty(dictKey)){
    			dictKeys.add(dictKey);
    		}
    	}
    	
    	if(dictKeys == null || dictKeys.size() <= 0){
    		return null;
    	}
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("rootKey", rootKey);
    	map.put("dictKeys", dictKeys);
    	List<SysDict> dictList = this.sysDictMapper.getSysDictListByRootAndDictKeyList(map);
    	if(dictList == null || dictList.size() <= 0){
    		return null;
    	}
    	
    	StringBuffer sb = new StringBuffer();
    	for(SysDict dict : dictList){
    		String dictValue1 = dict.getDictValue1();
    		if(StringUtils.isEmpty(dictValue1)){
    			continue;
    		}
    		
    		sb.append(dictValue1).append(",");
    	}
    	
    	if(sb.length() > 0){
    		sb = sb.deleteCharAt(sb.length() -1);
    	}
    	
    	return sb.toString();
    }

    @Override
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> add(SysDict t) throws Exception {
    	String id = t.getId();
		if(StringUtils.isEmpty(id)){
			id = SysIDGenerator.getInstance().genId();
			t.setId(id);
		}
		t.initBaseParameter();
		try {
			sysDictMapper.add(t);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("id", id);
		retMap.put("message", "成功");
		
		MessageProcessor.getInstance().sendAddDictMsg(t); //发送消息
		
		return retMap;
	}

    @Override
	@POST
	@Path("batchadd")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> addBatch(List<SysDict> list) throws Exception {
    	for(SysDict t : list){
    		String id = t.getId();
    		if(StringUtils.isEmpty(id)){
    			id = SysIDGenerator.getInstance().genId();
    			t.setId(id);
    		}
    		t.initBaseParameter();
		}
    	
		try {
			batchCommitHelper.addBatch(SysDictMapper.class, list);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		
		MessageProcessor.getInstance().sendBatchAddDictMsg(list); //发送消息
		
		return retMap;
	}
    
    
	@POST
	@Path("update")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String, String> update(SysDict t) throws Exception {
		t.initUpdParameter();
		try {
			sysDictMapper.update(t);
		} catch (Exception e) {
			logger.error("修改失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "修改失败");
		}
		
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		
		MessageProcessor.getInstance().sendUpdateDictMsg(t); //发送消息
		
		return retMap;
	}
	
	@GET
	@Path("{id}/delete")
	public Map<String, String> delete(@PathParam("id") String id) throws Exception {
		Map<String, String> retMap = new HashMap<String, String>();
		SysDict existDict = this.sysDictMapper.getById(id);
		if(existDict == null){
			retMap.put("message", "没有对应的字典项");
			return retMap;
		}
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("id", existDict.getId());
		paramMap.put("rootKey", existDict.getRootKey());
		
		List<SysDict> dictList = this.sysDictMapper.getNestChildrenById(paramMap);
		
		try {
			List<String> ids = new ArrayList<String>();
			for(SysDict dict : dictList){
				ids.add(dict.getId());
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("ids", ids);
			sysDictMapper.deleteByCondition(map);
		} catch (Exception e) {
			logger.error("删除失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		
		retMap.put("message", "成功");
		
		MessageProcessor.getInstance().sendDeleteDictMsg(dictList);; //发送消息
		
		return retMap;
	}
	
	
	/**
	 * 按照条件去删除
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Path("deletebycondition")
	@Consumes({MediaType.APPLICATION_JSON})
	public Map<String,String> deleteByCondition(Map<String,Object> map) throws Exception{
		Map<String, String> retMap = new HashMap<String, String>();
		List<SysDict> dictList = this.sysDictMapper.query(map);
		if(dictList == null || dictList.size() <= 0){
			retMap.put("message", "没有对应的字典项");
			return retMap;
		}
		
		try {
			sysDictMapper.deleteByCondition(map);
		} catch (Exception e) {
			logger.error("删除失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}

		retMap.put("message", "成功");
		MessageProcessor.getInstance().sendDeleteDictMsg(dictList);; //发送消息
		
		return retMap;
	}
	
	
	/**
	 * 获取所有字典项
	 * @return
	 */
	public List<SysDict> getAll() throws Exception{
		return sysDictMapper.getAll();
	}
}
