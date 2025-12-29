package com.moc.pro.mypage.service;

import com.moc.pro.mypage.dao.MyPageDAO;
import com.moc.pro.mypage.vo.MyPagePostVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 마이페이지 Service 구현체
 * 마이페이지 관련 비즈니스 로직 처리
 */
@Service
public class MyPageServiceImpl implements MyPageService {
    
    @Autowired
    private MyPageDAO myPageDAO;
    
    private static final int ITEMS_PER_PAGE = 5; // ⭐ 페이지당 5개 표시
    
    @Override
    public Map<String, Object> getMyPosts(String userId, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 목록 조회
        List<MyPagePostVO> posts = myPageDAO.selectMyPosts(params);
        
        // 전체 페이지 수 계산
        int totalCount = myPageDAO.selectMyPostsTotalCount(userId);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        // 결과 포맷팅
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> getSavedPosts(String userId, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 목록 조회
        List<MyPagePostVO> posts = myPageDAO.selectSavedPosts(params);
        
        // 전체 페이지 수 계산
        int totalCount = myPageDAO.selectSavedPostsTotalCount(userId);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        // 결과 포맷팅
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
}
