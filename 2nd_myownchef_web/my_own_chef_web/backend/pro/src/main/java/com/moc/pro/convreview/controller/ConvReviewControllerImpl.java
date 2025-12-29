package com.moc.pro.convreview.controller;

import com.moc.pro.convreview.service.ConvReviewService;
import com.moc.pro.convreview.vo.ConvReviewVO;
import com.moc.pro.convreview.vo.ConvReviewCommentVO;

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

/**
 * 편의점 리뷰 Controller 구현체
 */
@Controller
public class ConvReviewControllerImpl implements ConvReviewController {
    
    @Autowired
    private ConvReviewService convReviewService;
    
    /**
     * 편의점 리뷰 목록 조회 (편의점 + 카테고리 필터, 페이징)
     * GET /api/conv-review/list?store={store}&category={category}&page={page}
     */
    @GetMapping("/list")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(required = false) String store,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "1") Integer page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = convReviewService.getList(store, category, page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 편의점 리뷰 상세 조회
     * GET /api/conv-review/{id}
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getDetail(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = convReviewService.getDetail(id);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "상세 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 작성자 확인
     * GET /api/conv-review/{id}/check-author
     */
    @GetMapping("/{id}/check-author")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> checkAuthor(
            @PathVariable("id") int id,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("isAuthor", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.ok(response);
            }
            
            boolean isAuthor = convReviewService.checkAuthor(id, sessionUserId);
            
            response.put("success", true);
            response.put("isAuthor", isAuthor);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "작성자 확인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 편의점 리뷰 작성
     * POST /api/conv-review
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createConvReview(
            @RequestParam String userId,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(required = false) Integer price,
            @RequestParam String store,
            @RequestParam String category,
            @RequestParam(required = false) MultipartFile[] images,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // ConvReviewVO 생성
            ConvReviewVO convReview = new ConvReviewVO();
            convReview.setUserId(sessionUserId);
            convReview.setConvReviewTitle(title);
            convReview.setConvReviewContent(content);
            convReview.setConvReviewPrice(price);
            convReview.setConvReviewStore(store);
            convReview.setConvReviewCategory(category);
            
            // 편의점 리뷰 작성
            boolean success = convReviewService.createConvReview(convReview, images);
            
            if (success) {
                response.put("success", true);
                response.put("message", "편의점 리뷰가 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "편의점 리뷰 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "편의점 리뷰 작성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 편의점 리뷰 수정
     * PUT /api/conv-review/{id}
     */
    @PutMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> updateConvReview(
            @PathVariable("id") int id,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam(required = false) Integer price,
            @RequestParam String store,
            @RequestParam String category,
            @RequestParam(required = false) MultipartFile[] images,
            @RequestParam(required = false) String existingImageUrls,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 작성자 확인
            if (!convReviewService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "수정 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // ConvReviewVO 생성
            ConvReviewVO convReview = new ConvReviewVO();
            convReview.setConvReviewId(id);
            convReview.setUserId(sessionUserId);
            convReview.setConvReviewTitle(title);
            convReview.setConvReviewContent(content);
            convReview.setConvReviewPrice(price);
            convReview.setConvReviewStore(store);
            convReview.setConvReviewCategory(category);
            
            // 편의점 리뷰 수정
            boolean success = convReviewService.updateConvReview(convReview, images, existingImageUrls);
            
            if (success) {
                response.put("success", true);
                response.put("message", "편의점 리뷰가 수정되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "편의점 리뷰 수정에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "편의점 리뷰 수정 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 편의점 리뷰 삭제
     * DELETE /api/conv-review/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deleteConvReview(
            @PathVariable("id") int id,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 작성자 확인
            if (!convReviewService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "삭제 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 편의점 리뷰 삭제
            boolean success = convReviewService.deleteConvReview(id);
            
            if (success) {
                response.put("success", true);
                response.put("message", "편의점 리뷰가 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "편의점 리뷰 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "편의점 리뷰 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 댓글 목록 조회
     * GET /api/conv-review/{id}/comments
     */
    @GetMapping("/{id}/comments")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getComments(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<ConvReviewCommentVO> comments = convReviewService.getComments(id);
            
            response.put("success", true);
            response.put("comments", comments);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 댓글 작성
     * POST /api/conv-review/{id}/comments
     */
    @PostMapping("/{id}/comments")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createComment(
            @PathVariable("id") int id,
            @RequestBody Map<String, String> request,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            ConvReviewCommentVO comment = new ConvReviewCommentVO();
            comment.setConvReviewId(id);
            comment.setUserId(sessionUserId);
            comment.setConvReviewCommentContent(request.get("content"));
            
            boolean success = convReviewService.createComment(comment);
            
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
    
    /**
     * 댓글 삭제
     * DELETE /api/conv-review/comments/{commentId}
     */
    @DeleteMapping("/comments/{commentId}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deleteComment(
            @PathVariable("commentId") int commentId,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = convReviewService.deleteComment(commentId, sessionUserId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "댓글이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "댓글 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
