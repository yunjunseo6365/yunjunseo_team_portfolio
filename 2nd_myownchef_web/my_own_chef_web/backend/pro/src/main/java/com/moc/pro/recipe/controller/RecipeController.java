package com.moc.pro.recipe.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 레시피 Controller 인터페이스
 */
@RequestMapping("/api/recipe")
public interface RecipeController {
    
    /**
     * 레시피 목록 조회
     * @param page 페이지 번호
     * @return ResponseEntity
     */
    @GetMapping("/list")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getList(@RequestParam(defaultValue = "1") int page);
    
    /**
     * 레시피 검색
     * @param keyword 검색 키워드
     * @param ingredient 주재료
     * @param page 페이지 번호
     * @return ResponseEntity
     */
    @GetMapping("/search")
    @ResponseBody
    ResponseEntity<Map<String, Object>> searchRecipes(@RequestParam(required = false) String keyword,
                                                       @RequestParam(required = false) String ingredient,
                                                       @RequestParam(defaultValue = "1") int page);
    
    /**
     * 레시피 상세 조회
     * @param id 레시피 ID
     * @return ResponseEntity
     */
    @GetMapping("/{id}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getDetail(@PathVariable int id);
    
    /**
     * 작성자 확인
     * @param id 레시피 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    @GetMapping("/{id}/check-author")
    @ResponseBody
    ResponseEntity<Map<String, Object>> checkAuthor(@PathVariable int id, HttpSession session);
    
    /**
     * 레시피 작성
     * @param userId 작성자 ID
     * @param title 레시피 제목
     * @param mainIngredient 주재료
     * @param content 팁 & 소감
     * @param ingredients 재료 목록 (JSON)
     * @param orders 조리 순서 (JSON)
     * @param images 이미지 파일 배열
     * @param session HttpSession
     * @return ResponseEntity
     */
    @PostMapping
    @ResponseBody
    ResponseEntity<Map<String, Object>> createRecipe(@RequestParam String userId,
                                                      @RequestParam String title,
                                                      @RequestParam String mainIngredient,
                                                      @RequestParam String content,
                                                      @RequestParam(required = false) String ingredients,
                                                      @RequestParam(required = false) String orders,
                                                      @RequestParam(required = false) MultipartFile[] images,
                                                      HttpSession session);
    
    /**
     * 레시피 수정
     * @param id 레시피 ID
     * @param userId 작성자 ID
     * @param title 레시피 제목
     * @param mainIngredient 주재료
     * @param content 팁 & 소감
     * @param ingredients 재료 목록 (JSON)
     * @param orders 조리 순서 (JSON)
     * @param images 이미지 파일 배열
     * @param existingImageUrls 유지할 기존 이미지 URL (JSON)
     * @param session HttpSession
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> updateRecipe(@PathVariable int id,
                                                      @RequestParam String userId,
                                                      @RequestParam String title,
                                                      @RequestParam String mainIngredient,
                                                      @RequestParam String content,
                                                      @RequestParam(required = false) String ingredients,
                                                      @RequestParam(required = false) String orders,
                                                      @RequestParam(required = false) MultipartFile[] images,
                                                      @RequestParam(required = false) String existingImageUrls,
                                                      HttpSession session);
    
    /**
     * 레시피 삭제
     * @param id 레시피 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> deleteRecipe(@PathVariable int id, HttpSession session);
    
    /**
     * 댓글 목록 조회
     * @param id 레시피 ID
     * @return ResponseEntity
     */
    @GetMapping("/{id}/comments")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getComments(@PathVariable int id);
    
    /**
     * 댓글 작성
     * @param id 레시피 ID
     * @param request 댓글 정보
     * @param session HttpSession
     * @return ResponseEntity
     */
    @PostMapping("/{id}/comments")
    @ResponseBody
    ResponseEntity<Map<String, Object>> createComment(@PathVariable int id,
                                                       @RequestBody Map<String, String> request,
                                                       HttpSession session);
    
    /**
     * 댓글 삭제
     * @param commentId 댓글 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    @DeleteMapping("/comments/{commentId}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> deleteComment(@PathVariable int commentId, HttpSession session);
}
