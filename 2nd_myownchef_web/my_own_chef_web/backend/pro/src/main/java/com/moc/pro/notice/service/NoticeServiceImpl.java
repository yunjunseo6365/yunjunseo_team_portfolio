package com.moc.pro.notice.service;

import com.moc.pro.notice.dao.NoticeDAO;
import com.moc.pro.notice.vo.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 공지사항 Service 구현체
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    
    @Autowired
    private NoticeDAO noticeDAO;
    
    private static final int ITEMS_PER_PAGE = 10; // 페이지당 공지사항 수
    
    @Override
    public Map<String, Object> getList(int page) {
        Map<String, Object> result = new HashMap<>();
        
        // 페이징 계산
        int offset = (page - 1) * ITEMS_PER_PAGE;
        Map<String, Object> params = new HashMap<>();
        params.put("offset", offset);
        params.put("limit", ITEMS_PER_PAGE);
        
        // 목록 조회
        List<NoticeVO> posts = noticeDAO.selectList(params);
        
        // 전체 페이지 수 계산
        int totalCount = noticeDAO.selectTotalCount();
        int totalPage = (int) Math.ceil((double) totalCount / ITEMS_PER_PAGE);
        
        result.put("posts", posts);
        result.put("totalPage", totalPage);
        result.put("currentPage", page);
        result.put("totalCount", totalCount);
        
        return result;
    }
    
    @Override
    public NoticeVO getDetail(int noticeId) {
        return noticeDAO.selectById(noticeId);
    }
    
    @Override
    public boolean createNotice(NoticeVO notice) {
        try {
            int result = noticeDAO.insertNotice(notice);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean updateNotice(NoticeVO notice) {
        try {
            int result = noticeDAO.updateNotice(notice);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    @Override
    public boolean deleteNotice(int noticeId) {
        try {
            int result = noticeDAO.deleteNotice(noticeId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
