package com.moc.pro.notification.dao;

import com.moc.pro.notification.vo.CommentNotificationVO;

import java.util.List;

/**
 * 알림 DAO 인터페이스
 */
public interface NotificationDAO {
    
    /**
     * 댓글 알림 생성
     * @param notification 알림 정보
     * @return 생성된 행 수
     */
    int insertCommentNotification(CommentNotificationVO notification);
    
    /**
     * 사용자별 댓글 알림 목록 조회
     * @param userId 사용자 ID (게시글 작성자)
     * @return 댓글 알림 목록
     */
    List<CommentNotificationVO> selectCommentNotificationsByUserId(String userId);
    
    /**
     * 사용자별 읽지 않은 댓글 알림 개수 조회
     * @param userId 사용자 ID
     * @return 읽지 않은 알림 개수
     */
    int selectUnreadCount(String userId);
    
    /**
     * 모든 댓글 알림 읽음 처리
     * @param userId 사용자 ID
     * @return 업데이트된 행 수
     */
    int updateAllNotificationsAsRead(String userId);
    
    /**
     * 사용자별 모든 댓글 알림 삭제
     * @param userId 사용자 ID
     * @return 삭제된 행 수
     */
    int deleteAllNotificationsByUserId(String userId);
    
    /**
     * 특정 기간 경과한 알림 자동 삭제
     * @param days 삭제 기준 일수 (예: 10일)
     * @return 삭제된 행 수
     */
    int deleteOldNotifications(int days);
}
