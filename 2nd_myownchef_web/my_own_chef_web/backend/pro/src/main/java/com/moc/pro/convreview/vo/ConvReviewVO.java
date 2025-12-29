package com.moc.pro.convreview.vo;

import java.sql.Timestamp;

/**
 * 편의점 리뷰 VO
 * TB_CONV_REVIEW 테이블 매핑
 */
public class ConvReviewVO {
    
    private int convReviewId;              // 편의점 리뷰 ID (PK, IDENTITY)
    private String userId;                 // 작성자 ID
    private String convReviewTitle;        // 제목
    private String convReviewContent;      // 내용 (CLOB)
    private Integer convReviewPrice;       // 가격 (선택사항)
    private String convReviewStore;        // 편의점 종류 (GS25, 세븐일레븐, CU, 이마트24)
    private String convReviewCategory;     // 제품 분류 (식사류, 간식류, 음료(주)류)
    private Timestamp createdAt;           // 작성일시
    private String createdBy;              // 작성자
    private Timestamp updatedAt;           // 수정일시
    private String updatedBy;              // 수정자
    
    // 조인용 추가 필드
    private String userNickname;           // 작성자 닉네임 (TB_USER 조인)
    private String firstImageUrl;          // 첫 번째 이미지 URL (목록 표시용)
    
    // 기본 생성자
    public ConvReviewVO() {}
    
    // Getter/Setter
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
    
    public String getConvReviewTitle() {
        return convReviewTitle;
    }
    
    public void setConvReviewTitle(String convReviewTitle) {
        this.convReviewTitle = convReviewTitle;
    }
    
    public String getConvReviewContent() {
        return convReviewContent;
    }
    
    public void setConvReviewContent(String convReviewContent) {
        this.convReviewContent = convReviewContent;
    }
    
    public Integer getConvReviewPrice() {
        return convReviewPrice;
    }
    
    public void setConvReviewPrice(Integer convReviewPrice) {
        this.convReviewPrice = convReviewPrice;
    }
    
    public String getConvReviewStore() {
        return convReviewStore;
    }
    
    public void setConvReviewStore(String convReviewStore) {
        this.convReviewStore = convReviewStore;
    }
    
    public String getConvReviewCategory() {
        return convReviewCategory;
    }
    
    public void setConvReviewCategory(String convReviewCategory) {
        this.convReviewCategory = convReviewCategory;
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
    
    public String getFirstImageUrl() {
        return firstImageUrl;
    }
    
    public void setFirstImageUrl(String firstImageUrl) {
        this.firstImageUrl = firstImageUrl;
    }
}
