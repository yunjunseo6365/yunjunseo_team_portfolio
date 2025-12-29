package com.moc.pro.notice.dao;

import com.moc.pro.notice.vo.NoticeVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 공지사항 DAO 구현체
 */
@Repository
public class NoticeDAOImpl implements NoticeDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.notice.dao.NoticeDAO";
    
    @Override
    public List<NoticeVO> selectList(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".selectList", params);
    }
    
    @Override
    public int selectTotalCount() {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalCount");
    }
    
    @Override
    public NoticeVO selectById(int noticeId) {
        return sqlSession.selectOne(NAMESPACE + ".selectById", noticeId);
    }
    
    @Override
    public int insertNotice(NoticeVO notice) {
        return sqlSession.insert(NAMESPACE + ".insertNotice", notice);
    }
    
    @Override
    public int updateNotice(NoticeVO notice) {
        return sqlSession.update(NAMESPACE + ".updateNotice", notice);
    }
    
    @Override
    public int deleteNotice(int noticeId) {
        return sqlSession.delete(NAMESPACE + ".deleteNotice", noticeId);
    }
}
