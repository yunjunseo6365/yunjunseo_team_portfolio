package com.moc.pro.file.service;

import com.moc.pro.file.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 공통 서비스 인터페이스
 * User, Recipe, ShareTool 등 모든 이미지 업로드에 공통 사용
 */
public interface FileUploadService {
    
    /**
     * 이미지 업로드
     * @param file 업로드할 파일
     * @param type 이미지 타입 (user, recipe, sharetool 등)
     * @return ImageVO (url, path 포함)
     * @throws IllegalArgumentException 잘못된 파일인 경우
     * @throws IllegalStateException 파일 업로드 실패 시
     */
    ImageVO uploadImage(MultipartFile file, String type);
    
    /**
     * 이미지 삭제
     * @param path 삭제할 파일 경로 (절대 경로)
     * @return 삭제 성공 여부
     */
    boolean deleteImage(String path);
    
    /**
     * 이미지 검증 (크기, 확장자, MIME 타입)
     * @param file 검증할 파일
     * @return 검증 성공 여부
     */
    boolean validateImage(MultipartFile file);
    
    /**
     * 사용자 프로필 기본 이미지 URL 반환
     * @return 기본 프로필 이미지 URL
     */
    String getDefaultProfileImageUrl();
    
    /**
     * 공통 이미지 없음 URL 반환 (Placeholder - 동적 크기)
     * @param width 이미지 너비 (픽셀)
     * @param height 이미지 높이 (픽셀)
     * @return No Image URL
     */
    String getNoImageUrl(int width, int height);
}
