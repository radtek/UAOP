package com.hisign.sso.service.impl.sys;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.hisign.sdk.common.util.CommonIOUtils;
import com.hisign.sdk.common.util.StringUtils;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.MenuResource;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.rest.filter.RequestContext;
import com.hisign.sso.api.service.sys.DictService;
import com.hisign.sso.api.service.sys.MenuResourceService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.MenuResourceMapper;
import com.hisign.sso.persist.mapper.sys.SysUserMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;


/**
 * @Title:
 *   菜单权限点服务实现
 * @description:
 * 
 * @author lnj 
 * @create time：2016年7月15日  上午10:03:39
 */

@Path("menuresources")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("menuResourceService")
public class MenuResourceServiceImpl implements MenuResourceService {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuResourceMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private DictService dictService;
	
	@Override
	@POST
	@Path("get")
	@Consumes({ MediaType.APPLICATION_JSON })
	public MenuResource getById(Map<String, Object> map) throws Exception {
		MenuResource menuResource = mapper.getById(map);
		if(menuResource != null){
			String systemId = (String)map.get("systemId");
			String resourceId = (String)map.get("resourceId");
			Integer appType = (Integer)map.get("appType");
		    Map<String,Object> qryButtonMap = new HashMap<String,Object>();
		    qryButtonMap.put("systemId", systemId);
		    qryButtonMap.put("superId", resourceId);
		    qryButtonMap.put("appType", appType);
		    qryButtonMap.put("menuType", UAOPConstant.MenuType.BUTTON);
		    List<MenuResource> buttonMenuList = this.getChildMenuResources(qryButtonMap);
		    menuResource.setChildren(buttonMenuList);
		}
		return menuResource;
	}

	@Override
	@POST
	@Path("children")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<MenuResource> getChildMenuResources(Map<String, Object> map) throws Exception {
		Integer appType = (Integer)map.get("appType");
		if(appType == null){ //默认查询的是web端的菜单
			map.put("appType", UAOPConstant.AppType.WEB);
		}
		return mapper.getChildMenuResources(map);
	}

	@Override
	@POST
	@Path("delete")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void delete(MenuResource t) throws Exception {
		mapper.delete(t);
	}

	@Override
	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void update(MenuResource t) throws Exception {
		mapper.update(t);
	}

	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void add(MenuResource t) throws Exception {
		String resourceId = t.getResourceId();
		if(StringUtils.isEmpty(resourceId)){
			t.setResourceId(SysIDGenerator.getInstance().genId());
		}
		
		if(t.getMenuType() == null){ //没有设置，则默认是菜单
			t.setMenuType(UAOPConstant.MenuType.MENU);
		}
		
		if(t.getAppType() == null){  //没有设置，则默认是web
			t.setAppType(UAOPConstant.AppType.WEB);
		}
		
		if(t.getVisibleState() == null){//没有设置，则默认是可见的
			t.setVisibleState(UAOPConstant.VisibleState.VISIBLE);
		}
		
		mapper.add(t);
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public void addBatch(List<MenuResource> list) throws Exception {
		for(MenuResource t:list){
			String resourceId = t.getResourceId();
			if(StringUtils.isEmpty(resourceId)){
				t.setResourceId(SysIDGenerator.getInstance().genId());
			}
			
			if(t.getMenuType() == null){ //没有设置，则默认是菜单
				t.setMenuType(UAOPConstant.MenuType.MENU);
			}
			
			if(t.getAppType() == null){  //没有设置，则默认是web
				t.setAppType(UAOPConstant.AppType.WEB);
			}
			
			if(t.getVisibleState() == null){//没有设置，则默认是可见的
				t.setVisibleState(UAOPConstant.VisibleState.VISIBLE);
			}
		}
		batchCommitHelper.addBatch(MenuResourceMapper.class, list);
	}

	@Override
	@POST
	@Path("query")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<MenuResource> query(Map<String, Object> map)  throws Exception{
		Integer appType = (Integer)map.get("appType");
		if(appType == null){ //默认查询的是web端的菜单
			map.put("appType", UAOPConstant.AppType.WEB);
		}
		return mapper.query(map);
	}

	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.MenuResourceService#findMenuTreeByUserId(java.lang.String)
	 */
	@Override
	@POST
	@Path("system")
	@Consumes({ MediaType.APPLICATION_JSON })
	public String findMenuTreeByUserId(Map<String, Object> map) throws Exception{
		Integer appType = (Integer)map.get("appType");
		if(appType == null){ //默认查询的是web端的菜单
			map.put("appType", UAOPConstant.AppType.WEB);
		}
//		return this.readMenu();
    	String userId = (String)map.get("userId");
    	if(StringUtils.isEmpty(userId)){
    		userId = RequestContext.getLoginUserId();
    	}
    	String systemId = (String)map.get("systemId");
    	if(StringUtils.isEmpty(systemId)){
    		systemId = RequestContext.getSystemId();
    	}
    	
    	log.info("--findMenuTreeByUserId userId="+userId+",systemId="+systemId);
		List<MenuResource> resouces =  this.getMenuTreeByUserId(map);
		String jsonString = this.listToJson(resouces);
		log.info("--- findMenuTreeByUserId map="+map.toString()+",jsonString=\n"+jsonString);
		return jsonString;
	}
	
	/**
	 * 使用jackson来序列化菜单
	 * @param resouces
	 * @return
	 * @throws Exception
	 */
	private String listToJson(List<MenuResource> resouces) throws Exception{
		ObjectMapper mapper = new ObjectMapper(); 
		return mapper.writeValueAsString(resouces);
	}
	
	/**
	 * 使用jackson来序列化菜单
	 * @param resouces
	 * @return
	 * @throws Exception
	 */
	private String objToJson(Object obj) throws Exception{
		ObjectMapper mapper = new ObjectMapper(); 
		return mapper.writeValueAsString(obj);
	}

	private String readMenu(){
		InputStream is = null;
		BufferedReader br = null;
		try{
			URL url = this.getClass().getClassLoader().getResource("menu.json");
			is = url.openStream();
			String charSet = System.getProperty("charset");
			if(StringUtils.isEmpty(charSet)){
				charSet = "GBK";
			}
			log.info("-----charSet="+charSet);
			br = new BufferedReader(new InputStreamReader(is,charSet));
			StringBuffer sb = new StringBuffer();
			String line = null;
			Set<String> set = new HashSet<String>();
			while((line = br.readLine()) != null){
				sb.append(line).append("\n");
			}
			
			return sb.toString();
		}catch(Exception ex){
        	log.warn("can't read menu.json");
        }finally{
            CommonIOUtils.close(br, is);
        }
		
		return "";
	}
	
	
	/**
	 * 根据用户Id和SystemId获取菜单树
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("treebyuserid")
	@Produces({MediaType.APPLICATION_JSON})
    public List<MenuResource> getMenuTreeByUserId(Map<String,Object> map) throws Exception{
    	String userId = (String)map.get("userId");  
    	SysUser sysUser = this.sysUserMapper.getByUserId(userId);
    	if(sysUser == null){
    		throw new RestBusinessException(Response.Status.NOT_FOUND, "统一认证平台找不到对应用户");
    	}
    
    	map.put("account", sysUser.getAccount());
    	
    	return this.getMenuTreeByAccount(map);
    }
    
    /**
     * 根据用户account和SystemId获取菜单树
     * @param map
     * @return
     * @throws Exception
     */
	@Override
	@POST
	@Path("treebyaccount")
	@Produces({MediaType.APPLICATION_JSON})
    public List<MenuResource> getMenuTreeByAccount(Map<String,Object> map) throws Exception{
		Integer appType = (Integer)map.get("appType");
		if(appType == null){ //默认查询的是web端的菜单
			map.put("appType", UAOPConstant.AppType.WEB);
		}
		
    	String account = (String)map.get("account");
    	String systemId = (String)map.get("systemId");
    	
    	//所有菜单
    	List<MenuResource> allList = this.getAllMenusBySystemId(systemId,appType,UAOPConstant.MenuType.MENU);
		if(allList == null || allList.size() <= 0){
			return new ArrayList<MenuResource>();
		}
		
		//授权菜单
		map.put("menuType", UAOPConstant.MenuType.MENU);
		List<MenuResource> authedList =  this.mapper.getAuthedMenusByAccount(map);
		if(authedList == null || authedList.size() <= 0){
			return new ArrayList<MenuResource>();
		}
		
		System.out.println("authed="+this.listToJson(authedList));
		
		//授权Map
		Map<String,MenuResource> authedMap = new HashMap<String,MenuResource>();
		for(MenuResource resource : authedList){
			authedMap.put(resource.getResourceId(), resource);
		}
		
		//按照superId组织Map
		Map<String,List<MenuResource>> superMap = new HashMap<String,List<MenuResource>>();
		for(MenuResource resource : allList){
			String superId = resource.getSuperId();
			if(StringUtils.isEmpty(superId)){
				superId = "-1";
			}
			List<MenuResource> children = null;
		    if(superMap.containsKey(superId)){
		    	children = superMap.get(superId);
		    }else{
		    	children = new ArrayList<MenuResource>();
		    	superMap.put(superId, children);
		    }
		    children.add(resource);
		}
		
		//收集叶子菜单
		Map<String,MenuResource> leafMenuMap = new HashMap<String,MenuResource>();
		for(MenuResource resource : allList){
			String resouceId = resource.getResourceId();
			if(!superMap.containsKey(resouceId)){ //没有以此resouceId为父的子菜单，则表示是叶子菜单
				leafMenuMap.put(resouceId, resource);
			}
		}
		
		log.info("leafMenuMap="+this.objToJson(leafMenuMap));
		
		List<MenuResource> list = this.assembleAuthedMenuResourceTree(authedList, allList);
		
		return list;
    }
	
	/**
	 * 组织授权的菜单资源树
	 * @param list
	 * @param superMap
	 * @param leafMenuMap
	 * @param authedMap
	 */
	public List<MenuResource> assembleAuthedMenuResourceTree(List<MenuResource> authedList,List<MenuResource> allList){
		Map<String,MenuResource> allMenuMap = new HashMap<String,MenuResource>();
		for(MenuResource menu : allList){
			allMenuMap.put(menu.getResourceId(), menu);
		}
		
		Set<String> existSet = new HashSet<String>();
		Map<String,List<MenuResource>> superMap = new HashMap<String,List<MenuResource>>();
		for(MenuResource authedMenu : authedList){
			this.findSuper(authedMenu, allMenuMap, superMap,existSet);
		}
		
		List<MenuResource> list = superMap.get("-1");
		
		this.assembleMenuTree(list, superMap);
		
		return list;
	}
	
	/**
	 * 组织组织机构树
	 * @param list
	 * @param superMap
	 */
	public void assembleMenuTree(List<MenuResource> list,Map<String,List<MenuResource>> superMap){
		if(list == null || list.size() <= 0){
			return;
		}
		
		Collections.sort(list, new Comparator<MenuResource>() {

			@Override
			public int compare(MenuResource o1, MenuResource o2) {
				if(o1 == null || o2 == null){
					return 0;
				}
				
				int num1 = o1.getOrderNum();
				int num2 = o2.getOrderNum();
				if(num1 <= num2){
					return -1;
				}else{
					return 1;
				}
			}
			
		});
		
		for(MenuResource menu : list){
			String menuId = menu.getResourceId();
			List<MenuResource> children = superMap.get(menuId);
			menu.setChildren(children);
			this.assembleMenuTree(children, superMap);
		}
	}
	
	
	/**
	 * 寻找父
	 * @param menu
	 * @param allMenuMap
	 * @return
	 */
	public void findSuper(MenuResource menu, Map<String,MenuResource> allMenuMap,Map<String,List<MenuResource>> superMap,Set<String> existSet){
		if(menu == null){
			return;
		}
		
		String superId = menu.getSuperId();
		if(existSet.contains(menu.getResourceId())){
			return;
		}else{
			existSet.add(menu.getResourceId());
		}
		
		
		if(StringUtils.isEmpty(superId) || superId.equals("-1")){
			superId = "-1";
		}
		
		List<MenuResource> children = null;
	    if(superMap.containsKey(superId)){
	    	children = superMap.get(superId);
	    }else{
	    	children = new ArrayList<MenuResource>();
	    	superMap.put(superId, children);
	    }
	    
	    children.add(menu);
	    
	    
		MenuResource superMenu = allMenuMap.get(superId);
		this.findSuper(superMenu, allMenuMap,superMap,existSet);
	}
	
	
	/**
	 * 根据系统唯一标识获取菜单树
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("{systemId}/alltree")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MenuResource> getAllMenuResourceTree(@PathParam("systemId") String systemId) throws Exception{
		return this.getAllMenuResourceTree(systemId, UAOPConstant.AppType.WEB);
	}
	
	
	/**
	 * 移动app端:根据系统唯一标识获取菜单树
	 * @param systemId
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("app/{systemId}/alltree")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MenuResource> getAppAllMenuResourceTree(@PathParam("systemId") String systemId) throws Exception{
		return this.getAllMenuResourceTree(systemId, UAOPConstant.AppType.APP);
	}
	
	
	/**
	 * 根据systemId还有appType获取菜单树
	 * @param systemId
	 * @param appType
	 * @return
	 * @throws Exception
	 */
	public List<MenuResource> getAllMenuResourceTree(String systemId,Integer appType) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("systemId", systemId); 
		map.put("appType", appType);
		List<MenuResource> allList = this.mapper.query(map);

		if(allList == null || allList.size() <= 0){
			return new ArrayList<MenuResource>();
		}
		
		Map<String,List<MenuResource>> superMap = new HashMap<String,List<MenuResource>>();
		for(MenuResource menu : allList){
			String superMenuId = menu.getSuperId();
			if(StringUtils.isEmpty(superMenuId)){
				superMenuId = "-1";
			}
			List<MenuResource> children = null;
		    if(superMap.containsKey(superMenuId)){
		    	children = superMap.get(superMenuId);
		    }else{
		    	children = new ArrayList<MenuResource>();
		    	superMap.put(superMenuId, children);
		    }
		    children.add(menu);
		}
		
		List<MenuResource> list = superMap.get("-1"); //第一层
		
		this.assembleMenuResourceTree(list, superMap);
		
		return list;
	}
	
	
	/**
	 * 组织菜单权限点树
	 * @param list
	 * @param superMap
	 */
	public void assembleMenuResourceTree(List<MenuResource> list,Map<String,List<MenuResource>> superMap){
		if(list == null || list.size() <= 0){
			return;
		}
		
		for(MenuResource menu : list){
			String menuId = menu.getResourceId();
			List<MenuResource> children = superMap.get(menuId);
			menu.setChildren(children);
			this.assembleMenuResourceTree(children, superMap);
		}
	}
	
	
	/**
	 * 根据systemId获取某个系统下的所有菜单项
	 * @param systemId
	 * @return
	 */
	@Override
	@GET
	@Path("{systemId}/listbysystem")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MenuResource> getAllMenusBySystemId(@PathParam("systemId") String systemId) throws Exception{
		return this.getAllMenusBySystemId(systemId, UAOPConstant.AppType.WEB, null);
	}
	
	
	/**
	 * 根据systemId,appType,menuType获取系统菜单
	 * @param systemId
	 * @param appType
	 * @param menuType
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<MenuResource> getAllMenusBySystemId(String systemId,Integer appType,Integer menuType) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("systemId", systemId);
		map.put("appType", appType);
		map.put("menuType", menuType);
	
		return this.mapper.query(map);
	}
	
	
	/**
	 * 根据账号获取其拥有权限的某个系统的所有菜单
	 * @param map
	 * @return
	 */
	@Override
	@POST
	@Path("listbyauthed")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MenuResource> getAuthedMenusByAccount(Map<String, Object> map)  throws Exception{
		Integer appType = (Integer)map.get("appType");
		if(appType == null){ //默认查询的是web端的菜单
			map.put("appType", UAOPConstant.AppType.WEB);
		}
		return this.mapper.getAuthedMenusByAccount(map);
	}
	
	/* (non-Javadoc)
	 * @see com.hisign.sso.api.service.sys.MenuResourceService#getAuthedMenusByUserId(java.util.Map)
	 */
	@Override
	public List<MenuResource> getAuthedMenusByUserId(Map<String, Object> map) {
		Object userIdObj = map.get("userId");
		Assert.notNull(userIdObj, "userId必须有值");
		SysUser sysUser = this.sysUserMapper.getByUserId(userIdObj.toString());
		if (sysUser == null) {
			throw new RestBusinessException(Response.Status.NOT_FOUND, "统一认证平台找不到对应用户");
		}
		map.put("account", sysUser.getAccount());
		try {
			return getAuthedMenusByAccount(map);
		} catch (Exception e) {
			log.error("查询用户菜单异常", e);
			throw new RestBusinessException(Response.Status.NOT_FOUND, "查询用户菜单异常");
		}
	}

	/**
	 * 根据systemId获取某个系统下的所有仅菜单项，不包含按钮
	 * @param systemId
	 * @return
	 */
	@Override
	@GET
	@Path("{systemId}/onlymenulistbysystem")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MenuResource> getAllOnlyMenusBySystemId(@PathParam("systemId") String systemId) throws Exception{
		return this.getAllMenusBySystemId(systemId, UAOPConstant.AppType.WEB,UAOPConstant.MenuType.MENU);
	}
	
	/**
	 * 根据systemId获取某个系统下的所有仅菜单项，不包含按钮
	 * @param systemId
	 * @return
	 */
	@Override
	@GET
	@Path("{systemId}/onlybuttonlistbysystem")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MenuResource> getAllOnlyButtonsBySystemId(String systemId) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("systemId", systemId);
		map.put("menuType", UAOPConstant.MenuType.BUTTON);
		map.put("appType", UAOPConstant.AppType.WEB);
		return this.mapper.query(map);
	}
	
	/**
	 * 删除所有的子菜单
	 * @param map
	 * @return
	 * @throws Exception
	 */
	@Override
	@POST
	@Path("deletechildren")
	@Produces({MediaType.APPLICATION_JSON})
	public Map<String,String> deleteChildrenMenuResources(Map<String, Object> map) throws Exception{
		try{
			String systemId = (String)map.get(UAOPConstant.KEY_SYSTEMID);
			if(StringUtils.isEmpty(systemId)){
				throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
			}
			this.mapper.deleteByCondition(map);
		}catch(Exception ex){
			log.error("deleteChildren catch an exception",ex);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	public List<MenuResource> getChildrenById(MenuResource menuResource) throws Exception {
		return mapper.getChildrenById(menuResource);
	}
	
	/**
	 * 获取所有系统的菜单树
	 * 主要给一体化平台系统管理使用
	 * @return
	 * @throws Exception
	 */
	@Override
	@GET
	@Path("allsystemtree")
	@Produces({MediaType.APPLICATION_JSON})
	public List<MenuResource> getAllSystemMenuResourceTree() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("menuType", UAOPConstant.MenuType.MENU); //只查所有的菜单
		List<MenuResource> allList = this.mapper.query(map);
		if(allList == null || allList.size() <= 0){
			return new ArrayList<MenuResource>();
		}
		
		Map<String,Map<String,List<MenuResource>>> systemSuperMap = new ConcurrentHashMap<String, Map<String,List<MenuResource>>>();
		Map<String,Map<String,List<MenuResource>>> systemAppSuperMap = new ConcurrentHashMap<String, Map<String,List<MenuResource>>>();
		for(MenuResource menu : allList){
			int appType = -1;
			if(menu.getAppType() != null){
				appType = menu.getAppType().intValue();
			}

			if(appType == UAOPConstant.AppType.APP){ //手机移动端的
				this.assembleSystemSuperMap(menu,systemAppSuperMap);
			}else{ //web端的
				this.assembleSystemSuperMap(menu,systemSuperMap);
			}
		}
		
		Map<String,String> dictMap = this.dictService.getDictMapByRootKey("SYSTEM");
		
		List<MenuResource> allMenuTreeList = new ArrayList<MenuResource>();
		
		//web端菜单树组织
		Iterator<Map.Entry<String,Map<String,List<MenuResource>>>> it = systemSuperMap.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<String,Map<String,List<MenuResource>>> entry = it.next();
			String systemId = entry.getKey();
			Map<String,List<MenuResource>> superMap = entry.getValue();
			List<MenuResource> menuTreeList = this.buildMenuResourceTree(superMap);
			MenuResource systemMenu = this.createSystemMenuResource(systemId, UAOPConstant.AppType.WEB, dictMap);
			systemMenu.setChildren(menuTreeList);
			allMenuTreeList.add(systemMenu);
		}
		
		//移动app端菜单树组织
		if(systemAppSuperMap.size() > 0){
			Iterator<Map.Entry<String,Map<String,List<MenuResource>>>> appIt = systemAppSuperMap.entrySet().iterator();
			//建立一个移动app系统的根菜单
			MenuResource appSystemMenu = new MenuResource();
			appSystemMenu.setResourceId("-1");
			appSystemMenu.setResourceName("移动App");
			appSystemMenu.setAppType(UAOPConstant.AppType.APP);
			appSystemMenu.setSystemId("MOBILEAPP");
			appSystemMenu.setChildren(new ArrayList<MenuResource>());
			while(appIt.hasNext()){
				Map.Entry<String,Map<String,List<MenuResource>>> entry = appIt.next();
				String systemId = entry.getKey();
				Map<String,List<MenuResource>> appSuperMap = entry.getValue();
				List<MenuResource> menuTreeList = this.buildMenuResourceTree(appSuperMap);
				MenuResource systemMenu = this.createSystemMenuResource(systemId, UAOPConstant.AppType.APP, dictMap);
				systemMenu.setChildren(menuTreeList);
				appSystemMenu.getChildren().add(systemMenu);
			}
			
			allMenuTreeList.add(appSystemMenu);
		}

		
		return allMenuTreeList;
	}
	
	/**
	 * 根据系统编号构建一个菜单对象
	 * @param systemId 系统唯一标识
	 * @param appType  应用类型 0:web 1:移动app
	 * @param dictMap  系统字典
	 * @return 菜单对象
	 */
	private MenuResource createSystemMenuResource(String systemId,int appType,Map<String,String> dictMap){
		String systemName = this.getSystemName(systemId, dictMap);
		MenuResource systemMenu = new MenuResource();
		systemMenu.setResourceId("-1");
		systemMenu.setSystemId(systemId);
		systemMenu.setResourceName(systemName);
		systemMenu.setMenuType(UAOPConstant.MenuType.MENU);
		systemMenu.setAppType(appType);
		return systemMenu;
	}
	
	/**
	 * 获取系统名称
	 * @param systemId
	 * @param dictMap
	 * @return
	 */
	private String getSystemName(String systemId,Map<String,String> dictMap){
		String systemName = null;
		if(dictMap != null && dictMap.size() > 0){
			systemName = dictMap.get(systemId);
		}
		
		if(StringUtils.isEmpty(systemName)){ //如果字典表中没查询到
			if(systemId.equalsIgnoreCase("UAOP")){ 
				systemName = "统一认证";
			}else if(systemId.equalsIgnoreCase("OMP")){
				systemName = "统一运维";
			}else if(systemId.equalsIgnoreCase("XCKY")){
				systemName = "勘验系统";
			}else if(systemId.equalsIgnoreCase("ALIMS")){
				systemName = "检验鉴定";
			}else if(systemId.equalsIgnoreCase("YTHPT")){
				systemName = "一体化";
			}else if(systemId.equalsIgnoreCase("WZGL")){
				systemName = "物证保管";
			}else if(systemId.equalsIgnoreCase("MPP")){
				systemName = "移动警务";
			}
		}
		
		if(StringUtils.isEmpty(systemName)){ 
			systemName = "未知";
		}
		
		return systemName;
	}
	
	/**
	 * 组装单棵菜单树
	 * @param superMap
	 * @return
	 */
    private List<MenuResource> buildMenuResourceTree(Map<String,List<MenuResource>> superMap){
    	List<MenuResource> list = superMap.get("-1"); //第一层
		this.assembleMenuResourceTree(list, superMap);
		return list;
    }
	
	
	/**
	 * 组装systemSuperMap
	 * @param menu
	 * @param systemSuperMap
	 */
	private void assembleSystemSuperMap(MenuResource menu,Map<String,Map<String,List<MenuResource>>> systemSuperMap){
		String systemId = menu.getSystemId();
		Map<String,List<MenuResource>> superMap =  null;
		if(systemSuperMap.containsKey(systemId)){ 
			superMap = systemSuperMap.get(systemId);
		}else{
			superMap = new ConcurrentHashMap<String,List<MenuResource>>();
			systemSuperMap.put(systemId, superMap);
		}
		
		String superMenuId = menu.getSuperId();
		if(StringUtils.isEmpty(superMenuId)){
			superMenuId = "-1";
		}
		List<MenuResource> children = null;
	    if(superMap.containsKey(superMenuId)){
	    	children = superMap.get(superMenuId);
	    }else{
	    	children = new ArrayList<MenuResource>();
	    	superMap.put(superMenuId, children);
	    }
	    children.add(menu);
	}
	
}
