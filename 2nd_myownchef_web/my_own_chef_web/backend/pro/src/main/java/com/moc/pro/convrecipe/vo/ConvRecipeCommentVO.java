package com.moc.pro.convrecipe.vo;

import java.sql.Timestamp;

public class ConvRecipeCommentVO {
    private int convRecipeCommentId;
    private int convRecipeId;
    private String userId;
    private String convRecipeCommentContent;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    
    // 조인용 필드 (DB에는 없음)
    private String userNickname;
    
    // Getters and Setters
    public int getConvRecipeCommentId() {
        return convRecipeCommentId;
    }
    
    public void setConvRecipeCommentId(int convRecipeCommentId) {
        this.convRecipeCommentId = convRecipeCommentId;
    }
    
    public int getConvRecipeId() {
        return convRecipeId;
    }
    
    public void setConvRecipeId(int convRecipeId) {
        this.convRecipeId = convRecipeId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getConvRecipeCommentContent() {
        return convRecipeCommentContent;
    }
    
    public void setConvRecipeCommentContent(String convRecipeCommentContent) {
        this.convRecipeCommentContent = convRecipeCommentContent;
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
