package com.moc.pro.chat.vo;

import java.sql.Timestamp;

/**
 * 채팅 메시지 VO
 * TB_CHAT_MESSAGE 테이블 매핑
 */
public class ChatMessageVO {
    
    private int chatMessageId;       // CHAT_MESSAGE_ID
    private int chatRoomId;          // CHAT_ROOM_ID
    private String senderId;         // SENDER_ID
    private String messageContent;   // MESSAGE_CONTENT
    private String messageType;      // MESSAGE_TYPE (TEXT, ACCEPT, REJECT, EXIT, SYSTEM)
    private Timestamp createdAt;     // CREATED_AT
    private String createdBy;        // CREATED_BY
    private Timestamp updatedAt;     // UPDATED_AT
    private String updatedBy;        // UPDATED_BY
    
    // 추가 정보 (JOIN용)
    private String senderNickname;   // 발신자 닉네임
    
    /**
     * 기본 생성자
     * MyBatis에서 객체 생성 시 사용
     */
    public ChatMessageVO() {
        // MyBatis resultMap용 기본 생성자
    }
    
    // Getter & Setter
    public int getChatMessageId() {
        return chatMessageId;
    }
    
    public void setChatMessageId(int chatMessageId) {
        this.chatMessageId = chatMessageId;
    }
    
    public int getChatRoomId() {
        return chatRoomId;
    }
    
    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }
    
    public String getSenderId() {
        return senderId;
    }
    
    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    
    public String getMessageContent() {
        return messageContent;
    }
    
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
    
    public String getMessageType() {
        return messageType;
    }
    
    public void setMessageType(String messageType) {
        this.messageType = messageType;
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
    
    public String getSenderNickname() {
        return senderNickname;
    }
    
    public void setSenderNickname(String senderNickname) {
        this.senderNickname = senderNickname;
    }
    
    @Override
    public String toString() {
        return "ChatMessageVO{" +
                "chatMessageId=" + chatMessageId +
                ", chatRoomId=" + chatRoomId +
                ", senderId='" + senderId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageType='" + messageType + '\'' +
                ", createdAt=" + createdAt +
                ", senderNickname='" + senderNickname + '\'' +
                '}';
    }
}
