package com.moc.pro.notice.dao;

import com.moc.pro.notice.vo.NoticeVO;

import java.util.List;
import java.util.Map;

/**
 * 공지사항 DAO 인터페이스
 */
public interface NoticeDAO {
    
    /**
     * 공지사항 목록 조회 (페이징)
     * @param params offset, limit 포함
     * @return 공지사항 목록
     */
    List<NoticeVO> selectList(Map<String, Object> params);
    
    /**
     * 공지사항 전체 개수 조회
     * @return 전체 공지사항 수
     */
    int selectTotalCount();
    
    /**
     * 공지사항 상세 조회
     * @param noticeId 공지사항 ID
     * @return 공지사항 상세
     */
    NoticeVO selectById(int noticeId);
    
    /**
     * 공지사항 작성
     * @param notice 공지사항 정보
     * @return 삽입 성공 개수
     */
    int insertNotice(NoticeVO notice);
    
    /**
     * 공지사항 수정
     * @param notice 수정할 공지사항 정보
     * @return 수정 성공 개수
     */
    int updateNotice(NoticeVO notice);
    
    /**
     * 공지사항 삭제
     * @param noticeId 공지사항 ID
     * @return 삭제 성공 개수
     */
    int deleteNotice(int noticeId);
}
