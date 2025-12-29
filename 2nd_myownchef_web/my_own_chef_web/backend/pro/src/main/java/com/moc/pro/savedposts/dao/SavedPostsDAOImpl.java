package com.moc.pro.savedposts.dao;

import com.moc.pro.savedposts.vo.SavedPostsVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 저장된 게시글 DAO 구현체
 */
@Repository
public class SavedPostsDAOImpl implements SavedPostsDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.savedposts.";
    
    @Override
    public int insertSavedPost(SavedPostsVO savedPost) {
        return sqlSession.insert(NAMESPACE + "insertSavedPost", savedPost);
    }
    
    @Override
    public int deleteSavedPost(Map<String, Object> params) {
        return sqlSession.delete(NAMESPACE + "deleteSavedPost", params);
    }
    
    @Override
    public int checkSavedPost(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + "checkSavedPost", params);
    }
}
