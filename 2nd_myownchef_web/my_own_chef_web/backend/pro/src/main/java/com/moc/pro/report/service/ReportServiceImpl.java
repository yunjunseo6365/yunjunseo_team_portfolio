package com.moc.pro.report.service;

import com.moc.pro.report.dao.ReportDAO;
import com.moc.pro.report.vo.ReportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 신고 Service 구현체
 */
@Service
public class ReportServiceImpl implements ReportService {
    
    @Autowired
    private ReportDAO reportDAO;
    
    @Override
    public boolean createReport(String userId, String reportType, String boardType, int targetId, String reportContent) {
        // 중복 신고 확인
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("reportType", reportType);
        params.put("boardType", boardType);
        params.put("targetId", targetId);
        
        int count = reportDAO.checkReport(params);
        if (count > 0) {
            return false; // 이미 신고함
        }
        
        // 신고 등록
        ReportVO report = new ReportVO();
        report.setUserId(userId);
        report.setReportType(reportType);
        report.setBoardType(boardType);
        report.setTargetId(targetId);
        report.setReportContent(reportContent);
        
        int result = reportDAO.insertReport(report);
        return result > 0;
    }
    
    @Override
    public boolean checkReport(String userId, String reportType, String boardType, int targetId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("reportType", reportType);
        params.put("boardType", boardType);
        params.put("targetId", targetId);
        
        int count = reportDAO.checkReport(params);
        return count > 0;
    }
    
    // ========== 관리자용 신고 관리 ==========
    
    private static final int ITEMS_PER_PAGE = 10; // ⭐ Admin은 10개/페이지
    
    @Override
    public Map<String, Object> getAdminReports(int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 목록 조회
        var reports = reportDAO.selectAdminReports(params);
        
        // 전체 페이지 수 계산
        int totalCount = reportDAO.selectAdminReportsTotalCount();
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("reports", reports);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getAdminReportDetail(int reportId) {
        Map<String, Object> result = new HashMap<>();
        
        var reportDetail = reportDAO.selectAdminReportById(reportId);
        result.put("reportDetail", reportDetail);
        
        return result;
    }
    
    @Override
    public boolean deleteAdminReport(int reportId) {
        int result = reportDAO.deleteReport(reportId);
        return result > 0;
    }
}
