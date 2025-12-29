package com.moc.pro.report.service;

import java.util.Map;

/**
 * 신고 Service 인터페이스
 */
public interface ReportService {
    
    /**
     * 신고 등록
     * @param userId 신고자 ID
     * @param reportType 신고 타입 (POST, COMMENT)
     * @param boardType 게시판 타입
     * @param targetId 대상 ID
     * @param reportContent 신고 사유
     * @return 성공 여부
     */
    boolean createReport(String userId, String reportType, String boardType, int targetId, String reportContent);
    
    /**
     * 신고 여부 확인
     * @param userId 사용자 ID
     * @param reportType 신고 타입
     * @param boardType 게시판 타입
     * @param targetId 대상 ID
     * @return 신고 여부
     */
    boolean checkReport(String userId, String reportType, String boardType, int targetId);
    
    /**
     * 관리자용 신고 목록 조회
     * @param page 페이지 번호 (1부터 시작)
     * @return Map<String, Object> {reports, totalPage, currentPage, totalCount}
     */
    Map<String, Object> getAdminReports(int page);
    
    /**
     * 관리자용 신고 상세 조회
     * @param reportId 신고 ID
     * @return Map<String, Object> {reportDetail}
     */
    Map<String, Object> getAdminReportDetail(int reportId);
    
    /**
     * 관리자용 신고 삭제
     * @param reportId 신고 ID
     * @return 성공 여부
     */
    boolean deleteAdminReport(int reportId);
}
