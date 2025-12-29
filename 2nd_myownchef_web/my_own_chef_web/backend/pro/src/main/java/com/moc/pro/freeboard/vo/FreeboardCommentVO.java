package com.moc.pro.freeboard.vo;

import java.sql.Timestamp;

/**
 * 자유게시판 댓글 VO
 * TB_FREEBOARD_COMMENT 테이블 매핑
 */
public class FreeboardCommentVO {
    
    private int freeboardCommentId;         // 댓글 ID (PK)
    private int freeboardId;                // 게시글 ID (FK)
    private String userId;                  // 작성자 ID
    private String freeboardCommentContent; // 댓글 내용
    private Timestamp createdAt;            // 작성일시
    private String createdBy;               // 작성자
    private Timestamp updatedAt;            // 수정일시
    private String updatedBy;               // 수정자
    
    // 추가 필드 (조인용)
    private String userNickname;            // 작성자 닉네임 (TB_USER 조인)
    
    // 생성자
    public FreeboardCommentVO() {}
    
    // Getter/Setter
    public int getFreeboardCommentId() {
        return freeboardCommentId;
    }
    
    public void setFreeboardCommentId(int freeboardCommentId) {
        this.freeboardCommentId = freeboardCommentId;
    }
    
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
    
    public String getFreeboardCommentContent() {
        return freeboardCommentContent;
    }
    
    public void setFreeboardCommentContent(String freeboardCommentContent) {
        this.freeboardCommentContent = freeboardCommentContent;
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
