package com.hisign.sso.service.impl.msg;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hisign.sdk.msg.Message;
import com.hisign.sdk.msg.MessageClient;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.Organise;
import com.hisign.sso.api.entity.sys.Role;
import com.hisign.sso.api.entity.sys.SysDict;
import com.hisign.sso.api.entity.sys.SysUser;
import com.hisign.sso.api.entity.sys.SysUserRole;
import com.hisign.sso.api.entity.sys.UserInfo;
import com.hisign.sso.api.entity.sys.UserOrganization;
import com.hisign.sso.api.rest.entity.sys.User;

/**
 * @Title:
 *   消息处理类
 * @description:
 * 
 * @author lnj 
 */
public class MessageProcessor {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private boolean enable = false; //开启消息发送
	
	private static MessageProcessor instance =  null;
	
	//消息队列
	private LinkedBlockingQueue<Message> msgQueue = new LinkedBlockingQueue<Message>(10000);
	
	//消息发送线程
	private MessageSendThread sendThread = null;
	
	
	public synchronized static MessageProcessor getInstance(){
		if(instance == null){
			instance = new MessageProcessor();
		}
		return instance;
	}
	
	private MessageProcessor(){
		init();
	}
	
	private void init(){
		startSendThread();
	}
	
	/**
	 * 启动消息发送线程
	 */
	private void startSendThread(){
		sendThread = new MessageSendThread();
		sendThread.start();
		log.info("start message send thread completely!");
	}
	
	/**
	 * 发送消息
	 * @param msg
	 */
	private boolean executeSendMessage(Message msg){
		if(enable == false){  //如果没有开启消息发送，则直接返回
			return true;
		}
		
		if(msg == null){
			log.error("msg is null");
			return false;
		}
		
		try {
			MessageClient.getInstance().send(msg);
			return true;
		} catch (Exception ex) {
			log.error("executeSendMessage catch an exception",ex);
		}
		
		return false;
	}
	
	
	/**
	 * 消息发送线程
	 * @author Administrator
	 *
	 */
	private class MessageSendThread extends Thread{
		
		private boolean cancel = false;
		
		@Override
		public void run() {
			while(!cancel){
				try {
					Message message = msgQueue.take();
					executeSendMessage(message);
				} catch (Throwable tr) {
					log.error("MessageSendThread catch an exception",tr);
				}
			}
		}

		public boolean isCancel() {
			return cancel;
		}

		public void setCancel(boolean cancel) {
			this.cancel = cancel;
		}
	}
	
	/**
	 * 发送消息
	 * @param msg
	 */
	public void sendMessage(Message msg){
		if(msg == null){
			return;
		}
		
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送添加用户消息
	 * @param user
	 */
	public void sendAddUserMsg(User user){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_ADD,user);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送更新用户消息
	 * @param user
	 */
	public void sendUpdateUserMsg(User user){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_UPDATE,user);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送删除用户消息
	 * @param user
	 */
	public void sendDeleteUserMsg(List<String> userIds){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_DELETE,userIds);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送用户密码变更消息
	 * @param user
	 */
	public void sendUserPassModifyMsg(String account){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_PASS_MODIFY,account);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送重载所有用户消息
	 */
	public void sendReloadAllUsersMsg(){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_RELOADALL,"reloadAllUsers");
		this.executeSendMessage(msg);
		log.info("sendReloadAllUsersMsg completely!");
	}
	
	/**
	 * 发送添加用户账号消息
	 * @param sysUser
	 */
	public void sendAddUserAccountMsg(SysUser sysUser){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_ACCOUNT_ADD,sysUser);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送批量添加用户账号消息
	 * @param sysUserList
	 */
	public void sendBatchAddUserAccountMsg(List<SysUser> sysUserList){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_ACCOUNT_ADD,sysUserList);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送更新用户账号消息
	 * @param sysUser
	 */
	public void sendUpdateUserAccountMsg(SysUser sysUser){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_ACCOUNT_MODIFY,sysUser);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送删除用户账号消息
	 * @param accountList
	 */
	public void sendDeleteUserAccountMsg(List<String> accountList){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_ACCOUNT_DELETE,accountList);
		this.msgQueue.offer(msg);
	}
	
	
	/**
	 * 发送添加用户基本消息
	 * @param userInfo
	 */
	public void sendAddUserBaseInfoMsg(UserInfo userInfo){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_BASEINFO_ADD,userInfo);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送更新用户基本消息
	 * @param userInfo
	 */
	public void sendUpdateUserBaseInfoMsg(UserInfo userInfo){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_BASEINFO_MODIFY,userInfo);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送删除用户基本消息
	 * @param userIdList
	 */
	public void sendDeleteUserBaseInfoMsg(List<String> userIdList){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_BASEINFO_DELETE,userIdList);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送批量添加用户基本消息
	 * @param userInfo
	 */
	public void sendBatchAddUserBaseInfoMsg(List<UserInfo> userInfoList){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_BASEINFO_ADD,userInfoList);
		this.msgQueue.offer(msg);
	}
	
	
	/**
	 * 发送用户与组织机构关系变更消息
	 * @param user
	 */
	public void sendUserOrganiseRelUpdateMsg(List<UserOrganization> userOrgRels){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_ORGANISE_REL,userOrgRels);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送用户与组织机构关系变更消息
	 * @param user
	 */
	public void sendUserRoleRelUpdateMsg(List<String> accountList){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.USER_ROLE_REL,accountList);
		this.msgQueue.offer(msg);
	}
	
	
	/**
	 * 发送添加用户消息
	 * @param user
	 */
	public void sendAddOrganiseMsg(Organise org){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ORGANISE_ADD,org);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送更新用户消息
	 * @param user
	 */
	public void sendUpdateOrganiseMsg(Organise org){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ORGANISE_UPDATE,org);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送删除组织机构消息
	 * @param user
	 */
	public void sendDeleteOrganiseMsg(List<String> orgIds){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ORGANISE_DELETE,orgIds);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送重载所有组织机构消息
	 */
	public void sendReloadAllOrganisesMsg(){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ORGANISE_RELOADALL,"reloadAllOrganises");
		this.executeSendMessage(msg);
		log.info("sendReloadAllOrganisesMsg completely!");
	}
	
	/**
	 * 发送添加用户消息
	 * @param user
	 */
	public void sendAddRoleMsg(Role role){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ROLE_ADD,role);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送更新用户消息
	 * @param user
	 */
	public void sendUpdateRoleMsg(Role role){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ROLE_UPDATE,role);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送删除用户消息
	 * @param user
	 */
	public void sendDeleteRoleMsg(List<String> roleIds){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ROLE_DELETE,roleIds);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送重载所有角色消息
	 */
	public void sendReloadAllRolesMsg(){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.ROLE_RELOADALL,"reloadAllRoles");
		this.executeSendMessage(msg);
		log.info("sendReloadAllRolesMsg completely!");
	}

	/**
	 * 发送添加字典消息
	 * @param user
	 */
	public void sendAddDictMsg(SysDict dict){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.DICT_ADD,dict);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送批量添加字典消息
	 * @param user
	 */
	public void sendBatchAddDictMsg(List<SysDict> dictList){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.DICT_BATCHADD,dictList);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送更新字典消息
	 * @param user
	 */
	public void sendUpdateDictMsg(SysDict dict){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.DICT_UPDATE,dict);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送删除字典消息
	 * @param user
	 */
	public void sendDeleteDictMsg(List<SysDict> dictList){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.DICT_DELETE,dictList);
		this.msgQueue.offer(msg);
	}
	
	/**
	 * 发送重载所有字典项消息
	 */
	public void sendReloadAllDictsMsg(){
		Message msg = new Message(UAOPConstant.MsgTopic.UAOP_SSO,UAOPConstant.MsgType.DICT_RELOADALL,"reloadAllDicts");
		this.executeSendMessage(msg);
		log.info("sendReloadAllDictsMsg completely!");
	}
    
}
