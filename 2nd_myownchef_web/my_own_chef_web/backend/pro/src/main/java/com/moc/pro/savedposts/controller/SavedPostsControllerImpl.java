package com.moc.pro.savedposts.controller;

import com.moc.pro.savedposts.service.SavedPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

/**
 * 저장된 게시글 Controller 구현체
 */
@Controller
public class SavedPostsControllerImpl implements SavedPostsController {
    
    @Autowired
    private SavedPostsService savedPostsService;
    
    @Override
    public ResponseEntity<Map<String, Object>> savePost(
            Map<String, Object> request,
            jakarta.servlet.http.HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null || sessionUserId.isEmpty()) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 파라미터 추출
            String boardType = (String) request.get("boardType");
            Integer postId = (Integer) request.get("postId");
            
            // 유효성 검사
            if (boardType == null || postId == null) {
                response.put("success", false);
                response.put("message", "필수 파라미터가 누락되었습니다.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 저장
            boolean success = savedPostsService.savePost(sessionUserId, boardType, postId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "저장되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "이미 저장된 게시글입니다.");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "저장 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> unsavePost(
            Map<String, Object> request,
            jakarta.servlet.http.HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null || sessionUserId.isEmpty()) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 파라미터 추출
            String boardType = (String) request.get("boardType");
            Integer postId = (Integer) request.get("postId");
            
            // 유효성 검사
            if (boardType == null || postId == null) {
                response.put("success", false);
                response.put("message", "필수 파라미터가 누락되었습니다.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 저장 취소
            boolean success = savedPostsService.unsavePost(sessionUserId, boardType, postId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "저장이 취소되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "저장되지 않은 게시글입니다.");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "저장 취소 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> checkSavedPost(
            String boardType,
            int postId,
            jakarta.servlet.http.HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null || sessionUserId.isEmpty()) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 저장 여부 확인
            boolean isSaved = savedPostsService.checkSavedPost(sessionUserId, boardType, postId);
            
            response.put("success", true);
            response.put("data", Map.of("isSaved", isSaved));
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "저장 확인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
