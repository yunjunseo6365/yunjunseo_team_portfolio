package com.moc.pro.admin.controller;

import com.moc.pro.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 관리자 Controller 구현체
 */
@Controller
public class AdminControllerImpl implements AdminController {
    
    @Autowired
    private AdminService adminService;
    
    @Override
    public ResponseEntity<Map<String, Object>> getUsers(int page, String nickname, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 관리자 권한 확인 (Boolean 타입!)
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자 권한이 필요합니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 회원 목록 조회
            Map<String, Object> result = adminService.getUsers(page, nickname);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "회원 목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> deleteUser(String userId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 관리자 권한 확인 (Boolean 타입!)
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자 권한이 필요합니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 회원 삭제
            boolean success = adminService.deleteUser(userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "회원이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "회원 삭제 실패");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "회원 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getPosts(int page, String title, List<String> categories, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 관리자 권한 확인 (Boolean 타입!)
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자 권한이 필요합니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 게시글 목록 조회
            Map<String, Object> result = adminService.getPosts(page, title, categories);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> deletePost(Map<String, Object> request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 관리자 권한 확인 (Boolean 타입!)
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자 권한이 필요합니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 파라미터 추출
            String boardType = (String) request.get("boardType");
            Integer postId = (Integer) request.get("postId");
            
            if (boardType == null || postId == null) {
                response.put("success", false);
                response.put("message", "필수 파라미터가 누락되었습니다.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 게시글 삭제
            boolean success = adminService.deletePost(boardType, postId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "게시글이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "게시글 삭제 실패");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
