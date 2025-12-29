package com.moc.pro.notice.service;

import com.moc.pro.notice.vo.NoticeVO;

import java.util.Map;

/**
 * 공지사항 Service 인터페이스
 */
public interface NoticeService {
    
    /**
     * 공지사항 목록 조회 (페이징)
     * @param page 페이지 번호
     * @return { posts: List, totalPage: int }
     */
    Map<String, Object> getList(int page);
    
    /**
     * 공지사항 상세 조회
     * @param noticeId 공지사항 ID
     * @return 공지사항 상세 정보
     */
    NoticeVO getDetail(int noticeId);
    
    /**
     * 공지사항 작성
     * @param notice 공지사항 정보
     * @return 성공 여부
     */
    boolean createNotice(NoticeVO notice);
    
    /**
     * 공지사항 수정
     * @param notice 수정할 공지사항 정보
     * @return 성공 여부
     */
    boolean updateNotice(NoticeVO notice);
    
    /**
     * 공지사항 삭제
     * @param noticeId 공지사항 ID
     * @return 성공 여부
     */
    boolean deleteNotice(int noticeId);
}
