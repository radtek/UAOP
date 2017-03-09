package com.hisign.sso.api.entity.technical;


public class SmsNotify implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
    private String id;

    private String smsContent;

    private String recieveUser;

    private Integer status;

    private String createUser;

    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent == null ? null : smsContent.trim();
    }

    public String getRecieveUser() {
        return recieveUser;
    }

    public void setRecieveUser(String recieveUser) {
        this.recieveUser = recieveUser == null ? null : recieveUser.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}