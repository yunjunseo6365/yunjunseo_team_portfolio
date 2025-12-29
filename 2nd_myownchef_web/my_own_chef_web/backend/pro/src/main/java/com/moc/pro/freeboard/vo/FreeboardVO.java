package com.moc.pro.freeboard.vo;

import java.sql.Timestamp;

/**
 * 자유게시판 VO
 * TB_FREEBOARD 테이블 매핑
 */
public class FreeboardVO {
    
    private int freeboardId;           // 게시글 ID (PK)
    private String userId;              // 작성자 ID
    private String freeboardTitle;      // 제목
    private String freeboardContent;    // 내용
    private Timestamp createdAt;        // 작성일시
    private String createdBy;           // 작성자
    private Timestamp updatedAt;        // 수정일시
    private String updatedBy;           // 수정자
    
    // 추가 필드 (조인용)
    private String userNickname;        // 작성자 닉네임 (TB_USER 조인)
    
    // 생성자
    public FreeboardVO() {}
    
    // Getter/Setter
    public int getFreeboardId() {
        return freeboardId;
    }
    
    public void setFreeboardId(int freeboardId) {
        this.freeboardId = freeboardId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getFreeboardTitle() {
        return freeboardTitle;
    }
    
    public void setFreeboardTitle(String freeboardTitle) {
        this.freeboardTitle = freeboardTitle;
    }
    
    public String getFreeboardContent() {
        return freeboardContent;
    }
    
    public void setFreeboardContent(String freeboardContent) {
        this.freeboardContent = freeboardContent;
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
