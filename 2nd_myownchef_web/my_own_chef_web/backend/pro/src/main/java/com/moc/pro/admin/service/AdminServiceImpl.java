package com.moc.pro.admin.service;

import com.moc.pro.admin.dao.AdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 관리자 Service 구현체
 */
@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminDAO adminDAO;
    
    private static final int ITEMS_PER_PAGE = 10; // ⭐ Admin 10개/페이지
    
    @Override
    public Map<String, Object> getUsers(int page, String nickname) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        params.put("nickname", nickname);
        
        // 목록 조회
        var users = adminDAO.selectUsers(params);
        
        // 전체 페이지 수 계산
        int totalCount = adminDAO.selectUsersTotalCount(nickname);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("users", users);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public boolean deleteUser(String userId) {
        int result = adminDAO.deleteUser(userId);
        return result > 0;
    }
    
    @Override
    public Map<String, Object> getPosts(int page, String title, List<String> categories) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        params.put("title", title);
        params.put("categories", categories);
        
        // 목록 조회
        var posts = adminDAO.selectPosts(params);
        
        // 전체 페이지 수 계산
        Map<String, Object> countParams = new HashMap<>();
        countParams.put("title", title);
        countParams.put("categories", categories);
        int totalCount = adminDAO.selectPostsTotalCount(countParams);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public boolean deletePost(String boardType, int postId) {
        int result = adminDAO.deletePost(boardType, postId);
        return result > 0;
    }
}
