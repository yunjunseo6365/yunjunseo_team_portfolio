package com.moc.pro.notification.vo;

import java.sql.Timestamp;

/**
 * 댓글 알림 VO
 * TB_COMMENT_NOTIFICATION 테이블 매핑
 */
public class CommentNotificationVO {
    
    private int commentNotificationId;    // COMMENT_NOTIFICATION_ID
    private int postId;                   // POST_ID
    private String postType;              // POST_TYPE (recipe, freeboard, conv-review, conv-recipe, sharetool)
    private String postAuthorId;          // POST_AUTHOR_ID (알림 받는 사람)
    private String commenterId;           // COMMENTER_ID (댓글 작성자)
    private int commentId;                // COMMENT_ID
    private String isRead;                // IS_READ (Y, N)
    private Timestamp createdAt;          // CREATED_AT
    private String createdBy;             // CREATED_BY
    
    // 추가 정보 (JOIN용)
    private String commenterNickname;     // 댓글 작성자 닉네임
    private String commentContent;        // 댓글 내용
    
    /**
     * 기본 생성자
     * MyBatis에서 객체 생성 시 사용
     */
    public CommentNotificationVO() {
        // MyBatis resultMap용 기본 생성자
    }
    
    // Getter & Setter
    public int getCommentNotificationId() {
        return commentNotificationId;
    }
    
    public void setCommentNotificationId(int commentNotificationId) {
        this.commentNotificationId = commentNotificationId;
    }
    
    public int getPostId() {
        return postId;
    }
    
    public void setPostId(int postId) {
        this.postId = postId;
    }
    
    public String getPostType() {
        return postType;
    }
    
    public void setPostType(String postType) {
        this.postType = postType;
    }
    
    public String getPostAuthorId() {
        return postAuthorId;
    }
    
    public void setPostAuthorId(String postAuthorId) {
        this.postAuthorId = postAuthorId;
    }
    
    public String getCommenterId() {
        return commenterId;
    }
    
    public void setCommenterId(String commenterId) {
        this.commenterId = commenterId;
    }
    
    public int getCommentId() {
        return commentId;
    }
    
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    
    public String getIsRead() {
        return isRead;
    }
    
    public void setIsRead(String isRead) {
        this.isRead = isRead;
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
    
    public String getCommenterNickname() {
        return commenterNickname;
    }
    
    public void setCommenterNickname(String commenterNickname) {
        this.commenterNickname = commenterNickname;
    }
    
    public String getCommentContent() {
        return commentContent;
    }
    
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
    
    @Override
    public String toString() {
        return "CommentNotificationVO{" +
                "commentNotificationId=" + commentNotificationId +
                ", postId=" + postId +
                ", postType='" + postType + '\'' +
                ", postAuthorId='" + postAuthorId + '\'' +
                ", commenterId='" + commenterId + '\'' +
                ", commentId=" + commentId +
                ", isRead='" + isRead + '\'' +
                ", createdAt=" + createdAt +
                ", commenterNickname='" + commenterNickname + '\'' +
                '}';
    }
}
