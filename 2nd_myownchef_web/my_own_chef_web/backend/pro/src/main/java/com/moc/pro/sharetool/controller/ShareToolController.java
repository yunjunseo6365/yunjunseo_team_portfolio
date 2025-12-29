package com.moc.pro.sharetool.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 요리도구 나눔 Controller 인터페이스
 */
@RequestMapping("/api/sharetool")
public interface ShareToolController {
    
    /**
     * 요리도구 나눔 목록 조회 (지역 + 상태 필터, 페이징)
     * GET /api/sharetool/list?province={province}&city={city}&status={status}&page={page}
     */
    ResponseEntity<Map<String, Object>> getList(String province, String city, String status, Integer page);
    
    /**
     * 요리도구 나눔 검색 (키워드 + 지역 필터, 페이징)
     * GET /api/sharetool/search?keyword={keyword}&province={province}&city={city}&page={page}
     */
    ResponseEntity<Map<String, Object>> searchPosts(String keyword, String province, String city, Integer page);
    
    /**
     * 요리도구 나눔 상세 조회
     * GET /api/sharetool/{id}
     */
    ResponseEntity<Map<String, Object>> getDetail(int id);
    
    /**
     * 작성자 확인
     * GET /api/sharetool/{id}/check-author
     */
    ResponseEntity<Map<String, Object>> checkAuthor(int id, HttpSession session);
    
    /**
     * 요리도구 나눔 작성
     * POST /api/sharetool
     */
    ResponseEntity<Map<String, Object>> createShareTool(
            String userId,
            String title,
            String product,
            String content,
            String status,
            String province,
            String city,
            MultipartFile[] images,
            HttpSession session
    );
    
    /**
     * 요리도구 나눔 수정
     * PUT /api/sharetool/{id}
     */
    ResponseEntity<Map<String, Object>> updateShareTool(
            int id,
            String title,
            String product,
            String content,
            String status,
            String province,
            String city,
            MultipartFile[] images,
            String existingImageUrls,
            HttpSession session
    );
    
    /**
     * 요리도구 나눔 삭제
     * DELETE /api/sharetool/{id}
     */
    ResponseEntity<Map<String, Object>> deleteShareTool(int id, HttpSession session);
    
    /**
     * 나눔 완료 처리 (채팅 수락 시 호출)
     * POST /api/sharetool/{id}/complete
     */
    ResponseEntity<Map<String, Object>> completeShare(int id, HttpSession session);
}
