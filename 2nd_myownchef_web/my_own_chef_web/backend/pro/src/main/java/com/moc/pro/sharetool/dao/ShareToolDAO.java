package com.moc.pro.sharetool.dao;

import com.moc.pro.sharetool.vo.ShareToolVO;
import com.moc.pro.sharetool.vo.ShareToolImageVO;

import java.util.List;
import java.util.Map;

/**
 * 요리도구 나눔 DAO 인터페이스
 */
public interface ShareToolDAO {
    
    // ===== 요리도구 나눔 게시글 =====
    
    /**
     * 요리도구 나눔 목록 조회 (지역 + 상태 필터, 페이징)
     * @param params offset, limit, province, city, status
     * @return 요리도구 나눔 목록
     */
    List<ShareToolVO> selectList(Map<String, Object> params);
    
    /**
     * 요리도구 나눔 전체 개수 (필터 포함)
     * @param params province, city, status
     * @return 전체 개수
     */
    int selectTotalCount(Map<String, Object> params);
    
    /**
     * 요리도구 나눔 검색 (키워드 + 지역 필터, 페이징)
     * @param params keyword, province, city, offset, limit
     * @return 검색 결과 목록
     */
    List<ShareToolVO> searchByKeyword(Map<String, Object> params);
    
    /**
     * 검색 결과 전체 개수
     * @param params keyword, province, city
     * @return 검색 결과 개수
     */
    int selectSearchCount(Map<String, Object> params);
    
    /**
     * 요리도구 나눔 상세 조회
     * @param shareToolId 요리도구 나눔 ID
     * @return 요리도구 나눔 정보
     */
    ShareToolVO selectById(int shareToolId);
    
    /**
     * 요리도구 나눔 작성
     * @param shareTool 요리도구 나눔 정보
     * @return 작성된 행 수
     */
    int insertShareTool(ShareToolVO shareTool);
    
    /**
     * 요리도구 나눔 수정
     * @param shareTool 요리도구 나눔 정보
     * @return 수정된 행 수
     */
    int updateShareTool(ShareToolVO shareTool);
    
    /**
     * 요리도구 나눔 삭제
     * @param shareToolId 요리도구 나눔 ID
     * @return 삭제된 행 수
     */
    int deleteShareTool(int shareToolId);
    
    /**
     * 작성자 확인
     * @param params shareToolId, userId
     * @return 일치 개수 (1: 작성자, 0: 아님)
     */
    int checkAuthor(Map<String, Object> params);
    
    /**
     * 나눔 완료 처리 (채팅 수락 시 호출)
     * @param shareToolId 요리도구 나눔 ID
     * @return 수정된 행 수
     */
    int updateStatusComplete(int shareToolId);
    
    // ===== 요리도구 나눔 이미지 =====
    
    /**
     * 요리도구 나눔 이미지 목록 조회
     * @param shareToolId 요리도구 나눔 ID
     * @return 이미지 목록
     */
    List<ShareToolImageVO> selectImagesByShareToolId(int shareToolId);
    
    /**
     * 요리도구 나눔 이미지 저장
     * @param image 이미지 정보
     * @return 저장된 행 수
     */
    int insertImage(ShareToolImageVO image);
    
    /**
     * 요리도구 나눔 이미지 전체 삭제
     * @param shareToolId 요리도구 나눔 ID
     * @return 삭제된 행 수
     */
    int deleteImagesByShareToolId(int shareToolId);
    
    /**
     * 요리도구 나눔 이미지 개별 삭제
     * @param imageId 이미지 ID
     * @return 삭제된 행 수
     */
    int deleteImageById(int imageId);
}
