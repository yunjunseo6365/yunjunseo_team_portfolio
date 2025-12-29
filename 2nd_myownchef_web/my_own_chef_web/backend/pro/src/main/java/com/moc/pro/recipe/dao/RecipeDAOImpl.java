package com.moc.pro.recipe.dao;

import com.moc.pro.recipe.vo.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 레시피 DAO 구현체
 */
@Repository
public class RecipeDAOImpl implements RecipeDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.recipe.dao.RecipeDAO";
    
    // ===== 레시피 게시글 =====
    
    @Override
    public List<RecipeVO> selectList(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".selectList", params);
    }
    
    @Override
    public int selectTotalCount() {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalCount");
    }
    
    @Override
    public List<RecipeVO> searchByKeyword(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".searchByKeyword", params);
    }
    
    @Override
    public int selectSearchCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectSearchCount", params);
    }
    
    @Override
    public RecipeVO selectById(int recipeId) {
        return sqlSession.selectOne(NAMESPACE + ".selectById", recipeId);
    }
    
    @Override
    public int insertRecipe(RecipeVO recipe) {
        return sqlSession.insert(NAMESPACE + ".insertRecipe", recipe);
    }
    
    @Override
    public int updateRecipe(RecipeVO recipe) {
        return sqlSession.update(NAMESPACE + ".updateRecipe", recipe);
    }
    
    @Override
    public int deleteRecipe(int recipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteRecipe", recipeId);
    }
    
    @Override
    public int checkAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkAuthor", params);
    }
    
    // ===== 레시피 재료 =====
    
    @Override
    public List<RecipeIngredientVO> selectIngredientsByRecipeId(int recipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectIngredientsByRecipeId", recipeId);
    }
    
    @Override
    public int insertIngredient(RecipeIngredientVO ingredient) {
        return sqlSession.insert(NAMESPACE + ".insertIngredient", ingredient);
    }
    
    @Override
    public int deleteIngredientsByRecipeId(int recipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteIngredientsByRecipeId", recipeId);
    }
    
    // ===== 레시피 순서 =====
    
    @Override
    public List<RecipeOrderVO> selectOrdersByRecipeId(int recipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectOrdersByRecipeId", recipeId);
    }
    
    @Override
    public int insertOrder(RecipeOrderVO order) {
        return sqlSession.insert(NAMESPACE + ".insertOrder", order);
    }
    
    @Override
    public int deleteOrdersByRecipeId(int recipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteOrdersByRecipeId", recipeId);
    }
    
    // ===== 레시피 이미지 =====
    
    @Override
    public List<RecipeImageVO> selectImagesByRecipeId(int recipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectImagesByRecipeId", recipeId);
    }
    
    @Override
    public int insertImage(RecipeImageVO image) {
        return sqlSession.insert(NAMESPACE + ".insertImage", image);
    }
    
    @Override
    public int deleteImagesByRecipeId(int recipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteImagesByRecipeId", recipeId);
    }
    
    @Override
    public int deleteImageById(int imageId) {
        return sqlSession.delete(NAMESPACE + ".deleteImageById", imageId);
    }
    
    // ===== 레시피 댓글 =====
    
    @Override
    public List<RecipeCommentVO> selectCommentsByRecipeId(int recipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectCommentsByRecipeId", recipeId);
    }
    
    @Override
    public int insertComment(RecipeCommentVO comment) {
        return sqlSession.insert(NAMESPACE + ".insertComment", comment);
    }
    
    @Override
    public int deleteComment(int commentId) {
        return sqlSession.delete(NAMESPACE + ".deleteComment", commentId);
    }
    
    @Override
    public int checkCommentAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkCommentAuthor", params);
    }
}
