package com.moc.pro.convrecipe.vo;

import java.sql.Timestamp;

public class ConvRecipeProductVO {
    private int convRecipeProductId;
    private int convRecipeId;
    private String convRecipeProductContent;
    private int convRecipeProductIndex;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    
    // Getters and Setters
    public int getConvRecipeProductId() {
        return convRecipeProductId;
    }
    
    public void setConvRecipeProductId(int convRecipeProductId) {
        this.convRecipeProductId = convRecipeProductId;
    }
    
    public int getConvRecipeId() {
        return convRecipeId;
    }
    
    public void setConvRecipeId(int convRecipeId) {
        this.convRecipeId = convRecipeId;
    }
    
    public String getConvRecipeProductContent() {
        return convRecipeProductContent;
    }
    
    public void setConvRecipeProductContent(String convRecipeProductContent) {
        this.convRecipeProductContent = convRecipeProductContent;
    }
    
    public int getConvRecipeProductIndex() {
        return convRecipeProductIndex;
    }
    
    public void setConvRecipeProductIndex(int convRecipeProductIndex) {
        this.convRecipeProductIndex = convRecipeProductIndex;
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
