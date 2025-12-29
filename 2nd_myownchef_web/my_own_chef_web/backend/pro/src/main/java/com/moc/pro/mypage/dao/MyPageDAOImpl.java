package com.moc.pro.mypage.dao;

import com.moc.pro.mypage.vo.MyPagePostVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 마이페이지 DAO 구현체
 * MyBatis SqlSession을 이용한 데이터 접근
 */
@Repository
public class MyPageDAOImpl implements MyPageDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public List<MyPagePostVO> selectMyPosts(Map<String, Object> params) {
        return sqlSession.selectList("com.moc.pro.mypage.dao.MyPageDAO.selectMyPosts", params);
    }
    
    @Override
    public int selectMyPostsTotalCount(String userId) {
        return sqlSession.selectOne("com.moc.pro.mypage.dao.MyPageDAO.selectMyPostsTotalCount", userId);
    }
    
    @Override
    public List<MyPagePostVO> selectSavedPosts(Map<String, Object> params) {
        return sqlSession.selectList("com.moc.pro.mypage.dao.MyPageDAO.selectSavedPosts", params);
    }
    
    @Override
    public int selectSavedPostsTotalCount(String userId) {
        return sqlSession.selectOne("com.moc.pro.mypage.dao.MyPageDAO.selectSavedPostsTotalCount", userId);
    }
}
