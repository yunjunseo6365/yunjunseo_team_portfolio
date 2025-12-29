package com.moc.pro.notification.service;

import com.moc.pro.notification.dao.NotificationDAO;
import com.moc.pro.notification.vo.CommentNotificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 알림 Service 구현체
 */
@Service
public class NotificationServiceImpl implements NotificationService {
    
    @Autowired
    private NotificationDAO notificationDAO;
    
    @Override
    @Transactional
    public boolean createCommentNotification(int postId, String postType, String postAuthorId, 
                                             String commenterId, int commentId) {
        // 자기 자신의 댓글은 알림 생성하지 않음
        if (postAuthorId.equals(commenterId)) {
            return false;
        }
        
        CommentNotificationVO notification = new CommentNotificationVO();
        notification.setPostId(postId);
        notification.setPostType(postType);
        notification.setPostAuthorId(postAuthorId);
        notification.setCommenterId(commenterId);
        notification.setCommentId(commentId);
        notification.setCreatedBy(commenterId);
        
        int result = notificationDAO.insertCommentNotification(notification);
        return result > 0;
    }
    
    @Override
    public List<Map<String, Object>> getCommentNotifications(String userId) {
        List<CommentNotificationVO> notifications = notificationDAO.selectCommentNotificationsByUserId(userId);
        List<Map<String, Object>> result = new ArrayList<>();
        
        for (CommentNotificationVO notification : notifications) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", notification.getCommentNotificationId());
            map.put("postId", notification.getPostId());
            map.put("postType", notification.getPostType());
            map.put("username", notification.getCommenterNickname());
            map.put("content", notification.getCommentContent());
            map.put("createdAt", notification.getCreatedAt());
            map.put("isRead", "Y".equals(notification.getIsRead()));
            
            result.add(map);
        }
        
        return result;
    }
    
    @Override
    public int getUnreadCount(String userId) {
        return notificationDAO.selectUnreadCount(userId);
    }
    
    @Override
    @Transactional
    public boolean markAllAsRead(String userId) {
        int result = notificationDAO.updateAllNotificationsAsRead(userId);
        return result >= 0; // 0개 업데이트도 성공으로 간주
    }
    
    @Override
    @Transactional
    public boolean deleteAllNotifications(String userId) {
        int result = notificationDAO.deleteAllNotificationsByUserId(userId);
        return result >= 0; // 0개 삭제도 성공으로 간주
    }
    
    @Override
    @Transactional
    public int deleteOldNotifications(int days) {
        return notificationDAO.deleteOldNotifications(days);
    }
}
