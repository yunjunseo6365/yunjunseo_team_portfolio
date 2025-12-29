package com.moc.pro.mypage.service;

import java.util.Map;

/**
 * 마이페이지 Service 인터페이스
 * 마이페이지 관련 비즈니스 로직 정의
 */
public interface MyPageService {
    
    /**
     * 내가 쓴 글 목록 조회
     * @param userId 사용자 ID
     * @param page 페이지 번호 (1부터 시작)
     * @return Map<String, Object> {posts, totalPage, currentPage, totalCount}
     */
    Map<String, Object> getMyPosts(String userId, int page);
    
    /**
     * 저장한 글 목록 조회
     * @param userId 사용자 ID
     * @param page 페이지 번호 (1부터 시작)
     * @return Map<String, Object> {posts, totalPage, currentPage, totalCount}
     */
    Map<String, Object> getSavedPosts(String userId, int page);
}
