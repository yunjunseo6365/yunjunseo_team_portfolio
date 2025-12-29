package com.moc.pro.notice.vo;

import java.sql.Timestamp;

/**
 * 공지사항 VO
 * TB_NOTICE 테이블 매핑
 */
public class NoticeVO {
    
    private int noticeId;            // 공지사항 ID (PK)
    private String userId;           // 작성자 ID
    private String noticeTitle;      // 제목
    private String noticeContent;    // 내용
    private Timestamp createdAt;     // 작성일시
    private String createdBy;        // 작성자
    private Timestamp updatedAt;     // 수정일시
    private String updatedBy;        // 수정자
    
    // 추가 필드 (조인용)
    private String userNickname;     // 작성자 닉네임 (TB_USER 조인)
    
    // 생성자
    public NoticeVO() {}
    
    // Getter/Setter
    public int getNoticeId() {
        return noticeId;
    }
    
    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getNoticeTitle() {
        return noticeTitle;
    }
    
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
    
    public String getNoticeContent() {
        return noticeContent;
    }
    
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
    
    public String getUserNickname() {
        return userNickname;
    }
    
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
