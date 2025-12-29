package com.moc.pro.convrecipe.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moc.pro.convrecipe.dao.ConvRecipeDAO;
import com.moc.pro.convrecipe.vo.*;
import com.moc.pro.convreview.vo.ConvReviewImageVO;
import com.moc.pro.file.service.FileUploadService;
import com.moc.pro.file.vo.ImageVO;
import com.moc.pro.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

@Service
public class ConvRecipeServiceImpl implements ConvRecipeService {
    
    @Autowired
    private ConvRecipeDAO convRecipeDAO;
    
    @Autowired
    private FileUploadService fileUploadService;
    
    private static final int ITEMS_PER_PAGE = 10;
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public Map<String, Object> getList(String category, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("category", category);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 목록 조회
        List<ConvRecipeVO> posts = convRecipeDAO.selectList(params);
        
        // 각 게시글의 대표 이미지 설정
        for (ConvRecipeVO recipe : posts) {
            List<ConvRecipeImageVO> images = convRecipeDAO.selectImagesByRecipeId(recipe.getConvRecipeId());
            if (!images.isEmpty()) {
                // 첫 번째 이미지를 대표 이미지로 설정
                recipe.setFirstImageUrl(images.get(0).getConvRecipeImageUrl());
            } else {
                // 이미지 없음 (카드용 250x250)
                recipe.setFirstImageUrl(fileUploadService.getNoImageUrl(250, 250));
            }
        }
        
        // 전체 페이지 수 계산
        int totalCount = convRecipeDAO.selectTotalCount(params);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> searchRecipes(String keyword, String product, String category, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("product", product);
        params.put("category", category);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 검색 실행
        List<ConvRecipeVO> posts = convRecipeDAO.searchByKeyword(params);
        
        // 각 게시글의 대표 이미지 설정
        for (ConvRecipeVO recipe : posts) {
            List<ConvRecipeImageVO> images = convRecipeDAO.selectImagesByRecipeId(recipe.getConvRecipeId());
            if (!images.isEmpty()) {
                // 첫 번째 이미지를 대표 이미지로 설정
                recipe.setFirstImageUrl(images.get(0).getConvRecipeImageUrl());
            } else {
                // 이미지 없음 (카드용 250x250)
                recipe.setFirstImageUrl(fileUploadService.getNoImageUrl(250, 250));
            }
        }
        
        // 검색 결과 전체 페이지 수 계산
        int totalCount = convRecipeDAO.selectSearchCount(params);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        result.put("keyword", keyword);
        result.put("product", product);
        result.put("category", category);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getDetail(int convRecipeId) {
        Map<String, Object> result = new HashMap<>();
        
        // 기본 정보 조회
        ConvRecipeVO recipe = convRecipeDAO.selectById(convRecipeId);
        
        // 제품 목록 조회
        List<ConvRecipeProductVO> products = convRecipeDAO.selectProductsByRecipeId(convRecipeId);
        
        // 조리 순서 조회
        List<ConvRecipeOrderVO> orders = convRecipeDAO.selectOrdersByRecipeId(convRecipeId);
        
        // 이미지 목록 조회
        List<ConvRecipeImageVO> images = convRecipeDAO.selectImagesByRecipeId(convRecipeId);
        if (images.isEmpty()) {
            // 이미지 없으면 기본 이미지 추가 (상세 페이지용)
            ConvRecipeImageVO noImage = new ConvRecipeImageVO();
            noImage.setConvRecipeImageUrl(fileUploadService.getNoImageUrl(1000, 800));
            images.add(noImage);
        }

        result.put("recipe", recipe);
        result.put("products", products);
        result.put("orders", orders);
        result.put("images", images);
        
        return result;
    }
    
    @Override
    @Transactional
    public boolean createConvRecipe(ConvRecipeVO convRecipe, String productsJson, String ordersJson, MultipartFile[] images) {
        try {
            // 1. 편의점 조합 기본 정보 저장
            convRecipeDAO.insertConvRecipe(convRecipe);
            int recipeId = convRecipe.getConvRecipeId(); // useGeneratedKeys 사용
            
            // 2. 제품 목록 저장
            if (productsJson != null && !productsJson.isEmpty()) {
                List<Map<String, String>> productList = objectMapper.readValue(productsJson, 
                    new TypeReference<List<Map<String, String>>>() {});
                
                int productIndex = 1;
                for (Map<String, String> item : productList) {
                    ConvRecipeProductVO product = new ConvRecipeProductVO();
                    product.setConvRecipeId(recipeId);
                    product.setConvRecipeProductContent(item.get("content"));
                    product.setConvRecipeProductIndex(productIndex++);
                    
                    convRecipeDAO.insertProduct(product);
                }
            }
            
            // 3. 조리 순서 저장
            if (ordersJson != null && !ordersJson.isEmpty()) {
                List<Map<String, String>> orderList = objectMapper.readValue(ordersJson, 
                    new TypeReference<List<Map<String, String>>>() {});
                
                int orderIndex = 1;
                for (Map<String, String> item : orderList) {
                    ConvRecipeOrderVO order = new ConvRecipeOrderVO();
                    order.setConvRecipeId(recipeId);
                    order.setConvRecipeOrderContent(item.get("content"));
                    order.setConvRecipeOrderIndex(orderIndex++);
                    
                    convRecipeDAO.insertOrder(order);
                }
            }
            
            // 4. 이미지 파일 업로드 및 저장
            if (images != null && images.length > 0) {
                int imageIndex = 1;
                for (MultipartFile file : images) {
                    if (file.isEmpty()) {
                        continue;
                    }
                    
                    // 파일 업로드
                    ImageVO uploadResult = fileUploadService.uploadImage(file, "conv-recipe");
                    
                    // DB 저장
                    ConvRecipeImageVO image = new ConvRecipeImageVO();
                    image.setConvRecipeId(recipeId);
                    image.setConvRecipeImageUrl(uploadResult.getUrl());
                    image.setConvRecipeImagePath(uploadResult.getPath());
                    image.setConvRecipeImageIndex(imageIndex++);
                    
                    convRecipeDAO.insertImage(image);
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
    public boolean updateConvRecipe(ConvRecipeVO convRecipe, String productsJson, String ordersJson, MultipartFile[] images, String existingImageUrls) {
        try {
            int recipeId = convRecipe.getConvRecipeId();
            
            // 1. 기본 정보 수정
            convRecipeDAO.updateConvRecipe(convRecipe);
            
            // 2. 기존 제품 삭제 후 재저장
            convRecipeDAO.deleteProductsByRecipeId(recipeId);
            if (productsJson != null && !productsJson.isEmpty()) {
                List<Map<String, String>> productList = objectMapper.readValue(productsJson, 
                    new TypeReference<List<Map<String, String>>>() {});
                
                int productIndex = 1;
                for (Map<String, String> item : productList) {
                    ConvRecipeProductVO product = new ConvRecipeProductVO();
                    product.setConvRecipeId(recipeId);
                    product.setConvRecipeProductContent(item.get("content"));
                    product.setConvRecipeProductIndex(productIndex++);
                    
                    convRecipeDAO.insertProduct(product);
                }
            }
            
            // 3. 기존 조리 순서 삭제 후 재저장
            convRecipeDAO.deleteOrdersByRecipeId(recipeId);
            if (ordersJson != null && !ordersJson.isEmpty()) {
                List<Map<String, String>> orderList = objectMapper.readValue(ordersJson, 
                    new TypeReference<List<Map<String, String>>>() {});
                
                int orderIndex = 1;
                for (Map<String, String> item : orderList) {
                    ConvRecipeOrderVO order = new ConvRecipeOrderVO();
                    order.setConvRecipeId(recipeId);
                    order.setConvRecipeOrderContent(item.get("content"));
                    order.setConvRecipeOrderIndex(orderIndex++);
                    
                    convRecipeDAO.insertOrder(order);
                }
            }
            
            // 4. 이미지 선택적 삭제/유지 처리
            // 4-1. 유지할 이미지 URL 파싱
            Set<String> keepUrls = new HashSet<>();
            if (existingImageUrls != null && !existingImageUrls.isEmpty()) {
                List<String> urlList = objectMapper.readValue(existingImageUrls, 
                    new TypeReference<List<String>>() {});
                keepUrls.addAll(urlList);
            }
            
            // 4-2. 기존 이미지 조회
            List<ConvRecipeImageVO> oldImages = convRecipeDAO.selectImagesByRecipeId(recipeId);
            
            // 4-3. 삭제할 이미지만 삭제
            for (ConvRecipeImageVO img : oldImages) {
                if (!keepUrls.contains(img.getConvRecipeImageUrl())) {
                    // 파일 삭제
                    try {
                        fileUploadService.deleteImage(img.getConvRecipeImagePath());
                    } catch (Exception e) {
                        // 파일 삭제 실패 시에도 DB는 삭제
                        System.err.println("이미지 파일 삭제 실패: " + img.getConvRecipeImagePath());
                    }
                    // DB 삭제
                    convRecipeDAO.deleteImageById(img.getConvRecipeImageId());
                }
            }
            
            // 4-4. 새 이미지 추가
            if (images != null && images.length > 0) {
                // 기존 유지된 이미지 개수 확인
                int existingCount = (int) oldImages.stream()
                    .filter(img -> keepUrls.contains(img.getConvRecipeImageUrl()))
                    .count();
                
                int imageIndex = existingCount + 1;
                
                for (MultipartFile file : images) {
                    if (file.isEmpty()) {
                        continue;
                    }
                    
                    ImageVO uploadResult = fileUploadService.uploadImage(file, "conv-recipe");
                    
                    ConvRecipeImageVO image = new ConvRecipeImageVO();
                    image.setConvRecipeId(recipeId);
                    image.setConvRecipeImageUrl(uploadResult.getUrl());
                    image.setConvRecipeImagePath(uploadResult.getPath());
                    image.setConvRecipeImageIndex(imageIndex++);
                    
                    convRecipeDAO.insertImage(image);
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
    public boolean deleteConvRecipe(int convRecipeId) {
        try {
            // 1. 이미지 파일 삭제
            List<ConvRecipeImageVO> images = convRecipeDAO.selectImagesByRecipeId(convRecipeId);
            for (ConvRecipeImageVO img : images) {
                fileUploadService.deleteImage(img.getConvRecipeImagePath());
            }
            
            // 2. DB에서 삭제 (CASCADE로 연관 데이터 자동 삭제)
            convRecipeDAO.deleteConvRecipe(convRecipeId);
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean checkAuthor(int convRecipeId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("convRecipeId", convRecipeId);
        params.put("userId", userId);
        
        int count = convRecipeDAO.checkAuthor(params);
        return count > 0;
    }
    
    @Override
    public List<ConvRecipeCommentVO> getComments(int convRecipeId) {
        return convRecipeDAO.selectCommentsByRecipeId(convRecipeId);
    }
    
    @Autowired
    private NotificationService notificationService;
    
    @Override
    @Transactional
    public boolean createComment(ConvRecipeCommentVO comment) {
        // 1. 댓글 저장
        int result = convRecipeDAO.insertComment(comment);
        if (result == 0) {
            return false;
        }
        
        // 2. 게시글 작성자 조회
        ConvRecipeVO convRecipe = convRecipeDAO.selectById(comment.getConvRecipeId());
        if (convRecipe != null) {
            // 3. 알림 생성 (본인 댓글은 제외)
            notificationService.createCommentNotification(
                comment.getConvRecipeId(),
                "conv-recipe",
                convRecipe.getUserId(),
                comment.getUserId(),
                comment.getConvRecipeCommentId()
            );
        }
        
        return true;
    }
    
    @Override
    public boolean deleteComment(int commentId, String userId) {
        // 작성자 확인
        Map<String, Object> params = new HashMap<>();
        params.put("commentId", commentId);
        params.put("userId", userId);
        
        int count = convRecipeDAO.checkCommentAuthor(params);
        if (count == 0) {
            return false;
        }
        
        // 삭제
        int result = convRecipeDAO.deleteComment(commentId);
        return result > 0;
    }
}
