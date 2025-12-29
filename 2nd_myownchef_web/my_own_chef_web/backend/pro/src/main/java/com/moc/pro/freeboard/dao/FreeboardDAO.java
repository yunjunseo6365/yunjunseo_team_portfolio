package com.moc.pro.freeboard.dao;

import com.moc.pro.freeboard.vo.FreeboardVO;
import com.moc.pro.freeboard.vo.FreeboardCommentVO;

import java.util.List;
import java.util.Map;

/**
 * 자유게시판 DAO 인터페이스
 */
public interface FreeboardDAO {
    
    /**
     * 자유게시판 목록 조회 (페이징)
     * @param params offset, limit 포함
     * @return 게시글 목록
     */
    List<FreeboardVO> selectList(Map<String, Object> params);
    
    /**
     * 자유게시판 전체 개수 조회
     * @return 전체 게시글 수
     */
    int selectTotalCount();
    
    /**
     * 자유게시판 검색 (페이징)
     * @param params keyword, offset, limit 포함
     * @return 검색 결과 목록
     */
    List<FreeboardVO> searchByKeyword(Map<String, Object> params);
    
    /**
     * 자유게시판 검색 결과 개수
     * @param keyword 검색어
     * @return 검색 결과 수
     */
    int selectSearchCount(String keyword);
    
    /**
     * 자유게시판 상세 조회
     * @param freeboardId 게시글 ID
     * @return 게시글 상세
     */
    FreeboardVO selectById(int freeboardId);
    
    /**
     * 자유게시판 작성
     * @param freeboard 게시글 정보
     * @return 삽입 성공 개수
     */
    int insertFreeboard(FreeboardVO freeboard);
    
    /**
     * 자유게시판 수정
     * @param freeboard 수정할 게시글 정보
     * @return 수정 성공 개수
     */
    int updateFreeboard(FreeboardVO freeboard);
    
    /**
     * 자유게시판 삭제
     * @param freeboardId 게시글 ID
     * @return 삭제 성공 개수
     */
    int deleteFreeboard(int freeboardId);
    
    /**
     * 작성자 여부 확인
     * @param params freeboardId, userId 포함
     * @return 작성자 여부
     */
    int checkAuthor(Map<String, Object> params);
    
    // ===== 댓글 관련 =====
    
    /**
     * 댓글 목록 조회
     * @param freeboardId 게시글 ID
     * @return 댓글 목록
     */
    List<FreeboardCommentVO> selectCommentsByBoardId(int freeboardId);
    
    /**
     * 댓글 작성
     * @param comment 댓글 정보
     * @return 삽입 성공 개수
     */
    int insertComment(FreeboardCommentVO comment);
    
    /**
     * 댓글 삭제
     * @param commentId 댓글 ID
     * @return 삭제 성공 개수
     */
    int deleteComment(int commentId);
    
    /**
     * 댓글 작성자 여부 확인
     * @param params commentId, userId 포함
     * @return 작성자 여부
     */
    int checkCommentAuthor(Map<String, Object> params);
}
