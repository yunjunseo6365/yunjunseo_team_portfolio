package com.moc.pro.notification.dao;

import com.moc.pro.notification.vo.CommentNotificationVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 알림 DAO 구현체
 */
@Repository
public class NotificationDAOImpl implements NotificationDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.notification.NotificationMapper";
    
    @Override
    public int insertCommentNotification(CommentNotificationVO notification) {
        return sqlSession.insert(NAMESPACE + ".insertCommentNotification", notification);
    }
    
    @Override
    public List<CommentNotificationVO> selectCommentNotificationsByUserId(String userId) {
        return sqlSession.selectList(NAMESPACE + ".selectCommentNotificationsByUserId", userId);
    }
    
    @Override
    public int selectUnreadCount(String userId) {
        return sqlSession.selectOne(NAMESPACE + ".selectUnreadCount", userId);
    }
    
    @Override
    public int updateAllNotificationsAsRead(String userId) {
        return sqlSession.update(NAMESPACE + ".updateAllNotificationsAsRead", userId);
    }
    
    @Override
    public int deleteAllNotificationsByUserId(String userId) {
        return sqlSession.delete(NAMESPACE + ".deleteAllNotificationsByUserId", userId);
    }
    
    @Override
    public int deleteOldNotifications(int days) {
        return sqlSession.delete(NAMESPACE + ".deleteOldNotifications", days);
    }
}
