package com.moc.pro.sharetool.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moc.pro.sharetool.dao.ShareToolDAO;
import com.moc.pro.sharetool.vo.ShareToolVO;
import com.moc.pro.sharetool.vo.ShareToolImageVO;
import com.moc.pro.file.service.FileUploadService;
import com.moc.pro.file.vo.ImageVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 요리도구 나눔 Service 구현체
 */
@Service
public class ShareToolServiceImpl implements ShareToolService {
    
    @Autowired
    private ShareToolDAO shareToolDAO;
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final int ITEMS_PER_PAGE = 10; // 페이지당 게시글 수
    
    // ===== 요리도구 나눔 게시글 =====
    
    @Override
    public Map<String, Object> getList(String province, String city, String status, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        params.put("province", province);
        params.put("city", city);
        params.put("status", status);
        
        // 목록 조회
        List<ShareToolVO> posts = shareToolDAO.selectList(params);
        
        // 각 게시글의 대표 이미지 설정
        for (ShareToolVO shareTool : posts) {
            List<ShareToolImageVO> images = shareToolDAO.selectImagesByShareToolId(shareTool.getShareToolId());
            if (!images.isEmpty()) {
                // 첫 번째 이미지를 대표 이미지로 설정
                shareTool.setShareToolContent(images.get(0).getShareToolImageUrl());
            } else {
                // 이미지 없음 (카드용 250x250)
                shareTool.setShareToolContent(fileUploadService.getNoImageUrl(250, 250));
            }
        }
        
        // 전체 페이지 수 계산
        Map<String, Object> countParams = new HashMap<>();
        countParams.put("province", province);
        countParams.put("city", city);
        countParams.put("status", status);
        int totalCount = shareToolDAO.selectTotalCount(countParams);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> searchPosts(String keyword, String province, String city, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("province", province);
        params.put("city", city);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 검색 실행
        List<ShareToolVO> posts = shareToolDAO.searchByKeyword(params);
        
        // 각 게시글의 대표 이미지 설정
        for (ShareToolVO shareTool : posts) {
            List<ShareToolImageVO> images = shareToolDAO.selectImagesByShareToolId(shareTool.getShareToolId());
            if (!images.isEmpty()) {
                shareTool.setShareToolContent(images.get(0).getShareToolImageUrl());
            } else {
                shareTool.setShareToolContent(fileUploadService.getNoImageUrl(250, 250));
            }
        }
        
        // 검색 결과 전체 페이지 수 계산
        Map<String, Object> countParams = new HashMap<>();
        countParams.put("keyword", keyword);
        countParams.put("province", province);
        countParams.put("city", city);
        int totalCount = shareToolDAO.selectSearchCount(countParams);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        result.put("keyword", keyword);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getDetail(int shareToolId) {
        Map<String, Object> result = new HashMap<>();
        
        // 요리도구 나눔 기본 정보
        ShareToolVO shareTool = shareToolDAO.selectById(shareToolId);
        
        // 이미지 목록
        List<ShareToolImageVO> images = shareToolDAO.selectImagesByShareToolId(shareToolId);
        
        // 이미지 없으면 기본 이미지 설정 (상세용 1000x800)
        if (images.isEmpty()) {
            ShareToolImageVO defaultImage = new ShareToolImageVO();
            defaultImage.setShareToolImageUrl(fileUploadService.getNoImageUrl(1000, 800));
            images.add(defaultImage);
        }
        
        result.put("shareTool", shareTool);
        result.put("images", images);
        
        return result;
    }
    
    @Override
    @Transactional
    public boolean createShareTool(ShareToolVO shareTool, MultipartFile[] images) {
        try {
            // 1. 요리도구 나눔 기본 정보 저장
            shareToolDAO.insertShareTool(shareTool);
            int shareToolId = shareTool.getShareToolId(); // useGeneratedKeys로 자동 설정됨
            
            // 2. 이미지 파일 업로드 및 저장
            if (images != null && images.length > 0) {
                int index = 1; // 이미지 인덱스는 1부터 시작
                for (MultipartFile file : images) {
                    if (file != null && !file.isEmpty()) {
                        // FileUploadService를 통한 이미지 업로드
                        ImageVO uploadResult = fileUploadService.uploadImage(file, "sharetool");
                        
                        // 이미지 정보 DB 저장
                        ShareToolImageVO imageVO = new ShareToolImageVO();
                        imageVO.setShareToolId(shareToolId);
                        imageVO.setShareToolImageUrl(uploadResult.getUrl());
                        imageVO.setShareToolImagePath(uploadResult.getPath());
                        imageVO.setShareToolImageIndex(index);
                        imageVO.setCreatedBy(shareTool.getUserId());
                        
                        shareToolDAO.insertImage(imageVO);
                        index++;
                    }
                }
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean updateShareTool(ShareToolVO shareTool, MultipartFile[] images, String existingImageUrls) {
        try {
            int shareToolId = shareTool.getShareToolId();
            
            // 1. 요리도구 나눔 기본 정보 수정
            shareToolDAO.updateShareTool(shareTool);
            
            // 2. 이미지 선택적 삭제/유지 처리
            // 2-1. 유지할 이미지 URL 파싱
            Set<String> keepUrls = new HashSet<>();
            if (existingImageUrls != null && !existingImageUrls.isEmpty()) {
                List<String> urlList = objectMapper.readValue(existingImageUrls, 
                    new TypeReference<List<String>>() {});
                keepUrls.addAll(urlList);
            }
            
            // 2-2. 기존 이미지 조회
            List<ShareToolImageVO> oldImages = shareToolDAO.selectImagesByShareToolId(shareToolId);
            
            // 2-3. 삭제할 이미지만 삭제
            for (ShareToolImageVO img : oldImages) {
                if (!keepUrls.contains(img.getShareToolImageUrl())) {
                    // 파일 삭제
                    try {
                        fileUploadService.deleteImage(img.getShareToolImagePath());
                    } catch (Exception e) {
                        System.err.println("이미지 파일 삭제 실패: " + img.getShareToolImagePath());
                    }
                    // DB 삭제
                    shareToolDAO.deleteImageById(img.getShareToolImageId());
                }
            }
            
            // 2-4. 새 이미지 추가
            if (images != null && images.length > 0) {
                // 기존 유지된 이미지 개수 확인
                int existingCount = (int) oldImages.stream()
                    .filter(img -> keepUrls.contains(img.getShareToolImageUrl()))
                    .count();
                
                int imageIndex = existingCount + 1;
                
                for (MultipartFile file : images) {
                    if (file != null && !file.isEmpty()) {
                        ImageVO uploadResult = fileUploadService.uploadImage(file, "sharetool");
                        
                        ShareToolImageVO imageVO = new ShareToolImageVO();
                        imageVO.setShareToolId(shareToolId);
                        imageVO.setShareToolImageUrl(uploadResult.getUrl());
                        imageVO.setShareToolImagePath(uploadResult.getPath());
                        imageVO.setShareToolImageIndex(imageIndex++);
                        imageVO.setCreatedBy(shareTool.getUserId());
                        
                        shareToolDAO.insertImage(imageVO);
                    }
                }
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean deleteShareTool(int shareToolId) {
        try {
            // 1. 이미지 파일 삭제
            List<ShareToolImageVO> images = shareToolDAO.selectImagesByShareToolId(shareToolId);
            for (ShareToolImageVO image : images) {
                fileUploadService.deleteImage(image.getShareToolImagePath());
            }
            
            // 2. 이미지 DB 삭제
            shareToolDAO.deleteImagesByShareToolId(shareToolId);
            
            // 3. 게시글 삭제
            shareToolDAO.deleteShareTool(shareToolId);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean checkAuthor(int shareToolId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("shareToolId", shareToolId);
        params.put("userId", userId);
        
        int count = shareToolDAO.checkAuthor(params);
        return count > 0;
    }
    
    @Override
    @Transactional
    public boolean completeShare(int shareToolId) {
        try {
            shareToolDAO.updateStatusComplete(shareToolId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
