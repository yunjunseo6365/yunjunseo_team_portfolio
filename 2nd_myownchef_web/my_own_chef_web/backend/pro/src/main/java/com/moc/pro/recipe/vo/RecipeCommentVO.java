package com.moc.pro.recipe.vo;

import java.sql.Timestamp;

/**
 * 레시피 댓글 VO
 * TB_RECIPE_COMMENT 테이블 매핑
 */
public class RecipeCommentVO {
    private int recipeCommentId;            // 댓글 ID (PK)
    private int recipeId;                   // 레시피 ID (FK)
    private String userId;                  // 작성자 ID
    private String recipeCommentContent;    // 댓글 내용
    private Timestamp createdAt;            // 작성일시
    private String createdBy;               // 작성자
    private Timestamp updatedAt;            // 수정일시
    private String updatedBy;               // 수정자
    
    // 조인용 필드 (TB_USER)
    private String userNickname;            // 작성자 닉네임
    
    // Getter/Setter
    public int getRecipeCommentId() {
        return recipeCommentId;
    }
    
    public void setRecipeCommentId(int recipeCommentId) {
        this.recipeCommentId = recipeCommentId;
    }
    
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
    
    public String getRecipeCommentContent() {
        return recipeCommentContent;
    }
    
    public void setRecipeCommentContent(String recipeCommentContent) {
        this.recipeCommentContent = recipeCommentContent;
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
