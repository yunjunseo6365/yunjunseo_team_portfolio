package com.moc.pro.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 알림 Controller 인터페이스
 */
@RequestMapping("/api/notifications")
public interface NotificationController {
    
    /**
     * 댓글 알림 목록 조회
     * GET /api/notifications/comments
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getCommentNotifications(HttpSession session);
    
    /**
     * 읽지 않은 댓글 알림 개수 조회
     * GET /api/notifications/comments/unread-count
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getUnreadCount(HttpSession session);
    
    /**
     * 모든 댓글 알림 읽음 처리
     * PUT /api/notifications/comments/read-all
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> markAllAsRead(HttpSession session);
    
    /**
     * 모든 댓글 알림 삭제
     * DELETE /api/notifications/comments
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> deleteAllNotifications(HttpSession session);
}
