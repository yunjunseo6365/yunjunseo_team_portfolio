package com.moc.pro.savedposts.dao;

import com.moc.pro.savedposts.vo.SavedPostsVO;

import java.util.Map;

/**
 * 저장된 게시글 DAO 인터페이스
 */
public interface SavedPostsDAO {
    
    /**
     * 게시글 저장
     * @param savedPost 저장 정보
     * @return 저장된 행 수
     */
    int insertSavedPost(SavedPostsVO savedPost);
    
    /**
     * 게시글 저장 취소
     * @param params userId, boardType, postId
     * @return 삭제된 행 수
     */
    int deleteSavedPost(Map<String, Object> params);
    
    /**
     * 저장 여부 확인
     * @param params userId, boardType, postId
     * @return 저장 횟수
     */
    int checkSavedPost(Map<String, Object> params);
}
