package com.moc.pro.admin.dao;

import com.moc.pro.admin.vo.AdminPostVO;
import com.moc.pro.admin.vo.AdminUserVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 관리자 DAO 구현체
 */
@Repository
public class AdminDAOImpl implements AdminDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.admin.dao.AdminDAO.";
    
    @Override
    public List<AdminUserVO> selectUsers(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + "selectUsers", params);
    }
    
    @Override
    public int selectUsersTotalCount(String nickname) {
        return sqlSession.selectOne(NAMESPACE + "selectUsersTotalCount", nickname);
    }
    
    @Override
    public int deleteUser(String userId) {
        return sqlSession.update(NAMESPACE + "deleteUser", userId);
    }
    
    @Override
    public List<AdminPostVO> selectPosts(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + "selectPosts", params);
    }
    
    @Override
    public int selectPostsTotalCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + "selectPostsTotalCount", params);
    }
    
    @Override
    public int deletePost(String boardType, int postId) {
        Map<String, Object> params = new HashMap<>();
        params.put("boardType", boardType);
        params.put("postId", postId);
        return sqlSession.delete(NAMESPACE + "deletePost", params);
    }
}
