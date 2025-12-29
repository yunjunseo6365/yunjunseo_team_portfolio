package com.moc.pro.sharetool.dao;

import com.moc.pro.sharetool.vo.ShareToolVO;
import com.moc.pro.sharetool.vo.ShareToolImageVO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 요리도구 나눔 DAO 구현체
 */
@Repository
public class ShareToolDAOImpl implements ShareToolDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.sharetool.ShareToolMapper";
    
    // ===== 요리도구 나눔 게시글 =====
    
    @Override
    public List<ShareToolVO> selectList(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".selectList", params);
    }
    
    @Override
    public int selectTotalCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalCount", params);
    }
    
    @Override
    public List<ShareToolVO> searchByKeyword(Map<String, Object> params) {
        return sqlSession.selectList(NAMESPACE + ".searchByKeyword", params);
    }
    
    @Override
    public int selectSearchCount(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".selectSearchCount", params);
    }
    
    @Override
    public ShareToolVO selectById(int shareToolId) {
        return sqlSession.selectOne(NAMESPACE + ".selectById", shareToolId);
    }
    
    @Override
    public int insertShareTool(ShareToolVO shareTool) {
        return sqlSession.insert(NAMESPACE + ".insertShareTool", shareTool);
    }
    
    @Override
    public int updateShareTool(ShareToolVO shareTool) {
        return sqlSession.update(NAMESPACE + ".updateShareTool", shareTool);
    }
    
    @Override
    public int deleteShareTool(int shareToolId) {
        return sqlSession.delete(NAMESPACE + ".deleteShareTool", shareToolId);
    }
    
    @Override
    public int checkAuthor(Map<String, Object> params) {
        return sqlSession.selectOne(NAMESPACE + ".checkAuthor", params);
    }
    
    @Override
    public int updateStatusComplete(int shareToolId) {
        return sqlSession.update(NAMESPACE + ".updateStatusComplete", shareToolId);
    }
    
    // ===== 요리도구 나눔 이미지 =====
    
    @Override
    public List<ShareToolImageVO> selectImagesByShareToolId(int shareToolId) {
        return sqlSession.selectList(NAMESPACE + ".selectImagesByShareToolId", shareToolId);
    }
    
    @Override
    public int insertImage(ShareToolImageVO image) {
        return sqlSession.insert(NAMESPACE + ".insertImage", image);
    }
    
    @Override
    public int deleteImagesByShareToolId(int shareToolId) {
        return sqlSession.delete(NAMESPACE + ".deleteImagesByShareToolId", shareToolId);
    }
    
    @Override
    public int deleteImageById(int imageId) {
        return sqlSession.delete(NAMESPACE + ".deleteImageById", imageId);
    }
}
