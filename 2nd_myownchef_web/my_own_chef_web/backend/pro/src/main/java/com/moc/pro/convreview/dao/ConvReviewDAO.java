package com.moc.pro.convreview.dao;

import com.moc.pro.convreview.vo.ConvReviewVO;
import com.moc.pro.convreview.vo.ConvReviewCommentVO;
import com.moc.pro.convreview.vo.ConvReviewImageVO;

import java.util.List;
import java.util.Map;

/**
 * 편의점 리뷰 DAO 인터페이스
 */
public interface ConvReviewDAO {
    
    // ===== 편의점 리뷰 게시글 =====
    
    /**
     * 편의점 리뷰 목록 조회 (편의점 + 카테고리 필터, 페이징)
     * @param params offset, limit, store, category
     * @return 편의점 리뷰 목록
     */
    List<ConvReviewVO> selectList(Map<String, Object> params);
    
    /**
     * 편의점 리뷰 전체 개수 (필터 포함)
     * @param params store, category
     * @return 전체 개수
     */
    int selectTotalCount(Map<String, Object> params);
    
    /**
     * 편의점 리뷰 상세 조회
     * @param convReviewId 편의점 리뷰 ID
     * @return 편의점 리뷰 정보
     */
    ConvReviewVO selectById(int convReviewId);
    
    /**
     * 편의점 리뷰 작성
     * @param convReview 편의점 리뷰 정보
     * @return 작성된 행 수
     */
    int insertConvReview(ConvReviewVO convReview);
    
    /**
     * 편의점 리뷰 수정
     * @param convReview 편의점 리뷰 정보
     * @return 수정된 행 수
     */
    int updateConvReview(ConvReviewVO convReview);
    
    /**
     * 편의점 리뷰 삭제
     * @param convReviewId 편의점 리뷰 ID
     * @return 삭제된 행 수
     */
    int deleteConvReview(int convReviewId);
    
    /**
     * 작성자 확인
     * @param params convReviewId, userId
     * @return 일치 개수 (1: 작성자, 0: 아님)
     */
    int checkAuthor(Map<String, Object> params);
    
    // ===== 편의점 리뷰 이미지 =====
    
    /**
     * 편의점 리뷰 이미지 목록 조회
     * @param convReviewId 편의점 리뷰 ID
     * @return 이미지 목록
     */
    List<ConvReviewImageVO> selectImagesByConvReviewId(int convReviewId);
    
    /**
     * 편의점 리뷰 이미지 저장
     * @param image 이미지 정보
     * @return 저장된 행 수
     */
    int insertImage(ConvReviewImageVO image);
    
    /**
     * 편의점 리뷰 이미지 전체 삭제
     * @param convReviewId 편의점 리뷰 ID
     * @return 삭제된 행 수
     */
    int deleteImagesByConvReviewId(int convReviewId);
    
    /**
     * 편의점 리뷰 이미지 개별 삭제
     * @param imageId 이미지 ID
     * @return 삭제된 행 수
     */
    int deleteImageById(int imageId);
    
    // ===== 편의점 리뷰 댓글 =====
    
    /**
     * 편의점 리뷰 댓글 목록 조회
     * @param convReviewId 편의점 리뷰 ID
     * @return 댓글 목록
     */
    List<ConvReviewCommentVO> selectCommentsByConvReviewId(int convReviewId);
    
    /**
     * 편의점 리뷰 댓글 작성
     * @param comment 댓글 정보
     * @return 작성된 행 수
     */
    int insertComment(ConvReviewCommentVO comment);
    
    /**
     * 편의점 리뷰 댓글 삭제
     * @param commentId 댓글 ID
     * @return 삭제된 행 수
     */
    int deleteComment(int commentId);
    
    /**
     * 댓글 작성자 확인
     * @param params commentId, userId
     * @return 일치 개수 (1: 작성자, 0: 아님)
     */
    int checkCommentAuthor(Map<String, Object> params);
}
