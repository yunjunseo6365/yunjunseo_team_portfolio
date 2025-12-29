package com.moc.pro.recipe.vo;

import java.sql.Timestamp;

/**
 * 레시피 VO
 * TB_RECIPE 테이블 매핑
 */
public class RecipeVO {
    private int recipeId;                   // 레시피 ID (PK)
    private String userId;                  // 작성자 ID
    private String recipeTitle;             // 레시피 제목
    private String recipeMainIngredient;    // 주재료
    private String recipeContent;           // 팁 & 소감 (CLOB)
    private Timestamp createdAt;            // 작성일시
    private String createdBy;               // 작성자
    private Timestamp updatedAt;            // 수정일시
    private String updatedBy;               // 수정자
    
    // 조인용 필드 (TB_USER)
    private String userNickname;            // 작성자 닉네임
    
    // 기본 생성자
    public RecipeVO() {}
    
    // Getter/Setter
    public int getRecipeId() {
        return recipeId;
    }
    
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getRecipeTitle() {
        return recipeTitle;
    }
    
    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }
    
    public String getRecipeMainIngredient() {
        return recipeMainIngredient;
    }
    
    public void setRecipeMainIngredient(String recipeMainIngredient) {
        this.recipeMainIngredient = recipeMainIngredient;
    }
    
    public String getRecipeContent() {
        return recipeContent;
    }
    
    public void setRecipeContent(String recipeContent) {
        this.recipeContent = recipeContent;
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
