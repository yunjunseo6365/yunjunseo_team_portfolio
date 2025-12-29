package com.moc.pro.convreview.vo;

import java.sql.Timestamp;

/**
 * 편의점 리뷰 이미지 VO
 * TB_CONV_REVIEW_IMAGE 테이블 매핑
 */
public class ConvReviewImageVO {
    
    private int convReviewImageId;        // 이미지 ID (PK, IDENTITY)
    private int convReviewId;             // 편의점 리뷰 ID (FK)
    private String convReviewImageUrl;    // 이미지 URL
    private String convReviewImagePath;   // 이미지 파일 경로
    private int convReviewImageIndex;     // 이미지 순서
    private Timestamp createdAt;          // 작성일시
    private String createdBy;             // 작성자
    private Timestamp updatedAt;          // 수정일시
    private String updatedBy;             // 수정자
    
    // Getter/Setter
    public int getConvReviewImageId() {
        return convReviewImageId;
    }
    
    public void setConvReviewImageId(int convReviewImageId) {
        this.convReviewImageId = convReviewImageId;
    }
    
    public int getConvReviewId() {
        return convReviewId;
    }
    
    public void setConvReviewId(int convReviewId) {
        this.convReviewId = convReviewId;
    }
    
    public String getConvReviewImageUrl() {
        return convReviewImageUrl;
    }
    
    public void setConvReviewImageUrl(String convReviewImageUrl) {
        this.convReviewImageUrl = convReviewImageUrl;
    }
    
    public String getConvReviewImagePath() {
        return convReviewImagePath;
    }
    
    public void setConvReviewImagePath(String convReviewImagePath) {
        this.convReviewImagePath = convReviewImagePath;
    }
    
    public int getConvReviewImageIndex() {
        return convReviewImageIndex;
    }
    
    public void setConvReviewImageIndex(int convReviewImageIndex) {
        this.convReviewImageIndex = convReviewImageIndex;
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
