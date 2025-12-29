package com.moc.pro.chat.service;

import com.moc.pro.chat.dao.ChatDAO;
import com.moc.pro.chat.vo.ChatRoomVO;
import com.moc.pro.chat.vo.ChatMessageVO;
import com.moc.pro.sharetool.service.ShareToolService;
import com.moc.pro.withshopping.service.WithShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 채팅 Service 구현체
 */
@Service
public class ChatServiceImpl implements ChatService {
    
    @Autowired
    private ChatDAO chatDAO;
    
    @Autowired
    private ShareToolService shareToolService;
    
    @Autowired
    private WithShoppingService withShoppingService;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @Override
    @Transactional
    public int createOrGetRoom(int postId, String postType, String ownerId, String participantId) {
        // 1. 기존 채팅방 확인
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("ownerId", ownerId);
        params.put("participantId", participantId);
        
        ChatRoomVO existingRoom = chatDAO.selectRoomByPostAndUsers(params);
        
        if (existingRoom != null) {
            // 기존 채팅방 반환
            return existingRoom.getChatRoomId();
        }
        
        // 2. 새 채팅방 생성
        ChatRoomVO newRoom = new ChatRoomVO();
        newRoom.setPostId(postId);
        newRoom.setPostType(postType);
        newRoom.setOwnerId(ownerId);
        newRoom.setParticipantId(participantId);
        
        chatDAO.insertChatRoom(newRoom);
        int newRoomId = newRoom.getChatRoomId();
        
        // 3. 신청 자동 메시지 추가 (신규 생성 시에만)
        ChatMessageVO welcomeMessage = new ChatMessageVO();
        welcomeMessage.setChatRoomId(newRoomId);
        welcomeMessage.setSenderId("system");
        
        String postTypeKor = "sharetool".equals(postType) ? "나눔" : "장보기";
        welcomeMessage.setMessageContent("신청이 접수되었습니다.");
        welcomeMessage.setMessageType("SYSTEM");
        
        chatDAO.insertSystemMessage(welcomeMessage);
        
        return newRoomId;
    }
    
    @Override
    public ChatRoomVO getRoomInfo(int chatRoomId) {
        return chatDAO.selectRoomById(chatRoomId);
    }
    
    @Override
    public List<ChatMessageVO> getMessages(int chatRoomId) {
        return chatDAO.selectMessagesByRoomId(chatRoomId);
    }
    
    @Override
    @Transactional
    public boolean saveMessage(int chatRoomId, String senderId, String content) {
        ChatMessageVO message = new ChatMessageVO();
        message.setChatRoomId(chatRoomId);
        message.setSenderId(senderId);
        message.setMessageContent(content);
        
        int result = chatDAO.insertMessage(message);
        return result > 0;
    }
    
    @Override
    @Transactional
    public boolean acceptRoom(int chatRoomId, String userId) {
        // 1. 채팅방 정보 조회
        ChatRoomVO room = chatDAO.selectRoomById(chatRoomId);
        if (room == null) {
            return false;
        }
        
        // 2. 작성자 확인 (owner만 수락 가능)
        if (!room.getOwnerId().equals(userId)) {
            return false;
        }
        
        // 3. 채팅방 수락 처리
        chatDAO.updateRoomAccepted(chatRoomId);
        
        // 4. 수락 메시지 저장
        ChatMessageVO acceptMessage = new ChatMessageVO();
        acceptMessage.setChatRoomId(chatRoomId);
        acceptMessage.setSenderId(userId);
        acceptMessage.setMessageContent("신청이 수락되었습니다");
        acceptMessage.setMessageType("ACCEPT");
        acceptMessage.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        chatDAO.insertSystemMessage(acceptMessage);
        
        // 5. 수락 메시지 실시간 브로드캐스트
        messagingTemplate.convertAndSend("/topic/chat/" + chatRoomId, acceptMessage);
        
        // 6. 게시글 상태 완료 처리
        if ("sharetool".equals(room.getPostType())) {
            shareToolService.completeShare(room.getPostId());
        } else if ("shopping".equals(room.getPostType())) {
            withShoppingService.completeShopping(room.getPostId());
        }
        
        return true;
    }
    
    @Override
    @Transactional
    public boolean rejectRoom(int chatRoomId, String userId) {
        // 1. 채팅방 정보 조회
        ChatRoomVO room = chatDAO.selectRoomById(chatRoomId);
        if (room == null) {
            return false;
        }
        
        // 2. 작성자 확인 (owner만 거절 가능)
        if (!room.getOwnerId().equals(userId)) {
            return false;
        }
        
        // 3. 퇴장 메시지 저장
        ChatMessageVO exitMessage = new ChatMessageVO();
        exitMessage.setChatRoomId(chatRoomId);
        exitMessage.setSenderId("system");
        exitMessage.setMessageContent("상대방이 채팅창을 나갔습니다");
        exitMessage.setMessageType("EXIT");
        exitMessage.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        chatDAO.insertSystemMessage(exitMessage);
        
        // 4. 퇴장 메시지 실시간 브로드캐스트
        messagingTemplate.convertAndSend("/topic/chat/" + chatRoomId, exitMessage);
        
        // 5. 거절한 사람만 채팅방 숨김 처리 (ROOM_STATUS는 변경하지 않음)
        Map<String, Object> hideParams = new HashMap<>();
        hideParams.put("chatRoomId", chatRoomId);
        hideParams.put("userId", userId);  // 거절한 사람(owner)만 숨김
        
        int result = chatDAO.insertHiddenRoom(hideParams);
        return result > 0;
    }
    
    @Override
    @Transactional
    public boolean hideRoom(int chatRoomId, String userId) {
        // 중복 숨김 체크
        Map<String, Object> checkParams = new HashMap<>();
        checkParams.put("chatRoomId", chatRoomId);
        checkParams.put("userId", userId);
        
        int hiddenCount = chatDAO.checkHiddenRoom(checkParams);
        if (hiddenCount > 0) {
            return true; // 이미 숨김 처리됨
        }
        
        // 1. 퇴장 메시지 저장
        ChatMessageVO exitMessage = new ChatMessageVO();
        exitMessage.setChatRoomId(chatRoomId);
        exitMessage.setSenderId("system");
        exitMessage.setMessageContent("상대방이 채팅창을 나갔습니다");
        exitMessage.setMessageType("EXIT");
        exitMessage.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        chatDAO.insertSystemMessage(exitMessage);
        
        // 2. 퇴장 메시지 실시간 브로드캐스트
        messagingTemplate.convertAndSend("/topic/chat/" + chatRoomId, exitMessage);
        
        // 3. 채팅방 숨김 처리
        int result = chatDAO.insertHiddenRoom(checkParams);
        return result > 0;
    }
    
    @Override
    public List<ChatRoomVO> getUserRooms(String userId) {
        return chatDAO.selectRoomsByUserId(userId);
    }
    
    @Override
    public Map<String, Object> checkRoomExists(int postId, String postType, String userId) {
        Map<String, Object> result = new HashMap<>();
        
        // 1. postType에 따라 작성자(ownerId) 조회
        String ownerId = null;
        if ("sharetool".equals(postType)) {
            Map<String, Object> shareToolDetail = shareToolService.getDetail(postId);
            if (shareToolDetail != null && shareToolDetail.get("shareTool") != null) {
                com.moc.pro.sharetool.vo.ShareToolVO shareTool = 
                    (com.moc.pro.sharetool.vo.ShareToolVO) shareToolDetail.get("shareTool");
                ownerId = shareTool.getUserId();
            }
        } else if ("shopping".equals(postType)) {
            com.moc.pro.withshopping.vo.WithShoppingVO withShopping = 
                withShoppingService.getDetail(postId);
            if (withShopping != null) {
                ownerId = withShopping.getUserId();
            }
        }
        
        // 2. 작성자를 찾지 못한 경우
        if (ownerId == null) {
            result.put("exists", false);
            result.put("roomId", null);
            return result;
        }
        
        // 3. 기존 채팅방 확인
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);
        params.put("ownerId", ownerId);
        params.put("participantId", userId);
        
        ChatRoomVO existingRoom = chatDAO.selectRoomByPostAndUsers(params);
        
        if (existingRoom != null) {
            result.put("exists", true);
            result.put("roomId", existingRoom.getChatRoomId());
        } else {
            result.put("exists", false);
            result.put("roomId", null);
        }
        
        return result;
    }
    
    @Override
    @Transactional
    public boolean markRoomAsRead(int chatRoomId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("chatRoomId", chatRoomId);
        params.put("userId", userId);
        
        int result = chatDAO.upsertChatRoomRead(params);
        return result > 0;
    }
}
