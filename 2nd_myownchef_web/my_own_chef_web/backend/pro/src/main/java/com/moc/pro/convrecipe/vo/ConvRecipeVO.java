package com.moc.pro.convrecipe.vo;

import java.sql.Timestamp;

public class ConvRecipeVO {
    private int convRecipeId;
    private String userId;
    private String convRecipeTitle;
    private String convRecipeMainProduct;
    private String convRecipeCategory;
    private String convRecipeTip;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    
    // 조인용 필드 (DB에는 없음)
    private String userNickname;
    
    // 목록 표시용 (첫 번째 이미지 URL)
    private String firstImageUrl;
    
    // Getters and Setters
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
    
    public String getConvRecipeTitle() {
        return convRecipeTitle;
    }
    
    public void setConvRecipeTitle(String convRecipeTitle) {
        this.convRecipeTitle = convRecipeTitle;
    }
    
    public String getConvRecipeMainProduct() {
        return convRecipeMainProduct;
    }
    
    public void setConvRecipeMainProduct(String convRecipeMainProduct) {
        this.convRecipeMainProduct = convRecipeMainProduct;
    }
    
    public String getConvRecipeCategory() {
        return convRecipeCategory;
    }
    
    public void setConvRecipeCategory(String convRecipeCategory) {
        this.convRecipeCategory = convRecipeCategory;
    }
    
    public String getConvRecipeTip() {
        return convRecipeTip;
    }
    
    public void setConvRecipeTip(String convRecipeTip) {
        this.convRecipeTip = convRecipeTip;
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
    
    public String getFirstImageUrl() {
        return firstImageUrl;
    }
    
    public void setFirstImageUrl(String firstImageUrl) {
        this.firstImageUrl = firstImageUrl;
    }
}
