package com.moc.pro.notification.controller;

import com.moc.pro.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 알림 Controller 구현체
 */
@Controller
public class NotificationControllerImpl implements NotificationController {
    
    @Autowired
    private NotificationService notificationService;
    
    @GetMapping("/comments")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getCommentNotifications(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            List<Map<String, Object>> notifications = notificationService.getCommentNotifications(userId);
            
            response.put("success", true);
            response.put("data", notifications);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "알림 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/comments/unread-count")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getUnreadCount(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            int count = notificationService.getUnreadCount(userId);
            
            response.put("success", true);
            response.put("data", Map.of("count", count));
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "알림 개수 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/comments/read-all")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> markAllAsRead(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = notificationService.markAllAsRead(userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "모든 알림을 읽음 처리했습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "알림 읽음 처리 실패");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "알림 읽음 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @DeleteMapping("/comments")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deleteAllNotifications(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = notificationService.deleteAllNotifications(userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "모든 알림이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "알림 삭제 실패");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "알림 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
