package com.moc.pro.withshopping.dao;

import com.moc.pro.withshopping.vo.WithShoppingVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 같이쇼핑 DAO 구현체
 */
@Repository
public class WithShoppingDAOImpl implements WithShoppingDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.withshopping.WithShoppingMapper";
    
    @Override
    public List<WithShoppingVO> selectList(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".selectList", params);
    }
    
    @Override
    public int selectTotalCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalCount", params);
    }
    
    @Override
    public List<WithShoppingVO> searchByKeyword(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".searchByKeyword", params);
    }
    
    @Override
    public int selectSearchCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectSearchCount", params);
    }
    
    @Override
    public WithShoppingVO selectById(int withShoppingId) {
        return sqlSession.selectOne(NAMESPACE + ".selectById", withShoppingId);
    }
    
    @Override
    public int insertWithShopping(WithShoppingVO withShopping) {
        return sqlSession.insert(NAMESPACE + ".insertWithShopping", withShopping);
    }
    
    @Override
    public int updateWithShopping(WithShoppingVO withShopping) {
        return sqlSession.update(NAMESPACE + ".updateWithShopping", withShopping);
    }
    
    @Override
    public int deleteWithShopping(int withShoppingId) {
        return sqlSession.delete(NAMESPACE + ".deleteWithShopping", withShoppingId);
    }
    
    @Override
    public int checkAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkAuthor", params);
    }
    
    @Override
    public int updateStatusComplete(int withShoppingId) {
        return sqlSession.update(NAMESPACE + ".updateStatusComplete", withShoppingId);
    }
}
