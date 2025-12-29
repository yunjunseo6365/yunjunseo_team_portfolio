package com.moc.pro.savedposts.vo;

import java.sql.Timestamp;

/**
 * 저장된 게시글 VO
 * TB_SAVED_POSTS 테이블과 매핑
 */
public class SavedPostsVO {
    
    private int savedPostId;
    private String userId;
    private String boardType;   // recipe, freeboard, sharetool, conv_review, conv_comb
    private int postId;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    
    // 기본 생성자
    public SavedPostsVO() {
        // MyBatis를 위한 기본 생성자
    }
    
    // Getter/Setter
    public int getSavedPostId() {
        return savedPostId;
    }
    
    public void setSavedPostId(int savedPostId) {
        this.savedPostId = savedPostId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getBoardType() {
        return boardType;
    }
    
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    
    public int getPostId() {
        return postId;
    }
    
    public void setPostId(int postId) {
        this.postId = postId;
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
}
