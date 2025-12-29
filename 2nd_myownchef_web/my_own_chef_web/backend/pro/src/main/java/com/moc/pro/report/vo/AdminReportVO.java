package com.moc.pro.report.vo;

/**
 * 관리자용 신고 목록 VO
 * TB_REPORT + TB_USER + 각 게시판 JOIN 결과
 */
public class AdminReportVO {
    
    private int reportId;              // 신고 ID
    private String reporterNickname;   // 신고자 닉네임 (JOIN TB_USER)
    private String targetTitle;        // 게시글 제목 (JOIN 각 게시판)
    private String createdAt;          // 신고일 (YYYY-MM-DD)
    private int targetId;              // 대상 ID
    private String boardType;          // 게시판 타입 (recipe, freeboard 등)
    private String reportContent;      // 신고 내용
    private String reportType;         // 신고 타입 (POST, COMMENT)
    private String reportStatus;       // 신고 상태 (OPEN, PROCESSING, RESOLVED, REJECTED)
    
    /**
     * 기본 생성자
     */
    public AdminReportVO() {}
    
    /**
     * 전체 필드 생성자
     */
    public AdminReportVO(int reportId, String reporterNickname, String targetTitle, 
                        String createdAt, int targetId, String boardType, 
                        String reportContent, String reportType, String reportStatus) {
        this.reportId = reportId;
        this.reporterNickname = reporterNickname;
        this.targetTitle = targetTitle;
        this.createdAt = createdAt;
        this.targetId = targetId;
        this.boardType = boardType;
        this.reportContent = reportContent;
        this.reportType = reportType;
        this.reportStatus = reportStatus;
    }
    
    // Getter/Setter
    
    public int getReportId() {
        return reportId;
    }
    
    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
    
    public String getReporterNickname() {
        return reporterNickname;
    }
    
    public void setReporterNickname(String reporterNickname) {
        this.reporterNickname = reporterNickname;
    }
    
    public String getTargetTitle() {
        return targetTitle;
    }
    
    public void setTargetTitle(String targetTitle) {
        this.targetTitle = targetTitle;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public int getTargetId() {
        return targetId;
    }
    
    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
    
    public String getBoardType() {
        return boardType;
    }
    
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    
    public String getReportContent() {
        return reportContent;
    }
    
    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }
    
    public String getReportType() {
        return reportType;
    }
    
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
    
    public String getReportStatus() {
        return reportStatus;
    }
    
    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }
    
    @Override
    public String toString() {
        return "AdminReportVO{" +
                "reportId=" + reportId +
                ", reporterNickname='" + reporterNickname + '\'' +
                ", targetTitle='" + targetTitle + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", targetId=" + targetId +
                ", boardType='" + boardType + '\'' +
                ", reportContent='" + reportContent + '\'' +
                ", reportType='" + reportType + '\'' +
                ", reportStatus='" + reportStatus + '\'' +
                '}';
    }
}
