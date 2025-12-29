package com.moc.pro.admin.service;

import java.util.List;
import java.util.Map;

/**
 * 관리자 Service 인터페이스
 */
public interface AdminService {
    
    /**
     * 회원 목록 조회
     * @param page 페이지 번호
     * @param nickname 검색어 (닉네임)
     * @return Map<String, Object> {users, totalPage, currentPage, totalCount}
     */
    Map<String, Object> getUsers(int page, String nickname);
    
    /**
     * 회원 삭제
     * @param userId 사용자 ID
     * @return 성공 여부
     */
    boolean deleteUser(String userId);
    
    /**
     * 게시글 목록 조회
     * @param page 페이지 번호
     * @param title 검색어 (제목)
     * @param categories 카테고리 목록
     * @return Map<String, Object> {posts, totalPage, currentPage, totalCount}
     */
    Map<String, Object> getPosts(int page, String title, List<String> categories);
    
    /**
     * 게시글 삭제
     * @param boardType 게시판 타입
     * @param postId 게시글 ID
     * @return 성공 여부
     */
    boolean deletePost(String boardType, int postId);
}
