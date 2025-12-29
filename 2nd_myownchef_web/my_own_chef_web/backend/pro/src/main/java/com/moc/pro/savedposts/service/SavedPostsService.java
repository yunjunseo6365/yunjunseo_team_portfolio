package com.moc.pro.savedposts.service;

/**
 * 저장된 게시글 Service 인터페이스
 */
public interface SavedPostsService {
    
    /**
     * 게시글 저장
     * @param userId 사용자 ID
     * @param boardType 게시판 타입
     * @param postId 게시글 ID
     * @return 성공 여부
     */
    boolean savePost(String userId, String boardType, int postId);
    
    /**
     * 게시글 저장 취소
     * @param userId 사용자 ID
     * @param boardType 게시판 타입
     * @param postId 게시글 ID
     * @return 성공 여부
     */
    boolean unsavePost(String userId, String boardType, int postId);
    
    /**
     * 저장 여부 확인
     * @param userId 사용자 ID
     * @param boardType 게시판 타입
     * @param postId 게시글 ID
     * @return 저장 여부
     */
    boolean checkSavedPost(String userId, String boardType, int postId);
}
