package com.moc.pro.notice.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * 공지사항 Controller 인터페이스
 */
@RequestMapping("/api/notice")
public interface NoticeController {
    
    /**
     * 공지사항 목록 조회
     * @param page 페이지 번호
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getList(int page);
    
    /**
     * 공지사항 상세 조회
     * @param id 공지사항 ID
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getDetail(int id);
    
    /**
     * 공지사항 작성
     * @param request 공지사항 정보
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> createNotice(Map<String, String> request, HttpSession session);
    
    /**
     * 공지사항 수정
     * @param id 공지사항 ID
     * @param request 수정할 공지사항 정보
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> updateNotice(int id, Map<String, String> request, HttpSession session);
    
    /**
     * 공지사항 삭제
     * @param id 공지사항 ID
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> deleteNotice(int id, HttpSession session);
}
