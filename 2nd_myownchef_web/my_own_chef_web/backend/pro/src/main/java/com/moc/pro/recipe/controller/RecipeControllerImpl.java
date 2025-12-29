package com.moc.pro.recipe.controller;

import com.moc.pro.recipe.service.RecipeService;
import com.moc.pro.recipe.vo.RecipeVO;
import com.moc.pro.recipe.vo.RecipeCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 레시피 Controller 구현체
 */
@Controller
public class RecipeControllerImpl implements RecipeController {
    
    @Autowired
    private RecipeService recipeService;
    
    @Override
    public ResponseEntity<Map<String, Object>> getList(int page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = recipeService.getList(page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> searchRecipes(String keyword, String ingredient, int page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = recipeService.searchRecipes(keyword, ingredient, page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "검색 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getDetail(int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = recipeService.getDetail(id);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "상세 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> checkAuthor(int id, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("isAuthor", false);
                return ResponseEntity.ok(response);
            }
            
            boolean isAuthor = recipeService.checkAuthor(id, userId);
            response.put("isAuthor", isAuthor);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("isAuthor", false);
            response.put("message", "작성자 확인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> createRecipe(String userId, String title, String mainIngredient, 
                                                             String content, String ingredients, String orders, 
                                                             MultipartFile[] images, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 입력 검증
            if (title == null || title.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "제목을 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            if (mainIngredient == null || mainIngredient.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "주재료를 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // RecipeVO 생성
            RecipeVO recipe = new RecipeVO();
            recipe.setUserId(sessionUserId);
            recipe.setRecipeTitle(title);
            recipe.setRecipeMainIngredient(mainIngredient);
            recipe.setRecipeContent(content != null ? content : "");
            
            // 레시피 작성
            boolean success = recipeService.createRecipe(recipe, ingredients, orders, images);
            
            if (success) {
                response.put("success", true);
                response.put("message", "레시피가 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "레시피 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "레시피 작성 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> updateRecipe(int id, String userId, String title, 
                                                             String mainIngredient, String content, 
                                                             String ingredients, String orders, 
                                                             MultipartFile[] images, String existingImageUrls, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 작성자 확인
            if (!recipeService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "수정 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 입력 검증
            if (title == null || title.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "제목을 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            if (mainIngredient == null || mainIngredient.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "주재료를 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // RecipeVO 생성
            RecipeVO recipe = new RecipeVO();
            recipe.setRecipeId(id);
            recipe.setUserId(sessionUserId);
            recipe.setRecipeTitle(title);
            recipe.setRecipeMainIngredient(mainIngredient);
            recipe.setRecipeContent(content != null ? content : "");
            
            // 레시피 수정
            boolean success = recipeService.updateRecipe(recipe, ingredients, orders, images, existingImageUrls);
            
            if (success) {
                response.put("success", true);
                response.put("message", "레시피가 수정되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "레시피 수정에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "레시피 수정 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> deleteRecipe(int id, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 작성자 확인
            if (!recipeService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "삭제 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 레시피 삭제
            boolean success = recipeService.deleteRecipe(id);
            
            if (success) {
                response.put("success", true);
                response.put("message", "레시피가 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "레시피 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "레시피 삭제 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getComments(int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<RecipeCommentVO> comments = recipeService.getComments(id);
            
            response.put("success", true);
            response.put("comments", comments);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> createComment(int id, Map<String, String> request, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            String commentContent = request.get("content");
            if (commentContent == null || commentContent.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "댓글 내용을 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // RecipeCommentVO 생성
            RecipeCommentVO comment = new RecipeCommentVO();
            comment.setRecipeId(id);
            comment.setUserId(sessionUserId);
            comment.setRecipeCommentContent(commentContent);
            
            // 댓글 작성
            boolean success = recipeService.createComment(comment);
            
            if (success) {
                response.put("success", true);
                response.put("message", "댓글이 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "댓글 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 작성 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> deleteComment(int commentId, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 댓글 삭제 (작성자 확인 포함)
            boolean success = recipeService.deleteComment(commentId, sessionUserId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "댓글이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "댓글 삭제에 실패했습니다. 작성자만 삭제할 수 있습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 삭제 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
