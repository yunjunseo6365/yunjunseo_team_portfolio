package com.moc.pro.convreview.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moc.pro.convreview.dao.ConvReviewDAO;
import com.moc.pro.convreview.vo.ConvReviewVO;
import com.moc.pro.convreview.vo.ConvReviewCommentVO;
import com.moc.pro.convreview.vo.ConvReviewImageVO;
import com.moc.pro.file.service.FileUploadService;
import com.moc.pro.file.vo.ImageVO;
import com.moc.pro.notification.service.NotificationService;

import com.moc.pro.recipe.vo.RecipeImageVO;
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
 * 편의점 리뷰 Service 구현체
 */
@Service
public class ConvReviewServiceImpl implements ConvReviewService {
    
    @Autowired
    private ConvReviewDAO convReviewDAO;
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final int ITEMS_PER_PAGE = 10; // 페이지당 게시글 수
    
    // ===== 편의점 리뷰 게시글 =====
    
    @Override
    public Map<String, Object> getList(String store, String category, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        params.put("store", store);
        params.put("category", category);
        
        // 목록 조회
        List<ConvReviewVO> posts = convReviewDAO.selectList(params);
        
        // 각 게시글의 대표 이미지 설정
        for (ConvReviewVO convReview : posts) {
            List<ConvReviewImageVO> images = convReviewDAO.selectImagesByConvReviewId(convReview.getConvReviewId());
            if (!images.isEmpty()) {
                // 첫 번째 이미지를 대표 이미지로 설정
                convReview.setFirstImageUrl(images.get(0).getConvReviewImageUrl());
            } else {
                // 이미지 없음 (카드용 250x250)
                convReview.setFirstImageUrl(fileUploadService.getNoImageUrl(250, 250));
            }
        }
        
        // 전체 페이지 수 계산
        Map<String, Object> countParams = new HashMap<>();
        countParams.put("store", store);
        countParams.put("category", category);
        int totalCount = convReviewDAO.selectTotalCount(countParams);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getDetail(int convReviewId) {
        Map<String, Object> result = new HashMap<>();
        
        // 편의점 리뷰 기본 정보
        ConvReviewVO convReview = convReviewDAO.selectById(convReviewId);
        
        // 이미지 목록
        List<ConvReviewImageVO> images = convReviewDAO.selectImagesByConvReviewId(convReviewId);
        if (images.isEmpty()) {
            // 이미지 없으면 기본 이미지 추가 (상세 페이지용)
            ConvReviewImageVO noImage = new ConvReviewImageVO();
            noImage.setConvReviewImageUrl(fileUploadService.getNoImageUrl(1000, 800));
            images.add(noImage);
        }
        result.put("convReview", convReview);
        result.put("images", images);
        
        return result;
    }
    
    @Override
    @Transactional
    public boolean createConvReview(ConvReviewVO convReview, MultipartFile[] images) {
        try {
            // 1. 편의점 리뷰 기본 정보 저장
            convReviewDAO.insertConvReview(convReview);
            int convReviewId = convReview.getConvReviewId(); // useGeneratedKeys로 자동 설정됨
            
            // 2. 이미지 파일 업로드 및 저장
            if (images != null && images.length > 0) {
                int index = 0;
                for (MultipartFile file : images) {
                    if (file != null && !file.isEmpty()) {
                        // FileUploadService를 통한 이미지 업로드
                        ImageVO uploadResult = fileUploadService.uploadImage(file, "conv-review");
                        
                        // 이미지 정보 DB 저장
                        ConvReviewImageVO imageVO = new ConvReviewImageVO();
                        imageVO.setConvReviewId(convReviewId);
                        imageVO.setConvReviewImageUrl(uploadResult.getUrl());
                        imageVO.setConvReviewImagePath(uploadResult.getPath());
                        imageVO.setConvReviewImageIndex(index);
                        
                        convReviewDAO.insertImage(imageVO);
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
    public boolean updateConvReview(ConvReviewVO convReview, MultipartFile[] images, String existingImageUrls) {
        try {
            int convReviewId = convReview.getConvReviewId();
            
            // 1. 편의점 리뷰 기본 정보 수정
            convReviewDAO.updateConvReview(convReview);
            
            // 2. 이미지 선택적 삭제/유지 처리
            // 2-1. 유지할 이미지 URL 파싱
            Set<String> keepUrls = new HashSet<>();
            if (existingImageUrls != null && !existingImageUrls.isEmpty()) {
                List<String> urlList = objectMapper.readValue(existingImageUrls, 
                    new TypeReference<List<String>>() {});
                keepUrls.addAll(urlList);
            }
            
            // 2-2. 기존 이미지 조회
            List<ConvReviewImageVO> oldImages = convReviewDAO.selectImagesByConvReviewId(convReviewId);
            
            // 2-3. 삭제할 이미지만 삭제
            for (ConvReviewImageVO img : oldImages) {
                if (!keepUrls.contains(img.getConvReviewImageUrl())) {
                    // 파일 삭제
                    try {
                        fileUploadService.deleteImage(img.getConvReviewImagePath());
                    } catch (Exception e) {
                        System.err.println("이미지 파일 삭제 실패: " + img.getConvReviewImagePath());
                    }
                    // DB 삭제
                    convReviewDAO.deleteImageById(img.getConvReviewImageId());
                }
            }
            
            // 2-4. 새 이미지 추가
            if (images != null && images.length > 0) {
                // 기존 유지된 이미지 개수 확인
                int existingCount = (int) oldImages.stream()
                    .filter(img -> keepUrls.contains(img.getConvReviewImageUrl()))
                    .count();
                
                int imageIndex = existingCount;
                
                for (MultipartFile file : images) {
                    if (file != null && !file.isEmpty()) {
                        ImageVO uploadResult = fileUploadService.uploadImage(file, "conv-review");
                        
                        ConvReviewImageVO imageVO = new ConvReviewImageVO();
                        imageVO.setConvReviewId(convReviewId);
                        imageVO.setConvReviewImageUrl(uploadResult.getUrl());
                        imageVO.setConvReviewImagePath(uploadResult.getPath());
                        imageVO.setConvReviewImageIndex(imageIndex++);
                        
                        convReviewDAO.insertImage(imageVO);
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
    public boolean deleteConvReview(int convReviewId) {
        try {
            // 1. 이미지 파일 삭제
            List<ConvReviewImageVO> images = convReviewDAO.selectImagesByConvReviewId(convReviewId);
            for (ConvReviewImageVO image : images) {
                fileUploadService.deleteImage(image.getConvReviewImagePath());
            }
            
            // 2. 이미지 DB 삭제
            convReviewDAO.deleteImagesByConvReviewId(convReviewId);
            
            // 3. 편의점 리뷰 삭제
            convReviewDAO.deleteConvReview(convReviewId);
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean checkAuthor(int convReviewId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("convReviewId", convReviewId);
        params.put("userId", userId);
        
        return convReviewDAO.checkAuthor(params) > 0;
    }
    
    // ===== 편의점 리뷰 댓글 =====
    
    @Override
    public List<ConvReviewCommentVO> getComments(int convReviewId) {
        return convReviewDAO.selectCommentsByConvReviewId(convReviewId);
    }
    
    @Autowired
    private NotificationService notificationService;
    
    @Override
    @Transactional
    public boolean createComment(ConvReviewCommentVO comment) {
        try {
            // 1. 댓글 저장
            int result = convReviewDAO.insertComment(comment);
            if (result == 0) {
                return false;
            }
            
            // 2. 게시글 작성자 조회
            ConvReviewVO convReview = convReviewDAO.selectById(comment.getConvReviewId());
            if (convReview != null) {
                // 3. 알림 생성 (본인 댓글은 제외)
                notificationService.createCommentNotification(
                    comment.getConvReviewId(),
                    "conv-review",
                    convReview.getUserId(),
                    comment.getUserId(),
                    comment.getConvReviewCommentId()
                );
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteComment(int commentId, String userId) {
        try {
            // 작성자 확인
            Map<String, Object> params = new HashMap<>();
            params.put("commentId", commentId);
            params.put("userId", userId);
            
            if (convReviewDAO.checkCommentAuthor(params) > 0) {
                return convReviewDAO.deleteComment(commentId) > 0;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
