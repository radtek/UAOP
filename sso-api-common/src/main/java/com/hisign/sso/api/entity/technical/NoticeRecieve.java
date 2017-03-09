package com.hisign.sso.api.entity.technical;

public class NoticeRecieve implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
    private String id;

    private String noticeId;

    private Integer feedbackType;

    private String feedbackContent;

    private String attachPath;

    private String acceptor;

    private String acceptUnit;

    private Long acceptTime;

    private String feedbacker;

    private String feedbackUnit;

    private Long feedbackTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId == null ? null : noticeId.trim();
    }

    public Integer getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(Integer feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent == null ? null : feedbackContent.trim();
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath == null ? null : attachPath.trim();
    }

    public String getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(String acceptor) {
        this.acceptor = acceptor == null ? null : acceptor.trim();
    }

    public String getAcceptUnit() {
        return acceptUnit;
    }

    public void setAcceptUnit(String acceptUnit) {
        this.acceptUnit = acceptUnit == null ? null : acceptUnit.trim();
    }

    public Long getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Long acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getFeedbacker() {
        return feedbacker;
    }

    public void setFeedbacker(String feedbacker) {
        this.feedbacker = feedbacker == null ? null : feedbacker.trim();
    }

    public String getFeedbackUnit() {
        return feedbackUnit;
    }

    public void setFeedbackUnit(String feedbackUnit) {
        this.feedbackUnit = feedbackUnit == null ? null : feedbackUnit.trim();
    }

    public Long getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(Long feedbackTime) {
        this.feedbackTime = feedbackTime;
    }
}