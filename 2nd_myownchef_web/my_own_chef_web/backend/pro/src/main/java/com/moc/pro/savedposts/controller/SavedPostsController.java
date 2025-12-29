package com.moc.pro.savedposts.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 저장된 게시글 Controller 인터페이스
 */
@RequestMapping("/api/saved-posts")
public interface SavedPostsController {
    
    /**
     * 게시글 저장
     * @param request boardType, postId
     * @param session HttpSession
     * @return ResponseEntity
     */
    @PostMapping
    @ResponseBody
    ResponseEntity<Map<String, Object>> savePost(
            @RequestBody Map<String, Object> request,
            jakarta.servlet.http.HttpSession session
    );
    
    /**
     * 게시글 저장 취소
     * @param request boardType, postId
     * @param session HttpSession
     * @return ResponseEntity
     */
    @DeleteMapping
    @ResponseBody
    ResponseEntity<Map<String, Object>> unsavePost(
            @RequestBody Map<String, Object> request,
            jakarta.servlet.http.HttpSession session
    );
    
    /**
     * 저장 여부 확인
     * @param boardType 게시판 타입
     * @param postId 게시글 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    @GetMapping("/check")
    @ResponseBody
    ResponseEntity<Map<String, Object>> checkSavedPost(
            @RequestParam String boardType,
            @RequestParam int postId,
            jakarta.servlet.http.HttpSession session
    );
}
