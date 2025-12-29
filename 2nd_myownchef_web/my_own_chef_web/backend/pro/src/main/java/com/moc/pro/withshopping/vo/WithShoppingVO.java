package com.moc.pro.withshopping.vo;

import java.sql.Timestamp;

/**
 * 같이쇼핑 VO
 * TB_WITHSHOPPING 테이블 매핑
 */
public class WithShoppingVO {
    
    private int withShoppingId;            // 같이쇼핑 ID (PK, IDENTITY)
    private String userId;                 // 작성자 ID
    private String withShoppingTitle;      // 제목
    private String withShoppingContent;    // 내용 (CLOB)
    private String withShoppingStatus;     // 상태 (모집중, 모집완료)
    private String withShoppingSi;         // 시/도
    private String withShoppingGu;         // 시/군/구
    private Timestamp createdAt;           // 작성일시
    private String createdBy;              // 작성자
    private Timestamp updatedAt;           // 수정일시
    private String updatedBy;              // 수정자
    
    // 조인용 추가 필드
    private String userNickname;           // 작성자 닉네임 (TB_USER 조인)
    
    // 기본 생성자
    public WithShoppingVO() {
        // Default constructor
    }
    
    // Getter/Setter
    public int getWithShoppingId() {
        return withShoppingId;
    }
    
    public void setWithShoppingId(int withShoppingId) {
        this.withShoppingId = withShoppingId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getWithShoppingTitle() {
        return withShoppingTitle;
    }
    
    public void setWithShoppingTitle(String withShoppingTitle) {
        this.withShoppingTitle = withShoppingTitle;
    }
    
    public String getWithShoppingContent() {
        return withShoppingContent;
    }
    
    public void setWithShoppingContent(String withShoppingContent) {
        this.withShoppingContent = withShoppingContent;
    }
    
    public String getWithShoppingStatus() {
        return withShoppingStatus;
    }
    
    public void setWithShoppingStatus(String withShoppingStatus) {
        this.withShoppingStatus = withShoppingStatus;
    }
    
    public String getWithShoppingSi() {
        return withShoppingSi;
    }
    
    public void setWithShoppingSi(String withShoppingSi) {
        this.withShoppingSi = withShoppingSi;
    }
    
    public String getWithShoppingGu() {
        return withShoppingGu;
    }
    
    public void setWithShoppingGu(String withShoppingGu) {
        this.withShoppingGu = withShoppingGu;
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
