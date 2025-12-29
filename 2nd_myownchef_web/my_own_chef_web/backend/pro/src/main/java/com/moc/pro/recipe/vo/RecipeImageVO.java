package com.moc.pro.recipe.vo;

import java.sql.Timestamp;

/**
 * 레시피 이미지 VO
 * TB_RECIPE_IMAGE 테이블 매핑
 */
public class RecipeImageVO {
    private int recipeImageId;              // 이미지 ID (PK)
    private int recipeId;                   // 레시피 ID (FK)
    private String recipeImageUrl;          // 이미지 URL
    private String recipeImagePath;         // 이미지 경로
    private int recipeImageIndex;           // 이미지 순서
    private Timestamp createdAt;            // 작성일시
    private String createdBy;               // 작성자
    private Timestamp updatedAt;            // 수정일시
    private String updatedBy;               // 수정자
    
    // Getter/Setter
    public int getRecipeImageId() {
        return recipeImageId;
    }
    
    public void setRecipeImageId(int recipeImageId) {
        this.recipeImageId = recipeImageId;
    }
    
    public int getRecipeId() {
        return recipeId;
    }
    
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }
    
    public void setRecipeImageUrl(String recipeImageUrl) {
        this.recipeImageUrl = recipeImageUrl;
    }
    
    public String getRecipeImagePath() {
        return recipeImagePath;
    }
    
    public void setRecipeImagePath(String recipeImagePath) {
        this.recipeImagePath = recipeImagePath;
    }
    
    public int getRecipeImageIndex() {
        return recipeImageIndex;
    }
    
    public void setRecipeImageIndex(int recipeImageIndex) {
        this.recipeImageIndex = recipeImageIndex;
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
