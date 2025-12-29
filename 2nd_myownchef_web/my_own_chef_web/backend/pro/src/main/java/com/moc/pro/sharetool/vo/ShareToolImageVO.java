package com.moc.pro.sharetool.vo;

import java.sql.Timestamp;

/**
 * 요리도구 나눔 이미지 VO
 * TB_SHARETOOL_IMAGE 테이블 매핑
 */
public class ShareToolImageVO {
    
    private int shareToolImageId;          // 이미지 ID (PK, IDENTITY)
    private int shareToolId;               // 나눔 ID (FK)
    private String shareToolImageUrl;      // 이미지 URL
    private String shareToolImagePath;     // 이미지 파일 경로
    private int shareToolImageIndex;       // 이미지 순서
    private Timestamp createdAt;           // 작성일시
    private String createdBy;              // 작성자
    private Timestamp updatedAt;           // 수정일시
    private String updatedBy;              // 수정자
    
    // 기본 생성자
    public ShareToolImageVO() {}
    
    // Getter/Setter
    public int getShareToolImageId() {
        return shareToolImageId;
    }
    
    public void setShareToolImageId(int shareToolImageId) {
        this.shareToolImageId = shareToolImageId;
    }
    
    public int getShareToolId() {
        return shareToolId;
    }
    
    public void setShareToolId(int shareToolId) {
        this.shareToolId = shareToolId;
    }
    
    public String getShareToolImageUrl() {
        return shareToolImageUrl;
    }
    
    public void setShareToolImageUrl(String shareToolImageUrl) {
        this.shareToolImageUrl = shareToolImageUrl;
    }
    
    public String getShareToolImagePath() {
        return shareToolImagePath;
    }
    
    public void setShareToolImagePath(String shareToolImagePath) {
        this.shareToolImagePath = shareToolImagePath;
    }
    
    public int getShareToolImageIndex() {
        return shareToolImageIndex;
    }
    
    public void setShareToolImageIndex(int shareToolImageIndex) {
        this.shareToolImageIndex = shareToolImageIndex;
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
