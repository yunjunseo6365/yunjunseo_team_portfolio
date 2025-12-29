package com.moc.pro.withshopping.controller;

import com.moc.pro.withshopping.service.WithShoppingService;
import com.moc.pro.withshopping.vo.WithShoppingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 같이쇼핑 Controller 구현체
 */
@Controller
@RequestMapping("/api/withshopping")
public class WithShoppingControllerImpl implements WithShoppingController {
    
    @Autowired
    private WithShoppingService withShoppingService;
    
    /**
     * 같이쇼핑 목록 조회 (지역 + 상태 필터, 페이징)
     * GET /api/withshopping/list?si={si}&gu={gu}&status={status}&page={page}
     */
    @GetMapping("/list")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(required = false) String si,
            @RequestParam(required = false) String gu,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") Integer page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = withShoppingService.getList(si, gu, status, page);
            
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
     * 같이쇼핑 검색 (키워드 + 지역 필터, 페이징)
     * GET /api/withshopping/search?keyword={keyword}&si={si}&gu={gu}&page={page}
     */
    @GetMapping("/search")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> searchPosts(
            @RequestParam String keyword,
            @RequestParam(required = false) String si,
            @RequestParam(required = false) String gu,
            @RequestParam(defaultValue = "1") Integer page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = withShoppingService.searchPosts(keyword, si, gu, page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "검색 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 같이쇼핑 상세 조회
     * GET /api/withshopping/{id}
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getDetail(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            WithShoppingVO withShopping = withShoppingService.getDetail(id);
            
            if (withShopping != null) {
                response.put("success", true);
                response.put("data", withShopping);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "게시글을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "상세 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 작성자 확인
     * GET /api/withshopping/{id}/check-author
     */
    @GetMapping("/{id}/check-author")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> checkAuthor(
            @PathVariable("id") String id,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        int postId = Integer.parseInt(id);
        try {
            String sessionUserId = (String) session.getAttribute("userId");
            
            if (sessionUserId == null) {
                response.put("success", false);
                response.put("isAuthor", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.ok(response);
            }

            boolean isAuthor = withShoppingService.checkAuthor(postId, sessionUserId);
            
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
     * 같이쇼핑 작성
     * POST /api/withshopping
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createWithShopping(
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
            
            // WithShoppingVO 생성
            WithShoppingVO withShopping = new WithShoppingVO();
            withShopping.setUserId(sessionUserId);
            withShopping.setWithShoppingTitle(request.get("title"));
            withShopping.setWithShoppingContent(request.get("content"));
            withShopping.setWithShoppingStatus(request.get("status"));
            withShopping.setWithShoppingSi(request.get("si"));
            withShopping.setWithShoppingGu(request.get("gu"));
            
            // 같이쇼핑 작성
            boolean success = withShoppingService.createWithShopping(withShopping);
            
            if (success) {
                response.put("success", true);
                response.put("message", "같이쇼핑이 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "같이쇼핑 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "같이쇼핑 작성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 같이쇼핑 수정
     * PUT /api/withshopping/{id}
     */
    @PutMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> updateWithShopping(
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
            
            // 작성자 확인
            if (!withShoppingService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "수정 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // WithShoppingVO 생성
            WithShoppingVO withShopping = new WithShoppingVO();
            withShopping.setWithShoppingId(id);
            withShopping.setUserId(sessionUserId);
            withShopping.setWithShoppingTitle(request.get("title"));
            withShopping.setWithShoppingContent(request.get("content"));
            withShopping.setWithShoppingStatus(request.get("status"));
            withShopping.setWithShoppingSi(request.get("si"));
            withShopping.setWithShoppingGu(request.get("gu"));
            
            // 같이쇼핑 수정
            boolean success = withShoppingService.updateWithShopping(withShopping);
            
            if (success) {
                response.put("success", true);
                response.put("message", "같이쇼핑이 수정되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "같이쇼핑 수정에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "같이쇼핑 수정 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 같이쇼핑 삭제
     * DELETE /api/withshopping/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deleteWithShopping(
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
            if (!withShoppingService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "삭제 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 같이쇼핑 삭제
            boolean success = withShoppingService.deleteWithShopping(id);
            
            if (success) {
                response.put("success", true);
                response.put("message", "같이쇼핑이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "같이쇼핑 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "같이쇼핑 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 모집 완료 처리 (채팅 수락 시 호출)
     * POST /api/withshopping/{id}/complete
     */
    @PostMapping("/{id}/complete")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> completeShopping(
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
            if (!withShoppingService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 모집 완료 처리
            boolean success = withShoppingService.completeShopping(id);
            
            if (success) {
                response.put("success", true);
                response.put("message", "모집이 완료되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "모집 완료 처리에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "모집 완료 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
