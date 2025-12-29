package com.moc.pro.convreview.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 편의점 리뷰 Controller 인터페이스
 */
@RequestMapping("/api/conv-review")
public interface ConvReviewController {
    
    /**
     * 편의점 리뷰 목록 조회 (편의점 + 카테고리 필터, 페이징)
     * GET /api/conv-review/list?store={store}&category={category}&page={page}
     */
    ResponseEntity<Map<String, Object>> getList(String store, String category, Integer page);
    
    /**
     * 편의점 리뷰 상세 조회
     * GET /api/conv-review/{id}
     */
    ResponseEntity<Map<String, Object>> getDetail(int id);
    
    /**
     * 작성자 확인
     * GET /api/conv-review/{id}/check-author
     */
    ResponseEntity<Map<String, Object>> checkAuthor(int id, HttpSession session);
    
    /**
     * 편의점 리뷰 작성
     * POST /api/conv-review
     */
    ResponseEntity<Map<String, Object>> createConvReview(
            String userId,
            String title,
            String content,
            Integer price,
            String store,
            String category,
            MultipartFile[] images,
            HttpSession session
    );
    
    /**
     * 편의점 리뷰 수정
     * PUT /api/conv-review/{id}
     */
    ResponseEntity<Map<String, Object>> updateConvReview(
            int id,
            String title,
            String content,
            Integer price,
            String store,
            String category,
            MultipartFile[] images,
            String existingImageUrls,
            HttpSession session
    );
    
    /**
     * 편의점 리뷰 삭제
     * DELETE /api/conv-review/{id}
     */
    ResponseEntity<Map<String, Object>> deleteConvReview(int id, HttpSession session);
    
    /**
     * 댓글 목록 조회
     * GET /api/conv-review/{id}/comments
     */
    ResponseEntity<Map<String, Object>> getComments(int id);
    
    /**
     * 댓글 작성
     * POST /api/conv-review/{id}/comments
     */
    ResponseEntity<Map<String, Object>> createComment(int id, Map<String, String> request, HttpSession session);
    
    /**
     * 댓글 삭제
     * DELETE /api/conv-review/comments/{commentId}
     */
    ResponseEntity<Map<String, Object>> deleteComment(int commentId, HttpSession session);
}
