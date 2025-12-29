package com.moc.pro.recipe.dao;

import com.moc.pro.recipe.vo.*;
import java.util.List;
import java.util.Map;

/**
 * 레시피 DAO 인터페이스
 */
public interface RecipeDAO {
    
    // ===== 레시피 게시글 =====
    
    /**
     * 레시피 목록 조회 (페이징)
     * @param params offset, limit 포함
     * @return 레시피 목록
     */
    List<RecipeVO> selectList(Map<String, Object> params);
    
    /**
     * 전체 레시피 개수 조회
     * @return 전체 개수
     */
    int selectTotalCount();
    
    /**
     * 레시피 검색 (페이징)
     * @param params keyword, ingredient, offset, limit
     * @return 검색 결과
     */
    List<RecipeVO> searchByKeyword(Map<String, Object> params);
    
    /**
     * 검색 결과 개수 조회
     * @param params keyword, ingredient
     * @return 검색 결과 개수
     */
    int selectSearchCount(Map<String, Object> params);
    
    /**
     * 레시피 상세 조회
     * @param recipeId 레시피 ID
     * @return 레시피 상세 정보
     */
    RecipeVO selectById(int recipeId);
    
    /**
     * 레시피 작성
     * @param recipe 레시피 정보
     * @return 작성된 행 수
     */
    int insertRecipe(RecipeVO recipe);
    
    /**
     * 레시피 수정
     * @param recipe 수정할 레시피 정보
     * @return 수정된 행 수
     */
    int updateRecipe(RecipeVO recipe);
    
    /**
     * 레시피 삭제
     * @param recipeId 레시피 ID
     * @return 삭제된 행 수
     */
    int deleteRecipe(int recipeId);
    
    /**
     * 작성자 확인
     * @param params recipeId, userId
     * @return 일치하면 1, 아니면 0
     */
    int checkAuthor(Map<String, Object> params);
    
    // ===== 레시피 재료 =====
    
    /**
     * 레시피 재료 목록 조회
     * @param recipeId 레시피 ID
     * @return 재료 목록
     */
    List<RecipeIngredientVO> selectIngredientsByRecipeId(int recipeId);
    
    /**
     * 레시피 재료 추가
     * @param ingredient 재료 정보
     * @return 추가된 행 수
     */
    int insertIngredient(RecipeIngredientVO ingredient);
    
    /**
     * 레시피 재료 삭제 (레시피 삭제 시)
     * @param recipeId 레시피 ID
     * @return 삭제된 행 수
     */
    int deleteIngredientsByRecipeId(int recipeId);
    
    // ===== 레시피 순서 =====
    
    /**
     * 레시피 순서 목록 조회
     * @param recipeId 레시피 ID
     * @return 순서 목록
     */
    List<RecipeOrderVO> selectOrdersByRecipeId(int recipeId);
    
    /**
     * 레시피 순서 추가
     * @param order 순서 정보
     * @return 추가된 행 수
     */
    int insertOrder(RecipeOrderVO order);
    
    /**
     * 레시피 순서 삭제 (레시피 삭제 시)
     * @param recipeId 레시피 ID
     * @return 삭제된 행 수
     */
    int deleteOrdersByRecipeId(int recipeId);
    
    // ===== 레시피 이미지 =====
    
    /**
     * 레시피 이미지 목록 조회
     * @param recipeId 레시피 ID
     * @return 이미지 목록
     */
    List<RecipeImageVO> selectImagesByRecipeId(int recipeId);
    
    /**
     * 레시피 이미지 추가
     * @param image 이미지 정보
     * @return 추가된 행 수
     */
    int insertImage(RecipeImageVO image);
    
    /**
     * 레시피 이미지 삭제 (레시피 삭제 시)
     * @param recipeId 레시피 ID
     * @return 삭제된 행 수
     */
    int deleteImagesByRecipeId(int recipeId);
    
    /**
     * 레시피 이미지 개별 삭제
     * @param imageId 이미지 ID
     * @return 삭제된 행 수
     */
    int deleteImageById(int imageId);
    
    // ===== 레시피 댓글 =====
    
    /**
     * 레시피 댓글 목록 조회
     * @param recipeId 레시피 ID
     * @return 댓글 목록
     */
    List<RecipeCommentVO> selectCommentsByRecipeId(int recipeId);
    
    /**
     * 레시피 댓글 추가
     * @param comment 댓글 정보
     * @return 추가된 행 수
     */
    int insertComment(RecipeCommentVO comment);
    
    /**
     * 레시피 댓글 삭제
     * @param commentId 댓글 ID
     * @return 삭제된 행 수
     */
    int deleteComment(int commentId);
    
    /**
     * 댓글 작성자 확인
     * @param params commentId, userId
     * @return 일치하면 1, 아니면 0
     */
    int checkCommentAuthor(Map<String, Object> params);
}
