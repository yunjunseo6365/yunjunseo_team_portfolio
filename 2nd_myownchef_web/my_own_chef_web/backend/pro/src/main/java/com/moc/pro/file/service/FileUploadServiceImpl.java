package com.moc.pro.file.service;

import com.moc.pro.file.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 파일 업로드 공통 서비스 구현체
 * User, Recipe, ShareTool 등 모든 이미지 업로드에 공통 사용
 */
@Service
public class FileUploadServiceImpl implements FileUploadService {
    
    // 업로드 디렉토리 경로
    private static final String UPLOAD_DIR = "C:/uploads/moc/";
    
    // 기본 이미지 URL
    private static final String DEFAULT_PROFILE_IMAGE_URL = "http://localhost:18880/images/default/default-profile.png";
    
    // 공통 이미지 없음 URL (Placeholder - 베이스 URL)
    private static final String NO_IMAGE_BASE_URL = "https://placehold.co/";
    
    // 최대 파일 크기 (10MB)
    private static final long MAX_FILE_SIZE = 10L * 1024 * 1024;
    
    // 허용된 MIME 타입
    private static final String[] ALLOWED_TYPES = {
        "image/jpeg",
        "image/jpg",
        "image/png",
        "image/gif",
        "image/webp"
    };
    
    /**
     * 이미지 업로드
     * @param file 업로드할 파일
     * @param type 이미지 타입 (user, recipe, sharetool 등)
     * @return ImageVO (url, path 포함)
     * @throws IllegalArgumentException 잘못된 파일인 경우
     * @throws IllegalStateException 파일 업로드 실패 시
     */
    @Override
    public ImageVO uploadImage(MultipartFile file, String type) {
        // 1. 검증
        if (!validateImage(file)) {
            throw new IllegalArgumentException("잘못된 이미지 파일입니다.");
        }
        
        // 2. 파일명 생성 (UUID + 원본 확장자)
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new IllegalArgumentException("파일명이 없습니다.");
        }
        
        String extension = "";
        int dotIndex = originalFilename.lastIndexOf(".");
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }
        
        String filename = UUID.randomUUID().toString() + extension;
        
        // 3. 경로 생성 (타입별 폴더 분리)
        String relativePath = type + File.separator + filename;
        String fullPath = UPLOAD_DIR + relativePath;
        
        try {
            // 4. 디렉토리 생성 (없으면)
            Path dirPath = Paths.get(UPLOAD_DIR + type);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            
            // 5. 파일 저장
            file.transferTo(new File(fullPath));
            
            // 6. URL 생성 (Static Resource로 접근)
            String url = "http://localhost:18880/uploads/" + type + "/" + filename;
            
            return new ImageVO(url, fullPath);
            
        } catch (IOException e) {
            throw new IllegalStateException("파일 업로드 실패: " + e.getMessage(), e);
        }
    }
    
    /**
     * 이미지 삭제
     * @param path 삭제할 파일 경로 (절대 경로)
     * @return 삭제 성공 여부
     */
    @Override
    public boolean deleteImage(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }
        
        try {
            Path filePath = Paths.get(path);
            if (Files.exists(filePath) && Files.isRegularFile(filePath)) {
                Files.delete(filePath);
                return true;
            }
            return false;
        } catch (IOException e) {
            return false;
        }
    }
    
    /**
     * 이미지 검증 (크기, 확장자, MIME 타입)
     * @param file 검증할 파일
     * @return 검증 성공 여부
     */
    @Override
    public boolean validateImage(MultipartFile file) {
        // 1. 파일 존재 확인
        if (file == null || file.isEmpty()) {
            return false;
        }
        
        // 2. 파일 크기 확인
        if (file.getSize() > MAX_FILE_SIZE) {
            return false;
        }
        
        // 3. MIME 타입 확인
        String contentType = file.getContentType();
        if (contentType == null) {
            return false;
        }
        
        for (String allowedType : ALLOWED_TYPES) {
            if (allowedType.equals(contentType)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 사용자 프로필 기본 이미지 URL 반환
     * @return 기본 프로필 이미지 URL
     */
    @Override
    public String getDefaultProfileImageUrl() {
        return DEFAULT_PROFILE_IMAGE_URL;
    }
    
    /**
     * 공통 이미지 없음 URL 반환 (Placeholder - 동적 크기)
     * @param width 이미지 너비 (픽셀)
     * @param height 이미지 높이 (픽셀)
     * @return No Image URL
     */
    @Override
    public String getNoImageUrl(int width, int height) {
        return NO_IMAGE_BASE_URL + width + "x" + height + "/png?text=No+Image";
    }
}
