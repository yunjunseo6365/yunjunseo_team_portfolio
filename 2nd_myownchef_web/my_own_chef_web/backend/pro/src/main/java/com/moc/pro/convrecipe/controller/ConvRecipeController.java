package com.moc.pro.convrecipe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RequestMapping
public interface ConvRecipeController {
    
    // 목록 조회 (카테고리 필터 + 페이징)
    @GetMapping("/api/conv-recipe/list")
    ResponseEntity<Map<String, Object>> getList(
        @RequestParam(required = false) String category,
        @RequestParam(defaultValue = "1") int page
    );
    
    // 검색 (키워드 + 핵심제품 + 카테고리 필터)
    @GetMapping("/api/conv-recipe/search")
    ResponseEntity<Map<String, Object>> searchRecipes(
        @RequestParam(required = false) String keyword,
        @RequestParam(required = false) String product,
        @RequestParam(required = false) String category,
        @RequestParam(defaultValue = "1") int page
    );
    
    // 상세 조회
    @GetMapping("/api/conv-recipe/{id}")
    ResponseEntity<Map<String, Object>> getDetail(@PathVariable("id") int convRecipeId);
    
    // 작성자 확인
    @GetMapping("/api/conv-recipe/{id}/check-author")
    ResponseEntity<Map<String, Object>> checkAuthor(
        @PathVariable("id") int convRecipeId,
        HttpSession session
    );
    
    // 작성 (Multipart)
    @PostMapping("/api/conv-recipe")
    ResponseEntity<Map<String, Object>> createConvRecipe(
        @RequestParam String userId,
        @RequestParam String title,
        @RequestParam String mainProduct,
        @RequestParam String category,
        @RequestParam String tip,
        @RequestParam(required = false) String products,
        @RequestParam(required = false) String orders,
        @RequestParam(required = false) MultipartFile[] images,
        HttpSession session
    );
    
    // 수정 (Multipart)
    @PutMapping("/api/conv-recipe/{id}")
    ResponseEntity<Map<String, Object>> updateConvRecipe(
        @PathVariable("id") int convRecipeId,
        @RequestParam String userId,
        @RequestParam String title,
        @RequestParam String mainProduct,
        @RequestParam String category,
        @RequestParam String tip,
        @RequestParam(required = false) String products,
        @RequestParam(required = false) String orders,
        @RequestParam(required = false) MultipartFile[] images,
        @RequestParam(required = false) String existingImageUrls,
        HttpSession session
    );
    
    // 삭제
    @DeleteMapping("/api/conv-recipe/{id}")
    ResponseEntity<Map<String, Object>> deleteConvRecipe(
        @PathVariable("id") int convRecipeId,
        HttpSession session
    );
    
    // 댓글 목록 조회
    @GetMapping("/api/conv-recipe/{id}/comments")
    ResponseEntity<Map<String, Object>> getComments(@PathVariable("id") int convRecipeId);
    
    // 댓글 작성
    @PostMapping("/api/conv-recipe/{id}/comments")
    ResponseEntity<Map<String, Object>> createComment(
        @PathVariable("id") int convRecipeId,
        @RequestBody Map<String, String> request,
        HttpSession session
    );
    
    // 댓글 삭제
    @DeleteMapping("/api/conv-recipe/comments/{commentId}")
    ResponseEntity<Map<String, Object>> deleteComment(
        @PathVariable("commentId") int commentId,
        HttpSession session
    );
}
