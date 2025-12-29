package com.moc.pro.common.dao;

import com.moc.pro.common.vo.CommonCodeVO;
import com.moc.pro.common.vo.RegionVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Common DAO 구현체
 */
@Repository
public class CommonDAOImpl implements CommonDAO {
    
    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "com.moc.pro.common.CommonMapper";
    
    // ===== 공통코드 =====
    
    @Override
    public List<CommonCodeVO> selectByMasterCode(String masterCode) {
        return sqlSession.selectList(NAMESPACE + ".selectByMasterCode", masterCode);
    }
    
    // ===== 지역 =====
    
    @Override
    public List<RegionVO> selectProvinces() {
        return sqlSession.selectList(NAMESPACE + ".selectProvinces");
    }
    
    @Override
    public List<RegionVO> selectCitiesByRegion(String regionCode) {
        return sqlSession.selectList(NAMESPACE + ".selectCitiesByRegion", regionCode);
    }
}
