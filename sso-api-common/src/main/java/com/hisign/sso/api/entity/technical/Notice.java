package com.hisign.sso.api.entity.technical;


import com.hisign.sso.api.entity.SysBaseEntity;

public class Notice extends SysBaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 标题
	 */
    private String title;

    /**
	 * 发送状态:0:未发送 1:已发送
	 */
    private Integer sendStatus;

    /**
	 * 发送人
	 */
    private String sender;
    
    /**
	 * 发送时间
	 */
    private Long sendTime;
    
    /**
	 * 发送单位
	 */
    private String sendUnit;
    
    /**
	 * 发送类型:0:发送给单位 1:发送给个人
	 */
    private Integer sendType;
    
    /**
	 * 接受单位Id,多个单位之间以逗号分隔
	 */
    private String recieveUnit;
    
    /**
	 * 接受人userId,多个之间以逗号分隔
	 */
    private String recieveUser;
    
    /**
	 * 反馈状态:0:未反馈 1:已反馈
	 */
    private Integer feedbackStatus;
    
    /**
	 * 内容
	 */
    private byte[] content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Integer sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendUnit() {
		return sendUnit;
	}

	public void setSendUnit(String sendUnit) {
		this.sendUnit = sendUnit;
	}

	public Integer getSendType() {
		return sendType;
	}

	public void setSendType(Integer sendType) {
		this.sendType = sendType;
	}

	public String getRecieveUnit() {
		return recieveUnit;
	}

	public void setRecieveUnit(String recieveUnit) {
		this.recieveUnit = recieveUnit;
	}

	public String getRecieveUser() {
		return recieveUser;
	}

	public void setRecieveUser(String recieveUser) {
		this.recieveUser = recieveUser;
	}

	public Integer getFeedbackStatus() {
		return feedbackStatus;
	}

	public void setFeedbackStatus(Integer feedbackStatus) {
		this.feedbackStatus = feedbackStatus;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}