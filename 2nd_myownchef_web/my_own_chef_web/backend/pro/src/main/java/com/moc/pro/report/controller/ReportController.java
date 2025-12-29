package com.moc.pro.report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 신고 Controller 인터페이스
 */
@RequestMapping("/api/report")
public interface ReportController {
    
    /**
     * 신고 등록
     * @param request reportType, boardType, targetId, reportContent
     * @param session HttpSession
     * @return ResponseEntity
     */
    @PostMapping
    @ResponseBody
    ResponseEntity<Map<String, Object>> createReport(
            @RequestBody Map<String, Object> request,
            jakarta.servlet.http.HttpSession session
    );
    
    /**
     * 신고 여부 확인
     * @param reportType 신고 타입
     * @param boardType 게시판 타입
     * @param targetId 대상 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    @GetMapping("/check")
    @ResponseBody
    ResponseEntity<Map<String, Object>> checkReport(
            @RequestParam String reportType,
            @RequestParam String boardType,
            @RequestParam int targetId,
            jakarta.servlet.http.HttpSession session
    );
    
    // ========== 관리자용 신고 관리 API ==========
    
    /**
     * 관리자용 신고 목록 조회
     * @param page 페이지 번호 (기본값: 1)
     * @param session HttpSession (isAdmin 확인)
     * @return ResponseEntity
     */
    @GetMapping("/admin/list")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getAdminReports(
            @RequestParam(defaultValue = "1") int page,
            jakarta.servlet.http.HttpSession session
    );
    
    /**
     * 관리자용 신고 상세 조회
     * @param reportId 신고 ID
     * @param session HttpSession (isAdmin 확인)
     * @return ResponseEntity
     */
    @GetMapping("/admin/{reportId}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> getAdminReportDetail(
            @PathVariable int reportId,
            jakarta.servlet.http.HttpSession session
    );
    
    /**
     * 관리자용 신고 삭제
     * @param reportId 신고 ID
     * @param session HttpSession (isAdmin 확인)
     * @return ResponseEntity
     */
    @DeleteMapping("/admin/{reportId}")
    @ResponseBody
    ResponseEntity<Map<String, Object>> deleteAdminReport(
            @PathVariable int reportId,
            jakarta.servlet.http.HttpSession session
    );
}
