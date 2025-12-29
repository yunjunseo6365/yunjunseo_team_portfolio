package com.moc.pro.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

/**
 * 채팅 Controller 인터페이스
 */
@RequestMapping("/api/chat")
public interface ChatController {
    
    /**
     * 채팅방 생성/조회
     * POST /api/chat/rooms
     * @param request postId, postType, ownerId
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> createRoom(Map<String, Object> request, HttpSession session);
    
    /**
     * 채팅방 메시지 목록 조회
     * GET /api/chat/rooms/{roomId}/messages
     * @param roomId 채팅방 ID
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getMessages(int roomId);
    
    /**
     * 채팅방 수락 (게시글 완료 처리)
     * POST /api/chat/rooms/{roomId}/accept
     * @param roomId 채팅방 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> acceptRoom(int roomId, HttpSession session);
    
    /**
     * 채팅방 거절 (채팅방 종료)
     * POST /api/chat/rooms/{roomId}/reject
     * @param roomId 채팅방 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> rejectRoom(int roomId, HttpSession session);
    
    /**
     * 채팅방 숨김
     * POST /api/chat/rooms/{roomId}/hide
     * @param roomId 채팅방 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> hideRoom(int roomId, HttpSession session);
    
    /**
     * 사용자별 채팅방 목록 조회
     * GET /api/chat/rooms
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getUserRooms(HttpSession session);
    
    /**
     * 특정 게시글에 대한 채팅방 존재 여부 확인
     * GET /api/chat/rooms/check
     * @param postId 게시글 ID
     * @param postType 게시판 타입 (sharetool, shopping)
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> checkRoomExists(int postId, String postType, HttpSession session);
    
    /**
     * 채팅방 정보 조회
     * GET /api/chat/rooms/{roomId}/info
     * @param roomId 채팅방 ID
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> getRoomInfo(int roomId);
    
    /**
     * 채팅방 읽음 처리
     * PUT /api/chat/rooms/{roomId}/read
     * @param roomId 채팅방 ID
     * @param session HttpSession
     * @return ResponseEntity
     */
    ResponseEntity<Map<String, Object>> markRoomAsRead(int roomId, HttpSession session);
}
