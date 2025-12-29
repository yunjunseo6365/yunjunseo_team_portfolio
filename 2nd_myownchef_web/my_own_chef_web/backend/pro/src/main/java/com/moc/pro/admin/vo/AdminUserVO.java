package com.moc.pro.admin.vo;

/**
 * 관리자용 회원 목록 VO
 */
public class AdminUserVO {
    
    private String userId;         // 사용자 ID
    private String userName;       // 이름
    private String userNickname;   // 닉네임
    private String userEmail;      // 이메일
    private String createdAt;      // 가입일 (YYYY-MM-DD)
    private String userStatus;     // 회원 상태 (ACTIVE, WITHDRAWN, SUSPENDED)
    
    /**
     * 기본 생성자
     */
    public AdminUserVO() {}
    
    // Getter/Setter
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
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
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getUserStatus() {
        return userStatus;
    }
    
    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
    
    @Override
    public String toString() {
        return "AdminUserVO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", userStatus='" + userStatus + '\'' +
                '}';
    }
}
