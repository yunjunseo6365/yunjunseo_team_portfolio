package com.moc.pro.withshopping.service;

import com.moc.pro.withshopping.vo.WithShoppingVO;

import java.util.Map;

/**
 * 같이쇼핑 Service 인터페이스
 */
public interface WithShoppingService {
    
    /**
     * 같이쇼핑 목록 조회 (지역 + 상태 필터, 페이징)
     * @param si 시/도
     * @param gu 시/군/구
     * @param status 모집 상태
     * @param page 페이지 번호
     * @return { posts, totalPage, currentPage, totalCount }
     */
    Map<String, Object> getList(String si, String gu, String status, int page);
    
    /**
     * 같이쇼핑 검색 (키워드 + 지역 필터, 페이징)
     * @param keyword 검색 키워드
     * @param si 시/도
     * @param gu 시/군/구
     * @param page 페이지 번호
     * @return { posts, totalPage, currentPage, totalCount, keyword }
     */
    Map<String, Object> searchPosts(String keyword, String si, String gu, int page);
    
    /**
     * 같이쇼핑 상세 조회
     * @param withShoppingId 같이쇼핑 ID
     * @return 같이쇼핑 정보
     */
    WithShoppingVO getDetail(int withShoppingId);
    
    /**
     * 같이쇼핑 작성
     * @param withShopping 같이쇼핑 정보
     * @return 작성 성공 여부
     */
    boolean createWithShopping(WithShoppingVO withShopping);
    
    /**
     * 같이쇼핑 수정
     * @param withShopping 같이쇼핑 정보
     * @return 수정 성공 여부
     */
    boolean updateWithShopping(WithShoppingVO withShopping);
    
    /**
     * 같이쇼핑 삭제
     * @param withShoppingId 같이쇼핑 ID
     * @return 삭제 성공 여부
     */
    boolean deleteWithShopping(int withShoppingId);
    
    /**
     * 작성자 확인
     * @param withShoppingId 같이쇼핑 ID
     * @param userId 사용자 ID
     * @return 작성자 여부
     */
    boolean checkAuthor(int withShoppingId, String userId);
    
    /**
     * 모집 완료 처리 (채팅 수락 시 호출)
     * @param withShoppingId 같이쇼핑 ID
     * @return 완료 처리 성공 여부
     */
    boolean completeShopping(int withShoppingId);
}
