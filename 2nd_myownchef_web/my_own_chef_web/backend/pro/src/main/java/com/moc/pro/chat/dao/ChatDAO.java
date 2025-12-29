package com.moc.pro.chat.dao;

import com.moc.pro.chat.vo.ChatRoomVO;
import com.moc.pro.chat.vo.ChatMessageVO;

import java.util.List;
import java.util.Map;

/**
 * 채팅 DAO 인터페이스
 */
public interface ChatDAO {
    
    // ===== 채팅방 =====
    
    /**
     * 채팅방 생성
     * @param chatRoom 채팅방 정보
     * @return 생성된 행 수
     */
    int insertChatRoom(ChatRoomVO chatRoom);
    
    /**
     * 채팅방 조회 (중복 확인용)
     * @param params postId, ownerId, participantId
     * @return 채팅방 정보 (없으면 null)
     */
    ChatRoomVO selectRoomByPostAndUsers(Map<String, Object> params);
    
    /**
     * 채팅방 ID로 조회
     * @param chatRoomId 채팅방 ID
     * @return 채팅방 정보
     */
    ChatRoomVO selectRoomById(int chatRoomId);
    
    /**
     * 채팅방 수락 처리
     * @param chatRoomId 채팅방 ID
     * @return 수정된 행 수
     */
    int updateRoomAccepted(int chatRoomId);
    
    /**
     * 채팅방 상태 변경 (CLOSED)
     * @param params chatRoomId, roomStatus
     * @return 수정된 행 수
     */
    int updateRoomStatus(Map<String, Object> params);
    
    /**
     * 사용자별 채팅방 목록 조회
     * @param userId 사용자 ID
     * @return 채팅방 목록
     */
    List<ChatRoomVO> selectRoomsByUserId(String userId);
    
    // ===== 채팅 메시지 =====
    
    /**
     * 채팅방 메시지 목록 조회
     * @param chatRoomId 채팅방 ID
     * @return 메시지 목록
     */
    List<ChatMessageVO> selectMessagesByRoomId(int chatRoomId);
    
    /**
     * 메시지 저장
     * @param message 메시지 정보
     * @return 저장된 행 수
     */
    int insertMessage(ChatMessageVO message);
    
    /**
     * 시스템 메시지 저장 (수락/거절/퇴장)
     * @param message 시스템 메시지 정보
     * @return 저장된 행 수
     */
    int insertSystemMessage(ChatMessageVO message);
    
    // ===== 채팅방 숨김 =====
    
    /**
     * 채팅방 숨김 처리
     * @param params chatRoomId, userId
     * @return 저장된 행 수
     */
    int insertHiddenRoom(Map<String, Object> params);
    
    /**
     * 채팅방 숨김 여부 확인
     * @param params chatRoomId, userId
     * @return 숨김 개수 (1: 숨김, 0: 안 숨김)
     */
    int checkHiddenRoom(Map<String, Object> params);
    
    // ===== 채팅방 읽음 처리 =====
    
    /**
     * 채팅방 읽음 시간 기록 (MERGE)
     * @param params chatRoomId, userId
     * @return 처리된 행 수
     */
    int upsertChatRoomRead(Map<String, Object> params);
}
