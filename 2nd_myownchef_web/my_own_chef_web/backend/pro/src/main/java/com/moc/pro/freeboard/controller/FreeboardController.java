package com.moc.pro.freeboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 자유게시판 Controller 인터페이스
 */
@RequestMapping("/api/freeboard")
public interface FreeboardController {
    
    /**
     * 자유게시판 목록 조회
     * @param page 페이지 번호
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getList(int page);
    
    /**
     * 자유게시판 검색
     * @param keyword 검색어
     * @param page 페이지 번호
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> searchPosts(String keyword, int page);
    
    /**
     * 자유게시판 상세 조회
     * @param id 게시글 ID
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getDetail(int id);
    
    /**
     * 작성자 확인
     * @param id 게시글 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> checkAuthor(int id, HttpSession session);
    
    /**
     * 자유게시판 작성
     * @param request 게시글 정보
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> createPost(Map<String, String> request, HttpSession session);
    
    /**
     * 자유게시판 수정
     * @param id 게시글 ID
     * @param request 수정할 게시글 정보
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> updatePost(int id, Map<String, String> request, HttpSession session);
    
    /**
     * 자유게시판 삭제
     * @param id 게시글 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> deletePost(int id, HttpSession session);
    
    /**
     * 댓글 목록 조회
     * @param id 게시글 ID
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getComments(int id);
    
    /**
     * 댓글 작성
     * @param id 게시글 ID
     * @param request 댓글 정보
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> createComment(int id, Map<String, String> request, HttpSession session);
    
    /**
     * 댓글 삭제
     * @param commentId 댓글 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> deleteComment(int commentId, HttpSession session);
}
