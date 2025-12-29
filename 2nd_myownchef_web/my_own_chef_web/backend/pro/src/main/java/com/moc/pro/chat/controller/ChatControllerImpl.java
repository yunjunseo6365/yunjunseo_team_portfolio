package com.moc.pro.chat.controller;

import com.moc.pro.chat.service.ChatService;
import com.moc.pro.chat.vo.ChatRoomVO;
import com.moc.pro.chat.vo.ChatMessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 채팅 Controller 구현체
 * REST API + WebSocket 통합
 */
@Controller
public class ChatControllerImpl implements ChatController {
    
    @Autowired
    private ChatService chatService;
    
    // ===== WebSocket 메시지 핸들러 =====
    
    /**
     * 채팅 메시지 수신 및 브로드캐스트
     * 
     * 클라이언트가 /app/chat/{roomId}로 메시지 전송
     * → 서버에서 DB 저장
     * → /topic/chat/{roomId}로 브로드캐스트
     * 
     * @param roomId 채팅방 ID
     * @param message 메시지 VO
     * @return 브로드캐스트할 메시지
     */
    @MessageMapping("/chat/{roomId}")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessageVO sendMessage(
            @DestinationVariable int roomId,
            ChatMessageVO message) {
        
        try {
            System.out.println("🔵 [WebSocket] 메시지 수신 - roomId: " + roomId + ", senderId: " + message.getSenderId());
            
            // 1. 채팅방 ID 설정
            message.setChatRoomId(roomId);
            
            // 2. 메시지 타입 기본값 설정 (없으면 TEXT)
            if (message.getMessageType() == null || message.getMessageType().isEmpty()) {
                message.setMessageType("TEXT");
            }
            
            // 3. DB에 메시지 저장
            chatService.saveMessage(
                message.getChatRoomId(),
                message.getSenderId(),
                message.getMessageContent()
            );
            System.out.println("✅ [WebSocket] DB 저장 완료");
            
            // 4. 타임스탬프 설정 (브로드캐스트용)
            message.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
            
            // 5. 모든 구독자에게 브로드캐스트
            System.out.println("📤 [WebSocket] 브로드캐스트 전송 - /topic/chat/" + roomId);
            return message;
            
        } catch (Exception e) {
            // 에러 발생 시 에러 메시지 전송
            ChatMessageVO errorMessage = new ChatMessageVO();
            errorMessage.setChatRoomId(roomId);
            errorMessage.setSenderId("system");
            errorMessage.setMessageContent("메시지 전송 실패: " + e.getMessage());
            errorMessage.setMessageType("SYSTEM");
            return errorMessage;
        }
    }
    
    /**
     * 시스템 메시지 전송 (수락, 거절, 퇴장 등)
     * 
     * @param roomId 채팅방 ID
     * @param message 시스템 메시지
     * @return 브로드캐스트할 메시지
     */
    @MessageMapping("/chat/{roomId}/system")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessageVO sendSystemMessage(
            @DestinationVariable int roomId,
            ChatMessageVO message) {
        
        message.setChatRoomId(roomId);
        message.setSenderId("system");
        message.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        
        return message;
    }
    
    // ===== REST API =====
    
    @PostMapping("/rooms")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> createRoom(
            @RequestBody Map<String, Object> request,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // 세션에서 사용자 ID 가져오기
            String participantId = (String) session.getAttribute("userId");
            if (participantId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            // 요청 파라미터 추출
            int postId = Integer.parseInt(request.get("postId").toString());
            String postType = (String) request.get("postType");
            String ownerId = (String) request.get("ownerId");
            
            // 채팅방 생성/조회
            int chatRoomId = chatService.createOrGetRoom(postId, postType, ownerId, participantId);
            
            response.put("success", true);
            response.put("data", Map.of("chatRoomId", chatRoomId));
            response.put("message", "채팅방이 생성되었습니다.");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "채팅방 생성 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/rooms/{roomId}/messages")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getMessages(@PathVariable int roomId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            List<ChatMessageVO> messages = chatService.getMessages(roomId);
            
            response.put("success", true);
            response.put("data", messages);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "메시지 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/rooms/{roomId}/accept")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> acceptRoom(
            @PathVariable int roomId,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = chatService.acceptRoom(roomId, userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "수락되었습니다. 게시글 상태가 완료로 변경되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "수락 권한이 없거나 채팅방을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "수락 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/rooms/{roomId}/reject")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> rejectRoom(
            @PathVariable int roomId,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = chatService.rejectRoom(roomId, userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "거절되었습니다. 채팅방이 종료되었습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "거절 권한이 없거나 채팅방을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "거절 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PostMapping("/rooms/{roomId}/hide")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> hideRoom(
            @PathVariable int roomId,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = chatService.hideRoom(roomId, userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "채팅방이 숨겨졌습니다.");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "채팅방 숨김 실패.");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "채팅방 숨김 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/rooms")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getUserRooms(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            List<ChatRoomVO> rooms = chatService.getUserRooms(userId);
            
            response.put("success", true);
            response.put("data", rooms);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "채팅방 목록 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/rooms/check")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> checkRoomExists(
            @RequestParam int postId,
            @RequestParam String postType,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            Map<String, Object> result = chatService.checkRoomExists(postId, postType, userId);
            
            response.put("success", true);
            response.put("data", result);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "채팅방 확인 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @GetMapping("/rooms/{roomId}/info")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> getRoomInfo(@PathVariable int roomId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            ChatRoomVO room = chatService.getRoomInfo(roomId);
            
            if (room != null) {
                response.put("success", true);
                response.put("data", room);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "채팅방을 찾을 수 없습니다.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "채팅방 정보 조회 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    
    @PutMapping("/rooms/{roomId}/read")
    @ResponseBody
    @Override
    public ResponseEntity<Map<String, Object>> markRoomAsRead(
            @PathVariable int roomId,
            HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            String userId = (String) session.getAttribute("userId");
            if (userId == null) {
                response.put("success", false);
                response.put("message", "로그인이 필요합니다.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
            
            boolean success = chatService.markRoomAsRead(roomId, userId);
            
            if (success) {
                response.put("success", true);
                response.put("message", "읽음 처리 완료");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "읽음 처리 실패");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "읽음 처리 실패: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
