package com.moc.pro.sharetool.service;

import com.moc.pro.sharetool.vo.ShareToolVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 요리도구 나눔 Service 인터페이스
 */
public interface ShareToolService {
    
    // ===== 요리도구 나눔 게시글 =====
    
    /**
     * 요리도구 나눔 목록 조회 (지역 + 상태 필터, 페이징)
     * @param province 시/도
     * @param city 시/군/구
     * @param status 나눔 상태
     * @param page 페이지 번호
     * @return { posts, totalPage, currentPage, totalCount }
     */
    Map<String, Object> getList(String province, String city, String status, int page);
    
    /**
     * 요리도구 나눔 검색 (키워드 + 지역 필터, 페이징)
     * @param keyword 검색 키워드
     * @param province 시/도
     * @param city 시/군/구
     * @param page 페이지 번호
     * @return { posts, totalPage, currentPage, totalCount, keyword }
     */
    Map<String, Object> searchPosts(String keyword, String province, String city, int page);
    
    /**
     * 요리도구 나눔 상세 조회 (이미지 포함)
     * @param shareToolId 요리도구 나눔 ID
     * @return { shareTool, images }
     */
    Map<String, Object> getDetail(int shareToolId);
    
    /**
     * 요리도구 나눔 작성 (이미지 포함)
     * @param shareTool 요리도구 나눔 정보
     * @param images 이미지 파일 배열
     * @return 작성 성공 여부
     */
    boolean createShareTool(ShareToolVO shareTool, MultipartFile[] images);
    
    /**
     * 요리도구 나눔 수정 (이미지 포함)
     * @param shareTool 요리도구 나눔 정보
     * @param images 이미지 파일 배열
     * @param existingImageUrls 유지할 기존 이미지 URL (JSON)
     * @return 수정 성공 여부
     */
    boolean updateShareTool(ShareToolVO shareTool, MultipartFile[] images, String existingImageUrls);
    
    /**
     * 요리도구 나눔 삭제 (이미지 포함)
     * @param shareToolId 요리도구 나눔 ID
     * @return 삭제 성공 여부
     */
    boolean deleteShareTool(int shareToolId);
    
    /**
     * 작성자 확인
     * @param shareToolId 요리도구 나눔 ID
     * @param userId 사용자 ID
     * @return 작성자 여부
     */
    boolean checkAuthor(int shareToolId, String userId);
    
    /**
     * 나눔 완료 처리 (채팅 수락 시 호출)
     * @param shareToolId 요리도구 나눔 ID
     * @return 완료 처리 성공 여부
     */
    boolean completeShare(int shareToolId);
}
