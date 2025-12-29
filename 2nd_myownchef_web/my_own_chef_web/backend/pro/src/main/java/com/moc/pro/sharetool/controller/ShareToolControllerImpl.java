package com.moc.pro.sharetool.controller;

import com.moc.pro.sharetool.service.ShareToolService;
import com.moc.pro.sharetool.vo.ShareToolVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 요리도구 나눔 Controller 구현체
 */
@Controller
@RequestMapping("/api/sharetool")
public class ShareToolControllerImpl implements ShareToolController {
    
    @Autowired
    private ShareToolService shareToolService;
    
    /**
     * 요리도구 나눔 목록 조회 (지역 + 상태 필터, 페이징)
     * GET /api/sharetool/list?province={province}&city={city}&status={status}&page={page}
     */
    @GetMapping("/list")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(required = false) String province,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "1") Integer page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = shareToolService.getList(province, city, status, page);
            
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
     * 요리도구 나눔 검색 (키워드 + 지역 필터, 페이징)
     * GET /api/sharetool/search?keyword={keyword}&province={province}&city={city}&page={page}
     */
    @GetMapping("/search")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> searchPosts(
            @RequestParam String keyword,
            @RequestParam(required = false) String province,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "1") Integer page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = shareToolService.searchPosts(keyword, province, city, page);
            
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
     * 요리도구 나눔 상세 조회
     * GET /api/sharetool/{id}
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getDetail(@PathVariable("id") int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = shareToolService.getDetail(id);
            Object shareTool = result != null ? result.get("shareTool") : null;
            if (shareTool == null) {
                response.put("success", false);
                response.put("message", "게시글을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
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
     * GET /api/sharetool/{id}/check-author
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
            
            boolean isAuthor = shareToolService.checkAuthor(id, sessionUserId);
            
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
     * 요리도구 나눔 작성
     * POST /api/sharetool
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createShareTool(
            @RequestParam String userId,
            @RequestParam String title,
            @RequestParam String product,
            @RequestParam String content,
            @RequestParam String status,
            @RequestParam String province,
            @RequestParam String city,
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
            
            // ShareToolVO 생성
            ShareToolVO shareTool = new ShareToolVO();
            shareTool.setUserId(sessionUserId);
            shareTool.setShareToolTitle(title);
            shareTool.setShareToolProduct(product);
            shareTool.setShareToolContent(content);
            shareTool.setShareToolStatus(status);
            shareTool.setShareToolProvince(province);
            shareTool.setShareToolCity(city);
            
            // 요리도구 나눔 작성
            boolean success = shareToolService.createShareTool(shareTool, images);
            
            if (success) {
                response.put("success", true);
                response.put("message", "요리도구 나눔이 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "요리도구 나눔 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "요리도구 나눔 작성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 요리도구 나눔 수정
     * PUT /api/sharetool/{id}
     */
    @PutMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> updateShareTool(
            @PathVariable("id") int id,
            @RequestParam String title,
            @RequestParam String product,
            @RequestParam String content,
            @RequestParam String status,
            @RequestParam String province,
            @RequestParam String city,
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
            if (!shareToolService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "수정 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // ShareToolVO 생성
            ShareToolVO shareTool = new ShareToolVO();
            shareTool.setShareToolId(id);
            shareTool.setUserId(sessionUserId);
            shareTool.setShareToolTitle(title);
            shareTool.setShareToolProduct(product);
            shareTool.setShareToolContent(content);
            shareTool.setShareToolStatus(status);
            shareTool.setShareToolProvince(province);
            shareTool.setShareToolCity(city);
            
            // 요리도구 나눔 수정
            boolean success = shareToolService.updateShareTool(shareTool, images, existingImageUrls);
            
            if (success) {
                response.put("success", true);
                response.put("message", "요리도구 나눔이 수정되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "요리도구 나눔 수정에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "요리도구 나눔 수정 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 요리도구 나눔 삭제
     * DELETE /api/sharetool/{id}
     */
    @DeleteMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deleteShareTool(
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
            if (!shareToolService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "삭제 권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 요리도구 나눔 삭제
            boolean success = shareToolService.deleteShareTool(id);
            
            if (success) {
                response.put("success", true);
                response.put("message", "요리도구 나눔이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "요리도구 나눔 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "요리도구 나눔 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    /**
     * 나눔 완료 처리 (채팅 수락 시 호출)
     * POST /api/sharetool/{id}/complete
     */
    @PostMapping("/{id}/complete")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> completeShare(
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
            if (!shareToolService.checkAuthor(id, sessionUserId)) {
                response.put("success", false);
                response.put("message", "권한이 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 나눔 완료 처리
            boolean success = shareToolService.completeShare(id);
            
            if (success) {
                response.put("success", true);
                response.put("message", "나눔이 완료되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "나눔 완료 처리에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "나눔 완료 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
