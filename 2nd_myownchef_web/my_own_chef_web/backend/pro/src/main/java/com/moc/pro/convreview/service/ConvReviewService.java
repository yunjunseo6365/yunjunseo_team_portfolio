package com.moc.pro.convreview.service;

import com.moc.pro.convreview.vo.ConvReviewVO;
import com.moc.pro.convreview.vo.ConvReviewCommentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 편의점 리뷰 Service 인터페이스
 */
public interface ConvReviewService {
    
    // ===== 편의점 리뷰 게시글 =====
    
    /**
     * 편의점 리뷰 목록 조회 (편의점 + 카테고리 필터, 페이징)
     * @param store 편의점 종류
     * @param category 제품 분류
     * @param page 페이지 번호
     * @return { posts, totalPage, currentPage, totalCount }
     */
    Map<String, Object> getList(String store, String category, int page);
    
    /**
     * 편의점 리뷰 상세 조회 (이미지 포함)
     * @param convReviewId 편의점 리뷰 ID
     * @return { convReview, images }
     */
    Map<String, Object> getDetail(int convReviewId);
    
    /**
     * 편의점 리뷰 작성 (이미지 포함)
     * @param convReview 편의점 리뷰 정보
     * @param images 이미지 파일 배열
     * @return 작성 성공 여부
     */
    boolean createConvReview(ConvReviewVO convReview, MultipartFile[] images);
    
    /**
     * 편의점 리뷰 수정 (이미지 포함)
     * @param convReview 편의점 리뷰 정보
     * @param images 이미지 파일 배열
     * @param existingImageUrls 유지할 기존 이미지 URL (JSON)
     * @return 수정 성공 여부
     */
    boolean updateConvReview(ConvReviewVO convReview, MultipartFile[] images, String existingImageUrls);
    
    /**
     * 편의점 리뷰 삭제 (이미지 포함)
     * @param convReviewId 편의점 리뷰 ID
     * @return 삭제 성공 여부
     */
    boolean deleteConvReview(int convReviewId);
    
    /**
     * 작성자 확인
     * @param convReviewId 편의점 리뷰 ID
     * @param userId 사용자 ID
     * @return 작성자 여부
     */
    boolean checkAuthor(int convReviewId, String userId);
    
    // ===== 편의점 리뷰 댓글 =====
    
    /**
     * 편의점 리뷰 댓글 목록 조회
     * @param convReviewId 편의점 리뷰 ID
     * @return 댓글 목록
     */
    List<ConvReviewCommentVO> getComments(int convReviewId);
    
    /**
     * 편의점 리뷰 댓글 작성
     * @param comment 댓글 정보
     * @return 작성 성공 여부
     */
    boolean createComment(ConvReviewCommentVO comment);
    
    /**
     * 편의점 리뷰 댓글 삭제
     * @param commentId 댓글 ID
     * @param userId 사용자 ID
     * @return 삭제 성공 여부
     */
    boolean deleteComment(int commentId, String userId);
}
