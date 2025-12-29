package com.moc.pro.recipe.vo;

import java.sql.Timestamp;

/**
 * 레시피 순서 VO
 * TB_RECIPE_ORDER 테이블 매핑
 */
public class RecipeOrderVO {
    private int recipeOrderId;              // 순서 ID (PK)
    private int recipeId;                   // 레시피 ID (FK)
    private String recipeOrderContent;      // 순서 내용
    private int recipeOrderIndex;           // 순서 번호
    private Timestamp createdAt;            // 작성일시
    private String createdBy;               // 작성자
    private Timestamp updatedAt;            // 수정일시
    private String updatedBy;               // 수정자
    
    // Getter/Setter
    public int getRecipeOrderId() {
        return recipeOrderId;
    }
    
    public void setRecipeOrderId(int recipeOrderId) {
        this.recipeOrderId = recipeOrderId;
    }
    
    public int getRecipeId() {
        return recipeId;
    }
    
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    public String getRecipeOrderContent() {
        return recipeOrderContent;
    }
    
    public void setRecipeOrderContent(String recipeOrderContent) {
        this.recipeOrderContent = recipeOrderContent;
    }
    
    public int getRecipeOrderIndex() {
        return recipeOrderIndex;
    }
    
    public void setRecipeOrderIndex(int recipeOrderIndex) {
        this.recipeOrderIndex = recipeOrderIndex;
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
