package com.moc.pro.report.dao;

import com.moc.pro.report.vo.AdminReportVO;
import com.moc.pro.report.vo.ReportVO;

import java.util.List;
import java.util.Map;

/**
 * 신고 DAO 인터페이스
 */
public interface ReportDAO {
    
    /**
     * 신고 등록
     * @param report 신고 정보
     * @return 등록된 행 수
     */
    int insertReport(ReportVO report);
    
    /**
     * 신고 여부 확인 (중복 방지)
     * @param params userId, reportType, boardType, targetId
     * @return 신고 횟수
     */
    int checkReport(Map<String, Object> params);
    
    /**
     * 관리자용 신고 목록 조회 (페이징)
     * @param params offset, limit
     * @return List<AdminReportVO> 신고 목록
     */
    List<AdminReportVO> selectAdminReports(Map<String, Object> params);
    
    /**
     * 관리자용 신고 전체 개수
     * @return 전체 신고 개수
     */
    int selectAdminReportsTotalCount();
    
    /**
     * 관리자용 신고 상세 조회
     * @param reportId 신고 ID
     * @return AdminReportVO 신고 상세 정보
     */
    AdminReportVO selectAdminReportById(int reportId);
    
    /**
     * 관리자용 신고 삭제
     * @param reportId 신고 ID
     * @return 삭제된 행 수
     */
    int deleteReport(int reportId);
}
