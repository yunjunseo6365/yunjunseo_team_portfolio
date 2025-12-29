package com.moc.pro.withshopping.service;

import com.moc.pro.withshopping.dao.WithShoppingDAO;
import com.moc.pro.withshopping.vo.WithShoppingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 같이쇼핑 Service 구현체
 */
@Service
public class WithShoppingServiceImpl implements WithShoppingService {
    
    @Autowired
    private WithShoppingDAO withShoppingDAO;
    
    private static final int ITEMS_PER_PAGE = 9; // 페이지당 게시글 수
    
    @Override
    public Map<String, Object> getList(String si, String gu, String status, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        params.put("si", si);
        params.put("gu", gu);
        params.put("status", status);
        
        // 목록 조회
        List<WithShoppingVO> posts = withShoppingDAO.selectList(params);
        
        // 전체 페이지 수 계산
        Map<String, Object> countParams = new HashMap<>();
        countParams.put("si", si);
        countParams.put("gu", gu);
        countParams.put("status", status);
        int totalCount = withShoppingDAO.selectTotalCount(countParams);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> searchPosts(String keyword, String si, String gu, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("si", si);
        params.put("gu", gu);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 검색 실행
        List<WithShoppingVO> posts = withShoppingDAO.searchByKeyword(params);
        
        // 검색 결과 전체 페이지 수 계산
        Map<String, Object> countParams = new HashMap<>();
        countParams.put("keyword", keyword);
        countParams.put("si", si);
        countParams.put("gu", gu);
        int totalCount = withShoppingDAO.selectSearchCount(countParams);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        result.put("keyword", keyword);
        
        return result;
    }
    
    @Override
    public WithShoppingVO getDetail(int withShoppingId) {
        return withShoppingDAO.selectById(withShoppingId);
    }
    
    @Override
    @Transactional
    public boolean createWithShopping(WithShoppingVO withShopping) {
        try {
            withShoppingDAO.insertWithShopping(withShopping);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean updateWithShopping(WithShoppingVO withShopping) {
        try {
            withShoppingDAO.updateWithShopping(withShopping);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    @Transactional
    public boolean deleteWithShopping(int withShoppingId) {
        try {
            withShoppingDAO.deleteWithShopping(withShoppingId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean checkAuthor(int withShoppingId, String userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("withShoppingId", withShoppingId);
        params.put("userId", userId);
        
        int count = withShoppingDAO.checkAuthor(params);
        return count > 0;
    }
    
    @Override
    @Transactional
    public boolean completeShopping(int withShoppingId) {
        try {
            withShoppingDAO.updateStatusComplete(withShoppingId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
