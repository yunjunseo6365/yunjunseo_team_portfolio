package com.moc.pro.convrecipe.vo;

import java.sql.Timestamp;

public class ConvRecipeImageVO {
    private int convRecipeImageId;
    private int convRecipeId;
    private String convRecipeImageUrl;
    private String convRecipeImagePath;
    private int convRecipeImageIndex;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    
    // Getters and Setters
    public int getConvRecipeImageId() {
        return convRecipeImageId;
    }
    
    public void setConvRecipeImageId(int convRecipeImageId) {
        this.convRecipeImageId = convRecipeImageId;
    }
    
    public int getConvRecipeId() {
        return convRecipeId;
    }
    
    public void setConvRecipeId(int convRecipeId) {
        this.convRecipeId = convRecipeId;
    }
    
    public String getConvRecipeImageUrl() {
        return convRecipeImageUrl;
    }
    
    public void setConvRecipeImageUrl(String convRecipeImageUrl) {
        this.convRecipeImageUrl = convRecipeImageUrl;
    }
    
    public String getConvRecipeImagePath() {
        return convRecipeImagePath;
    }
    
    public void setConvRecipeImagePath(String convRecipeImagePath) {
        this.convRecipeImagePath = convRecipeImagePath;
    }
    
    public int getConvRecipeImageIndex() {
        return convRecipeImageIndex;
    }
    
    public void setConvRecipeImageIndex(int convRecipeImageIndex) {
        this.convRecipeImageIndex = convRecipeImageIndex;
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
