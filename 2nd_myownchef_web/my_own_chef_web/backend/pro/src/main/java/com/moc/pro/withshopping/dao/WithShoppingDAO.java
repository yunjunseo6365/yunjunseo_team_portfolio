package com.moc.pro.withshopping.dao;

import com.moc.pro.withshopping.vo.WithShoppingVO;

import java.util.List;
import java.util.Map;

/**
 * 같이쇼핑 DAO 인터페이스
 */
public interface WithShoppingDAO {
    
    /**
     * 같이쇼핑 목록 조회 (지역 + 상태 필터, 페이징)
     * @param params offset, limit, si, gu, status
     * @return 같이쇼핑 목록
     */
    List<WithShoppingVO> selectList(Map<String, Object> params);
    
    /**
     * 같이쇼핑 전체 개수 (필터 포함)
     * @param params si, gu, status
     * @return 전체 개수
     */
    int selectTotalCount(Map<String, Object> params);
    
    /**
     * 같이쇼핑 검색 (키워드 + 지역 필터, 페이징)
     * @param params keyword, si, gu, offset, limit
     * @return 검색 결과 목록
     */
    List<WithShoppingVO> searchByKeyword(Map<String, Object> params);
    
    /**
     * 검색 결과 전체 개수
     * @param params keyword, si, gu
     * @return 검색 결과 개수
     */
    int selectSearchCount(Map<String, Object> params);
    
    /**
     * 같이쇼핑 상세 조회
     * @param withShoppingId 같이쇼핑 ID
     * @return 같이쇼핑 정보
     */
    WithShoppingVO selectById(int withShoppingId);
    
    /**
     * 같이쇼핑 작성
     * @param withShopping 같이쇼핑 정보
     * @return 작성된 행 수
     */
    int insertWithShopping(WithShoppingVO withShopping);
    
    /**
     * 같이쇼핑 수정
     * @param withShopping 같이쇼핑 정보
     * @return 수정된 행 수
     */
    int updateWithShopping(WithShoppingVO withShopping);
    
    /**
     * 같이쇼핑 삭제
     * @param withShoppingId 같이쇼핑 ID
     * @return 삭제된 행 수
     */
    int deleteWithShopping(int withShoppingId);
    
    /**
     * 작성자 확인
     * @param params withShoppingId, userId
     * @return 일치 개수 (1: 작성자, 0: 아님)
     */
    int checkAuthor(Map<String, Object> params);
    
    /**
     * 모집 완료 처리 (채팅 수락 시 호출)
     * @param withShoppingId 같이쇼핑 ID
     * @return 수정된 행 수
     */
    int updateStatusComplete(int withShoppingId);
}
