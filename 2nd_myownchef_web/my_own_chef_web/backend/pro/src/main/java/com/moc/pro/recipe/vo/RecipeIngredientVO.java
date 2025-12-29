package com.moc.pro.recipe.vo;

import java.sql.Timestamp;

/**
 * 레시피 상세 재료 VO
 * TB_RECIPE_DETAIL_INGREDIENT 테이블 매핑
 */
public class RecipeIngredientVO {
    private int recipeDetailIngredientId;       // 재료 ID (PK)
    private int recipeId;                       // 레시피 ID (FK)
    private String recipeDetailIngredientContent; // 재료 내용
    private int recipeDetailIngredientIndex;    // 재료 순서
    private Timestamp createdAt;                // 작성일시
    private String createdBy;                   // 작성자
    private Timestamp updatedAt;                // 수정일시
    private String updatedBy;                   // 수정자
    
    // Getter/Setter
    public int getRecipeDetailIngredientId() {
        return recipeDetailIngredientId;
    }
    
    public void setRecipeDetailIngredientId(int recipeDetailIngredientId) {
        this.recipeDetailIngredientId = recipeDetailIngredientId;
    }
    
    public int getRecipeId() {
        return recipeId;
    }
    
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    public String getRecipeDetailIngredientContent() {
        return recipeDetailIngredientContent;
    }
    
    public void setRecipeDetailIngredientContent(String recipeDetailIngredientContent) {
        this.recipeDetailIngredientContent = recipeDetailIngredientContent;
    }
    
    public int getRecipeDetailIngredientIndex() {
        return recipeDetailIngredientIndex;
    }
    
    public void setRecipeDetailIngredientIndex(int recipeDetailIngredientIndex) {
        this.recipeDetailIngredientIndex = recipeDetailIngredientIndex;
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
