package com.moc.pro.convrecipe.vo;

import java.sql.Timestamp;

public class ConvRecipeOrderVO {
    private int convRecipeOrderId;
    private int convRecipeId;
    private String convRecipeOrderContent;
    private int convRecipeOrderIndex;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    
    // Getters and Setters
    public int getConvRecipeOrderId() {
        return convRecipeOrderId;
    }
    
    public void setConvRecipeOrderId(int convRecipeOrderId) {
        this.convRecipeOrderId = convRecipeOrderId;
    }
    
    public int getConvRecipeId() {
        return convRecipeId;
    }
    
    public void setConvRecipeId(int convRecipeId) {
        this.convRecipeId = convRecipeId;
    }
    
    public String getConvRecipeOrderContent() {
        return convRecipeOrderContent;
    }
    
    public void setConvRecipeOrderContent(String convRecipeOrderContent) {
        this.convRecipeOrderContent = convRecipeOrderContent;
    }
    
    public int getConvRecipeOrderIndex() {
        return convRecipeOrderIndex;
    }
    
    public void setConvRecipeOrderIndex(int convRecipeOrderIndex) {
        this.convRecipeOrderIndex = convRecipeOrderIndex;
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
