package com.moc.pro.freeboard.dao;

import com.moc.pro.freeboard.vo.FreeboardVO;
import com.moc.pro.freeboard.vo.FreeboardCommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 자유게시판 DAO 구현체
 */
@Repository
public class FreeboardDAOImpl implements FreeboardDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.freeboard.dao.FreeboardDAO";
    
    @Override
    public List<FreeboardVO> selectList(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".selectList", params);
    }
    
    @Override
    public int selectTotalCount() {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalCount");
    }
    
    @Override
    public List<FreeboardVO> searchByKeyword(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".searchByKeyword", params);
    }
    
    @Override
    public int selectSearchCount(String keyword) {
        return sqlSession.selectOne(NAMESPACE + ".selectSearchCount", keyword);
    }
    
    @Override
    public FreeboardVO selectById(int freeboardId) {
        return sqlSession.selectOne(NAMESPACE + ".selectById", freeboardId);
    }
    
    @Override
    public int insertFreeboard(FreeboardVO freeboard) {
        return sqlSession.insert(NAMESPACE + ".insertFreeboard", freeboard);
    }
    
    @Override
    public int updateFreeboard(FreeboardVO freeboard) {
        return sqlSession.update(NAMESPACE + ".updateFreeboard", freeboard);
    }
    
    @Override
    public int deleteFreeboard(int freeboardId) {
        return sqlSession.delete(NAMESPACE + ".deleteFreeboard", freeboardId);
    }
    
    @Override
    public int checkAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkAuthor", params);
    }
    
    // ===== 댓글 관련 =====
    
    @Override
    public List<FreeboardCommentVO> selectCommentsByBoardId(int freeboardId) {
        return sqlSession.selectList(NAMESPACE + ".selectCommentsByBoardId", freeboardId);
    }
    
    @Override
    public int insertComment(FreeboardCommentVO comment) {
        return sqlSession.insert(NAMESPACE + ".insertComment", comment);
    }
    
    @Override
    public int deleteComment(int commentId) {
        return sqlSession.delete(NAMESPACE + ".deleteComment", commentId);
    }
    
    @Override
    public int checkCommentAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkCommentAuthor", params);
    }
}
