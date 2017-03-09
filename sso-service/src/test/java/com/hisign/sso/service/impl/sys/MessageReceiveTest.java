package com.hisign.sso.service.impl.sys;

import com.hisign.sdk.config.SysConfigLoader;
import com.hisign.sdk.msg.Message;
import com.hisign.sdk.msg.MessageClient;
import com.hisign.sdk.msg.MessageHandler;
import com.hisign.sso.api.constant.UAOPConstant;

/**
 * @Title:
 *
 * @description:
 * 
 * @author lnj 
 */
public class MessageReceiveTest {
	
	public void receiveMsg(){
		try{
			MessageClient.getInstance().addMessageHandler("UAOP_SSO", new MessageHandler(){
				@Override
				public void onMessage(Message msg) {
					long msgType = msg.getType();
					Object obj = msg.getUserObject();
					System.out.println("receive msg "+msgType+" userObject="+obj.toString());
				}
			});
			System.out.println("start receiveMsg completey!");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception{
		SysConfigLoader.getInstance().loadSysConfig(UAOPConstant.SYSTEMID);
		MessageReceiveTest test = new MessageReceiveTest();
		test.receiveMsg();
		System.in.read(); //等待，防止直接进程退出
	}

}
