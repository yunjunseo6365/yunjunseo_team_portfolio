package com.moc.pro.withshopping.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 같이쇼핑 Controller 인터페이스
 */
@RequestMapping("/api/withshopping")
public interface WithShoppingController {
    
    /**
     * 같이쇼핑 목록 조회 (지역 + 상태 필터, 페이징)
     * GET /api/withshopping/list?si={si}&gu={gu}&status={status}&page={page}
     */
    ResponseEntity<Map<String, Object>> getList(String si, String gu, String status, Integer page);
    
    /**
     * 같이쇼핑 검색 (키워드 + 지역 필터, 페이징)
     * GET /api/withshopping/search?keyword={keyword}&si={si}&gu={gu}&page={page}
     */
    ResponseEntity<Map<String, Object>> searchPosts(String keyword, String si, String gu, Integer page);
    
    /**
     * 같이쇼핑 상세 조회
     * GET /api/withshopping/{id}
     */
    ResponseEntity<Map<String, Object>> getDetail(int id);
    
    /**
     * 작성자 확인
     * GET /api/withshopping/{id}/check-author
     */
    ResponseEntity<Map<String, Object>> checkAuthor(String id, HttpSession session);
    
    /**
     * 같이쇼핑 작성
     * POST /api/withshopping
     */
    ResponseEntity<Map<String, Object>> createWithShopping(Map<String, String> request, HttpSession session);
    
    /**
     * 같이쇼핑 수정
     * PUT /api/withshopping/{id}
     */
    ResponseEntity<Map<String, Object>> updateWithShopping(int id, Map<String, String> request, HttpSession session);
    
    /**
     * 같이쇼핑 삭제
     * DELETE /api/withshopping/{id}
     */
    ResponseEntity<Map<String, Object>> deleteWithShopping(int id, HttpSession session);
    
    /**
     * 모집 완료 처리 (채팅 수락 시 호출)
     * POST /api/withshopping/{id}/complete
     */
    ResponseEntity<Map<String, Object>> completeShopping(int id, HttpSession session);
}
