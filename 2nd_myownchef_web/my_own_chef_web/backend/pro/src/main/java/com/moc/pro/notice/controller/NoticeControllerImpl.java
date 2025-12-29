package com.moc.pro.notice.controller;

import com.moc.pro.notice.service.NoticeService;
import com.moc.pro.notice.vo.NoticeVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 공지사항 Controller 구현체
 */
@Controller
public class NoticeControllerImpl implements NoticeController {
    
    @Autowired
    private NoticeService noticeService;
    
    @GetMapping("/list")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getList(@RequestParam(defaultValue = "1") int page) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            Map<String, Object> result = noticeService.getList(page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getDetail(@PathVariable int id) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            NoticeVO notice = noticeService.getDetail(id);
            
            if (notice == null) {
                response.put("success", false);
                response.put("message", "공지사항을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
            response.put("success", true);
            response.put("data", notice);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "상세 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createNotice(
            @RequestBody Map<String, String> request,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 관리자 권한 확인
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자만 작성할 수 있습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            NoticeVO notice = new NoticeVO();
            notice.setUserId(userId);
            notice.setNoticeTitle(request.get("title"));
            notice.setNoticeContent(request.get("content"));
            
            boolean success = noticeService.createNotice(notice);
            
            if (success) {
                response.put("success", true);
                response.put("message", "공지사항이 작성되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "공지사항 작성에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "공지사항 작성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> updateNotice(
            @PathVariable int id,
            @RequestBody Map<String, String> request,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 관리자 권한 확인
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자만 수정할 수 있습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            NoticeVO notice = new NoticeVO();
            notice.setNoticeId(id);
            notice.setUserId(userId);
            notice.setNoticeTitle(request.get("title"));
            notice.setNoticeContent(request.get("content"));
            
            boolean success = noticeService.updateNotice(notice);
            
            if (success) {
                response.put("success", true);
                response.put("message", "공지사항이 수정되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "공지사항 수정에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "공지사항 수정 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @DeleteMapping("/{id}")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> deleteNotice(
            @PathVariable int id,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 관리자 권한 확인
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자만 삭제할 수 있습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            boolean success = noticeService.deleteNotice(id);
            
            if (success) {
                response.put("success", true);
                response.put("message", "공지사항이 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "공지사항 삭제에 실패했습니다.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "공지사항 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
