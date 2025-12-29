package com.moc.pro.admin.vo;

/**
 * 관리자용 게시글 목록 VO
 */
public class AdminPostVO {
    
    private int postId;            // 게시글 ID
    private String userNickname;   // 작성자 닉네임
    private String title;          // 제목
    private String createdAt;      // 작성일 (YYYY-MM-DD)
    private String category;       // 카테고리 한글명 (레시피, 자유게시판 등)
    private String boardType;      // 게시판 타입 (recipe, freeboard 등)
    
    /**
     * 기본 생성자
     */
    public AdminPostVO() {}
    
    // Getter/Setter
    
    public int getPostId() {
        return postId;
    }
    
    public void setPostId(int postId) {
        this.postId = postId;
    }
    
    public String getUserNickname() {
        return userNickname;
    }
    
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public String getBoardType() {
        return boardType;
    }
    
    public void setBoardType(String boardType) {
        this.boardType = boardType;
    }
    
    @Override
    public String toString() {
        return "AdminPostVO{" +
                "postId=" + postId +
                ", userNickname='" + userNickname + '\'' +
                ", title='" + title + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", category='" + category + '\'' +
                ", boardType='" + boardType + '\'' +
                '}';
    }
}
