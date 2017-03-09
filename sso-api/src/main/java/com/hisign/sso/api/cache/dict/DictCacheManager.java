package com.hisign.sso.api.cache.dict;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hisign.sdk.msg.Message;
import com.hisign.sdk.msg.MessageClient;
import com.hisign.sdk.msg.MessageHandler;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.SysDict;
import com.hisign.sso.api.service.sys.DictService;
import com.hisign.sso.api.util.StringUtils;

/**
 * @Title:
 *   数据字典缓存加载
 * @description:
 *   
 * @author lnj 
 */
public class DictCacheManager {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DictService dictService;
	
	public void init(){
		load(); //加载缓存
		receiveMessage(); //启动数据变更消息监听
	}
	
	public synchronized void load(){
		try{
		    log.info("begin to load dict cache from sso ...");
			List<SysDict> allList = dictService.getAll();
			if(allList == null || allList.size() <= 0){
				log.error("can't get any dict data from sso");
				return;
			}
			
			log.info("get "+allList.size()+" dict from sso");
			Map<String,Map<String,SysDict>> dictMap = DictCache.getInstance().getDictMap();
			dictMap.clear(); //清空数据
			
			for(SysDict dict : allList){
				String systemId = dict.getSysCode();
				if(StringUtils.isEmpty(systemId)){
					systemId = UAOPConstant.UNKOWN_SYSTEMID;
				}
				
				String rootKey = dict.getRootKey();
				String dictKey = dict.getDictKey();
				
				if(StringUtils.isEmpty(rootKey) || StringUtils.isEmpty(dictKey)){
					log.warn("rootKey="+rootKey+",dictKey="+dictKey+" is empty!");
					continue;
				}
				
				//key均转换为大写
				systemId = systemId.toUpperCase();
				rootKey = rootKey.toUpperCase();
				dictKey = dictKey.toUpperCase();
				
				String key = DictCache.getInstance().getKey(rootKey, dictKey);
				
				Map<String,SysDict> map = null;
				if(dictMap.containsKey(systemId)){
					map = dictMap.get(systemId);
				}else{
					map = new ConcurrentHashMap<String,SysDict>();
					dictMap.put(systemId, map);
				}
				map.put(key, dict);
			}
			
			log.info("load dict cache from sso completely!");
		}catch(Throwable tr){
			log.error("load dict from sso catch an exception",tr);
		}
	}
	
	/**
	 * 重新加载数据
	 */
	public void reload(){
		log.info(">>>begin to reload dict cache ...");
		load();
		log.info("<<<reload dict cache completely!");
	}
	
	/**
	 * 启动数据监听
	 */
	private void receiveMessage(){
		log.info("begin to start receiveMessage");
		try{
			MessageClient.getInstance().addMessageHandler(UAOPConstant.MsgTopic.UAOP_SSO, new MessageHandler(){
				@Override
				public void onMessage(Message msg) {
					if(msg.getType() == UAOPConstant.MsgType.DICT_ADD
							|| msg.getType() == UAOPConstant.MsgType.DICT_BATCHADD
							|| msg.getType() == UAOPConstant.MsgType.DICT_UPDATE
							|| msg.getType() == UAOPConstant.MsgType.DICT_DELETE
							|| msg.getType() == UAOPConstant.MsgType.DICT_RELOADALL){ //字典数据变更
						reload(); //重新加载数据
					}
				}
			});
			log.info("start receiveMessage completely!");
		}catch(Throwable tr){
			log.error("start receiveMessage catch an exception"+tr.getMessage());
		}
	}

}
