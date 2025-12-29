package com.moc.pro.chat.dao;

import com.moc.pro.chat.vo.ChatRoomVO;
import com.moc.pro.chat.vo.ChatMessageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 채팅 DAO 구현체
 */
@Repository
public class ChatDAOImpl implements ChatDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.chat.ChatMapper";
    
    // ===== 채팅방 =====
    
    @Override
    public int insertChatRoom(ChatRoomVO chatRoom) {
        return sqlSession.insert(NAMESPACE + ".insertChatRoom", chatRoom);
    }
    
    @Override
    public ChatRoomVO selectRoomByPostAndUsers(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectRoomByPostAndUsers", params);
    }
    
    @Override
    public ChatRoomVO selectRoomById(int chatRoomId) {
        return sqlSession.selectOne(NAMESPACE + ".selectRoomById", chatRoomId);
    }
    
    @Override
    public int updateRoomAccepted(int chatRoomId) {
        return sqlSession.update(NAMESPACE + ".updateRoomAccepted", chatRoomId);
    }
    
    @Override
    public int updateRoomStatus(Map<String, Object> params) {
        return sqlSession.update(NAMESPACE + ".updateRoomStatus", params);
    }
    
    @Override
    public List<ChatRoomVO> selectRoomsByUserId(String userId) {
        return sqlSession.selectList(NAMESPACE + ".selectRoomsByUserId", userId);
    }
    
    // ===== 채팅 메시지 =====
    
    @Override
    public List<ChatMessageVO> selectMessagesByRoomId(int chatRoomId) {
        return sqlSession.selectList(NAMESPACE + ".selectMessagesByRoomId", chatRoomId);
    }
    
    @Override
    public int insertMessage(ChatMessageVO message) {
        return sqlSession.insert(NAMESPACE + ".insertMessage", message);
    }
    
    @Override
    public int insertSystemMessage(ChatMessageVO message) {
        return sqlSession.insert(NAMESPACE + ".insertSystemMessage", message);
    }
    
    // ===== 채팅방 숨김 =====
    
    @Override
    public int insertHiddenRoom(Map<String, Object> params) {
        return sqlSession.insert(NAMESPACE + ".insertHiddenRoom", params);
    }
    
    @Override
    public int checkHiddenRoom(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkHiddenRoom", params);
    }
    
    // ===== 채팅방 읽음 처리 =====
    
    @Override
    public int upsertChatRoomRead(Map<String, Object> params) {
        return sqlSession.insert(NAMESPACE + ".upsertChatRoomRead", params);
    }
}
