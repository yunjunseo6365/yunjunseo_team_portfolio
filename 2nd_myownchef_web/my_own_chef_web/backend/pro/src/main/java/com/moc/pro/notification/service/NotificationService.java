package com.moc.pro.notification.service;

import com.moc.pro.notification.vo.CommentNotificationVO;

import java.util.List;
import java.util.Map;

/**
 * 알림 Service 인터페이스
 */
public interface NotificationService {
    
    /**
     * 댓글 알림 생성
     * @param postId 게시글 ID
     * @param postType 게시판 타입 (recipe, freeboard, conv-review, conv-recipe, sharetool)
     * @param postAuthorId 게시글 작성자 ID (알림 받는 사람)
     * @param commenterId 댓글 작성자 ID
     * @param commentId 댓글 ID
     * @return 생성 성공 여부
     */
    boolean createCommentNotification(int postId, String postType, String postAuthorId, 
                                      String commenterId, int commentId);
    
    /**
     * 사용자별 댓글 알림 목록 조회 (프론트엔드용 포맷)
     * @param userId 사용자 ID
     * @return 알림 목록 (id, postId, postType, username, content, createdAt, isRead)
     */
    List<Map<String, Object>> getCommentNotifications(String userId);
    
    /**
     * 읽지 않은 댓글 알림 개수 조회
     * @param userId 사용자 ID
     * @return 읽지 않은 알림 개수
     */
    int getUnreadCount(String userId);
    
    /**
     * 모든 댓글 알림 읽음 처리
     * @param userId 사용자 ID
     * @return 업데이트 성공 여부
     */
    boolean markAllAsRead(String userId);
    
    /**
     * 사용자별 모든 댓글 알림 삭제
     * @param userId 사용자 ID
     * @return 삭제 성공 여부
     */
    boolean deleteAllNotifications(String userId);
    
    /**
     * 특정 기간 경과한 알림 자동 삭제
     * @param days 삭제 기준 일수 (예: 10일)
     * @return 삭제된 알림 개수
     */
    int deleteOldNotifications(int days);
}
