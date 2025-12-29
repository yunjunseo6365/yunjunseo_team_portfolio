package com.moc.pro.recipe.service;

import com.moc.pro.recipe.dao.RecipeDAO;
import com.moc.pro.recipe.vo.*;
import com.moc.pro.file.service.FileUploadService;
import com.moc.pro.file.vo.ImageVO;
import com.moc.pro.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 레시피 Service 구현체
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    
    @Autowired
    private RecipeDAO recipeDAO;
    
    @Autowired
    private FileUploadService fileUploadService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private static final int ITEMS_PER_PAGE = 10; // 페이지당 게시글 수
    
    @Override
    public Map<String, Object> getList(int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 목록 조회
        List<RecipeVO> posts = recipeDAO.selectList(params);
        
        // 각 레시피의 대표 이미지 조회 (첫 번째 이미지)
        for (RecipeVO recipe : posts) {
            List<RecipeImageVO> images = recipeDAO.selectImagesByRecipeId(recipe.getRecipeId());
            if (!images.isEmpty()) {
                recipe.setRecipeContent(images.get(0).getRecipeImageUrl()); // 목록에서는 content에 이미지 URL 저장
            } else {
                recipe.setRecipeContent(fileUploadService.getNoImageUrl(250, 250));
            }
        }
        
        // 전체 페이지 수 계산
        int totalCount = recipeDAO.selectTotalCount();
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> searchRecipes(String keyword, String ingredient, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("ingredient", ingredient);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 검색 실행
        List<RecipeVO> posts = recipeDAO.searchByKeyword(params);
        
        // 각 레시피의 대표 이미지 조회
        for (RecipeVO recipe : posts) {
            List<RecipeImageVO> images = recipeDAO.selectImagesByRecipeId(recipe.getRecipeId());
            if (!images.isEmpty()) {
                recipe.setRecipeContent(images.get(0).getRecipeImageUrl());
            } else {
                recipe.setRecipeContent(fileUploadService.getNoImageUrl(250, 250));
            }
        }
        
        // 검색 결과 전체 페이지 수 계산
        int totalCount = recipeDAO.selectSearchCount(params);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        result.put("keyword", keyword);
        result.put("ingredient", ingredient);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getDetail(int recipeId) {
        Map<String, Object> result = new HashMap<>();
        
        // 레시피 기본 정보
        RecipeVO recipe = recipeDAO.selectById(recipeId);
        
        // 재료 목록
        List<RecipeIngredientVO> ingredients = recipeDAO.selectIngredientsByRecipeId(recipeId);
        
        // 조리 순서
        List<RecipeOrderVO> orders = recipeDAO.selectOrdersByRecipeId(recipeId);
        
        // 이미지 목록
        List<RecipeImageVO> images = recipeDAO.selectImagesByRecipeId(recipeId);
        if (images.isEmpty()) {
            // 이미지 없으면 기본 이미지 추가 (상세 페이지용)
            RecipeImageVO noImage = new RecipeImageVO();
            noImage.setRecipeImageUrl(fileUploadService.getNoImageUrl(1000, 800));
            images.add(noImage);
        }
        
        result.put("recipe", recipe);
        result.put("ingredients", ingredients);
        result.put("orders", orders);
        result.put("images", images);
        
        return result;
    }
    
    @Override
    @Transactional
    public boolean createRecipe(RecipeVO recipe, String ingredientsJson, String ordersJson, MultipartFile[] images) {
        try {
            // 1. 레시피 기본 정보 저장
            int insertCount = recipeDAO.insertRecipe(recipe);
            if (insertCount == 0) {
                return false;
            }
            
            int recipeId = recipe.getRecipeId(); // useGeneratedKeys로 자동 설정됨
            
            // 2. 재료 저장
            if (ingredientsJson != null && !ingredientsJson.isEmpty()) {
                List<Map<String, String>> ingredientList = objectMapper.readValue(
                    ingredientsJson, new TypeReference<List<Map<String, String>>>() {}
                );
                
                int index = 1;
                for (Map<String, String> item : ingredientList) {
                    RecipeIngredientVO ingredient = new RecipeIngredientVO();
                    ingredient.setRecipeId(recipeId);
                    ingredient.setRecipeDetailIngredientContent(item.get("content"));
                    ingredient.setRecipeDetailIngredientIndex(index++);
                    ingredient.setCreatedBy(recipe.getUserId());
                    recipeDAO.insertIngredient(ingredient);
                }
            }
            
            // 3. 조리 순서 저장
            if (ordersJson != null && !ordersJson.isEmpty()) {
                List<Map<String, String>> orderList = objectMapper.readValue(
                    ordersJson, new TypeReference<List<Map<String, String>>>() {}
                );
                
                int index = 1;
                for (Map<String, String> item : orderList) {
                    RecipeOrderVO order = new RecipeOrderVO();
                    order.setRecipeId(recipeId);
                    order.setRecipeOrderContent(item.get("content"));
                    order.setRecipeOrderIndex(index++);
                    order.setCreatedBy(recipe.getUserId());
                    recipeDAO.insertOrder(order);
                }
            }
            
            // 4. 이미지 저장
            if (images != null && images.length > 0) {
                int index = 1;
                for (MultipartFile file : images) {
                    if (!file.isEmpty()) {
                        ImageVO uploadResult = fileUploadService.uploadImage(file, "recipe");
                        
                        RecipeImageVO image = new RecipeImageVO();
                        image.setRecipeId(recipeId);
                        image.setRecipeImageUrl(uploadResult.getUrl());
                        image.setRecipeImagePath(uploadResult.getPath());
                        image.setRecipeImageIndex(index++);
                        image.setCreatedBy(recipe.getUserId());
                        recipeDAO.insertImage(image);
                    }
                }
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("레시피 작성 실패: " + e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public boolean updateRecipe(RecipeVO recipe, String ingredientsJson, String ordersJson, MultipartFile[] images, String existingImageUrls) {
        try {
            // 1. 레시피 기본 정보 수정
            int updateCount = recipeDAO.updateRecipe(recipe);
            if (updateCount == 0) {
                return false;
            }
            
            int recipeId = recipe.getRecipeId();
            
            // 2. 기존 재료/순서/이미지 삭제
            recipeDAO.deleteIngredientsByRecipeId(recipeId);
            recipeDAO.deleteOrdersByRecipeId(recipeId);
            
            // 기존 이미지 파일 삭제
            List<RecipeImageVO> oldImages = recipeDAO.selectImagesByRecipeId(recipeId);
            for (RecipeImageVO oldImage : oldImages) {
                fileUploadService.deleteImage(oldImage.getRecipeImagePath());
            }
            recipeDAO.deleteImagesByRecipeId(recipeId);
            
            // 3. 새로운 재료 저장
            if (ingredientsJson != null && !ingredientsJson.isEmpty()) {
                List<Map<String, String>> ingredientList = objectMapper.readValue(
                    ingredientsJson, new TypeReference<List<Map<String, String>>>() {}
                );
                
                int index = 1;
                for (Map<String, String> item : ingredientList) {
                    RecipeIngredientVO ingredient = new RecipeIngredientVO();
                    ingredient.setRecipeId(recipeId);
                    ingredient.setRecipeDetailIngredientContent(item.get("content"));
                    ingredient.setRecipeDetailIngredientIndex(index++);
                    ingredient.setCreatedBy(recipe.getUserId());
                    recipeDAO.insertIngredient(ingredient);
                }
            }
            
            // 4. 새로운 조리 순서 저장
            if (ordersJson != null && !ordersJson.isEmpty()) {
                List<Map<String, String>> orderList = objectMapper.readValue(
                    ordersJson, new TypeReference<List<Map<String, String>>>() {}
                );
                
                int index = 1;
                for (Map<String, String> item : orderList) {
                    RecipeOrderVO order = new RecipeOrderVO();
                    order.setRecipeId(recipeId);
                    order.setRecipeOrderContent(item.get("content"));
                    order.setRecipeOrderIndex(index++);
                    order.setCreatedBy(recipe.getUserId());
                    recipeDAO.insertOrder(order);
                }
            }
            
            // 5. 새로운 이미지 저장
            if (images != null && images.length > 0) {
                int index = 1;
                for (MultipartFile file : images) {
                    if (!file.isEmpty()) {
                        ImageVO uploadResult = fileUploadService.uploadImage(file, "recipe");
                        
                        RecipeImageVO image = new RecipeImageVO();
                        image.setRecipeId(recipeId);
                        image.setRecipeImageUrl(uploadResult.getUrl());
                        image.setRecipeImagePath(uploadResult.getPath());
                        image.setRecipeImageIndex(index++);
                        image.setCreatedBy(recipe.getUserId());
                        recipeDAO.insertImage(image);
                    }
                }
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("레시피 수정 실패: " + e.getMessage());
        }
    }
    
    @Override
    @Transactional
    public boolean deleteRecipe(int recipeId) {
        try {
            // 1. 이미지 파일 삭제
            List<RecipeImageVO> images = recipeDAO.selectImagesByRecipeId(recipeId);
            for (RecipeImageVO image : images) {
                fileUploadService.deleteImage(image.getRecipeImagePath());
            }
            
            // 2. 관련 데이터 삭제
            recipeDAO.deleteIngredientsByRecipeId(recipeId);
            recipeDAO.deleteOrdersByRecipeId(recipeId);
            recipeDAO.deleteImagesByRecipeId(recipeId);
            // 댓글은 CASCADE 설정 또는 별도 삭제 (현재는 레시피 삭제만)
            
            // 3. 레시피 삭제
            int deleteCount = recipeDAO.deleteRecipe(recipeId);
            return deleteCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("레시피 삭제 실패: " + e.getMessage());
        }
    }
    
    @Override
    public boolean checkAuthor(int recipeId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("recipeId", recipeId);
        params.put("userId", userId);
        return recipeDAO.checkAuthor(params) > 0;
    }
    
    @Override
    public List<RecipeCommentVO> getComments(int recipeId) {
        return recipeDAO.selectCommentsByRecipeId(recipeId);
    }
    
    @Override
    @Transactional
    public boolean createComment(RecipeCommentVO comment) {
        try {
            // 1. 댓글 저장
            int insertCount = recipeDAO.insertComment(comment);
            if (insertCount == 0) {
                return false;
            }
            
            // 2. 게시글 작성자 조회
            RecipeVO recipe = recipeDAO.selectById(comment.getRecipeId());
            if (recipe != null) {
                // 3. 알림 생성 (본인 댓글은 제외)
                notificationService.createCommentNotification(
                    comment.getRecipeId(),
                    "recipe",
                    recipe.getUserId(),
                    comment.getUserId(),
                    comment.getRecipeCommentId()
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
            
            if (recipeDAO.checkCommentAuthor(params) == 0) {
                return false; // 작성자가 아님
            }
            
            int deleteCount = recipeDAO.deleteComment(commentId);
            return deleteCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
