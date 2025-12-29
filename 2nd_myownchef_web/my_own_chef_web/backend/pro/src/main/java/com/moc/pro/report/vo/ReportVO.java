package com.moc.pro.report.vo;

import java.sql.Timestamp;

/**
 * 신고 VO
 * TB_REPORT 테이블과 매핑
 */
public class ReportVO {
    
    private int reportId;
    private String userId;
    private String reportType;      // POST, COMMENT
    private String boardType;       // recipe, freeboard, sharetool, conv_review, conv_comb, withshopping
    private int targetId;           // 게시글 ID 또는 댓글 ID
    private String reportContent;   // 신고 사유
    private String reportStatus;    // OPEN, PROCESSING, RESOLVED, REJECTED
    private String processedBy;
    private Timestamp processedAt;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
    
    // 기본 생성자
    public ReportVO() {
        // MyBatis를 위한 기본 생성자
    }
    
    // Getter/Setter
    public int getReportId() {
        return reportId;
    }
    
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getReportType() {
        return reportType;
    }
    
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    
    public String getBoardType() {
        return boardType;
    }
    
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    
    public int getTargetId() {
        return targetId;
    }
    
    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
    
    public String getReportContent() {
        return reportContent;
    }
    
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }
    
    public String getReportStatus() {
        return reportStatus;
    }
    
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }
    
    public String getProcessedBy() {
        return processedBy;
    }
    
    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }
    
    public Timestamp getProcessedAt() {
        return processedAt;
    }
    
    public void setProcessedAt(Timestamp processedAt) {
        this.processedAt = processedAt;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getCreatedBy() {
        return createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getUpdatedBy() {
        return updatedBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
