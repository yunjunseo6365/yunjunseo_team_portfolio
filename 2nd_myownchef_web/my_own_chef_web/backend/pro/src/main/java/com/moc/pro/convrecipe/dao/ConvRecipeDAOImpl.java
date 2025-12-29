package com.moc.pro.convrecipe.dao;

import com.moc.pro.convrecipe.vo.ConvRecipeVO;
import com.moc.pro.convrecipe.vo.ConvRecipeCommentVO;
import com.moc.pro.convrecipe.vo.ConvRecipeProductVO;
import com.moc.pro.convrecipe.vo.ConvRecipeOrderVO;
import com.moc.pro.convrecipe.vo.ConvRecipeImageVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ConvRecipeDAOImpl implements ConvRecipeDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.convrecipe.dao.ConvRecipeDAO";
    
    // 편의점 조합 게시글 관련
    @Override
    public List<ConvRecipeVO> selectList(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".selectList", params);
    }
    
    @Override
    public int selectTotalCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalCount", params);
    }
    
    @Override
    public List<ConvRecipeVO> searchByKeyword(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".searchByKeyword", params);
    }
    
    @Override
    public int selectSearchCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectSearchCount", params);
    }
    
    @Override
    public ConvRecipeVO selectById(int convRecipeId) {
        return sqlSession.selectOne(NAMESPACE + ".selectById", convRecipeId);
    }
    
    @Override
    public int insertConvRecipe(ConvRecipeVO convRecipe) {
        return sqlSession.insert(NAMESPACE + ".insertConvRecipe", convRecipe);
    }
    
    @Override
    public int updateConvRecipe(ConvRecipeVO convRecipe) {
        return sqlSession.update(NAMESPACE + ".updateConvRecipe", convRecipe);
    }
    
    @Override
    public int deleteConvRecipe(int convRecipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteConvRecipe", convRecipeId);
    }
    
    @Override
    public int checkAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkAuthor", params);
    }
    
    // 제품 관련
    @Override
    public List<ConvRecipeProductVO> selectProductsByRecipeId(int convRecipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectProductsByRecipeId", convRecipeId);
    }
    
    @Override
    public int insertProduct(ConvRecipeProductVO product) {
        return sqlSession.insert(NAMESPACE + ".insertProduct", product);
    }
    
    @Override
    public int deleteProductsByRecipeId(int convRecipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteProductsByRecipeId", convRecipeId);
    }
    
    // 조리 순서 관련
    @Override
    public List<ConvRecipeOrderVO> selectOrdersByRecipeId(int convRecipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectOrdersByRecipeId", convRecipeId);
    }
    
    @Override
    public int insertOrder(ConvRecipeOrderVO order) {
        return sqlSession.insert(NAMESPACE + ".insertOrder", order);
    }
    
    @Override
    public int deleteOrdersByRecipeId(int convRecipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteOrdersByRecipeId", convRecipeId);
    }
    
    // 이미지 관련
    @Override
    public List<ConvRecipeImageVO> selectImagesByRecipeId(int convRecipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectImagesByRecipeId", convRecipeId);
    }
    
    @Override
    public int insertImage(ConvRecipeImageVO image) {
        return sqlSession.insert(NAMESPACE + ".insertImage", image);
    }
    
    @Override
    public int deleteImagesByRecipeId(int convRecipeId) {
        return sqlSession.delete(NAMESPACE + ".deleteImagesByRecipeId", convRecipeId);
    }
    
    @Override
    public int deleteImageById(int imageId) {
        return sqlSession.delete(NAMESPACE + ".deleteImageById", imageId);
    }
    
    // 댓글 관련
    @Override
    public List<ConvRecipeCommentVO> selectCommentsByRecipeId(int convRecipeId) {
        return sqlSession.selectList(NAMESPACE + ".selectCommentsByRecipeId", convRecipeId);
    }
    
    @Override
    public int insertComment(ConvRecipeCommentVO comment) {
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
