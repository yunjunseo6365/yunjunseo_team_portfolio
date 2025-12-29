package com.moc.pro.convrecipe.dao;

import com.moc.pro.convrecipe.vo.ConvRecipeVO;
import com.moc.pro.convrecipe.vo.ConvRecipeCommentVO;
import com.moc.pro.convrecipe.vo.ConvRecipeProductVO;
import com.moc.pro.convrecipe.vo.ConvRecipeOrderVO;
import com.moc.pro.convrecipe.vo.ConvRecipeImageVO;

import java.util.List;
import java.util.Map;

public interface ConvRecipeDAO {
    
    // 편의점 조합 게시글 관련 (9개)
    List<ConvRecipeVO> selectList(Map<String, Object> params);
    int selectTotalCount(Map<String, Object> params);
    List<ConvRecipeVO> searchByKeyword(Map<String, Object> params);
    int selectSearchCount(Map<String, Object> params);
    ConvRecipeVO selectById(int convRecipeId);
    int insertConvRecipe(ConvRecipeVO convRecipe);
    int updateConvRecipe(ConvRecipeVO convRecipe);
    int deleteConvRecipe(int convRecipeId);
    int checkAuthor(Map<String, Object> params);
    
    // 제품 관련 (3개)
    List<ConvRecipeProductVO> selectProductsByRecipeId(int convRecipeId);
    int insertProduct(ConvRecipeProductVO product);
    int deleteProductsByRecipeId(int convRecipeId);
    
    // 조리 순서 관련 (3개)
    List<ConvRecipeOrderVO> selectOrdersByRecipeId(int convRecipeId);
    int insertOrder(ConvRecipeOrderVO order);
    int deleteOrdersByRecipeId(int convRecipeId);
    
    // 이미지 관련 (4개)
    List<ConvRecipeImageVO> selectImagesByRecipeId(int convRecipeId);
    int insertImage(ConvRecipeImageVO image);
    int deleteImagesByRecipeId(int convRecipeId);
    int deleteImageById(int imageId);
    
    // 댓글 관련 (4개)
    List<ConvRecipeCommentVO> selectCommentsByRecipeId(int convRecipeId);
    int insertComment(ConvRecipeCommentVO comment);
    int deleteComment(int commentId);
    int checkCommentAuthor(Map<String, Object> params);
}
