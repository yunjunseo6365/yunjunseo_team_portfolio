package com.moc.pro.convreview.dao;

import com.moc.pro.convreview.vo.ConvReviewVO;
import com.moc.pro.convreview.vo.ConvReviewCommentVO;
import com.moc.pro.convreview.vo.ConvReviewImageVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 편의점 리뷰 DAO 구현체
 */
@Repository
public class ConvReviewDAOImpl implements ConvReviewDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.convreview.ConvReviewMapper";
    
    // ===== 편의점 리뷰 게시글 =====
    
    @Override
    public List<ConvReviewVO> selectList(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".selectList", params);
    }
    
    @Override
    public int selectTotalCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalCount", params);
    }
    
    @Override
    public ConvReviewVO selectById(int convReviewId) {
        return sqlSession.selectOne(NAMESPACE + ".selectById", convReviewId);
    }
    
    @Override
    public int insertConvReview(ConvReviewVO convReview) {
        return sqlSession.insert(NAMESPACE + ".insertConvReview", convReview);
    }
    
    @Override
    public int updateConvReview(ConvReviewVO convReview) {
        return sqlSession.update(NAMESPACE + ".updateConvReview", convReview);
    }
    
    @Override
    public int deleteConvReview(int convReviewId) {
        return sqlSession.delete(NAMESPACE + ".deleteConvReview", convReviewId);
    }
    
    @Override
    public int checkAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkAuthor", params);
    }
    
    // ===== 편의점 리뷰 이미지 =====
    
    @Override
    public List<ConvReviewImageVO> selectImagesByConvReviewId(int convReviewId) {
        return sqlSession.selectList(NAMESPACE + ".selectImagesByConvReviewId", convReviewId);
    }
    
    @Override
    public int insertImage(ConvReviewImageVO image) {
        return sqlSession.insert(NAMESPACE + ".insertImage", image);
    }
    
    @Override
    public int deleteImagesByConvReviewId(int convReviewId) {
        return sqlSession.delete(NAMESPACE + ".deleteImagesByConvReviewId", convReviewId);
    }
    
    @Override
    public int deleteImageById(int imageId) {
        return sqlSession.delete(NAMESPACE + ".deleteImageById", imageId);
    }
    
    // ===== 편의점 리뷰 댓글 =====
    
    @Override
    public List<ConvReviewCommentVO> selectCommentsByConvReviewId(int convReviewId) {
        return sqlSession.selectList(NAMESPACE + ".selectCommentsByConvReviewId", convReviewId);
    }
    
    @Override
    public int insertComment(ConvReviewCommentVO comment) {
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
