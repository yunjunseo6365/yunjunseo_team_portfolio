package com.moc.pro.savedposts.service;

import com.moc.pro.savedposts.dao.SavedPostsDAO;
import com.moc.pro.savedposts.vo.SavedPostsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 저장된 게시글 Service 구현체
 */
@Service
public class SavedPostsServiceImpl implements SavedPostsService {
    
    @Autowired
    private SavedPostsDAO savedPostsDAO;
    
    @Override
    public boolean savePost(String userId, String boardType, int postId) {
        try {
            // 중복 확인
            Map<String, Object> params = new HashMap<>();
            params.put("userId", userId);
            params.put("boardType", boardType);
            params.put("postId", postId);
            
            int count = savedPostsDAO.checkSavedPost(params);
            if (count > 0) {
                return false; // 이미 저장함
            }
            
            // 저장
            SavedPostsVO savedPost = new SavedPostsVO();
            savedPost.setUserId(userId);
            savedPost.setBoardType(boardType);
            savedPost.setPostId(postId);
            
            int result = savedPostsDAO.insertSavedPost(savedPost);
            return result > 0;
            
        } catch (Exception e) {
            // UNIQUE 제약조건 위반 시
            return false;
        }
    }
    
    @Override
    public boolean unsavePost(String userId, String boardType, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("boardType", boardType);
        params.put("postId", postId);
        
        int result = savedPostsDAO.deleteSavedPost(params);
        return result > 0;
    }
    
    @Override
    public boolean checkSavedPost(String userId, String boardType, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("boardType", boardType);
        params.put("postId", postId);
        
        int count = savedPostsDAO.checkSavedPost(params);
        return count > 0;
    }
}
