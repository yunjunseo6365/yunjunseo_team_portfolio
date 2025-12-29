package com.moc.pro.mypage.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 마이페이지 Controller 인터페이스
 * 마이페이지 관련 API 정의
 */
@RequestMapping("/api/mypage")
public interface MyPageController {
    
    /**
     * 내가 쓴 글 목록 조회
     * @param page 페이지 번호 (기본값: 1)
     * @param session HttpSession (userId 추출용)
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping("/posts")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getMyPosts(
            @RequestParam(defaultValue = "1") int page,
            HttpSession session);
    
    /**
     * 저장한 글 목록 조회
     * @param page 페이지 번호 (기본값: 1)
     * @param session HttpSession (userId 추출용)
     * @return ResponseEntity<Map<String, Object>>
     */
    @GetMapping("/saved")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getSavedPosts(
            @RequestParam(defaultValue = "1") int page,
            HttpSession session);
}
