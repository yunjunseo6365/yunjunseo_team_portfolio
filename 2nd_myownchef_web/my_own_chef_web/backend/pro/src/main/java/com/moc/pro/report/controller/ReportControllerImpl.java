package com.moc.pro.report.controller;

import com.moc.pro.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

/**
 * 신고 Controller 구현체
 */
@Controller
public class ReportControllerImpl implements ReportController {
    
    @Autowired
    private ReportService reportService;
    
    @Override
    public ResponseEntity<Map<String, Object>> createReport(
            Map<String, Object> request,
            jakarta.servlet.http.HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null || sessionUserId.isEmpty()) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 파라미터 추출
            String reportType = (String) request.get("reportType");
            String boardType = (String) request.get("boardType");
            Integer targetId = (Integer) request.get("targetId");
            String reportContent = (String) request.get("reportContent");
            
            // 유효성 검사
            if (reportType == null || boardType == null || targetId == null || reportContent == null) {
                response.put("success", false);
                response.put("message", "필수 파라미터가 누락되었습니다.");
                return ResponseEntity.badRequest().body(response);
            }
            
            // 신고 등록
            boolean success = reportService.createReport(
                sessionUserId,
                reportType,
                boardType,
                targetId,
                reportContent
            );
            
            if (success) {
                response.put("success", true);
                response.put("message", "신고가 접수되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "이미 신고한 내용입니다.");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "신고 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> checkReport(
            String reportType,
            String boardType,
            int targetId,
            jakarta.servlet.http.HttpSession session) {
        
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 로그인 확인
            String sessionUserId = (String) session.getAttribute("userId");
            if (sessionUserId == null || sessionUserId.isEmpty()) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 신고 여부 확인
            boolean isReported = reportService.checkReport(
                sessionUserId,
                reportType,
                boardType,
                targetId
            );
            
            response.put("success", true);
            response.put("data", Map.of("isReported", isReported));
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "신고 확인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    // ========== 관리자용 신고 관리 API ==========
    
    @Override
    public ResponseEntity<Map<String, Object>> getAdminReports(int page, jakarta.servlet.http.HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 관리자 권한 확인 (Boolean 타입!)
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자 권한이 필요합니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 신고 목록 조회
            Map<String, Object> result = reportService.getAdminReports(page);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "신고 목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getAdminReportDetail(int reportId, jakarta.servlet.http.HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 관리자 권한 확인 (Boolean 타입!)
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자 권한이 필요합니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 신고 상세 조회
            Map<String, Object> result = reportService.getAdminReportDetail(reportId);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "신고 상세 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> deleteAdminReport(int reportId, jakarta.servlet.http.HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 관리자 권한 확인 (Boolean 타입!)
            Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
            if (!Boolean.TRUE.equals(isAdmin)) {
                response.put("success", false);
                response.put("message", "관리자 권한이 필요합니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
            // 신고 삭제
            boolean success = reportService.deleteAdminReport(reportId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "신고가 삭제되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "신고 삭제 실패");
                return ResponseEntity.badRequest().body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "신고 삭제 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
