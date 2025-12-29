package com.moc.pro.chat.service;

import com.moc.pro.chat.vo.ChatRoomVO;
import com.moc.pro.chat.vo.ChatMessageVO;

import java.util.List;
import java.util.Map;

/**
 * 채팅 Service 인터페이스
 */
public interface ChatService {
    
    /**
     * 채팅방 생성 또는 조회 (중복 방지)
     * @param postId 게시글 ID
     * @param postType 게시판 타입 (sharetool, shopping)
     * @param ownerId 게시글 작성자 ID
     * @param participantId 신청자 ID
     * @return 채팅방 ID
     */
    int createOrGetRoom(int postId, String postType, String ownerId, String participantId);
    
    /**
     * 채팅방 정보 조회
     * @param chatRoomId 채팅방 ID
     * @return 채팅방 정보
     */
    ChatRoomVO getRoomInfo(int chatRoomId);
    
    /**
     * 채팅방 메시지 목록 조회
     * @param chatRoomId 채팅방 ID
     * @return 메시지 목록
     */
    List<ChatMessageVO> getMessages(int chatRoomId);
    
    /**
     * 메시지 저장 (WebSocket에서 호출)
     * @param chatRoomId 채팅방 ID
     * @param senderId 발신자 ID
     * @param content 메시지 내용
     * @return 저장 성공 여부
     */
    boolean saveMessage(int chatRoomId, String senderId, String content);
    
    /**
     * 채팅방 수락 처리 (게시글 상태 완료 변경)
     * @param chatRoomId 채팅방 ID
     * @param userId 수락자 ID (작성자 확인용)
     * @return 수락 성공 여부
     */
    boolean acceptRoom(int chatRoomId, String userId);
    
    /**
     * 채팅방 거절 처리 (채팅방 종료)
     * @param chatRoomId 채팅방 ID
     * @param userId 거절자 ID (작성자 확인용)
     * @return 거절 성공 여부
     */
    boolean rejectRoom(int chatRoomId, String userId);
    
    /**
     * 채팅방 숨김 처리
     * @param chatRoomId 채팅방 ID
     * @param userId 사용자 ID
     * @return 숨김 성공 여부
     */
    boolean hideRoom(int chatRoomId, String userId);
    
    /**
     * 사용자별 채팅방 목록 조회
     * @param userId 사용자 ID
     * @return 채팅방 목록
     */
    List<ChatRoomVO> getUserRooms(String userId);
    
    /**
     * 특정 게시글에 대한 사용자의 채팅방 존재 여부 확인
     * @param postId 게시글 ID
     * @param postType 게시판 타입 (sharetool, shopping)
     * @param userId 사용자 ID
     * @return { exists: boolean, roomId: Integer|null }
     */
    Map<String, Object> checkRoomExists(int postId, String postType, String userId);
    
    /**
     * 채팅방 읽음 처리
     * @param chatRoomId 채팅방 ID
     * @param userId 사용자 ID
     * @return 성공 여부
     */
    boolean markRoomAsRead(int chatRoomId, String userId);
}
