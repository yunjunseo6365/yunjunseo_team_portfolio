package com.moc.pro.convreview.vo;

import java.sql.Timestamp;

/**
 * 편의점 리뷰 댓글 VO
 * TB_CONV_REVIEW_COMMENT 테이블 매핑
 */
public class ConvReviewCommentVO {
    
    private int convReviewCommentId;           // 댓글 ID (PK, IDENTITY)
    private int convReviewId;                  // 편의점 리뷰 ID (FK)
    private String userId;                     // 작성자 ID
    private String convReviewCommentContent;   // 댓글 내용
    private Timestamp createdAt;               // 작성일시
    private String createdBy;                  // 작성자
    private Timestamp updatedAt;               // 수정일시
    private String updatedBy;                  // 수정자
    
    // 조인용 추가 필드
    private String userNickname;               // 작성자 닉네임 (TB_USER 조인)
    
    // Getter/Setter
    public int getConvReviewCommentId() {
        return convReviewCommentId;
    }
    
    public void setConvReviewCommentId(int convReviewCommentId) {
        this.convReviewCommentId = convReviewCommentId;
    }
    
    public int getConvReviewId() {
        return convReviewId;
    }
    
    public void setConvReviewId(int convReviewId) {
        this.convReviewId = convReviewId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getConvReviewCommentContent() {
        return convReviewCommentContent;
    }
    
    public void setConvReviewCommentContent(String convReviewCommentContent) {
        this.convReviewCommentContent = convReviewCommentContent;
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
    
    public String getUserNickname() {
        return userNickname;
    }
    
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
