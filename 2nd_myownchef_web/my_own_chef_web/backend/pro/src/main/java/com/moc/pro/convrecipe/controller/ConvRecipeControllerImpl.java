package com.moc.pro.convrecipe.controller;

import com.moc.pro.convrecipe.service.ConvRecipeService;
import com.moc.pro.convrecipe.vo.ConvRecipeVO;
import com.moc.pro.convrecipe.vo.ConvRecipeCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ConvRecipeControllerImpl implements ConvRecipeController {
    
    @Autowired
    private ConvRecipeService convRecipeService;
    
    @Override
    @GetMapping("/api/conv-recipe/list")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") int page) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = convRecipeService.getList(category, page);
            
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
    @GetMapping("/api/conv-recipe/search")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchRecipes(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") int page) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = convRecipeService.searchRecipes(keyword, product, category, page);
            
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
    @GetMapping("/api/conv-recipe/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getDetail(@PathVariable("id") int convRecipeId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = convRecipeService.getDetail(convRecipeId);
            
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
    @GetMapping("/api/conv-recipe/{id}/check-author")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> checkAuthor(
            @PathVariable("id") int convRecipeId,
            HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", true);
                response.put("isAuthor", false);
                return ResponseEntity.ok(response);
            }
            
            boolean isAuthor = convRecipeService.checkAuthor(convRecipeId, sessionUserId);
            
            response.put("success", true);
            response.put("isAuthor", isAuthor);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "작성자 확인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    @PostMapping("/api/conv-recipe")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createConvRecipe(
            @RequestParam String userId,
            @RequestParam String title,
            @RequestParam String mainProduct,
            @RequestParam String category,
            @RequestParam String tip,
            @RequestParam(required = false) String products,
            @RequestParam(required = false) String orders,
            @RequestParam(required = false) MultipartFile[] images,
            HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // ConvRecipeVO 생성
            ConvRecipeVO convRecipe = new ConvRecipeVO();
            convRecipe.setUserId(sessionUserId);
            convRecipe.setConvRecipeTitle(title);
            convRecipe.setConvRecipeMainProduct(mainProduct);
            convRecipe.setConvRecipeCategory(category);
            convRecipe.setConvRecipeTip(tip);
            
            // 편의점 조합 작성
            boolean success = convRecipeService.createConvRecipe(convRecipe, products, orders, images);
            
            if (success) {
                response.put("success", true);
                response.put("message", "편의점 조합이 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "편의점 조합 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "작성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    @PutMapping("/api/conv-recipe/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateConvRecipe(
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
            HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 작성자 확인
            boolean isAuthor = convRecipeService.checkAuthor(convRecipeId, sessionUserId);
            if (!isAuthor) {
                response.put("success", false);
                response.put("message", "수정 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // ConvRecipeVO 생성
            ConvRecipeVO convRecipe = new ConvRecipeVO();
            convRecipe.setConvRecipeId(convRecipeId);
            convRecipe.setUserId(sessionUserId);
            convRecipe.setConvRecipeTitle(title);
            convRecipe.setConvRecipeMainProduct(mainProduct);
            convRecipe.setConvRecipeCategory(category);
            convRecipe.setConvRecipeTip(tip);
            
            // 편의점 조합 수정
            boolean success = convRecipeService.updateConvRecipe(convRecipe, products, orders, images, existingImageUrls);
            
            if (success) {
                response.put("success", true);
                response.put("message", "편의점 조합이 수정되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "편의점 조합 수정에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "수정 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    @DeleteMapping("/api/conv-recipe/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteConvRecipe(
            @PathVariable("id") int convRecipeId,
            HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 작성자 확인
            boolean isAuthor = convRecipeService.checkAuthor(convRecipeId, sessionUserId);
            if (!isAuthor) {
                response.put("success", false);
                response.put("message", "삭제 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 편의점 조합 삭제
            boolean success = convRecipeService.deleteConvRecipe(convRecipeId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "편의점 조합이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "편의점 조합 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    @GetMapping("/api/conv-recipe/{id}/comments")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getComments(@PathVariable("id") int convRecipeId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<ConvRecipeCommentVO> comments = convRecipeService.getComments(convRecipeId);
            
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
    @PostMapping("/api/conv-recipe/{id}/comments")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> createComment(
            @PathVariable("id") int convRecipeId,
            @RequestBody Map<String, String> request,
            HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            String content = request.get("content");
            if (content == null || content.trim().isEmpty()) {
                response.put("success", false);
                response.put("message", "댓글 내용을 입력해주세요.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 댓글 생성
            ConvRecipeCommentVO comment = new ConvRecipeCommentVO();
            comment.setConvRecipeId(convRecipeId);
            comment.setUserId(sessionUserId);
            comment.setConvRecipeCommentContent(content);
            
            boolean success = convRecipeService.createComment(comment);
            
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
            response.put("message", "댓글 작성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    @DeleteMapping("/api/conv-recipe/comments/{commentId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteComment(
            @PathVariable("commentId") int commentId,
            HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 댓글 삭제
            boolean success = convRecipeService.deleteComment(commentId, sessionUserId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "댓글이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "댓글 삭제에 실패했습니다. (권한이 없거나 댓글이 존재하지 않습니다)");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
