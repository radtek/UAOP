package com.hisign.sso.service.impl.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sso.api.constant.UAOPConstant;

/**
 * @Title:
 *   数据同步消息发送app
 * @description:
 * 
 * @author lnj 
 */
public class DataSyncMessageSendApp {
	
	private static Logger log = LoggerFactory.getLogger(DataSyncMessageSendApp.class);
    
    public static String usage = "使用说明:\n"
            + "1.发送字典重载消息:./dataSyncMsgSend.sh(bat) -d -a\n"
            + "2.发送用户重载消息:./dataSyncMsgSend.sh(bat) -u -a\n"
            + "3.发送角色重载消息:./dataSyncMsgSend.sh(bat) -r -a\n"
            + "4.发送组织机构重载消息:./dataSyncMsgSend.sh(bat) -o -a\n";
            
    /**
     * 打印dataSyncMsgSend.sh(bat)脚本调用参数说明
     */
    public static void usage() {
        System.out.println(usage);
    }
    
    public static void main(String[] args) {
    	if(args == null || args.length <= 0){
            usage();
            return;
        }
        
        String appName = "DataSyncMessageSendApp";
        System.setProperty("SYSTEMID", appName);
        
        if(args.length == 2){
        	SysConfigLoader.getInstance().loadSysConfig(UAOPConstant.SYSTEMID);
            if(args[0].equalsIgnoreCase("-d") && args[1].equalsIgnoreCase("-a")){
            	MessageProcessor.getInstance().sendReloadAllDictsMsg(); //发送重载所有字典项消息
            }else if(args[0].equalsIgnoreCase("-u") && args[1].equalsIgnoreCase("-a")){
            	MessageProcessor.getInstance().sendReloadAllUsersMsg(); //发送重载所有字典项消息
            }else if(args[0].equalsIgnoreCase("-r") && args[1].equalsIgnoreCase("-a")){
            	MessageProcessor.getInstance().sendReloadAllRolesMsg(); //发送重载所有字典项消息
            }else if(args[0].equalsIgnoreCase("-o") && args[1].equalsIgnoreCase("-a")){
            	MessageProcessor.getInstance().sendReloadAllOrganisesMsg(); //发送重载所有组织机构消息
            }
        }else{
            usage();
        }
        
        System.exit(0);
    }
}
