package com.moc.pro.freeboard.service;

import com.moc.pro.freeboard.dao.FreeboardDAO;
import com.moc.pro.freeboard.vo.FreeboardVO;
import com.moc.pro.freeboard.vo.FreeboardCommentVO;
import com.moc.pro.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 자유게시판 Service 구현체
 */
@Service
public class FreeboardServiceImpl implements FreeboardService {
    
    @Autowired
    private FreeboardDAO freeboardDAO;
    
    @Autowired
    private NotificationService notificationService;
    
    private static final int ITEMS_PER_PAGE = 10; // 페이지당 게시글 수
    
    @Override
    public Map<String, Object> getList(int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 목록 조회
        List<FreeboardVO> posts = freeboardDAO.selectList(params);
        
        // 전체 페이지 수 계산
        int totalCount = freeboardDAO.selectTotalCount();
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public Map<String, Object> searchPosts(String keyword, int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", keyword);
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 검색 실행
        List<FreeboardVO> posts = freeboardDAO.searchByKeyword(params);
        
        // 검색 결과 전체 페이지 수 계산
        int totalCount = freeboardDAO.selectSearchCount(keyword);
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        result.put("keyword", keyword);
        
        return result;
    }
    
    @Override
    public FreeboardVO getDetail(int freeboardId) {
        return freeboardDAO.selectById(freeboardId);
    }
    
    @Override
    public boolean createPost(FreeboardVO freeboard) {
        try {
            int result = freeboardDAO.insertFreeboard(freeboard);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updatePost(FreeboardVO freeboard) {
        try {
            int result = freeboardDAO.updateFreeboard(freeboard);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deletePost(int freeboardId, String userId) {
        try {
            // 작성자 확인
            Map<String, Object> params = new HashMap<>();
            params.put("freeboardId", freeboardId);
            params.put("userId", userId);
            
            int isAuthor = freeboardDAO.checkAuthor(params);
            if (isAuthor == 0) {
                return false; // 작성자가 아니면 삭제 불가
            }
            
            // 삭제 실행
            int result = freeboardDAO.deleteFreeboard(freeboardId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean checkAuthor(int freeboardId, String userId) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("freeboardId", freeboardId);
            params.put("userId", userId);
            
            int count = freeboardDAO.checkAuthor(params);
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // ===== 댓글 관련 =====
    
    @Override
    public List<FreeboardCommentVO> getComments(int freeboardId) {
        return freeboardDAO.selectCommentsByBoardId(freeboardId);
    }
    
    @Override
    @Transactional
    public boolean createComment(FreeboardCommentVO comment) {
        try {
            // 1. 댓글 저장
            int result = freeboardDAO.insertComment(comment);
            if (result == 0) {
                return false;
            }
            
            // 2. 게시글 작성자 조회
            FreeboardVO freeboard = freeboardDAO.selectById(comment.getFreeboardId());
            if (freeboard != null) {
                // 3. 알림 생성 (본인 댓글은 제외)
                notificationService.createCommentNotification(
                    comment.getFreeboardId(),
                    "freeboard",
                    freeboard.getUserId(),
                    comment.getUserId(),
                    comment.getFreeboardCommentId()
                );
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteComment(int commentId, String userId) {
        try {
            // 댓글 작성자 확인
            Map<String, Object> params = new HashMap<>();
            params.put("commentId", commentId);
            params.put("userId", userId);
            
            int isAuthor = freeboardDAO.checkCommentAuthor(params);
            if (isAuthor == 0) {
                return false; // 작성자가 아니면 삭제 불가
            }
            
            // 삭제 실행
            int result = freeboardDAO.deleteComment(commentId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
