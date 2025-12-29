package com.moc.pro.user.vo;

import java.sql.Timestamp;

/**
 * 사용자(User) 정보를 담는 VO 클래스
 * TB_USER 테이블과 매핑
 */
public class UserVO {
    
    private String userId;          // 사용자 ID (PK)
    private String userEmail;       // 이메일 (UNIQUE)
    private String userName;        // 이름
    private String userNickname;    // 닉네임 (UNIQUE)
    private String userPwd;         // 비밀번호 (BCrypt 해시)
    private String userStatus;      // 상태 (ACTIVE/WITHDRAWN)
    private Timestamp withdrawnAt;  // 탈퇴일시
    private String userIsadmin;     // 관리자 여부 (Y/N)
    private String userImageUrl;    // 프로필 이미지 URL (TB_USER_IMAGE)
    private String userImagePath;   // 프로필 이미지 경로 (TB_USER_IMAGE)
    private Timestamp createdAt;    // 생성일시
    private String createdBy;       // 생성자
    private Timestamp updatedAt;    // 수정일시
    private String updatedBy;       // 수정자
    
    // 기본 생성자
    public UserVO() {
        // MyBatis에서 객체 생성 시 사용
    }
    
    // Getter/Setter
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserNickname() {
        return userNickname;
    }
    
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
    
    public String getUserPwd() {
        return userPwd;
    }
    
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    
    public String getUserStatus() {
        return userStatus;
    }
    
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    
    public Timestamp getWithdrawnAt() {
        return withdrawnAt;
    }
    
    public void setWithdrawnAt(Timestamp withdrawnAt) {
        this.withdrawnAt = withdrawnAt;
    }
    
    public String getUserIsadmin() {
        return userIsadmin;
    }
    
    public void setUserIsadmin(String userIsadmin) {
        this.userIsadmin = userIsadmin;
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
    
    public String getUserImageUrl() {
        return userImageUrl;
    }
    
    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }
    
    public String getUserImagePath() {
        return userImagePath;
    }
    
    public void setUserImagePath(String userImagePath) {
        this.userImagePath = userImagePath;
    }
    
    @Override
    public String toString() {
        return "UserVO{" +
                "userId='" + userId + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", userIsadmin='" + userIsadmin + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
