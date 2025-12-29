package com.moc.pro.freeboard.service;

import com.moc.pro.freeboard.vo.FreeboardVO;
import com.moc.pro.freeboard.vo.FreeboardCommentVO;

import java.util.List;
import java.util.Map;

/**
 * 자유게시판 Service 인터페이스
 */
public interface FreeboardService {
    
    /**
     * 자유게시판 목록 조회 (페이징)
     * @param page 페이지 번호
     * @return { posts: List, totalPage: int }
     */
    Map<String, Object> getList(int page);
    
    /**
     * 자유게시판 검색 (페이징)
     * @param keyword 검색어
     * @param page 페이지 번호
     * @return { posts: List, totalPage: int }
     */
    Map<String, Object> searchPosts(String keyword, int page);
    
    /**
     * 자유게시판 상세 조회
     * @param freeboardId 게시글 ID
     * @return 게시글 상세 정보
     */
    FreeboardVO getDetail(int freeboardId);
    
    /**
     * 자유게시판 작성
     * @param freeboard 게시글 정보
     * @return 성공 여부
     */
    boolean createPost(FreeboardVO freeboard);
    
    /**
     * 자유게시판 수정
     * @param freeboard 수정할 게시글 정보
     * @return 성공 여부
     */
    boolean updatePost(FreeboardVO freeboard);
    
    /**
     * 자유게시판 삭제
     * @param freeboardId 게시글 ID
     * @param userId 사용자 ID (권한 확인용)
     * @return 성공 여부
     */
    boolean deletePost(int freeboardId, String userId);
    
    /**
     * 작성자 여부 확인
     * @param freeboardId 게시글 ID
     * @param userId 사용자 ID
     * @return 작성자 여부
     */
    boolean checkAuthor(int freeboardId, String userId);
    
    // ===== 댓글 관련 =====
    
    /**
     * 댓글 목록 조회
     * @param freeboardId 게시글 ID
     * @return 댓글 목록
     */
    List<FreeboardCommentVO> getComments(int freeboardId);
    
    /**
     * 댓글 작성
     * @param comment 댓글 정보
     * @return 성공 여부
     */
    boolean createComment(FreeboardCommentVO comment);
    
    /**
     * 댓글 삭제
     * @param commentId 댓글 ID
     * @param userId 사용자 ID (권한 확인용)
     * @return 성공 여부
     */
    boolean deleteComment(int commentId, String userId);
}
