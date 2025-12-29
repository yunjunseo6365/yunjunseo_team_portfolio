package com.moc.pro.convrecipe.service;

import com.moc.pro.convrecipe.vo.ConvRecipeVO;
import com.moc.pro.convrecipe.vo.ConvRecipeCommentVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ConvRecipeService {
    
    // 목록 조회 (카테고리 필터 + 페이징)
    Map<String, Object> getList(String category, int page);
    
    // 검색 (키워드 + 핵심제품 + 카테고리 필터)
    Map<String, Object> searchRecipes(String keyword, String product, String category, int page);
    
    // 상세 조회 (제품/순서/이미지 포함)
    Map<String, Object> getDetail(int convRecipeId);
    
    // 작성 (MultipartFile[] 처리)
    boolean createConvRecipe(ConvRecipeVO convRecipe, String productsJson, String ordersJson, MultipartFile[] images);
    
    // 수정 (MultipartFile[] 처리)
    boolean updateConvRecipe(ConvRecipeVO convRecipe, String productsJson, String ordersJson, MultipartFile[] images, String existingImageUrls);
    
    // 삭제
    boolean deleteConvRecipe(int convRecipeId);
    
    // 작성자 확인
    boolean checkAuthor(int convRecipeId, String userId);
    
    // 댓글 목록 조회
    List<ConvRecipeCommentVO> getComments(int convRecipeId);
    
    // 댓글 작성
    boolean createComment(ConvRecipeCommentVO comment);
    
    // 댓글 삭제
    boolean deleteComment(int commentId, String userId);
}
