package com.moc.pro.freeboard.controller;

import com.moc.pro.freeboard.service.FreeboardService;
import com.moc.pro.freeboard.vo.FreeboardVO;
import com.moc.pro.freeboard.vo.FreeboardCommentVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 자유게시판 Controller 구현체
 */
@Controller
public class FreeboardControllerImpl implements FreeboardController {
    
    @Autowired
    private FreeboardService freeboardService;
    
    @GetMapping("/list")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getList(@RequestParam(defaultValue = "1") int page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = freeboardService.getList(page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/search")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> searchPosts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = freeboardService.searchPosts(keyword, page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "검색 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getDetail(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            FreeboardVO freeboard = freeboardService.getDetail(id);
            
            if (freeboard == null) {
                response.put("success", false);
                response.put("message", "게시글을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            response.put("success", true);
            response.put("data", freeboard);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "상세 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/{id}/check-author")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> checkAuthor(
            @PathVariable int id,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("isAuthor", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.ok(response);
            }
            
            boolean isAuthor = freeboardService.checkAuthor(id, userId);
            
            response.put("success", true);
            response.put("isAuthor", isAuthor);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("isAuthor", false);
            response.put("message", "작성자 확인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createPost(
            @RequestBody Map<String, String> request,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            FreeboardVO freeboard = new FreeboardVO();
            freeboard.setUserId(userId);
            freeboard.setFreeboardTitle(request.get("title"));
            freeboard.setFreeboardContent(request.get("content"));
            
            boolean success = freeboardService.createPost(freeboard);
            
            if (success) {
                response.put("success", true);
                response.put("message", "게시글이 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "게시글 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 작성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> updatePost(
            @PathVariable int id,
            @RequestBody Map<String, String> request,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 작성자 확인
            boolean isAuthor = freeboardService.checkAuthor(id, userId);
            if (!isAuthor) {
                response.put("success", false);
                response.put("message", "수정 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            FreeboardVO freeboard = new FreeboardVO();
            freeboard.setFreeboardId(id);
            freeboard.setUserId(userId);
            freeboard.setFreeboardTitle(request.get("title"));
            freeboard.setFreeboardContent(request.get("content"));
            
            boolean success = freeboardService.updatePost(freeboard);
            
            if (success) {
                response.put("success", true);
                response.put("message", "게시글이 수정되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "게시글 수정에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 수정 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deletePost(
            @PathVariable int id,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = freeboardService.deletePost(id, userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "게시글이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "삭제 권한이 없거나 게시글 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "게시글 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // ===== 댓글 관련 =====
    
    @GetMapping("/{id}/comments")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getComments(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<FreeboardCommentVO> comments = freeboardService.getComments(id);
            
            response.put("success", true);
            response.put("data", comments);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/{id}/comments")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createComment(
            @PathVariable int id,
            @RequestBody Map<String, String> request,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            FreeboardCommentVO comment = new FreeboardCommentVO();
            comment.setFreeboardId(id);
            comment.setUserId(userId);
            comment.setFreeboardCommentContent(request.get("content"));
            
            boolean success = freeboardService.createComment(comment);
            
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
    
    @DeleteMapping("/comments/{commentId}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deleteComment(
            @PathVariable int commentId,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = freeboardService.deleteComment(commentId, userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "댓글이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "삭제 권한이 없거나 댓글 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "댓글 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
