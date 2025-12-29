package com.moc.pro.chat.vo;

import java.sql.Timestamp;

/**
 * 채팅방 VO
 * TB_CHAT_ROOM 테이블 매핑
 */
public class ChatRoomVO {
    
    private int chatRoomId;           // CHAT_ROOM_ID
    private int postId;               // POST_ID
    private String postType;          // POST_TYPE (sharetool, shopping)
    private String ownerId;           // OWNER_ID (게시글 작성자)
    private String participantId;     // PARTICIPANT_ID (신청자)
    private String roomStatus;        // ROOM_STATUS (ACTIVE, CLOSED)
    private String isAccepted;        // IS_ACCEPTED (Y, N)
    private Timestamp createdAt;      // CREATED_AT
    private String createdBy;         // CREATED_BY
    private Timestamp updatedAt;      // UPDATED_AT
    private String updatedBy;         // UPDATED_BY
    
    // 추가 정보 (JOIN용)
    private String ownerNickname;     // 게시글 작성자 닉네임
    private String participantNickname; // 신청자 닉네임
    private String postTitle;         // 게시글 제목
    private boolean hasUnread;        // 읽지 않은 메시지 존재 여부
    
    /**
     * 기본 생성자
     * MyBatis에서 객체 생성 시 사용
     */
    public ChatRoomVO() {
        // MyBatis resultMap용 기본 생성자
    }
    
    // Getter & Setter
    public int getChatRoomId() {
        return chatRoomId;
    }
    
    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
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
    
    public String getOwnerId() {
        return ownerId;
    }
    
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
    
    public String getParticipantId() {
        return participantId;
    }
    
    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }
    
    public String getRoomStatus() {
        return roomStatus;
    }
    
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }
    
    public String getIsAccepted() {
        return isAccepted;
    }
    
    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
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
    
    public String getOwnerNickname() {
        return ownerNickname;
    }
    
    public void setOwnerNickname(String ownerNickname) {
        this.ownerNickname = ownerNickname;
    }
    
    public String getParticipantNickname() {
        return participantNickname;
    }
    
    public void setParticipantNickname(String participantNickname) {
        this.participantNickname = participantNickname;
    }
    
    public String getPostTitle() {
        return postTitle;
    }
    
    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }
    
    public boolean isHasUnread() {
        return hasUnread;
    }
    
    public void setHasUnread(boolean hasUnread) {
        this.hasUnread = hasUnread;
    }
    
    @Override
    public String toString() {
        return "ChatRoomVO{" +
                "chatRoomId=" + chatRoomId +
                ", postId=" + postId +
                ", postType='" + postType + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", participantId='" + participantId + '\'' +
                ", roomStatus='" + roomStatus + '\'' +
                ", isAccepted='" + isAccepted + '\'' +
                ", createdAt=" + createdAt +
                ", ownerNickname='" + ownerNickname + '\'' +
                ", participantNickname='" + participantNickname + '\'' +
                ", postTitle='" + postTitle + '\'' +
                '}';
    }
}
