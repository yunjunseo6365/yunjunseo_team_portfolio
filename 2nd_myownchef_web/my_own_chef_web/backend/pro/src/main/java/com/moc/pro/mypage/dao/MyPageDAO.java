package com.moc.pro.mypage.dao;

import com.moc.pro.mypage.vo.MyPagePostVO;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 DAO 인터페이스
 * 내가 쓴 글, 저장한 글 데이터 접근
 */
public interface MyPageDAO {
    
    /**
     * 내가 쓴 글 목록 조회 (페이징)
     * @param params {userId, offset, limit}
     * @return List<MyPagePostVO> 내가 쓴 글 목록
     */
    List<MyPagePostVO> selectMyPosts(Map<String, Object> params);
    
    /**
     * 내가 쓴 글 전체 개수
     * @param userId 사용자 ID
     * @return int 전체 개수
     */
    int selectMyPostsTotalCount(String userId);
    
    /**
     * 저장한 글 목록 조회 (페이징)
     * @param params {userId, offset, limit}
     * @return List<MyPagePostVO> 저장한 글 목록
     */
    List<MyPagePostVO> selectSavedPosts(Map<String, Object> params);
    
    /**
     * 저장한 글 전체 개수
     * @param userId 사용자 ID
     * @return int 전체 개수
     */
    int selectSavedPostsTotalCount(String userId);
}
