package com.moc.pro.admin.dao;

import com.moc.pro.admin.vo.AdminPostVO;
import com.moc.pro.admin.vo.AdminUserVO;

import java.util.List;
import java.util.Map;

/**
 * 관리자 DAO 인터페이스
 */
public interface AdminDAO {
    
    // ========== 회원 관리 ==========
    
    /**
     * 회원 목록 조회 (페이징 + 검색)
     * @param params offset, limit, nickname
     * @return List<AdminUserVO> 회원 목록
     */
    List<AdminUserVO> selectUsers(Map<String, Object> params);
    
    /**
     * 회원 전체 개수 (검색 포함)
     * @param nickname 검색어 (null 가능)
     * @return 전체 개수
     */
    int selectUsersTotalCount(String nickname);
    
    /**
     * 회원 삭제 (논리 삭제)
     * @param userId 사용자 ID
     * @return 삭제된 행 수
     */
    int deleteUser(String userId);
    
    // ========== 게시글 관리 ==========
    
    /**
     * 게시글 목록 조회 (UNION ALL + 페이징 + 검색)
     * @param params offset, limit, title, categories
     * @return List<AdminPostVO> 게시글 목록
     */
    List<AdminPostVO> selectPosts(Map<String, Object> params);
    
    /**
     * 게시글 전체 개수 (검색 포함)
     * @param params title, categories
     * @return 전체 개수
     */
    int selectPostsTotalCount(Map<String, Object> params);
    
    /**
     * 게시글 삭제 (boardType별 분기)
     * @param boardType 게시판 타입
     * @param postId 게시글 ID
     * @return 삭제된 행 수
     */
    int deletePost(String boardType, int postId);
}
