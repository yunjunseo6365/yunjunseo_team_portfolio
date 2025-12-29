package com.moc.pro.notification.scheduler;

import com.moc.pro.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 알림 자동 삭제 스케줄러
 * 10일 이전 알림 자동 삭제
 */
@Component
public class NotificationScheduler {
    
    @Autowired
    private NotificationService notificationService;
    
    /**
     * 10일 이전 알림 자동 삭제
     * 매일 새벽 3시에 실행
     * cron: 초 분 시 일 월 요일
     */
    @Scheduled(cron = "0 0 3 * * *")
    public void deleteOldNotifications() {
        try {
            int deletedCount = notificationService.deleteOldNotifications(10);
            System.out.println("[NotificationScheduler] 10일 이전 알림 삭제 완료: " + deletedCount + "개");
        } catch (Exception e) {
            System.err.println("[NotificationScheduler] 알림 삭제 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
