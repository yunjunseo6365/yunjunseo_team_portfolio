package com.moc.pro.sharetool.vo;

import java.sql.Timestamp;

/**
 * 요리도구 나눔 VO
 * TB_SHARETOOL 테이블 매핑
 */
public class ShareToolVO {
    
    private int shareToolId;               // 나눔 ID (PK, IDENTITY)
    private String userId;                 // 작성자 ID
    private String shareToolTitle;         // 제품 카테고리 (전자레인지, TV, 냄비, 후라이팬 등)
    private String shareToolProduct;       // 제품 상세이름 (LG 디오스 23L 전자레인지)
    private String shareToolContent;       // 상세 내용 (CLOB)
    private String shareToolStatus;        // 나눔 상태 (나눔중, 나눔완료)
    private String shareToolProvince;      // 시/도 (서울특별시, 경기도 등)
    private String shareToolCity;          // 시/군/구 (강남구, 고양시 등)
    private Timestamp createdAt;           // 작성일시
    private String createdBy;              // 작성자
    private Timestamp updatedAt;           // 수정일시
    private String updatedBy;              // 수정자
    
    // 조인용 추가 필드
    private String userNickname;           // 작성자 닉네임 (TB_USER 조인)
    
    // 기본 생성자
    public ShareToolVO() {}
    
    // Getter/Setter
    public int getShareToolId() {
        return shareToolId;
    }
    
    public void setShareToolId(int shareToolId) {
        this.shareToolId = shareToolId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getShareToolTitle() {
        return shareToolTitle;
    }
    
    public void setShareToolTitle(String shareToolTitle) {
        this.shareToolTitle = shareToolTitle;
    }
    
    public String getShareToolProduct() {
        return shareToolProduct;
    }
    
    public void setShareToolProduct(String shareToolProduct) {
        this.shareToolProduct = shareToolProduct;
    }
    
    public String getShareToolContent() {
        return shareToolContent;
    }
    
    public void setShareToolContent(String shareToolContent) {
        this.shareToolContent = shareToolContent;
    }
    
    public String getShareToolStatus() {
        return shareToolStatus;
    }
    
    public void setShareToolStatus(String shareToolStatus) {
        this.shareToolStatus = shareToolStatus;
    }
    
    public String getShareToolProvince() {
        return shareToolProvince;
    }
    
    public void setShareToolProvince(String shareToolProvince) {
        this.shareToolProvince = shareToolProvince;
    }
    
    public String getShareToolCity() {
        return shareToolCity;
    }
    
    public void setShareToolCity(String shareToolCity) {
        this.shareToolCity = shareToolCity;
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
