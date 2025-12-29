package com.moc.pro.mypage.controller;

import com.moc.pro.mypage.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 마이페이지 Controller 구현체
 * Session 기반 인증 및 마이페이지 API 처리
 */
@Controller
public class MyPageControllerImpl implements MyPageController {
    
    @Autowired
    private MyPageService myPageService;
    
    @Override
    public ResponseEntity<Map<String, Object>> getMyPosts(int page, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션에서 userId 가져오기
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 내가 쓴 글 조회
            Map<String, Object> result = myPageService.getMyPosts(userId, page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "내가 쓴 글 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getSavedPosts(int page, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션에서 userId 가져오기
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 저장한 글 조회
            Map<String, Object> result = myPageService.getSavedPosts(userId, page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "저장한 글 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
