package com.moc.pro.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

/**
 * 관리자 Controller 인터페이스
 */
@RequestMapping("/api/admin")
public interface AdminController {
    
    /**
     * 회원 목록 조회
     * @param page 페이지 번호 (기본값: 1)
     * @param nickname 검색어 (닉네임)
     * @param session HttpSession (isAdmin 확인)
     * @return ResponseEntity
     */
    @GetMapping("/users")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String nickname,
            HttpSession session
    );
    
    /**
     * 회원 삭제
     * @param userId 사용자 ID
     * @param session HttpSession (isAdmin 확인)
     * @return ResponseEntity
     */
    @DeleteMapping("/users/{userId}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> deleteUser(
            @PathVariable String userId,
            HttpSession session
    );
    
    /**
     * 게시글 목록 조회
     * @param page 페이지 번호 (기본값: 1)
     * @param title 검색어 (제목)
     * @param categories 카테고리 목록 (쉼표 구분)
     * @param session HttpSession (isAdmin 확인)
     * @return ResponseEntity
     */
    @GetMapping("/posts")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getPosts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<String> categories,
            HttpSession session
    );
    
    /**
     * 게시글 삭제
     * @param request boardType, postId
     * @param session HttpSession (isAdmin 확인)
     * @return ResponseEntity
     */
    @DeleteMapping("/posts")
    @ResponseBody
    ResponseEntity<Map<String, Object>> deletePost(
            @RequestBody Map<String, Object> request,
            HttpSession session
    );
}
